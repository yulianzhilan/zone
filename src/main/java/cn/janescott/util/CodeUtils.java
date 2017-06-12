package cn.janescott.util;

import cn.janescott.common.Constants;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

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

    public static StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor coder = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(Constants.KEY);
        config.setAlgorithm(Constants.ALGORITHM);
        config.setPoolSize(1);
        coder.setConfig(config);
        return coder;
    }

//    public static void main(String[] args) {
//        System.out.println(stringEncryptor().encrypt("Sd131953"));
////        System.out.println(stringEncryptor().decrypt("A+wvQrOauydBq9Bb5T1a4SCdyxEtMularUjDU23eKtjFOtLr0YYsEVi9MOveeKmDyQjI5soea4uz8cRB+QL/6ZFIJoWYTbZDLZIHcSP6RwDLBIp+m6Vanw=="));
//    }
}
