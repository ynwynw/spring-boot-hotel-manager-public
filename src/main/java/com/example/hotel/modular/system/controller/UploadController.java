package com.example.hotel.modular.system.controller;


import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: luhailiang
 * @Date: 2018/10/20 19:07
 * @Description: 文件上传Controller
 */
@Controller
public class UploadController {

    @Value(value = "${upload.file.path}")
    private String uploadFilePath;


    /**
     * @param file
     * @return : java.util.Map<java.lang.String,java.lang.Object>
     * @author: luhailiang
     * @date: 2018/10/30 12:10
     * @description: 文件上传
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadFile(MultipartFile file) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (!file.isEmpty()) {

            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = UUID.randomUUID() + suffixName;

            Date date = new Date();
            int hashcode = fileName.hashCode();
            int dir1 = hashcode & 0xf;  //0--15
            int dir2 = (hashcode & 0xf0) >> 4; //0--15
            String path = uploadFilePath + new SimpleDateFormat("yyyy/MM/dd").format(date) + "/" + dir1 + "/" + dir2 + "/";
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path + newFileName));
            map.put("code", 0);
            map.put("msg", "上传成功");
            map.put("src", "/uploads/" + new SimpleDateFormat("yyyy/MM/dd").format(date) + "/" + dir1 + "/" + dir2 + "/" + newFileName);
            System.out.println("http://localhost:8080/uploads/" + new SimpleDateFormat("yyyy/MM/dd").format(date) + "/" + dir1 + "/" + dir2 + "/" + newFileName);
        }
        return map;
    }


    /**
     * @param file
     * @param CKEditorFuncNum
     * @return : java.lang.String
     * @author: luhailiang
     * @date: 2018/11/11 18:12
     * @description: ckeditor上传
     */
    @RequestMapping(value = "/ckeditorUpload", method = RequestMethod.POST)
    @ResponseBody
    public String ckeditorUpload(@RequestParam("upload") MultipartFile file, String CKEditorFuncNum) throws IOException {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID() + suffixName;
        Date date = new Date();
        int hashcode = fileName.hashCode();
        int dir1 = hashcode & 0xf;  //0--15
        int dir2 = (hashcode & 0xf0) >> 4; //0--15
        String path = uploadFilePath + new SimpleDateFormat("yyyy/MM/dd").format(date) + "/" + dir1 + "/" + dir2 + "/";
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path + newFileName));

        //回显
        StringBuffer sb = new StringBuffer();
        sb.append("<script type=\"text/javascript\">");
        sb.append("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'" + "/uploads/" + new SimpleDateFormat("yyyy/MM/dd").format(date) + "/" + dir1 + "/" + dir2 + "/" + newFileName + "','')");
        sb.append("</script>");
        return sb.toString();
    }
}
