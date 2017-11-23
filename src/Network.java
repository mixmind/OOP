
public class Network {
	private Hotspots[] line;
	private int real_size;
	private final int INIT_SIZE=3,RESIZE=5;
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
	 * @param dot Input Wifi into Arrays
	 */
	public void add(WIFI dot)//adding spot to the array of arrays
	{
		if(real_size==line.length)
		{
			resize();
		}
		if(checkFS(dot))//if last firstseen== new firstseen put it into one array
		{
			line[real_size-1].add(dot);
		}
		else
			line[real_size++]=new Hotspots(dot);
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
		else if(line[real_size-1].getLine()[0].getFirtseen().equals(dot.getFirtseen())) return true;
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
	 * Set Array of Wifis
	 * @param line Hotpots to set
	 */
	public void setLine(Hotspots[] line) {
		this.line = line;
	}
	/**
	 * 
	 * @return Size of array
	 */
	public int getReal_size() {
		return real_size;
	}


}
