package com.ds;


import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

public class WordCount {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JobConf conf = new JobConf(WordCount.class);
		System.out.println("设置job名称");
		conf.setJobName("wordcount");
		
		
		System.out.println("设置输出kv类型");
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		
		System.out.println("设置mr类");
		conf.setMapperClass(Map.class);
		conf.setReducerClass(Reduce.class);
		
		System.out.println("设置输入类型");
		conf.setInputFormat(TextInputFormat.class);
		
		System.out.println("设置输出类型");
		conf.setOutputFormat(TextOutputFormat.class);
		
		System.out.println("设置输入路径");
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		System.out.println("设置输出路径");
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		
		try {
			System.out.println("runjob");
			JobClient.runJob(conf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
