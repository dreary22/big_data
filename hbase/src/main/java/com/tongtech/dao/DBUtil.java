package com.tongtech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tongtech.model.GatherPoint;
import com.tongtech.utils.C3p0Bean;

public class DBUtil {
	private static final Log logger = LogFactory
			.getLog(DBUtil.class);
	
	public List<GatherPoint> queryDataBySql(String sql,String[] args) throws SQLException{
		List<GatherPoint> list = new ArrayList<GatherPoint>();
		PreparedStatement st = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = C3p0Bean.getConn();
			st = conn.prepareStatement(sql);
			if (null != args) {
				for (int i = 0; i < args.length; i++) {
					st.setString((i + 1), args[i]);
				}
			}
			rs = st.executeQuery();
			
			int i = 1;
			while(rs.next()){
				GatherPoint gp = new GatherPoint();
				try {
					gp.setAcquiredTime(rs.getTimestamp(1));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				gp.setAppVersion(rs.getString(2));
				gp.setBattery(rs.getString(3));
				gp.setBid(rs.getString(4));
				gp.setBigRegion(rs.getString(5));
				gp.setCdma(rs.getString(6));
				gp.setCdmaevdo(rs.getString(7));
				gp.setCdmaevdoa(rs.getString(8));
				gp.setCellId(rs.getString(9));
				gp.setChildGridId("CGID"+(i++));
				gp.setCity(rs.getString(10));
				gp.setDbm(rs.getString(11));
				gp.setDistrict(rs.getString(12));
				gp.setGridId("GID"+(i++));
				gp.setGsm(rs.getString(13));
				gp.setHardware(rs.getString(14));
				gp.setLac(rs.getString(15));
				gp.setLatitude(rs.getString(16));
				gp.setLatitudeBd(rs.getString(17));
				gp.setLongitude(rs.getString(18));
				gp.setLongitudeBd(rs.getString(19));
				gp.setLte(rs.getString(20));
				gp.setModel(rs.getString(21));
				gp.setNetSystem(rs.getString(22));
				gp.setNid(rs.getString(23));
				gp.setOfficialRegion(rs.getString(24));
				gp.setOperator(rs.getString(25));
				gp.setOs(rs.getString(26));
				gp.setPci(rs.getString(27));
				gp.setProvince(rs.getString(28));
				gp.setScdma(rs.getString(29));
				gp.setSid(rs.getString(30));
				gp.setSinr(rs.getString(31));
				gp.setSlevel(rs.getString(32));
				gp.setSmallRegion(rs.getString(33));
				gp.setSpeed(rs.getString(34));
				gp.setStoreTime(DateUtil.formatDate(new Date(),"yyyy-MM-dd hh:mm:ss"));
				gp.setTac(rs.getString(35));
				gp.setTopDbm(rs.getString(36));
				gp.setWcdma(rs.getString(37));
				list.add(gp);
			}
		} catch (SQLException e) {
			logger.error(Thread.currentThread().getName() + "，查询数据抛出异常:"
					+ e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
		return list;
	}
	
	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		DBUtil dbUtil = new DBUtil();
		String sql = "select t.acquired_time,t.application_version,t.battery,t2.bid,'bigRegion','0','0','0',t2.cell_id,t.city,t2.dbm,t.district,t3.gsm,t3.hardware,t2.lac,t.latitude,t.latitude2,t.longitude,t.longitude2,'0',t3.terminal_model,t2.cell_type,t2.nid,'行政区域',t.operator_id,t.android_version,t2.pci,t.province,'0',t2.sid,t2.sinr,t2.slevel,'smallRegion',t.speed,t2.tac,t.top_dbm,t3.wcdma from ACQUIRED_INFO t,acquired_station t2,terminal t3 where rownum<1000";
		List<GatherPoint> list = dbUtil.queryDataBySql(sql, null);
		Iterator<GatherPoint> itor = list.iterator();
		while(itor.hasNext()){
			GatherPoint gp = itor.next();
			System.out.println(gp);
		}
		
		//System.out.println(StringUtils.trimToEmpty(null));
	}

}
