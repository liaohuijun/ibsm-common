package com.hm.common.network.httpclient;

/**
 * @author shishun.wang
 * @date 2016年12月19日 上午9:59:29
 * @version 1.0
 * @describe
 */
public interface HttpClientStatus {
	
	public String CHARACTER_ENCODING = "UTF-8";
	
	public String JSON_CONTENT_TYPE = "application/json";

	public interface  ResponseCode{
		
		public int OK = 200;
		
		public int SERVER_ERROR = 500;
		
		public int BAD_REQUEST = 400;
		
	}
}
