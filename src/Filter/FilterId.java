package Filter;

import WiFi_data.Network;

public class FilterId implements filterCSV{
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
	public Network runOn(Network nt)
	{
		Network temp=new Network();
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
		for(int i=0;i<nt.getReal_size();i++)
		{
			if(nt.getHotspots()[i]!=null)
			temp.add(nt.getHotspots()[i]);
		}
		return temp;
	}
	/**
	 * @param nt database of wifi's
	 */
	public Network runNot(Network nt)
	{
		Network temp = new Network();
		for(int i=0;i<nt.getReal_size()&&nt.getHotspots()[i]!=null;i++)
		{
			if(nt.getHotspots()[i]!=null&&name!=null)
			{
				if(nt.getHotspots()[i].getDataOfdot().getId().equals(name))
				{
					nt.getHotspots()[i]=null;
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
