package util.test;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.security.cert.X509Certificate;

import org.apache.http.HttpResponse;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import com.hm.common.network.httpclient.HttpClientFactory;
import com.hm.common.network.httpclient.HttpClientStatus;

public class MyTest {

	public static void main(String[] args) throws Exception {
		HttpResponse response = HttpClientFactory.GET
				.build("http://jingyan.baidu.com/article/e52e3615a2b18f40c60c51d1.html").execute();
		System.out.println(response);
		System.out.println(EntityUtils.toString(response.getEntity(), HttpClientStatus.CHARACTER_ENCODING));
	}

	// public static CloseableHttpClient createSSLClientDefault() {
	// try {
	// SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null,
	// new TrustStrategy() {
	// // 信任所有
	// public boolean isTrusted(X509Certificate[] chain, String authType) throws
	// CertificateException {
	// return true;
	// }
	// }).build();
	// SSLConnectionSocketFactory sslsf = new
	// SSLConnectionSocketFactory(sslContext);
	// return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	// } catch (KeyManagementException e) {
	// e.printStackTrace();
	// } catch (NoSuchAlgorithmException e) {
	// e.printStackTrace();
	// } catch (KeyStoreException e) {
	// e.printStackTrace();
	// }
	// return HttpClients.createDefault();
	// }
}
