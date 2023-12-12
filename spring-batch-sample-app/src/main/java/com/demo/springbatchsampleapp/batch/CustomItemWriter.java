package com.demo.springbatchsampleapp.batch;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class CustomItemWriter implements ItemWriter<String> {

	@Override
	public void write(Chunk<? extends String> chunk) throws Exception {
		// TODO Auto-generated method stub

	}

}
