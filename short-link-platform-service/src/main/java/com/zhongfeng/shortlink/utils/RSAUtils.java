package com.zhongfeng.shortlink.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;
import static org.apache.commons.codec.binary.Base64.encodeBase64String;

/**
 * <p>Description: RSA非对称加密算法实现 </p >
 * @version 1.0
 * @date 2023-05-09 09:38
 */
@Slf4j
public class RSAUtils {
    
    private static final String SRC = "15962528635";

    public static void main(String[] args) throws UnsupportedEncodingException {
//        jdkRsa();
//        Map<String, String> stringStringMap = genKeyPair(512);
//        String publicKey = stringStringMap.get("publicKey");
//        String privateKey = stringStringMap.get("privateKey");
//        out.println("公钥:" + publicKey);
//        out.println("私钥:"+privateKey);
//        String encrypt = encryptByPublicKey(SRC, privateKey);
//        out.println("密文:" + encrypt);
//        String decrypt = decryptByPrivateKey(encrypt, publicKey);
//        out.println("明文:"+decrypt);
        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJscNJzWa/TdxOabvormOlwx7Y45DqIsAvsXjPBELEY7W73QJ2bXgHA/UM5LyYvhQbKZO9+iQEdOJ+AtvNmVufECAwEAAQ==";
        out.println(decryptByPrivateKey(URLDecoder.decode("YMBk6rZqWyHxXnsdp3nTc6aOSGRtdxK1c3EMj61a%2BnYiNdvSt1GODxrv66TS6CYWc7c9drThA0zz9uPveVNAew%3D%3D", "UTF-8"), publicKey));

    }
    
    /**
     * JDK-RSA算法实现
     */
    private static void jdkRsa() {
        try {
            // 初始化密钥
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            
            out.println("public key is : " + encodeBase64String(rsaPublicKey.getEncoded()));
            out.println("private key is : " + encodeBase64String(rsaPrivateKey.getEncoded()));
            
            // 私钥加密，公钥解密 -- 加密
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal(SRC.getBytes(StandardCharsets.UTF_8));
            out.println("私钥加密，公钥解密 -- 加密 : " + encodeBase64String(result));
            
            // 私钥加密，公钥解密 -- 解密
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            result = cipher.doFinal(result);
            out.println("私钥加密，公钥解密 -- 解密 : " + new String(result));
    
            // 公钥加密，私钥解密 -- 加密
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] res = cipher.doFinal(SRC.getBytes(StandardCharsets.UTF_8));
            out.println("公钥加密，私钥解密 -- 加密 : " + encodeBase64String(res));
            // 公钥加密，私钥解密 -- 解密
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            res = cipher.doFinal(res);
            out.println("公钥加密，私钥解密 -- 解密 : " + new String(res));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成公私钥
     *
     * @param keySize 密钥长度
     */
    public static Map<String, String> genKeyPair(int keySize) {
        Map<String, String> keyMap = new HashMap<>();
        try {
            //创建密钥对生成器
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(keySize);
            //生成密钥对
            KeyPair keyPair = kpg.generateKeyPair();
            //公钥
            PublicKey publicKey = keyPair.getPublic();
            //私钥
            PrivateKey privateKey = keyPair.getPrivate();
            keyMap.put("publicKey", encodeBase64String(publicKey.getEncoded()));
            keyMap.put("privateKey", encodeBase64String(privateKey.getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            log.error("生成公私钥信息失败："+e.getMessage());
        }
        return keyMap;
    }

    /**
     * 加密
     * @param data
     * @param privateKeyStr
     * @return
     */
    public static String encryptByPublicKey(String data, String privateKeyStr) {
        try{
            // 私钥加密，公钥解密 -- 加密
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyStr));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return URLEncoder.encode(Base64.encodeBase64String(result),"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
            log.error("RSA私钥加密失败,data:{}",data);
        }
       return null;
    }

    /**
     * 解密
     * @param data
     * @return
     */
    public static String decryptByPrivateKey(String data,String publicKeyStr) {

        try{
            // 私钥加密，公钥解密 -- 解密
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyStr));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] result = cipher.doFinal(Base64.decodeBase64(data));
            return new String(result);
        }catch (Exception e){
            log.error("RAS解密失败，data:{}",data);
        }
        return null;
    }

}