package com.wh.servlet;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(value = "/async",asyncSupported = true)
public class HelloAsyncServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.支持异步处理
		//2开启异步模式
		System.out.println("main thread start"+Thread.currentThread());
		AsyncContext startAsync = req.startAsync();
		//3业务逻辑进行异步处理
		startAsync.start(() -> {
			System.out.println("sub thread start"+Thread.currentThread());
			try {
				
				sayHello();
				//获取到异步的上下文
				AsyncContext asyncContext = req.getAsyncContext();
				ServletResponse response = asyncContext.getResponse();
			
				response.getWriter().write("hello async ...");
				startAsync.complete();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println("sub thread end"+Thread.currentThread());
		});
		System.out.println("main thread end"+Thread.currentThread());
	}
	
	public void sayHello() {
		System.out.println(Thread.currentThread() + "processing...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
