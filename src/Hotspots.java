public class Hotspots {
	public void setLine(WIFI[] line) {
		this.line = line;
	}
	private WIFI [] line;
	private int real_size;
	private final int INIT_SIZE=1,RESIZE =1,MAX_SIZE=10;
	/**
	 * Constructor
	 */
	public Hotspots()
	{
		line=new WIFI[INIT_SIZE];
		real_size=0;
	}
	/**
	 * Constructor with Wifi to input
	 * @param dot Input Wifi into Wifi array
	 */
	public Hotspots(WIFI dot)
	{
		if(line==null) line=new WIFI[INIT_SIZE];
		if(real_size==line.length)
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
		line[real_size++]=new WIFI(dot);
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
	public WIFI[] getLine() {
		return line;
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

}
