package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserPurchaseOrz;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserPurchaseOrzDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserPurchaseOrzFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.UserPurchaseOrzVO;

import java.util.List;

/**
 * 用户采购组织中间表(UserPurchaseOrz)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:58:44
 */
public interface UserPurchaseOrzBaseService<S, T, E> extends BaseService<UserPurchaseOrz, UserPurchaseOrzVO, UserPurchaseOrz> {

    /**
     * 添加用户采购组织中间表(UserPurchaseOrz)
     *
     * @param userPurchaseOrzDTO 用户采购组织中间表(UserPurchaseOrzDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveUserPurchaseOrz(UserPurchaseOrzDTO userPurchaseOrzDTO);

    /**
     * 修改用户采购组织中间表(UserPurchaseOrz)
     *
     * @param userPurchaseOrzDTO 用户采购组织中间表(UserPurchaseOrzDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateUserPurchaseOrz(UserPurchaseOrzDTO userPurchaseOrzDTO);

    /**
     * 批量保存或更新用户采购组织中间表(UserPurchaseOrz)
     *
     * @param userPurchaseOrzList 用户采购组织中间表(UserPurchaseOrzList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<UserPurchaseOrz> userPurchaseOrzList);

    /**
     * 批量保存或更新用户采购组织中间表(UserPurchaseOrzDTOList)
     *
     * @param userPurchaseOrzDTOList 用户采购组织中间表(UserPurchaseOrzDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<UserPurchaseOrzDTO> userPurchaseOrzDTOList);

    /**
     * 删除用户采购组织中间表(UserPurchaseOrz)
     *
     * @param id 用户采购组织中间表(UserPurchaseOrz)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteUserPurchaseOrzLogical(Long id);

    /**
     * 查询用户采购组织中间表(UserPurchaseOrz)
     *
     * @param userPurchaseOrzFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<UserPurchaseOrzVO>} 状态信息
     */
    List<UserPurchaseOrzVO> queryUserPurchaseOrzVOList(UserPurchaseOrzFilterDTO userPurchaseOrzFilterDTO);

    /**
     * 查询用户采购组织中间表(UserPurchaseOrz)
     *
     * @param userPurchaseOrzFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<UserPurchaseOrzVO>} 状态信息
     */
    PageVO<UserPurchaseOrzVO> queryUserPurchaseOrz(UserPurchaseOrzFilterDTO userPurchaseOrzFilterDTO);

    /**
     * 查询一个
     *
     * @param id 用户采购组织中间表(UserPurchaseOrz)主键
     * @return {@link UserPurchaseOrzVO} 用户采购组织中间表(UserPurchaseOrz)信息
     */
    UserPurchaseOrzVO queryUserPurchaseOrzById(Long id);


}
