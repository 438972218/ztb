package com.xdcplus.xdcweb.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.xdcplus.mp.utils.AuthUtils;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.tool.utils.PageableUtils;
import com.xdcplus.tool.utils.PropertyUtils;
import com.xdcplus.xdcweb.biz.common.constants.ZtbConstant;
import com.xdcplus.xdcweb.biz.common.enums.ResponseEnum;
import com.xdcplus.xdcweb.biz.common.exceptions.InvException;
import com.xdcplus.xdcweb.biz.common.interaction.IeRequestResponseUtils;
import com.xdcplus.xdcweb.biz.common.pojo.dto.*;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorKpiDetail;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorSiteIns;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorSiteInsDetail;
import com.xdcplus.xdcweb.biz.common.pojo.vo.*;
import com.xdcplus.xdcweb.biz.generator.impl.VendorKpiBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorKpiMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorKpi;
import com.xdcplus.xdcweb.biz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 供应商绩效考核表(VendorKpi)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:42
 */
@Slf4j
@Service("vendorKpiService")
public class VendorKpiServiceImpl extends VendorKpiBaseServiceImpl<VendorKpi, VendorKpiVO, VendorKpi, VendorKpiMapper> implements VendorKpiService {

    @Autowired
    private VendorService vendorService;

    @Autowired
    private VendorKpiDetailService vendorKpiDetailService;
    @Autowired
    private VendorKpiDetailUserService vendorKpiDetailUserService;

    @Autowired
    private VendorKpiUserService vendorKpiUserService;

    @Override
    public VendorKpiVO showVendorKpiById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        VendorKpiVO vendorKpiVO = this.objectConversion(this.getById(id));

        VendorKpiDetailFilterDTO vendorKpiDetailFilterDTO = new VendorKpiDetailFilterDTO();
        vendorKpiDetailFilterDTO.setVendorKpiId(id);
        vendorKpiVO.setVendorKpiDetailVOS(vendorKpiDetailService.queryVendorKpiDetailVOList(vendorKpiDetailFilterDTO));

        VendorKpiUserFilterDTO vendorKpiUserFilterDTO = new VendorKpiUserFilterDTO();
        vendorKpiUserFilterDTO.setVendorKpiId(id);
        vendorKpiVO.setVendorKpiUserVOS(vendorKpiUserService.queryVendorKpiUserVOList(vendorKpiUserFilterDTO));

