/**
 * 文件名：NewsInfoController.java
 * 修改人：xxxx
 * 修改时间：
 * 修改内容：新增
 */

package com.coolwen.experimentplatform.controller;
import com.coolwen.experimentplatform.dao.ExpModelRepository;
import com.coolwen.experimentplatform.dao.NewsInfoRepository;
import com.coolwen.experimentplatform.dao.StudentRepository;
import com.coolwen.experimentplatform.dao.TeacherRepository;
import com.coolwen.experimentplatform.model.ExpModel;
import com.coolwen.experimentplatform.model.NewsInfo;
import com.coolwen.experimentplatform.model.SetInfo;
import com.coolwen.experimentplatform.service.NewsInfoService;
import com.coolwen.experimentplatform.service.SetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Date;

/**
*@Description 首页显示的数据，后台管理系统中 首页-->平台公告页面的增删改查
*@Author 朱治汶
*@Version 1.0
*@Date 2020/5/29 19:12
*/
@Controller
@RequestMapping(value = "/newsinfo")
public class NewsInfoController {

    @Autowired
    NewsInfoRepository newsInfoRepository;
    @Autowired
    ExpModelRepository expModelRepository;
    @Autowired
    NewsInfoService newsInfoService;
    @Autowired
    SetInfoService setInfoService;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TeacherRepository teacherRepository;

    /**
     * 进入前端首页的接口
     * @param model 存储需要展示在首页上的数据（虚拟仿真实验列表，推荐实验项目，虚拟实验平台公告，平台统计）
     * @param pageNum 分页
     * @return 进入首前端页
     */
    @GetMapping(value = "/newslist")
    public String newslist(Model model,@RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum){
        //查询所有公告信息
        Pageable pageable = PageRequest.of(pageNum,10);
        Page<NewsInfo> page = newsInfoRepository.findAllorderby(pageable);
        model.addAttribute("newsPageInfo",page);
        //查询所有实验列表
        Pageable pageable1 = PageRequest.of(pageNum,9);
        Page<ExpModel> page1 = expModelRepository.findAllexp(pageable1);
        model.addAttribute("allexp",page1);
        //轮播展示
        SetInfo setInfo = setInfoService.findById(1);
        String ids = setInfo.getSet_rotateimg();
        //数据库中存储为拼接（例：1,2,3,4），拆分后，查询图片存储路径并存入model
        String[] sid =ids.split(",");
        for (int i = 0; i < sid.length ; i++) {
//            String imgurl = setInfoService.findexpimg(Integer.parseInt(sid[i]));
//            String imgurl = expModelRepository.findexpimg(Integer.parseInt(sid[i]));
            try {
                ExpModel expModel = expModelRepository.findById(Integer.parseInt(sid[i])).get();
                model.addAttribute("img"+String.valueOf(i),expModel.getImageurl());
                model.addAttribute("mid"+String.valueOf(i),expModel.getM_id());
            }catch (Exception e){

            }

        }
        //平台统计
        //查询实验模块总数
        int modenum = (int) expModelRepository.count();
        model.addAttribute("modenum",modenum);
        System.out.println(modenum);
        //查询平台总用户数
        int studentnum = (int) studentRepository.count();
        int teachernum = (int) teacherRepository.count();
        model.addAttribute("usernum",studentnum+teachernum);
        //查询参与考核人数
        int studentmodel = newsInfoService.findAllmodelpeople();
        model.addAttribute("studentmodel",studentmodel);
        System.out.println(studentmodel);
        //查询通过考核人数
        int passpeople = newsInfoService.findAllPass();
        model.addAttribute("passpeople",passpeople);
        System.out.println(passpeople);

        //访问量
        // 获取访问量信息
        String txtFilePath = "E://count.txt";
        Long count = Get_Visit_Count(txtFilePath);
        model.addAttribute("count", count);
        return "home_page/index";
    }


    //前端实验大厅入口
    @GetMapping(value = "/shiyan")
    public String model(Model model){
        model.addAttribute("disMid",false);
        return "kuangjia/shiyan";
    }


