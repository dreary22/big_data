package com.ds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HiveJdbcClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		try {
			//ע��jdbc����
			Class.forName("org.apache.hadoop.hive.jdbc.HiveDriver");
			
			//��������
			con = DriverManager.getConnection("jdbc:hive://localhost:10000/default","","");
			
			//����statement
			stmt = con.createStatement();
			
			//ɾ����
			String dropSql = "drop table t1_data";
			System.out.println("Running:"+dropSql);
			stmt.executeQuery(dropSql);
			
			//������
			String createSql = "create table t1_data (str STRING) row format delimited fields terminated by '\\s' stored as textfile";
			System.out.println("Running:"+createSql);
			stmt.executeQuery(createSql);
			
			//��ʾ���б�
			String showSql = "show tables";
			System.out.println("Running:"+showSql);
			ResultSet res = stmt.executeQuery(showSql);
			while(res.next()){
				System.out.println(" "+res.getString(1));
			}
			
			//��ʾ��ṹ
			String describeSql = "describe t1_data";
			System.out.println("Running:"+describeSql);
			res = stmt.executeQuery(describeSql);
			while(res.next()){
				System.out.println(" "+res.getString(1)+"\t"+res.getString(2));
			}
			
			//��������
			String filePath = "/software/input/file03";
			String loadSql = "load data local inpath '"+filePath+"' overwrite into table t1_data";
			System.out.println("Running:"+loadSql);
			res = stmt.executeQuery(loadSql);
			
			//��ѯǰ5��
			String selSql = "select * from t1_data limit 5";
			System.out.println("Running:"+selSql);
			res = stmt.executeQuery(selSql);
			while(res.next()){
				System.out.println(" "+res.getString(1));
			}
			
			//ͳ��
//			String countSql = "select count(*) from t1_data";
//			System.out.println("Running:"+countSql);
//			res = stmt.executeQuery(countSql);
//			while(res.next()){
//				System.out.println(" "+res.getString(1));
//			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(!stmt.isClosed()){
					stmt.close();
				}
				if(!con.isClosed()){
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
	}

}
