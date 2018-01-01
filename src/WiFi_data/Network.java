package WiFi_data;
import java.util.ArrayList;
import java.util.Arrays;

public class Network {
	private Hotspots[] line;
	private int real_size;
	private final int INIT_SIZE=1,RESIZE=1;
	/**
	 * Constructor of Arrays of Hotspots
	 */
	public Network()
	{
		line=new Hotspots[INIT_SIZE];
		real_size=0;
	}
	/**
	 * 
	 * @param size of hotspots
	 */
	public Network(int size)
	{
		line=new Hotspots[size];
		real_size=0;
	}
	/**
	 * 
	 * @param a Add Hotspots
	 */

	public Network(Hotspots[] a)
	{
		line=new Hotspots[a.length];
		for(int i=0;i<a.length;i++)
		{
			line[i]=new Hotspots(a[i]);
			line[i].setDataOfdot(a[i].getDataOfdot());
		}
		real_size=a.length;
	}
	/**
	 * @param dot Input Wifi into Arrays
	 * @param geo input geo
	 */
	public void add(WIFI dot,GeoModDat geo)//adding spot to the array of arrays
	{
		if(dot!=null) {
			if(real_size==line.length)
			{
				resize();
			}
			if(checkFS(dot)&&dot.getid().equals(line[real_size-1].getDataOfdot().getId()))//if last firstseen == new firstseen put it into one array
			{
				line[real_size-1].add(dot);

			}
			else
			{
				if(real_size>0) line[real_size-1].sortRSSI();
				line[real_size++]=new Hotspots(dot);
				line[real_size-1].setDataOfdot(geo);
			}
		}
	}
	/**
	 * 
	 * @param dot Add Wifi
	 */
	public void add(WIFI dot)//adding spot to the array of arrays
	{
		if(dot!=null) {
			if(real_size==line.length)
			{
				resize();
			}
			if(checkFS(dot))//if last firstseen == new firstseen put it into one array
			{
				line[real_size-1].add(dot);

			}
			else
			{
				if(real_size>0) line[real_size-1].sortRSSI();
				line[real_size++]=new Hotspots(dot);
			}
		}
	}
	/**
	 * 
	 * @param a adding spot to the array of arrays
	 */
	public void add(Hotspots a)
	{
		if(a!=null) {
			if(real_size==line.length)
			{
				resize();
			}
			line[real_size++]=new Hotspots(a);
			line[real_size-1].setDataOfdot(a.getDataOfdot());

		}
	}
	/**
	 * 
	 * @param a Add Hotspots
	 * @param data add GeoData
	 */
	public void add(Hotspots a,GeoModDat data)
	{
		if(a!=null) {
			if(real_size==line.length)
			{
				resize();
			}
			line[real_size++]=new Hotspots(a);
			line[real_size-1].setDataOfdot(data);
		}
	}
	/**
	 * Resize length of array if its full
	 */
	private void resize()
	{
		Hotspots temp[]=new Hotspots[line.length+RESIZE];
		for(int i=0;i<line.length;i++)
		{
			temp[i]=line[i];
		}
		line=temp;

	}
	/**
	 * 
	 * @param dot Check if Wifi that in th array found in same time
	 * @return true if yes,false if not
	 */
	private boolean checkFS(WIFI dot)//check date
	{
		if(line[0]==null) return false;
		else if(line[real_size-1].getWIFI()[0].getFirtseen().equals(dot.getFirtseen())&&real_size>0) 
			return true;
		return false;
	}
	/**
	 * 
	 * @return Array of Wifis
	 */
	public Hotspots[] getHotspots() {
		return line;
	}
	/**
	 * 
	 * @return Size of array
	 */
	public int getReal_size() {
		return real_size;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Network [line=" + Arrays.toString(line) + ", real_size=" + real_size + ", INIT_SIZE=" + INIT_SIZE
				+ ", RESIZE=" + RESIZE + "]";
	}
	/**
	 * Sorting by Size
	 */
	public void Sort()
	{
		int maxInd;
		for(int i=0;i<real_size;i++)
		{
			maxInd=getMinPlace(line, i);
			swapPlaces(line,i,maxInd);
		}
	}
	/**
	 * 
	 * @param a Hotspots 
	 * @param start Place for start
	 * @return place of biggest index
	 */
	private static int getMinPlace(Hotspots[] a, int start) {
		int index = start;
		for (int i = start; i < a.length; i++) {
			if(a[i].getReal_size()>a[index].getReal_size())
			{
				index = i;
			}
		}
		return index;
	}
	/**
	 * 
	 * @param a Hotspots
	 * @param i place from swap
	 * @param j place to swap
	 */
	private static void swapPlaces(Hotspots[] a, int i, int j) {
		Hotspots t = a[i];
		a[i]=a[j];
		a[j]=t;
	}


}
