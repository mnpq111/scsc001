package com.itheima.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 购物车添加的Servlet类
 * 
 * @author
 * @version 1.0, 2017-08-22 09:55:15
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 添加到购物车的方法
	 * 
	 * @param request 请求对象
	 * @param response 响应对象
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* 思路分析
		 * 接收商品的名字
		 * 判断cart中有没有含商品信息的map集合，若没有， 创建map集合，key是商品名，value是数量
		 * 如果购物车已经有该商品，则value+1;如果没有，创建新的key，value=1
		 * 将写好的map集合存入session中
		 * 输出到购物车页面(jsp)
		 */
		
		//接收加入购物车的商品(需要解决get方式的中文乱码问题)
		String name = new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
		//创建map集合
		Map<String, Integer> map = (Map<String, Integer>) request.getSession().getAttribute("cart");
		if (map == null) {
			map = new LinkedHashMap<String, Integer>();
		}
		//添加商品信息
		if (map.containsKey(name)) {
			map.put(name, map.get(name)+1);
		}else{
			map.put(name, 1);
		}
		System.out.println(map);
		//写入session
		request.getSession().setAttribute("cart", map);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println("<h3><a href='product/pro.jsp'>继续购物</a></h3> <h3><a href='product/cart.jsp'>结算</a></h3>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
