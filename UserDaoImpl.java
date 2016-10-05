/**
 * 
 */
package com.userdemo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.userdemo.model.User;
/**
 * @author shivraj-patil
 *
 */
public class UserDaoImpl implements IUserDao{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(User user) {
		String queryInsert = "insert into USERSTAB (id, first_name, last_name, age) values (?,?,?,?)";
		Connection con = null;
		Statement stmt = null;
		Statement selectStmt = null;
		PreparedStatement ps = null;
		try{
			con = dataSource.getConnection();
			con.setAutoCommit(true);
			System.out.println("Opened database successfully");

			stmt = con.createStatement();


			selectStmt = con.createStatement();
			ResultSet selectRs = selectStmt.executeQuery("select max(id) as id from USERSTAB");

			if(selectRs!=null){
				System.out.println("User data exists::");
				while(selectRs.next()){
					System.out.println("Set up new id for new insertion");
					user.setId(selectRs.getInt("id")+1);
				}
			}else{
				System.out.println("No User data found in USERSTAB table");
			}
		}catch(SQLException e){
			System.out.println("No USERSTAB table exists!");

			try{
				stmt.executeUpdate("create table USERSTAB (ID,FIRST_NAME, LAST_NAME, AGE)");
				System.out.println("table USERSTAB  created !");
			}catch(SQLException e3){
				System.out.println("No USERSTAB table exists!");
			}

		}
		try{
			ps = con.prepareStatement(queryInsert);
			ps.setInt(1, user.getId());
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setInt(4, user.getAge());
			int out = ps.executeUpdate();
			if(out !=0)
				System.out.println("User saved with id="+user.getId());
			else 
				System.out.println("User save failed with id="+user.getId());

		}catch(SQLException e3){
			System.out.println("No USERSTAB table exists!");
		}

		finally{
			try {
				if (stmt!=null) stmt.close();
				if (selectStmt!=null) selectStmt.close();
				if (ps!=null) ps.close();
				if (con!=null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public User getById(int id) {
		String query = "select FIRST_NAME, LAST_NAME, age from USERSTAB where id = ?";
		User user = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				user = new User();
				user.setId(id);
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setAge(rs.getInt("age"));
				System.out.println("User Found::"+user);
			}else{
				System.out.println("No User found with id="+id);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				if (rs!=null) rs.close();
				if (ps!=null) ps.close();
				if (con!=null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public void update(User user) {
		String query = "update USERSTAB set first_name=?, last_name=?, age=? where id=?";
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setInt(3, user.getAge());
			ps.setInt(4, user.getId());
			int out = ps.executeUpdate();
			if(out !=0){
				System.out.println("User updated with id="+user.getId());
			}else System.out.println("No User found with id="+user.getId());
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				if (ps!=null) ps.close();
				if (con!=null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteById(int id) {
		String query = "delete from USERSTAB where id=?";
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			int out = ps.executeUpdate();
			if(out !=0){
				System.out.println("User deleted with id="+id);
			}else System.out.println("No User found with id="+id);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				if (ps!=null) ps.close();
				if (con!=null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<User> getAll() {
		String query = "select id, first_name, last_name, age from USERSTAB";
		List<User> userList = new ArrayList<User>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setAge(rs.getInt("age"));
				userList.add(user);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				if (rs!=null) rs.close();
				if (ps!=null) ps.close();
				if (con!=null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userList;
	}

}
