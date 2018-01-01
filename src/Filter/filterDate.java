package Filter;

import java.text.ParseException;
import java.util.Date;

import WiFi_data.Network;
import WiFi_data.WIFI;

public class filterDate {
	/**
	 * filter
	 * @param x from date
	 * @param y to date 
	 * @param nt database for filter
	 * @return filtered data base
	 * @throws ParseException error parsing
	 */
	public static Network FilterByDate(Date x,Date y,Network nt) throws ParseException
	{
		WIFI checkX=new WIFI();
		checkX.setFirtseen(x);
		WIFI checkY=new WIFI();
		checkY.setFirtseen(y);
		for(int i=0;i<nt.getReal_size()&&nt.getHotspots()[i]!=null;i++)
		{
			if(nt.getHotspots()[i]!=null)
			{ 
				try {
					if(nt.getHotspots()[i].getDataOfdot().getFirtseen().before(checkX.getFirtseen())||
							nt.getHotspots()[i].getDataOfdot().getFirtseen().after(checkY.getFirtseen()))
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
		return nt;
	}
	

}
