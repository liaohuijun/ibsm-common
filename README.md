#常用工具类合集:
1. 统一web后端处理

```
package com.hm.car.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hm.common.ServerResponse;

/**
 * @author shishun.wang
 * @date 2016年12月15日 下午12:00:07
 * @version 1.0
 * @describe
 */
public class ControllerResult<T> {

	public static <T> ResponseEntity<ServerResponse<T>> success(T result) {
		return new ResponseEntity<ServerResponse<T>>(new ServerResponse<T>().success(result), HttpStatus.OK);
	}

	public static <T> ResponseEntity<ServerResponse<T>> failed(String message) {
		return new ResponseEntity<ServerResponse<T>>(new ServerResponse<T>().failure(message), HttpStatus.BAD_REQUEST);
	}

	public static <T> ResponseEntity<ServerResponse<T>> failed(Exception e) {
		return new ResponseEntity<ServerResponse<T>>(new ServerResponse<T>().failure(e.getMessage()),
				HttpStatus.BAD_REQUEST);
	}
}

```
2. java模型数据快速拷贝

```
package com.hm.car.util;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import com.hm.common.util.CommonUtil;

/**
 * @author shishun.wang
 * @date 2016年12月14日 上午10:11:15
 * @version 1.0
 * @describe
 */
public class BeanUtil {

	private static final String MODEL_ID_FIELD = "id";
	
	public static final void copyProperties(Object source, Object target, String... ignoreProperties) {
		if (com.hm.common.util.CommonUtil.isAnyEmpty(source, target)) {
			return;
		}
		BeanUtils.copyProperties(source, target, ignoreProperties);
		// Trying copy id field
		if (com.hm.common.util.CommonUtil.isNotEmpty(ignoreProperties) && Arrays.asList(ignoreProperties).contains(MODEL_ID_FIELD)) {
			return;
		}
		Field targetFiled = ReflectionUtils.findField(target.getClass(), MODEL_ID_FIELD, String.class);
		if (targetFiled == null) {
			return;
		}
		Field sourceFiled = ReflectionUtils.findField(source.getClass(), MODEL_ID_FIELD);
		if (sourceFiled == null) {
			return;
		}
		try {
			sourceFiled.setAccessible(true);
			Object sourceId = sourceFiled.get(source);
			if (sourceId == null) {
				return;
			}
			targetFiled.setAccessible(true);
			if (sourceId instanceof String) {
				ReflectionUtils.setField(targetFiled, target, sourceId);
			} else {
				ReflectionUtils.setField(targetFiled, target, sourceId.toString());
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			LoggerFactory.getLogger(CommonUtil.class).error("Failed to reflect set id properties", e);
		}

	}
}

```

