package com.xdcplus.xdcweb.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.tool.utils.PageableUtils;
import com.xdcplus.tool.utils.PropertyUtils;
import com.xdcplus.xdcweb.biz.common.constants.ZtbConstant;
import com.xdcplus.xdcweb.biz.common.enums.ResponseEnum;
import com.xdcplus.xdcweb.biz.common.exceptions.InvException;
import com.xdcplus.xdcweb.biz.common.interaction.IeRequestResponseUtils;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQuestionDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQuestionDetailDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQuestionDetailFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQuestionFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.RequestVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQuestionDetailVO;
import com.xdcplus.xdcweb.biz.generator.impl.VendorQuestionBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorQuestionMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQuestion;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQuestionVO;
import com.xdcplus.xdcweb.biz.service.VendorQuestionDetailService;
import com.xdcplus.xdcweb.biz.service.VendorQuestionService;
import com.xdcplus.xdcweb.biz.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 供应商电子调查表(VendorQuestion)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:58
 */
@Slf4j
@Service("vendorQuestionService")
public class VendorQuestionServiceImpl extends VendorQuestionBaseServiceImpl<VendorQuestion, VendorQuestionVO, VendorQuestion, VendorQuestionMapper> implements VendorQuestionService {

    @Autowired
    private VendorService vendorService;

    @Autowired
    private VendorQuestionDetailService vendorQuestionDetailService;

    @Override
    public VendorQuestionVO showVendorQuestionById(Long id) {
        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());
        VendorQuestionVO vendorQuestionVO = this.objectConversion(this.getById(id));

        VendorQuestionDetailFilterDTO vendorQuestionDetailFilterDTO = new VendorQuestionDetailFilterDTO();
        vendorQuestionDetailFilterDTO.setVendorQuestionId(id);
        vendorQuestionVO.setVendorQuestionDetailVOS(vendorQuestionDetailService.queryVendorQuestionDetailVOList(vendorQuestionDetailFilterDTO));

        return vendorQuestionVO;
    }

    @Override
    public Boolean updateVendorQuestionWithDetail(VendorQuestionDTO vendorQuestionDTO) {
        VendorQuestion vendorQuestion = this.getById(vendorQuestionDTO.getId());
        if (ObjectUtil.isNull(vendorQuestion)) {
            log.error("updateVendorQuestion() The VendorQuestion does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        BeanUtil.copyProperties(vendorQuestionDTO, vendorQuestion);
        vendorQuestion.setUpdatedUser(vendorQuestionDTO.getUpdatedUser());
        vendorQuestion.setUpdatedTime(DateUtil.current());
        Boolean result = this.updateById(vendorQuestion);
        vendorQuestionDetailService.deleteVendorQuestionDetailByQuestionId(vendorQuestionDTO.getId());
        if (CollectionUtil.isEmpty(vendorQuestionDTO.getVendorQuestionDetailDTOS())) {
            return result;
        }
        List<VendorQuestionDetailDTO> vendorQuestionDetailDTOS = vendorQuestionDTO.getVendorQuestionDetailDTOS();
        vendorQuestionDetailDTOS.forEach(vendorQuestionDetailDTO -> {
            vendorQuestionDetailDTO.setVendorQuestionId(vendorQuestionDTO.getId());
            vendorQuestionDetailService.saveVendorQuestionDetail(vendorQuestionDetailDTO);
        });

        return result;
    }

    @Override
    public VendorQuestionVO saveVendorQuestionReturnVO(VendorQuestionDTO vendorQuestionDTO) {
        VendorQuestion vendorQuestion = vendorQuestionMapper.selectById(vendorQuestionDTO.getId());
        if (ObjectUtil.isNotNull(vendorQuestion)) {
            log.error("saveVendorQuestion() The VendorQuestion already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorQuestion = new VendorQuestion();
        BeanUtil.copyProperties(vendorQuestionDTO, vendorQuestion);
        vendorQuestion.setCreatedTime(DateUtil.current());
        vendorQuestion.setDeleted(0);
        boolean result = this.save(vendorQuestion);
        VendorQuestionVO vendorQuestionVO = BeanUtil.copyProperties(vendorQuestion, VendorQuestionVO.class);
        if (result) {
            if (CollectionUtil.isNotEmpty(vendorQuestionDTO.getVendorQuestionDetailDTOS())) {
                List<VendorQuestionDetailDTO> vendorQuestionDetailDTOS = vendorQuestionDTO.getVendorQuestionDetailDTOS();
                VendorQuestion finalVendorQuestion = vendorQuestion;
                List<VendorQuestionDetailVO> vendorQuestionDetailVOS=new ArrayList<VendorQuestionDetailVO>();
                vendorQuestionDetailDTOS.forEach(vendorQuestionDetailDTO -> {
                    vendorQuestionDetailDTO.setVendorQuestionId(finalVendorQuestion.getId());
                    vendorQuestionDetailDTO.setId(null);
                    vendorQuestionDetailDTO.setDeleted(0);
                    vendorQuestionDetailService.saveVendorQuestionDetail(vendorQuestionDetailDTO);
                    vendorQuestionDetailVOS.add(BeanUtil.copyProperties(vendorQuestionDTO,VendorQuestionDetailVO.class));
                });
                vendorQuestionVO.setVendorQuestionDetailVOS(vendorQuestionDetailVOS);
            }
            return vendorQuestionVO;
        }
        return null;
    }

    @Override
    public PageVO<VendorQuestionVO> queryVendorQuestionWithRequest(VendorQuestionFilterDTO vendorQuestionFilterDTO) {
        PageVO<VendorQuestionVO> pageVO = new PageVO<>();

        if (vendorQuestionFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorQuestionFilterDTO.getCurrentPage(), vendorQuestionFilterDTO.getPageSize(),
                    vendorQuestionFilterDTO.getOrderType(), vendorQuestionFilterDTO.getOrderField());
        }

        List<VendorQuestionVO> vendorQuestionVOS= queryVendorQuestionVOList(new VendorQuestionFilterDTO());
        if(CollectionUtil.isEmpty(vendorQuestionVOS)){
            return null;
        }
        for (VendorQuestionVO vendorQuestionVO : vendorQuestionVOS) {
            if (vendorQuestionVO.getRequestId() == null || vendorQuestionVO.getRequestId() == 0) {
                vendorQuestionVO.setRequestStatusName(ZtbConstant.TOBESUBMITTED);
            } else {
                //表单状态
                RequestVO requestVO = IeRequestResponseUtils.queryRequestById(vendorQuestionVO.getRequestId());
                vendorQuestionVO.setOddNumber(requestVO.getOddNumber());
                vendorQuestionVO.setRequestStatusName(requestVO.getStatus().getName());
            }
        }

        PageInfo<VendorQuestionVO> pageInfo = new PageInfo<>(vendorQuestionVOS);
        PropertyUtils.copyProperties(pageInfo, pageVO, vendorQuestionVOS);

        return pageVO;
    }
}
