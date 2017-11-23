import java.text.ParseException;
import java.util.Date;

public class WIFI {

	/*Creating WIfi spot with all data for compare
	 * 
	 * 
	 */

	private Date firtseen;
	private String mac,ssid,authmode,type,id,freq;
	private int channel,rssi;
	private double lat,lot,alt,accm;
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
	 * @param firtseen of Wifi
	 * @param channel of Wifi
	 * @param rssi of Wifi
	 * @param lat of Wifi
	 * @param lot of Wifi
	 * @param alt of Wifi
	 * @param accm of Wifi
	 * @param type of Wifi
	 */
	public WIFI(String mac,String ssid,String 
			authmode,Date firtseen,int channel,int rssi,double lat,
			double lot,double alt,double accm,String type) {
		this.firtseen=firtseen;
		this.mac=mac;
		this.ssid=ssid;
		this.authmode=authmode;
		this.type=type;
		this.channel=channel;
		this.rssi=rssi;
		this.lat=lat;
		this.lot=lot;
		this.alt=alt;
		this.accm=accm;
	}
	/**
	 * Copy Constructor
	 * @param a Wifi to copy from
	 */
	public WIFI(WIFI a) {
		this.firtseen=a.getFirtseen();
		this.mac=a.getMac();
		this.ssid=a.getSsid();
		this.authmode=a.getAuthmode();
		this.type=a.getType();
		this.channel=a.getChannel();
		this.rssi=a.getRssi();
		this.lat=a.getLat();
		this.lot=a.getLot();
		this.alt=a.getAlt();
		this.accm=a.getAccm();
		this.id=a.getid();
		this.freq=a.getFreq();
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
	 */
	public void setFirtseen(Date firtseen) throws ParseException {
		this.firtseen = firtseen;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(accm);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(alt);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((authmode == null) ? 0 : authmode.hashCode());
		result = prime * result + channel;
		result = prime * result + ((firtseen == null) ? 0 : firtseen.hashCode());
		result = prime * result + ((freq == null) ? 0 : freq.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		temp = Double.doubleToLongBits(lat);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lot);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((mac == null) ? 0 : mac.hashCode());
		result = prime * result + rssi;
		result = prime * result + ((ssid == null) ? 0 : ssid.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
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
	 * @return Lot of WIfi
	 */
	public double getLot() {
		return lot;
	}
	/**
	 * Set lot of Wifi
	 * @param lot Set lot
	 */
	public void setLot(double lot) {
		this.lot = lot;
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
	 * print all data of Wifi
	 */
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
		if (Double.doubleToLongBits(lot) != Double.doubleToLongBits(other.lot))
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
	public String toString() {
		return   "mac:"+mac +"\nssid:"+ ssid +"\nProtection:"+ authmode + "\nDate:"+firtseen + "\nFrequency:"+
				channel + "\nPower:"+ rssi +"\nType:"+type+"\nID:"+id ;
	}

}
