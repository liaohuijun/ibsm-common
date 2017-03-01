package com.hm.common.event.ops;

import java.util.EventListener;
import java.util.EventObject;

/**
 * @author shishun.wang
 * @date 2017年3月1日 上午11:44:27
 * @version 1.0
 * @describe
 */
public interface OpsListener extends EventListener {

	public void event(EventObject event);
}
