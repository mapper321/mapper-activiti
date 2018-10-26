package com.mapper.vo;

import org.activiti.engine.impl.cfg.IdGenerator;
import org.springframework.stereotype.Service;

import com.mapper.core.util.UniqueIdUtil;

/**
 * 使用分布式的方式产生流程id。
 *
 */
@Service
public class ActivitiIdGenerator implements IdGenerator {

	@Override
	public String getNextId() {
		return String.valueOf(UniqueIdUtil.genId());
		
	}

}
