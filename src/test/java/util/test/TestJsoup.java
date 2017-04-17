package util.test;

import java.util.concurrent.atomic.AtomicInteger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author shishun.wang
 * @date 2017年4月17日 下午2:51:52
 * @version 1.0
 * @describe
 */
public class TestJsoup {

	public static void main(String[] args) throws Exception {
		String uri = "http://www.caoegg.cn/";
		Document doc = Jsoup.connect(uri).get();
		Elements elements = doc.getElementsByClass("infobox");
		Document containerDoc = Jsoup.parse(elements.toString());
		Elements clearfixs = containerDoc.getElementsByClass("c");
		AtomicInteger index = new AtomicInteger(10);
		for (Element element : clearfixs) {
//			System.out.println(element.toString());
			int num = index.getAndIncrement();
			String content = element.select("a span").html().replaceAll("What a fucking day!", "");
			String sql = "INSERT INTO `tbl_archive` VALUES ("+num+", 0, '"+content+"', 1, '2017-4-11 00:22:17', '', 0);";
			sql = "INSERT INTO `tbl_archive` VALUES ("+index.getAndIncrement()+", 0, '"+content+"', 1, '2017-4-11 00:31:32', '"+content+"', NULL, 0, 3, '', '', '2017-4-11 00:31:32', NULL, NULL, NULL, 0, 0, 0, 0, '"+content+"', 1);";
//			System.out.println(sql);
			System.out.println("INSERT INTO `tbl_article` VALUES ("+num+", '2017-4-11 00:22:17', 5, "+num+", 1);");
//			System.out.println(element.select("a span").html().replaceAll("What a fucking day!", ""));
//			System.out.println("---------------");
		}
	}
}
