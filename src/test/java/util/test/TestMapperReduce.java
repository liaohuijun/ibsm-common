package util.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.hm.common.su.BaseMapperReduce;

/**
 * @author shishun.wang
 * @date 2017年5月16日 下午4:51:37
 * @version 1.0
 * @describe
 */
public class TestMapperReduce {

	public static void main(String[] args) throws Exception {

		BaseMapperReduce<T> baseMapperReduce = new BaseMapperReduce<T>() {

			@Override
			public Object execute(List<T> entitys) {

				try {
					System.out.println(Thread.currentThread().getName() + "===" + entitys.toString());
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		};

		for (int i = 0; i < 11; i++) {
			List<T> entitys = new ArrayList<T>();
			baseMapperReduce.execute(entitys);
		}
		System.out.println(123);
	}
}
