package com.hm.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shishun.wang
 * @date 2017年12月8日 上午11:21:15
 * @version 1.0
 * @describe
 */
@Slf4j
public class Test01 {

	public static void main(String[] args) {
		Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {

			@Override
			public void subscribe(ObservableEmitter<String> e) throws Exception {
				log.info("sub-01");
				e.onNext("事件一");
				log.info("sub-02");
				e.onNext("事件二");
				log.info("sub-03");
				e.onNext("事件三");
				log.info("sub-onClomplate");
				e.onComplete();
				log.info("sub-04");
				e.onNext("事件四");
			}
		});

		{// subscribe
			// 执行onComplete，接收方将不再接收处理，sub依旧会发送数据，①当ObservableEmitter发送了onComplete()，之后发送的事件，Observer不再接收
			// ②ObservableEmitter可以不主动发送onError事件，程序运行发生异常时，Observer也会接收到onError事件，只要Observer接收到onError事件，就不会再接收其他事件，但ObservableEmitter不能发送多个onError事件，当发送第二个是，程序会崩溃。
			observable.subscribe(new Observer<String>() {

				@Override
				public void onComplete() {
					log.info("server->onComplete");
				}

				@Override
				public void onError(Throwable arg0) {
					log.info("server->onError");
				}

				@Override
				public void onNext(String s) {
					log.info("server->onNext->{}", s);

				}

				@Override
				public void onSubscribe(Disposable disposable) {
					log.info("server->onSubscribe");
				}

			});
		}
		log.info("-----------------{}---------------", "华丽的分界线");

		{// Disposable：用完即可丢弃的。简单理解就是，当Disposable执行dispose()方法后，ObservableEmitter依然发送数据，但是Observer不再处理数据。
			observable.subscribe(new Observer<String>() {

				private Disposable disposable;

				@Override
				public void onComplete() {
					log.info("server->onComplete");
				}

				@Override
				public void onError(Throwable e) {
					log.info("server->onError",e);
				}

				@Override
				public void onNext(String s) {
					log.info("server->onNext->{}", s);
					if (s.equals("事件一")) {
						log.info("执行disposable");
						disposable.dispose();
						log.info("disposable执行结果为:{}", disposable.isDisposed());
					}
				}

				@Override
				public void onSubscribe(Disposable disposable) {
					log.info("server->onSubscribe");
					this.disposable = disposable;
				}
			});
		}
		log.info("-----------------{}---------------", "华丽的分界线");
		{//subscribe,携带参数情况。其中，不带参数的subscribe()，就是不处理事件，实际使用的较少 带有一个Consumer参数的subscribe()，表示只关心onNext事件，这里使用了链式结构：
			observable.subscribe(new Consumer<String>() {

				@Override
				public void accept(String s) throws Exception {
					log.info(s);
				}
			});
		}
	}
}
