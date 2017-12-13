
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
	 * Constructor of Arrays of Hotspots
	 */
	public Network(int size)
	{
		line=new Hotspots[size];
		real_size=0;
	}
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
	 * 
	 * @param dot Input Wifi into Arrays
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
		else if(line[real_size-1].getLine()[0].getFirtseen().equals(dot.getFirtseen())&&real_size>0) 
			return true;
	 return false;
	}
	/**
	 * 
	 * @return Array of Wifis
	 */
	public Hotspots[] getLine() {
		return line;
	}
	/**
	 * 
	 * @return Size of array
	 */
	public int getReal_size() {
		return real_size;
	}


}
