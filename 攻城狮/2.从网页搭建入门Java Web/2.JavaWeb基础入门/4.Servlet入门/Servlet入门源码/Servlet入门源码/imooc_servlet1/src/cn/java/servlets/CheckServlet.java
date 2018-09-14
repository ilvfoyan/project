package cn.java.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.java.check.Check;
import cn.java.checkdao.CheckDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class CheckServlet
 */

public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			JSONArray rows = new JSONArray();
	       
			
		    
			
		    int pageNo = 1;int pageSize=5;
		    String strPageNo = request.getParameter("pageNo"); 
		    if(strPageNo != null){
		       pageNo = Integer.parseInt(strPageNo); // 把字符串转换成数字
		    }
	       
		        	CheckDao checkdao = new CheckDao();
		        	Check check = new Check();
			        List<Check> checklist=new ArrayList<Check>();
			        
					try {
						checklist = checkdao.ShowAllDataServlet();
						
					}catch (Exception e) {
						// TODO: handle exception
						System.out.println("111抛出异常！");
					}
				try {
			        JSONObject jsonObj = new JSONObject();
			        
			        int j=checkdao.getPageCount();
			        int onerows=Integer.parseInt(request.getParameter("rows"));//获取所选择的一页数据量
			        //System.out.println("onerows：" + onerows);
			        int page=Integer.parseInt(request.getParameter("page"));//获取当前的页码
			        int t1 = j % onerows;
			        int t2 = j / onerows;
			        int pagecount = (t1==0?t2:t2+1);
				    jsonObj.put("page",page);                // 当前页码
				    //System.out.println("当前页码page：" + page);
			        jsonObj.put("total", pagecount);// 总页数
			        //System.out.println("总页数pagecount：" + pagecount);
			        jsonObj.put("records", j);// 总记录数
			        //System.out.println("总记录数j：" + j);
			        //System.out.println("i的循环终止条件：" + Math.min(checkdao.getPageCount(),page*onerows));
			        // 将rows放入json对象中  
			        for(int i=(page-1)*onerows;i<Math.min(checkdao.getPageCount(),page*onerows);i++){//设置选择下一页
			        	check = checklist.get(i);
			        	JSONObject cell = new JSONObject();
			        	
					    cell.put("id",check.getId());
					    cell.put("pname",check.getPname());
					    cell.put("num",check.getNum());
					    cell.put("spec",check.getSpec());
					    cell.put("amount",check.getAmount());
					    cell.put("price",check.getPrice());
					    cell.put("rate",check.getRate());
					    cell.put("payway",check.getPayway());
					    cell.put("supname",check.getSupname());
					    cell.put("remark",check.getRemark());
					    cell.put("status",check.getStatus());
					    cell.put("date",check.getDate());
					    cell.put("approver",check.getApprover());
					    cell.put("purchaser",check.getPurchaser());
					    // 将该记录放入rows中  
					    rows.add(cell);  
					}
					jsonObj.put("rows", rows); 
			          
			        // 自控制台打印输出，以检验json对象生成是否正确  
			        //System.out.println("要返回的json对象：" + jsonObj.toString());  
			          
			        // 设置字符编码  
			        response.setCharacterEncoding("UTF-8");  
			        // 返回json对象（通过PrintWriter输出）  
			        response.getWriter().print(jsonObj); 
				}catch (Exception e) {
					// TODO: handle exception
					System.out.println("222抛出异常！！！");
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
