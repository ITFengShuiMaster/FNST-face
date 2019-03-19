package com.fnst.face.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Luyue
 * @date 2019/3/18 9:07
 **/
public class FaceCompareUtil {
    private static String apiKey = "-62uv_akklJjwoQuZawRjy-dbxoPhvWT";
    private static String apiSecreat = "CTTqho-9btsW-DVPJcLeCBIFHIt6LH_A";

    /**  人脸图片比较，参数为base64编码
     *@param  imgBase64_1 待比较图片1的base64编码
     *@param imgBase64_2 待比较图片2的base64编码
     *@return  String confidence
                        比对结果置信度，范围 [0,100]，小数点后3位有效数字，数字越大表示两个人脸越可能是同一个人。
                        注：如果传入图片但图片中未检测到人脸，则无法进行比对，本字段不返回。
     *@author  卢越
     *@date  2019/3/18
     */
    public static String compare(String imgBase64_1, String imgBase64_2) {
        String url = "https://api-cn.faceplusplus.com/facepp/v3/compare";

        HashMap<String, String> map = new HashMap<>();
        map.put("api_key", apiKey);
        map.put("api_secret", apiSecreat);
        map.put("image_base64_1", imgBase64_1);
        map.put("image_base64_2", imgBase64_2);

        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);

        // post请求携带的参数entity
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for(Map.Entry<String,String> entry : map.entrySet()) {
            pairs.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
        }

        CloseableHttpResponse response = null;
        try {
            post.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));
            response = httpClient.execute(post);
            if(response != null && response.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity entity = response.getEntity();
                result = entityToString(entity);
            }
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
                if(response != null)
                {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public static String compareByUrl(String imgUrl1, String imgBase64_2) {
        String url = "https://api-cn.faceplusplus.com/facepp/v3/compare";

        HashMap<String, String> map = new HashMap<>();
        map.put("api_key", apiKey);
        map.put("api_secret", apiSecreat);
        map.put("image_url1", imgUrl1);
        map.put("image_base64_2", imgBase64_2);

        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);

        // post请求携带的参数entity
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for(Map.Entry<String,String> entry : map.entrySet()) {
            pairs.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
        }

        CloseableHttpResponse response = null;
        try {
            post.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));
            response = httpClient.execute(post);
            if(response != null && response.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity entity = response.getEntity();
                result = entityToString(entity);
            }
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
                if(response != null)
                {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public static String compareByToken(String imgToken1, String imgToken2) {
        String url = "https://api-cn.faceplusplus.com/facepp/v3/compare";

        HashMap<String, String> map = new HashMap<>();
        map.put("api_key", apiKey);
        map.put("api_secret", apiSecreat);
        map.put("face_token1", imgToken1);
        map.put("face_token2", imgToken2);

        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);

        // post请求携带的参数entity
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for(Map.Entry<String,String> entry : map.entrySet()) {
            pairs.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
        }

        CloseableHttpResponse response = null;
        try {
            post.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));
            response = httpClient.execute(post);
            if(response != null && response.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity entity = response.getEntity();
                result = entityToString(entity);
            }
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
                if(response != null)
                {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    /** HTTPClient返回值解析
     *@author  卢越
     *@date  2019/3/18
     */
    private static String entityToString(HttpEntity entity) throws IOException {
        String result = null;
        if(entity != null)
        {
            long lenth = entity.getContentLength();
            if(lenth != -1 && lenth < 2048)
            {
                result = EntityUtils.toString(entity,"UTF-8");
            }else {
                InputStreamReader reader1 = new InputStreamReader(entity.getContent(), "UTF-8");
                CharArrayBuffer buffer = new CharArrayBuffer(2048);
                char[] tmp = new char[1024];
                int l;
                while((l = reader1.read(tmp)) != -1) {
                    buffer.append(tmp, 0, l);
                }
                result = buffer.toString();
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(compare(imageToBase64ByLocal("E:\\FNST\\face\\face\\src\\main\\resources\\static\\img\\luyue1.jpg")
//                , imageToBase64ByLocal("E:\\FNST\\face\\face\\src\\main\\resources\\static\\img\\luyue2.JPG")));
    }
}
