package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.PurchaseOrzMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PurchaseOrz;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PurchaseOrzDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PurchaseOrzFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PurchaseOrzVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.PurchaseOrzQuery;
import com.xdcplus.xdcweb.biz.generator.PurchaseOrzBaseService;
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
 * 采购组织(PurchaseOrz)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:27
 */
public class PurchaseOrzBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<PurchaseOrz, PurchaseOrzVO, PurchaseOrz, PurchaseOrzMapper> implements PurchaseOrzBaseService<S, T, E> {

    @Autowired
    protected PurchaseOrzMapper purchaseOrzMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean savePurchaseOrz(PurchaseOrzDTO purchaseOrzDTO) {

        PurchaseOrz purchaseOrz = purchaseOrzMapper.selectById(purchaseOrzDTO.getId());
        if (ObjectUtil.isNotNull(purchaseOrz)) {
            log.error("savePurchaseOrz() The PurchaseOrz already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        purchaseOrz = new PurchaseOrz();
        BeanUtil.copyProperties(purchaseOrzDTO, purchaseOrz);
        purchaseOrz.setCreatedTime(DateUtil.current());
        purchaseOrz.setDeleted(0);

        return this.save(purchaseOrz);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updatePurchaseOrz(PurchaseOrzDTO purchaseOrzDTO) {

        PurchaseOrz purchaseOrz = this.getById(purchaseOrzDTO.getId());
        if (ObjectUtil.isNull(purchaseOrz)) {
            log.error("updatePurchaseOrz() The PurchaseOrz does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(purchaseOrzDTO, purchaseOrz);
        purchaseOrz.setUpdatedUser(purchaseOrzDTO.getUpdatedUser());
        purchaseOrz.setUpdatedTime(DateUtil.current());

        return this.updateById(purchaseOrz);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<PurchaseOrz> purchaseOrzList) {

        purchaseOrzList.forEach(purchaseOrz -> {
            PurchaseOrz purchaseOrzParam = new PurchaseOrz();
            purchaseOrzParam.setId(purchaseOrz.getId());
            if (ObjectUtil.isNotNull(purchaseOrz.getId())) {
                purchaseOrz.setId(purchaseOrz.getId());
                purchaseOrz.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<PurchaseOrz> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(PurchaseOrz::getId, purchaseOrz.getId());
                update(purchaseOrz, lambdaUpdate);
            } else {
                purchaseOrz.setCreatedTime(DateUtil.current());
                purchaseOrz.setDeleted(0);
                save(purchaseOrz);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<PurchaseOrzDTO> purchaseOrzDTOList) {

        List<PurchaseOrz> purchaseOrzList = BeanUtils.copyProperties(purchaseOrzDTOList, PurchaseOrz.class);
        return saveOrUpdateBatch(purchaseOrzList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deletePurchaseOrzLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        PurchaseOrz purchaseOrz = this.getById(id);

        if (ObjectUtil.isNull(purchaseOrz)) {
            log.error("deletePurchaseOrz() The PurchaseOrz does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        purchaseOrz.setDeleted(1);

        return this.updateById(purchaseOrz);
    }

    private List<PurchaseOrz> queryPurchaseOrzList(PurchaseOrzFilterDTO purchaseOrzFilterDTO) {
        if (ObjectUtil.isNull(purchaseOrzFilterDTO)) {
            purchaseOrzFilterDTO = new PurchaseOrzFilterDTO();
        }
        purchaseOrzFilterDTO.setDeleted(0);
        PurchaseOrzQuery purchaseOrzQuery = BeanUtil.copyProperties(purchaseOrzFilterDTO, PurchaseOrzQuery.class);

        return purchaseOrzMapper.queryPurchaseOrz(purchaseOrzQuery);
    }

    @Override
    public List<PurchaseOrzVO> queryPurchaseOrzVOList(PurchaseOrzFilterDTO purchaseOrzFilterDTO) {
        if (ObjectUtil.isNull(purchaseOrzFilterDTO)) {
            purchaseOrzFilterDTO = new PurchaseOrzFilterDTO();
        }
        purchaseOrzFilterDTO.setDeleted(0);
        return this.objectConversion(queryPurchaseOrzList(purchaseOrzFilterDTO));
    }

    @Override
    public PageVO<PurchaseOrzVO> queryPurchaseOrz(PurchaseOrzFilterDTO purchaseOrzFilterDTO) {
        if (ObjectUtil.isNull(purchaseOrzFilterDTO)) {
            purchaseOrzFilterDTO = new PurchaseOrzFilterDTO();
        }
        purchaseOrzFilterDTO.setDeleted(0);
        PageVO<PurchaseOrzVO> pageVO = new PageVO<>();

        if (purchaseOrzFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(purchaseOrzFilterDTO.getCurrentPage(), purchaseOrzFilterDTO.getPageSize(),
                    purchaseOrzFilterDTO.getOrderType(), purchaseOrzFilterDTO.getOrderField());
        }

        List<PurchaseOrz> purchaseOrzList = queryPurchaseOrzList(purchaseOrzFilterDTO);

        PageInfo<PurchaseOrz> pageInfo = new PageInfo<>(purchaseOrzList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(purchaseOrzList));

        return pageVO;
    }

    @Override
    public PurchaseOrzVO queryPurchaseOrzById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
