package cn.java.checkdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


import cn.java.check.Check;
import cn.java.jdbc.JDBC;

public class CheckDao {
	
	public List<Check> check(ResultSet rs,PreparedStatement ps,Connection conn) throws SQLException {
		List<Check> checklist = new ArrayList<Check>();
		conn = JDBC.getConnection();
		rs=ps.executeQuery();
		//Check check= new Check();
		while(rs.next()) {
		   Check check= new Check();//在每次循环时候必须要重新定义check对象，不然就会导致check对象重复内容了
		   check.setId(rs.getString(1));
		   check.setPname(rs.getString(2));
		   check.setNum(rs.getString(3));
		   check.setSpec(rs.getString(4));
		   check.setAmount(rs.getString(5));
		   check.setPrice(rs.getString(6));
		   check.setRate(rs.getString(7));
		   check.setPayway(rs.getString(8));
		   check.setSupname(rs.getString(9));
		   check.setRemark(rs.getString(10));
		   check.setStatus(rs.getString(11));
		   check.setDate(rs.getString(12));
		   check.setApprover(rs.getString(13));
		   check.setPurchaser(rs.getString(14));
		   checklist.add(check);
		}
		return checklist;
	}
	
	
	public List<Check> check(String sql,int i,String s[]) throws SQLException {
		List<Check> checklist = new ArrayList<Check>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = JDBC.getConnection();
		ps = conn.prepareStatement(sql);
		for(int j=0;j<i;j++) {
			ps.setString(j+1,s[j]);
		}
		rs=ps.executeQuery();
		Check check= new Check();
		while(rs.next()) {
		   //Check check= new Check();//在每次循环时候必须要重新定义check对象，不然就会导致check对象重复内容了
		   check.setId(rs.getString(1));
		   check.setPname(rs.getString(2));
		   check.setNum(rs.getString(3));
		   check.setSpec(rs.getString(4));
		   check.setAmount(rs.getString(5));
		   check.setPrice(rs.getString(6));
		   check.setRate(rs.getString(7));
		   check.setPayway(rs.getString(8));
		   check.setSupname(rs.getString(9));
		   check.setRemark(rs.getString(10));
		   check.setStatus(rs.getString(11));
		   check.setDate(rs.getString(12));
		   check.setApprover(rs.getString(13));
		   check.setPurchaser(rs.getString(14));
		   checklist.add(check);
		}
		return checklist;
	}
	
