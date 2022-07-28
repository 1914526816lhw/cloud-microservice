package com.atguigu.util;

import cn.hutool.core.codec.Base64;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author song
 * @description: AESUtil
 * @date 2021/12/29
 */
@Slf4j
public class AESUtil {

    public static final String cKey = "bst2019%$#*&1231";

    public final static String KEY = "sjx@20171226andr";

    public final static String PROCESS_KEY = "pro@20180927andr";

    public static String Encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        } else if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        } else {
            byte[] raw = sKey.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(1, skeySpec);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes(StandardCharsets.UTF_8));
            return encrypted == null ? null : new String(encrypted, StandardCharsets.UTF_8);
        }
    }

    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            } else if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            } else {
                byte[] raw = sKey.getBytes(StandardCharsets.UTF_8);
                SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(2, skeySpec);
                byte[] encrypted1 = (new Base64()).decode(sSrc);

                try {
                    byte[] original = cipher.doFinal(encrypted1);
                    String originalString = new String(original, StandardCharsets.UTF_8);
                    return originalString;
                } catch (Exception var8) {
                    System.out.println(var8.toString());
                    return null;
                }
            }
        } catch (Exception var9) {
            System.out.println(var9.toString());
            return null;
        }
    }

    public static String encrypt4AES(String source, String key) {
        try {
//            key = MD5Util.encode(key);
            IvParameterSpec zeroIv = new IvParameterSpec(key.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec key1 = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, key1, zeroIv);
            byte[] encryptedData = cipher.doFinal(source.getBytes(StandardCharsets.UTF_8));
            return Base64.encode(encryptedData);
        } catch (NoSuchAlgorithmException e) {
            log.error("", e);
        } catch (NoSuchPaddingException e) {
            log.error("", e);
        } catch (InvalidKeyException e) {
            log.error("", e);
        } catch (IllegalBlockSizeException e) {
            log.error("", e);
        } catch (BadPaddingException e) {
            log.error("", e);
        } catch (NullPointerException e) {
            log.error("", e);
        } catch (Exception localException) {
            log.error("", localException);
        }
        return null;
    }

    public static String decrypt4AES(String content, String key) {
        try {
//            key = MD5Util.encode(key);
            byte[] decryptFrom = Base64.decode(content);
            IvParameterSpec zeroIv = new IvParameterSpec(key.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec key1 = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, key1, zeroIv);
            byte[] decryptedData = cipher.doFinal(decryptFrom);
            return new String(decryptedData);
        } catch (NoSuchAlgorithmException e) {
            log.error("", e);
        } catch (NoSuchPaddingException e) {
            log.error("", e);
        } catch (InvalidKeyException e) {
            log.error("", e);
        } catch (IllegalBlockSizeException e) {
            log.error("", e);
        } catch (BadPaddingException e) {
            log.error("", e);
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }


//    public static void main(String[] args) throws Exception {
//        String cKey = "jsbst2019asdjasd";
//        String cSrc = "{\r\n   code: \"0000\",\r\n   custId: \"20190619164810847315\",\r\n   custName: \"特殊的商户名称\",\r\n   feePhone: \"17717285091\",\r\n   isPwd: \"1\",\r\n   msg: \"success\",\r\n   powerList: [],\r\n   repeat: \"2\",\r\n   starflag: \"0\",\r\n   tokenKey:\r\n     \"nGncSgh+naJnjXmBis8umVXeKX+RcbGuSYdIL5XCKw7PcDd+RNtZGomppNDQ NQAfD88U50ong99GNJ+rl3Hr5Q==\",\r\n   type: \"LD,\",\r\n   updatePwdTime: \"2\",\r\n   userStyle: 2,\r\n   userType: \"new\"\r\n };";
//        String enString = Encrypt(cSrc, cKey);
//        System.out.println("加密后的字串是：" + enString);
//        String value = "qARDfh4gEjMUKR6VoeP6R2JQ0hhp2FtYbiLEUcNvSIJzthoJ6qLKWnPrX01kym3kEWQiw1Rgl6uwfMmdafLdqO+PmHscmTXTbIdvjTFpkwuzOKLjdPh5QXjZM6vRYLrAQBLFP0N5b5PNf9OTd7mqa+qQf4NaiWEok73sdaplEgusSfZe9X5eICNwk7A0k+LpgauOjGK91OVMbh7rN4pBsc4+TCgjgGBWtepNuNj86PsRYZ0zy6wZUzW4Vv193KIzPXl7GwkhtNSWEDv0MxoJ0l3F/0FHwMYM6p2+erKtphyUGtj+hninOQ3Iiik1nTw6O0qfZWA4J6hS3vk9zcLnF238OlOdBVhUcg0RwpJss8RqI+fJOyDXesBN7bCT2FrjOAjPsNEhd6wT93NkR07ZVeBItIg8MgApxU9xE4Jh6xYjsVbomRBfy4US6rbAKYwL9AV82TQgyMNZNythicL4KQ==";
//        String DeString = Decrypt(value, cKey);
//        System.out.println("解密后的字串是：" + DeString);
//    }


    public static void main(String[] args) throws Exception {
        String info = encrypt4AES("15759216738", KEY);
        System.out.println("info = " + info);
        String info1 = decrypt4AES(info, KEY);
        System.out.println("info1 = " + info1);
    }
}
