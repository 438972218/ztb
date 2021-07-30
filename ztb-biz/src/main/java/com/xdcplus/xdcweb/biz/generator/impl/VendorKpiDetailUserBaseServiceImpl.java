package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorKpiDetailUserMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorKpiDetailUser;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorKpiDetailUserDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorKpiDetailUserFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorKpiDetailUserVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorKpiDetailUserQuery;
import com.xdcplus.xdcweb.biz.generator.VendorKpiDetailUserBaseService;
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
 * 供应商绩效人员评价表(VendorKpiDetailUser)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:46
 */
public class VendorKpiDetailUserBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<VendorKpiDetailUser, VendorKpiDetailUserVO, VendorKpiDetailUser, VendorKpiDetailUserMapper> implements VendorKpiDetailUserBaseService<S, T, E> {

    @Autowired
    protected VendorKpiDetailUserMapper vendorKpiDetailUserMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveVendorKpiDetailUser(VendorKpiDetailUserDTO vendorKpiDetailUserDTO) {

        VendorKpiDetailUser vendorKpiDetailUser = vendorKpiDetailUserMapper.selectById(vendorKpiDetailUserDTO.getId());
        if (ObjectUtil.isNotNull(vendorKpiDetailUser)) {
            log.error("saveVendorKpiDetailUser() The VendorKpiDetailUser already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorKpiDetailUser = new VendorKpiDetailUser();
        BeanUtil.copyProperties(vendorKpiDetailUserDTO, vendorKpiDetailUser);
        vendorKpiDetailUser.setCreatedTime(DateUtil.current());
        vendorKpiDetailUser.setDeleted(0);

        return this.save(vendorKpiDetailUser);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateVendorKpiDetailUser(VendorKpiDetailUserDTO vendorKpiDetailUserDTO) {

        VendorKpiDetailUser vendorKpiDetailUser = this.getById(vendorKpiDetailUserDTO.getId());
        if (ObjectUtil.isNull(vendorKpiDetailUser)) {
            log.error("updateVendorKpiDetailUser() The VendorKpiDetailUser does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorKpiDetailUserDTO, vendorKpiDetailUser);
        vendorKpiDetailUser.setUpdatedUser(vendorKpiDetailUserDTO.getUpdatedUser());
        vendorKpiDetailUser.setUpdatedTime(DateUtil.current());

        return this.updateById(vendorKpiDetailUser);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<VendorKpiDetailUser> vendorKpiDetailUserList) {

        vendorKpiDetailUserList.forEach(vendorKpiDetailUser -> {
            VendorKpiDetailUser vendorKpiDetailUserParam = new VendorKpiDetailUser();
            vendorKpiDetailUserParam.setId(vendorKpiDetailUser.getId());
            if (ObjectUtil.isNotNull(vendorKpiDetailUser.getId())) {
                vendorKpiDetailUser.setId(vendorKpiDetailUser.getId());
                vendorKpiDetailUser.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<VendorKpiDetailUser> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(VendorKpiDetailUser::getId, vendorKpiDetailUser.getId());
                update(vendorKpiDetailUser, lambdaUpdate);
            } else {
                vendorKpiDetailUser.setCreatedTime(DateUtil.current());
                vendorKpiDetailUser.setDeleted(0);
                save(vendorKpiDetailUser);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<VendorKpiDetailUserDTO> vendorKpiDetailUserDTOList) {

        List<VendorKpiDetailUser> vendorKpiDetailUserList = BeanUtils.copyProperties(vendorKpiDetailUserDTOList, VendorKpiDetailUser.class);
        return saveOrUpdateBatch(vendorKpiDetailUserList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVendorKpiDetailUserLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        VendorKpiDetailUser vendorKpiDetailUser = this.getById(id);

        if (ObjectUtil.isNull(vendorKpiDetailUser)) {
            log.error("deleteVendorKpiDetailUser() The VendorKpiDetailUser does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        vendorKpiDetailUser.setDeleted(1);

        return this.updateById(vendorKpiDetailUser);
    }

    private List<VendorKpiDetailUser> queryVendorKpiDetailUserList(VendorKpiDetailUserFilterDTO vendorKpiDetailUserFilterDTO) {
        if (ObjectUtil.isNull(vendorKpiDetailUserFilterDTO)) {
            vendorKpiDetailUserFilterDTO = new VendorKpiDetailUserFilterDTO();
        }
        vendorKpiDetailUserFilterDTO.setDeleted(0);
        VendorKpiDetailUserQuery vendorKpiDetailUserQuery = BeanUtil.copyProperties(vendorKpiDetailUserFilterDTO, VendorKpiDetailUserQuery.class);

        return vendorKpiDetailUserMapper.queryVendorKpiDetailUser(vendorKpiDetailUserQuery);
    }

    @Override
    public List<VendorKpiDetailUserVO> queryVendorKpiDetailUserVOList(VendorKpiDetailUserFilterDTO vendorKpiDetailUserFilterDTO) {
        if (ObjectUtil.isNull(vendorKpiDetailUserFilterDTO)) {
            vendorKpiDetailUserFilterDTO = new VendorKpiDetailUserFilterDTO();
        }
        vendorKpiDetailUserFilterDTO.setDeleted(0);
        return this.objectConversion(queryVendorKpiDetailUserList(vendorKpiDetailUserFilterDTO));
    }

    @Override
    public PageVO<VendorKpiDetailUserVO> queryVendorKpiDetailUser(VendorKpiDetailUserFilterDTO vendorKpiDetailUserFilterDTO) {
        if (ObjectUtil.isNull(vendorKpiDetailUserFilterDTO)) {
            vendorKpiDetailUserFilterDTO = new VendorKpiDetailUserFilterDTO();
        }
        vendorKpiDetailUserFilterDTO.setDeleted(0);
        PageVO<VendorKpiDetailUserVO> pageVO = new PageVO<>();

        if (vendorKpiDetailUserFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorKpiDetailUserFilterDTO.getCurrentPage(), vendorKpiDetailUserFilterDTO.getPageSize(),
                    vendorKpiDetailUserFilterDTO.getOrderType(), vendorKpiDetailUserFilterDTO.getOrderField());
        }

        List<VendorKpiDetailUser> vendorKpiDetailUserList = queryVendorKpiDetailUserList(vendorKpiDetailUserFilterDTO);

        PageInfo<VendorKpiDetailUser> pageInfo = new PageInfo<>(vendorKpiDetailUserList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(vendorKpiDetailUserList));

        return pageVO;
    }

    @Override
    public VendorKpiDetailUserVO queryVendorKpiDetailUserById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
