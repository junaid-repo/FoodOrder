package com.food.swigato.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class AsyncConfiguration {

	@Bean("asyncTaksExe")
	public Executor asyncExecutorTask() {

		ThreadPoolTaskExecutor exe = new ThreadPoolTaskExecutor();
		exe.setMaxPoolSize(4);
		exe.setCorePoolSize(3);
		exe.setQueueCapacity(200);
		exe.setThreadNamePrefix("GithubLookup-");
		exe.initialize();
		return exe;

	}

}
