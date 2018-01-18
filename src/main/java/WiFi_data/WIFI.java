package WiFi_data;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

public class WIFI implements Serializable{

	/*Creating WIfi spot with all data for compare
	 * 
	 * 
	 */
	private Date firtseen;
	private String mac,ssid,authmode,type,id,freq;
	private int channel,rssi;
	private double lat,lon,alt,accm;
	private GeoModDat position;
	/**
	 * COnstructor
	 */
	public WIFI() {
		super();
	}

	/**
	 * Constructor with all data about WIfi
	 * @param mac of Wifi
	 * @param ssid of Wifi
	 * @param authmode of Wifi
	 * @param channel of Wifi
	 * @param rssi of Wifi
	 * @param accm of Wifi
	 * @param type of Wifi
	 * @param firstseen of Wifi
	 */
	public WIFI(String mac,String ssid,String 
			authmode,int channel,int rssi,double accm,String type,Date firstseen) {
		this.mac=mac;
		this.ssid=ssid;
		this.authmode=authmode;
		this.type=type;
		this.firtseen=firstseen;
		this.channel=channel;
		this.rssi=rssi;
		this.accm=accm;
	}
	/**
	 * Copy Constructor
	 * @param a Wifi to copy from
	 */
	public WIFI(WIFI a) {
		if(a!=null) {
			this.firtseen=a.getFirtseen();
			this.mac=a.getMac();
			this.ssid=a.getSsid();
			this.authmode=a.getAuthmode();
			this.type=a.getType();
			this.channel=a.getChannel();
			this.rssi=a.getRssi();
			this.lat=a.getLat();
			this.lon=a.getLon();
			this.alt=a.getAlt();
			this.accm=a.getAccm();
			this.id=a.getid();
			this.freq=a.getFreq();
		}
	}
	/**
	 * 
	 * @return id of Wifi
	 */
	public String getid() {
		return id;
	}
	/**
	 * Setid of WIfi
	 * @param id Set id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 
	 * @return Mac of WIfi
	 */
	public String getMac() {
		return mac;
	}
	/**
	 * Set mac of Wifi
	 * @param mac Set mac
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}
	/**
	 * 
	 * @return SSID of wifi
	 */
	public String getSsid() {
		return ssid;
	}
	/**
	 * Set SSID of Wifi
	 * @param ssid Set ssid
	 */
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	/**
	 * 
	 * @return Authmode of Wifi
	 */
	public String getAuthmode() {
		return authmode;
	}
	/**
	 * Set Authmode for WIfi
	 * @param authmode Set authmode
	 */
	public void setAuthmode(String authmode) {
		this.authmode = authmode;
	}
	/**
	 * 
	 * @return type of spot
	 */
	public String getType() {
		return type;
	}
	/**
	 * Set type of Spot
	 * @param type Set type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 
	 * @return When firstseen WIfi
	 */
	public Date getFirtseen() {
		return firtseen;
	}
	/**
	 * Set firstseen of Wifi
	 * @param firtseen set Date
	 * @throws ParseException throws exception
	 */
	public void setFirtseen(Date firtseen) throws ParseException {
		this.firtseen = firtseen;
	}
	/**
	 * 
	 * @return Channel of Wifi
	 */
	public int getChannel() {
		return channel;
	}
	/**
	 * Set channel of Wifi
	 * @param channel Set channel
	 */
	public void setChannel(int channel) {
		this.channel = channel;
	}
	/**
	 * 
	 * @return Signal of Wifi
	 */
	public int getRssi() {
		return rssi;
	}
	/**
	 * Set signal of Wifi
	 * @param rssi Set rssi
	 */
	public void setRssi(int rssi) {
		this.rssi = rssi;
	}
	/**
	 * 
	 * @return Lat of Wifi
	 */
	public double getLat() {
		return lat;
	}
	/**
	 * Set Lat of WIfi
	 * @param lat Set Lat
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}
	/**
	 * 
	 * @return Lon of WIfi
	 */
	public double getLon() {
		return lon;
	}
	/**
	 * Set Lon of Wifi
	 * @param lon Set Lon
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}
	/**
	 * 
	 * @return Alt of Wifi
	 */
	public double getAlt() {
		return alt;
	}
	/**
	 * Set Alt of WIfi
	 * @param alt Set alt
	 */
	public void setAlt(double alt) {
		this.alt = alt;
	}
	/**
	 * 
	 * @return Accm of WIfi
	 */
	public double getAccm() {
		return accm;
	}
	/**
	 * Set Accm of Wifi
	 * @param accm Set accm
	 */
	public void setAccm(double accm) {
		this.accm = accm;
	}
	/**
	 * 
	 * @return freq of WIfi
	 */
	public String getFreq() {
		return freq;
	}
	/**
	 * Set Freq of Wifi
	 * @param freq Set freq
	 */
	public void setFreq(String freq) {
		this.freq = freq;
	}
	/**
	 * @return the position
	 */
	public GeoModDat getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(GeoModDat position) {
		this.position = position;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/* (non-Javadoc) Checking equels
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(WIFI obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WIFI other = (WIFI) obj;
		if (Double.doubleToLongBits(accm) != Double.doubleToLongBits(other.accm))
			return false;
		if (Double.doubleToLongBits(alt) != Double.doubleToLongBits(other.alt))
			return false;
		if (authmode == null) {
			if (other.authmode != null)
				return false;
		} else if (!authmode.equals(other.authmode))
			return false;
		if (channel != other.channel)
			return false;
		if (firtseen == null) {
			if (other.firtseen != null)
				return false;
		} else if (!firtseen.equals(other.firtseen))
			return false;
		if (freq == null) {
			if (other.freq != null)
				return false;
		} else if (!freq.equals(other.freq))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(lat) != Double.doubleToLongBits(other.lat))
			return false;
		if (Double.doubleToLongBits(lon) != Double.doubleToLongBits(other.lon))
			return false;
		if (mac == null) {
			if (other.mac != null)
				return false;
		} else if (!mac.equals(other.mac))
			return false;
		if (rssi != other.rssi)
			return false;
		if (ssid == null) {
			if (other.ssid != null)
				return false;
		} else if (!ssid.equals(other.ssid))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	/**
	 * print all data of Wifi
	 */
	public String toString() {
		return   "mac:"+mac +"\nssid:"+ ssid +"\nProtection:"+ authmode + "\nDate:"+firtseen + "\nFrequency:"+
				channel + "\nPower:"+ rssi +"\nType:"+type+"\nID:"+id ;
	}

}
