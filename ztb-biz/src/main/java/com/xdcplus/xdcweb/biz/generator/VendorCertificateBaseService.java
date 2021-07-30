package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorCertificate;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorCertificateDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorCertificateFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorCertificateVO;

import java.util.List;

/**
 * 供应商证书(VendorCertificate)表服务接口层
 *
 * @author Fish.Fei
 * @since 2021-07-27 11:31:38
 */
public interface VendorCertificateBaseService<S, T, E> extends BaseService<VendorCertificate, VendorCertificateVO, VendorCertificate> {

    /**
     * 添加供应商证书(VendorCertificate)
     *
     * @param vendorCertificateDTO 供应商证书(VendorCertificateDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveVendorCertificate(VendorCertificateDTO vendorCertificateDTO);

    /**
     * 修改供应商证书(VendorCertificate)
     *
     * @param vendorCertificateDTO 供应商证书(VendorCertificateDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorCertificate(VendorCertificateDTO vendorCertificateDTO);

    /**
     * 批量保存或更新供应商证书(VendorCertificate)
     *
     * @param vendorCertificateList 供应商证书(VendorCertificateList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<VendorCertificate> vendorCertificateList);

    /**
     * 批量保存或更新供应商证书(VendorCertificateDTOList)
     *
     * @param vendorCertificateDTOList 供应商证书(VendorCertificateDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<VendorCertificateDTO> vendorCertificateDTOList);

    /**
     * 删除供应商证书(VendorCertificate)
     *
     * @param id 供应商证书(VendorCertificate)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteVendorCertificateLogical(Long id);

    /**
     * 查询供应商证书(VendorCertificate)
     *
     * @param vendorCertificateFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorCertificateVO>} 状态信息
     */
    List<VendorCertificateVO> queryVendorCertificateVOList(VendorCertificateFilterDTO vendorCertificateFilterDTO);

    /**
     * 查询供应商证书(VendorCertificate)
     *
     * @param vendorCertificateFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorCertificateVO>} 状态信息
     */
    PageVO<VendorCertificateVO> queryVendorCertificate(VendorCertificateFilterDTO vendorCertificateFilterDTO);

    /**
     * 查询一个
     *
     * @param id 供应商证书(VendorCertificate)主键
     * @return {@link VendorCertificateVO} 供应商证书(VendorCertificate)信息
     */
    VendorCertificateVO queryVendorCertificateById(Long id);


}
