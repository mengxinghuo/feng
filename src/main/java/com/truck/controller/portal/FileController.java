package com.truck.controller.portal;

import com.google.common.collect.Maps;
import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.pojo.User;
import com.truck.service.FileService;
import com.truck.util.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by master on 2018/3/12.
 */
@Controller
@RequestMapping("/file")
public class FileController {


    @Autowired
    private FileService fileService;


    @RequestMapping(value = "uploadFile.do", method = RequestMethod.POST)
    @ResponseBody
    //上传图片
    public Map upLoad(@RequestParam(value = "upload_file", required = false) MultipartFile[] files, HttpServletRequest request, HttpServletResponse response) {
        Map resultMap = Maps.newHashMap();
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = null;
        int success = 0;
        String[] url = new String[files.length];
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                targetFileName = fileService.upload(files[i], path);
                if (!StringUtils.isBlank(targetFileName)) {
                    success++;
                    url[i] = PropertiesUtil.getProperty("ftp.server.http.prefixBack") + targetFileName;
                }
            }
        }
        resultMap.put("success", true);
        resultMap.put("msg", "成功上传" + success + "图片");
        resultMap.put("file_path", url);
        response.addHeader("Access-Control-Allow-Headers", "X-File-Name");
        return resultMap;
    }

    @RequestMapping(value = "uploadFileCDN.do", method = RequestMethod.POST)
    @ResponseBody
    //上传图片
    public Map upLoadCDN(HttpSession session,@RequestParam(value = "upload_file", required = false) MultipartFile[] files, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        Map resultMap = Maps.newHashMap();
   /*     if(admin == null){
            resultMap.put("success",false);
            resultMap.put("msg","请登录管理员");
            return resultMap;
        }*/
        MultipartFile file = null;
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = null;
        int success = 0;
        String[] urlS = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            targetFileName = fileService.uploadCDN(files[i], path);
            if (StringUtils.isNotBlank(targetFileName)) {
                urlS[i] = PropertiesUtil.getProperty("field") + targetFileName;
            }
            success++;
        }
        resultMap.put("success", true);
        resultMap.put("msg", "成功上传" + success + "文件");
        resultMap.put("file_path", urlS);
        return resultMap;
    }

    @RequestMapping(value = "delFileCDN.do", method = RequestMethod.POST)
    @ResponseBody
    //删除图片
    public Map delCDN(HttpSession session,String urls ,HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        Map resultMap = Maps.newHashMap();
   /*     if(admin == null){
            resultMap.put("success",false);
            resultMap.put("msg","请登录管理员");
            return resultMap;
        }*/
        Map map = fileService.delCDN( urls);
        resultMap.put("success", true);
        resultMap.put("msg", "成功删除文件");
        resultMap.put("file_path", map);
        return resultMap;
    }

}
