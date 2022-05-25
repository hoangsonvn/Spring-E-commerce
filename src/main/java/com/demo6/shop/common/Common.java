package com.demo6.shop.common;

import com.demo6.shop.constant.SystemConstant;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;

@Component
public class Common implements ICommon {
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
}
