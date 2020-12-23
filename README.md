<p align="center">
	<a href="https://github.com/Ln-guolin/easy-oss-starter"><img src="https://soilove.oss-cn-hangzhou.aliyuncs.com/32e/pro-mall/easy-oss-starter.png" width="350px"></a>
</p>
<p align="center">
	<strong>阿里云OSS云存储操作SpringBoot Starter，基于阿里云OSS SDK封装</strong>
</p>
<p align="center">
	<a target="_blank" href="https://github.com/Ln-guolin/easy-oss-starter/blob/master/LICENSE">
		<img src="https://img.shields.io/:license-Apache2.0-blue.svg" />
	</a>
	<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/JDK-8+-green.svg" />
	</a>
	<a target="_blank" href="https://gitter.im/pro-32e/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge">
		<img src="https://badges.gitter.im/pro-32e/community.svg" />
	</a>
	<a href="https://github.com/Ln-guolin/easy-oss-starter">
        <img src="https://img.shields.io/github/repo-size/Ln-guolin/easy-oss-starter"/>
    </a>
	<a href="https://github.com/Ln-guolin/easy-oss-starter">
        <img src="https://img.shields.io/github/issues-raw/Ln-guolin/easy-oss-starter"/>
    </a>
    <a href="https://github.com/Ln-guolin/easy-oss-starter">
        <img src="https://img.shields.io/github/v/tag/Ln-guolin/easy-oss-starter?include_prereleases"/>
    </a>
	<a href="https://github.com/Ln-guolin/easy-oss-starter">
        <img src="https://img.shields.io/github/stars/Ln-guolin/easy-oss-starter?style=social"/>
    </a>
</p>


## 使用方法
**1.** pom文件添加依赖
```xml
<!-- oss组件 -->
<dependency>
    <groupId>cn.soilove</groupId>
    <artifactId>easy-oss-starter</artifactId>
    <version>1.0.2</version>
</dependency>
```

**2.** 在配置文件添加配置
```yaml
# 阿里云oss
aliyun.oss.accessKeyId=Il5AfBvWmZHMxxxx
aliyun.oss.accessKeySecret=W4h9qEMCNth25fsk0VcvGUKwjxxxx
aliyun.oss.bucket=xxxx
aliyun.oss.endpoint=https://oss-cn-beijing.aliyuncs.com
aliyun.oss.prefix=https
```

**3.** 调用示例


```java

@Autowired
private OssService ossService;

// 网络图片上传
String url = ossService.upload4url("test/upload4url.png","http://cdn.juxiang365.cn/ucgoods/a8031da596c84bed90e0205479efe612.png");

// 本地文件上传 
String url = ossService.upload4path("upload4path.png","/Users/chenguolin/Desktop/uc.png");

// byte[]数组上传
File file = new File("/Users/chenguolin/Desktop/uc.png");
InputStream in = new FileInputStream(file);
byte b[]=new byte[(int)file.length()];
in.read(b);
in.close();
String url = ossService.upload4bytes("upload4bytes.png", b);

// 文件流上传
InputStream in = new FileInputStream(new File("/Users/chenguolin/Desktop/uc.png"));
String url = ossService.upload4inputStream("upload4inputStream.png",in);

// 其他类似...

```

