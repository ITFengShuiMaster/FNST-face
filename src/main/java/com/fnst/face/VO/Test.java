package com.fnst.face.VO;

import com.fnst.face.util.JsonUtil;

/**
 * @author Luyue
 * @date 2019/3/18 17:31
 **/
public class Test {

    public static void main(String[] args) {
//        User user1 = new User();
//        user1.setId(1);
//        user1.setEmail("luyue@qq.com");
//
//        String json1 = objToJson(user1);
//        String json2 = objToJsonPretty(user1);
//
//        log.info("json1 is {}", json1);
//        log.info("json2 is {}", json2);
//
//        User user = json2Object(json1, User.class);
//        System.out.println("===============================");
//
//        List<User> userList = Lists.newArrayList();
//        User u1 = new User();
//        u1.setId(5);
//        u1.setEmail("luyue@qq.comcom");
//
//        User u2 = new User();
//        u2.setId(6);
//        u2.setEmail("luyue@qq.comcom");
//
//        userList.add(u1);
//        userList.add(u2);
//        String listJson = objToJsonPretty(userList);
//        log.info("jsonList is {}", listJson);
//
//        List<User> testUserList = json2Object(listJson, List.class);
//
//        List<User> jsonToObj = json2Object(listJson, new TypeReference<List<User>>(){});
//        List<User> jsonToObj2 = json2Object(listJson, List.class, User.class);
//        System.out.println("**************************");

        String json = "{\n" +
                "\t\"faces1\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"face_rectangle\":{\"width\": 311, \"top\": 254, \"left\": 137, \"height\": 311},\n" +
                "\t\t\t\"face_token\": \"548e665f66f702984d217400c0d54f5e\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"faces2\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"face_rectangle\":{\"width\": 277, \"top\": 250, \"left\": 150, \"height\": 277},\n" +
                "\t\t\t\"face_token\": \"a2121f4793a5cfbf2bb9b6b8af939c75\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"time_used\": 1118,\n" +
                "\t\"thresholds\":{\n" +
                "\t\t\"1e-3\": 62.327,\n" +
                "\t\t\"1e-5\": 73.975,\n" +
                "\t\t\"1e-4\": 69.101\n" +
                "\t},\n" +
                "\t\"confidence\": 97.043,\n" +
                "\t\"image_id2\": \"XvpXSJpb99EpwUNR62FfJw==\",\n" +
                "\t\"image_id1\": \"6tLJJmTrlvcqDk+uzCC67g==\",\n" +
                "\t\"request_id\": \"1552896916,85f9efa6-3e59-446a-aa12-c94f75f9ad86\"\n" +
                "}";

        Face face = JsonUtil.json2Object(json, Face.class);
        int a = 1;
    }
}
