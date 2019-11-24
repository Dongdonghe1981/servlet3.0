package com.wh.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		System.out.println(Thread.currentThread() + "start...");
		try {
			sayHello();
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().write("hello");
		System.out.println(Thread.currentThread() + "end...");
	}
	
	public void sayHello() throws InterruptedException {
		System.out.println(Thread.currentThread() + "processing...");
		Thread.sleep(3000);
	}
}
