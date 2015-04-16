package com.ds;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class Map extends MapReduceBase implements
		Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	public void map(LongWritable key, Text value,
			OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {

		System.out.println("map������ʼ,�������ݣ�"+value.toString());
		String line = value.toString();
		
		System.out.println("map(),key:"+key.get());
		System.out.println("map(),value:"+line);
		
		System.out.println("��ʼ�ָ��");
		String[] words = line.split("\\s");
		System.out.println("��ʼѭ������");
		for (int i = 0; i < words.length; i++) {
			output.collect(new Text(words[i]), new IntWritable(1));
		}
		
		System.out.println("map��������,�������ݣ�"+value.toString());
		System.out.println("==================================");
	}

}
