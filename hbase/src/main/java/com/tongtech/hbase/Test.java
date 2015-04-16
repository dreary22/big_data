package com.tongtech.hbase;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import org.apache.hadoop.hbase.util.Bytes;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		System.out.println("a:"+Bytes.toBytes("abc"));
//		for(int i = 0;i < Bytes.toBytes("abc").length;i++){
//			System.out.println(Bytes.toBytes("abc")[i]);
//		}
		/*System.out.println(Arrays.toString(Bytes.toBytes("abc")));
		System.out.println(Arrays.toString(Bytes.toBytes("   ")));
		System.out.println(Long.MAX_VALUE);
		*/
		System.out.println("SIZEOF_INT:"+Bytes.SIZEOF_INT);
		System.out.println("SIZEOF_LONG:"+Bytes.SIZEOF_LONG);
		
		
		
//		makeRowKey("abcdef",(new Date(2014,4,27)).getTime());
//		makeRowKey("abcdef",(new Date(2014,4,28)).getTime());
//		makeRowKey("abcdef",(new Date(2014,4,29)).getTime());
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		java.util.Calendar c = Calendar.getInstance();
		c.set(2009, 0, 21);
		System.out.println(sdf.format(c.getTime()));
		System.out.println();
		makeRowKey("abcdef",c.getTimeInMillis());
		
		
//		System.out.println(Arrays.toString(Bytes.toBytes(1L)));
	}
	
	public static final int ID_LENGTH = 6;
	
	public static void makeRowKey(String id,long time){
		byte[] row = new byte[ID_LENGTH+Bytes.SIZEOF_LONG];
		Bytes.putBytes(row, 0, Bytes.toBytes(id), 0, ID_LENGTH);
		long reverseTime = Long.MAX_VALUE-time;
		Bytes.putLong(row, ID_LENGTH, reverseTime);
		System.out.println(Arrays.toString(row));
		//System.out.println(Arrays.toString(Bytes.toBytes(reverseTime)));
	}

}
