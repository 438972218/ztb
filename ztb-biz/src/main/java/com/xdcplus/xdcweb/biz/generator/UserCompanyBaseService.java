package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserCompany;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserCompanyDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.UserCompanyFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.UserCompanyVO;

import java.util.List;

/**
 * 用户公司中间表(UserCompany)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:58:42
 */
public interface UserCompanyBaseService<S, T, E> extends BaseService<UserCompany, UserCompanyVO, UserCompany> {

    /**
     * 添加用户公司中间表(UserCompany)
     *
     * @param userCompanyDTO 用户公司中间表(UserCompanyDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveUserCompany(UserCompanyDTO userCompanyDTO);

    /**
     * 修改用户公司中间表(UserCompany)
     *
     * @param userCompanyDTO 用户公司中间表(UserCompanyDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateUserCompany(UserCompanyDTO userCompanyDTO);

    /**
     * 批量保存或更新用户公司中间表(UserCompany)
     *
     * @param userCompanyList 用户公司中间表(UserCompanyList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<UserCompany> userCompanyList);

    /**
     * 批量保存或更新用户公司中间表(UserCompanyDTOList)
     *
     * @param userCompanyDTOList 用户公司中间表(UserCompanyDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<UserCompanyDTO> userCompanyDTOList);

    /**
     * 删除用户公司中间表(UserCompany)
     *
     * @param id 用户公司中间表(UserCompany)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteUserCompanyLogical(Long id);

    /**
     * 查询用户公司中间表(UserCompany)
     *
     * @param userCompanyFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<UserCompanyVO>} 状态信息
     */
    List<UserCompanyVO> queryUserCompanyVOList(UserCompanyFilterDTO userCompanyFilterDTO);

    /**
     * 查询用户公司中间表(UserCompany)
     *
     * @param userCompanyFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<UserCompanyVO>} 状态信息
     */
    PageVO<UserCompanyVO> queryUserCompany(UserCompanyFilterDTO userCompanyFilterDTO);

    /**
     * 查询一个
     *
     * @param id 用户公司中间表(UserCompany)主键
     * @return {@link UserCompanyVO} 用户公司中间表(UserCompany)信息
     */
    UserCompanyVO queryUserCompanyById(Long id);


}
