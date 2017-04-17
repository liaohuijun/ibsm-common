package com.hm.nio.socket.single;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author shishun.wang
 * @date 2017年4月13日 下午2:26:25
 * @version 1.0
 * @describe
 */
public class Client {

	public static void main(String[] args) throws Exception {

		Socket socket = new Socket("localhost", 10000);
		BufferedReader serverCallBack = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String msg = reader.readLine();
			printWriter.println(msg);
			printWriter.flush();

			if ("bye".equals(msg)) {
				break;
			}
			System.out.println("--->" + serverCallBack.readLine());
		}
		socket.close();

	}
}
