package WiFi_data;
import java.io.Serializable;
import java.util.Arrays;

public class Hotspots implements Serializable{

	private WIFI [] line;
	private int real_size;
	private final int INIT_SIZE=1,RESIZE =1,MAX_SIZE=10;
	private GeoModDat dataOfdot;
	/**
	 * Constructor
	 */
	public Hotspots()
	{
		line=new WIFI[INIT_SIZE];
		real_size=0;
	}
	/**
	 * 
	 * @param a copy constructor
	 */
	public Hotspots(Hotspots a)
	{
		line=new WIFI[a.getReal_size()];
		for(int i=0;i<a.getReal_size();i++)
		{
			line[i]=new WIFI(a.getWIFI()[i]);
		}
		real_size=a.getReal_size();
		
	}

	/**
	 * Constructor with Wifi to input
	 * @param dot Input Wifi into Wifi array
	 */

	public Hotspots(WIFI dot)
	{
		if(line==null) line=new WIFI[INIT_SIZE];

		if(real_size==line.length)	resize();
		if(real_size==MAX_SIZE)
		{
			int i=checkSignal(dot);
			if(i!=-1)
			{
				line[i]=new WIFI(dot);	
			}
		}
		if(dot!=null) line[real_size++]=new WIFI(dot);
	}

	/**
	 * change size of array
	 */
	private void resize()
	{
		WIFI temp[]=new WIFI[line.length+RESIZE];
		for(int i=0;i<line.length;i++)
		{
			temp[i]=line[i];
		}
		line=temp;

	}
	/**
	 * 
	 * @param dot input Wifi into array
	 */
	public void add(WIFI dot)
	{

		if(line==null) line=new WIFI[INIT_SIZE];
		if(real_size==line.length&&real_size!=MAX_SIZE)
		{
			resize();
		}
		if(real_size==MAX_SIZE)
		{
			int i=checkSignal(dot);
			if(i!=-1)
			{
				line[i]=new WIFI(dot);	
			}
		}
		else line[real_size++]=new WIFI(dot);
	}
	/**
	 * @return the dataOfdot
	 */
	public GeoModDat getDataOfdot() {
		return dataOfdot;
	}
	/**
	 * @param dataOfdot the dataOfdot to set
	 */
	public void setDataOfdot(GeoModDat dataOfdot) {
		this.dataOfdot = dataOfdot;
	}
	/**
	 * 
	 * @param check if GeoData is equal
	 * @return if true
	 */
	public boolean equalGeo(GeoModDat check)
	{
		return check.getAlt()==dataOfdot.getAlt()&&check.getLon()==dataOfdot.getLon()&&
				check.getAlt()==dataOfdot.getAlt()&&check.getFirtseen().equals(dataOfdot.getFirtseen())&&
				check.getId().equals(dataOfdot.getId());

	}
	/**
	 * 
	 * @return Returning size of array
	 */
	public int getReal_size() {
		return real_size;
	}
	/**
	 * 
	 * @return returning Array of Wifis
	 */
	public WIFI[] getWIFI() {
		return line;
	}
	/**
	 * Sort by signal
	 */
	public void sortRSSI()
	{
		selectionSort(line);
	}
	/**
	 * 
	 * @param a Wifis to sort
	 */
	private static void selectionSort(WIFI[] a) {
		int minIndex;
		for (int i = 0; i < a.length; i++) {
			minIndex = getMinIndex(a,i);
			swap(a,i,minIndex);
		}
	}
	/**
	 * 
	 * @param a Wifis
	 * @param i index from swap
	 * @param j index to swap
	 */
	private static void swap(WIFI[] a, int i, int j) {
		WIFI t = a[i];
		a[i] = new WIFI(a[j]);
		a[j] = new WIFI(t);
	}
	/**
	 * 
	 * @param a Wifis
	 * @param start start point for check
	 * @return index of max
	 */
	private static int getMinIndex(WIFI[] a, int start) {
		int index = start;
		for (int i = start; i < a.length; i++) {
			if(a[i].getRssi()>a[index].getRssi()){
				index = i;
			}
		}
		return index;
	}
	/**
	 * Check signal of Wifi before import
	 * @param dot Wifi to check
	 * @return place of import or -1 if its doesn't fit
	 */
	private int checkSignal(WIFI dot)
	{
		int max=line[0].getRssi();
		int index=0;
		for(int i=1;i<real_size;i++)
		{
			if(line[i].getRssi()<=max)
			{
				max=line[i].getRssi();
				index=i;
			}
		}
		if(max<=dot.getRssi()) return index;
		else return -1;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " real_size=" + real_size;
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
		Hotspots other = (Hotspots) obj;
		if (INIT_SIZE != other.INIT_SIZE)
			return false;
		if (MAX_SIZE != other.MAX_SIZE)
			return false;
		if (RESIZE != other.RESIZE)
			return false;
		if (dataOfdot == null) {
			if (other.dataOfdot != null)
				return false;
		} else if (!dataOfdot.equals(other.dataOfdot))
			return false;
		if (!Arrays.equals(line, other.line))
			return false;
		if (real_size != other.real_size)
			return false;
		return true;
	}




}
