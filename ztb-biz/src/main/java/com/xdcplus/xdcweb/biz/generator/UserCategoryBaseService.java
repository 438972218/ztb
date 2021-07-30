package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserCategory;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserCategoryDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserCategoryFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.UserCategoryVO;

import java.util.List;

/**
 * 用户品类中间表(UserCategory)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:58:41
 */
public interface UserCategoryBaseService<S, T, E> extends BaseService<UserCategory, UserCategoryVO, UserCategory> {

    /**
     * 添加用户品类中间表(UserCategory)
     *
     * @param userCategoryDTO 用户品类中间表(UserCategoryDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveUserCategory(UserCategoryDTO userCategoryDTO);

    /**
     * 修改用户品类中间表(UserCategory)
     *
     * @param userCategoryDTO 用户品类中间表(UserCategoryDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateUserCategory(UserCategoryDTO userCategoryDTO);

    /**
     * 批量保存或更新用户品类中间表(UserCategory)
     *
     * @param userCategoryList 用户品类中间表(UserCategoryList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<UserCategory> userCategoryList);

    /**
     * 批量保存或更新用户品类中间表(UserCategoryDTOList)
     *
     * @param userCategoryDTOList 用户品类中间表(UserCategoryDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<UserCategoryDTO> userCategoryDTOList);

    /**
     * 删除用户品类中间表(UserCategory)
     *
     * @param id 用户品类中间表(UserCategory)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteUserCategoryLogical(Long id);

    /**
     * 查询用户品类中间表(UserCategory)
     *
     * @param userCategoryFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<UserCategoryVO>} 状态信息
     */
    List<UserCategoryVO> queryUserCategoryVOList(UserCategoryFilterDTO userCategoryFilterDTO);

    /**
     * 查询用户品类中间表(UserCategory)
     *
     * @param userCategoryFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<UserCategoryVO>} 状态信息
     */
    PageVO<UserCategoryVO> queryUserCategory(UserCategoryFilterDTO userCategoryFilterDTO);

    /**
     * 查询一个
     *
     * @param id 用户品类中间表(UserCategory)主键
     * @return {@link UserCategoryVO} 用户品类中间表(UserCategory)信息
     */
    UserCategoryVO queryUserCategoryById(Long id);


}
