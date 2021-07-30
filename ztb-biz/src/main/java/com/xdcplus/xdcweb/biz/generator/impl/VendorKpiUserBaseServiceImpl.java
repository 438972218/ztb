package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorKpiUserMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorKpiUser;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorKpiUserDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorKpiUserFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorKpiUserVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorKpiUserQuery;
import com.xdcplus.xdcweb.biz.generator.VendorKpiUserBaseService;
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
 * 供应商绩效人员表(VendorKpiUser)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:47
 */
public class VendorKpiUserBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<VendorKpiUser, VendorKpiUserVO, VendorKpiUser, VendorKpiUserMapper> implements VendorKpiUserBaseService<S, T, E> {

    @Autowired
    protected VendorKpiUserMapper vendorKpiUserMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveVendorKpiUser(VendorKpiUserDTO vendorKpiUserDTO) {

        VendorKpiUser vendorKpiUser = vendorKpiUserMapper.selectById(vendorKpiUserDTO.getId());
        if (ObjectUtil.isNotNull(vendorKpiUser)) {
            log.error("saveVendorKpiUser() The VendorKpiUser already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorKpiUser = new VendorKpiUser();
        BeanUtil.copyProperties(vendorKpiUserDTO, vendorKpiUser);
        vendorKpiUser.setCreatedTime(DateUtil.current());
        vendorKpiUser.setDeleted(0);

        return this.save(vendorKpiUser);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateVendorKpiUser(VendorKpiUserDTO vendorKpiUserDTO) {

        VendorKpiUser vendorKpiUser = this.getById(vendorKpiUserDTO.getId());
        if (ObjectUtil.isNull(vendorKpiUser)) {
            log.error("updateVendorKpiUser() The VendorKpiUser does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorKpiUserDTO, vendorKpiUser);
        vendorKpiUser.setUpdatedUser(vendorKpiUserDTO.getUpdatedUser());
        vendorKpiUser.setUpdatedTime(DateUtil.current());

        return this.updateById(vendorKpiUser);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<VendorKpiUser> vendorKpiUserList) {

        vendorKpiUserList.forEach(vendorKpiUser -> {
            VendorKpiUser vendorKpiUserParam = new VendorKpiUser();
            vendorKpiUserParam.setId(vendorKpiUser.getId());
            if (ObjectUtil.isNotNull(vendorKpiUser.getId())) {
                vendorKpiUser.setId(vendorKpiUser.getId());
                vendorKpiUser.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<VendorKpiUser> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(VendorKpiUser::getId, vendorKpiUser.getId());
                update(vendorKpiUser, lambdaUpdate);
            } else {
                vendorKpiUser.setCreatedTime(DateUtil.current());
                vendorKpiUser.setDeleted(0);
                save(vendorKpiUser);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<VendorKpiUserDTO> vendorKpiUserDTOList) {

        List<VendorKpiUser> vendorKpiUserList = BeanUtils.copyProperties(vendorKpiUserDTOList, VendorKpiUser.class);
        return saveOrUpdateBatch(vendorKpiUserList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVendorKpiUserLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        VendorKpiUser vendorKpiUser = this.getById(id);

        if (ObjectUtil.isNull(vendorKpiUser)) {
            log.error("deleteVendorKpiUser() The VendorKpiUser does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        vendorKpiUser.setDeleted(1);

        return this.updateById(vendorKpiUser);
    }

    private List<VendorKpiUser> queryVendorKpiUserList(VendorKpiUserFilterDTO vendorKpiUserFilterDTO) {
        if (ObjectUtil.isNull(vendorKpiUserFilterDTO)) {
            vendorKpiUserFilterDTO = new VendorKpiUserFilterDTO();
        }
        vendorKpiUserFilterDTO.setDeleted(0);
        VendorKpiUserQuery vendorKpiUserQuery = BeanUtil.copyProperties(vendorKpiUserFilterDTO, VendorKpiUserQuery.class);

        return vendorKpiUserMapper.queryVendorKpiUser(vendorKpiUserQuery);
    }

    @Override
    public List<VendorKpiUserVO> queryVendorKpiUserVOList(VendorKpiUserFilterDTO vendorKpiUserFilterDTO) {
        if (ObjectUtil.isNull(vendorKpiUserFilterDTO)) {
            vendorKpiUserFilterDTO = new VendorKpiUserFilterDTO();
        }
        vendorKpiUserFilterDTO.setDeleted(0);
        return this.objectConversion(queryVendorKpiUserList(vendorKpiUserFilterDTO));
    }

    @Override
    public PageVO<VendorKpiUserVO> queryVendorKpiUser(VendorKpiUserFilterDTO vendorKpiUserFilterDTO) {
        if (ObjectUtil.isNull(vendorKpiUserFilterDTO)) {
            vendorKpiUserFilterDTO = new VendorKpiUserFilterDTO();
        }
        vendorKpiUserFilterDTO.setDeleted(0);
        PageVO<VendorKpiUserVO> pageVO = new PageVO<>();

        if (vendorKpiUserFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorKpiUserFilterDTO.getCurrentPage(), vendorKpiUserFilterDTO.getPageSize(),
                    vendorKpiUserFilterDTO.getOrderType(), vendorKpiUserFilterDTO.getOrderField());
        }

        List<VendorKpiUser> vendorKpiUserList = queryVendorKpiUserList(vendorKpiUserFilterDTO);

        PageInfo<VendorKpiUser> pageInfo = new PageInfo<>(vendorKpiUserList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(vendorKpiUserList));

        return pageVO;
    }

    @Override
    public VendorKpiUserVO queryVendorKpiUserById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
