package com.xdcplus.xdcweb.biz.mapper;

import com.xdcplus.mp.mapper.IBaseMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.InquiryMaterial;
import com.xdcplus.xdcweb.biz.common.pojo.query.InquiryMaterialQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 询价物品(InquiryMaterial)表数据库访问层
 *
 * @author Fish.Fei
 * @since 2021-07-06 14:17:44
 */
public interface InquiryMaterialMapper extends IBaseMapper<InquiryMaterial> {

    /**
     * 查询询价物品(InquiryMaterial)
     *
     * @param inquiryMaterialQuery 询价物品(InquiryMaterial)查询
     * @return {@link List<InquiryMaterial>}
     */
    List<InquiryMaterial> queryInquiryMaterial(InquiryMaterialQuery inquiryMaterialQuery);

}
