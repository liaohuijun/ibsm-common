package com.hm.rxjava;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shishun.wang
 * @date 2017年12月8日 上午11:54:44
 * @version 1.0
 * @describe 操作符简化代码
 */
@Slf4j
public class OperatorTest {

	public static void main(String[] args) {
		{// 1、just;创建一个Observable并自动为你调用onNext( )发射数据
			Observable.just("事件1", "事件2", "事件3", "事件4", "事件5").subscribe(new Consumer<String>() {

				@Override
				public void accept(String s) throws Exception {
					log.info(s);
				}
			});
		}

		{// 2、just-tmp
			Observable.just("事件1", "事件2", "事件3", "事件4", "事件5").subscribe(s -> log.info("jdk8->{}", s));
		}

		log.info("-----------------{}---------------", "华丽的分界线");
		
		{// interval;创建一个按固定时间间隔发射整数序列的Observable，可以用于定时操作
			Observable.interval(1, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {

				@Override
				public void accept(Long t) throws Exception {
					log.info("每隔一秒执行一次:{}", t);
				}
			});
		}
	}
}
