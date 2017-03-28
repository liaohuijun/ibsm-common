package com.hm.common.event.ops;

import java.util.EventObject;
import java.util.Vector;

/**
 * @author shishun.wang
 * @date 2017年2月28日 下午4:28:27
 * @version 1.0
 * @describe
 */
public class OpsActionListener {

	private Vector<OpsListener> listeners = new Vector<OpsListener>();

	public void addListener(OpsTarget target, OpsListener listener) {
		if (null == target) {
			return;
		}
		listeners.add(listener);
		this.handler(new EventObject(target));
	}

	private void removeListener(OpsListener listener) {
		listeners.removeElement(listener);
	}

	@SuppressWarnings("unchecked")
	private void handler(EventObject event) {
		Vector<OpsListener> currentListeners = null;
		synchronized (OpsActionListener.class) {
			currentListeners = (Vector<OpsListener>) listeners.clone();
		}
		currentListeners.iterator().forEachRemaining(listener -> {
			listener.event(event);
			this.removeListener(listener);
		});
	}
}
