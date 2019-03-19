package com.fnst.face.util;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Luyue
 * @date 2019/3/19 14:45
 **/
public class Base64ImageUtil {

    public static void main(String[] args) {
        System.out.println(imageToBase64ByOnline("http://120.77.146.118/2019-03-19-16-14-16-黄忠2.png"));
    }

    /** 将本地图片转换为base64编码
     *@return  String base64图片编码
     *@author  卢越
     *@date  2019/3/18
     */
    public static String imageToBase64ByLocal(String imgFile) {
        InputStream is = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            is = new FileInputStream(imgFile);
            data = new byte[is.available()];
            is.read(data);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }

    public static String imageToBase64ByOnline(String imgURL) {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            // 创建URL
            URL url = new URL(imgURL);
            byte[] by = new byte[1024];
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            InputStream is = conn.getInputStream();
            // 将内容读取内存中
            int len = -1;
            while ((len = is.read(by)) != -1) {
                data.write(by, 0, len);
            }
            // 关闭流
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data.toByteArray());
    }

    /** 对字节数组字符串进行Base64解码并生成图片
     * @param imgStr		base64字符串
     * @param imgFilePath	图片存放路径
     *@author  卢越
     *@date  2019/3/19
     */
    public static boolean Base64ToImage(String imgStr,String imgFilePath) {

        // 图像数据为空
        if (StringUtils.isEmpty(imgStr)) {
            return false;
        }

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                // 调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static boolean Base64ToImage(String imgStr,File targetFile) {

        // 图像数据为空
        if (StringUtils.isEmpty(imgStr)) {
            return false;
        }

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                // 调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(targetFile);
            out.write(b);
            out.flush();
            out.close();

            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
