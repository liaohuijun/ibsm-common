package util.test;

import com.hm.common.queue.FixedLimitQueue;

/**
 * @author shishun.wang
 * @date 上午10:55:31 2017年7月19日
 * @version 1.0
 * @describe
 */
public class TestFixedLimitQueue {

	public static void main(String[] args) {

		FixedLimitQueue<String> limitQueue = new FixedLimitQueue<String>(3);

		for (int i = 0; i < 10; i++) {
			limitQueue.offer(i + "");
		}

		for (String string : limitQueue.getQueue()) {
			System.out.println(string);
		}
	}
}
