package com.hm.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;

/**
 * @author shishun.wang
 * @date 2017年12月3日 上午11:44:26
 * @version 1.0
 * @describe
 */
public final class FreemakUtil extends CommonUtil {

	private FreemakUtil() {

	}

	private static Configuration build(File templateFolder) throws IOException {
		@SuppressWarnings("deprecation")
		Configuration configuration = new Configuration();
		configuration.setDirectoryForTemplateLoading(templateFolder);
		configuration.setDefaultEncoding("UTF-8");
		return configuration;
	}

	public static String process(File templateFolder, String templateName, Map<String, Object> mapper)
			throws Exception {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		Writer out = new OutputStreamWriter(byteStream);
		build(templateFolder).getTemplate(templateName).process(mapper, out);

		return new String(byteStream.toByteArray());
	}

	public static void process(File templateFolder, String templateName, Map<String, Object> mapper, Writer out)
			throws Exception {
		build(templateFolder).getTemplate(templateName).process(mapper, out);
	}
}
