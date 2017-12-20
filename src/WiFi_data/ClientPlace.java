package WiFi_data;

public class ClientPlace {

	private GeoModDat position;
	private WIFI wifi;
	public ClientPlace() {
		// TODO Auto-generated constructor stub
	}
	public ClientPlace(GeoModDat pos,WIFI a)
	{
		position=pos;
		wifi=new WIFI(a);
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
	 * @return the wifi
	 */
	public WIFI getWifi() {
		return wifi;
	}
	/**
	 * @param wifi the wifi to set
	 */
	public void setWifi(WIFI wifi) {
		this.wifi = wifi;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClientPlace [position=" + position + ", wifi=" + wifi + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientPlace other = (ClientPlace) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (wifi == null) {
			if (other.wifi != null)
				return false;
		} else if (!wifi.equals(other.wifi))
			return false;
		return true;
	}
	
}
