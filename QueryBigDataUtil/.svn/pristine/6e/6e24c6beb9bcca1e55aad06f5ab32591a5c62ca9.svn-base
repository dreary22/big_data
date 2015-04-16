package com.tongtech;

import org.apache.solr.client.solrj.SolrServerException;

import com.tongtech.solr.SolrService;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) throws SolrServerException {
		SolrService ss = new SolrService();
		//д╛хож╣
		String wt = "javabin";
		int rows = 1000;
		String par_oper = "ZGYD";
		String par_netSy = null;
		String par_district = null;
		String lng1 = null;
		String lat1 = null;
		String lng2 = null;
		String lat2 = null;
		String dbm_st = null;
		String dbm_et = null;
		for (int i = 0; i < args.length; i++) {
			switch (i) {
			case 0:
				wt = args[i];
			case 1:
				rows = Integer.parseInt(args[i]);
			case 2:
				par_oper = args[i];
			case 3:
				par_netSy = args[i];
			case 4:
				par_district = args[i];
			case 5:
				lng1 = args[i];
			case 6:
				lat1 = args[i];
			case 7:
				lng2 = args[i];
			case 8:
				lat2 = args[i];
			case 9:
				dbm_st = args[i];
			case 10:
				dbm_et = args[i];
			}

		}

		ss.query2(wt, rows, par_oper, par_netSy, par_district, lng1, lat1,
				lng2, lat2, dbm_st, dbm_et);

	}
}
