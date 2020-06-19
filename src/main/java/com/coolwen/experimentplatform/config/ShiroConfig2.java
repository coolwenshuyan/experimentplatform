package com.coolwen.experimentplatform.config;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasSubjectFactory;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 单点登录配置 需要单点登录时使用本类
 *
 * @author 小鸟的胖次
 * @version 1.0
 * @date 2020-06-09 11:24
 */
@Configuration
@EnableTransactionManagement
public class ShiroConfig2 {
    private static final String casFilterUrlPattern = "/shiro-cas";


    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

    /**
     * 生命周期管理
     *
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * shiro的SecurityManager
     *
     * @param casServerUrlPrefix
     * @param shiroServerUrlPrefix
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Value("${shiro.cas}") String casServerUrlPrefix,
                                                                  @Value("${shiro.server}") String shiroServerUrlPrefix) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        这是shiro - cas整合包提供的Realm也可以重写这个类完成自定义
        CasRealm casRealm = new CasRealm();
        casRealm.setDefaultRoles("ROLE_USER");
//        设置cas服务器地址
        casRealm.setCasServerUrlPrefix(casServerUrlPrefix);
//        设置cas服务器地址
        casRealm.setCasService(shiroServerUrlPrefix + casFilterUrlPattern);

        securityManager.setRealm(casRealm);
//        缓存
        securityManager.setCacheManager(new MemoryConstrainedCacheManager());
//        照着写的
        securityManager.setSubjectFactory(new CasSubjectFactory());
        return securityManager;
    }

    /**
     * 单独提出一个函数用于对shiro权限过滤的设置
     *
     * @param shiroFilterFactoryBean
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean) {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        filterChainDefinitionMap.put(casFilterUrlPattern, "casFilter");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/test/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }


    /**
     * CasFilter
     *
     * @param casServerUrlPrefix
     * @param shiroServerUrlPrefix
     * @return
     */
    @Bean(name = "casFilter")
    public CasFilter getCasFilter(@Value("${shiro.cas}") String casServerUrlPrefix,
                                  @Value("${shiro.server}") String shiroServerUrlPrefix) {
        CasFilter casFilter = new CasFilter();
        casFilter.setName("casFilter");
        casFilter.setEnabled(true);
//        拼接登录地址
        String loginUrl = casServerUrlPrefix + "/login?service=" + shiroServerUrlPrefix + casFilterUrlPattern;
//        登录成功跳转的地址
        casFilter.setSuccessUrl("/index");
//        登录失败的地址
        casFilter.setFailureUrl(loginUrl);
        return casFilter;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager,
                                                            CasFilter casFilter,
                                                            @Value("${shiro.cas}") String casServerUrlPrefix,
                                                            @Value("${shiro.server}") String shiroServerUrlPrefix) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //        拼接登录地址
        String loginUrl = casServerUrlPrefix + "/login?service=" + shiroServerUrlPrefix + casFilterUrlPattern;
//        设置登录地址
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
//        设置登录成功地址
        shiroFilterFactoryBean.setSuccessUrl("/index");
        Map<String, Filter> filters = new HashMap<>();
        filters.put("casFilter", casFilter);
        LogoutFilter logoutFilter = new LogoutFilter();
//        设置登出
        logoutFilter.setRedirectUrl(casServerUrlPrefix + "/logout?service=" + shiroServerUrlPrefix);

        filters.put("logout", logoutFilter);

        shiroFilterFactoryBean.setFilters(filters);
//        加载其他过滤配置
        loadShiroFilterChain(shiroFilterFactoryBean);
        return shiroFilterFactoryBean;
    }
}
