package util.test.wycto;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hm.common.network.httpclient.HttpClientFactory;
import com.hm.common.network.httpclient.HttpClientResponseParse;

/**
 * @author shishun.wang
 * @date 上午11:47:03 2017年10月30日
 * @version 1.0
 * @describe 51cto
 */
public class WyCto {

	private static int timeout = 300000;

	public static void main(String[] args) throws Exception {
//type_id=1708 大数据
		//type_id=15 云计算
		//1735 人工智能
		//475 数据库
		
		// loadArticleMulu();
//		String uri = "http://cloud.51cto.com/art/201710/555517.htm";
////		uri = "http://bigdata.51cto.com/art/201710/555466.htm";
//		loadArticleDetail(uri);
		
		int typeIds[] = {1708,15,1735,475};
		for(int typeId:typeIds){
			for(int i=0;i<10;i++){
				loadArticleMulu(i,typeId);
			}
		}
	}

	public static void loadArticleDetail(String uri) throws Exception {
		try{
			Document document = Jsoup.connect(uri).data("query", "Java").userAgent("Mozilla").cookie("auth", "token")
					.timeout(timeout).get();

			Elements element = document.getElementsByClass("zwnr");
			element.get(0).getElementsByTag("h2").remove();
			element.get(0).getElementsByTag("div").remove();
			element.get(0).getElementsByClass("dzdz").remove();
			
			System.out.println(document.select("meta[name=keywords]").get(0).attr("content"));
			System.out.println(document.select("meta[name=description]").get(0).attr("content"));
			
			System.out.println(element.get(0).html());
		}catch(Exception e){
			System.out.println("拉数据出错了");
		}
		
//		System.out.println(element.get(0).toString().replace("点赞 <span>0</span>", "")
//				.replaceAll("mailto:sunsj@51cto.com", "javascrpit:;").replaceAll("【责任编辑：", ""));
	}

	private static void loadArticleMulu(int pageNumber,int typeId) throws Exception {
		String jsonpTag = "jsonp1509334907443";

		HttpResponse response = HttpClientFactory.GET
				.build("http://other.51cto.com/php/get_channel_recommend_art_list.php?callback=" + jsonpTag
						+ "&page="+pageNumber+"&type_id="+typeId+"&type=recommend&page_size=19")
				.execute();
		String parse = HttpClientResponseParse.parse(response);
		List<WyCtoCallback> callbacks = JSON.parseObject(parse.substring(jsonpTag.length() + 2, parse.length() - 4),
				new TypeReference<List<WyCtoCallback>>() {
				});
		for (WyCtoCallback callback : callbacks) {
			loadArticleDetail(callback.getUrl());
			System.out.println("----------->"+callback.getTitle());
		}
	}

	public static class WyCtoCallback implements Serializable {

		private static final long serialVersionUID = 1L;

		private Integer msg;

		private String ID;

		private String title;

		private String picname;

		private String url;

		private String stime;

		private String typeid;

		private String thumb;

		private String has_thumb;

		private String info;

		private String keywords;

		private String typename;

		private String typedomain;

		public Integer getMsg() {
			return msg;
		}

		public void setMsg(Integer msg) {
			this.msg = msg;
		}

		public String getID() {
			return ID;
		}

		public void setID(String iD) {
			ID = iD;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getPicname() {
			return picname;
		}

		public void setPicname(String picname) {
			this.picname = picname;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getStime() {
			return stime;
		}

		public void setStime(String stime) {
			this.stime = stime;
		}

		public String getTypeid() {
			return typeid;
		}

		public void setTypeid(String typeid) {
			this.typeid = typeid;
		}

		public String getThumb() {
			return thumb;
		}

		public void setThumb(String thumb) {
			this.thumb = thumb;
		}

		public String getHas_thumb() {
			return has_thumb;
		}

		public void setHas_thumb(String has_thumb) {
			this.has_thumb = has_thumb;
		}

		public String getInfo() {
			return info;
		}

		public void setInfo(String info) {
			this.info = info;
		}

		public String getKeywords() {
			return keywords;
		}

		public void setKeywords(String keywords) {
			this.keywords = keywords;
		}

		public String getTypename() {
			return typename;
		}

		public void setTypename(String typename) {
			this.typename = typename;
		}

		public String getTypedomain() {
			return typedomain;
		}

		public void setTypedomain(String typedomain) {
			this.typedomain = typedomain;
		}

		@Override
		public String toString() {
			return "WyCtoCallback [msg=" + msg + ", ID=" + ID + ", title=" + title + ", picname=" + picname + ", url="
					+ url + ", stime=" + stime + ", typeid=" + typeid + ", thumb=" + thumb + ", has_thumb=" + has_thumb
					+ ", info=" + info + ", keywords=" + keywords + ", typename=" + typename + ", typedomain="
					+ typedomain + "]";
		}

	}
}
