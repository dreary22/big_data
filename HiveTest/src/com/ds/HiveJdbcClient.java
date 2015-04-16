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
			//注册jdbc驱动
			Class.forName("org.apache.hadoop.hive.jdbc.HiveDriver");
			
			//创建连接
			con = DriverManager.getConnection("jdbc:hive://localhost:10000/default","","");
			
			//创建statement
			stmt = con.createStatement();
			
			//删除表
			String dropSql = "drop table t1_data";
			System.out.println("Running:"+dropSql);
			stmt.executeQuery(dropSql);
			
			//创建表
			String createSql = "create table t1_data (str STRING) row format delimited fields terminated by '\\s' stored as textfile";
			System.out.println("Running:"+createSql);
			stmt.executeQuery(createSql);
			
			//显示所有表
			String showSql = "show tables";
			System.out.println("Running:"+showSql);
			ResultSet res = stmt.executeQuery(showSql);
			while(res.next()){
				System.out.println(" "+res.getString(1));
			}
			
			//显示表结构
			String describeSql = "describe t1_data";
			System.out.println("Running:"+describeSql);
			res = stmt.executeQuery(describeSql);
			while(res.next()){
				System.out.println(" "+res.getString(1)+"\t"+res.getString(2));
			}
			
			//加载数据
			String filePath = "/software/input/file03";
			String loadSql = "load data local inpath '"+filePath+"' overwrite into table t1_data";
			System.out.println("Running:"+loadSql);
			res = stmt.executeQuery(loadSql);
			
			//查询前5条
			String selSql = "select * from t1_data limit 5";
			System.out.println("Running:"+selSql);
			res = stmt.executeQuery(selSql);
			while(res.next()){
				System.out.println(" "+res.getString(1));
			}
			
			//统计
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
