package com.coolwen.experimentplatform.realm;

import com.coolwen.experimentplatform.model.ClassModel;
import com.coolwen.experimentplatform.model.Student;
import com.coolwen.experimentplatform.service.ClassService;
import com.coolwen.experimentplatform.service.StudentService;
import com.coolwen.experimentplatform.utils.LoginToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: experimentplatform
 * @Package: com.coolwen.experimentplatform.realm
 * @ClassName: StudentRealm
 * @Author: Txc
 * @Description: 学生权限
 * @Date: 2020/5/15 0015 13:18
 * @Version: 1.0
 */
public class StudentRealm extends AuthorizingRealm {
    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (principals.getPrimaryPrincipal() instanceof Student){
            //拿到当前登录用户
            Student student = (Student) principals.getPrimaryPrincipal();
            //添加角色和权限
            List<String> perminsStrlist = new ArrayList<>();//用户的权限集合
            List<String> roleStrlist=new ArrayList<>();////用户的角色集合
            roleStrlist.add("student");
            if (student.isStuIsinschool()==true){//判断是否在校
                perminsStrlist.add("isSchool");
                if (student.getClassId()!=0){//判断是否分班
                    perminsStrlist.add("inClass");
                    //判断班级是否往期
                    ClassModel classModel= classService.findClassById(student.getClassId());
                    if (classModel!=null){
                        if (classModel.getClassIscurrent()==false){
                            perminsStrlist.add("isCurrent");
                        }
                    }
                }
            }
            simpleAuthorizationInfo.addStringPermissions(perminsStrlist);
            simpleAuthorizationInfo.addRoles(roleStrlist);
        }
        return simpleAuthorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (token.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        LoginToken loginToken = (LoginToken) token;
        Student student = studentService.findByUname(loginToken.getUsername());
        if (student == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("user", student);//成功则放入session
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(student, student.getStuPassword(), getName());
            return simpleAuthenticationInfo;
        }
    }

    @Override
    public boolean supports(AuthenticationToken var1){
        return var1 instanceof LoginToken;
    }
}
