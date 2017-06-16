package com.hm.common.oss.qiniu.su;

import com.google.gson.Gson;
import com.hm.common.exception.ServiceException;
import com.hm.common.util.CommonUtil;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 * @author shishun.wang
 * @date 下午3:17:15 2017年6月16日
 * @version 1.0
 * @describe
 */
public final class QiNiuHandlerSupport {

	private String accessKey;

	private String secretKey;

	private String bucket;

	private String prefixUri = "";

	public QiNiuHandlerSupport(String accessKey, String secretKey, String bucket) {
		this.accessKey = accessKey;
		this.secretKey = secretKey;
		this.bucket = bucket;
	}

	public QiNiuHandlerSupport setPrefixUri(String uri) {
		this.prefixUri = uri;
		return this;
	}

	public QiNiuSampleHandlerSupport buildSampleHandler() {
		return new QiNiuSampleHandlerSupport();
	}

	public class QiNiuSampleHandlerSupport {

		public String upload(String fileFullName, byte[] uploadBytes) throws Exception {
			if (CommonUtil.isAnyEmpty(bucket, fileFullName, uploadBytes)) {
				throw ServiceException.warn("七牛云文件上传参数错误:bucket、fileFullName、uploadBytes不能为空 ");
			}
			UploadManager uploadManager = new UploadManager(new Configuration());
			Auth auth = Auth.create(accessKey, secretKey);
			String upToken = auth.uploadToken(bucket);

			Response response = uploadManager.put(uploadBytes, fileFullName, upToken);
			// 解析上传成功的结果
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			return prefixUri + putRet.key;
		}
	}
}
