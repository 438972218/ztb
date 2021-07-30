package com.xdcplus.xdcweb.biz.service.impl;

import com.xdcplus.xdcweb.biz.generator.impl.DictionaryBaseServiceImpl;
import com.xdcplus.xdcweb.biz.mapper.DictionaryMapper;
import com.xdcplus.xdcweb.biz.common.pojo.entity.Dictionary;
import com.xdcplus.xdcweb.biz.common.pojo.vo.DictionaryVO;
import com.xdcplus.xdcweb.biz.service.DictionaryService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 字典管理(Dictionary)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-07-27 10:49:33
 */
@Slf4j
@Service("dictionaryService")
public class DictionaryServiceImpl extends DictionaryBaseServiceImpl<Dictionary, DictionaryVO, Dictionary, DictionaryMapper> implements DictionaryService {


}
