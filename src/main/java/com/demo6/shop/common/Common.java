package com.demo6.shop.common;

import com.demo6.shop.constant.SystemConstant;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class Common implements ICommon{
    public String image(MultipartFile imageFile){
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

    public int totalPage(int count,int pageSize){
        return count % SystemConstant.PAGESIZE ==0?count/SystemConstant.PAGESIZE : (count/SystemConstant.PAGESIZE+1);
    }
}
