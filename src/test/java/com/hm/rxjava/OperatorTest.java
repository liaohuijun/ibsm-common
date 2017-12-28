package com.hm.rxjava;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shishun.wang
 * @date 2017年12月8日 上午11:54:44
 * @version 1.0
 * @describe 操作符简化代码
 */
@Slf4j
public class OperatorTest {

	public static void main(String[] args) throws Exception {
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
		Thread.sleep(3000);

		{// range；创建一个发射特定整数序列的Observable，第一个参数为起始值，第二个为发送的个数，如果为0则不发送，负数则抛异常
			Observable.range(10, 5).subscribe(new Consumer<Integer>() {

				@Override
				public void accept(Integer t) throws Exception {
					log.info("range--->{}", t);
				}
			});
		}
		
		{//repeat；创建一个重复发射特定数据的Observable
			Observable.just("nihao").repeat(3).subscribe(new Consumer<String>() {

				@Override
				public void accept(String t) throws Exception {
					log.info("repeat-->{}",t);
				}
			});
		}

		{//defer;直到有观察者订阅时才创建Observable，而且这个Observable是使用Observable工厂方法生成一个新的Observable。 
			
			AtomicInteger number = new AtomicInteger(100);
			Observable<Integer> defer = Observable.defer(new Callable<ObservableSource<Integer>>() {

				@Override
				public ObservableSource<Integer> call() throws Exception {
					return Observable.just(number.get());
				}
			});
			number.addAndGet(1);
			defer.subscribe(new Consumer<Integer>() {

				@Override
				public void accept(Integer t) throws Exception {
					log.info("defer-->{}",t);
				}
			});
		}
		
		log.info("-----------------{}---------------", "华丽的分界线");
		{
			List<String> list=new ArrayList<String>();
			list.add("I");
			list.add("am");
			list.add("a");
			list.add("person");
			
			Observable.fromIterable(list).map(new Function<String,Integer>(){

				@Override
				public Integer apply(String t) throws Exception {
					return t.hashCode();
				}
				
			}).subscribe(new Consumer<Integer>() {

				@Override
				public void accept(Integer t) throws Exception {
					log.info("-->"+t);
				}
			});
		}
	}
}
