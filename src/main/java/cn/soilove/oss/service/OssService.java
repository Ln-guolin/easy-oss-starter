package cn.soilove.oss.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * aliyun oss 文件服务
 *
 * @author: Chen GuoLin
 * @create: 2020-04-07 18:11
 **/
public interface OssService {

    /**
     * 上传文件 - base64
     * @param objectName
     * @param base64
     */
    String upload4base64(String objectName,String base64) ;

    /**
     * 上传文件 - byte[]
     * @param objectName
     * @param bytes
     */
    String upload4bytes(String objectName,byte[] bytes);

    /**
     * 上传文件 - 本地文件路径
     * @param objectName
     * @param path
     */
    String upload4path(String objectName, String path);

    /**
     * 上传文件 - 本地File
     * @param objectName
     * @param file
     */
    String upload4file(String objectName, File file);

    /**
     * 上传文件 - 文件流
     * @param objectName
     * @param inputStream
     * @return
     */
    String upload4inputStream(String objectName, InputStream inputStream);

    /**
     * 上传文件 - ⽹络文件
     * @param objectName
     * @param url
     * @return
     */
    String upload4url(String objectName,String url);


    /**
     * 文件下载
     * @param objectName
     * @return
     * @throws IOException
     */
    BufferedReader download(String objectName);

    /**
     * 文件下载
     * @param objectName
     * @param path
     */
    void downLoad(String objectName,String path);

    /**
     * 文件是否存在
     * @param objectName
     * @return
     */
    boolean exist(String objectName);

    /**
     * 文件删除
     * @param objectName
     */
    void delete(String objectName);

    /**
     * 获取obj访问url
     * @param objectName
     * @return
     */
    String getFileUrl(String objectName);
}
