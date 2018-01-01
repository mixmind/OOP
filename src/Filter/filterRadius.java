package Filter;

import WiFi_data.Network;

public class filterRadius {
	/**
	 * 
	 * @param radius radius for filter
	 * @param lat Lat for filter
	 * @param lot Lot for filter	
	 * @param nt database for filter
	 * @return filtered database
	 */
	public static Network FilterByRadius(double radius,double lat,double lon,Network nt)
	{
		for(int i=0;i<nt.getReal_size()&&nt.getHotspots()[i]!=null;i++)
		{
			if(nt.getHotspots()[i]!=null)
			{
				try
				{
					if(Math.pow((nt.getHotspots()[i].getDataOfdot().getLon()-lon), 2)+
				   Math.pow((nt.getHotspots()[i].getDataOfdot().getLat()-lat), 2)>Math.pow(radius, 2))
					nt.getHotspots()[i]=null;
				}
				catch(NullPointerException e)
				{
					System.out.println(e.getMessage());
				}

			}
		}
		return nt;
	}
}
