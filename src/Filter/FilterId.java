package Filter;

import WiFi_data.Network;

public class FilterId {
	private String name;

	/**
	 * 
	 * @param name ID for filter	
	 */
	public FilterId(String name)
	{
		this.name=name;
	}
	/**
	 * @param nt database of wifi's
	 */
	public void runOn(Network nt)
	{
		for(int i=0;i<nt.getReal_size()&&nt.getHotspots()[i]!=null;i++)
		{
			if(nt.getHotspots()[i]!=null&&name!=null)
			{
				if(!nt.getHotspots()[i].getDataOfdot().getId().equals(name))
				{
					nt.getHotspots()[i]=null;
				}
			}

		}


	}
}
