package com.xdcplus.xdcweb.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
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
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQualifyReviewDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQualifyReviewDetailDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQualifyReviewDetailFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQualifyReviewFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.RequestVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQualifyReviewDetailVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQuestionVO;
import com.xdcplus.xdcweb.biz.generator.impl.VendorQualifyReviewBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorQualifyReviewMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQualifyReview;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQualifyReviewVO;
import com.xdcplus.xdcweb.biz.service.VendorQualifyReviewDetailService;
import com.xdcplus.xdcweb.biz.service.VendorQualifyReviewService;
import com.xdcplus.xdcweb.biz.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 供应商合格评审表(VendorQualifyReview)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:52
 */
@Slf4j
@Service("vendorQualifyReviewService")
public class VendorQualifyReviewServiceImpl extends VendorQualifyReviewBaseServiceImpl<VendorQualifyReview, VendorQualifyReviewVO, VendorQualifyReview, VendorQualifyReviewMapper> implements VendorQualifyReviewService {

    @Autowired
    private VendorQualifyReviewDetailService vendorQualifyReviewDetailService;

    @Autowired
    private VendorService vendorService;

    @Override
    public VendorQualifyReviewVO saveVendorQualifyReviewReturnVO(VendorQualifyReviewDTO vendorQualifyReviewDTO) {
        VendorQualifyReview vendorQualifyReview = vendorQualifyReviewMapper.selectById(vendorQualifyReviewDTO.getId());
        if (ObjectUtil.isNotNull(vendorQualifyReview)) {
            log.error("saveVendorQualifyReview() The VendorQualifyReview already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorQualifyReview = new VendorQualifyReview();
        BeanUtil.copyProperties(vendorQualifyReviewDTO, vendorQualifyReview);
        vendorQualifyReview.setCreatedTime(DateUtil.current());
        vendorQualifyReview.setDeleted(0);

        boolean result = this.save(vendorQualifyReview);
        VendorQualifyReviewVO vendorQualifyReviewVO = BeanUtil.copyProperties(vendorQualifyReview, VendorQualifyReviewVO.class);

        if (result) {
            if (CollectionUtil.isNotEmpty(vendorQualifyReviewDTO.getVendorQualifyReviewDetailDTOS())) {
                List<VendorQualifyReviewDetailDTO> vendorQualifyReviewDetailDTOS = vendorQualifyReviewDTO.getVendorQualifyReviewDetailDTOS();
                VendorQualifyReview finalVendorQualifyReview = vendorQualifyReview;
                List<VendorQualifyReviewDetailVO> vendorQualifyReviewDetailVOS = new ArrayList<>();
                vendorQualifyReviewDetailDTOS.forEach(vendorQualifyReviewDetailDTO -> {
                    vendorQualifyReviewDetailDTO.setVendorQualifyReviewId(finalVendorQualifyReview.getId());
                    vendorQualifyReviewDetailDTO.setId(null);
                    vendorQualifyReviewDetailDTO.setCreatedTime(DateUtil.current());
                    vendorQualifyReviewDetailDTO.setCreatedUser(AuthUtils.getCurrentUser());
                    vendorQualifyReviewDetailDTO.setDeleted(0);
                    vendorQualifyReviewDetailService.saveVendorQualifyReviewDetail(vendorQualifyReviewDetailDTO);
                    vendorQualifyReviewDetailVOS.add(BeanUtil.copyProperties(vendorQualifyReviewDetailDTO,VendorQualifyReviewDetailVO.class));
                });
                vendorQualifyReviewVO.setVendorQualifyReviewDetailVOS(vendorQualifyReviewDetailVOS);
            }
        }

        if (result) {
            return vendorQualifyReviewVO;
        } else {
            return null;
        }
    }

    @Override
    public Boolean updateVendorQualifyReviewWithDetail(VendorQualifyReviewDTO vendorQualifyReviewDTO) {
        VendorQualifyReview vendorQualifyReview = this.getById(vendorQualifyReviewDTO.getId());
        if (ObjectUtil.isNull(vendorQualifyReview)) {
            log.error("updateVendorQualifyReview() The VendorQualifyReview does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorQualifyReviewDTO, vendorQualifyReview);
        vendorQualifyReview.setUpdatedUser(vendorQualifyReviewDTO.getUpdatedUser());
        vendorQualifyReview.setUpdatedTime(DateUtil.current());
        Boolean result = this.updateById(vendorQualifyReview);
        vendorQualifyReviewDetailService.deleteVendorQualifyReviewDetailByReviewId(vendorQualifyReviewDTO.getId());
        if (CollectionUtil.isEmpty(vendorQualifyReviewDTO.getVendorQualifyReviewDetailDTOS())) {
            return result;
        }
        List<VendorQualifyReviewDetailDTO> vendorQualifyReviewDetailDTOS = vendorQualifyReviewDTO.getVendorQualifyReviewDetailDTOS();
        vendorQualifyReviewDetailDTOS.forEach(vendorQualifyReviewDetailDTO -> {
            vendorQualifyReviewDetailDTO.setVendorQualifyReviewId(vendorQualifyReviewDTO.getId());
            vendorQualifyReviewDetailService.saveVendorQualifyReviewDetail(vendorQualifyReviewDetailDTO);
        });


        return result;
    }

    @Override
    public VendorQualifyReviewVO showVendorQualifyReview(Long id) {
        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        VendorQualifyReviewVO vendorQualifyReviewVO = this.objectConversion(this.getById(id));

        Assert.notNull(vendorQualifyReviewVO, ResponseEnum.SITELNS_SELECT_FAIL.getMessage());

        vendorQualifyReviewVO.setVendorVO(vendorService.queryVendorById(vendorQualifyReviewVO.getVendorId()));

        VendorQualifyReviewDetailFilterDTO vendorQualifyReviewDetailFilterDTO = new VendorQualifyReviewDetailFilterDTO();
        vendorQualifyReviewDetailFilterDTO.setVendorQualifyReviewId(id);
        vendorQualifyReviewVO.setVendorQualifyReviewDetailVOS(vendorQualifyReviewDetailService.queryVendorQualifyReviewDetailVOList(vendorQualifyReviewDetailFilterDTO));

        return vendorQualifyReviewVO;
    }

    @Override
    public PageVO<VendorQualifyReviewVO> queryVendorQualifyReviewWithRequest(VendorQualifyReviewFilterDTO vendorQualifyReviewFilterDTO) {
        PageVO<VendorQualifyReviewVO> pageVO = new PageVO<>();

        if (vendorQualifyReviewFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorQualifyReviewFilterDTO.getCurrentPage(), vendorQualifyReviewFilterDTO.getPageSize(),
                    vendorQualifyReviewFilterDTO.getOrderType(), vendorQualifyReviewFilterDTO.getOrderField());
        }

        List<VendorQualifyReviewVO> vendorQualifyReviewVOS= queryVendorQualifyReviewVOList(new VendorQualifyReviewFilterDTO());
        if(CollectionUtil.isEmpty(vendorQualifyReviewVOS)){
            return null;
        }
        for (VendorQualifyReviewVO vendorQualifyReviewVO : vendorQualifyReviewVOS) {
            if (vendorQualifyReviewVO.getRequestId() == null || vendorQualifyReviewVO.getRequestId() == 0) {
                vendorQualifyReviewVO.setRequestStatusName(ZtbConstant.TOBESUBMITTED);
            } else {
                //表单状态
                RequestVO requestVO = IeRequestResponseUtils.queryRequestById(vendorQualifyReviewVO.getRequestId());
                vendorQualifyReviewVO.setOddNumber(requestVO.getOddNumber());
                vendorQualifyReviewVO.setRequestStatusName(requestVO.getStatus().getName());
            }
        }

        PageInfo<VendorQualifyReviewVO> pageInfo = new PageInfo<>(vendorQualifyReviewVOS);
        PropertyUtils.copyProperties(pageInfo, pageVO, vendorQualifyReviewVOS);

        return pageVO;
    }

}
