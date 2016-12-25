package util.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shishun.wang
 * @date 2016年12月25日 上午10:35:39
 * @version 1.0
 * @describe
 */
public class TestMap {

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		{
			map.put("34567890", 43567890);
			map.put("jkdrf", "2yuiop;/");
			map.put("aaa", 1234567890);
		}

		System.out.println(map.getOrDefault("aaa", false));
		List<Object> list = new ArrayList<Object>(map.values());
		System.out.println(list.size());
	}
}
