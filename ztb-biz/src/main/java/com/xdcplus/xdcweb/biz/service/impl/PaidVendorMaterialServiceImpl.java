package com.xdcplus.xdcweb.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.xdcplus.tool.constants.NumberConstant;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.tool.utils.PageableUtils;
import com.xdcplus.tool.utils.PropertyUtils;
import com.xdcplus.xdcweb.biz.common.enums.ResponseEnum;
import com.xdcplus.xdcweb.biz.common.exceptions.InvException;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidVendorFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidVendorMaterialDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.PaidVendorMaterialFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PaidVendor;
import com.xdcplus.xdcweb.biz.common.pojo.query.PaidVendorMaterialQuery;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidMaterialVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidSheetVO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidVendorVO;
import com.xdcplus.xdcweb.biz.generator.impl.PaidVendorMaterialBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.PaidVendorMaterialMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.PaidVendorMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.vo.PaidVendorMaterialVO;
import com.xdcplus.xdcweb.biz.service.PaidMaterialService;
import com.xdcplus.xdcweb.biz.service.PaidSheetService;
import com.xdcplus.xdcweb.biz.service.PaidVendorMaterialService;
import com.xdcplus.xdcweb.biz.service.PaidVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 竞价供应商物品(PaidVendorMaterial)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 08:48:48
 */
@Slf4j
@Service("paidVendorMaterialService")
public class PaidVendorMaterialServiceImpl extends PaidVendorMaterialBaseServiceImpl<PaidVendorMaterial, PaidVendorMaterialVO, PaidVendorMaterial, PaidVendorMaterialMapper> implements PaidVendorMaterialService {

    @Autowired
    private PaidSheetService paidSheetService;

    @Autowired
    private PaidMaterialService paidMaterialService;

    @Autowired
    private PaidVendorService paidVendorService;

    @Override
    public Integer getOfferingNumber(PaidVendorMaterialFilterDTO paidVendorMaterialFilterDTO) {
        List<PaidVendorMaterialVO> paidVendorMaterials = queryPaidVendorMaterialsDesc(paidVendorMaterialFilterDTO);
        if (CollectionUtil.isEmpty(paidVendorMaterials)) {
            return 1;
        } else {
            return paidVendorMaterials.get(0).getOfferingNumber() + 1;
        }
    }

    @Override
    public List<PaidVendorMaterialVO> queryPaidVendorMaterialsDesc(PaidVendorMaterialFilterDTO paidVendorMaterialFilterDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (paidVendorMaterialFilterDTO.getMaterialId() != null) {
            queryWrapper.eq("material_id", paidVendorMaterialFilterDTO.getMaterialId());
        }
        if (paidVendorMaterialFilterDTO.getPaidVendorId() != null) {
            queryWrapper.eq("paid_vendor_id", paidVendorMaterialFilterDTO.getPaidVendorId());
        }
        if (paidVendorMaterialFilterDTO.getOfferingType() != null) {
            queryWrapper.eq("offering_type", paidVendorMaterialFilterDTO.getOfferingType());
        }
        queryWrapper.orderByDesc("created_time");
        List<PaidVendorMaterial> paidVendorMaterials = paidVendorMaterialMapper.selectList(queryWrapper);
        List<PaidVendorMaterialVO> paidVendorMaterialVOS = this.objectConversion(paidVendorMaterials);
        if(CollectionUtil.isNotEmpty(paidVendorMaterialVOS)){
            combinePaidVendorVO(paidVendorMaterialVOS);
        }
        return paidVendorMaterialVOS;

    }

    @Override
    public PageVO<PaidVendorMaterialVO> queryPageVODesc(PaidVendorMaterialFilterDTO paidVendorMaterialFilterDTO) {
        PageVO<PaidVendorMaterialVO> pageVO = new PageVO<>();

        if (paidVendorMaterialFilterDTO.getCurrentPage() > NumberConstant.ZERO) {
            PageableUtils.basicPage(paidVendorMaterialFilterDTO.getCurrentPage(), paidVendorMaterialFilterDTO.getPageSize(),
                    paidVendorMaterialFilterDTO.getOrderType(), paidVendorMaterialFilterDTO.getOrderField());
        }

        List<PaidVendorMaterialVO> paidVendorMaterialList = queryPaidVendorMaterialsDesc(paidVendorMaterialFilterDTO);

        PageInfo<PaidVendorMaterialVO> pageInfo = new PageInfo<>(paidVendorMaterialList);
        PropertyUtils.copyProperties(pageInfo, pageVO, paidVendorMaterialList);

        return pageVO;
    }

