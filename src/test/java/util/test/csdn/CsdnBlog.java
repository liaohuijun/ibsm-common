package util.test.csdn;

import java.io.Serializable;

import org.apache.http.HttpResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.hm.common.network.httpclient.HttpClientFactory;
import com.hm.common.network.httpclient.HttpClientResponseParse;
import com.hm.common.util.StringUtil;
import com.hm.common.util.WebUtil;

/**
 * @author shishun.wang
 * @date 下午12:08:38 2017年10月25日
 * @version 1.0
 * @describe
 */
public class CsdnBlog {

	private static int timeout = 300000;

	public static void main(String[] args) throws Exception {

		loadArticleMulu();
		
	}
	
	private static void loadArticleMulu() throws Exception{
		String jsonpTag = "jQuery20305676851124417437_1508912028399";
		for(int i= 1; i <2;i++){
			HttpResponse response = HttpClientFactory.GET
					.build("http://geek.csdn.net/service/news/get_category_news_list?category_id=bigdata&jsonpcallback="
							+ jsonpTag + "&username=&from="+(i-1)*20+"&size=20&type=category&_=1508912028400")
					.execute();

			String parse = HttpClientResponseParse.parse(response);
			CSDNCallback callback = JSON.parseObject(parse.substring(jsonpTag.length() + 1, parse.length() - 1),
					CSDNCallback.class);
			Document document = Jsoup.parse(callback.getHtml());
			
			Elements elements = document.getElementsByClass("title");
			for (Element element : elements) {
				String uri = element.attr("href");
				if(uri.contains("blog.csdn.net")){
					System.out.println(uri);
					String title = element.html();
					loadArticleDetail(uri);
				}
			}
		}
	}
	
	public static void loadArticleDetail(String uri) throws Exception{
		Document document = Jsoup.connect(uri).data("query", "Java").userAgent("Mozilla")
				.cookie("auth", "token").timeout(timeout).get();
		
		Element element = document.getElementById("article_content");
		System.out.println(StringUtil.dislodgeAllBlank(WebUtil.clearHtmlTags(element.toString())).substring(0,200));
	}

	public static class CSDNCallback implements Serializable {

		private static final long serialVersionUID = 1L;

		private Integer status;

		private String error;

		private String data;

		private Boolean has_more;

		private String html;

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public Boolean getHas_more() {
			return has_more;
		}

		public void setHas_more(Boolean has_more) {
			this.has_more = has_more;
		}

		public String getHtml() {
			return html;
		}

		public void setHtml(String html) {
			this.html = html;
		}

	}
}
