package com.hm.nio.socket.single;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author shishun.wang
 * @date 2017年4月13日 下午2:17:06
 * @version 1.0
 * @describe
 */
@SuppressWarnings("resource")
public class Server {

	public static void main(String[] args) throws Exception {
		Socket socket = new ServerSocket(10000).accept();
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream());

		while (true) {
			String msg = in.readLine();
			System.out.println(msg);
			out.println("Server received " + msg);
			out.flush();
			if (msg.equals("bye")) {
				break;
			}
		}
		socket.close();
	}

}
