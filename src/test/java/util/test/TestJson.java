package util.test;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author shishun.wang
 * @date 2016年12月22日 下午4:10:15
 * @version 1.0
 * @describe
 */
public class TestJson {

	public static void main(String[] args) {
		try {
			JsonModel jsonModel = new JsonModel();
			{
				jsonModel.setId("234567890");
				jsonModel.setName("张三");
				jsonModel.setParentName("李四");
			}
			String json = JSON.toJSONString(jsonModel);
			System.out.println(json);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static class JsonModel implements Serializable {

		private static final long serialVersionUID = -8047543092057411804L;

		private String id;

		private String name;

		@JSONField(name="parent_name")
		private String parentName;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getParentName() {
			return parentName;
		}

		public void setParentName(String parentName) {
			this.parentName = parentName;
		}

	}
}
