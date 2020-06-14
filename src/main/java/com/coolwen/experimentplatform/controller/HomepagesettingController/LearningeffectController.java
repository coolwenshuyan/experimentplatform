/**
 * 文件名：LearningeffectController.java
 * 修改人：xxxx
 * 修改时间：
 * 修改内容：新增
 */

package com.coolwen.experimentplatform.controller.HomepagesettingController;

import com.coolwen.experimentplatform.dao.EffectRepository;
import com.coolwen.experimentplatform.model.Effect;
import com.coolwen.experimentplatform.model.NewsInfo;
import com.coolwen.experimentplatform.service.EffectService;
import com.coolwen.experimentplatform.service.NewsInfoService;
import com.coolwen.experimentplatform.utils.FileUploadUtil;
import com.coolwen.experimentplatform.utils.GetServerRealPathUnit;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Date;
/**
*@Description 主要为后台管理系统中首页-->学习效果 的增删改查，前端学习效果页面优秀实验报告展示
*@Author 朱治汶
*@Version 1.0
*@Date 2020/5/29 16:11
*/
@Controller
@RequestMapping(value = "/learning")
public class LearningeffectController {

    @Autowired
    EffectRepository effectRepository;   //学习效果的dao层
    @Autowired
    EffectService effectService;        //学习效果的servi层
    @Autowired
    NewsInfoService newsInfoService;

    FileUploadController fileUploadController =new FileUploadController();  //上传文件
    /**
     * 进入后台管理系统的接口
     * @return 返回到后台框架界面
     */
    @GetMapping(value = "kuangjia")
    public String kuangjia(){
        return "kuangjia/frame";
    }

    /**
     * 进入前端首页的界面
     * @param model 存储前端学习效果页面的优秀实验报告展示的数据
     * @param pageNum 分页页数
     * @return 进入到前端前端学习效果页面
     */
    @GetMapping(value = "/learningList")
    public String learningList(Model model, @RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum){
        //查询全部优秀实验报告数据
        Pageable pageable = PageRequest.of(pageNum,5);
        Page<Effect> page = effectRepository.findAll(pageable);
        model.addAttribute("learningPageInfo",page);
        //往期参与考核的全部学生
        int allpasspeople = newsInfoService.findAllpasspeople();
        //往期参与考核的优秀学生（85分以上）
        int excellent = newsInfoService.findExcellentpeople();
        //往期参与考核的优秀学生（60分-85分）
        int qualified = newsInfoService.findQualifiedpeople();
        //往期参与考核的优秀学生（60分以下）
        int unqualified = newsInfoService.findUnqualifiedpeople();

        if (allpasspeople == 0){
            allpasspeople = 1;
        }
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String excellentstu = numberFormat.format((float) excellent / (float) allpasspeople * 100) +"%";
        String qualifiedstu = numberFormat.format((float) qualified / (float) allpasspeople * 100) +"%";
        String unqualifiedstu = numberFormat.format((float) unqualified / (float) allpasspeople * 100) +"%";

        model.addAttribute("excellentstu",excellentstu);
        model.addAttribute("qualifiedstu",qualifiedstu);
        model.addAttribute("unqualifiedstu",unqualifiedstu);
        return "home_page/study_situation";
    }

    /**
     * 查看优秀实验报告（详情页）
     * @param id 对应优秀实验报告的id号
     * @param model 存储查询到对应id号的优秀实验报告的内容
     * @return 返回到优秀实验报告的详情页
     */
    @GetMapping(value = "/effectDetails/{id}")
    public String effectDetails(@PathVariable int id,Model model){
        //查询对应id的优秀实验报告的整条数据
        Effect effect = effectService.findById(id);
        model.addAttribute("effect",effect);
        return "home_page/effectDetails";
    }

