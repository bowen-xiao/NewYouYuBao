package com.youlian.youyubao.util;

import com.youlian.youyubao.MyApplication;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.security.Key;

import javax.crypto.Cipher;

/**
 * Created by happy on 2016/8/21.
 */
public class RSAUtils {
    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
    public static Key getKeyByFile() throws Exception {
        BufferedInputStream fis = new BufferedInputStream(MyApplication.getInstance().getResources().getAssets().open("key.properties") );
        ObjectInputStream ois = new ObjectInputStream(fis);
        Key keys = (Key) ois.readObject();
        ois.close();
        return keys;
    }
    /**
     * 解密
     *
     * @param srcBytes
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(Key key, byte[] srcBytes) throws Exception {
        if (key != null) {
            // Cipher负责完成加密或解密工作，基于RSA
            Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
            // 根据私钥，对Cipher对象进行初始化
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] resultBytes = cipher.doFinal(srcBytes);
            return resultBytes;
        }
        return null;
    }
}