package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorRequestMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorRequest;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorRequestDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorRequestFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorRequestVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorRequestQuery;
import com.xdcplus.xdcweb.biz.generator.VendorRequestBaseService;
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
 * 供应商注册单(VendorRequest)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:52
 */
public class VendorRequestBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<VendorRequest, VendorRequestVO, VendorRequest, VendorRequestMapper> implements VendorRequestBaseService<S, T, E> {

    @Autowired
    protected VendorRequestMapper vendorRequestMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveVendorRequest(VendorRequestDTO vendorRequestDTO) {

        VendorRequest vendorRequest = vendorRequestMapper.selectById(vendorRequestDTO.getId());
        if (ObjectUtil.isNotNull(vendorRequest)) {
            log.error("saveVendorRequest() The VendorRequest already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorRequest = new VendorRequest();
        BeanUtil.copyProperties(vendorRequestDTO, vendorRequest);
        vendorRequest.setCreatedTime(DateUtil.current());
        vendorRequest.setDeleted(0);

        return this.save(vendorRequest);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateVendorRequest(VendorRequestDTO vendorRequestDTO) {

        VendorRequest vendorRequest = this.getById(vendorRequestDTO.getId());
        if (ObjectUtil.isNull(vendorRequest)) {
            log.error("updateVendorRequest() The VendorRequest does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorRequestDTO, vendorRequest);
        vendorRequest.setUpdatedUser(vendorRequestDTO.getUpdatedUser());
        vendorRequest.setUpdatedTime(DateUtil.current());

        return this.updateById(vendorRequest);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<VendorRequest> vendorRequestList) {

        vendorRequestList.forEach(vendorRequest -> {
            VendorRequest vendorRequestParam = new VendorRequest();
            vendorRequestParam.setId(vendorRequest.getId());
            if (ObjectUtil.isNotNull(vendorRequest.getId())) {
                vendorRequest.setId(vendorRequest.getId());
                vendorRequest.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<VendorRequest> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(VendorRequest::getId, vendorRequest.getId());
                update(vendorRequest, lambdaUpdate);
            } else {
                vendorRequest.setCreatedTime(DateUtil.current());
                vendorRequest.setDeleted(0);
                save(vendorRequest);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<VendorRequestDTO> vendorRequestDTOList) {

        List<VendorRequest> vendorRequestList = BeanUtils.copyProperties(vendorRequestDTOList, VendorRequest.class);
        return saveOrUpdateBatch(vendorRequestList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVendorRequestLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        VendorRequest vendorRequest = this.getById(id);

        if (ObjectUtil.isNull(vendorRequest)) {
            log.error("deleteVendorRequest() The VendorRequest does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        vendorRequest.setDeleted(1);

        return this.updateById(vendorRequest);
    }

    private List<VendorRequest> queryVendorRequestList(VendorRequestFilterDTO vendorRequestFilterDTO) {
        if (ObjectUtil.isNull(vendorRequestFilterDTO)) {
            vendorRequestFilterDTO = new VendorRequestFilterDTO();
        }
        vendorRequestFilterDTO.setDeleted(0);
        VendorRequestQuery vendorRequestQuery = BeanUtil.copyProperties(vendorRequestFilterDTO, VendorRequestQuery.class);

        return vendorRequestMapper.queryVendorRequest(vendorRequestQuery);
    }

    @Override
    public List<VendorRequestVO> queryVendorRequestVOList(VendorRequestFilterDTO vendorRequestFilterDTO) {
        if (ObjectUtil.isNull(vendorRequestFilterDTO)) {
            vendorRequestFilterDTO = new VendorRequestFilterDTO();
        }
        vendorRequestFilterDTO.setDeleted(0);
        return this.objectConversion(queryVendorRequestList(vendorRequestFilterDTO));
    }

    @Override
    public PageVO<VendorRequestVO> queryVendorRequest(VendorRequestFilterDTO vendorRequestFilterDTO) {
        if (ObjectUtil.isNull(vendorRequestFilterDTO)) {
            vendorRequestFilterDTO = new VendorRequestFilterDTO();
        }
        vendorRequestFilterDTO.setDeleted(0);
        PageVO<VendorRequestVO> pageVO = new PageVO<>();

        if (vendorRequestFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorRequestFilterDTO.getCurrentPage(), vendorRequestFilterDTO.getPageSize(),
                    vendorRequestFilterDTO.getOrderType(), vendorRequestFilterDTO.getOrderField());
        }

        List<VendorRequest> vendorRequestList = queryVendorRequestList(vendorRequestFilterDTO);

        PageInfo<VendorRequest> pageInfo = new PageInfo<>(vendorRequestList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(vendorRequestList));

        return pageVO;
    }

    @Override
    public VendorRequestVO queryVendorRequestById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
