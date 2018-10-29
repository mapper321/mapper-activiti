package com.mapper.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.mapper.process.listener.MyEventListener;
import com.mapper.vo.ActivitiIdGenerator;

@Configuration
public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration{

	@Autowired
	ActivitiIdGenerator idGenerator;
	
	//此处有个小坑，如果返回值类型不是SpringProcessEngineConfiguration，会走springboot的默认配置类。
	@Bean
	public SpringProcessEngineConfiguration processEngineConfiguration(DataSource dataSource,
			PlatformTransactionManager transactionManager,SpringAsyncExecutor springAsyncExecutor) throws IOException {
		SpringProcessEngineConfiguration processEngineConfiguration = this.baseSpringProcessEngineConfiguration(dataSource, transactionManager, springAsyncExecutor);
		processEngineConfiguration.setDataSource(dataSource);
		processEngineConfiguration.setDatabaseType("mysql");
		processEngineConfiguration.setTransactionManager(transactionManager);
		//id生成策略
		processEngineConfiguration.setIdGenerator(idGenerator);
		// 流程图字体
		processEngineConfiguration.setActivityFontName("宋体");
		processEngineConfiguration.setAnnotationFontName("宋体");
		processEngineConfiguration.setLabelFontName("宋体");
		//事件监听器
//		List<ActivitiEventListener> eventListeners = new ArrayList<>();
//		eventListeners.add(new MyEventListener());
//		processEngineConfiguration.setEventListeners(eventListeners);
		return processEngineConfiguration;
	}
	
}
