package com.tongtech.irrigatedata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.ConcurrentUpdateSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertData {
	private static Logger log = LoggerFactory.getLogger(InsertData.class);
	private String solr_url = "";//solr地址
	private ConcurrentUpdateSolrServer concurrent;
	
	public InsertData(String solr_url){
		this.solr_url = solr_url;
		//solr地址,队列缓冲大小,线程数
		concurrent = new ConcurrentUpdateSolrServer(solr_url, 1000,5);
	}
	
	/**
	 * 灌入数据
	 * @param maps
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public void insertData(final List<Map> maps) throws SolrServerException, IOException{
		long startTime = System.nanoTime();//纳秒
		List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
		for(Map map :maps){
			SolrInputDocument solrDoc = null;
								
			solrDoc = new SolrInputDocument();
			
			solrDoc.addField("id",map.get("id"));
			solrDoc.addField("name",map.get("name"));
			//solrDoc.addField("desc", map.get(""));			
			
			docs.add(solrDoc);
		}
		
		int  docsize = docs.size();
		if(docsize>0){			
			concurrent.add(docs,10000);//max time (in ms) before a commit will happen
		}
		
		long endTime = System.nanoTime();
		log.debug("灌入数据耗时:"+(endTime-startTime)+"纳秒");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		
	}

}
