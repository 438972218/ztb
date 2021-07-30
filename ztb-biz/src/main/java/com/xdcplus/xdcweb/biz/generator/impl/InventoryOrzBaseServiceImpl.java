package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.InventoryOrzMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InventoryOrz;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InventoryOrzDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.InventoryOrzFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.InventoryOrzVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.InventoryOrzQuery;
import com.xdcplus.xdcweb.biz.generator.InventoryOrzBaseService;
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
 * 库存组织(InventoryOrz)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:12
 */
public class InventoryOrzBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<InventoryOrz, InventoryOrzVO, InventoryOrz, InventoryOrzMapper> implements InventoryOrzBaseService<S, T, E> {

    @Autowired
    protected InventoryOrzMapper inventoryOrzMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveInventoryOrz(InventoryOrzDTO inventoryOrzDTO) {

        InventoryOrz inventoryOrz = inventoryOrzMapper.selectById(inventoryOrzDTO.getId());
        if (ObjectUtil.isNotNull(inventoryOrz)) {
            log.error("saveInventoryOrz() The InventoryOrz already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        inventoryOrz = new InventoryOrz();
        BeanUtil.copyProperties(inventoryOrzDTO, inventoryOrz);
        inventoryOrz.setCreatedTime(DateUtil.current());
        inventoryOrz.setDeleted(0);

        return this.save(inventoryOrz);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateInventoryOrz(InventoryOrzDTO inventoryOrzDTO) {

        InventoryOrz inventoryOrz = this.getById(inventoryOrzDTO.getId());
        if (ObjectUtil.isNull(inventoryOrz)) {
            log.error("updateInventoryOrz() The InventoryOrz does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(inventoryOrzDTO, inventoryOrz);
        inventoryOrz.setUpdatedUser(inventoryOrzDTO.getUpdatedUser());
        inventoryOrz.setUpdatedTime(DateUtil.current());

        return this.updateById(inventoryOrz);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<InventoryOrz> inventoryOrzList) {

        inventoryOrzList.forEach(inventoryOrz -> {
            InventoryOrz inventoryOrzParam = new InventoryOrz();
            inventoryOrzParam.setId(inventoryOrz.getId());
            if (ObjectUtil.isNotNull(inventoryOrz.getId())) {
                inventoryOrz.setId(inventoryOrz.getId());
                inventoryOrz.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<InventoryOrz> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(InventoryOrz::getId, inventoryOrz.getId());
                update(inventoryOrz, lambdaUpdate);
            } else {
                inventoryOrz.setCreatedTime(DateUtil.current());
                inventoryOrz.setDeleted(0);
                save(inventoryOrz);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<InventoryOrzDTO> inventoryOrzDTOList) {

        List<InventoryOrz> inventoryOrzList = BeanUtils.copyProperties(inventoryOrzDTOList, InventoryOrz.class);
        return saveOrUpdateBatch(inventoryOrzList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteInventoryOrzLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        InventoryOrz inventoryOrz = this.getById(id);

        if (ObjectUtil.isNull(inventoryOrz)) {
            log.error("deleteInventoryOrz() The InventoryOrz does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        inventoryOrz.setDeleted(1);

        return this.updateById(inventoryOrz);
    }

    private List<InventoryOrz> queryInventoryOrzList(InventoryOrzFilterDTO inventoryOrzFilterDTO) {
        if (ObjectUtil.isNull(inventoryOrzFilterDTO)) {
            inventoryOrzFilterDTO = new InventoryOrzFilterDTO();
        }
        inventoryOrzFilterDTO.setDeleted(0);
        InventoryOrzQuery inventoryOrzQuery = BeanUtil.copyProperties(inventoryOrzFilterDTO, InventoryOrzQuery.class);

        return inventoryOrzMapper.queryInventoryOrz(inventoryOrzQuery);
    }

    @Override
    public List<InventoryOrzVO> queryInventoryOrzVOList(InventoryOrzFilterDTO inventoryOrzFilterDTO) {
        if (ObjectUtil.isNull(inventoryOrzFilterDTO)) {
            inventoryOrzFilterDTO = new InventoryOrzFilterDTO();
        }
        inventoryOrzFilterDTO.setDeleted(0);
        return this.objectConversion(queryInventoryOrzList(inventoryOrzFilterDTO));
    }

    @Override
    public PageVO<InventoryOrzVO> queryInventoryOrz(InventoryOrzFilterDTO inventoryOrzFilterDTO) {
        if (ObjectUtil.isNull(inventoryOrzFilterDTO)) {
            inventoryOrzFilterDTO = new InventoryOrzFilterDTO();
        }
        inventoryOrzFilterDTO.setDeleted(0);
        PageVO<InventoryOrzVO> pageVO = new PageVO<>();

        if (inventoryOrzFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(inventoryOrzFilterDTO.getCurrentPage(), inventoryOrzFilterDTO.getPageSize(),
                    inventoryOrzFilterDTO.getOrderType(), inventoryOrzFilterDTO.getOrderField());
        }

        List<InventoryOrz> inventoryOrzList = queryInventoryOrzList(inventoryOrzFilterDTO);

        PageInfo<InventoryOrz> pageInfo = new PageInfo<>(inventoryOrzList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(inventoryOrzList));

        return pageVO;
    }

    @Override
    public InventoryOrzVO queryInventoryOrzById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
