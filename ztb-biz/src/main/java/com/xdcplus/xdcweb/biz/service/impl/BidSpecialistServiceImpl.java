package com.xdcplus.xdcweb.biz.service.impl;

import com.xdcplus.xdcweb.biz.common.pojo.dto.BidSpecialistFilterDTO;
import com.xdcplus.xdcweb.biz.generator.impl.BidSpecialistBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.BidSpecialistMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.BidSpecialist;
import com.xdcplus.xdcweb.biz.common.pojo.vo.BidSpecialistVO;
import com.xdcplus.xdcweb.biz.service.BidSpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 专家(BidSpecialist)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-26 18:06:05
 */
@Slf4j
@Service("bidSpecialistService")
public class BidSpecialistServiceImpl extends BidSpecialistBaseServiceImpl<BidSpecialist, BidSpecialistVO, BidSpecialist, BidSpecialistMapper> implements BidSpecialistService {

}
