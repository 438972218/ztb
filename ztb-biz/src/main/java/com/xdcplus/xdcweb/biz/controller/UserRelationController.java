package com.xdcplus.xdcweb.biz.controller;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.xdcplus.mp.controller.AbstractController;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.permission.PceCompanyResponseUtils;
import com.xdcplus.xdcweb.biz.common.permission.PceUserResponseUtils;
import com.xdcplus.xdcweb.biz.common.pojo.dto.*;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserCategory;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserCompany;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserPurchaseOrz;
import com.xdcplus.xdcweb.biz.common.pojo.entity.UserVendor;
import com.xdcplus.xdcweb.biz.common.pojo.vo.*;
import com.xdcplus.xdcweb.biz.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validation;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 用户关联单独入口
 *
 * @author makejava
 * @since 2021-07-22 15:38:41
 */
@Api(tags = "用户关联(UserRelation)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("userRelation")
public class UserRelationController extends AbstractController {

    @Autowired
    private final UserCompanyService userCompanyService;
    @Autowired
    private final UserVendorService userVendorService;
    @Autowired
    private final UserCategoryService userCategoryService;
    @Autowired
    private final UserPurchaseOrzService userPurchaseOrzService;
    @Autowired
    private final VendorService vendorService;

    @ApiOperation("新增,维护用户关联")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveUserCompany(@RequestBody UserRelationDTO userRelationDTO) {

        log.info("saveUserRelation {}", userRelationDTO.toString());

        userRelationDTO.setCreatedUser(getAccount());
        userCompanyService.deleteUserCompanyByUserId(userRelationDTO.getUserId());
        userVendorService.deleteUserVendorByUserId(userRelationDTO.getUserId());
        userCategoryService.deleteUserCategoryByUserId(userRelationDTO.getUserId());
        userPurchaseOrzService.deleteUserPurchaseOrzByUserId(userRelationDTO.getUserId());

        if(CollectionUtil.isNotEmpty(userRelationDTO.getCompanyIds())){
            UserCompany userCompany;
            List<Long> companyIds=userRelationDTO.getCompanyIds();
            for (Long companyId:companyIds){
                userCompany=new UserCompany();
                userCompany.setUserId(userRelationDTO.getUserId());
                userCompany.setCompanyId(companyId);
                userCompany.setCreatedUser(getAccount());
                userCompany.setCreatedTime(DateUtil.current());
                userCompany.setDeleted(0);
                userCompanyService.save(userCompany);
            }
        }
        if(CollectionUtil.isNotEmpty(userRelationDTO.getVendorIds())){
            UserVendor userVendor;
            List<Long> vendorIds=userRelationDTO.getVendorIds();
            for (Long vendorId:vendorIds){
                userVendor=new UserVendor();
                userVendor.setUserId(userRelationDTO.getUserId());
                userVendor.setVendorId(vendorId);
                userVendor.setCreatedUser(getAccount());
                userVendor.setCreatedTime(DateUtil.current());
                userVendor.setDeleted(0);
                userVendorService.save(userVendor);
            }
        }

        if(CollectionUtil.isNotEmpty(userRelationDTO.getCategoryIds())){
            UserCategory userCategory;
            List<Long> categoryIds=userRelationDTO.getCategoryIds();
            for (Long categoryId:categoryIds){
                userCategory=new UserCategory();
                userCategory.setUserId(userRelationDTO.getUserId());
                userCategory.setCategoryId(categoryId);
                userCategory.setCreatedUser(getAccount());
                userCategory.setCreatedTime(DateUtil.current());
                userCategory.setDeleted(0);
                userCategoryService.save(userCategory);
            }
        }

        if(CollectionUtil.isNotEmpty(userRelationDTO.getPurchaseOrzIds())){
            UserPurchaseOrz userPurchaseOrz;
            List<Long> purchaseOrzIds=userRelationDTO.getPurchaseOrzIds();
            for (Long purchaseId:purchaseOrzIds){
                userPurchaseOrz=new UserPurchaseOrz();
                userPurchaseOrz.setUserId(userRelationDTO.getUserId());
                userPurchaseOrz.setPurchaseOrzId(purchaseId);
                userPurchaseOrz.setCreatedUser(getAccount());
                userPurchaseOrz.setCreatedTime(DateUtil.current());
                userPurchaseOrz.setDeleted(0);
                userPurchaseOrzService.save(userPurchaseOrz);
            }
        }
        return ResponseVO.success();
    }


    @ApiOperation("用户关联详情")
    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "userId", dataType = "Long", value = "用户ID", required = true),
    })
    public ResponseVO<UserRelationVO> showUserRelation(@PathVariable("userId")
                                               @NotNull(message = "用户ID不能为空") Long userId) {

        log.info("showUserRelation {}", userId);
        UserRelationVO userRelationVO=new UserRelationVO();
        userRelationVO.setUserId(userId);

        UserCompanyFilterDTO userCompanyFilterDTO=new UserCompanyFilterDTO();
        userCompanyFilterDTO.setUserId(userId);
        userCompanyFilterDTO.setDeleted(0);
        List<UserCompanyVO> userCompanyVOS=userCompanyService.queryUserCompanyVOList(userCompanyFilterDTO);

        if(CollectionUtil.isNotEmpty(userCompanyVOS)){
            userRelationVO.setCompanyIds(userCompanyVOS.stream().map(s->s.getCompanyId()).collect(Collectors.toList()));
        }

        UserVendorFilterDTO userVendorFilterDTO=new UserVendorFilterDTO();
        userVendorFilterDTO.setUserId(userId);
        userVendorFilterDTO.setDeleted(0);
        List<UserVendorVO> userVendorVOS = userVendorService.queryUserVendorVOList(userVendorFilterDTO);

        if(CollectionUtil.isNotEmpty(userVendorVOS)){
            userRelationVO.setVendorIds(userVendorVOS.stream().map(s->s.getVendorId()).collect(Collectors.toList()));
            List<String> vendorNames=new ArrayList<>();
            userVendorVOS.forEach(userVendorVO -> {
               VendorVO vendorVO = vendorService.queryVendorById(userVendorVO.getVendorId());
               if(ObjectUtil.isNotNull(vendorVO)){
                   vendorNames.add(vendorVO.getName());
               }
            });
            userRelationVO.setVendorNames(vendorNames);
        }

        UserCategoryFilterDTO userCategoryFilterDTO=new UserCategoryFilterDTO();
        userCategoryFilterDTO.setUserId(userId);
        userCategoryFilterDTO.setDeleted(0);
        List<UserCategoryVO> userCategoryVOS = userCategoryService.queryUserCategoryVOList(userCategoryFilterDTO);
        if(CollectionUtil.isNotEmpty(userCategoryVOS)){
            userRelationVO.setCategoryIds(userCategoryVOS.stream().map(s->s.getCategoryId()).collect(Collectors.toList()));
        }
        UserPurchaseOrzFilterDTO userPurchaseOrzFilterDTO=new UserPurchaseOrzFilterDTO();
        userPurchaseOrzFilterDTO.setUserId(userId);
        userPurchaseOrzFilterDTO.setDeleted(0);
        List<UserPurchaseOrzVO> userPurchaseOrzVOS=userPurchaseOrzService.queryUserPurchaseOrzVOList(userPurchaseOrzFilterDTO);

        if(CollectionUtil.isNotEmpty(userPurchaseOrzVOS)){
            userRelationVO.setPurchaseOrzIds(userPurchaseOrzVOS.stream().map(s->s.getPurchaseOrzId()).collect(Collectors.toList()));
        }

        return ResponseVO.success(userRelationVO);
    }
}
