package com.xdcplus.xdcweb.biz.generator;

import com.xdcplus.mp.service.BaseService;
import com.xdcplus.tool.pojo.vo.PageVO;
import com.xdcplus.xdcweb.biz.common.pojo.entity.VendorRequestCertificate;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorRequestCertificateDTO;
import com.xdcplus.xdcweb.biz.common.pojo.dto.VendorRequestCertificateFilterDTO;
import com.xdcplus.xdcweb.biz.common.pojo.vo.VendorRequestCertificateVO;

import java.util.List;

/**
 * 供应商注册单证书(VendorRequestCertificate)表服务接口层
 *
 * @author makejava
 * @since 2021-07-27 11:08:57
 */
public interface VendorRequestCertificateBaseService<S, T, E> extends BaseService<VendorRequestCertificate, VendorRequestCertificateVO, VendorRequestCertificate> {

    /**
     * 添加供应商注册单证书(VendorRequestCertificate)
     *
     * @param vendorRequestCertificateDTO 供应商注册单证书(VendorRequestCertificateDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveVendorRequestCertificate(VendorRequestCertificateDTO vendorRequestCertificateDTO);

    /**
     * 修改供应商注册单证书(VendorRequestCertificate)
     *
     * @param vendorRequestCertificateDTO 供应商注册单证书(VendorRequestCertificateDTO)
     * @return {@link Boolean} 是否成功
     */
    Boolean updateVendorRequestCertificate(VendorRequestCertificateDTO vendorRequestCertificateDTO);

    /**
     * 批量保存或更新供应商注册单证书(VendorRequestCertificate)
     *
     * @param vendorRequestCertificateList 供应商注册单证书(VendorRequestCertificateList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatch(List<VendorRequestCertificate> vendorRequestCertificateList);

    /**
     * 批量保存或更新供应商注册单证书(VendorRequestCertificateDTOList)
     *
     * @param vendorRequestCertificateDTOList 供应商注册单证书(VendorRequestCertificateDTOList)
     * @return {@link Boolean} 是否成功
     */
    Boolean saveOrUpdateBatchByDTOList(List<VendorRequestCertificateDTO> vendorRequestCertificateDTOList);

    /**
     * 删除供应商注册单证书(VendorRequestCertificate)
     *
     * @param id 供应商注册单证书(VendorRequestCertificate)主键
     * @return {@link Boolean} 是否成功
     */
    Boolean deleteVendorRequestCertificateLogical(Long id);

    /**
     * 查询供应商注册单证书(VendorRequestCertificate)
     *
     * @param vendorRequestCertificateFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorRequestCertificateVO>} 状态信息
     */
    List<VendorRequestCertificateVO> queryVendorRequestCertificateVOList(VendorRequestCertificateFilterDTO vendorRequestCertificateFilterDTO);

    /**
     * 查询供应商注册单证书(VendorRequestCertificate)
     *
     * @param vendorRequestCertificateFilterDTO 过程状态过滤DTO
     * @return {@link PageVO<VendorRequestCertificateVO>} 状态信息
     */
    PageVO<VendorRequestCertificateVO> queryVendorRequestCertificate(VendorRequestCertificateFilterDTO vendorRequestCertificateFilterDTO);

    /**
     * 查询一个
     *
     * @param id 供应商注册单证书(VendorRequestCertificate)主键
     * @return {@link VendorRequestCertificateVO} 供应商注册单证书(VendorRequestCertificate)信息
     */
    VendorRequestCertificateVO queryVendorRequestCertificateById(Long id);


}
