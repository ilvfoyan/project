package cn.java.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.java.userdao.UserDao;

/**
 * Servlet implementation class RegisterServlet
 */
/*@WebServlet("/RegisterServlet")有这个注释就不用写servlet配置了*/
public class RegisterServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		/*// 获取表单提交过来的数据
        // getParameter()方法可以获取请求的参数信息
        String username = request.getParameter("username");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        PrintWriter pw=response.getWriter();
        
        // 打印获取到的参数信息
        System.out.println("username=" + username);
        System.out.println("password1=" + password1);
        System.out.println("password2=" + password2);
        // 如果usename=admin，password=123则跳转到success.jsp，否则跳转到fail.jsp
        // 1、通过转发实现跳转
        if(username.equals("")) {
        	pw.write("<script language='javascript'>alert('请输入用户名！')</script>");
        	request.setAttribute("message", "请输入用户名！");
        	request.getRequestDispatcher("/register.jsp").forward(request, response);
        }else {
        	if(password1=="" || password2=="") {
        		request.setAttribute("message", "请输入密码！");
            	request.getRequestDispatcher("/register.jsp").forward(request, response);
        	}else {
		        if(!password1.equals(password2)) {
		        	request.setAttribute("message", "两个密码不一致，请重新输入！");
		        	request.getRequestDispatcher("/register.jsp").forward(request, response);
		        } else {
		            // request.getRequestDispatcher("/fail.jsp").forward(request,
		            // response);
		            response.sendRedirect("/imooc_servlet1/fail.jsp");
		        	request.getRequestDispatcher("/index.jsp").forward(request, response);
		        }
        	}
		}*/
		
		
		
		String username = request.getParameter("username");
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		UserDao u=new UserDao();
		
		if(!"".equals(username)){
				try {
					if(u.findUsername(username)) {
						request.setAttribute("message", "用户名已存在！");
						request.getRequestDispatcher("/register.jsp").forward(request, response);
					}else{
						String pwd1 = request.getParameter("password1");
						String pwd2 = request.getParameter("password2");
						if(!"".equals(pwd1) || !"".equals(pwd2)) {
							if(pwd1.equals(pwd2)){
								u.add(username,pwd1);
					        	request.getRequestDispatcher("/index.jsp").forward(request, response);
							}else {
								request.setAttribute("message", "两个密码不一致，请重新输入！");
								request.getRequestDispatcher("/register.jsp").forward(request, response);
							}
						}else {
							request.setAttribute("message", "请输入密码！");
							request.getRequestDispatcher("/register.jsp").forward(request, response);
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		}else {
			request.setAttribute("message", "请输入用户名！");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
