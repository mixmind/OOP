import java.util.Date;

public class GeoModDat {
	private Date firtseen;
	private String Id;
	
	public GeoModDat(Date firtseen, double lat, double lon, double alt) {
		super();
		this.firtseen = firtseen;
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
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
	private double lat,lon,alt;
	

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
}