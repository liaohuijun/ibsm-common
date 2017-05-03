package com.hm.event;

import java.util.EventObject;
import java.util.UUID;

import com.hm.common.event.ops.OpsActionListener;
import com.hm.common.event.ops.OpsListener;
import com.hm.common.event.ops.OpsTarget;

/**
 * @author shishun.wang
 * @date 2017年3月1日 上午11:48:51
 * @version 1.0
 * @describe
 */
public class OpsTest {

	public static void main(String[] args) {
		OpsActionListener opsActionListener = new OpsActionListener();
		while (true) {
			new Thread() {
				public void run() {
					try {
						for (int j = 0; j < 30; j++) {
							OpsTarget target = new OpsTarget();
							{
								target.setName(UUID.randomUUID().toString());
								target.setNote("test");
								target.setExt("aa");
							}
							opsActionListener.addListener(target, new OpsListener() {

								@Override
								public void event(EventObject event) {
									try {
										System.out.println(event.getSource().toString());
										Thread.sleep(500);
									} catch (InterruptedException e) {
										logger.error(e.getMessage(), e);
									}
								}
							});
						}
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				};
			}.start();
		}
	}
}
