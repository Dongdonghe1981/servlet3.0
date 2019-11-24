package com.wh.servlet;

import java.util.EnumSet;
import java.util.Set;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.annotation.HandlesTypes;

import com.wh.service.HelloService;

//容器启动的时候，会将@HandlesTypes指定的类型下面的子类（实现类，子接口等）传递过来
//传入类型
@HandlesTypes(value = {HelloService.class})
public class MyServletContainerInitializer implements ServletContainerInitializer {

	@Override
	/**
	    * 应用启动的时候，会运行该方法
	 * Set<Class<?>> arg0：传入类型的所有子类型
	 * ServletContext arg1：代表当前web应用的ServletContext;一个Web应用对应一个ServletContext
	 * 
	 * 使用ServletContext注册Web三大组件（Servlet，Filter，Listener）
	 */
	public void onStartup(Set<Class<?>> arg0, ServletContext sc) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("==========interest types");
		for(Class<?> class1: arg0) {
			System.out.println(class1);
		}
		
		//注册组件
		//注册Servlet
		Dynamic userServlet = sc.addServlet("userService", new UserServlet());
		//配置servlet的映射信息
		userServlet.addMapping("/user");
		
		//注册Listener
		sc.addListener(UserListener.class);
		
		//注册Filter
		FilterRegistration.Dynamic filterDynamic = sc.addFilter("userFilter", UserFilter.class);
		filterDynamic.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
		
		
		//sc.addFilter(arg0, arg1)	
	}

}
