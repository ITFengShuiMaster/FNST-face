package com.fnst.face.service.async;

import com.fnst.face.VO.face.FaceToken;
import com.fnst.face.common.CommonVar;
import com.fnst.face.entity.User;
import com.fnst.face.mapper.UserMapper;
import com.fnst.face.util.JsonUtil;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Luyue
 * @date 2019/3/19 22:13
 **/
@Component
public class ImgToFaceTokenAsync {
    private static Logger log = LoggerFactory.getLogger(ImgToFaceTokenAsync.class);

    @Autowired
    private UserMapper userMapper;

    @Async
    public void imgToFaceToken(User user, String imgUrl) {
        String url = "https://api-cn.faceplusplus.com/facepp/v3/detect";

        HashMap<String, String> map = new HashMap<>();
        map.put("api_key", CommonVar.API_KEY);
        map.put("api_secret", CommonVar.API_SECREAT);
        map.put("image_url", imgUrl);

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

            log.info(result);
            FaceToken faceToken = JsonUtil.json2Object(result, FaceToken.class);
            if (faceToken.getFaces() == null || faceToken.getFaces().size() == 0) {
                return;
            } else {
                user.setFaceToken(faceToken.getFaces().get(0).getFace_token());
                userMapper.updateByPrimaryKeySelective(user);
            }

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
    }

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
}
