## 阿里云 OSS


#### 使用方法
**1.** pom文件添加依赖
```xml
<!-- 私有oss组件 -->
<dependency>
    <groupId>cn.soilove</groupId>
    <artifactId>spring-boot-starter-oss</artifactId>
    <version>1.0.0</version>
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

