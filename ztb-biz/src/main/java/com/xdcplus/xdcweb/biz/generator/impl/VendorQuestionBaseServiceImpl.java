package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorQuestionMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorQuestion;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQuestionDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorQuestionFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorQuestionVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorQuestionQuery;
import com.xdcplus.xdcweb.biz.generator.VendorQuestionBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.tool.utils.BeanUtils;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.tool.utils.PageableUtils;
import com.xdcplus.tool.utils.PropertyUtils;
import com.xdcplus.xdcweb.biz.common.enums.ResponseEnum;
import com.xdcplus.xdcweb.biz.common.exceptions.InvException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 供应商电子调查表(VendorQuestion)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:49
 */
public class VendorQuestionBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<VendorQuestion, VendorQuestionVO, VendorQuestion, VendorQuestionMapper> implements VendorQuestionBaseService<S, T, E> {

    @Autowired
    protected VendorQuestionMapper vendorQuestionMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveVendorQuestion(VendorQuestionDTO vendorQuestionDTO) {

        VendorQuestion vendorQuestion = vendorQuestionMapper.selectById(vendorQuestionDTO.getId());
        if (ObjectUtil.isNotNull(vendorQuestion)) {
            log.error("saveVendorQuestion() The VendorQuestion already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorQuestion = new VendorQuestion();
        BeanUtil.copyProperties(vendorQuestionDTO, vendorQuestion);
        vendorQuestion.setCreatedTime(DateUtil.current());
        vendorQuestion.setDeleted(0);

        return this.save(vendorQuestion);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateVendorQuestion(VendorQuestionDTO vendorQuestionDTO) {

        VendorQuestion vendorQuestion = this.getById(vendorQuestionDTO.getId());
        if (ObjectUtil.isNull(vendorQuestion)) {
            log.error("updateVendorQuestion() The VendorQuestion does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorQuestionDTO, vendorQuestion);
        vendorQuestion.setUpdatedUser(vendorQuestionDTO.getUpdatedUser());
        vendorQuestion.setUpdatedTime(DateUtil.current());

        return this.updateById(vendorQuestion);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<VendorQuestion> vendorQuestionList) {

        vendorQuestionList.forEach(vendorQuestion -> {
            VendorQuestion vendorQuestionParam = new VendorQuestion();
            vendorQuestionParam.setId(vendorQuestion.getId());
            if (ObjectUtil.isNotNull(vendorQuestion.getId())) {
                vendorQuestion.setId(vendorQuestion.getId());
                vendorQuestion.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<VendorQuestion> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(VendorQuestion::getId, vendorQuestion.getId());
                update(vendorQuestion, lambdaUpdate);
            } else {
                vendorQuestion.setCreatedTime(DateUtil.current());
                vendorQuestion.setDeleted(0);
                save(vendorQuestion);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<VendorQuestionDTO> vendorQuestionDTOList) {

        List<VendorQuestion> vendorQuestionList = BeanUtils.copyProperties(vendorQuestionDTOList, VendorQuestion.class);
        return saveOrUpdateBatch(vendorQuestionList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVendorQuestionLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        VendorQuestion vendorQuestion = this.getById(id);

        if (ObjectUtil.isNull(vendorQuestion)) {
            log.error("deleteVendorQuestion() The VendorQuestion does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        vendorQuestion.setDeleted(1);

        return this.updateById(vendorQuestion);
    }

    private List<VendorQuestion> queryVendorQuestionList(VendorQuestionFilterDTO vendorQuestionFilterDTO) {
        if (ObjectUtil.isNull(vendorQuestionFilterDTO)) {
            vendorQuestionFilterDTO = new VendorQuestionFilterDTO();
        }
        vendorQuestionFilterDTO.setDeleted(0);
        VendorQuestionQuery vendorQuestionQuery = BeanUtil.copyProperties(vendorQuestionFilterDTO, VendorQuestionQuery.class);

        return vendorQuestionMapper.queryVendorQuestion(vendorQuestionQuery);
    }

    @Override
    public List<VendorQuestionVO> queryVendorQuestionVOList(VendorQuestionFilterDTO vendorQuestionFilterDTO) {
        if (ObjectUtil.isNull(vendorQuestionFilterDTO)) {
            vendorQuestionFilterDTO = new VendorQuestionFilterDTO();
        }
        vendorQuestionFilterDTO.setDeleted(0);
        return this.objectConversion(queryVendorQuestionList(vendorQuestionFilterDTO));
    }

    @Override
    public PageVO<VendorQuestionVO> queryVendorQuestion(VendorQuestionFilterDTO vendorQuestionFilterDTO) {
        if (ObjectUtil.isNull(vendorQuestionFilterDTO)) {
            vendorQuestionFilterDTO = new VendorQuestionFilterDTO();
        }
        vendorQuestionFilterDTO.setDeleted(0);
        PageVO<VendorQuestionVO> pageVO = new PageVO<>();

        if (vendorQuestionFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorQuestionFilterDTO.getCurrentPage(), vendorQuestionFilterDTO.getPageSize(),
                    vendorQuestionFilterDTO.getOrderType(), vendorQuestionFilterDTO.getOrderField());
        }

        List<VendorQuestion> vendorQuestionList = queryVendorQuestionList(vendorQuestionFilterDTO);

        PageInfo<VendorQuestion> pageInfo = new PageInfo<>(vendorQuestionList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(vendorQuestionList));

        return pageVO;
    }

    @Override
    public VendorQuestionVO queryVendorQuestionById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
