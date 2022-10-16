package DB;

import java.sql.*;

public class TrainSQL {
	
	private Connection conn = null;
	private Statement sth = null;
	
	public TrainSQL() throws SQLException {
		conn = DriverManager.getConnection(DBconfig.url,DBconfig.user,DBconfig.pwd);
		
		
	}
	
	public void inser(String station) throws SQLException{
		String sql = "insert into twtrain(name) values('"+station+"')";
		sth = conn.createStatement();
		sth.execute(sql);
	}
	
	public void update(int id, String station) throws SQLException{
		String sql = "update twtrain set name = '"+station+"' where id = "+id;
		sth = conn.createStatement();
		sth.executeUpdate(sql);
	}
	
	public void delete(int id) throws Exception{
		String sql = "delete from twtrain where id = "+id;
		sth = conn.createStatement();
		sth.executeUpdate(sql);
	}
	
	public ResultSet queryAll() throws Exception{
		String sql = "select * from twtrain";
		sth = conn.createStatement();
		sth.executeQuery(sql);
		ResultSet rs = sth.getResultSet();
		return rs;
	}
	
	public boolean Login(String acc, String pwd) throws SQLException{
		pwd = AccountMD5.getMD5(pwd);
		
		String sql ="select * from auth where account='"+acc+"' and password = '"+pwd+"'";
		sth = conn.createStatement();
		sth.executeQuery(sql);
		ResultSet rs = sth.getResultSet();
		if(rs.next()) {
			return true; 
		}else {
			return false;
		}
	}
	
	public void updatePwd(String acc,String pwd)throws SQLException {
		pwd = AccountMD5.getMD5(pwd);
		String sql ="update auth set password='"+pwd+"' where account = '"+acc+"'";
		sth = conn.createStatement();
		sth.executeUpdate(sql);
		
	}
	
	public void addUser(String acc, String pwd) throws SQLException{
		pwd = AccountMD5.getMD5(pwd);
		String sql = "select * from auth where account = '"+acc+"'";
		sth = conn.createStatement();
		sth.executeQuery(sql);
		ResultSet rs = sth.getResultSet();
		if(!rs.next()) {
			sql = "insert into auth(account,password) values('"+acc+"','"+pwd+"')";
			sth = conn.createStatement();
			sth.execute(sql);
		}
	}
}
