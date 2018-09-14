package cn.java.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.java.user.User;
import cn.java.userdao.UserDao;

/**
 * Servlet implementation class ShowAllUserServlet
 */

public class ShowAllUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAllUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		int pageNo = 1;int pageSize=5;
	    String strPageNo = request.getParameter("pageNo"); 
	    if(strPageNo != null){
	       pageNo = Integer.parseInt(strPageNo); // 把字符串转换成数字
	    }
	    UserDao userDao = new UserDao();
	    try{
	       List<User> userlist = userDao.ShowAllUserServlet(pageNo);
	       request.setAttribute("userlist",userlist);
	       Integer pageCount = new Integer(userDao.getPageCount());
	       request.setAttribute("pageCount",pageCount);
	       request.setAttribute("pageNo",pageNo);
	       System.out.println("总页数pageCount:" + pageCount +",当前页数pageNo:" + pageNo);
	       RequestDispatcher rd = request.getRequestDispatcher("showuser.jsp");
	       rd.forward(request,response); 
	    }catch(Exception e){
	       e.printStackTrace();
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
