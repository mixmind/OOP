package Filter;

import java.text.ParseException;
import java.util.Date;

import WiFi_data.Network;
import WiFi_data.WIFI;

public class FilterDate {
	private Date start, end;
	
public FilterDate(Date x,Date y)
{
	start=x;
	end=y;
}
	/**
	 * filter
	 * @param x from date
	 * @param y to date 
	 * @param nt database for filter
	 * @return filtered data base
	 * @throws ParseException error parsing
	 */
	public void runOn(Network nt) throws ParseException
	{
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
	}
}
