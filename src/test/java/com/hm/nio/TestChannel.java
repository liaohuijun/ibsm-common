package com.hm.nio;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shishun.wang
 * @date 2017年4月5日 上午11:16:19
 * @version 1.0
 * @describe 
 */
public class TestChannel {

	public static void main(String[] args) throws Exception {
		
//		RandomAccessFile channel = new RandomAccessFile(new File("D:/tmp/insurance.log"), "rw");
//		FileChannel fileChannel = channel.getChannel();
//		ByteBuffer buffer = ByteBuffer.allocate(1024);
//		int read = fileChannel.read(buffer);
//		while(-1 != read){
//			buffer.flip();
//			while(buffer.hasRemaining()){
//				System.out.println(buffer.get());
//			}
//			buffer.clear();
//			read = fileChannel.read(buffer);
//		}
//		channel.close();
		
		long start = System.currentTimeMillis();//1099519、146849
		AtomicInteger index = new AtomicInteger(0);
		Files.readAllLines(Paths.get("D:/tmp/insurance.log")).parallelStream().forEach(line ->{
//			index.incrementAndGet();
			System.out.println(Thread.currentThread().getName());
			System.out.println(line);
		});
		System.out.println(System.currentTimeMillis() - start);
			
	}
}
