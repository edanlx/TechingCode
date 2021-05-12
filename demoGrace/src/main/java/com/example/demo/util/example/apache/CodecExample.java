package com.example.demo.util.example.apache;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author seal email:876651109@qq.com
 * @date 2020/5/24 7:18 PM
 * @description
 */
public class CodecExample {
    public static void main(String[] args) {
        // Base64、MD5、Hex、SHA1、DES QCodec
    }

    public void base64Example(){
        System.out.println("===============base64======================");
        Base64 base64 = new Base64();
        String s = base64.encodeToString("测试22222222222".getBytes());
        System.out.println(s);
        String s1 = new String(base64.decode(s));
        System.out.println(s1);
    }

    public void md5Example(){
        System.out.println("===============MD5======================");
        String result = DigestUtils.md5Hex("测试");
        System.out.println(result);
    }

    public void shaExample(){
        System.out.println("===============sha======================");
        String result = DigestUtils.sha256Hex("测试");
        System.out.println(result);
    }

    public static String encodeHexTest(String str) throws UnsupportedEncodingException {
        return Hex.encodeHexString(str.getBytes(StandardCharsets.UTF_8));
    }

    private static String decodeHexTest(String str) throws DecoderException {
        return new String((byte[])new Hex().decode(str));
    }
}
