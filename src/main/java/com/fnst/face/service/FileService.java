package com.fnst.face.service;

import com.fnst.face.util.Base64ImageUtil;
import com.fnst.face.util.DateTimeUtil;
import com.fnst.face.util.FTPFileUploadUtil;
import com.google.common.collect.Lists;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author Luyue
 * @date 2019/3/19 15:26
 **/
@Service
public class FileService {

    @Async
    public void saveImgFile(String imgStr, String path, String fileName) {
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }

//        String fileName = DateTimeUtil.dateToStr(new Date()) + "-online.jpg";
        File targetFile = new File(path, fileName);

        if (!Base64ImageUtil.Base64ToImage(imgStr, targetFile)) {
//            return null;
        }

        try {
            // 将图片上传至ftp服务器
            if (!FTPFileUploadUtil.ftpUpload(Lists.newArrayList(targetFile))) {
//                return null;
            }

            //删除本地图片
            targetFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
//            return null;
        }
//        return targetFile.getName();
    }

    public String saveImgFile(MultipartFile file, String path) {
        if (file.isEmpty()) {
            return "";
        }

        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }

        String fileName = DateTimeUtil.dateToStr(new Date()) + "-local.jpg";
        File targetFile = new File(path, fileName);
        // 将图片上传到FTP服务器
        try {
            // 到这一步，图片已经成功上传
            file.transferTo(targetFile);

            // 将图片上传至ftp服务器
            if (!FTPFileUploadUtil.ftpUpload(Lists.newArrayList(targetFile))) {
                return null;
            }

            //删除本地图片
            targetFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return targetFile.getName();
    }



}
