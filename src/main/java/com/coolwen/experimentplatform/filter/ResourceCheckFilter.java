package com.coolwen.experimentplatform.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CoolWen
 * @version 2018-10-31 10:31
 */
//@Component
public class ResourceCheckFilter extends AccessControlFilter {

    protected static final Logger logger = LoggerFactory.getLogger(ResourceCheckFilter.class);


    private String errorUrl;


    public String getErrorUrl() {
        return errorUrl;
    }

    public void setErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl;
    }


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
        Subject subject = getSubject(request, response);
        String url = getPathWithinApplication(request);
        return subject.isPermitted(url);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse hsp = (HttpServletResponse) response;
        HttpServletRequest hReq = (HttpServletRequest) request;
        hsp.sendRedirect(hReq.getContextPath() + "/" + this.errorUrl);
        return false;

    }
}
