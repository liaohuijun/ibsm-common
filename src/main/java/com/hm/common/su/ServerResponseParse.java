package com.hm.common.su;

import com.hm.common.ServerResponse;
import com.hm.common.ServerResponse.MetaType;

/**
 * @author shishun.wang
 * @date 2016年12月19日 上午10:56:57
 * @version 1.0
 * @describe
 */
public class ServerResponseParse {

	public static <T> T parse(ServerResponse<T> serverResponse) throws Exception {
		MetaType metaType = serverResponse.getMetaType();
		if (metaType.isSuccess()) {
			return serverResponse.getData();
		} else {
			throw new Exception(metaType.getMessage());
		}
	}
}
