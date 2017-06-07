package com.mzj.im.web.controller;

import com.mzj.im.model.po.MessagePO;
import com.mzj.im.util.IOUtil;
import com.mzj.im.util.ResponseObject;
import com.mzj.im.util.dic.ProjectConfig;
import com.mzj.im.web.webSocket.CustomWebSocketHandler;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.socket.TextMessage;

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
    private CustomWebSocketHandler webSocketHandler;

    @Autowired
    private IOUtil ioUtil;

    @ResponseBody
    @RequestMapping(value = "upload/img")
    public String uploadFile(HttpServletRequest request, HttpServletResponse response) {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        String name = StringUtils.EMPTY;
        if(resolver.isMultipart(request)) {
            MultipartHttpServletRequest requests = resolver.resolveMultipart(request);
            Iterator<String> iterator = requests.getFileNames();
            while (iterator.hasNext()) {
                MultipartFile file = requests.getFile(iterator.next());
                if(file != null){
                    String filename = file.getOriginalFilename();
                    String suffix = ioUtil.getFileSuffix(filename);
                    if(StringUtils.isNotBlank(suffix)){
                        String fileUploadPath = projectConfig.getConfigs().getProperty("imgUploadPath");
                        name = UUID.randomUUID().toString() + "." + suffix;
                        File localFile = new File(fileUploadPath + "/" + name);
                        try {
                            file.transferTo(localFile);
                            return new ResponseObject<String>().setSuccess().setData(name).toJSONString();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return new ResponseObject<String>().setFaild().setData(StringUtils.EMPTY).toJSONString();
    }

    @RequestMapping(value = "message/redirect", method = RequestMethod.POST)
    @ResponseBody
    public String redirectMessage(MessagePO message) {
//        Long userId = message.getTargetUserId();
//        try {
//            webSocketHandler.sendMessageToUser(userId, new TextMessage(message.toString()));
//            return new ResponseObject<String>().setSuccess().setData("success").toJSONString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return new ResponseObject<String>().setFaild().setData("fail").toJSONString();
    }
}
