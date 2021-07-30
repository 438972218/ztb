package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserVendor;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserVendorDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserVendorFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.UserVendorVO;

import java.util.List;

/**
 * 用户供应商中间表(UserVendor)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:58:44
 */
public interface UserVendorBaseService<S, T, E> extends BaseService<UserVendor, UserVendorVO, UserVendor> {

    /**
     * 添加用户供应商中间表(UserVendor)
     *
     * @param userVendorDTO 用户供应商中间表(UserVendorDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveUserVendor(UserVendorDTO userVendorDTO);

    /**
     * 修改用户供应商中间表(UserVendor)
     *
     * @param userVendorDTO 用户供应商中间表(UserVendorDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateUserVendor(UserVendorDTO userVendorDTO);

    /**
     * 批量保存或更新用户供应商中间表(UserVendor)
     *
     * @param userVendorList 用户供应商中间表(UserVendorList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<UserVendor> userVendorList);

    /**
     * 批量保存或更新用户供应商中间表(UserVendorDTOList)
     *
     * @param userVendorDTOList 用户供应商中间表(UserVendorDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<UserVendorDTO> userVendorDTOList);

    /**
     * 删除用户供应商中间表(UserVendor)
     *
     * @param id 用户供应商中间表(UserVendor)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteUserVendorLogical(Long id);

    /**
     * 查询用户供应商中间表(UserVendor)
     *
     * @param userVendorFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<UserVendorVO>} 状态信息
     */
    List<UserVendorVO> queryUserVendorVOList(UserVendorFilterDTO userVendorFilterDTO);

    /**
     * 查询用户供应商中间表(UserVendor)
     *
     * @param userVendorFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<UserVendorVO>} 状态信息
     */
    PageVO<UserVendorVO> queryUserVendor(UserVendorFilterDTO userVendorFilterDTO);

    /**
     * 查询一个
     *
     * @param id 用户供应商中间表(UserVendor)主键
     * @return {@link UserVendorVO} 用户供应商中间表(UserVendor)信息
     */
    UserVendorVO queryUserVendorById(Long id);


}
