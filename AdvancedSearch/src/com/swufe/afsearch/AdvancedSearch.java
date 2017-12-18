package com.swufe.afsearch;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.functors.AndPredicate;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AdvancedSearch {
	public static class MyMapper extends Mapper<Object, Text, Text, IntWritable> {

		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();
		private String[] advancedType = { "intitle:", "inurl:", "intext:", 
				"filetype:", "site:","+","-",
				"allinanchor:","allintitle:", "allintext:", "allinurl:" ,
				"author:","bphonebook:","cache:","datarange:","define:",
				"group:","inanchor:","info:","insubject:","link:",
				"location:","movie:","phonebook:","related:","safesearch:",
				"source:","stocks:","store:","tq:","weather:"};

		private String hasUsedAdvanceSearch( String searchWord ) {
			if( searchWord.indexOf('\"') != searchWord.lastIndexOf('\"') )
				return "\"\"";
			if( searchWord.indexOf("¡¶")!=-1 && searchWord.indexOf("¡·")!=-1 )
				return "¡¶¡·";
			for(String advancedWord : advancedType) {
				if( searchWord.indexOf(advancedWord)!=-1 )
					return advancedWord;
			}
			return null;
		}
		
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			StringTokenizer itr = new StringTokenizer(value.toString());
			while (itr.hasMoreTokens()) {
				String cur = itr.nextToken();
				if (cur.startsWith("[") && cur.endsWith("]")) {
					word.set("all");
					context.write(word, one);
					
					String searchWord = String.valueOf(cur.toCharArray(), 1, cur.length() - 1).toLowerCase();
					String searchType = hasUsedAdvanceSearch( searchWord );
					if( searchType != null ) {
						word.set( searchType );
						context.write(word, one);
					}
					
				}
			}
		}
	}

	public static class MyReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

		public void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable val : values) {
				sum = sum + val.get();
			}
			context.write(key, new IntWritable(sum));
		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = new Job(conf, "cal advanced search");
		job.setJarByClass(AdvancedSearch.class);
		job.setMapperClass(MyMapper.class);
		job.setReducerClass(MyReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
