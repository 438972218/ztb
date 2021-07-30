package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.PurchaseOrzInventoryOrzMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PurchaseOrzInventoryOrz;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PurchaseOrzInventoryOrzDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PurchaseOrzInventoryOrzFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PurchaseOrzInventoryOrzVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.PurchaseOrzInventoryOrzQuery;
import com.xdcplus.xdcweb.biz.generator.PurchaseOrzInventoryOrzBaseService;
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
 * 采购组织 工厂(库存组织)对应关系(PurchaseOrzInventoryOrz)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:28
 */
public class PurchaseOrzInventoryOrzBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<PurchaseOrzInventoryOrz, PurchaseOrzInventoryOrzVO, PurchaseOrzInventoryOrz, PurchaseOrzInventoryOrzMapper> implements PurchaseOrzInventoryOrzBaseService<S, T, E> {

    @Autowired
    protected PurchaseOrzInventoryOrzMapper purchaseOrzInventoryOrzMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean savePurchaseOrzInventoryOrz(PurchaseOrzInventoryOrzDTO purchaseOrzInventoryOrzDTO) {

        PurchaseOrzInventoryOrz purchaseOrzInventoryOrz = purchaseOrzInventoryOrzMapper.selectById(purchaseOrzInventoryOrzDTO.getId());
        if (ObjectUtil.isNotNull(purchaseOrzInventoryOrz)) {
            log.error("savePurchaseOrzInventoryOrz() The PurchaseOrzInventoryOrz already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        purchaseOrzInventoryOrz = new PurchaseOrzInventoryOrz();
        BeanUtil.copyProperties(purchaseOrzInventoryOrzDTO, purchaseOrzInventoryOrz);
        purchaseOrzInventoryOrz.setCreatedTime(DateUtil.current());
        purchaseOrzInventoryOrz.setDeleted(0);

        return this.save(purchaseOrzInventoryOrz);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updatePurchaseOrzInventoryOrz(PurchaseOrzInventoryOrzDTO purchaseOrzInventoryOrzDTO) {

        PurchaseOrzInventoryOrz purchaseOrzInventoryOrz = this.getById(purchaseOrzInventoryOrzDTO.getId());
        if (ObjectUtil.isNull(purchaseOrzInventoryOrz)) {
            log.error("updatePurchaseOrzInventoryOrz() The PurchaseOrzInventoryOrz does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(purchaseOrzInventoryOrzDTO, purchaseOrzInventoryOrz);
        purchaseOrzInventoryOrz.setUpdatedUser(purchaseOrzInventoryOrzDTO.getUpdatedUser());
        purchaseOrzInventoryOrz.setUpdatedTime(DateUtil.current());

        return this.updateById(purchaseOrzInventoryOrz);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<PurchaseOrzInventoryOrz> purchaseOrzInventoryOrzList) {

        purchaseOrzInventoryOrzList.forEach(purchaseOrzInventoryOrz -> {
            PurchaseOrzInventoryOrz purchaseOrzInventoryOrzParam = new PurchaseOrzInventoryOrz();
            purchaseOrzInventoryOrzParam.setId(purchaseOrzInventoryOrz.getId());
            if (ObjectUtil.isNotNull(purchaseOrzInventoryOrz.getId())) {
                purchaseOrzInventoryOrz.setId(purchaseOrzInventoryOrz.getId());
                purchaseOrzInventoryOrz.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<PurchaseOrzInventoryOrz> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(PurchaseOrzInventoryOrz::getId, purchaseOrzInventoryOrz.getId());
                update(purchaseOrzInventoryOrz, lambdaUpdate);
            } else {
                purchaseOrzInventoryOrz.setCreatedTime(DateUtil.current());
                purchaseOrzInventoryOrz.setDeleted(0);
                save(purchaseOrzInventoryOrz);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<PurchaseOrzInventoryOrzDTO> purchaseOrzInventoryOrzDTOList) {

        List<PurchaseOrzInventoryOrz> purchaseOrzInventoryOrzList = BeanUtils.copyProperties(purchaseOrzInventoryOrzDTOList, PurchaseOrzInventoryOrz.class);
        return saveOrUpdateBatch(purchaseOrzInventoryOrzList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deletePurchaseOrzInventoryOrzLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        PurchaseOrzInventoryOrz purchaseOrzInventoryOrz = this.getById(id);

        if (ObjectUtil.isNull(purchaseOrzInventoryOrz)) {
            log.error("deletePurchaseOrzInventoryOrz() The PurchaseOrzInventoryOrz does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        purchaseOrzInventoryOrz.setDeleted(1);

        return this.updateById(purchaseOrzInventoryOrz);
    }

    private List<PurchaseOrzInventoryOrz> queryPurchaseOrzInventoryOrzList(PurchaseOrzInventoryOrzFilterDTO purchaseOrzInventoryOrzFilterDTO) {
        if (ObjectUtil.isNull(purchaseOrzInventoryOrzFilterDTO)) {
            purchaseOrzInventoryOrzFilterDTO = new PurchaseOrzInventoryOrzFilterDTO();
        }
        purchaseOrzInventoryOrzFilterDTO.setDeleted(0);
        PurchaseOrzInventoryOrzQuery purchaseOrzInventoryOrzQuery = BeanUtil.copyProperties(purchaseOrzInventoryOrzFilterDTO, PurchaseOrzInventoryOrzQuery.class);

        return purchaseOrzInventoryOrzMapper.queryPurchaseOrzInventoryOrz(purchaseOrzInventoryOrzQuery);
    }

    @Override
    public List<PurchaseOrzInventoryOrzVO> queryPurchaseOrzInventoryOrzVOList(PurchaseOrzInventoryOrzFilterDTO purchaseOrzInventoryOrzFilterDTO) {
        if (ObjectUtil.isNull(purchaseOrzInventoryOrzFilterDTO)) {
            purchaseOrzInventoryOrzFilterDTO = new PurchaseOrzInventoryOrzFilterDTO();
        }
        purchaseOrzInventoryOrzFilterDTO.setDeleted(0);
        return this.objectConversion(queryPurchaseOrzInventoryOrzList(purchaseOrzInventoryOrzFilterDTO));
    }

    @Override
    public PageVO<PurchaseOrzInventoryOrzVO> queryPurchaseOrzInventoryOrz(PurchaseOrzInventoryOrzFilterDTO purchaseOrzInventoryOrzFilterDTO) {
        if (ObjectUtil.isNull(purchaseOrzInventoryOrzFilterDTO)) {
            purchaseOrzInventoryOrzFilterDTO = new PurchaseOrzInventoryOrzFilterDTO();
        }
        purchaseOrzInventoryOrzFilterDTO.setDeleted(0);
        PageVO<PurchaseOrzInventoryOrzVO> pageVO = new PageVO<>();

        if (purchaseOrzInventoryOrzFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(purchaseOrzInventoryOrzFilterDTO.getCurrentPage(), purchaseOrzInventoryOrzFilterDTO.getPageSize(),
                    purchaseOrzInventoryOrzFilterDTO.getOrderType(), purchaseOrzInventoryOrzFilterDTO.getOrderField());
        }

        List<PurchaseOrzInventoryOrz> purchaseOrzInventoryOrzList = queryPurchaseOrzInventoryOrzList(purchaseOrzInventoryOrzFilterDTO);

        PageInfo<PurchaseOrzInventoryOrz> pageInfo = new PageInfo<>(purchaseOrzInventoryOrzList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(purchaseOrzInventoryOrzList));

        return pageVO;
    }

    @Override
    public PurchaseOrzInventoryOrzVO queryPurchaseOrzInventoryOrzById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