    @Override
    public Boolean offeringPaidVendorMaterial(PaidVendorMaterialDTO paidVendorMaterialDTO) {
        PaidSheetVO paidSheetVO = paidSheetService.showPaidSheetById(paidVendorMaterialDTO.getSheetId());

        //最新报价
        Double price = paidVendorMaterialDTO.getPrice();
        PaidVendorMaterialFilterDTO paidVendorMaterialFilterDTO = new PaidVendorMaterialFilterDTO();
        BeanUtil.copyProperties(paidVendorMaterialDTO, paidVendorMaterialFilterDTO);
        List<PaidVendorMaterialVO> paidVendorMaterials = queryPaidVendorMaterialsDesc(paidVendorMaterialFilterDTO);

        //得到报价次数
        Integer offeringNumber = getOfferingNumber(paidVendorMaterialFilterDTO);
        paidVendorMaterialDTO.setOfferingNumber(offeringNumber);
        if (CollectionUtil.isEmpty(paidVendorMaterials)) {

            //修改供应商状态
            PaidVendor paidVendor = new PaidVendor();
            paidVendor.setId(paidVendorMaterialDTO.getPaidVendorId());
            paidVendor.setVendorStatus("已报价");
            paidVendorService.updateById(paidVendor);

            return savePaidVendorMaterial(paidVendorMaterialDTO);
        } else {
            //判断报价是否合理
            judgePrice(paidVendorMaterials, paidSheetVO, price);

            //修改供应商状态
            PaidVendor paidVendor = new PaidVendor();
            paidVendor.setId(paidVendorMaterialDTO.getPaidVendorId());
            paidVendor.setVendorStatus("已报价");
            paidVendorService.updateById(paidVendor);

            return savePaidVendorMaterial(paidVendorMaterialDTO);
        }
    }

    private void judgePrice(List<PaidVendorMaterialVO> paidVendorMaterials, PaidSheetVO paidSheetVO, Double price) {
        Double minPrice = null;
        //竞价方向
        String paidDirection = paidSheetVO.getPaidDirection();
        //竞价幅度
        String priceRange = paidSheetVO.getPriceRange();
        //幅度类型
        String rangeType = paidSheetVO.getRangeType();
        Assert.notNull(priceRange, ResponseEnum.VALUE_CANNOT_BE_EMPTY.getMessage());
        Assert.notNull(rangeType, ResponseEnum.VALUE_CANNOT_BE_EMPTY.getMessage());

        Double priceRangeD = null;
        if (rangeType.equals("百分比")) {
            priceRangeD = Double.valueOf(priceRange) / 100;
//            try {
//                priceRangeD = (Double) NumberFormat.getPercentInstance().parse(priceRange);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
        } else if (rangeType.equals("绝对值")) {
            priceRangeD = Double.valueOf(priceRange);
        }

        PaidVendorMaterialVO paidVendorMaterial = paidVendorMaterials.get(0);
        if (rangeType.equals("百分比")) {
            if (paidDirection.equals("正向")) {
                minPrice = paidVendorMaterial.getPrice() + (paidVendorMaterial.getPrice() * priceRangeD);
                if (price < minPrice) {
                    log.error("最低竞价为" + minPrice + ",当前出价为" + price);
                    throw new InvException(ResponseEnum.PAID_PRICE_ERROR, "最低竞价为" + minPrice);
                }
            } else {
                minPrice = paidVendorMaterial.getPrice() - (paidVendorMaterial.getPrice() * priceRangeD);
                if (price > minPrice) {
                    log.error("最低竞价为" + minPrice + ",当前出价为" + price);
                    throw new InvException(ResponseEnum.PAID_PRICE_ERROR, "最低竞价为" + minPrice);
                }
            }
        } else if (rangeType.equals("绝对值")) {
            if (paidDirection.equals("正向")) {
                minPrice = paidVendorMaterial.getPrice() + priceRangeD;
                if (price < minPrice) {
                    log.error("最低竞价为" + minPrice + ",当前出价为" + price);
                    throw new InvException(ResponseEnum.PAID_PRICE_ERROR, "最低竞价为" + minPrice);
                }
            } else {
                minPrice = paidVendorMaterial.getPrice() - priceRangeD;
                if (price > minPrice) {
                    log.error("最低竞价为" + minPrice + ",当前出价为" + price);
                    throw new InvException(ResponseEnum.PAID_PRICE_ERROR, "最低竞价为" + minPrice);
                }
            }
        }
    }

