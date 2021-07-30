package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.PaidMaterialMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PaidMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidMaterialDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidMaterialVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.PaidMaterialQuery;
import com.xdcplus.xdcweb.biz.generator.PaidMaterialBaseService;
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
 * 竞价物品(PaidMaterial)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:23
 */
public class PaidMaterialBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<PaidMaterial, PaidMaterialVO, PaidMaterial, PaidMaterialMapper> implements PaidMaterialBaseService<S, T, E> {

    @Autowired
    protected PaidMaterialMapper paidMaterialMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean savePaidMaterial(PaidMaterialDTO paidMaterialDTO) {

        PaidMaterial paidMaterial = paidMaterialMapper.selectById(paidMaterialDTO.getId());
        if (ObjectUtil.isNotNull(paidMaterial)) {
            log.error("savePaidMaterial() The PaidMaterial already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        paidMaterial = new PaidMaterial();
        BeanUtil.copyProperties(paidMaterialDTO, paidMaterial);
        paidMaterial.setCreatedTime(DateUtil.current());
        paidMaterial.setDeleted(0);

        return this.save(paidMaterial);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updatePaidMaterial(PaidMaterialDTO paidMaterialDTO) {

        PaidMaterial paidMaterial = this.getById(paidMaterialDTO.getId());
        if (ObjectUtil.isNull(paidMaterial)) {
            log.error("updatePaidMaterial() The PaidMaterial does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(paidMaterialDTO, paidMaterial);
        paidMaterial.setUpdatedUser(paidMaterialDTO.getUpdatedUser());
        paidMaterial.setUpdatedTime(DateUtil.current());

        return this.updateById(paidMaterial);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<PaidMaterial> paidMaterialList) {

        paidMaterialList.forEach(paidMaterial -> {
            PaidMaterial paidMaterialParam = new PaidMaterial();
            paidMaterialParam.setId(paidMaterial.getId());
            if (ObjectUtil.isNotNull(paidMaterial.getId())) {
                paidMaterial.setId(paidMaterial.getId());
                paidMaterial.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<PaidMaterial> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(PaidMaterial::getId, paidMaterial.getId());
                update(paidMaterial, lambdaUpdate);
            } else {
                paidMaterial.setCreatedTime(DateUtil.current());
                paidMaterial.setDeleted(0);
                save(paidMaterial);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<PaidMaterialDTO> paidMaterialDTOList) {

        List<PaidMaterial> paidMaterialList = BeanUtils.copyProperties(paidMaterialDTOList, PaidMaterial.class);
        return saveOrUpdateBatch(paidMaterialList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deletePaidMaterialLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        PaidMaterial paidMaterial = this.getById(id);

        if (ObjectUtil.isNull(paidMaterial)) {
            log.error("deletePaidMaterial() The PaidMaterial does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        paidMaterial.setDeleted(1);

        return this.updateById(paidMaterial);
    }

    private List<PaidMaterial> queryPaidMaterialList(PaidMaterialFilterDTO paidMaterialFilterDTO) {
        if (ObjectUtil.isNull(paidMaterialFilterDTO)) {
            paidMaterialFilterDTO = new PaidMaterialFilterDTO();
        }
        paidMaterialFilterDTO.setDeleted(0);
        PaidMaterialQuery paidMaterialQuery = BeanUtil.copyProperties(paidMaterialFilterDTO, PaidMaterialQuery.class);

        return paidMaterialMapper.queryPaidMaterial(paidMaterialQuery);
    }

    @Override
    public List<PaidMaterialVO> queryPaidMaterialVOList(PaidMaterialFilterDTO paidMaterialFilterDTO) {
        if (ObjectUtil.isNull(paidMaterialFilterDTO)) {
            paidMaterialFilterDTO = new PaidMaterialFilterDTO();
        }
        paidMaterialFilterDTO.setDeleted(0);
        return this.objectConversion(queryPaidMaterialList(paidMaterialFilterDTO));
    }

    @Override
    public PageVO<PaidMaterialVO> queryPaidMaterial(PaidMaterialFilterDTO paidMaterialFilterDTO) {
        if (ObjectUtil.isNull(paidMaterialFilterDTO)) {
            paidMaterialFilterDTO = new PaidMaterialFilterDTO();
        }
        paidMaterialFilterDTO.setDeleted(0);
        PageVO<PaidMaterialVO> pageVO = new PageVO<>();

        if (paidMaterialFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(paidMaterialFilterDTO.getCurrentPage(), paidMaterialFilterDTO.getPageSize(),
                    paidMaterialFilterDTO.getOrderType(), paidMaterialFilterDTO.getOrderField());
        }

        List<PaidMaterial> paidMaterialList = queryPaidMaterialList(paidMaterialFilterDTO);

        PageInfo<PaidMaterial> pageInfo = new PageInfo<>(paidMaterialList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(paidMaterialList));

        return pageVO;
    }

    @Override
    public PaidMaterialVO queryPaidMaterialById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
