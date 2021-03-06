package com.hm.common.su.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.hm.common.util.QueryParamUtil;

/**
 * @author shishun.wang
 * @date 2016年12月15日 上午11:24:05
 * @version 1.0
 * @describe
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PageInfo<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final PageInfo EMPTY = new PageInfo();

	private int totalPage;

	private long totalSize;

	private List<T> content;

	public PageInfo() {
		this(new ArrayList(), 0, 0);
	}

	public PageInfo(List<T> content, int totalPage, long totalSize) {
		this.content = (List<T>) (content == null ? new ArrayList() : content);
		this.totalPage = totalPage < 0 ? 0 : totalPage;
		this.totalSize = totalSize < 0 ? 0 : totalSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public static final <T> PageInfo<T> empty() {
		return EMPTY;
	}

	@Override
	public String toString() {
		return "PageInfo [totalPage=" + totalPage + ", totalSize=" + totalSize + ", content=" + content + "]";
	}

	/**
	 * @author shishun.wang
	 * @date 下午2:33:12 2017年10月18日
	 * @version 1.0
	 * @describe
	 */
	public static class PageParam {

		private int page;

		private int size;

		public PageParam(int pageNumber, int pageSize) {
			QueryParamUtil.checkPagging(pageNumber, pageSize);
			this.page = pageNumber;
			this.size = pageSize;
		}

		public int getPage() {
			return this.page;
		}

		public int getSize() {
			return this.size;
		}
	}

	/**
	 * @author shishun.wang
	 * @date 下午2:32:27 2017年10月18日
	 * @version 1.0
	 * @describe
	 */
	public static class PageInfoParam {

		private int page;

		private int size;

		public PageInfoParam(int pageNumber, int pageSize) {
			QueryParamUtil.checkPagging(pageNumber, pageSize);
			this.page = (pageNumber - 1);
			this.size = pageSize;
		}

		public int getPage() {
			return this.page;
		}

		public int getSize() {
			return this.size;
		}
	}

}
