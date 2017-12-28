package util.test;

import java.util.ArrayList;
import java.util.List;

import com.hm.common.util.CollectionUtil;

/**
 * @author shishun.wang
 * @date 2017年12月18日 上午11:21:08
 * @version 1.0
 * @describe 
 */
public class TestCollection {

	public static void main(String[] args) {
		List<Integer> a = new ArrayList<Integer>();
		{
			a.add(1);
			a.add(3);
		}
		
		List<Integer> b = new ArrayList<Integer>();
		{
			b.add(3);
			b.add(5);
			b.add(3);
			b.add(1);
		}
		
		System.out.println(CollectionUtil.difference(a, b).toString());
		
		System.out.println(CollectionUtil.difference(b, a).toString());
	}
}
