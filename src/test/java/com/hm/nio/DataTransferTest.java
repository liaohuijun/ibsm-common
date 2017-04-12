package com.hm.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @author shishun.wang
 * @date 2017年4月12日 上午11:47:47
 * @version 1.0
 * @describe
 */
public class DataTransferTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		RandomAccessFile fromFile = new RandomAccessFile("D:/tmp/insurance.log", "rw");
		FileChannel fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("d:/tmp/11.log", "rw");
		FileChannel toChannel = toFile.getChannel();
		
		long position = 0;
		long count = fromChannel.size();
		System.out.println("from channel size "+count);
		toChannel.transferFrom(fromChannel, position, count);
	}

}
