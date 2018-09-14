package cn.java.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.java.jdbc.JDBC;
import cn.java.user.User;

public class UserDao {
	/*//向数据库中添加用户记录的方法add()
	   public void add(User user) throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 try {
			 conn = JDBC.getConnection();
			 String sql = "insert into user  values (?,?) ";
			 ps = conn.prepareStatement(sql);
			 ps.setInt(1, user.getUid());
			 ps.setString(2,user.getUsername());
			 ps.setString(3,user.getPassword());
			 ps.executeUpdate();
		 }finally {JDBC.free(null,ps, conn);}
	   }
	   //修改数据库用户记录的方法update()
	   public void update(User user) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBC.getConnection();
			String sql = "update user set username=?,password=? where uid=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1,user.getUsername());
			ps.setString(2,user.getPassword());
			ps.setInt(3, user.getUid());
			ps.executeUpdate();
		}finally {JDBC.free(null,ps, conn);}
	   }
	   //删除数据库用户记录的方法delete()
	   public void delete(String userId) throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 try {
			conn = JDBC.getConnection();
			String sql = "delete from user where uid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,userId);
			ps.executeUpdate();
		}finally {JDBC.free( null,ps, conn);}
	   }
	   //根据id查询用户的方法findUserById()
	   public User findUserById(int userId) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user=null;
		try {
			conn = JDBC.getConnection();
			String sql = "select * from user where uid=? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs=ps.executeQuery();
			if(rs.next()){
			   user=new User();
			   user.setUid(rs.getInt(1));
			   user.setUsername(rs.getString(2));
			   user.setPassword(rs.getString(3));
			}
		}finally {JDBC.free(rs, ps, conn);}
		return user;
	   }
	   //查询全部用户的方法QueryAll()
	   public List<User> QueryAll() throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> userList=new ArrayList<User>();
		try {
			conn = JDBC.getConnection();
			String sql = "select * from user ";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
			   User user=new User();
			   user.setUid(rs.getInt(1));
			   user.setUsername(rs.getString(2));
			   user.setPassword(rs.getString(3));
			   userList.add(user);
			}
		}finally {JDBC.free(rs, ps, conn);}
		return userList;
	   }*/
	
	
	
	
	public void add(String user,String password) throws ClassNotFoundException{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 try {
			 conn = JDBC.getConnection();
			 String sql = "insert into account values(?,?) ";
			 ps = conn.prepareStatement(sql);
			 ps.setString(1,user);
			 ps.setString(2,password);
			 ps.executeUpdate();
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {JDBC.free(null,ps, conn);}
	   }
	   //修改数据库用户记录的方法update()
	   public void update(User user) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBC.getConnection();
			String sql = "update account set password=? where username=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1,user.getPassword());
			ps.setString(2, user.getUsername());
			ps.executeUpdate();
		}finally {JDBC.free(null,ps, conn);}
	   }
	   //删除数据库用户记录的方法delete()
	   public void delete(String username) throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 try {
			conn = JDBC.getConnection();
			String sql = "delete from account where username=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,username);
			ps.executeUpdate();
		}finally {JDBC.free( null,ps, conn);}
	   }
	   //根据id查询用户的方法findUsername()
	   public boolean findUsername(String username) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBC.getConnection();
			String sql = "select * from account where username=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs=ps.executeQuery();
			if(rs.next())
			   return true;
			else
				return false;
		}finally {JDBC.free(rs, ps, conn);}
	   }
	   
	   
	   public boolean findPassword(String user,String pwd) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBC.getConnection();
			String sql = "select * from account where username=? and password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,user);
			ps.setString(2,pwd);
			rs=ps.executeQuery();
			if(rs.next())
			   return true;
			else
				return false;
		}finally {JDBC.free(rs, ps, conn);}
	   }
	   
	   
	   //查询全部用户的方法QueryAll()
	   public List<User> QueryAll() throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> userList=new ArrayList<User>();
		try {
			conn = JDBC.getConnection();
			String sql = "select * from account";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
			   User user=new User();
			   user.setPassword(rs.getString(1));
			   user.setUsername(rs.getString(2));
			   userList.add(user);
			}
		}finally {JDBC.free(rs, ps, conn);}
		return userList;
	   }
	   
	   
	   public int getPage() throws Exception{
		   Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			int recordCount=0,t1=0,t2=0;
			try {
				conn = JDBC.getConnection();
				String sql = "select count(*) from account";
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
			int recordCount=0,t1=0,t2=0;
			try {
				conn = JDBC.getConnection();
				String sql = "select count(*) from account";
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				rs.next();
				recordCount=rs.getInt(1);
				System.out.println("表格数据个数：" + recordCount);
				t1=recordCount%5;
				t2=recordCount/5;
			}finally {
				JDBC.free(rs, ps, conn);
			}
			int i = (t1==0?t2:t2+1);
			System.out.println("t1：" + t1 + ",t2:" + t2 + ",return:" + i);
			//return t1==0?t2:t2+1;
			return recordCount;
		}
		public List<User> ShowAllUserServlet(int pageNo) throws Exception{
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			int pageSize=5;
			int startRecno=(pageNo-1)*pageSize;
			//ArrayList<User> userList=new ArrayList<User>();
			List<User> userList=new ArrayList<User>();
			try {
				conn = JDBC.getConnection();
				String sql = "select * from account";
				//String sql = "select * from account limit ?,?";
				ps=conn.prepareStatement(sql);
				/*ps.setInt(1,startRecno);
				ps.setInt(2,pageSize);*/
				rs=ps.executeQuery();
				while(rs.next()){
				   User user=new User();
				   user.setUid(rs.getInt(1));
				   user.setUsername(rs.getString(2));
				   user.setPassword(rs.getString(3));
				   userList.add(user);
				   System.out.println("用户id：" + user.getUid() + ",用户名：" + user.getUsername() + "，密码：" + user.getPassword());
				}
				System.out.println("userList数据总数：" + userList.size());
			}finally {
				JDBC.free(rs, ps, conn);
			}
			return userList;
		}
}
