package util.test;

import java.util.Arrays;

import org.junit.Test;

import com.hm.common.util.ArraysUtil;

/**
 * @author shishun.wang
 * @date 2016年12月20日 下午3:52:10
 * @version 1.0
 * @describe
 */
public class ArrayUtilTest {

	private Object[] sources = new Object[] { "aa", 123,null, "bb", "cc",null, 123, "bb" };

	private Object target = "dd";

	@Test
	public void distinct() {
		loop(ArraysUtil.distinct(sources));
		System.out.println("--------------------");
		Object[] temp = Arrays.copyOf(sources, sources.length + 1);
		loop(ArraysUtil.distinct(temp));
	}

	@Test
	public void join() {
		loop(ArraysUtil.join(sources,target));
		System.out.println("------------------");
		loop(ArraysUtil.join(null,target));
	}

	@Test
	public void remove() {
		loop(ArraysUtil.remove(sources,"bb"));
		System.out.println("------------------");
		loop(ArraysUtil.remove(sources,null));
	}

	private void loop(Object[] objects) {
		for (Object obj : objects) {
			System.out.println("------" + obj);
		}
	}
}
