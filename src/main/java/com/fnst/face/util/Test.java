package com.fnst.face.util;

import sun.net.ftp.FtpClient;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * @author Luyue
 * @date 2019/3/18 13:05
 **/
public class Test {

    public static void main(String[] args) {
        File fIle = new File("E:\\FNST\\face\\face\\src\\main\\resources\\static\\img\\luyue1.jpg");
        ArrayList<File> list = new ArrayList<>();
        list.add(fIle);
        try {
            FTPFileUploadUtil.ftpUpload(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
