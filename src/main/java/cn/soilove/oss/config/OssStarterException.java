package cn.soilove.oss.config;

import lombok.Data;

/**
 * oss组件异常
 *
 * @author: Chen GuoLin
 * @create: 2020-04-11 12:41
 **/
@Data
public class OssStarterException extends RuntimeException {

    public OssStarterException() {
        super();
    }

    public OssStarterException(String msg) {
        super(msg);
    }

}