    /**
     * 后台管理系统页面-->首页-->学习效果页面
     * @param model 储存优秀实验报告所有数据
     * @param pageNum 分页
     * @return 返回到学习效果列表展示页面
     */
    @GetMapping(value = "/list")
    public String LearningeffectList(Model model, @RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum){
        //查询优秀实验报告所有数据
        Pageable pageable = PageRequest.of(pageNum,3);
        Page<Effect> page = effectRepository.findAll(pageable);
        model.addAttribute("learningPageInfo",page);
        return "shouye/student_level";
    }

    /**
     * 点击学习效果页面添加按钮，来到添加页面
     * @return 返回到学习效果添加页面
     */
    @GetMapping(value = "/add")
    public String LearningeffectAdd(){
        return "shouye/study_add";
    }


    /**
     * 学习效果添加页面，完成添加操作
     * @param effect 从前端返回的参数（effect_content,effect_name,effect_person）
     * @param attachs  从前端返回的图片文件
     * @param req  返回的文件信息
     * @return 重定向到学习效果页面
     */
    @PostMapping(value = "/add")
    public String add(Effect effect, @RequestParam("attachs") MultipartFile[] attachs, HttpServletRequest req){
        //设置点击次数和创建时间
        effect.setDic_num(0);
        effect.setDic_datetime(new Date());
        //储存图片，并将图片路径储存到数据库
        String realpath = GetServerRealPathUnit.getPath("static/upload/");
//       System.out.println("realPath:" + realpath);
        for (MultipartFile attach : attachs) {
            if (attach.isEmpty()) {
                continue;
            }
            //图片验证重命名
            String picName = FileUploadUtil.picRename(attach.getContentType());
            String path = realpath + "/" + picName;
//            System.out.println(path);
            File f = new File(path);
//            user.setImg(picName);
            effect.setEffect_imgurl(picName);
            try {
                FileUtils.copyInputStreamToFile(attach.getInputStream(), f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //将信息储存到数据库
        effectService.add(effect);
        return "redirect:/learning/list";
    }

    /**
     * 点击学习效果中修改按钮，进入修改页面
     * @param id 对应需要修改数据的id
     * @param model 储存需要回显到修改页面的数据
     * @return 返回到修改页面
     */
    @GetMapping(value = "/{id}/update")
    public String update(@PathVariable int id, Model model){
        //查询到id所对应的整条数据
        Effect effect = effectService.findById(id);
        model.addAttribute("effect",effect);
        return "shouye/study_update";
    }

    /**
     * 完成修改操作
     * @param id 被修改数据的id
     * @param effect 被修改的数据参数（effect_content,effect_name,effect_person）
     * @param attachs 修改的图片文件
     * @param req 返回的文件信息
     * @return 重定向到学习效果页面
     */
    @PostMapping(value = "/{id}/update")
    public String update(@PathVariable int id,Effect effect,@RequestParam("attachs") MultipartFile[] attachs, HttpServletRequest req){
        effect.setId(id);
        effect.setDic_datetime(new Date());
        //储存图片，并将图片路径储存到数据库
        String realpath = GetServerRealPathUnit.getPath("static/upload/");
//       System.out.println("realPath:" + realpath);
        for (MultipartFile attach : attachs) {
            if (attach.isEmpty()) {
                effect.setEffect_imgurl(effectService.findById(id).getEffect_imgurl());
                continue;
            }
            //图片验证重命名
            String picName = FileUploadUtil.picRename(attach.getContentType());
            String path = realpath + "/" + picName;
//            System.out.println(path);
            File f = new File(path);
//            user.setImg(picName);
            effect.setEffect_imgurl(picName);
            try {
                FileUtils.copyInputStreamToFile(attach.getInputStream(), f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //将信息储存到数据库
        effectService.add(effect);
        System.out.println(effectService.findById(id).toString());
        System.out.println("修改成功");
        return "redirect:/learning/list";
    }

    /**
     * 删除单条优秀实验报告信息
     * @param id 对应优秀实验报告id
     * @return 重定向到学习效果页面
     */
    @GetMapping(value = "/{id}/delete")
    public String delete(@PathVariable int id){
        //执行删除操作
        effectService.delete(id);
        return "redirect:/learning/list";
    }
}
