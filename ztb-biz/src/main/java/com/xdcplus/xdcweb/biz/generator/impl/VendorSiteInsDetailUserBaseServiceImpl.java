package com.xdcplus.xdcweb.biz.generator.impl;

import com.xdcplus.mp.service.impl.BaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.VendorSiteInsDetailUserMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorSiteInsDetailUser;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsDetailUserDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorSiteInsDetailUserFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorSiteInsDetailUserVO;
import com.xdcplus.xdcweb.biz.common.pojo.query.VendorSiteInsDetailUserQuery;
import com.xdcplus.xdcweb.biz.generator.VendorSiteInsDetailUserBaseService;
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
 * 供应商现场考察人员评价表(VendorSiteInsDetailUser)表服务基础实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 15:40:57
 */
public class VendorSiteInsDetailUserBaseServiceImpl<S, T, E, M extends BaseMapper<E>> extends BaseServiceImpl<VendorSiteInsDetailUser, VendorSiteInsDetailUserVO, VendorSiteInsDetailUser, VendorSiteInsDetailUserMapper> implements VendorSiteInsDetailUserBaseService<S, T, E> {

    @Autowired
    protected VendorSiteInsDetailUserMapper vendorSiteInsDetailUserMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveVendorSiteInsDetailUser(VendorSiteInsDetailUserDTO vendorSiteInsDetailUserDTO) {

        VendorSiteInsDetailUser vendorSiteInsDetailUser = vendorSiteInsDetailUserMapper.selectById(vendorSiteInsDetailUserDTO.getId());
        if (ObjectUtil.isNotNull(vendorSiteInsDetailUser)) {
            log.error("saveVendorSiteInsDetailUser() The VendorSiteInsDetailUser already exists");
            throw new InvException(ResponseEnum.ERROR);
        }

        vendorSiteInsDetailUser = new VendorSiteInsDetailUser();
        BeanUtil.copyProperties(vendorSiteInsDetailUserDTO, vendorSiteInsDetailUser);
        vendorSiteInsDetailUser.setCreatedTime(DateUtil.current());
        vendorSiteInsDetailUser.setDeleted(0);

        return this.save(vendorSiteInsDetailUser);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean updateVendorSiteInsDetailUser(VendorSiteInsDetailUserDTO vendorSiteInsDetailUserDTO) {

        VendorSiteInsDetailUser vendorSiteInsDetailUser = this.getById(vendorSiteInsDetailUserDTO.getId());
        if (ObjectUtil.isNull(vendorSiteInsDetailUser)) {
            log.error("updateVendorSiteInsDetailUser() The VendorSiteInsDetailUser does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }

        BeanUtil.copyProperties(vendorSiteInsDetailUserDTO, vendorSiteInsDetailUser);
        vendorSiteInsDetailUser.setUpdatedUser(vendorSiteInsDetailUserDTO.getUpdatedUser());
        vendorSiteInsDetailUser.setUpdatedTime(DateUtil.current());

        return this.updateById(vendorSiteInsDetailUser);
    }

    @Override
    public Boolean saveOrUpdateBatch(List<VendorSiteInsDetailUser> vendorSiteInsDetailUserList) {

        vendorSiteInsDetailUserList.forEach(vendorSiteInsDetailUser -> {
            VendorSiteInsDetailUser vendorSiteInsDetailUserParam = new VendorSiteInsDetailUser();
            vendorSiteInsDetailUserParam.setId(vendorSiteInsDetailUser.getId());
            if (ObjectUtil.isNotNull(vendorSiteInsDetailUser.getId())) {
                vendorSiteInsDetailUser.setId(vendorSiteInsDetailUser.getId());
                vendorSiteInsDetailUser.setUpdatedTime(DateUtil.current());
                LambdaUpdateWrapper<VendorSiteInsDetailUser> lambdaUpdate = Wrappers.lambdaUpdate();
                lambdaUpdate.eq(VendorSiteInsDetailUser::getId, vendorSiteInsDetailUser.getId());
                update(vendorSiteInsDetailUser, lambdaUpdate);
            } else {
                vendorSiteInsDetailUser.setCreatedTime(DateUtil.current());
                vendorSiteInsDetailUser.setDeleted(0);
                save(vendorSiteInsDetailUser);
            }
        });
        return true;
    }

    @Override
    public Boolean saveOrUpdateBatchByDTOList(List<VendorSiteInsDetailUserDTO> vendorSiteInsDetailUserDTOList) {

        List<VendorSiteInsDetailUser> vendorSiteInsDetailUserList = BeanUtils.copyProperties(vendorSiteInsDetailUserDTOList, VendorSiteInsDetailUser.class);
        return saveOrUpdateBatch(vendorSiteInsDetailUserList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteVendorSiteInsDetailUserLogical(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        VendorSiteInsDetailUser vendorSiteInsDetailUser = this.getById(id);

        if (ObjectUtil.isNull(vendorSiteInsDetailUser)) {
            log.error("deleteVendorSiteInsDetailUser() The VendorSiteInsDetailUser does not exist or has been deleted");
            throw new InvException(ResponseEnum.ERROR);
        }
        vendorSiteInsDetailUser.setDeleted(1);

        return this.updateById(vendorSiteInsDetailUser);
    }

    private List<VendorSiteInsDetailUser> queryVendorSiteInsDetailUserList(VendorSiteInsDetailUserFilterDTO vendorSiteInsDetailUserFilterDTO) {
        if (ObjectUtil.isNull(vendorSiteInsDetailUserFilterDTO)) {
            vendorSiteInsDetailUserFilterDTO = new VendorSiteInsDetailUserFilterDTO();
        }
        vendorSiteInsDetailUserFilterDTO.setDeleted(0);
        VendorSiteInsDetailUserQuery vendorSiteInsDetailUserQuery = BeanUtil.copyProperties(vendorSiteInsDetailUserFilterDTO, VendorSiteInsDetailUserQuery.class);

        return vendorSiteInsDetailUserMapper.queryVendorSiteInsDetailUser(vendorSiteInsDetailUserQuery);
    }

    @Override
    public List<VendorSiteInsDetailUserVO> queryVendorSiteInsDetailUserVOList(VendorSiteInsDetailUserFilterDTO vendorSiteInsDetailUserFilterDTO) {
        if (ObjectUtil.isNull(vendorSiteInsDetailUserFilterDTO)) {
            vendorSiteInsDetailUserFilterDTO = new VendorSiteInsDetailUserFilterDTO();
        }
        vendorSiteInsDetailUserFilterDTO.setDeleted(0);
        return this.objectConversion(queryVendorSiteInsDetailUserList(vendorSiteInsDetailUserFilterDTO));
    }

    @Override
    public PageVO<VendorSiteInsDetailUserVO> queryVendorSiteInsDetailUser(VendorSiteInsDetailUserFilterDTO vendorSiteInsDetailUserFilterDTO) {
        if (ObjectUtil.isNull(vendorSiteInsDetailUserFilterDTO)) {
            vendorSiteInsDetailUserFilterDTO = new VendorSiteInsDetailUserFilterDTO();
        }
        vendorSiteInsDetailUserFilterDTO.setDeleted(0);
        PageVO<VendorSiteInsDetailUserVO> pageVO = new PageVO<>();

        if (vendorSiteInsDetailUserFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(vendorSiteInsDetailUserFilterDTO.getCurrentPage(), vendorSiteInsDetailUserFilterDTO.getPageSize(),
                    vendorSiteInsDetailUserFilterDTO.getOrderType(), vendorSiteInsDetailUserFilterDTO.getOrderField());
        }

        List<VendorSiteInsDetailUser> vendorSiteInsDetailUserList = queryVendorSiteInsDetailUserList(vendorSiteInsDetailUserFilterDTO);

        PageInfo<VendorSiteInsDetailUser> pageInfo = new PageInfo<>(vendorSiteInsDetailUserList);
        PropertyUtils.copyProperties(pageInfo, pageVO, this.objectConversion(vendorSiteInsDetailUserList));

        return pageVO;
    }

    @Override
    public VendorSiteInsDetailUserVO queryVendorSiteInsDetailUserById(Long id) {

        Assert.notNull(id, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return this.objectConversion(this.getById(id));
    }


}