        return vendorKpiVO;
    }

    @Override
    public Boolean updateVendorKpiWithDetail(VendorKpiDTO vendorKpiDTO) {
        VendorKpi vendorKpi = this.getById(vendorKpiDTO.getId());
        if (ObjectUtil.isNull(vendorKpi)) {
            log.error("");
            throw new InvException(ResponseEnum.ERROR);
        }
        BeanUtil.copyProperties(vendorKpiDTO, vendorKpi);

        vendorKpi.setUpdatedUser(vendorKpiDTO.getUpdatedUser());
        vendorKpi.setUpdatedTime(DateUtil.current());
        Boolean result = this.updateById(vendorKpi);
        vendorKpiDetailService.deleteVendorKpiDetailByKpiId(vendorKpiDTO.getId());
        vendorKpiUserService.deleteVendorKpiUserByKpiId(vendorKpiDTO.getId());

        if (CollectionUtil.isEmpty(vendorKpiDTO.getVendorKpiDetailDTOS())) {
            return result;
        }

        List<VendorKpiDetailDTO> vendorKpiDetailDTOS = vendorKpiDTO.getVendorKpiDetailDTOS();
        vendorKpiDetailDTOS.forEach(vendorKpiDetailDTO -> {
            vendorKpiDetailDTO.setVendorKpiId(vendorKpiDTO.getId());
            vendorKpiDetailDTO.setId(null);
            vendorKpiDetailDTO.setDeleted(0);
            vendorKpiDetailService.saveVendorKpiDetail(vendorKpiDetailDTO);
        });

        if (CollectionUtil.isEmpty(vendorKpiDTO.getVendorKpiUserDTOS())) {
            return result;
        }
        List<VendorKpiUserDTO> vendorKpiUserDTOS = vendorKpiDTO.getVendorKpiUserDTOS();
        vendorKpiUserDTOS.forEach(vendorKpiUserDTO -> {
            vendorKpiUserDTO.setVendorKpiId(vendorKpiDTO.getId());
            vendorKpiUserDTO.setId(null);
            vendorKpiUserDTO.setDeleted(0);
            vendorKpiUserService.saveVendorKpiUser(vendorKpiUserDTO);
        });
        return result;
    }

    @Override
    public VendorKpiVO saveVendorKpiReturnVO(VendorKpiDTO vendorKpiDTO) {

        VendorKpi vendorKpi = vendorKpiMapper.selectById(vendorKpiDTO.getId());
        if (ObjectUtil.isNotNull(vendorKpi)) {
            log.error("saveVendorKpi() The VendorKpi already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorKpi = new VendorKpi();
        BeanUtil.copyProperties(vendorKpiDTO, vendorKpi);
        vendorKpi.setCreatedTime(DateUtil.current());
        vendorKpi.setDeleted(0);
        boolean result = this.save(vendorKpi);
        VendorKpiVO vendorKpiVO=BeanUtil.copyProperties(vendorKpi,VendorKpiVO.class);
        if (result) {
            if (CollectionUtil.isNotEmpty(vendorKpiDTO.getVendorKpiDetailDTOS())) {
                List<VendorKpiDetailDTO> vendorKpiDetailDTOS = vendorKpiDTO.getVendorKpiDetailDTOS();
                List<VendorKpiDetailVO> vendorKpiDetailVOS=new ArrayList<>();

                VendorKpi finalVendorKpi = vendorKpi;
                vendorKpiDetailDTOS.forEach(vendorKpiDetailDTO -> {
                    vendorKpiDetailDTO.setVendorKpiId(finalVendorKpi.getId());
                    vendorKpiDetailDTO.setId(null);
                    vendorKpiDetailDTO.setCreatedTime(DateUtil.current());
                    vendorKpiDetailDTO.setCreatedUser(AuthUtils.getCurrentUser());
                    vendorKpiDetailDTO.setDeleted(0);
                    vendorKpiDetailService.saveVendorKpiDetail(vendorKpiDetailDTO);
                    vendorKpiDetailVOS.add(BeanUtil.copyProperties(vendorKpiDTO,VendorKpiDetailVO.class));
                });
                vendorKpiVO.setVendorKpiDetailVOS(vendorKpiDetailVOS);
            }
            if (CollectionUtil.isNotEmpty(vendorKpiDTO.getVendorKpiUserDTOS())) {
                List<VendorKpiUserDTO> vendorKpiUserDTOS = vendorKpiDTO.getVendorKpiUserDTOS();
                VendorKpi finalVendorKpi = vendorKpi;
                List<VendorKpiUserVO> vendorKpiUserVOS=new ArrayList<>();
                vendorKpiUserDTOS.forEach(vendorKpiUserDTO -> {
                    vendorKpiUserDTO.setVendorKpiId(finalVendorKpi.getId());
                    vendorKpiUserDTO.setId(null);
                    vendorKpiUserDTO.setDeleted(0);
                    vendorKpiUserService.saveVendorKpiUser(vendorKpiUserDTO);
                    vendorKpiUserVOS.add(BeanUtil.copyProperties(vendorKpiDTO,VendorKpiUserVO.class));
                });
                vendorKpiVO.setVendorKpiUserVOS(vendorKpiUserVOS);
            }
        }
        if (result) {
            return vendorKpiVO;
        }else{
            return null;
        }
    }

    @Override
    public PageVO<VendorKpiVO> queryVendorKpiWithRequest(VendorKpiFilterDTO vendorKpiFilterDTO) {
        PageVO<VendorKpiVO> pageVO = new PageVO<>();

        if (vendorKpiFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorKpiFilterDTO.getCurrentPage(), vendorKpiFilterDTO.getPageSize(),
                    vendorKpiFilterDTO.getOrderType(), vendorKpiFilterDTO.getOrderField());
        }

        List<VendorKpiVO> vendorKpiVOS= queryVendorKpiVOList(new VendorKpiFilterDTO());
        if(CollectionUtil.isEmpty(vendorKpiVOS)){
            return null;
        }
        for (VendorKpiVO vendorKpiVO : vendorKpiVOS) {
            if (vendorKpiVO.getRequestId() == null || vendorKpiVO.getRequestId() == 0) {
                vendorKpiVO.setRequestStatusName(ZtbConstant.TOBESUBMITTED);
            } else {
                //表单状态
                RequestVO requestVO = IeRequestResponseUtils.queryRequestById(vendorKpiVO.getRequestId());
                vendorKpiVO.setOddNumber(requestVO.getOddNumber());
                vendorKpiVO.setRequestStatusName(requestVO.getStatus().getName());
            }
        }

        PageInfo<VendorKpiVO> pageInfo = new PageInfo<>(vendorKpiVOS);
        PropertyUtils.copyProperties(pageInfo, pageVO, vendorKpiVOS);

        return pageVO;

    }


    @Override
    public void calculateScore(Long requestId) {
        VendorKpi vendorKpi= vendorKpiMapper.selectOne(new QueryWrapper<VendorKpi>().eq("request_id",requestId));
        if(ObjectUtil.isNull(vendorKpi)){
            log.error("calculateScore() The VendorKpi does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        VendorKpiDetailFilterDTO vendorKpiDetailFilterDTO=  new VendorKpiDetailFilterDTO();
        vendorKpiDetailFilterDTO.setVendorKpiId(vendorKpi.getId());
        List<VendorKpiDetailVO> vendorKpiDetailVOS = vendorKpiDetailService.queryVendorKpiDetailVOList(vendorKpiDetailFilterDTO);

        if(CollectionUtil.isEmpty(vendorKpiDetailVOS)){
            log.error("calculateScore() The VendorKpiDetails does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        
        //找到每个明细的打分
        vendorKpiDetailVOS.forEach(vendorKpiDetailVO -> {
            VendorKpiDetailUserFilterDTO vendorKpiDetailUserFilterDTO=new VendorKpiDetailUserFilterDTO();
            vendorKpiDetailUserFilterDTO.setVendorKpiDetailId(vendorKpiDetailVO.getId());
            List<VendorKpiDetailUserVO> vendorKpiDetailUserVOS=vendorKpiDetailUserService.queryVendorKpiDetailUserVOList(vendorKpiDetailUserFilterDTO);
            Double score = vendorKpiDetailUserVOS.stream().map(VendorKpiDetailUserVO::getScore).mapToInt(Integer::parseInt).average().getAsDouble();//平均值
            VendorKpiDetail vendorKpiDetail=BeanUtil.copyProperties(vendorKpiDetailVO, VendorKpiDetail.class);
            vendorKpiDetail.setScore(String.valueOf(score));
            vendorKpiDetailService.updateById(vendorKpiDetail);
        });

    }

    @Override
    public List<VendorKpiUserVO> getVendorKpiUserVosByRequestId(Long requestId) {

        VendorKpi vendorKpi= vendorKpiMapper.selectOne(new QueryWrapper<VendorKpi>().eq("request_id",requestId));
        if(ObjectUtil.isNull(vendorKpi)){
            log.error("getVendorKpiUserVosByRequestId() The VendorKpi does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        VendorKpiUserFilterDTO vendorKpiUserFilterDTO=  new VendorKpiUserFilterDTO();
        vendorKpiUserFilterDTO.setVendorKpiId(vendorKpi.getId());
        List<VendorKpiUserVO> vendorKpiUserVOS = vendorKpiUserService.queryVendorKpiUserVOList(vendorKpiUserFilterDTO);
        return vendorKpiUserVOS;
    }

}
