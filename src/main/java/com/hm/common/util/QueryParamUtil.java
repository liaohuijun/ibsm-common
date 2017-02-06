package com.hm.common.util;

import com.hm.common.exception.ServiceException;

/**
 * @author shishun.wang
 * @date 下午7:10:35 2017年2月2日
 * @version 1.0
 * @describe
 */
public class QueryParamUtil {

	public static void checkPagging(int pageNumber,int pageSize){
		
		if(CommonUtil.isAnyEmpty(pageNumber,pageSize)){
			throw ServiceException.warn("分页查询参数错误,pageNumber、pageSize不能为空");
		}
		
		if(pageNumber < 1){
			throw ServiceException.warn("分页查询参数错误,pageNumber不能小于1");
		}
		
		if(pageSize > 500){
			throw ServiceException.warn("分页查询参数错误,pageSize最大不能大于500");
		}
	}
}
