package com.hm.test;

import java.util.HashMap;
import java.util.Map;

import com.hm.common.util.BeanToMapUtil;

/**
 * @author shishun.wang
 * @date 2018年3月8日 下午4:32:27
 * @version 1.0
 * @describe 
 */
public class MainApp {

	public static void main(String[] args) {
		
		Map<String,Object> map = new HashMap<>();
		{
			map.put("emPlayRuleId", 16);
			map.put("adResourceId", 25);
			map.put("totalPriceLimitType", "INFINITE");
			map.put("remainingTotalLimitPrice", null);
			map.put("totalLimitType", null);
			map.put("remainingTotalLimitCount", 50);
			map.put("banner", "http://omsm3vi6d.bkt.clouddn.com//gcrcsUploadFile/2018/1/15/145333/005PD4iZly1fmf15nwcedj30k007hwg7.jpg");
			map.put("adName", "限制，固定抵扣40");
		}
		
		AdPlayRuleBaseInfo vo = (AdPlayRuleBaseInfo) BeanToMapUtil.Sample.mapToObject(map, AdPlayRuleBaseInfo.class);
	
		System.out.println(vo.toString());
	}
}
