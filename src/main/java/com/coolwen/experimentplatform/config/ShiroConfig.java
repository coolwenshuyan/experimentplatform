package com.coolwen.experimentplatform.config;



/**
 * @author CoolWen
 * @version 2018-11-01 7:44
 */
//@Configuration
//@EnableTransactionManagement
public class ShiroConfig {
//
//    protected static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);
//
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
//        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
//        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
//        filterRegistration.setEnabled(true);
//        filterRegistration.addUrlPatterns("/*");
//        return filterRegistration;
//    }
//
//    @Bean("lifecycleBeanPostProcessor")
//    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//    /**
//     * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
//     *
//     * @return
//     */
//    @Bean
//    public ShiroDialect shiroDialect() {
//        return new ShiroDialect();
//    }
//
//    @Bean
//    public ResourceCheckFilter resourceCheckFilter() {
//        ResourceCheckFilter resourceCheckFilter = new ResourceCheckFilter();
//        resourceCheckFilter.setErrorUrl("405");
//        return resourceCheckFilter;
//    }
//
//    @Bean
//    public FilterRegistrationBean delegatingFilterProxy() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
//        proxy.setTargetFilterLifecycle(true);
//        proxy.setTargetBeanName("shiroFilter");
//        filterRegistrationBean.setFilter(proxy);
//        return filterRegistrationBean;
//    }
//
//    /**
//     * cas过滤器
//     *
//     * @param casServerUrlPrefix
//     * @param shiroServerUrlPrefix
//     * @return
//     */
//    @Bean(name = "casFilter")
//    public CasFilter getCasFilter(@Value("${shiro.cas}") String casServerUrlPrefix,
//                                  @Value("${shiro.server}") String shiroServerUrlPrefix) {
//        CasFilter casFilter = new CasFilter();
//        casFilter.setName("casFilter");
//        casFilter.setEnabled(true);
//        String loginUrl = casServerUrlPrefix + "/login?service=" + shiroServerUrlPrefix + casFilterUrlPattern;
//        casFilter.setFailureUrl(loginUrl);
//        return casFilter;
//    }
//
//    /**
//     * ShiroFilterFactoryBean 处理拦截资源文件问题。
//     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
//     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
//     * <p>
//     * Filter Chain定义说明  authc
//     * 1、一个URL可以配置多个Filter，使用逗号分隔
//     * 2、当设置多个过滤器时，全部验证通过，才视为通过
//     * 3、部分过滤器可指定参数，如perms，roles
//     */
//    @Bean("shiroFilter")
//    public ShiroFilterFactoryBean shirFilter(DefaultWebSecurityManager securityManager,
//                                             CasFilter casFilter,
//                                             @Value("${shiro.cas}") String casServerUrlPrefix,
//                                             @Value("${shiro.server}") String shiroServerUrlPrefix) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        String loginUrl = casServerUrlPrefix + "/login?service=" + shiroServerUrlPrefix + casFilterUrlPattern;
////        设置登录地址
//        shiroFilterFactoryBean.setLoginUrl(loginUrl);
////        设置登录成功地址
//        shiroFilterFactoryBean.setSuccessUrl("/index");
//
//        Map<String, Filter> filters = new HashMap<>();
//        filters.put("casFilter", casFilter);
//        LogoutFilter logoutFilter = new LogoutFilter();
//        logoutFilter.setRedirectUrl(casServerUrlPrefix + "/logout?service=" + shiroServerUrlPrefix);
//        filters.put("logout", logoutFilter);
//
//        shiroFilterFactoryBean.setFilters(filters);
//
//        loadShiroFilterChain(shiroFilterFactoryBean);
//
//        shiroFilterFactoryBean.setUnauthorizedUrl("/405");
//        return shiroFilterFactoryBean;
//    }
//
//    /**
//     * 配置shiro权限
//     *
//     * @param shiroFilterFactoryBean
//     */
//    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean) {
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//
//        filterChainDefinitionMap.put(casFilterUrlPattern, "casFilter");
//        filterChainDefinitionMap.put("/logout", "logout");
//        filterChainDefinitionMap.put("/test/**", "anon");
//        filterChainDefinitionMap.put("/**", "authc");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//    }
//
//    //    @Bean
////    public DefaultWebSecurityManager securityManager() {
////        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
////        //设置realm.
////        securityManager.setAuthenticator(modularRealmAuthenticator());
////        List<Realm> realms = new ArrayList<>();
////
////        realms.add(studentRealm());
////        realms.add(adminRealm());
////
////        securityManager.setRealms(realms);
////        // 自定义缓存实现
////        securityManager.setCacheManager(ehCacheManager());
////
////        return securityManager;
////    }
//    private static final String casFilterUrlPattern = "/cas";
//
//    @Bean(name = "securityManager")
//    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Value("${shiro.cas}") String casServerUrlPrefix,
//                                                                  @Value("${shiro.server}") String shiroServerUrlPrefix) {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//
//        CasRealm casRealm = new CasRealm();
//        casRealm.setDefaultRoles("ROLE_USER");
//        casRealm.setCasServerUrlPrefix(casServerUrlPrefix);
//        casRealm.setCasService(shiroServerUrlPrefix + casFilterUrlPattern);
//
//        securityManager.setRealm(casRealm);
//        securityManager.setCacheManager(new MemoryConstrainedCacheManager());
//        securityManager.setSubjectFactory(new CasSubjectFactory());
//        return securityManager;
//    }
//
//    /**
//     * URL 权限匹配器
//     *
//     * @return
//     */
//    @Bean
//    public UrlPermissionResovler urlPermissionResovler() {
//        UrlPermissionResovler urlPermissionResovler = new UrlPermissionResovler();
//        return urlPermissionResovler;
//    }
//
//
//    @Bean
//    public MyShiroRealm myShiroRealm() {
//        MyShiroRealm myShiroRealm = new MyShiroRealm();
//        myShiroRealm.setCachingEnabled(true);
//        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
//        myShiroRealm.setPermissionResolver(urlPermissionResovler());
//        myShiroRealm.setAuthenticationCachingEnabled(true);
//        myShiroRealm.setAuthorizationCachingEnabled(true);
//        myShiroRealm.setAuthenticationCacheName("shiro");
//        myShiroRealm.setAuthorizationCacheName("shiro");
//        myShiroRealm.setCacheManager(ehCacheManager());
//        return myShiroRealm;
//    }
//
//    @Bean
//    public StudentRealm studentRealm() {
//        StudentRealm studentRealm = new StudentRealm();
//        studentRealm.setCachingEnabled(true);
//        studentRealm.setAuthenticationCachingEnabled(true);
//        studentRealm.setAuthorizationCachingEnabled(true);
//        studentRealm.setCacheManager(ehCacheManager());
//        return studentRealm;
//    }
//
//    @Bean
//    public AdminRealm adminRealm() {
//        AdminRealm adminRealm = new AdminRealm();
//        return adminRealm;
//    }
//
////    /**
////     * Realm管理，主要针对多realm
////     */
////    @Bean
////    public ModularRealmAuthenticator modularRealmAuthenticator() {
////        //自己重写的ModularRealmAuthenticator
////        UserModularRealmAuthenticator modularRealmAuthenticator = new UserModularRealmAuthenticator();
////        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
////        return modularRealmAuthenticator;
////    }
//
//
//    /**
//     * 凭证匹配器
//     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
//     * 所以我们需要修改下doGetAuthenticationInfo中的代码;
//     * ）
//     *
//     * @return
//     */
//    @Bean
//    public HashedCredentialsMatcher hashedCredentialsMatcher() {
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//
//        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
//        hashedCredentialsMatcher.setHashIterations(1);//散列的次数，比如散列两次，相当于 md5(md5(""));
//
//        return hashedCredentialsMatcher;
//    }
//
//    /**
//     * 开启shiro aop注解支持.
//     * 使用代理方式;所以需要开启代码支持;
//     *
//     * @param
//     * @return
//     */
//    @Bean
//    @DependsOn("lifecycleBeanPostProcessor")
//    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        advisorAutoProxyCreator.setProxyTargetClass(true);
//        return advisorAutoProxyCreator;
//    }
//
////    @Bean
////    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
////        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
////        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
////        return authorizationAttributeSourceAdvisor;
////    }
//
//
//    /**
//     * ehcache缓存管理器；shiro整合ehcache：
//     * 通过安全管理器：securityManager
//     * 单例的cache防止热部署重启失败
//     *
//     * @return EhCacheManager
//     */
//    @Bean
//    public EhCacheManager ehCacheManager() {
//        logger.debug("=====shiro整合ehcache缓存：ShiroConfiguration.getEhCacheManager()");
//        EhCacheManager ehcache = new EhCacheManager();
////        ehcache.setCacheManager(cacheManager);
//        ehcache.setCacheManagerConfigFile("classpath:config/ehcache.xml");
//     /*   CacheManager cacheManager = CacheManager.getCacheManager("es");
//        if (cacheManager == null) {
//            try {
//
//                cacheManager = CacheManager.create(ResourceUtils
//                        .getInputStreamForPath("classpath:config/ehcache.xml"));
//
//            } catch (CacheException | IOException e) {
//                e.printStackTrace();
//            }
//        }*/
////        ehcache.setCacheManager(cacheManager);
//        return ehcache;
//    }
}
