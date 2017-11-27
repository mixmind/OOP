import java.text.ParseException;
import java.util.Date;

public class filterCSV
{
	/**
	 * 
	 * @param name ID for filter	
	 * @param nt database of wifi's
	 * @return filtered database
	 */
	public static Network FilterId(String name,Network nt)
	{
		for(int i=0;i<nt.getReal_size()&&nt.getLine()[i]!=null;i++)
		{
			if(nt.getLine()[i]!=null&&name!=null)
			{
				if(nt.getLine()[i].getDataOfdot().getId().equals(name))
				{
					nt.getLine()[i]=null;
				}
			}

		}
		return nt;

	}
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
		for(int i=0;i<nt.getReal_size()&&nt.getLine()[i]!=null;i++)
		{
			if(nt.getLine()[i]!=null)
			{ 
				try {
					if(nt.getLine()[i].getDataOfdot().getFirtseen().before(checkX.getFirtseen())||
							nt.getLine()[i].getDataOfdot().getFirtseen().after(checkY.getFirtseen()))
					{
						nt.getLine()[i]=null;
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
		for(int i=0;i<nt.getReal_size()&&nt.getLine()[i]!=null;i++)
		{
			if(nt.getLine()[i]!=null)
			{
				if(Math.pow((nt.getLine()[i].getDataOfdot().getLon()-lon), 2)+
				   Math.pow((nt.getLine()[i].getDataOfdot().getLat()-lat), 2)>Math.pow(radius, 2))
					nt.getLine()[i]=null;

			}
		}
		return nt;
	}

}

