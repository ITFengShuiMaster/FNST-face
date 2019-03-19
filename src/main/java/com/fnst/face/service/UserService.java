package com.fnst.face.service;

import com.fnst.face.common.ResponseCode;
import com.fnst.face.common.ServerResponse;
import com.fnst.face.entity.User;
import com.fnst.face.mapper.UserMapper;
import com.fnst.face.util.DateTimeUtil;
import com.fnst.face.util.FTPFileUploadUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

/**
 * @author Luyue
 * @date 2019/3/17 16:24
 **/
@Service
public class UserService {

    @Value("${img.path}")
    private String imgPath;
    @Autowired
    private UserMapper userMapper;

    public ServerResponse deleteUser(Long id) {
        if (id == null) {
            return ServerResponse.failure(ResponseCode.PARAM_IS_BLANK);
        }

        if (userMapper.deleteByPrimaryKey(id) <= 0) {
            return ServerResponse.failure(ResponseCode.SYSTEM_INNER_ERROR);
        }
        return ServerResponse.success();
    }

    public ServerResponse updateUser(User user) {
        // TODO 待测
        user.setUpdateTime(new Date());
        try {
            if (userMapper.updateByPrimaryKeySelective(user) <= 0) {
                return ServerResponse.failure(ResponseCode.PARAM_IS_INVALID);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.failure(ResponseCode.PARAM_IS_INVALID);
        }
        return ServerResponse.success();
    }

    public ServerResponse getUser(Long id) {
        if (id == null) {
            return ServerResponse.failure(ResponseCode.PARAM_IS_BLANK);
        }

        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return ServerResponse.failure(ResponseCode.USER_NOT_EXIST);
        }
        return ServerResponse.success(user);
    }

    public ServerResponse insertUser(User user, String path) {
        if (!validUserData(user)) {
            return ServerResponse.failure(ResponseCode.PARAM_NOT_COMPLETE);
        }

        if (userMapper.selectByUserNumber(user.getJobNumber()) != null) {
            return ServerResponse.failure(ResponseCode.USER_HAS_EXISTED);
        }

        String fileName = saveImgFile(user.getImgFile(), path);
        if (StringUtils.isBlank(fileName)) {
            return ServerResponse.failure(ResponseCode.SYSTEM_INNER_ERROR);
        }

        user.setImgUrl(imgPath + fileName);

        if (userMapper.insertReturnId(user) <= 0) {
            return ServerResponse.failure(ResponseCode.PARAM_IS_INVALID);
        }

        return ServerResponse.success();
    }

    private boolean validUserData(User user) {
        if (StringUtils.isBlank(user.getName())) {
            return false;
        }

        if (StringUtils.isBlank(user.getImgUrl())) {
            return false;
        }

        if (StringUtils.isBlank(user.getJobNumber())) {
            return false;
        }

        if (user.getSex() == null) {
            user.setSex(false);
        }

        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        return true;
    }

    private String saveImgFile(MultipartFile file, String path) {
        if (file.isEmpty()) {
            return "";
        }

        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }

        String fileName = DateTimeUtil.dateToStr(new Date()) + "-" + file.getOriginalFilename();
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

    public static void main(String[] args) {
        System.out.println(StringUtils.isBlank(null));
    }

    public ServerResponse listUsers() {
        return ServerResponse.success(userMapper.selectAll());
    }
}
