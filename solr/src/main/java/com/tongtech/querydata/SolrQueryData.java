package com.tongtech.querydata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tongtech.irrigatedata.InsertData;

public class SolrQueryData {

	private static Logger log = LoggerFactory.getLogger(SolrQuery.class);
	
	private HttpSolrServer solrServer = null;
	
	public SolrQueryData(String solr_url){
		solrServer = new HttpSolrServer(solr_url);
	}
	
	public List<String> getDesc(String name) throws SolrServerException{
		List<String> ids = new ArrayList<String>();
		long startTime = System.nanoTime();
		SolrQuery query = new SolrQuery();
		query.set("wt", "javabin");
 		query.set("q","name:*"+name);     		
		query.set("fl", "id");	
		QueryResponse rsp = null;
		rsp = solrServer.query(query);
		SolrDocumentList docs = rsp.getResults();
		long endTime = System.nanoTime();
		log.debug("solr查询耗时："+(endTime-startTime)+"纳秒");
		Iterator<SolrDocument> itrdoc = docs.listIterator();
		String resultId = "";
		long count = 0;
		while (itrdoc.hasNext()) {
			resultId = itrdoc.next().getFieldValue("id").toString();
			if (resultId != null) {
				resultId = resultId.toString();
			}
			ids.add(resultId);
			count++;
		}		
		log.debug("查询总条数:"+count);
		return ids;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String solr_url = "http://168.1.195.12:8983/solr/collection1";
		
//		List<Map> maps = new ArrayList<Map>();
//		int i = 100;
//		while(i>0){
//			Map m = new HashMap();
//			m.put("id", java.util.UUID.randomUUID().toString());
//			m.put("name", "asdf"+i);
//			maps.add(m);
//			i--;
//		}
//		
//		InsertData insertData = new InsertData(solr_url);
//		try {
//			insertData.insertData(maps);
//		} catch (SolrServerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		SolrQueryData sqd = new SolrQueryData(solr_url);
		try {
			List<String> list = sqd.getDesc("asdf"+20);
			System.out.println(list);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
