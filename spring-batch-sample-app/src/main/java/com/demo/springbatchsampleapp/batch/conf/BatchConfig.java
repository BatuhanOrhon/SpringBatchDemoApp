package com.demo.springbatchsampleapp.batch.conf;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.demo.springbatchsampleapp.batch.CustomItemProcessor;
import com.demo.springbatchsampleapp.batch.CustomItemReader;
import com.demo.springbatchsampleapp.batch.CustomItemWriter;

@Configuration
public class BatchConfig {

	@Bean
	public CustomItemReader reader() {
		List<String> inputList = List.of("word1", "word2", "word3", "word4", "word5", "word6");
		return new CustomItemReader(inputList);
	}

	@Bean
	public CustomItemProcessor processor() {
		return new CustomItemProcessor();
	}

	@Bean
	public CustomItemWriter writer() {
		return new CustomItemWriter();
	}

	@Bean
	public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("step1", jobRepository).<String, String>chunk(0, transactionManager).reader(reader())
				.processor(processor()).writer(writer()).build();
	}

	@Bean
	public Job demoJob(JobRepository jobRepository) {
		return new JobBuilder("demoJob", jobRepository).flow(step1(null, null)).end().build();
	}
}
