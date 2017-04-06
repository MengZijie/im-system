package com.mzj.im.web.controller;

import com.mzj.im.util.IOUtil;
import com.mzj.im.util.dic.ProjectConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

/**
 * Created by ob on 17-3-30.
 */
@Controller
public class UtilsController {

    @Autowired
    private ProjectConfig projectConfig;

    @Autowired
    private IOUtil ioUtil;

    @RequestMapping(value = "upload")
    public String uploadFile(HttpServletRequest request, HttpServletResponse response) {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if(resolver.isMultipart(request)) {
            MultipartHttpServletRequest requests = (MultipartHttpServletRequest) request;
            Iterator<String> iterator = ((MultipartHttpServletRequest) request).getFileNames();
            while (iterator.hasNext()) {
                MultipartFile file = requests.getFile(iterator.next());
                if(file != null){
                    String filename = file.getOriginalFilename();
                    String suffix = ioUtil.getFileSuffix(filename);
                    if(StringUtils.isNotBlank(suffix)){
                        String fileUploadPath = projectConfig.getConfigs().getProperty("fileUploadPath");
                        String name = UUID.randomUUID().toString() + suffix;
                        File localFile = new File(filename + "/" + name);
                        try {
                            file.transferTo(localFile);
                            return filename;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return StringUtils.EMPTY;
    }
}
