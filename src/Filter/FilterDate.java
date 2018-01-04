package Filter;
import java.util.Date;
import WiFi_data.Network;

public class FilterDate implements filterCSV{
	private Date start, end;

	/**
	 * @param x from date
	 * @param y to date 
	 */
	public FilterDate(Date x,Date y)
	{
		start=x;
		end=y;
	}
	/**
	 * filter
	 * @param nt database for filter
	 */
	public Network runOn(Network nt)
	{
		Network temp=new Network();
		for(int i=0;i<nt.getReal_size()&&nt.getHotspots()[i]!=null;i++)
		{
			if(nt.getHotspots()[i]!=null)
			{ 
				try {
					if(nt.getHotspots()[i].getDataOfdot().getFirtseen().before(start)||
							nt.getHotspots()[i].getDataOfdot().getFirtseen().after(end))
					{
						nt.getHotspots()[i]=null;
					}
				}
				catch(NullPointerException e)
				{
					System.out.println("The date is null ");
				}
			}
		}
		for(int i=0;i<nt.getReal_size();i++)
		{
			if(nt.getHotspots()[i]!=null)
			temp.add(nt.getHotspots()[i]);
		}
		return temp;
	}
	/**
	 * filter
	 * @param nt database for filter
	 */
	public Network runNot(Network nt)
	{
		Network temp=new Network();
		for(int i=0;i<nt.getReal_size()&&nt.getHotspots()[i]!=null;i++)
		{
			if(nt.getHotspots()[i]!=null)
			{ 
				try {
					if(nt.getHotspots()[i].getDataOfdot().getFirtseen().before(end)&&
							nt.getHotspots()[i].getDataOfdot().getFirtseen().after(start))
					{
						nt.getHotspots()[i]=null;
					}
				}
				catch(NullPointerException e)
				{
					System.out.println("The date is null ");
				}
			}
		}
		for(int i=0;i<nt.getReal_size();i++)
		{
			if(nt.getHotspots()[i]!=null)
			temp.add(nt.getHotspots()[i]);
		}
		return temp;
	}
}
