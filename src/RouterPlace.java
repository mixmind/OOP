
public class RouterPlace {

private String mac;
private int signal;
private GeoModDat position;

public RouterPlace()
{
	
}
public RouterPlace(RouterPlace other)
{
	this(other.getMac(), other.getSignal(),
			other.getPosition().getLat(), 
			other.getPosition().getLon(),
			other.getPosition().getAlt());
}


public RouterPlace(String mac,int signal,double lat,double lon,double alt)
{	
	this.mac=mac;
	this.signal=signal;
	position=new GeoModDat(lat,lon,alt);
	
}

/**
 * @return the mac
 */
public String getMac() {
	return mac;
}

/**
 * @param mac the mac to set
 */
public void setMac(String mac) {
	this.mac = mac;
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
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "RouterPlace [mac=" + mac + ", signal=" + signal + ", position=" + position + "]";
}

}
