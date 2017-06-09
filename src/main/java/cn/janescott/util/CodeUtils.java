package cn.janescott.util;

import cn.janescott.common.Constants;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * Created by scott on 2017/6/9.
 * 加密解密工具类
 */
public class CodeUtils {
    public static StandardPBEStringEncryptor standardPBEStringEncryptor(){
        StandardPBEStringEncryptor decoder = new StandardPBEStringEncryptor();
        decoder.setAlgorithm(StandardPBEStringEncryptor.DEFAULT_STRING_OUTPUT_TYPE);
        decoder.setPassword(Constants.KEY);
        return decoder;
    }
}
