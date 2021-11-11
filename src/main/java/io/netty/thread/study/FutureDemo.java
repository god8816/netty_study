package io.netty.thread.study;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 构建异步通知类 ChannelFuture 
 * */
public class FutureDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();
		
		// 等凉菜 
		Callable ca1 = new Callable(){
 
			@Override
			public String call() throws Exception {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return "凉菜准备完毕";
			}
		};
		FutureTask<String> ft1 = new FutureTask<String>(ca1);
		
		new Thread(ft1).start();
		
		// 等包子 -- 必须要等待返回的结果，所以要调用join方法
		Callable ca2 = new Callable(){
 
				@Override
				public Object call() throws Exception {
					try {
						Thread.sleep(1000*3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return "包子准备完毕";
			}
		};
		
		   
		FutureTask<String> ft2 = new FutureTask<String>(ca2);
		new Thread(ft2).start();
		System.out.println("包子是否完成:"+ft2.isDone());
		System.out.println("凉菜是否完成:"+ft1.isDone());
		System.out.println(ft2.get());
		System.out.println(ft1.get());
		System.out.println("包子是否完成:"+ft2.isDone());
		System.out.println("凉菜是否完成:"+ft1.isDone());
		
		long end = System.currentTimeMillis();
		System.out.println("准备完毕时间："+(end-start));
	}
 
}