	/*public void docheck(ResultSet rs,PreparedStatement ps,Connection conn,String sql) {
		ps = conn.prepareStatement(sql);
		ps.setString(1,"已审批");
		checklist = check(rs,ps,conn);
	}*/
	
	
	//修改数据库用户记录的方法update()
	   public void update(Check check) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBC.getConnection();
			String sql = "update purchases set status=?,approver=? where id=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1,"已审批");
			ps.setString(2,check.getApprover());
			ps.setString(3,check.getId());
			ps.executeUpdate();
		}finally {JDBC.free(null,ps, conn);}
	   }
	
	 //根据id查询用户的方法findData()
	   public List<Check> findData(String dataid,String datanum,String datadate,String datachoose) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CheckDao checkdao = new CheckDao();
		List<Check> checklist = new ArrayList<Check>();
		String s[] = null;
		try {
			conn = JDBC.getConnection();
			//针对查询选择进行筛选信息
			if(dataid == ""){
				if(datanum == ""){
					if(datadate == ""){
						if(datachoose == ""){
							System.out.println("出错1！");
							//弹出错误窗口
							//JOptionPane.showMessageDialog(null, "请选择查询条件！", "查询错误提示", JOptionPane.ERROR_MESSAGE);
							System.out.println("出错2！");
							String sql = "select * from purchases";
							ps = conn.prepareStatement(sql);
							rs=ps.executeQuery();
							checklist = checkdao.ShowAllDataServlet();
						}else if(datachoose.equals("已审批")){
							String sql = "select * from purchases where status=?";
							ps = conn.prepareStatement(sql);
							ps.setString(1,"已审批");
							checklist = check(rs,ps,conn);
							/*s[0] = "已审批";
							checklist = check(sql,1,s);*/
						}else if(datachoose.equals("未审批")){
							String sql = "select * from purchases where status=?";
							ps = conn.prepareStatement(sql);
							ps.setString(1,"未审批");
							checklist = check(rs,ps,conn);
							/*s[0] = "未审批";
							checklist = check(sql,1,s);*/
						}else if(datachoose.equals("全部")) {
							String sql = "select * from purchases";
							ps = conn.prepareStatement(sql);
							rs=ps.executeQuery();
							checklist = checkdao.ShowAllDataServlet();
						}	
					}else{
						if(datachoose == ""){
							String sql = "select * from purchases where date=?";
							ps = conn.prepareStatement(sql);
							ps.setString(1, datadate);
							checklist = check(rs,ps,conn);
							/*s[0] = datadate;
							checklist = check(sql,1,s);*/
						}else if(datachoose.equals("已审批")){
							String sql = "select * from purchases where status=? and date=?";
							ps = conn.prepareStatement(sql);
							ps.setString(1,"已审批");
							ps.setString(2,datadate);
							checklist = check(rs,ps,conn);
							/*s[0]="已审批";
							s[1]=datadate;
							checklist = check(sql,2,s);*/
						}else if(datachoose.equals("未审批")){
							String sql = "select * from purchases where status=? and date=?";
							ps = conn.prepareStatement(sql);
							ps.setString(1,"未审批");
							ps.setString(2,datadate);
							checklist = check(rs,ps,conn);	
							/*s[0]="未审批";
							s[1]=datadate;
							checklist = check(sql,2,s);*/
						}else if(datachoose.equals("全部")) {
							String sql = "select * from purchases";
							ps = conn.prepareStatement(sql);
							rs=ps.executeQuery();
							checklist = checkdao.ShowAllDataServlet();
						}
					}
				}else{
					if(datadate == ""){
						if(datachoose == ""){
							String sql = "select * from purchases where num=?";
							ps = conn.prepareStatement(sql);
							ps.setString(1, datanum);
							checklist = check(rs,ps,conn);
							/*s[0]=datanum;
							checklist = check(sql,1,s);*/
						}else if(datachoose.equals("已审批")){
							String sql = "select * from purchases where status=? and num=?";
							ps = conn.prepareStatement(sql);
							ps.setString(1,"已审批");
							ps.setString(2,datanum);
							checklist = check(rs,ps,conn);
							/*s[0]="已审批";
							s[1]=datanum;
							checklist = check(sql,2,s);*/
						}else if(datachoose.equals("未审批")){
							String sql = "select * from purchases where status=? and num=?";
							ps = conn.prepareStatement(sql);
							ps.setString(1,"未审批");
							ps.setString(2,datanum);
							checklist = check(rs,ps,conn);
							/*s[0]="未审批";
							s[1]=datadate;
							checklist = check(sql,2,s);*/
						}else if(datachoose.equals("全部")) {
							String sql = "select * from purchases";
							ps = conn.prepareStatement(sql);
							rs=ps.executeQuery();
							checklist = checkdao.ShowAllDataServlet();
						}	
					}else{
						if(datachoose == ""){
							String sql = "select * from purchases where date=? and num=?";
							ps = conn.prepareStatement(sql);
							ps.setString(1, datadate);
							ps.setString(2,datanum);
							checklist = check(rs,ps,conn);
							/*s[0]=datadate;
							s[1]=datanum;
							checklist = check(sql,2,s);*/
						}else if(datachoose.equals("已审批")){
							String sql = "select * from purchases where status=? and date=? and num=?";
							ps = conn.prepareStatement(sql);
							ps.setString(1,"已审批");
							ps.setString(2,datadate);
							ps.setString(3,datanum);
							checklist = check(rs,ps,conn);
							/*s[0]="已审批";
							s[1]=datadate;
							s[2]=datanum;
							checklist = check(sql,3,s);*/
						}else if(datachoose.equals("未审批")){
							String sql = "select * from purchases where status=? and date=? and num=?";
							ps = conn.prepareStatement(sql);
							ps.setString(1,"未审批");
							ps.setString(2,datadate);
							ps.setString(3,datanum);
							checklist = check(rs,ps,conn);
							/*s[0]="未审批";
							s[1]=datadate;
							s[2]=datanum;
							checklist = check(sql,3,s);*/
						}else if(datachoose.equals("全部")) {
							String sql = "select * from purchases";
							ps = conn.prepareStatement(sql);
							rs=ps.executeQuery();
							checklist = checkdao.ShowAllDataServlet();
						}
					}
				}
			}else{
				if(datanum == ""){
					if(datadate == ""){
						if(datachoose == ""){
							String sql = "select * from purchases where id=?";
							ps = conn.prepareStatement(sql);
							ps.setString(1, dataid);
							checklist = check(rs,ps,conn);
							/*s[0]=dataid;
							checklist = check(sql,1,s);*/
						}else if(datachoose.equals("已审批")){
							String sql = "select * from purchases where status=? and id=?";
							ps = conn.prepareStatement(sql);
							ps.setString(1,"已审批");
							ps.setString(2,dataid);
							checklist = check(rs,ps,conn);
							/*s[0]="已审批";
							s[1]=dataid;
							checklist = check(sql,2,s);*/
						}else if(datachoose.equals("未审批")){
							String sql = "select * from purchases where status=? and id=?";
							ps = conn.prepareStatement(sql);
							ps.setString(1,"未审批");
							ps.setString(2,dataid);
							checklist = check(rs,ps,conn);
							/*s[0]="未审批";
							s[1]=dataid;
							checklist = check(sql,2,s);*/
						}else if(datachoose.equals("全部")) {
							String sql = "select * from purchases";
							ps = conn.prepareStatement(sql);
							rs=ps.executeQuery();
							checklist = checkdao.ShowAllDataServlet();
						}	
					}else{
						if(datachoose == ""){
							String sql = "select * from purchases where date=? and id=?";
							ps = conn.prepareStatement(sql);
							ps.setString(1, datadate);
							ps.setString(2,dataid);
							checklist = check(rs,ps,conn);
							/*s[0]=datadate;
							s[1]=dataid;
							checklist = check(sql,2,s);*/
						}else if(datachoose.equals("已审批")){
							String sql = "select * from purchases where status=? and date=? and id=?";
							ps = conn.prepareStatement(sql);
							ps.setString(1,"已审批");
							ps.setString(2,datadate);
							ps.setString(3,dataid);
							checklist = check(rs,ps,conn);
							/*s[0]="已审批";
							s[1]=datadate;
							s[3]=dataid;
							checklist = check(sql,3,s);*/
						}else if(datachoose.equals("未审批")){
							String sql = "select * from purchases where status=? and date=? and id=?";
							ps = conn.prepareStatement(sql);
							ps.setString(1,"未审批");
							ps.setString(2,datadate);
							ps.setString(3,dataid);
							checklist = check(rs,ps,conn);
							/*s[0]="未审批";
							s[1]=datadate;
							checklist = check(sql,3,s);*/
						}else if(datachoose.equals("全部")) {
							String sql = "select * from purchases";
							ps = conn.prepareStatement(sql);
							rs=ps.executeQuery();
							checklist = checkdao.ShowAllDataServlet();
						}
					}
				}else{
					if(datadate == ""){
						if(datachoose == ""){
							String sql = "select * from purchases where num=? and id=?";
							ps = conn.prepareStatement(sql);
							ps.setString(1, datanum);
							ps.setString(2,dataid);
							checklist = check(rs,ps,conn);
							/*s[0]=datanum;
							s[1]=dataid;
							checklist = check(sql,2,s);*/
						}else if(datachoose.equals("已审批")){
							String sql = "select * from purchases where status=? and num=? and id=?";
							ps = conn.prepareStatement(sql);
							ps.setString(1,"已审批");
							ps.setString(2,datanum);
							ps.setString(3,dataid);
							checklist = check(rs,ps,conn);
							/*s[0]="已审批";
							s[1]=datanum;
							s[2]=dataid;
							checklist = check(sql,3,s);*/
						}else if(datachoose.equals("未审批")){
							String sql = "select * from purchases where status=? and num=? and id=?";
							ps = conn.prepareStatement(sql);
							ps.setString(1,"未审批");
							ps.setString(2,datanum);
							ps.setString(3,dataid);
							checklist = check(rs,ps,conn);
							/*s[0]="未审批";
							s[1]=datanum;
							s[2]=dataid;
							checklist = check(sql,3,s);*/
						}else if(datachoose.equals("全部")) {
							String sql = "select * from purchases";
							ps = conn.prepareStatement(sql);
							rs=ps.executeQuery();
							checklist = checkdao.ShowAllDataServlet();
						}	
					}else{
						if(datachoose == ""){
							String sql = "select * from purchases where date=? and num=? and id=?";
							ps = conn.prepareStatement(sql);
							ps.setString(1, datadate);
							ps.setString(2,datanum);
							ps.setString(3,dataid);
							checklist = check(rs,ps,conn);
							/*s[0]=datadate;
							s[1]=datanum;
							s[2]=dataid;
							checklist = check(sql,3,s);*/
						}else if(datachoose.equals("已审批")){
							String sql = "select * from purchases where status=? and date=? and num=? and id=?";
							ps = conn.prepareStatement(sql);
							ps.setString(1,"已审批");
							ps.setString(2,datadate);
							ps.setString(3,datanum);
							ps.setString(4,dataid);
							checklist = check(rs,ps,conn);
							/*s[0]="已审批";
							s[1]=datadate;
							s[2]=datanum;
							s[3]=dataid;
							checklist = check(sql,4,s);*/
						}else if(datachoose.equals("未审批")){
							String sql = "select * from purchases where status=? and date=? and num=? and id=?";
							ps = conn.prepareStatement(sql);
							ps.setString(1,"未审批");
							ps.setString(2,datadate);
							ps.setString(3,datanum);
							ps.setString(4,dataid);
							checklist = check(rs,ps,conn);
							/*s[0]="未审批";
							s[1]=datadate;
							s[2]=datanum;
							s[3]=dataid;
							checklist = check(sql,4,s);*/
						}else if(datachoose.equals("全部")) {
							String sql = "select * from purchases";
							ps = conn.prepareStatement(sql);
							rs=ps.executeQuery();
							checklist = checkdao.ShowAllDataServlet();
						}
					}
				}
			}
			
			
		}finally {JDBC.free(rs, ps, conn);}
			return checklist;
	   }
	   
	public int getPage() throws Exception{
		   	Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			int recordCount=0,t1=0,t2=0;
			try {
				conn = JDBC.getConnection();
				String sql = "select count(*) from purchases";
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				rs.next();
				recordCount=rs.getInt(1);
				t1=recordCount%10;
				t2=recordCount/10;
			}finally {
				JDBC.free(rs, ps, conn);
			}
			return t1==0?t2:t2+1;//返回的是总页数
		}
	   
	   
	   
	   
	   public int getPageCount() throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			int recordCount=0;
			try {
				conn = JDBC.getConnection();
				String sql = "select count(*) from purchases";
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				rs.next();
				recordCount=rs.getInt(1);
				//System.out.println("表格数据个数：" + recordCount);
			}finally {
				JDBC.free(rs, ps, conn);
			}
			return recordCount;
		}
		public List<Check> ShowAllDataServlet() throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<Check> checklist=new ArrayList<Check>();
			try {
				conn = JDBC.getConnection();
				String sql = "select * from purchases";
				ps=conn.prepareStatement(sql);
				//checklist = check(rs,ps,conn);
				rs=ps.executeQuery();
				
				while(rs.next()) {
				   Check check= new Check();//在每次循环时候必须要重新定义check对象，不然就会导致check对象重复内容了
				   check.setId(rs.getString(1));
				   check.setPname(rs.getString(2));
				   check.setNum(rs.getString(3));
				   check.setSpec(rs.getString(4));
				   check.setAmount(rs.getString(5));
				   check.setPrice(rs.getString(6));
				   check.setRate(rs.getString(7));
				   check.setPayway(rs.getString(8));
				   check.setSupname(rs.getString(9));
				   check.setRemark(rs.getString(10));
				   check.setStatus(rs.getString(11));
				   check.setDate(rs.getString(12));
				   check.setApprover(rs.getString(13));
				   check.setPurchaser(rs.getString(14));
				   checklist.add(check);
				}
				
			}finally {
				JDBC.free(rs, ps, conn);
			}
			return checklist;
		}
}
