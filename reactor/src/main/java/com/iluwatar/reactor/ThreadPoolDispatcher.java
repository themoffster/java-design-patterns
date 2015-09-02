package com.iluwatar.reactor;

import java.nio.channels.SelectionKey;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDispatcher extends SameThreadDispatcher {

	private ExecutorService exectorService;

	public ThreadPoolDispatcher(int poolSize) {
		this.exectorService = Executors.newFixedThreadPool(poolSize);
	}
	
	@Override
	public void onChannelReadEvent(AbstractNioChannel channel, Object readObject, SelectionKey key) {
		exectorService.execute(new Runnable() {
			
			@Override
			public void run() {
				ThreadPoolDispatcher.super.onChannelReadEvent(channel, readObject, key);
			}
		});
	}

}