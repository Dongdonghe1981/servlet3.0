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

//����������ʱ�򣬻Ὣ@HandlesTypesָ����������������ࣨʵ���࣬�ӽӿڵȣ����ݹ���
//��������
@HandlesTypes(value = {HelloService.class})
public class MyServletContainerInitializer implements ServletContainerInitializer {

	@Override
	/**
	    * Ӧ��������ʱ�򣬻����и÷���
	 * Set<Class<?>> arg0���������͵�����������
	 * ServletContext arg1������ǰwebӦ�õ�ServletContext;һ��WebӦ�ö�Ӧһ��ServletContext
	 * 
	 * ʹ��ServletContextע��Web���������Servlet��Filter��Listener��
	 */
	public void onStartup(Set<Class<?>> arg0, ServletContext sc) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("==========interest types");
		for(Class<?> class1: arg0) {
			System.out.println(class1);
		}
		
		//ע�����
		//ע��Servlet
		Dynamic userServlet = sc.addServlet("userService", new UserServlet());
		//����servlet��ӳ����Ϣ
		userServlet.addMapping("/user");
		
		//ע��Listener
		sc.addListener(UserListener.class);
		
		//ע��Filter
		FilterRegistration.Dynamic filterDynamic = sc.addFilter("userFilter", UserFilter.class);
		filterDynamic.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
		
		
		//sc.addFilter(arg0, arg1)	
	}

}
