package jxab;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author shishun.wang
 * @date 2017年3月23日 下午3:37:11
 * @version 1.0
 * @describe
 */
@XmlRootElement
public class JxabModel implements Serializable {

	private static final long serialVersionUID = -6976299838706239944L;

	private String id;

	private String name;

	private int age;

	public String getId() {
		return id;
	}

	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
