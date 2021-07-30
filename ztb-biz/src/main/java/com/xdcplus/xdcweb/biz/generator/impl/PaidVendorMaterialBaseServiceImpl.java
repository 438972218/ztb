package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.PaidVendorMaterialMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PaidVendorMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidVendorMaterialDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidVendorMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidVendorMaterialVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.PaidVendorMaterialQuery;
import com.xdcplus.xdcweb.biz.generator.PaidVendorMaterialBaseService;
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
 * 竞价供应商物品(PaidVendorMaterial)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:26
 */
public class PaidVendorMaterialBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<PaidVendorMaterial, PaidVendorMaterialVO, PaidVendorMaterial, PaidVendorMaterialMapper> implements PaidVendorMaterialBaseService<S, T, E> {

    @Autowired
    protected PaidVendorMaterialMapper paidVendorMaterialMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean savePaidVendorMaterial(PaidVendorMaterialDTO paidVendorMaterialDTO) {

        PaidVendorMaterial paidVendorMaterial = paidVendorMaterialMapper.selectById(paidVendorMaterialDTO.getId());
        if (ObjectUtil.isNotNull(paidVendorMaterial)) {
            log.error("savePaidVendorMaterial() The PaidVendorMaterial already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        paidVendorMaterial = new PaidVendorMaterial();
        BeanUtil.copyProperties(paidVendorMaterialDTO, paidVendorMaterial);
        paidVendorMaterial.setCreatedTime(DateUtil.current());
        paidVendorMaterial.setDeleted(0);

        return this.save(paidVendorMaterial);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updatePaidVendorMaterial(PaidVendorMaterialDTO paidVendorMaterialDTO) {

        PaidVendorMaterial paidVendorMaterial = this.getById(paidVendorMaterialDTO.getId());
        if (ObjectUtil.isNull(paidVendorMaterial)) {
            log.error("updatePaidVendorMaterial() The PaidVendorMaterial does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(paidVendorMaterialDTO, paidVendorMaterial);
        paidVendorMaterial.setUpdatedUser(paidVendorMaterialDTO.getUpdatedUser());
        paidVendorMaterial.setUpdatedTime(DateUtil.current());

        return this.updateById(paidVendorMaterial);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<PaidVendorMaterial> paidVendorMaterialList) {

        paidVendorMaterialList.forEach(paidVendorMaterial -> {
            PaidVendorMaterial paidVendorMaterialParam = new PaidVendorMaterial();
            paidVendorMaterialParam.setId(paidVendorMaterial.getId());
            if (ObjectUtil.isNotNull(paidVendorMaterial.getId())) {
                paidVendorMaterial.setId(paidVendorMaterial.getId());
                paidVendorMaterial.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<PaidVendorMaterial> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(PaidVendorMaterial::getId, paidVendorMaterial.getId());
                update(paidVendorMaterial, lambdaUpdate);
            } else {
                paidVendorMaterial.setCreatedTime(DateUtil.current());
                paidVendorMaterial.setDeleted(0);
                save(paidVendorMaterial);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<PaidVendorMaterialDTO> paidVendorMaterialDTOList) {

        List<PaidVendorMaterial> paidVendorMaterialList = BeanUtils.copyProperties(paidVendorMaterialDTOList, PaidVendorMaterial.class);
        return saveOrUpdateBatch(paidVendorMaterialList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deletePaidVendorMaterialLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        PaidVendorMaterial paidVendorMaterial = this.getById(id);

        if (ObjectUtil.isNull(paidVendorMaterial)) {
            log.error("deletePaidVendorMaterial() The PaidVendorMaterial does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        paidVendorMaterial.setDeleted(1);

        return this.updateById(paidVendorMaterial);
    }

    private List<PaidVendorMaterial> queryPaidVendorMaterialList(PaidVendorMaterialFilterDTO paidVendorMaterialFilterDTO) {
        if (ObjectUtil.isNull(paidVendorMaterialFilterDTO)) {
            paidVendorMaterialFilterDTO = new PaidVendorMaterialFilterDTO();
        }
        paidVendorMaterialFilterDTO.setDeleted(0);
        PaidVendorMaterialQuery paidVendorMaterialQuery = BeanUtil.copyProperties(paidVendorMaterialFilterDTO, PaidVendorMaterialQuery.class);

        return paidVendorMaterialMapper.queryPaidVendorMaterial(paidVendorMaterialQuery);
    }

    @Override
    public List<PaidVendorMaterialVO> queryPaidVendorMaterialVOList(PaidVendorMaterialFilterDTO paidVendorMaterialFilterDTO) {
        if (ObjectUtil.isNull(paidVendorMaterialFilterDTO)) {
            paidVendorMaterialFilterDTO = new PaidVendorMaterialFilterDTO();
        }
        paidVendorMaterialFilterDTO.setDeleted(0);
        return this.objectConversion(queryPaidVendorMaterialList(paidVendorMaterialFilterDTO));
    }

    @Override
    public PageVO<PaidVendorMaterialVO> queryPaidVendorMaterial(PaidVendorMaterialFilterDTO paidVendorMaterialFilterDTO) {
        if (ObjectUtil.isNull(paidVendorMaterialFilterDTO)) {
            paidVendorMaterialFilterDTO = new PaidVendorMaterialFilterDTO();
        }
        paidVendorMaterialFilterDTO.setDeleted(0);
        PageVO<PaidVendorMaterialVO> pageVO = new PageVO<>();

        if (paidVendorMaterialFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(paidVendorMaterialFilterDTO.getCurrentPage(), paidVendorMaterialFilterDTO.getPageSize(),
                    paidVendorMaterialFilterDTO.getOrderType(), paidVendorMaterialFilterDTO.getOrderField());
        }

        List<PaidVendorMaterial> paidVendorMaterialList = queryPaidVendorMaterialList(paidVendorMaterialFilterDTO);

        PageInfo<PaidVendorMaterial> pageInfo = new PageInfo<>(paidVendorMaterialList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(paidVendorMaterialList));

        return pageVO;
    }

    @Override
    public PaidVendorMaterialVO queryPaidVendorMaterialById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
