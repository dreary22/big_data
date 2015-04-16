package com.tongtech.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SolrService {
	private static Logger log = LoggerFactory.getLogger(SolrService.class);
	private CloudSolrServer cloudSolrServer;
	private String zkHost = "node1:2181,node2:2181,node3:2181/solrcloud-ha";
	private final String  defaultCollection = "collectionCdas";  
    private final int  zkClientTimeout = 20000;  
    private final int zkConnectTimeout = 1000; 
	
	public SolrService(){
		cloudSolrServer = new CloudSolrServer(zkHost);
		cloudSolrServer.setZkConnectTimeout(zkConnectTimeout);
		cloudSolrServer.setZkClientTimeout(zkClientTimeout);
		cloudSolrServer.setDefaultCollection(defaultCollection);
		cloudSolrServer.connect();
	}
	
	
	public void delAll(){
		String delQuery = "*:*";
		try {
			cloudSolrServer.deleteByQuery(delQuery, 10000);
			cloudSolrServer.commit();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<String> query(String par_oper,String par_netSys,String par_district,String result_rowkey,String result_dbm,String result_sinr) throws SolrServerException{
		List<String> ids = new ArrayList<String>();
		long startTime = System.currentTimeMillis();
		SolrQuery query = new SolrQuery();
		query.set("wt", "javabin");
 		query.set("q","oper:"+par_oper,"net:"+par_netSys,"dis:"+par_district);
		query.set("fl", result_rowkey,result_dbm,result_sinr);
		query.setRows(1000);
		QueryResponse rsp = null;
		rsp = cloudSolrServer.query(query);
		SolrDocumentList docs = rsp.getResults();
		System.out.println("共查询出数据量:"+docs.size());
		System.out.println("查询耗时(ms):"+(System.currentTimeMillis()-startTime));
		
		startTime = System.currentTimeMillis();
		Iterator<SolrDocument> itrdoc = docs.listIterator();
		String resultId = "";
		while (itrdoc.hasNext()) {
			resultId = itrdoc.next().getFieldValue("id").toString();
			if (resultId != null) {
				resultId = resultId.toString();
			}
			ids.add(resultId);
		}		
		System.out.println("循环耗时(ms):"+(System.currentTimeMillis()-startTime));
		return ids;
	}
	
	public List<String> query2(String wt,int rows,String par_oper,String par_netSys,String par_district,
			String lng1,String lat1,String lng2,String lat2,String dbm_st,String dbm_et) throws SolrServerException{
		List<String> ids = new ArrayList<String>();
		long startTime = System.currentTimeMillis();
		SolrQuery query = new SolrQuery();
		query.set("wt", wt);
 		StringBuffer sb = new StringBuffer("oper:"+par_oper);
 		
 		if(!StringUtils.isEmpty(par_netSys)){
 			sb.append(" AND net:"+par_netSys);
 		}
 		
 		if(!StringUtils.isEmpty(lng1) && !StringUtils.isEmpty(lng2)){
 			sb.append(" AND lngbd:["+lng1+" TO "+lng2+"]");
 		}
 		
 		if(!StringUtils.isEmpty(lat1) && !StringUtils.isEmpty(lat2)){
 			sb.append(" AND latbd:["+lat1+" TO "+lat2+"]");
 		}
 		
 		if(!StringUtils.isEmpty(par_district)){
 			sb.append(" AND dis:"+par_district);
 		}
 		
 		if(StringUtils.isEmpty(dbm_st)){
 			dbm_st = "*";
 		}
 		if(StringUtils.isEmpty(dbm_et)){
 			dbm_et = "*";
 		}
 		sb.append(" AND dbm:["+dbm_st+" TO "+dbm_et+"]");
 		query.set("q",sb.toString());
// 		query.set("q.op", "AND");
		query.set("fl", "id","lngbd","latbd","dbm");
		System.out.println(query.getQuery());
		long num = 0;
		int start = 0;
		query.setStart(start);
		query.setRows(rows);
		while(true){
			QueryResponse rsp = null;
			rsp = cloudSolrServer.query(query);
			SolrDocumentList docs = rsp.getResults();
			
			Iterator<SolrDocument> itrdoc = docs.listIterator();
			String resultId = "";
			while (itrdoc.hasNext()) {
				resultId = itrdoc.next().getFieldValue("id").toString();
//						+ itrdoc.next().getFieldValue("lngbd").toString()
//						+ itrdoc.next().getFieldValue("latbd").toString();
				if (resultId != null) {
					resultId = resultId.toString();
				}
				ids.add(resultId);
			}
			
			
			num = num + docs.size();
			if(docs.size()<=0){
				break;	
			}
			start = start + rows;
			query.setStart(start);
			query.setRows(rows);
		}
		
		System.out.println("共查询出数据量:"+num);
		System.out.println("查询耗时(ms):"+(System.currentTimeMillis()-startTime));
		
		startTime = System.currentTimeMillis();
				
		System.out.println("循环耗时(ms):"+(System.currentTimeMillis()-startTime));
		return ids;
	}
	
}
