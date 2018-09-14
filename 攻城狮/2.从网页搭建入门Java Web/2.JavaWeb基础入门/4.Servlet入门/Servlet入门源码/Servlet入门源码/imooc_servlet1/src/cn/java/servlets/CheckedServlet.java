package cn.java.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.java.check.Check;
import cn.java.checkdao.CheckDao;

/**
 * Servlet implementation class CheckedServlet
 */

public class CheckedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CheckedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String ds = request.getParameter("ds");
		//System.out.println("从jqgrid获取到的id值：" + ds);
		String username = request.getParameter("username");
		//System.out.println("审批用户：" + username);
		Check check = new Check();
		CheckDao checkDao = new CheckDao();
		check.setId(ds);
		check.setApprover(username);
		try {
			checkDao.update(check);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private Object $(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
