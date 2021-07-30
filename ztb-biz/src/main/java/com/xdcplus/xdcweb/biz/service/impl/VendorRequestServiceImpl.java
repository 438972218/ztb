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
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorRequestCertificateDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorRequestCertificateFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorRequestDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorRequestFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.Vendor;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorCertificate;
import com.xdcplus.xdcweb.biz.common.pojo.vo.RequestVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorRequestCertificateVO;
import com.xdcplus.xdcweb.biz.generator.impl.VendorRequestBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorRequestMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorRequest;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorRequestVO;
import com.xdcplus.xdcweb.biz.service.VendorCertificateService;
import com.xdcplus.xdcweb.biz.service.VendorRequestCertificateService;
import com.xdcplus.xdcweb.biz.service.VendorRequestService;
import com.xdcplus.xdcweb.biz.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 供应商注册单(VendorRequest)表服务实现类
 *
 * @author makejava
 * @since 2021-07-27 11:08:57
 */
@Slf4j
@Service("vendorRequestService")
public class VendorRequestServiceImpl extends VendorRequestBaseServiceImpl<VendorRequest, VendorRequestVO, VendorRequest, VendorRequestMapper> implements VendorRequestService {

    @Autowired
    private VendorRequestMapper vendorRequestMapper;
    @Autowired
    private VendorRequestCertificateService vendorRequestCertificateService;
    @Autowired
    private VendorService vendorService;
    @Autowired
    private VendorCertificateService vendorCertificateService;

    @Override
    public PageVO<VendorRequestVO> queryVendorRequestWithRequest(VendorRequestFilterDTO vendorRequestFilterDTO) {
        PageVO<VendorRequestVO> pageVO = new PageVO<>();

        if (vendorRequestFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorRequestFilterDTO.getCurrentPage(), vendorRequestFilterDTO.getPageSize(),
                    vendorRequestFilterDTO.getOrderType(), vendorRequestFilterDTO.getOrderField());
        }

        //得到requestId不为空的BidSheet
        List<VendorRequest> vendorRequestList = vendorRequestMapper.selectList(new QueryWrapper<VendorRequest>().isNotNull("request_id"));
//        List<VendorRequestVO> vendorRequestVOS= queryVendorRequestVOList(null);

        if(CollectionUtil.isEmpty(vendorRequestList)){
            return null;
        }
        List<VendorRequestVO> vendorRequestVOS = new ArrayList<>();
        for (VendorRequest vendorRequest : vendorRequestList) {
            VendorRequestVO vendorRequestVO = BeanUtil.copyProperties(vendorRequest, VendorRequestVO.class);
            if (vendorRequestVO.getRequestId() == null || vendorRequestVO.getRequestId() == 0) {
                vendorRequestVO.setRequestStatusName(ZtbConstant.TOBESUBMITTED);
            } else {
                //表单状态
                RequestVO requestVO = IeRequestResponseUtils.queryRequestById(vendorRequestVO.getRequestId());
                vendorRequestVO.setOddNumber(requestVO.getOddNumber());
                vendorRequestVO.setRequestStatusName(requestVO.getStatus().getName());
            }
            vendorRequestVOS.add(vendorRequestVO);
        }

        PageInfo<VendorRequestVO> pageInfo = new PageInfo<>(vendorRequestVOS);
        PropertyUtils.copyProperties(pageInfo, pageVO, vendorRequestVOS);

