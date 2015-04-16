package com.ds;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class Reduce extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	public void reduce(Text key, Iterator<IntWritable> values,
			OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {
		System.out.println("reduce函数开始,key："+key.toString());
		int sum = 0;
		while(values.hasNext()){
			sum += values.next().get();
		}
		
		System.out.println("reduce():"+key.toString()+","+sum);
		output.collect(key, new IntWritable(sum));
		
		System.out.println("reduce函数结束,key："+key.toString());
		System.out.println("==================================");
	}

}
