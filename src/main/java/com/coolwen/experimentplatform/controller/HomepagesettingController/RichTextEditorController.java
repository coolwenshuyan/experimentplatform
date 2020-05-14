package com.coolwen.experimentplatform.controller.HomepagesettingController;

import com.coolwen.experimentplatform.config.MyMvcConfig;
import com.coolwen.experimentplatform.utils.FileType;
import com.coolwen.experimentplatform.utils.FileUtil;
import com.coolwen.experimentplatform.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: experimentplatform
 * @Package: com.coolwen.experimentplatform.controller.HomepagesettingController
 * @ClassName: FileController
 * @Author: Txc
 * @Description: 富文本编辑器
 * @Date: 2020/5/14 0014 12:22
 * @Version: 1.0
 */

@Controller
@RequestMapping(value = "/file")
public class RichTextEditorController {
    @Value("${web.ExpModelImage-path}")
    private String expModelImage ;

    @ResponseBody
    @RequestMapping("/upload")
    public Message upload(@RequestParam("myFileName") MultipartFile file, HttpServletRequest request) {
        Map<String, Object> object = new HashMap();
        String fileName = file.getOriginalFilename();
        fileName = FileUtil.renameToUUID(fileName);
        try {
            FileUtil.uploadFile(file.getBytes(), expModelImage, fileName);
            object.put("path", expModelImage +"/"+ fileName);
            object.put("url","/ExperimentPlatform/ExpModelImage/"+fileName);
            return Message.ok().put("data",object);
        } catch (Exception e) {
            return Message.error();
        }
    }
}
