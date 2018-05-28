package com.truck.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.truck.service.FileService;
import com.truck.util.FTPUtil;
import com.truck.util.PropertiesUtil;
import main.java.com.UpYun;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by geely
 */
@Service("iFileService")
public class FileServiceImpl implements FileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    private String bucketName = PropertiesUtil.getProperty("bucketName"),
            userName = PropertiesUtil.getProperty("userName"),
            passWord = PropertiesUtil.getProperty("passWord"),
            uploadUrl = PropertiesUtil.getProperty("uploadUrl");


    public String upload(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        //扩展名
        //abc.jpg
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        logger.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}", fileName, path, uploadFileName);

        File fileDir = new File(path);
        if (!fileDir.exists()) {
           /* fileDir.setWritable(true);*/
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);
        try {
            file.transferTo(targetFile);
            //文件已经上传成功了
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //已经上传到ftp服务器上
            targetFile.delete();
        } catch (IOException e) {
            logger.error("上传文件异常", e);
            return null;
        }
        return targetFile.getName();
    }

    public String uploadCDN(MultipartFile file, String path) throws IOException {
        UpYun upyun = new UpYun(bucketName, userName, passWord);
        upyun.setTimeout(60);
        upyun.setApiDomain(UpYun.ED_AUTO);
        String fileName = file.getOriginalFilename();
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);
        file.transferTo(targetFile);
        boolean result = upyun.writeFile(uploadUrl + uploadFileName,
                targetFile, true);
        targetFile.delete();
        if (result) {
            return uploadUrl + uploadFileName;
        }
        return null;
    }

    public Map<String,String> delCDN( String urls) throws IOException {
        Map fileMap = Maps.newHashMap();
        UpYun upyun = new UpYun(bucketName, userName, passWord);
        upyun.setTimeout(60);
        upyun.setApiDomain(UpYun.ED_AUTO);

        List<String> urlList = Splitter.on(",").splitToList(urls);
        for (String url : urlList) {
            String path = url.substring(url.indexOf("com")+3);

            boolean result = upyun.deleteFile(path);
            fileMap.put(url,result);
        }
        return fileMap;
    }
}
