package cn.soilove.oss.service.impl;

import cn.soilove.oss.config.OssStarterException;
import cn.soilove.oss.properties.OssProperties;
import cn.soilove.oss.service.OssService;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.net.URL;

/**
 * 文件服务
 *
 * @author: Chen GuoLin
 * @create: 2020-04-07 18:12
 **/
@Slf4j(topic = "[starter][oss]")
@Service
public class OssServiceImpl implements OssService {

    @Autowired
    private OssProperties ossProperties;
    @Autowired
    private OSS oss;

    /**
     * 上传文件 - base64
     * @param objectName
     * @param base64
     */
    public String upload4base64(String objectName,String base64) {
        try {
            log.info("正在执行：上传文件 - base64，objectName=" + objectName);
            return upload4bytes(objectName,new BASE64Decoder().decodeBuffer(base64));
        } catch (IOException e) {
            log.error("异常：上传文件 - base64，objectName=" + objectName,e);
            throw new OssStarterException("[starter][oss]文件上传异常:" + getStackTraceAsString(e));
        }

    }

    /**
     * 上传文件 - byte[]
     * @param objectName
     * @param bytes
     */
    public String upload4bytes(String objectName,byte[] bytes) {
        log.info("正在执行：上传文件 - byte[]，objectName=" + objectName);
        PutObjectRequest putObjectRequest = new PutObjectRequest(ossProperties.getBucket(), objectName, new ByteArrayInputStream(bytes));
        oss.putObject(putObjectRequest);

        // 生成URL
        return getFileUrl(objectName);
    }

    /**
     * 上传文件 - 本地文件路径
     * @param objectName
     * @param path
     */
    public String upload4path(String objectName, String path) {
        log.info("正在执行：上传文件 - 本地文件路径，objectName=" + objectName + "，path=" + path);
        File file = new File(path);

        // 生成URL
        return upload4file(objectName,file);
    }

    /**
     * 上传文件 - 本地File
     * @param objectName
     * @param file
     */
    public String upload4file(String objectName, File file) {
        log.info("正在执行：上传文件 - 本地File，objectName=" + objectName);
        PutObjectRequest putObjectRequest = new PutObjectRequest(ossProperties.getBucket(), objectName, file);
        oss.putObject(putObjectRequest);

        // 生成URL
        return getFileUrl(objectName);
    }


    /**
     * 上传文件 - 文件流
     * @param objectName
     * @param inputStream
     * @return
     */
    public String upload4inputStream(String objectName, InputStream inputStream) {
        log.info("正在执行：上传文件 - 文件流，objectName=" + objectName);
        PutObjectRequest putObjectRequest = new PutObjectRequest(ossProperties.getBucket(), objectName, inputStream);
        oss.putObject(putObjectRequest);

        // 生成URL
        return getFileUrl(objectName);
    }

    /**
     * 上传文件 - ⽹络文件
     * @param objectName
     * @param url
     * @return
     */
    public String upload4url(String objectName,String url){
        log.info("正在执行：上传文件 - ⽹络文件，objectName=" + objectName + "，url=" + url);
        // 上传⽹络流
        InputStream inputStream = null;
        try {
            inputStream = new URL(url).openStream();
        } catch (IOException e) {
            log.error("异常：上传文件 - ⽹络文件，objectName=" + objectName + "，url=" + url + objectName,e);
            throw new OssStarterException("[starter][oss]文件上传异常:" + getStackTraceAsString(e));
        }
        oss.putObject(ossProperties.getBucket(), objectName,inputStream);

        // 生成URL
        return getFileUrl(objectName);
    }


    /**
     * 文件下载 - BufferedReader
     * @param objectName
     * @return
     * @throws IOException
     */
    public BufferedReader download(String objectName) {
        log.info("正在执行：文件下载 - BufferedReader，objectName=" + objectName);
        try {
            OSSObject ossObject = oss.getObject(ossProperties.getBucket(),objectName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
            }
            reader.close();
            return reader;
        } catch (IOException e) {
            log.error("异常：文件下载 - BufferedReader，objectName=" + objectName,e);
            throw new OssStarterException("[starter][oss]文件下载异常:" + getStackTraceAsString(e));
        }
    }

    /**
     * 文件下载 - 本地路径
     * @param objectName
     * @param path
     */
    public void downLoad(String objectName,String path) {
        log.info("正在执行：文件下载 - 本地路径，objectName=" + objectName + "，path=" + path);
        File file = new File(path);
        oss.getObject(new GetObjectRequest(ossProperties.getBucket(), objectName), file);
    }

    /**
     * 文件是否存在
     * @param objectName
     * @return
     */
    public boolean exist(String objectName) {
        log.info("正在执行：文件是否存在，objectName=" + objectName);
        return oss.doesObjectExist(ossProperties.getBucket(), objectName);
    }

    /**
     * 文件删除
     * @param objectName
     */
    public void delete(String objectName) {
        log.info("正在执行：文件删除，objectName=" + objectName);
        oss.deleteObject(ossProperties.getBucket(), objectName);
    }

    /**
     * 获取obj访问url
     * @param objectName
     * @return
     */
    public String getFileUrl(String objectName){

        // build file url
        StringBuilder url = new StringBuilder();
        url.append(ossProperties.getEndpoint().replace("https://", "https://" + ossProperties.getBucket() + "."))
                .append("/")
                .append(objectName);

        // 生成URL
        return url.toString();

    }

    /**
     * 获取具体的堆栈信息
     * @param e
     * @return
     */
    private String getStackTraceAsString(Exception e) {
        try {
            // StringWriter将包含堆栈信息
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            // 获取堆栈信息
            e.printStackTrace(printWriter);
            // 转换成String，并返回该String
            StringBuffer error = stringWriter.getBuffer();
            return error.toString();
        } catch (Exception e2) {
            return "[starter][oss]获取堆栈信息异常";
        }
    }
}
