package com.thinkgem.jeesite.common.web;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.thinkgem.jeesite.common.annotation.SameUrlData;
import com.thinkgem.jeesite.common.mapper.JsonMapper;  
  

public class SameUrlDataInterceptor  extends HandlerInterceptorAdapter{  
      
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			SameUrlData annotation = method.getAnnotation(SameUrlData.class);
			if (annotation != null) {
				if (repeatDataValidator(request)) {
					response.setHeader("Content-type", "text/html;charset=UTF-8");
			        response.setCharacterEncoding("utf-8");
					PrintWriter out = response.getWriter();
		            out.print("请不要重复提交表单");
		            out.flush();
		            out.close();
					return false;
				} else {
					return true;
				}

			}
			return true;
		} else {
			return super.preHandle(request, response, handler);
		}
	}

    /** 
     * 验证同一个url数据是否相同提交  ,相同返回true 
     * @param httpServletRequest 
     * @return 
     */  
    public boolean repeatDataValidator(HttpServletRequest httpServletRequest)  
    {  
        String params=JsonMapper.toJsonString(httpServletRequest.getParameterMap());  
        String url=httpServletRequest.getRequestURI();  
        Map<String,String> map=new HashMap<String,String>();  
        map.put(url, params);  
        String nowUrlParams=map.toString();//  
          
        Object preUrlParams=httpServletRequest.getSession().getAttribute("repeatData");  
        if(preUrlParams==null)//如果上一个数据为null,表示还没有访问页面  
        {  
            httpServletRequest.getSession().setAttribute("repeatData", nowUrlParams);  
            return false;  
        }  
        else//否则，已经访问过页面  
        {  
            if(preUrlParams.toString().equals(nowUrlParams))//如果上次url+数据和本次url+数据相同，则表示城府添加数据  
            {  
                  
                return true;  
            }  
            else//如果上次 url+数据 和本次url加数据不同，则不是重复提交  
            {  
                httpServletRequest.getSession().setAttribute("repeatData", nowUrlParams);  
                return false;  
            }  
              
        }  
    }  
  
}  