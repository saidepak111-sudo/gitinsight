package com.deepak.gitinsight.Config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
@EnableScheduling
public class AsyncConfig {
 @Bean("taskExecutor")
 public Executor taskExecutor() {
  ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
  executor.setCorePoolSize(2);
  executor.setMaxPoolSize(4);
  executor.setQueueCapacity(100);
  executor.setThreadNamePrefix("async-thread-");
  executor.initialize();
  return executor;
 }
}
