package com.tongtech.model;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.http.impl.cookie.DateUtils;

public class GatherPoint {
	
	//��Ӫ��
	private String operator;
	//��ʽ
	private String netSystem;
	//�ٷ���������
	private String officialRegion;
	//һ������id
	private String gridId;
	//��������id
	private String childGridId;
	//����
	private String hardware;
	//�ͺ�
	private String model;
	//����ϵͳ
	private String os;
	//����ֵ
	private String bigRegion;
	//С��ֵ
	private String smallRegion;
	//gps����
	private String longitude;
	//gpsγ��
	private String latitude;
	//�ٶȾ���
	private String longitudeBd;
	//�ٶ�γ��
	private String latitudeBd;
	//�ɼ�ʱ��
	private Date acquiredTime;
	//ʡ
	private String province;
	//����
	private String city;
	//����
	private String district;
	//���
	private String battery;
	//app�汾
	private String appVersion;
	//
	private String topDbm;
	
	private String dbm;
	
	private String sinr;
	
	private String sid;
	
	private String nid;
	
	private String bid;
	//�ٶ�
	private String speed;
	
	private String cellId;
	
	private String lac;
	
	private String slevel;
	
	private String pci;
	
	private String tac;
	
	private String gsm;
	
	private String scdma;
	
	private String lte;
	
	private String wcdma;
	
	private String cdma;
	
	private String cdmaevdo;
	
	private String cdmaevdoa;
	//�洢ʱ��
	private String storeTime;
	
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getNetSystem() {
		return netSystem;
	}
	public void setNetSystem(String netSystem) {
		this.netSystem = netSystem;
	}
	public String getOfficialRegion() {
		return officialRegion;
	}
	public void setOfficialRegion(String officialRegion) {
		this.officialRegion = officialRegion;
	}
	public String getGridId() {
		return gridId;
	}
	public void setGridId(String gridId) {
		this.gridId = gridId;
	}
	public String getChildGridId() {
		return childGridId;
	}
	public void setChildGridId(String childGridId) {
		this.childGridId = childGridId;
	}
	public String getHardware() {
		return hardware;
	}
	public void setHardware(String hardware) {
		this.hardware = hardware;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getBigRegion() {
		return bigRegion;
	}
	public void setBigRegion(String bigRegion) {
		this.bigRegion = bigRegion;
	}
	public String getSmallRegion() {
		return smallRegion;
	}
	public void setSmallRegion(String smallRegion) {
		this.smallRegion = smallRegion;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitudeBd() {
		return longitudeBd;
	}
	public void setLongitudeBd(String longitudeBd) {
		this.longitudeBd = longitudeBd;
	}
	public String getLatitudeBd() {
		return latitudeBd;
	}
	public void setLatitudeBd(String latitudeBd) {
		this.latitudeBd = latitudeBd;
	}
	public Date getAcquiredTime() {
		return acquiredTime;
	}
	public void setAcquiredTime(Date acquiredTime) throws ParseException {
		this.acquiredTime = org.apache.commons.lang.time.DateUtils.parseDate(DateFormatUtils.format(acquiredTime, "yyyy-MM-dd hh:mm:ss"), new String[]{"yyyy-MM-dd hh:mm:ss"});
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getBattery() {
		return battery;
	}
	public void setBattery(String battery) {
		this.battery = StringUtils.trimToEmpty(battery);
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getTopDbm() {
		return topDbm;
	}
	public void setTopDbm(String topDbm) {
		this.topDbm = topDbm;
	}
	public String getDbm() {
		return dbm;
	}
	public void setDbm(String dbm) {
		this.dbm = dbm;
	}
	public String getSinr() {
		return sinr;
	}
	public void setSinr(String sinr) {
		this.sinr = StringUtils.trimToEmpty(sinr);
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = StringUtils.trimToEmpty(sid);
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = StringUtils.trimToEmpty(nid);
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = StringUtils.trimToEmpty(bid);
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = StringUtils.trimToEmpty(speed);
	}
	public String getCellId() {
		return cellId;
	}
	public void setCellId(String cellId) {
		this.cellId = StringUtils.trimToEmpty(cellId);
	}
	public String getLac() {
		return lac;
	}
	public void setLac(String lac) {
		this.lac = StringUtils.trimToEmpty(lac);
	}
	public String getSlevel() {
		return slevel;
	}
	public void setSlevel(String slevel) {
		this.slevel = StringUtils.trimToEmpty(slevel);
	}
	public String getPci() {
		return pci;
	}
	public void setPci(String pci) {
		this.pci = StringUtils.trimToEmpty(pci);
	}
	public String getTac() {
		return tac;
	}
	public void setTac(String tac) {
		this.tac = StringUtils.trimToEmpty(tac);
	}
	public String getGsm() {
		return gsm;
	}
	public void setGsm(String gsm) {
		this.gsm = gsm;
	}
	public String getScdma() {
		return scdma;
	}
	public void setScdma(String scdma) {
		this.scdma = scdma;
	}
	public String getLte() {
		return lte;
	}
	public void setLte(String lte) {
		this.lte = lte;
	}
	public String getWcdma() {
		return wcdma;
	}
	public void setWcdma(String wcdma) {
		this.wcdma = wcdma;
	}
	public String getCdma() {
		return cdma;
	}
	public void setCdma(String cdma) {
		this.cdma = cdma;
	}
	public String getCdmaevdo() {
		return cdmaevdo;
	}
	public void setCdmaevdo(String cdmaevdo) {
		this.cdmaevdo = cdmaevdo;
	}
	public String getCdmaevdoa() {
		return cdmaevdoa;
	}
	public void setCdmaevdoa(String cdmaevdoa) {
		this.cdmaevdoa = cdmaevdoa;
	}
	public String getStoreTime() {
		return storeTime;
	}
	public void setStoreTime(String storeTime) {
		this.storeTime = storeTime;
	}
	@Override
	public String toString() {
		return "GatherPoint [operator=" + operator + ", netSystem=" + netSystem
				+ ", officialRegion=" + officialRegion + ", gridId=" + gridId
				+ ", childGridId=" + childGridId + ", hardware=" + hardware
				+ ", model=" + model + ", os=" + os + ", bigRegion="
				+ bigRegion + ", smallRegion=" + smallRegion + ", longitude="
				+ longitude + ", latitude=" + latitude + ", longitudeBd="
				+ longitudeBd + ", latitudeBd=" + latitudeBd
				+ ", acquiredTime=" + acquiredTime + ", province=" + province
				+ ", city=" + city + ", district=" + district + ", battery="
				+ battery + ", appVersion=" + appVersion + ", topDbm=" + topDbm
				+ ", dbm=" + dbm + ", sinr=" + sinr + ", sid=" + sid + ", nid="
				+ nid + ", bid=" + bid + ", speed=" + speed + ", cellId="
				+ cellId + ", lac=" + lac + ", slevel=" + slevel + ", pci="
				+ pci + ", tac=" + tac + ", gsm=" + gsm + ", scdma=" + scdma
				+ ", lte=" + lte + ", wcdma=" + wcdma + ", cdma=" + cdma
				+ ", cdmaevdo=" + cdmaevdo + ", cdmaevdoa=" + cdmaevdoa
				+ ", storeTime=" + storeTime + "]";
	}
	
	
}