        return pageVO;
    }

    @Override
    public Boolean updateVendorRequestWithDetail(VendorRequestDTO vendorRequestDTO) {
        VendorRequest vendorRequest = this.getById(vendorRequestDTO.getId());
        if (ObjectUtil.isNull(vendorRequest)) {
            log.error("updateVendorRequest() The VendorRequest does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorRequestDTO, vendorRequest);
        vendorRequestDTO.setUpdatedUser(vendorRequestDTO.getUpdatedUser());
        vendorRequestDTO.setUpdatedTime(DateUtil.current());
        Boolean result = this.updateById(vendorRequest);

        vendorRequestCertificateService.deleteVendorRequestCertificateByRequestId(vendorRequestDTO.getId());

        if (CollectionUtil.isEmpty(vendorRequestDTO.getVendorRequestCertificateDTOS())) {
            return result;
        }

        List<VendorRequestCertificateDTO> vendorRequestCertificateDTOS = vendorRequestDTO.getVendorRequestCertificateDTOS();
        vendorRequestCertificateDTOS.forEach(vendorRequestCertificateDTO -> {
            vendorRequestCertificateDTO.setVendorRequestId(vendorRequestDTO.getId());
            vendorRequestCertificateService.saveVendorRequestCertificate(vendorRequestCertificateDTO);
        });

        return result;
    }

    @Override
    public VendorRequestVO showVendorRequestById(Long id) {
        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        VendorRequestVO vendorRequestVO = this.objectConversion(this.getById(id));

        if(ObjectUtil.isNull(vendorRequestVO)){
            return null;
        }

        VendorRequestCertificateFilterDTO vendorRequestCertificateFilterDTO = new VendorRequestCertificateFilterDTO();
        vendorRequestCertificateFilterDTO.setVendorRequestId(id);
        vendorRequestVO.setVendorRequestCertificateVOS(vendorRequestCertificateService.queryVendorRequestCertificateVOList(vendorRequestCertificateFilterDTO));

        return vendorRequestVO;
    }

    @Override
    public VendorRequestVO saveVendorQuestionReturnVO(VendorRequestDTO vendorRequestDTO) {
        VendorRequest vendorRequest = vendorRequestMapper.selectById(vendorRequestDTO.getId());
        if (ObjectUtil.isNotNull(vendorRequest)) {
            log.error("saveVendorRequest() The VendorRequest already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorRequest = new VendorRequest();
        BeanUtil.copyProperties(vendorRequestDTO, vendorRequest);
        vendorRequest.setCreatedTime(DateUtil.current());
        vendorRequest.setDeleted(0);

        Boolean result= this.save(vendorRequest);
        VendorRequestVO vendorRequestVO=BeanUtil.copyProperties(vendorRequest,VendorRequestVO.class);
        if(result){
            if(CollectionUtil.isNotEmpty(vendorRequestDTO.getVendorRequestCertificateDTOS())){
                List<VendorRequestCertificateDTO> vendorRequestCertificateDTOS=vendorRequestDTO.getVendorRequestCertificateDTOS();
                VendorRequest finalVendorRequest=vendorRequest;
                List<VendorRequestCertificateVO> vendorRequestCertificateVOS=new ArrayList<>();
                vendorRequestCertificateDTOS.forEach(vendorRequestCertificateDTO -> {
                    vendorRequestCertificateDTO.setId(null);
                    vendorRequestCertificateDTO.setVendorRequestId(finalVendorRequest.getId());
                    vendorRequestCertificateDTO.setCreatedUser(AuthUtils.getCurrentUser());
                    vendorRequestCertificateService.saveVendorRequestCertificate(vendorRequestCertificateDTO);
                    vendorRequestCertificateVOS.add(BeanUtil.copyProperties(vendorRequestCertificateDTO,VendorRequestCertificateVO.class));
                });
                vendorRequestVO.setVendorRequestCertificateVOS(vendorRequestCertificateVOS);
            }
        }
        if(result){
            return vendorRequestVO;
        }else{
            return null;
        }
    }

    @Override
    public void saveVendorByRequestId(Long requestId) {
        VendorRequestFilterDTO vendorRequestFilterDTO=new VendorRequestFilterDTO();
        vendorRequestFilterDTO.setRequestId(requestId);
        List<VendorRequestVO> vendorRequestVOS=queryVendorRequestVOList(vendorRequestFilterDTO);
        if(CollectionUtil.isNotEmpty(vendorRequestVOS)){
            VendorRequestVO vendorRequestVO=vendorRequestVOS.get(0);
            Vendor vendor= BeanUtil.copyProperties(vendorRequestVO, Vendor.class);
            vendor.setId(vendorRequestVO.getVendorId());
            vendor.setStatus("已过审");

            if(vendor.getId()==null){
                vendorService.save(vendor);
            }else{
                vendorService.updateById(vendor);
            }

            Vendor finalVendor=vendor;
            //删除对应的证件
            vendorCertificateService.deleteVendorCertificateByVendorId(finalVendor.getId());

            VendorRequestCertificateFilterDTO vendorRequestCertificateFilterDTO = new VendorRequestCertificateFilterDTO();
            vendorRequestCertificateFilterDTO.setVendorRequestId(vendorRequestVO.getId());
            List<VendorRequestCertificateVO> vendorRequestCertificateVOS = vendorRequestCertificateService.queryVendorRequestCertificateVOList(vendorRequestCertificateFilterDTO);

            if(CollectionUtil.isNotEmpty(vendorRequestCertificateVOS)){
                vendorRequestCertificateVOS.forEach(vendorRequestCertificateVO -> {
                    VendorCertificate vendorCertificate=BeanUtil.copyProperties(vendorRequestCertificateVO,VendorCertificate.class);
                    vendorCertificate.setId(null);
                    vendorCertificate.setVendorId(finalVendor.getId());
                    vendorCertificateService.save(vendorCertificate);
                });
            }


        }

    }
}
