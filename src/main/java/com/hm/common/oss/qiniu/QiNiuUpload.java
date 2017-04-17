package com.hm.common.oss.qiniu;

import com.google.gson.Gson;
import com.hm.common.exception.ServiceException;
import com.hm.common.util.CommonUtil;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 * @author shishun.wang
 * @date 2017年4月17日 上午11:31:18
 * @version 1.0
 * @describe
 */
public final class QiNiuUpload {

	private QiNiuUpload() {

	}

	/**
	 * @author shishun.wang
	 * @date 2017年4月17日 上午11:34:00
	 * @version 1.0
	 * @describe
	 */
	public static class Sample {

		private Sample() {
		}

		/**
		 * @param bucket
		 *            块
		 * @param fileFullName
		 * @param uploadBytes
		 * @return
		 * @throws Exception
		 */
		public static String upload(String bucket, String fileFullName, byte[] uploadBytes) throws Exception {
			if (CommonUtil.isAnyEmpty(bucket, fileFullName, uploadBytes)) {
				throw ServiceException.warn("七牛云文件上传参数错误:bucket、fileFullName、uploadBytes不能为空 ");
			}
			QiNiuConfig exportConfig = QiNiuConfig.load();
			UploadManager uploadManager = new UploadManager(new Configuration());
			Auth auth = Auth.create(exportConfig.getAccessKey(), exportConfig.getSecretKey());
			String upToken = auth.uploadToken(bucket);

			Response response = uploadManager.put(uploadBytes, fileFullName, upToken);
			// 解析上传成功的结果
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			return putRet.key;
		}

		/**
		 * @param bucket
		 * @param fileFullName
		 * @throws Exception
		 */
		public static void delete(String bucket, String fileFullName) throws Exception {
			if (CommonUtil.isAnyEmpty(bucket, fileFullName)) {
				throw ServiceException.warn("七牛云文件删除文件参数错误:bucket、fileFullName不能为空 ");
			}

			QiNiuConfig exportConfig = QiNiuConfig.load();
			Auth auth = Auth.create(exportConfig.getAccessKey(), exportConfig.getSecretKey());
			BucketManager bucketManager = new BucketManager(auth, new Configuration());
			bucketManager.delete(bucket, fileFullName);
		}

	}
}
