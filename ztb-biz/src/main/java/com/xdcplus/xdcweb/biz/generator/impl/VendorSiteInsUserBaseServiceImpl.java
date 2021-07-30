package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorSiteInsUserMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorSiteInsUser;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsUserDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsUserFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorSiteInsUserVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorSiteInsUserQuery;
import com.xdcplus.xdcweb.biz.generator.VendorSiteInsUserBaseService;
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
 * 供应商现场考察人员表(VendorSiteInsUser)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:58
 */
public class VendorSiteInsUserBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<VendorSiteInsUser, VendorSiteInsUserVO, VendorSiteInsUser, VendorSiteInsUserMapper> implements VendorSiteInsUserBaseService<S, T, E> {

    @Autowired
    protected VendorSiteInsUserMapper vendorSiteInsUserMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveVendorSiteInsUser(VendorSiteInsUserDTO vendorSiteInsUserDTO) {

        VendorSiteInsUser vendorSiteInsUser = vendorSiteInsUserMapper.selectById(vendorSiteInsUserDTO.getId());
        if (ObjectUtil.isNotNull(vendorSiteInsUser)) {
            log.error("saveVendorSiteInsUser() The VendorSiteInsUser already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorSiteInsUser = new VendorSiteInsUser();
        BeanUtil.copyProperties(vendorSiteInsUserDTO, vendorSiteInsUser);
        vendorSiteInsUser.setCreatedTime(DateUtil.current());
        vendorSiteInsUser.setDeleted(0);

        return this.save(vendorSiteInsUser);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateVendorSiteInsUser(VendorSiteInsUserDTO vendorSiteInsUserDTO) {

        VendorSiteInsUser vendorSiteInsUser = this.getById(vendorSiteInsUserDTO.getId());
        if (ObjectUtil.isNull(vendorSiteInsUser)) {
            log.error("updateVendorSiteInsUser() The VendorSiteInsUser does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorSiteInsUserDTO, vendorSiteInsUser);
        vendorSiteInsUser.setUpdatedUser(vendorSiteInsUserDTO.getUpdatedUser());
        vendorSiteInsUser.setUpdatedTime(DateUtil.current());

        return this.updateById(vendorSiteInsUser);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<VendorSiteInsUser> vendorSiteInsUserList) {

        vendorSiteInsUserList.forEach(vendorSiteInsUser -> {
            VendorSiteInsUser vendorSiteInsUserParam = new VendorSiteInsUser();
            vendorSiteInsUserParam.setId(vendorSiteInsUser.getId());
            if (ObjectUtil.isNotNull(vendorSiteInsUser.getId())) {
                vendorSiteInsUser.setId(vendorSiteInsUser.getId());
                vendorSiteInsUser.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<VendorSiteInsUser> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(VendorSiteInsUser::getId, vendorSiteInsUser.getId());
                update(vendorSiteInsUser, lambdaUpdate);
            } else {
                vendorSiteInsUser.setCreatedTime(DateUtil.current());
                vendorSiteInsUser.setDeleted(0);
                save(vendorSiteInsUser);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<VendorSiteInsUserDTO> vendorSiteInsUserDTOList) {

        List<VendorSiteInsUser> vendorSiteInsUserList = BeanUtils.copyProperties(vendorSiteInsUserDTOList, VendorSiteInsUser.class);
        return saveOrUpdateBatch(vendorSiteInsUserList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVendorSiteInsUserLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        VendorSiteInsUser vendorSiteInsUser = this.getById(id);

        if (ObjectUtil.isNull(vendorSiteInsUser)) {
            log.error("deleteVendorSiteInsUser() The VendorSiteInsUser does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        vendorSiteInsUser.setDeleted(1);

        return this.updateById(vendorSiteInsUser);
    }

    private List<VendorSiteInsUser> queryVendorSiteInsUserList(VendorSiteInsUserFilterDTO vendorSiteInsUserFilterDTO) {
        if (ObjectUtil.isNull(vendorSiteInsUserFilterDTO)) {
            vendorSiteInsUserFilterDTO = new VendorSiteInsUserFilterDTO();
        }
        vendorSiteInsUserFilterDTO.setDeleted(0);
        VendorSiteInsUserQuery vendorSiteInsUserQuery = BeanUtil.copyProperties(vendorSiteInsUserFilterDTO, VendorSiteInsUserQuery.class);

        return vendorSiteInsUserMapper.queryVendorSiteInsUser(vendorSiteInsUserQuery);
    }

    @Override
    public List<VendorSiteInsUserVO> queryVendorSiteInsUserVOList(VendorSiteInsUserFilterDTO vendorSiteInsUserFilterDTO) {
        if (ObjectUtil.isNull(vendorSiteInsUserFilterDTO)) {
            vendorSiteInsUserFilterDTO = new VendorSiteInsUserFilterDTO();
        }
        vendorSiteInsUserFilterDTO.setDeleted(0);
        return this.objectConversion(queryVendorSiteInsUserList(vendorSiteInsUserFilterDTO));
    }

    @Override
    public PageVO<VendorSiteInsUserVO> queryVendorSiteInsUser(VendorSiteInsUserFilterDTO vendorSiteInsUserFilterDTO) {
        if (ObjectUtil.isNull(vendorSiteInsUserFilterDTO)) {
            vendorSiteInsUserFilterDTO = new VendorSiteInsUserFilterDTO();
        }
        vendorSiteInsUserFilterDTO.setDeleted(0);
        PageVO<VendorSiteInsUserVO> pageVO = new PageVO<>();

        if (vendorSiteInsUserFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorSiteInsUserFilterDTO.getCurrentPage(), vendorSiteInsUserFilterDTO.getPageSize(),
                    vendorSiteInsUserFilterDTO.getOrderType(), vendorSiteInsUserFilterDTO.getOrderField());
        }

        List<VendorSiteInsUser> vendorSiteInsUserList = queryVendorSiteInsUserList(vendorSiteInsUserFilterDTO);

        PageInfo<VendorSiteInsUser> pageInfo = new PageInfo<>(vendorSiteInsUserList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(vendorSiteInsUserList));

        return pageVO;
    }

    @Override
    public VendorSiteInsUserVO queryVendorSiteInsUserById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
