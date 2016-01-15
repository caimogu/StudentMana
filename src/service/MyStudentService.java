package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bean.MyClass;
import bean.MyStudent;
import dao.MyStudentDAO;

public class MyStudentService {
	public static MyStudentDAO dao = new MyStudentDAO();
	
	public static void main(String[] args) throws SQLException {	
		int op = 0;
		while(true){
			System.out.println("1:�鿴���а༶    2.�鿴�༶��Ա   3.���ѧ��   4.��ѯѧ����Ϣ   5.��Ӱ༶");
			System.out.print("�����������");
			Scanner in = new Scanner(System.in);
			op = in.nextInt();
			switch(op){
				case 1:allClass();break;
				case 2:allStudent();break;
				case 3:insertStu();break;
				case 4:stuInfo();break; 
				case 5:insertClass();break;
			}
		}
	}

	private static void insertClass() throws SQLException {
		// TODO Auto-generated method stub
		MyClass myclass = new MyClass();
		Scanner in = new Scanner(System.in);
		System.out.print("������༶��:");
		myclass.setCno(in.nextLine());
		System.out.print("������רҵ:");
		myclass.setMajor(in.nextLine());
		System.out.print("����������:");
		myclass.setNumber(in.nextInt());
		System.out.print("������೤ѧ��:");
		myclass.setMonitor(String.valueOf(in.nextInt()));
		dao.insert(myclass, 2);
	}

	private static void stuInfo() throws SQLException {
		// TODO Auto-generated method stub
		List<MyStudent> mystu = new ArrayList<MyStudent>();
		String name ;
		System.out.print("������������");
		Scanner in = new Scanner(System.in);
		name = in.nextLine();
		mystu = (List<MyStudent>) dao.select(name, 3);
		for(int i = 0;i<mystu.size();i++){
			System.out.printf("%10s %10s %5d %10s "+"\n",mystu.get(i).getSno(),mystu.get(i).getSname(),mystu.get(i).getMonitor(),mystu.get(i).getCno());			
		}
	}

	private static void allStudent() throws SQLException {
		// TODO Auto-generated method stub
		List<MyStudent> mystu = new ArrayList<MyStudent>();
		Scanner in = new Scanner(System.in);
		System.out.print("������༶:");
		String c = String.valueOf(in.nextInt());
		mystu = (List<MyStudent>) dao.select(c, 1);
		for(int i = 0;i<mystu.size();i++){
			System.out.printf("%10s %10s %5d %10s "+"\n",mystu.get(i).getSno(),mystu.get(i).getSname(),mystu.get(i).getMonitor(),mystu.get(i).getCno());			
		}
	}

	private static void insertStu() throws SQLException {
		// TODO Auto-generated method stub
		MyStudent mystu = new MyStudent();
		Scanner in = new Scanner(System.in);
		System.out.print("������ѧ��:");
		mystu.setSno(in.nextLine());
		System.out.print("����������:");
		mystu.setSname(in.nextLine());
		System.out.print("�������Ƿ�೤:");
		mystu.setMonitor(0);
		System.out.print("������༶:");
		mystu.setCno(String.valueOf(in.nextInt()));
		dao.insert(mystu, 3);
	}

	private static void allClass() throws SQLException {
		// TODO Auto-generated method stub
		List<MyClass> myclass = new ArrayList<MyClass>();
		myclass = (List<MyClass>) dao.select(null, 2);
		System.out.printf("   %10s         %10s        %5s    %10s ","�༶��","רҵ","����","�೤ѧ��");
		System.out.println("");
		for(int i = 0;i<myclass.size();i++){
			System.out.printf("%10s %10s %5d %10s "+"\n",myclass.get(i).getCno(),myclass.get(i).getMajor(),myclass.get(i).getNumber(),myclass.get(i).getMonitor());			
		}
	}
}
