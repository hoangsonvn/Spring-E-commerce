package com.demo6.shop.common;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.demo6.shop.constant.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;

@Component
public class Common implements ICommon {
    @Autowired
    private AmazonS3Client awsS3Client;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

    public String image(MultipartFile imageFile) {
        String originalFilename = imageFile.getOriginalFilename();
        int lastIndex = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(lastIndex);
        String avatarFilename = System.currentTimeMillis() + ext;
        File newfile = new File("C:\\image_spring_boot\\" + avatarFilename);
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(newfile);
            fileOutputStream.write(imageFile.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return avatarFilename;
    }

    public int totalPage(int count, int pageSize) {
        return count % SystemConstant.PAGESIZE == 0 ? count / SystemConstant.PAGESIZE : (count / SystemConstant.PAGESIZE + 1);
    }

    public void notificate(HttpServletRequest request) {
        String messageCreate = request.getParameter("messagecreate");
        String messageDelete = request.getParameter("messagedelete");
        String messageUpdate = request.getParameter("messageupdate");
        String tick = request.getParameter("tick");
        if (messageCreate != null) {
            request.setAttribute("messagecreate", resourceBundle.getString(messageCreate));
        }
        if (messageDelete != null) {
            request.setAttribute("messagedelete", resourceBundle.getString(messageDelete));
        }
        if (messageUpdate != null) {
            request.setAttribute("messageupdate", resourceBundle.getString(messageUpdate));
        }
        if (tick != null) {
            request.setAttribute("tick", resourceBundle.getString(tick));
        }
    }

    @Override
    public String imageUpload(MultipartFile imageFile) {
        String filenameExtension = StringUtils.getFilenameExtension(imageFile.getOriginalFilename());
        String key = System.currentTimeMillis() + "." + filenameExtension;
        ObjectMetadata metaData = new ObjectMetadata();
        metaData.setContentLength(imageFile.getSize());
        metaData.setContentType(imageFile.getContentType());

        try {
            awsS3Client.putObject("bucketslhs", key, imageFile.getInputStream(), metaData);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An exception occured while uploading the file");
        }
        awsS3Client.setObjectAcl("bucketslhs", key, CannedAccessControlList.PublicRead);
        return key;
    }
}
