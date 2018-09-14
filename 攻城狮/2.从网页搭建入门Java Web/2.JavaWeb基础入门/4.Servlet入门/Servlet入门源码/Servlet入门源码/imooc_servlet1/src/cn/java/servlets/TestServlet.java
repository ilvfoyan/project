package cn.java.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.java.user.User;
import cn.java.userdao.UserDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
//import org.json.JSONObject;

/**
 * Servlet implementation class TestServlet
 */

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 定义返回的数据类型：json，使用了json-lib
		
        // 根据jqGrid对JSON的数据格式要求给jsonObj赋值
        
        // 定义rows，存放数据
       JSONArray rows = new JSONArray();
	       
       int pageNo = 1;int pageSize=5;
	    String strPageNo = request.getParameter("pageNo"); 
	    if(strPageNo != null){
	       pageNo = Integer.parseInt(strPageNo); // 把字符串转换成数字
	    }
       
	        	UserDao userdao = new UserDao();
	        	User user = new User();
		        List<User> userList=new ArrayList<User>();
				try {
					userList = userdao.ShowAllUserServlet(1);
					
					 /*request.setAttribute("userlist",userList);
				       Integer pageCount = new Integer(userdao.getPage());
				       request.setAttribute("pageCount",pageCount);
				       request.setAttribute("pageNo",pageNo);
				       RequestDispatcher rd = request.getRequestDispatcher("userlist.jsp");
				       rd.forward(request,response); */
					
					
					for(int i=0;i<userdao.getPageCount();i++) {
						JSONObject cell = new JSONObject();
						user = userList.get(i);
					    cell.put("id",user.getUid());
					    cell.put("username",user.getUsername());
					    cell.put("password",user.getPassword());
					    // 将该记录放入rows中  
					    rows.add(cell);  
					}
				}catch (Exception e) {
					// TODO: handle exception
				}
				
				
	       
		     
			try {
		        JSONObject jsonObj = new JSONObject();
		        jsonObj.put("page", 1);                // 当前页
		        jsonObj.put("total", userdao.getPage());        // 总页数,应该动态设置
		        jsonObj.put("records", userdao.getPageCount());        // 总记录数，应该动态设置
		        // 将rows放入json对象中  
		        jsonObj.put("rows", rows);  
		          
		        // 自控制台打印输出，以检验json对象生成是否正确  
		        System.out.println("要返回的json对象：" + jsonObj.toString());  
		          
		        // 设置字符编码  
		        response.setCharacterEncoding("UTF-8");  
		        // 返回json对象（通过PrintWriter输出）  
		        response.getWriter().print(jsonObj); 
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println("222抛出异常！！！");
			}
        
        
        
        
		/*response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数  
        String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数  
        String sidx = request.getParameter("sidx"); //取得排序字段
        String sord  = request.getParameter("sord");//排序方式asc、desc 
		PrintWriter out = response.getWriter();
		List Alllist=PersonInfoDao.getInstance().getAllPersonInfo();
		List list=PersonInfoDao.getInstance().getPersonInfo(Integer.parseInt(page),Integer.parseInt(rows),sidx,sord);
		int records=Alllist.size();
		
		// 定义返回的数据类型：json，使用了json-lib  
        JSONObject jsonObj = new JSONObject();  
 
        // 根据jqGrid对JSON的数据格式要求给jsonObj赋值  
        jsonObj.put("page", page);     // 当前页  
        int total=(records%Integer.valueOf(rows)==0)?records/Integer.valueOf(rows):(records/Integer.valueOf(rows)+1);
        jsonObj.put("total", total);    // 总页数  
        jsonObj.put("records", records);  // 总记录数  
		int i=0;
		 // 定义rows，存放数据  
        JSONArray json = new JSONArray();  
		Iterator it=list.iterator();
		while(it.hasNext()){
			PersonInfo p=(PersonInfo) it.next();
			// 存放一条记录的对象  
            JSONObject cell = new JSONObject();  
            cell.put("id", ((Integer.parseInt(page)-1)*Integer.parseInt(rows)+i));
            cell.put("pName", p.getpName());
            cell.put("type", p.getType().getPersonType());
            if(p.getpSex().equals("m")){
            	cell.put("pSex","男");
            }else{
            	cell.put("pSex","女");
            }
            cell.put("pAge", p.getpAge());
            cell.put("pMobileNo", p.getpMobileNo());
            cell.put("email", p.getEmail());
            i++;
            if(i==10){
            	i=0;
            }
         // 将该记录放入rows中  
            json.add(cell);
		}
		// 将rows放入json对象中  
		jsonObj.put("rows", json);
		
		//System.out.println(list);
		//JSONArray json=JSONArray.fromObject(list.toArray());
		//System.out.println(json.toString());
		out.print(jsonObj);
		*/
		
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
