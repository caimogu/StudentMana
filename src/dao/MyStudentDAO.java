package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import bean.*;
import conn.DatabaseConn;
import conn.MySqlConn;

public class MyStudentDAO {
	private Connection conn;
	private DatabaseConn mysql;
	private PreparedStatement ps;
	private ResultSet rs;
	private int iRs;
	
	public MyStudentDAO(){
		mysql = new MySqlConn();
		conn = mysql.getConnection();
	}
	
	public void insert(Object o,int tb) throws SQLException{
		switch(tb){
			case 1:ClassStudent myObject = (ClassStudent)o;csInsert(myObject);break;
			case 2:MyClass myObject1 = (MyClass)o;cInsert(myObject1);break;
			case 3:MyStudent myObject2 = (MyStudent)o;sInsert(myObject2);break;
		}
	}
	public List<?> select(Object o,int tb) throws SQLException{
		List<Object> list = new ArrayList<Object>();
		switch(tb){
			case 1:
				String cno = (String)o;
				list = (List<Object>) csSelect(cno);
				break;
			case 2:
				list = (List<Object>) cSelect();
				break;
			case 3:
				String sname = (String)o;
				list = (List<Object>) sSelect(sname);
				break;
		}
		return list;
	}
	

	private List<?> sSelect(String sname) {
		// TODO Auto-generated method stub
		List<MyStudent> list = new ArrayList<MyStudent>();
		MyStudent mystu = null;
		String sql = "SELECT * FROM tbl_stu WHERE sname LIKE '%"+sname+"%';";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				mystu = new MyStudent();
				mystu.setSno(rs.getString("sno"));
				mystu.setSname(rs.getString("sname"));
				mystu.setMonitor(rs.getInt("status"));
				mystu.setCno(rs.getString("cno"));
				list.add(mystu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private List<?> csSelect(String cno) {
		// TODO Auto-generated method stub
		List<MyStudent> list = new ArrayList<MyStudent>();
		MyStudent mystu = null;
		String sql = "SELECT tbl_stu.sno,tbl_stu.sname,tbl_stu.status FROM tbl_stu,tbl_sc WHERE tbl_sc.cno = '"+cno+"' AND "+"tbl_sc.cno = tbl_stu.cno ;";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				mystu = new MyStudent();
				mystu.setSno(rs.getString("sno"));
				mystu.setSname(rs.getString("sname"));
				mystu.setMonitor(rs.getInt("status"));
				mystu.setCno(cno);
				list.add(mystu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	private List<?> cSelect() throws SQLException {
		List<MyClass> list = new ArrayList<MyClass>();
		MyClass myclass = null;
		String sql = "SELECT * FROM tbl_class ;";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()){
			myclass = new MyClass();
			myclass.setCno(rs.getString("cno"));
			myclass.setMajor(rs.getString("major"));
			myclass.setNumber(rs.getInt("number"));
			myclass.setMonitor(rs.getString("monitor"));
			list.add(myclass);
		}
		return list;
	}

	private void sInsert(MyStudent myObject2) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO tbl_stu value(?,?,?,?)";	
		ps = conn.prepareStatement(sql);
		ps.setString(1, myObject2.getSno());
		ps.setString(2, myObject2.getSname());
		ps.setInt(3, myObject2.getMonitor());
		System.out.println("cno="+myObject2.getCno());
		ps.setString(4,myObject2.getCno());
		iRs = ps.executeUpdate();
		ps.close();
	}

	private void cInsert(MyClass myObject1) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO tbl_class value(?,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, myObject1.getCno());
		ps.setString(2, myObject1.getMajor());
		ps.setInt(3, myObject1.getNumber());
		ps.setString(4, myObject1.getMonitor());
		iRs = ps.executeUpdate();
		ps.close();
	}

	private void csInsert(ClassStudent myObject) throws SQLException {
		// TODO Auto-generated method stub
		List<String> liststu = myObject.getListstu();
		for(int i=0;i<liststu.size();i++){
			String sql = "INSERT INTO tbl_stuclass"+ myObject.getCno() +" value(?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1,liststu.get(i).toString());
			iRs = ps.executeUpdate();
			ps.close();
		}
	}
}
