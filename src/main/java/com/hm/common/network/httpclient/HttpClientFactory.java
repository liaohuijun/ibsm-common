package com.hm.common.network.httpclient;

import java.nio.charset.Charset;
import java.text.MessageFormat;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.alibaba.fastjson.JSON;

/**
 * @author shishun.wang
 * @date 2016年12月18日 下午3:41:16
 * @version 1.0
 * @describe
 */
public enum HttpClientFactory {

	/**
	 * @author shishun.wang
	 * @date 2016年12月18日 下午5:25:59
	 * @version 1.0
	 * @describe GET 请求
	 */
	GET {

		private HttpRequestBase request;

		private HttpEntity httpEntity;

		@Override
		public HttpClientFactory build(String uri, Object... objects) {
			request = new HttpGet(MessageFormat.format(uri, objects));
			return this;
		}

		@Override
		public HttpClientFactory headers(String key, String value) {
			this.request.setHeader(key, value);
			return this;
		}

		@Override
		public HttpClientFactory parameters(Object parameters) {
			this.httpEntity = super.getHttpEntity(parameters);
			return this;
		}

		@Override
		public HttpResponse execute() throws Exception {
			return super.execute(this.request, this.httpEntity);
		}
	},
	/**
	 * @author shishun.wang
	 * @date 2016年12月18日 下午5:26:10
	 * @version 1.0
	 * @describe POST 请求
	 */
	POST {

		private HttpRequestBase request;

		private HttpEntity httpEntity;

		@Override
		public HttpClientFactory build(String uri, Object... objects) {
			request = new HttpPost(MessageFormat.format(uri, objects));
			return this;
		}

		@Override
		public HttpClientFactory headers(String key, String value) {
			this.request.setHeader(key, value);
			return this;
		}

		@Override
		public HttpClientFactory parameters(Object parameters) {
			this.httpEntity = super.getHttpEntity(parameters);
			return this;
		}

		@Override
		public HttpResponse execute() throws Exception {
			return super.execute(this.request, this.httpEntity);
		}

	},
	/**
	 * @author shishun.wang
	 * @date 2016年12月18日 下午5:26:19
	 * @version 1.0
	 * @describe PUT请求
	 */
	PUT {

		private HttpRequestBase request;

		private HttpEntity httpEntity;

		@Override
		public HttpClientFactory build(String uri, Object... objects) {
			request = new HttpPut(MessageFormat.format(uri, objects));
			return this;
		}

		@Override
		public HttpClientFactory headers(String key, String value) {
			this.request.setHeader(key, value);
			return this;
		}

		@Override
		public HttpClientFactory parameters(Object parameters) {
			this.httpEntity = super.getHttpEntity(parameters);
			return this;
		}

		@Override
		public HttpResponse execute() throws Exception {
			return super.execute(this.request, this.httpEntity);
		}
	},
	/**
	 * @author shishun.wang
	 * @date 2016年12月18日 下午5:26:30
	 * @version 1.0
	 * @describe DELETE 请求
	 */
	DELETE {

		private HttpRequestBase request;

		private HttpEntity httpEntity;

		@Override
		public HttpClientFactory build(String uri, Object... objects) {
			request = new HttpDelete(MessageFormat.format(uri, objects));
			return this;
		}

		@Override
		public HttpClientFactory headers(String key, String value) {
			this.request.setHeader(key, value);
			return this;
		}

		@Override
		public HttpClientFactory parameters(Object parameters) {
			this.httpEntity = super.getHttpEntity(parameters);
			return this;
		}

		@Override
		public HttpResponse execute() throws Exception {
			return super.execute(this.request, this.httpEntity);
		}
	},
	/**
	 * @author shishun.wang
	 * @date 2016年12月18日 下午5:27:08
	 * @version 1.0
	 * @describe OPTIONS 请求
	 */
	OPTIONS {

		private HttpRequestBase request;

		private HttpEntity httpEntity;

		@Override
		public HttpClientFactory build(String uri, Object... objects) {
			request = new HttpOptions(MessageFormat.format(uri, objects));
			return this;
		}

		@Override
		public HttpClientFactory headers(String key, String value) {
			this.request.setHeader(key, value);
			return this;
		}

		@Override
		public HttpClientFactory parameters(Object parameters) {
			this.httpEntity = super.getHttpEntity(parameters);
			return this;
		}

		@Override
		public HttpResponse execute() throws Exception {
			return super.execute(this.request, this.httpEntity);
		}
	},
	/**
	 * @author shishun.wang
	 * @date 2016年12月18日 下午5:28:24
	 * @version 1.0
	 * @describe PATCH 请求
	 */
	PATCH {

		private HttpRequestBase request;

		private HttpEntity httpEntity;

		@Override
		public HttpClientFactory build(String uri, Object... objects) {
			request = new HttpPatch(MessageFormat.format(uri, objects));
			return this;
		}

		@Override
		public HttpClientFactory headers(String key, String value) {
			this.request.setHeader(key, value);
			return this;
		}

		@Override
		public HttpClientFactory parameters(Object parameters) {
			this.httpEntity = super.getHttpEntity(parameters);
			return this;
		}

		@Override
		public HttpResponse execute() throws Exception {
			return super.execute(this.request, this.httpEntity);
		}
	},
	/**
	 * @author shishun.wang
	 * @date 2016年12月19日 上午11:18:21
	 * @version 1.0
	 * @describe 文件上传请求
	 */
	FILE {

		private HttpRequestBase request;

		private HttpEntity httpEntity;

		@Override
		public HttpClientFactory build(String uri, Object... objects) {
			request = new HttpPost(MessageFormat.format(uri, objects));
			return this;
		}

		@Override
		public HttpClientFactory headers(String key, String value) {
			this.request.setHeader(key, value);
			return this;
		}

		@Override
		public HttpClientFactory parameters(Object parameters) {
			this.httpEntity = super.getHttpEntity(parameters);
			return this;
		}

		@Override
		public HttpResponse execute() throws Exception {
			return super.execute(this.request, this.httpEntity);
		}
	};

	public abstract HttpClientFactory build(String uri, Object... objects);

	public abstract HttpClientFactory headers(String key, String value);

	public abstract HttpClientFactory parameters(Object parameters);

	public abstract HttpResponse execute() throws Exception;

	private HttpEntity getHttpEntity(Object parameters) {
		StringEntity entity = new StringEntity(JSON.toJSONString(parameters),
				Charset.forName(HttpClientStatus.CHARACTER_ENCODING));
		entity.setContentEncoding(HttpClientStatus.CHARACTER_ENCODING);
		entity.setContentType(HttpClientStatus.JSON_CONTENT_TYPE);

		return entity;
	}

	private HttpResponse execute(HttpRequestBase request, HttpEntity httpEntity) throws Exception {
		if (HttpEntityEnclosingRequestBase.class.isAssignableFrom(request.getClass())) {
			((HttpEntityEnclosingRequestBase) request).setEntity(httpEntity);
		}
		return HttpClientBuilder.create().build().execute(request);
	}
}
