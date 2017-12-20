package WiFi_data;
import java.util.Date;

public class RouterPlace {

private String mac,SSID;
private int signal,channel;
private GeoModDat position;

public RouterPlace()
{
	
}
public RouterPlace(RouterPlace other)
{
	this(	other.getPosition().getId(),
			other.getPosition().getFirtseen(),
			other.getMac(), 
			other.getSignal(),
			other.getPosition().getLat(), 
			other.getPosition().getLon(),
			other.getPosition().getAlt());
	
}
public RouterPlace(RouterPlace other,String Id,Date first)
{
	this(	Id,
			first,
			other.getMac(), 
			other.getSignal(),
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

public RouterPlace(String id,Date firstseen,String mac,int signal,double lat,double lon,double alt)
{	
	this.mac=mac;
	this.signal=signal;
	position=new GeoModDat(firstseen,lat,lon,alt,id);

	
}
public RouterPlace(String id,String SSID,Date firstseen,String mac,int signal,int channel,double lat,double lon,double alt)
{	
	this.mac=mac;
	this.signal=signal;
	this.SSID=SSID;
	this.channel=channel;
	position=new GeoModDat(firstseen,lat,lon,alt,id);

	
}

/**
 * @return the sSID
 */
public String getSSID() {
	return SSID;
}
/**
 * @param sSID the sSID to set
 */
public void setSSID(String sSID) {
	SSID = sSID;
}
/**
 * @return the channel
 */
public int getChannel() {
	return channel;
}
/**
 * @param channel the channel to set
 */
public void setChannel(int channel) {
	this.channel = channel;
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

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	RouterPlace other = (RouterPlace) obj;
	if (SSID == null) {
		if (other.SSID != null)
			return false;
	} else if (!SSID.equals(other.SSID))
		return false;
	if (channel != other.channel)
		return false;
	if (mac == null) {
		if (other.mac != null)
			return false;
	} else if (!mac.equals(other.mac))
		return false;
	if (position == null) {
		if (other.position != null)
			return false;
	} else if (!position.equals(other.position))
		return false;
	if (signal != other.signal)
		return false;
	return true;
}


}