    //前端首页，点击公告，查看详情
    @GetMapping(value = "/noticeDetails/{id}")
    public String noticedetails(@PathVariable int id,Model model){
        //查询对应id的整条数据
        NewsInfo newsInfo = newsInfoService.findById(id);
        model.addAttribute("newsInfo",newsInfo);
        //设置点击次数+1
        newsInfo.setDic_num(newsInfo.getDic_num()+1);
        //更新数据
        newsInfoService.add(newsInfo);
        return "home_page/noticeDetails";
    }

    //前端首页 点击查看更多
    @GetMapping(value = "more")
    public String more(Model model,@RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum){
        Pageable pageable = PageRequest.of(pageNum,12);
        Page<NewsInfo> page = newsInfoRepository.findAllorderby(pageable);
        model.addAttribute("newsPageInfo",page);
        return "home_page/notice_more";
    }


    //后台管理系统 首页-->平台公告列表
    @GetMapping(value = "/list")
    public String list(Model model,@RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum){
        Pageable pageable = PageRequest.of(pageNum,6);
        Page<NewsInfo> page = newsInfoRepository.findAll(pageable);
        model.addAttribute("newsPageInfo",page);
        return "shouye/notice";
    }

    //后台管理系统页面 进入公告添加
    @GetMapping(value = "/add")
    public String add(){
        return "shouye/notice_add";
    }

    /**
     * 完成公告添加
     * @param newsInfo 存储前端的返回的传值（content，news_name，news_person）
     * @return 重定向到平台公告页面
     */
    @PostMapping(value = "/add")
    public String add(NewsInfo newsInfo){
        //设置时间和点击次数
        newsInfo.setDic_datetime(new Date());
        newsInfo.setDic_num(0);
        //存储到数据库中
        newsInfoService.add(newsInfo);
        return "redirect:/newsinfo/list";
    }

    //进入公告修改
    @GetMapping(value = "/{id}/update")
    public String update(@PathVariable int id,Model model){
        //查询id对应的整条数据，存入model，返回给前端
        NewsInfo newsInfo = newsInfoService.findById(id);
        model.addAttribute("newsInfo",newsInfo);
        return "shouye/notice_update";
    }

    /**
     * 完成公告修改
     * @param id 公告id
     * @param newsInfo 存储前端的返回的传值（content，news_name，news_person）
     * @return 重定向到平台公告页面
     */
    @PostMapping(value = "/{id}/update")
    public String update(@PathVariable int id, NewsInfo newsInfo){
        NewsInfo newsInfo1 = newsInfoService.findById(id);
        newsInfo.setId(id);
        newsInfo.setDic_datetime(newsInfo1.getDic_datetime());
        newsInfo.setDic_num(newsInfo1.getDic_num());
        newsInfoService.add(newsInfo);
        return "redirect:/newsinfo/list";
    }

    //公告删除
    @GetMapping(value = "/{id}/delete")
    public String delete(@PathVariable int id){
        newsInfoService.delete(id);
        return "redirect:/newsinfo/list";
    }


    public static Long Get_Visit_Count(String txtFilePath) {
        try {
            File file = new File(txtFilePath);
            if (!file.exists()){
                file.createNewFile();
                //写入相应的文件
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txtFilePath),"UTF-8"));
                Long count = Long.valueOf("0");
                out.write(String.valueOf(count));
                //清楚缓存
                out.flush();
                //关闭流
                out.close();
            }
            //读取文件(字符流)
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(txtFilePath),"UTF-8"));
            //循环读取数据
            String str = null;
            StringBuffer content = new StringBuffer();
            while ((str = in.readLine()) != null) {
                content.append(str);
            }
            //关闭流
            in.close();
            //System.out.println(content);
            // 解析获取的数据
            Long count = Long.valueOf(content.toString());
            count ++; // 访问量加1
            //写入相应的文件
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txtFilePath),"UTF-8"));
            out.write(String.valueOf(count));
            //清楚缓存
            out.flush();
            //关闭流
            out.close();
            return count;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0L;
        }
    }
}
