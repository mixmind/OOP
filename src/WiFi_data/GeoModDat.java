package WiFi_data;
import java.io.Serializable;
import java.util.Date;

public class GeoModDat implements Serializable{
	private Date firtseen;
	private String Id;
	private double lat,lon,alt;

	public GeoModDat()
	{

	}
	/**
	 * 
	 * @param lat insert lat
	 * @param lon insert lon
	 * @param alt insert alt
	 */
	public GeoModDat(double lat, double lon, double alt)
	{
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
	}
	/**
	 * 
	 * @param firtseen insert date
	 * @param lat insert lat
	 * @param lon insert lon
	 * @param alt insert alt
	 */
	public GeoModDat(Date firtseen, double lat, double lon, double alt) {
		super();
		this.firtseen = firtseen;
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
	}
	/**
	 * 
	 * @param firtseen insert date
	 * @param lat insert lat
	 * @param lon insert lon
	 * @param alt insert alt
	 * @param Id insert id
	 */
	public GeoModDat(Date firtseen, double lat, double lon, double alt,String Id) {
		super();
		this.firtseen = firtseen;
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
		this.Id=Id;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return Id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		Id = id;
	}

	/**
	 * @return the firtseen
	 */
	public Date getFirtseen() {
		return firtseen;
	}
	/**
	 * @param firtseen the firtseen to set
	 */
	public void setFirtseen(Date firtseen) {
		this.firtseen = firtseen;
	}
	/**
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}
	/**
	 * @param lat the lat to set
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}
	/**
	 * @return the lon
	 */
	public double getLon() {
		return lon;
	}
	/**
	 * @param lon the lon to set
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}
	/**
	 * @return the alt
	 */
	public double getAlt() {
		return alt;
	}
	/**
	 * @param alt the alt to set
	 */
	public void setAlt(double alt) {
		this.alt = alt;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GeoModDat [firtseen=" + firtseen + ", Id=" + Id + ", lat=" + lat + ", lon=" + lon + ", alt=" + alt
				+ "]";
	}

}
