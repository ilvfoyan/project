package cn.java.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.java.user.User;
import cn.java.userdao.UserDao;

public class LoginServlet extends HttpServlet {
	private static final long SerialVersionUIDAdder = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
        // 获取表单提交过来的数据
        // getParameter()方法可以获取请求的参数信息
        response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");//客户端网页我们控制为UTF-8
		String username=request.getParameter("username");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		UserDao u=new UserDao();
		if(!"".equals(username)){
					try {
						if(!u.findUsername(username)) {
							request.setAttribute("message","该用户不存在！");//其后需要有跳转的页面
							request.getRequestDispatcher("/index.jsp").forward(request, response);
						}else{
							String pwd=request.getParameter("password");
							if(!"".equals(pwd)){
								 try {
									if(u.findPassword(username, pwd)){
										 request.setAttribute("username",username);
									     request.getRequestDispatcher("/homepage.jsp").forward(request, response);
										
									 }else {
										request.setAttribute("message","密码错误！");
									 	request.getRequestDispatcher("/index.jsp").forward(request, response);
									}
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}else {
								request.setAttribute("message","请输入密码！");
								request.getRequestDispatcher("/index.jsp").forward(request, response);
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}else {
				request.setAttribute("message","请输入用户名！");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
        
  }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
