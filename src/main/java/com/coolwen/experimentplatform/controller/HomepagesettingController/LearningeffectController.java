package com.coolwen.experimentplatform.controller.HomepagesettingController;

import com.coolwen.experimentplatform.dao.EffectDao;
import com.coolwen.experimentplatform.model.Effect;
import com.coolwen.experimentplatform.service.EffectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping(value = "/learning")
public class LearningeffectController {

    @Autowired
    EffectDao effectDao;
    @Autowired
    EffectService effectService;

    @GetMapping(value = "/list")
    public String LearningeffectList(Model model, @RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum){
        Pageable pageable = PageRequest.of(pageNum,3);
        Page<Effect> page = effectDao.findAll(pageable);
        model.addAttribute("learningPageInfo",page);
        return "learningeffect/effect";
    }


    //点击首页添加按钮，来到添加页面
    @GetMapping(value = "/add")
    public String LearningeffectAdd(){
        return "learningeffect/add";
    }

    FileUploadController fileUploadController =new FileUploadController();

    //完成添加操作
    @PostMapping(value = "/add")
    public String add(Effect effect, MultipartFile file, HttpServletRequest req){
        effect.setDic_num(0);
        effect.setDic_datetime(new Date());
        String url = fileUploadController.upload(file,req);
        effect.setEffect_imgurl(url);
        effectService.add(effect);
        return "redirect:/learning/list";
    }

    //进入修改界面
    @GetMapping(value = "/{id}/update")
    public String update(@PathVariable int id, Model model){
        Effect effect = effectService.findById(id);
        model.addAttribute("effect",effect);
        return "learningeffect/update";
    }

    //完成修改操作
    @PostMapping(value = "/{id}/update")
    public String update(@PathVariable int id,Effect effect,MultipartFile file, HttpServletRequest req){
        effect.setId(id);
        effect.setDic_datetime(new Date());
        String url = fileUploadController.upload(file,req);
        if (url !=null){
            effect.setEffect_imgurl(url);
        }else {
            effect.setEffect_imgurl(effectService.findById(id).getEffect_imgurl());
        }
        effect.setDic_num(effectService.findById(id).getDic_num());
        effectService.add(effect);
        System.out.println(effectService.findById(id).toString());
        System.out.println("修改成功");
        return "redirect:/learning/list";
    }

    @GetMapping(value = "/{id}/delete")
    public String delete(@PathVariable int id){
        effectService.delete(id);
        return "redirect:/learning/list";
    }
}
