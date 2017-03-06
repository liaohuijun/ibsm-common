package com.hm.common.su;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hm.common.exception.ServiceException;
import com.hm.common.su.bean.ServerResponse;
import com.hm.common.su.bean.ServerResponse.MetaType;

/**
 * @author shishun.wang
 * @date 2016年12月19日 上午10:56:57
 * @version 1.0
 * @describe
 */
public class ServerResponseParse {

	private ServerResponseParse() {
	}

	public static <T> T parse(ServerResponse<T> serverResponse) throws ServiceException {
		MetaType metaType = serverResponse.getMetaType();
		if (metaType.isSuccess()) {
			return serverResponse.getData();
		} else {
			throw ServiceException.warn(metaType.getMessage());
		}
	}

	public static String parseError(Exception e) {
		String msg = e.getMessage().substring(4, e.getMessage().length());
		ServerResponse<String> response = JSON.parseObject(msg, new TypeReference<ServerResponse<String>>() {
		});
		return response.getMetaType().getMessage();
	}
}
