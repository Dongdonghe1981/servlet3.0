package com.wh.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ������Ŀ��������ֹͣ
 * @author HP
 *
 */
public class UserListener implements ServletContextListener {

	//����ServletContext����
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("UserListener...contextDestroyed..");
		
	}
	
	//����ServletContext������ʼ��
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("UserListener...contextInitialized..");
		
	}

}
