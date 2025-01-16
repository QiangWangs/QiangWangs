package com.example.springbootthreaddemo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RSAEncrypt {


    public static void main(String[] args) throws Exception {

        String point = "xxx";
//        Map<String, String> keyMap = genKeyPair(point);
        //生成公钥和私钥
        Map<String, String> keyMap = new HashMap<>();//genKeyPair();
        keyMap.put("publicKey", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDOUDwJVY1JMY4oSHM1+VKeYZ5T2LjQ4wvENnt0TlRoOYDrUen4Nm3GbVKiGTot76gu7xYL1X9PQvDnYLpUVu0mA2oLrXWZj2ByTW83Ehdc5Y9aLXtNzmm4e6PXtuEtXk2sqUZz+XtBBAUMAne4J9G9DAZVPLFxUUJyzVb9cnK6NQIDAQAB");
        keyMap.put("privateKey", "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAM5QPAlVjUkxjihIczX5Up5hnlPYuNDjC8Q2e3ROVGg5gOtR6fg2bcZtUqIZOi3vqC7vFgvVf09C8OdgulRW7SYDagutdZmPYHJNbzcSF1zlj1ote03Oabh7o9e24S1eTaypRnP5e0EEBQwCd7gn0b0MBlU8sXFRQnLNVv1ycro1AgMBAAECgYAKkJlCcRsXEG6TKYKc1POiIKWW7ZYpPDcyCQgxYIF6BNfRNRSiHUdpzddZbalJCOi33o5mdLxcNrVXY+CmyPzDyeyNWWX8UcL2Wud8vRlWU7kQ+YcCVyS/nqRLBpHb0QgW7bqzb7fRpnmqhfj+A9hzRaoKxsZ8EWQfvN5UcdmQgQJBAPyTXzHcicK6gsgaXVo8awXsKxT6/bVAq7+FO/F4ckflS3oyABFNnqVRTC3nuQsU6nq3fu1kDwa03NcAa5zZaykCQQDRHEw4N0pnGKdecKTjBlD95B9WI0KCMWHpSOTIZJUIEvKXANX5BFaGHY01BNxDmwVcuecHgG2XH/WyIzVeJEQtAkEA8WM/NX4aQvrRhsB7u4PGnPBq9DA0TQeznOSOt2ZvgfrIOc6TdfYCyuh5r92oYcjpl8LLEcHxAm3UKb8DGfJIkQJAAjyYQB2vSQ0FdUglK1x870pKX4R/CJ94maMy90XEJlL1j1Ht9/zo5ARa509G/94fn49JflYMVgp8eUxRHNGsfQJBAKm39ZUaFyuDSpRINHZNHfldasmy9hLyXdTb3sLFj/bPaq0MyORAZPqq6XCu+nnIhVKVyADbXb+8T5kn70lzIbg=");
        String publicKey = keyMap.get("publicKey");
        String privateKey = keyMap.get("privateKey");
        System.out.println(publicKey);
        System.out.println(privateKey);
//        //加密字符串
        String message = "我是测试";
        System.out.println("前端请求的原数据\n"+message);
        String messageEn = publicKeyEncrypt(message, publicKey,point);
        System.out.println("前端请求的加密:\n" + messageEn);
        String messageDe = privateKeyDecrypt(messageEn, privateKey,point);
        System.out.println("后端解密出来的数据:\n" + messageDe);

        System.out.println("=====================");
        //前端数据展示处理
//        //私钥加密，公钥解密
        String s = privateKeyEncrypt(messageDe, privateKey,point);
        System.out.println("后端返回的加密数据\n"+s);
//        s = "M3sQjI8vSpWzyuiyNakHaMRb0QgKSt9aHoAYj1G6cLenPJ235IqGwsipO/tU8WlObn35aUM0vXKCmeLO0E1Qip7FKoYB4OYhD74anwXZFwb7cTXBiazvP5FaN0Fb7vJRWrApfU0JgSqifRCfjNza1pSTxLc8kD3oZ+an88nzVzw=";
//        String  s = "azwa1HWeAjiP2Y2rIJot/cgi9Li31Rs92ycpv/vBYf3ydprwEJii7ZNYo98mGLWh20JZ0ur43FwhYWQw/uZCPHhGMCg7yK3CIqpEMSzzUxXLVxMf8qPmF7wzbNQ6LG+YVDwGHMr+R3r4sKYpw41ZxQzEFd61c7tNqZ9oaouBL3A=";
        String s1 = publicKeyDecrypt(s, publicKey,point);
        System.out.println("前端解密出来显示的数据\n"+s1);
    }

    /**
     * 随机生成密钥对
     *
     * @throws NoSuchAlgorithmException
     * @param point
     */
    public static Map<String, String> genKeyPair(String point) throws NoSuchAlgorithmException {
        log.info("{}|开始生成公私钥",point);
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        Map<String, String> map = new HashMap<>();
        map.put("publicKey", publicKeyString);
        map.put("privateKey", privateKeyString);
        log.info("{}|生成的公私钥|map:{}",point,map);
        return map;
    }

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String publicKeyEncrypt(String str, String publicKey,String point) throws Exception {
        log.info("{}|RSA公钥加密前的数据|str:{}|publicKey:{}",point,str,publicKey);
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").
                generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        log.info("{}|公钥加密后的数据|outStr:{}",point,outStr);
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @param point
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String privateKeyDecrypt(String str, String privateKey, String point) throws Exception {
        log.info("{}|RSA私钥解密前的数据|str:{}|privateKey:{}",point,str,privateKey);
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        log.info("{}|RSA私钥解密后的数据|outStr:{}",point,outStr);
        return outStr;
    }


    /**
     * RSA私钥加密
     *
     * @param str
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String privateKeyEncrypt(String str, String privateKey,String point) throws Exception {
        log.info("{}|RSA私钥加密前的数据|str:{}|publicKey:{}",point,str,privateKey);
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        PrivateKey priKey = KeyFactory.getInstance("RSA").
                generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, priKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes()));
        log.info("{}|RSA私钥加密后的数据|outStr:{}",point,outStr);
        return outStr;
    }

    /**
     * RSA公钥解密
     *
     * @param str
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static String publicKeyDecrypt(String str, String publicKey,String point) throws Exception {
        log.info("{}|RSA公钥解密前的数据|str:{}|publicKey:{}",point,str,publicKey);
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        PublicKey pubKey =  KeyFactory.getInstance("RSA")
                .generatePublic(new X509EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        String outStr = new String(cipher.doFinal(inputByte));
        log.info("{}|RSA公钥解密后的数据|outStr:{}",point,outStr);
        return outStr;
    }



    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    /*public static String publicKeyEncrypt(String str, String publicKey, String point) throws Exception {
        log.info("{}|RSA公钥加密前的数据|str:{}|publicKey:{}", point, str, publicKey);
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").
                generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        //当长度过长的时候，需要分割后加密 117个字节
        byte[] resultBytes = getMaxResultEncrypt(str, point, cipher);

        String outStr = Base64.encodeBase64String(resultBytes);
        log.info("{}|公钥加密后的数据|outStr:{}", point, outStr);
        return outStr;
    }*/

    private static byte[] getMaxResultEncrypt(String str, String point, Cipher cipher) throws IllegalBlockSizeException, BadPaddingException {
        byte[] inputArray = str.getBytes();
        int inputLength = inputArray.length;
        log.info("{}|加密字节数|inputLength:{}", point, inputLength);
        // 最大加密字节数，超出最大字节数需要分组加密
        int MAX_ENCRYPT_BLOCK = 117;
        // 标识
        int offSet = 0;
        byte[] resultBytes = {};
        byte[] cache = {};
        while (inputLength - offSet > 0) {
            if (inputLength - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(inputArray, offSet, MAX_ENCRYPT_BLOCK);
                offSet += MAX_ENCRYPT_BLOCK;
            } else {
                cache = cipher.doFinal(inputArray, offSet, inputLength - offSet);
                offSet = inputLength;
            }
            resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
            System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
        }
        return resultBytes;
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @param point
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    /*public static String privateKeyDecrypt(String str, String privateKey, String point) throws Exception {
        log.info("{}|RSA私钥解密前的数据|str:{}|privateKey:{}", point, str, privateKey);
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
//        String outStr = new String(cipher.doFinal(inputByte));
        //当长度过长的时候，需要分割后解密 128个字节
        String outStr = new String(getMaxResultDecrypt(str, point, cipher));
        log.info("{}|RSA私钥解密后的数据|outStr:{}", point, outStr);
        return outStr;
    }*/

    private static byte[] getMaxResultDecrypt(String str, String point, Cipher cipher) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        byte[] inputArray = Base64.decodeBase64(str.getBytes("UTF-8"));
        int inputLength = inputArray.length;
        log.info("{}|解密字节数|inputLength:{}", point, inputLength);
        // 最大解密字节数，超出最大字节数需要分组加密
        int MAX_ENCRYPT_BLOCK = 128;
        // 标识
        int offSet = 0;
        byte[] resultBytes = {};
        byte[] cache = {};
        while (inputLength - offSet > 0) {
            if (inputLength - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(inputArray, offSet, MAX_ENCRYPT_BLOCK);
                offSet += MAX_ENCRYPT_BLOCK;
            } else {
                cache = cipher.doFinal(inputArray, offSet, inputLength - offSet);
                offSet = inputLength;
            }
            resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
            System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
        }
        return resultBytes;
    }

}