3、maven项目配置。
```
<?xml version="1.0" encoding="UTF-8"?>

<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
  
  <pluginGroups>
  </pluginGroups>
  <proxies></proxies>
  <servers>
    <server>  
      <id>nexus-releases</id>  
      <username>deployment</username>  
      <password>1234567</password>  
    </server>  
    <server>  
      <id>nexus-snapshots</id>  
      <username>deployment</username>  
      <password>1234567</password>  
    </server> 
	
	<server>  
      <id>hm-snapshots</id>  
      <username>admin</username>  
      <password>1234567</password>  
    </server>
  </servers>

  <mirrors>
      <mirror>
        <id>nexus-snapshots</id>
        <mirrorOf>nexus-snapshots</mirrorOf>
        <url>http://192.168.3.100:8888/nexus/content/groups/public-snapshots</url>
      </mirror>

      <mirror>
        <id>nexus-releases</id>
        <mirrorOf>nexus-releases</mirrorOf>
        <url>http://192.168.3.100:8888/nexus/content/groups/public</url>
      </mirror>
	  
	  <mirror>
		<id>hm-snapshots</id>
		<mirrorOf>hm-snapshots</mirrorOf>
		<url>http://120.92.147.60:8081/nexus/content/repositories/hm-snapshots</url>
	  </mirror>
	  
	  <mirror>
		<id>thirdparty</id>
		<mirrorOf>thirdparty</mirrorOf>
		<url>http://120.92.147.60:8081/nexus/content/repositories/thirdparty</url>
	  </mirror>

      <mirror>
          <id>central-maven</id>
	<mirrorOf>*</mirrorOf>
          <url>http://central.maven.org/maven2/</url>
      </mirror>
<mirror>
<id>central-repo1</id>
<mirrorOf>*</mirrorOf>
<url>http://repo1.maven.org/maven2/</url>
</mirror>

  </mirrors>

  
  <profiles>
  <!---->
    <profile>
      <id>nexus</id>
      <activation><activeByDefault>true</activeByDefault></activation>
      <repositories>  

		<repository>
			<id>hm-snapshots</id>
			<name>hm-snapshots</name>
			<url>http://120.92.147.60:8081/nexus/content/repositories/hm-snapshots</url>
			<snapshots><enabled>true</enabled></snapshots>
			<releases><enabled>true</enabled></releases>
		 </repository>

		<repository>
			<id>thirdparty</id>
			<name>thirdparty</name>
			<url>http://120.92.147.60:8081/nexus/content/repositories/thirdparty</url>
			<snapshots><enabled>true</enabled></snapshots>
			<releases><enabled>true</enabled></releases>
		 </repository>     
	  
        <repository>
          <id>nexus-snapshots</id>
          <name>nexus snapshots</name>
          <snapshots><enabled>true</enabled></snapshots>
          <releases><enabled>false</enabled></releases>
          <url>http://192.168.3.100:8888/nexus/content/groups/public-snapshots</url>
        </repository>

        <repository>
           <id>nexus-releases</id>
           <name>nexus releases</name>
           <snapshots><enabled>false</enabled></snapshots>
           <releases><enabled>true</enabled></releases>
           <url>http://192.168.3.100:8888/nexus/content/groups/public</url>
        </repository>

        <repository>  
          <id>maven</id>  
          <name>maven</name>  
          <url>http://repo1.maven.org/maven2/</url>  
        </repository> 
	
	<repository>
          <id>central-maven</id>
          <url>http://central.maven.org/maven2/</url>
        </repository>
        
        <repository>
          <id>apache.snapshots</id>
          <url>http://people.apache.org/maven-snapshot-repository/</url>
        </repository>

        <repository>  
          <id>nexus-world-repo</id>
          <name>world-repo</name>  
          <url>http://search.maven.org/</url>  
        </repository> 
          
        <repository>  
          <id>nexus-osc</id>
          <name>Nexus osc</name>  
          <url>http://maven.oschina.net/content/groups/public/</url>  
        </repository>  
        
        <repository>     
          <id>mirrors.ibiblio.org</id>     
          <name>mirrors.ibiblio.org</name>     
          <url>http://mirrors.ibiblio.org/maven2/</url>  
        </repository>
          
        <repository>  
          <id>nexus-osc-thirdparty</id> 
          <name>Nexus osc thirdparty</name>  
          <url>http://maven.oschina.net/content/repositories/thirdparty/</url>  
        </repository>
		
		<repository>  
          <id>thirdparty</id> 
          <name>thirdparty</name>  
          <url>http://120.92.147.60:8081/nexus/content/repositories/thirdparty/</url>  
        </repository>
      </repositories>
  	  <pluginRepositories>
        <pluginRepository>
          <id>nexus-releases</id>
          <name>nexus releases</name>
          <url>http://192.168.3.100:8888/nexus/content/groups/public</url>
          <snapshots><enabled>false</enabled></snapshots>
          <releases><enabled>true</enabled></releases>
        </pluginRepository>
        <pluginRepository>
          <id>nexus-snapshots</id>
          <name>nexus snapshots</name>
          <url>http://192.168.3.100:8888/nexus/content/groups/public-snapshots</url>
          <snapshots><enabled>true</enabled></snapshots>
          <releases><enabled>false</enabled></releases>
        </pluginRepository>
		
		<pluginRepository>
			<id>hm-snapshots</id>
			<name>hm-snapshots</name>
			<url>http://120.92.147.60:8081/nexus/content/repositories/hm-snapshots</url>
			<snapshots><enabled>true</enabled></snapshots>
			<releases><enabled>true</enabled></releases>
        </pluginRepository>

		<pluginRepository>
			<id>thirdparty</id>
			<name>thirdparty</name>
			<url>http://120.92.147.60:8081/nexus/content/repositories/thirdparty</url>
			<snapshots><enabled>true</enabled></snapshots>
			<releases><enabled>true</enabled></releases>
        </pluginRepository>
		
  	  </pluginRepositories>
    </profile>
	
  </profiles>
<!---->

</settings>

```