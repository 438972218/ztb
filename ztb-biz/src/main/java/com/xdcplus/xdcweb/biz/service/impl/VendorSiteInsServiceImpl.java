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
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorSiteInsDetail;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorSiteInsDetailUser;
import com.xdcplus.xdcweb.biz.common.pojo.vo.*;
import com.xdcplus.xdcweb.biz.generator.impl.VendorSiteInsBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorSiteInsMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorSiteIns;
import com.xdcplus.xdcweb.biz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 供应商现场考察表(VendorSiteIns)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:40:14
 */
@Slf4j
@Service("vendorSiteInsService")
public class VendorSiteInsServiceImpl extends VendorSiteInsBaseServiceImpl<VendorSiteIns, VendorSiteInsVO, VendorSiteIns, VendorSiteInsMapper> implements VendorSiteInsService {

    @Autowired
    private VendorSiteInsDetailService vendorSiteInsDetailService;

    @Autowired
    private VendorSiteInsUserService vendorSiteInsUserService;
    @Autowired
    private VendorSiteInsDetailUserService vendorSiteInsDetailUserService;

    @Autowired
    private VendorService vendorService;

    @Override
    public VendorSiteInsVO saveVendorSiteInsReturnVO(VendorSiteInsDTO vendorSiteInsDTO) {
        VendorSiteIns vendorSiteIns = vendorSiteInsMapper.selectById(vendorSiteInsDTO.getId());
        if (ObjectUtil.isNotNull(vendorSiteIns)) {
            log.error("saveVendorSiteIns() The VendorSiteIns already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorSiteIns = new VendorSiteIns();
        BeanUtil.copyProperties(vendorSiteInsDTO, vendorSiteIns);
        vendorSiteIns.setCreatedTime(DateUtil.current());
        vendorSiteIns.setDeleted(0);

        boolean result = this.save(vendorSiteIns);
        VendorSiteInsVO vendorSiteInsVO = BeanUtil.copyProperties(vendorSiteIns, VendorSiteInsVO.class);
        if (result) {
            if (CollectionUtil.isNotEmpty(vendorSiteInsDTO.getVendorSiteInsDetailDTOS())) {
                List<VendorSiteInsDetailDTO> vendorSiteInsDetailDTOS = vendorSiteInsDTO.getVendorSiteInsDetailDTOS();
                VendorSiteIns finalVendorSiteIns = vendorSiteIns;
                List<VendorSiteInsDetailVO> vendorSiteInsDetailVOS=new ArrayList<VendorSiteInsDetailVO>();

                vendorSiteInsDetailDTOS.forEach(vendorSiteInsDetailDTO -> {
                    vendorSiteInsDetailDTO.setVendorSiteInsId(finalVendorSiteIns.getId());
                    vendorSiteInsDetailDTO.setId(null);
                    vendorSiteInsDetailDTO.setCreatedTime(DateUtil.current());
                    vendorSiteInsDetailDTO.setDeleted(0);
                    vendorSiteInsDetailService.saveVendorSiteInsDetail(vendorSiteInsDetailDTO);
                    vendorSiteInsDetailVOS.add(BeanUtil.copyProperties(vendorSiteInsDetailDTO, VendorSiteInsDetailVO.class));
                });
                vendorSiteInsVO.setVendorSiteInsDetailVOS(vendorSiteInsDetailVOS);
            }

            if (CollectionUtil.isNotEmpty(vendorSiteInsDTO.getVendorSiteInsUserDTOS())) {
                List<VendorSiteInsUserDTO> vendorSiteInsUserDTOS = vendorSiteInsDTO.getVendorSiteInsUserDTOS();
                VendorSiteIns finalVendorSiteIns = vendorSiteIns;
                List<VendorSiteInsUserVO> vendorSiteInsUserVOS=new ArrayList<>();

                vendorSiteInsUserDTOS.forEach(vendorSiteInsUserDTO -> {
                    vendorSiteInsUserDTO.setVendorSiteInsId(finalVendorSiteIns.getId());
                    vendorSiteInsUserDTO.setId(null);
                    vendorSiteInsUserDTO.setCreatedUser(AuthUtils.getCurrentUser());
                    vendorSiteInsUserDTO.setCreatedTime(DateUtil.current());
                    vendorSiteInsUserDTO.setDeleted(0);
                    vendorSiteInsUserService.saveVendorSiteInsUser(vendorSiteInsUserDTO);
                    vendorSiteInsUserVOS.add(BeanUtil.copyProperties(vendorSiteInsDTO,VendorSiteInsUserVO.class));
                });
                vendorSiteInsVO.setVendorSiteInsUserVOS(vendorSiteInsUserVOS);
            }
        }


        if (result) {
            return vendorSiteInsVO;
        } else {
            return null;
        }
    }

    @Override
    public VendorSiteInsVO showVendorSiteIns(Long id) {
        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        VendorSiteInsVO vendorSiteInsVO = this.objectConversion(this.getById(id));

        Assert.notNull(vendorSiteInsVO, ResponseEnum.SITELNS_SELECT_FAIL.getMessage());
        if(vendorSiteInsVO.getVendorId()!=null){
            vendorSiteInsVO.setVendorVO(vendorService.queryVendorById(vendorSiteInsVO.getVendorId()));
        }
        VendorSiteInsDetailFilterDTO vendorSiteInsDetailFilterDTO = new VendorSiteInsDetailFilterDTO();
        vendorSiteInsDetailFilterDTO.setVendorSiteInsId(id);
        vendorSiteInsVO.setVendorSiteInsDetailVOS(vendorSiteInsDetailService.queryVendorSiteInsDetailVOList(vendorSiteInsDetailFilterDTO));

        VendorSiteInsUserFilterDTO vendorSiteInsUserFilterDTO=new VendorSiteInsUserFilterDTO();
        vendorSiteInsUserFilterDTO.setVendorSiteInsId(id);
        vendorSiteInsVO.setVendorSiteInsUserVOS(vendorSiteInsUserService.queryVendorSiteInsUserVOList(vendorSiteInsUserFilterDTO));

        return vendorSiteInsVO;
    }

    @Override
    public Boolean updateVendorSiteInsWithDetail(VendorSiteInsDTO vendorSiteInsDTO) {
        VendorSiteIns vendorSiteIns = this.getById(vendorSiteInsDTO.getId());

        if (ObjectUtil.isNull(vendorSiteIns)) {
            log.error("updateVendorSiteIns() The VendorSiteIns does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        BeanUtil.copyProperties(vendorSiteInsDTO, vendorSiteIns);
        vendorSiteIns.setUpdatedUser(vendorSiteInsDTO.getUpdatedUser());
        vendorSiteIns.setUpdatedTime(DateUtil.current());
        Boolean result = this.updateById(vendorSiteIns);

        vendorSiteInsDetailService.deleteVendorSiteInsBySiteInsId(vendorSiteInsDTO.getId());
        vendorSiteInsUserService.deleteVendorSiteInsBySiteInsId(vendorSiteInsDTO.getId());

        if (CollectionUtil.isEmpty(vendorSiteInsDTO.getVendorSiteInsDetailDTOS())) {
            return result;
        }
        List<VendorSiteInsDetailDTO> vendorSiteInsDetailDTOS = vendorSiteInsDTO.getVendorSiteInsDetailDTOS();
        vendorSiteInsDetailDTOS.forEach(vendorSiteInsDetailDTO -> {
            vendorSiteInsDetailDTO.setVendorSiteInsId(vendorSiteInsDTO.getId());
            vendorSiteInsDetailDTO.setId(null);
            vendorSiteInsDetailDTO.setDeleted(0);
            vendorSiteInsDetailService.saveVendorSiteInsDetail(vendorSiteInsDetailDTO);
        });

        if (CollectionUtil.isEmpty(vendorSiteInsDTO.getVendorSiteInsUserDTOS())) {
            return result;
        }

        List<VendorSiteInsUserDTO> vendorSiteInsUserDTOS=vendorSiteInsDTO.getVendorSiteInsUserDTOS();
        vendorSiteInsUserDTOS.forEach(vendorSiteInsUserDTO -> {
            vendorSiteInsUserDTO.setVendorSiteInsId(vendorSiteInsDTO.getId());
            vendorSiteInsUserDTO.setId(null);
            vendorSiteInsUserDTO.setDeleted(0);
            vendorSiteInsUserService.saveVendorSiteInsUser(vendorSiteInsUserDTO);
        });
        return null;
    }

    @Override
    public PageVO<VendorSiteInsVO> queryVendorSiteInsWithRequest(VendorSiteInsFilterDTO vendorSiteInsFilterDTO) {
        PageVO<VendorSiteInsVO> pageVO = new PageVO<>();

        if (vendorSiteInsFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorSiteInsFilterDTO.getCurrentPage(), vendorSiteInsFilterDTO.getPageSize(),
                    vendorSiteInsFilterDTO.getOrderType(), vendorSiteInsFilterDTO.getOrderField());
        }

        List<VendorSiteInsVO> vendorSiteInsVOS= queryVendorSiteInsVOList(new VendorSiteInsFilterDTO());
        if(CollectionUtil.isEmpty(vendorSiteInsVOS)){
            return null;
        }
        for (VendorSiteInsVO vendorSiteInsVO : vendorSiteInsVOS) {
            if (vendorSiteInsVO.getRequestId() == null || vendorSiteInsVO.getRequestId() == 0) {
                vendorSiteInsVO.setRequestStatusName(ZtbConstant.TOBESUBMITTED);
            } else {
                //表单状态
                RequestVO requestVO = IeRequestResponseUtils.queryRequestById(vendorSiteInsVO.getRequestId());
                vendorSiteInsVO.setOddNumber(requestVO.getOddNumber());
                vendorSiteInsVO.setRequestStatusName(requestVO.getStatus().getName());
            }
        }

        PageInfo<VendorSiteInsVO> pageInfo = new PageInfo<>(vendorSiteInsVOS);
        PropertyUtils.copyProperties(pageInfo, pageVO, vendorSiteInsVOS);

        return pageVO;

    }

    @Override
    public void calculateScore(Long requestId) {
      VendorSiteIns vendorSiteIns= vendorSiteInsMapper.selectOne(new QueryWrapper<VendorSiteIns>().eq("request_id",requestId));
      if(ObjectUtil.isNull(vendorSiteIns)){
          log.error("calculateScore() The VendorSiteIns does not exist or has been deleted");
          throw new InvException(ResponseEnum.ERROR);
      }
        VendorSiteInsDetailFilterDTO vendorSiteInsDetailFilterDTO=  new VendorSiteInsDetailFilterDTO();
        vendorSiteInsDetailFilterDTO.setVendorSiteInsId(vendorSiteIns.getId());
        List<VendorSiteInsDetailVO> vendorSiteInsDetailVOS = vendorSiteInsDetailService.queryVendorSiteInsDetailVOList(vendorSiteInsDetailFilterDTO);

        if(CollectionUtil.isEmpty(vendorSiteInsDetailVOS)){
            log.error("calculateScore() The VendorSiteInsDetails does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        //找到每个明细的打分
        vendorSiteInsDetailVOS.forEach(vendorSiteInsDetailVO -> {
            VendorSiteInsDetailUserFilterDTO vendorSiteInsDetailUserFilterDTO=new VendorSiteInsDetailUserFilterDTO();
            vendorSiteInsDetailUserFilterDTO.setVendorSiteInsDetailId(vendorSiteInsDetailVO.getId());
            List<VendorSiteInsDetailUserVO> vendorSiteInsDetailUserVOS=vendorSiteInsDetailUserService.queryVendorSiteInsDetailUserVOList(vendorSiteInsDetailUserFilterDTO);
            Double score = vendorSiteInsDetailUserVOS.stream().map(VendorSiteInsDetailUserVO::getScore).mapToInt(Integer::parseInt).average().getAsDouble();//平均值
            VendorSiteInsDetail vendorSiteInsDetail=BeanUtil.copyProperties(vendorSiteInsDetailVO, VendorSiteInsDetail.class);
            vendorSiteInsDetail.setScore(String.valueOf(score));
            vendorSiteInsDetailService.updateById(vendorSiteInsDetail);
        });

    }

    @Override
    public  List<VendorSiteInsUserVO> getVendorSiteInsUserVosByRequestId(Long requestId) {
        VendorSiteIns vendorSiteIns= vendorSiteInsMapper.selectOne(new QueryWrapper<VendorSiteIns>().eq("request_id",requestId));
        if(ObjectUtil.isNull(vendorSiteIns)){
            log.error("getVendorSiteInsUserVosByRequestId() The VendorSiteIns does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        VendorSiteInsUserFilterDTO vendorSiteInsUserFilterDTO=  new VendorSiteInsUserFilterDTO();
        vendorSiteInsUserFilterDTO.setVendorSiteInsId(vendorSiteIns.getId());
        List<VendorSiteInsUserVO> vendorSiteInsUserVOS = vendorSiteInsUserService.queryVendorSiteInsUserVOList(vendorSiteInsUserFilterDTO);
        return vendorSiteInsUserVOS;
    }
}
