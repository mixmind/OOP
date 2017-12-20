package WiFi_data;

public class ClientPlace {

	private GeoModDat position;
	private WIFI wifi;
	private int signal;
	private double weight;
	
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
	/**
	 * @return the signal
	 */
	public int getSignal() {
		return signal;
	}
	/**
	 * @param signal the signal to set
	 */
	public void setSignal(int signal) {
		this.signal = signal;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
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