    @Override
    public PaidMaterialVO queryRanking(PaidVendorMaterialFilterDTO paidVendorMaterialFilterDTO) {
        PaidMaterialFilterDTO paidMaterialFilterDTO = new PaidMaterialFilterDTO();
        paidMaterialFilterDTO.setPaidSheetId(paidVendorMaterialFilterDTO.getSheetId());
        paidMaterialFilterDTO.setDeleted(0);
        List<PaidMaterialVO> paidMaterialVOS = paidMaterialService.queryPaidMaterialVOList(paidMaterialFilterDTO);
        if (CollUtil.isEmpty(paidMaterialVOS)) {
            log.error("paid_material found failed");
            throw new InvException(ResponseEnum.PAID_MATERIAL_SELECT_FAIL);
        }

        PaidMaterialVO paidMaterialVO = paidMaterialVOS.get(0);
        paidVendorMaterialFilterDTO.setMaterialId(paidMaterialVO.getId());

        //排名
        Integer ranking = paidVendorMaterialMapper.queryRankingByVendorId(paidVendorMaterialFilterDTO);
        paidMaterialVO.setCurrentRanking(ranking);
        PaidVendorFilterDTO paidVendorFilterDTO = new PaidVendorFilterDTO();
        paidVendorFilterDTO.setPaidSheetId(paidVendorMaterialFilterDTO.getSheetId());
        paidVendorFilterDTO.setVendorStatus("已报价");
        //竞价人数
        List<PaidVendorVO> paidVendorVOS = paidVendorService.queryPaidVendorVOList(paidVendorFilterDTO);
        if (CollectionUtil.isEmpty(paidVendorVOS)) {
            paidMaterialVO.setPaidNumber(0);
        } else {
            long paidNumber = paidVendorVOS.stream().count();
            paidMaterialVO.setPaidNumber(Math.toIntExact(paidNumber));
        }

        //排名明细
        paidVendorMaterialFilterDTO.setPaidVendorId(null);
        paidVendorMaterialFilterDTO.setMaterialId(paidMaterialVO.getId());
        List<PaidVendorMaterialVO> paidVendorMaterials = queryPaidVendorMaterialsDesc(paidVendorMaterialFilterDTO);
        paidMaterialVO.setPaidVendorMaterialVOS(paidVendorMaterials);

        return paidMaterialVO;
    }

    @Override
    public List<PaidVendorMaterialVO> queryNewPaidVendorMaterialByMaterialId(PaidVendorMaterialQuery paidVendorMaterialQuery) {
        List<PaidVendorMaterial> paidVendorMaterials = paidVendorMaterialMapper.queryNewPaidVendorMaterialByMaterialId(paidVendorMaterialQuery);
        if (CollUtil.isEmpty(paidVendorMaterials)) {
            return null;
        }
        List<PaidVendorMaterialVO> paidVendorMaterialVOS = this.objectConversion(paidVendorMaterials);
        paidVendorMaterialVOS.forEach(paidVendorMaterialVO -> {
            if (paidVendorMaterialVO.getMaterialId() != null) {
                PaidMaterialVO paidMaterialVO = paidMaterialService.queryPaidMaterialById(paidVendorMaterialVO.getMaterialId());
                paidVendorMaterialVO.setMaterialQuantity(paidMaterialVO.getDemandedQuantity());
            }
        });
        combinePaidVendorVO(paidVendorMaterialVOS);
        return paidVendorMaterialVOS;
    }

    @Override
    public List<PaidVendorMaterialVO> queryAllocatedRanking(PaidVendorMaterialDTO paidVendorMaterialDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("material_id", paidVendorMaterialDTO.getMaterialId());
        queryWrapper.isNotNull("allotted_quantity");
        List<PaidVendorMaterial> paidVendorMaterials = paidVendorMaterialMapper.selectList(queryWrapper);
        List<PaidVendorMaterialVO> paidVendorMaterialVOS = this.objectConversion(paidVendorMaterials);
        if(CollectionUtil.isNotEmpty(paidVendorMaterialVOS)){
            combinePaidVendorVO(paidVendorMaterialVOS);
        }

        return paidVendorMaterialVOS;
    }

    private void combinePaidVendorVO(List<PaidVendorMaterialVO> paidVendorMaterialVOS) {
        paidVendorMaterialVOS.forEach(paidVendorMaterialVO -> {
            if (paidVendorMaterialVO.getPaidVendorId() != null) {
                PaidVendorVO paidVendorVO = paidVendorService.queryPaidVendorById(paidVendorMaterialVO.getPaidVendorId());
                paidVendorMaterialVO.setPaidVendorVO(paidVendorVO);
            }
        });
    }


}
