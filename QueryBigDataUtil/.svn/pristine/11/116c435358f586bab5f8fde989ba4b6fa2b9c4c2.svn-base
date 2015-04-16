package com.tongtech.hbase;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.RetriesExhaustedWithDetailsException;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HbaseService {
	private static Logger log = LoggerFactory.getLogger(HbaseService.class);
	public static Configuration configuration;
	private static HConnection con = null;
	private HTable writeHtable = null;
	private HTable readHtable = null;
	private String tableName = "";
	static {
		configuration = HBaseConfiguration.create();
		configuration.set("hbase.zookeeper.property.clientPort", "2181");
		configuration.set("hbase.zookeeper.quorum", "node1,node2,node3");
		configuration.set("hbase.master", "node1:60000");
		configuration.set("hbase.client.scanner.caching", "500");

		try {
			con = HConnectionManager.createConnection(configuration);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("创建htable失败", e);
		}
		System.out.println("创建config成功");
	}

	public HbaseService(String tableName) {
		this.tableName = tableName;
	}

	private HTable getWriteTable() throws IOException {
		if (writeHtable != null)
			return writeHtable;
		writeHtable = (HTable) con.getTable(this.tableName);
		writeHtable.setWriteBufferSize(6 * 1024 * 1024);
		writeHtable.setAutoFlush(false, false);
		return writeHtable;
	}

	private HTable getNewReadTable() throws IOException {
		readHtable = (HTable) con.getTable(this.tableName);
		return readHtable;
	}

	/**
	 * 创建表
	 * 
	 * @param tableName
	 */
	public boolean createTable() {
		boolean flag = false;
		System.out.println("start create table ......");
		try {
			HBaseAdmin hBaseAdmin = new HBaseAdmin(configuration);
			if (hBaseAdmin.tableExists(tableName)) {// 如果存在要创建的表，那么先删除，再创建
				hBaseAdmin.disableTable(tableName);
				hBaseAdmin.deleteTable(tableName);
				System.out.println(tableName + " is exist,detele....");
			}
			HTableDescriptor tableDescriptor = new HTableDescriptor(
					TableName.valueOf(tableName));
			HColumnDescriptor col = new HColumnDescriptor("f");
			col.setCompressionType(Compression.Algorithm.LZO);
			tableDescriptor.addFamily(col); //
			hBaseAdmin.createTable(tableDescriptor);
			flag = true;
		} catch (MasterNotRunningException e) {
			e.printStackTrace();
		} catch (ZooKeeperConnectionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("end create table ......");
		return flag;
	}

	public List<String> QueryByRowkeys(List<String> rowkeys) {
		// System.out.println("!!!!!!!!!!!! "+myTid+"： "+rowkeys.size());
		long start = System.currentTimeMillis();
		List<String> values = new ArrayList<String>();
		try {
			readHtable = getNewReadTable();
			List<Get> gets = new ArrayList<Get>();
			for (String rowkey : rowkeys) {
				Get get = new Get(rowkey.getBytes());
				gets.add(get);
			}
			Object[] r1 = readHtable.batch(gets);
			for (Object o : r1) {
				// batch ensures if there is a failure we get an exception
				// instead
				Result result = (Result) o;
				StringBuffer sb = new StringBuffer();
				sb.append(Bytes.toStringBinary(result.getValue("f".getBytes(),
						"operator".getBytes())));
				sb.append(",");
				sb.append(Bytes.toStringBinary(result.getValue("f".getBytes(),
						"netSystem".getBytes())));
				sb.append(",");
				sb.append(Bytes.toStringBinary(result.getValue("f".getBytes(),
						"longitudeBd".getBytes())));
				sb.append(",");
				sb.append(Bytes.toStringBinary(result.getValue("f".getBytes(),
						"latitudeBd".getBytes())));
				values.add(sb.toString());
			}
			// System.out.println("开始时间"+start+"查询hbase数量-"+rowkeys.size()+"-消耗时间："+(System.currentTimeMillis()
			// - start));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("插入数据报错：", e);
		}
		return values;
	}

	public List<String> queryByPreRowey(byte[] start, byte[] end) {
		long starttime = System.currentTimeMillis();
		List<String> values = new ArrayList<String>();
		try {
			readHtable = getNewReadTable();
			List<Get> gets = new ArrayList<Get>();
			Scan scan = new Scan();
			scan.setStartRow(start);
			scan.setStopRow(end);
			ResultScanner rs = readHtable.getScanner(scan);
			Iterator<Result> iresult = rs.iterator();
			while (iresult.hasNext()) {
				// batch ensures if there is a failure we get an exception
				// instead
				Result result = iresult.next();
				StringBuffer sb = new StringBuffer();
				sb.append(Bytes.toStringBinary(result.getValue("f".getBytes(),
						"operator".getBytes())));
				sb.append(",");
				sb.append(Bytes.toStringBinary(result.getValue("f".getBytes(),
						"netSystem".getBytes())));
				sb.append(",");
				sb.append(Bytes.toStringBinary(result.getValue("f".getBytes(),
						"longitudeBd".getBytes())));
				sb.append(",");
				sb.append(Bytes.toStringBinary(result.getValue("f".getBytes(),
						"latitudeBd".getBytes())));
				values.add(sb.toString());
			}
			System.out.println("查询总数量为：" + values.size() + "  "
					+ (System.currentTimeMillis() - starttime));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("插入数据报错：", e);
		}
		return values;
	}

	public void flush() {
		try {
			writeHtable.flushCommits();
		} catch (RetriesExhaustedWithDetailsException e) {
			// TODO Auto-generated catch block
			log.error("flush Table 失败:RetriesExhaustedWithDetailsException", e);
		} catch (InterruptedIOException e) {
			// TODO Auto-generated catch block
			log.error("flush Table 失败:RetriesExhaustedWithDetailsException", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public static void main(String[] args) {
		HbaseService hs = new HbaseService("test1218");
		// hs.createTable(hs.TABLENAME);
		//
		// DBUtil dbUtil = new DBUtil();
		// String sql =
		// "select t.acquired_time,t.application_version,t.battery,t2.bid,'bigRegion','0','0','0',t2.cell_id,(select min(d.district_id) from district d where d.name=t.city),t2.dbm,(select min(d.district_id) from district d where d.name=t.district),t3.gsm,t3.hardware,t2.lac,t.latitude,t.latitude2,t.longitude,t.longitude2,'0',t3.terminal_model,t2.cell_type,t2.nid,'行政区域',t.operator_id,t.android_version,t2.pci,(select min(d.district_id) from district d where d.name=t.province),'0',t2.sid,t2.sinr,t2.slevel,'smallRegion',t.speed,t2.tac,t.top_dbm,t3.wcdma from ACQUIRED_INFO t,acquired_station t2,terminal t3 where rownum<1000";
		// try {
		// List<GatherPoint> list = dbUtil.queryDataBySql(sql, null);
		// hs.insertGatherData(list);
		// hs.flush();
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// List<String> queryRowIds = new ArrayList<String>();
		// String rowkey = "ZGDX"+
		// StringUtils.rightPad("cdma", 5, '-')+
		// StringUtils.rightPad("110000", 7, '-')+
		// StringUtils.rightPad("110000", 11, '-')+
		// StringUtils.rightPad("110102", 7, '-')+
		// StringUtils.rightPad("GID1258", 8, '-')+
		// "20141127093133"+
		// StringUtils.rightPad("CGID1257", 8, '-')+
		// StringUtils.leftPad("629", 4, '0');
		// System.out.println(rowkey);
		// queryRowIds.add(rowkey);
		// List<String> reStr = hs.QueryByRowkeys(queryRowIds);
		// System.out.println(reStr);

		String rowkeyStart = "ZGDX" + StringUtils.rightPad("cdma", 5, '-')
				+ StringUtils.rightPad("110000", 7, '-')
				+ StringUtils.rightPad("110000", 11, '-')
				+ StringUtils.rightPad("110102", 7, '-')
				+ StringUtils.rightPad("GID1", 8, '-');

		String rowkeyEnd = "ZGDXd";
		List<String> reStr = hs.queryByPreRowey(rowkeyStart.getBytes(),
				rowkeyEnd.getBytes());
		for (String s : reStr) {
			System.out.println(s);
		}
	}

}
