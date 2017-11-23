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
		WIFI[] temp=new WIFI[10];
		int count=0;
		for(int i=0;i<nt.getReal_size();i++)
		{
			count=0;
			temp=nt.getLine()[i].getLine();
			for(int j=0;j<temp.length;j++)
				if(temp[j]!=null)
				{
					if(temp[j].getid().equals(name))
					{
						temp[j]=null;
					}
				}
			for(int q=0;q<temp.length;q++)
			{
				if(temp[q]==null) count++;
			}
			if(count==temp.length) 
			{
				nt.getLine()[i]=null;
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
		WIFI[] temp=new WIFI[10];
		int count=0;
		for(int i=0;i<nt.getReal_size();i++)
		{
			count=0;
			temp=nt.getLine()[i].getLine();
			for(int j=0;j<temp.length;j++)
				if(temp[j]!=null)
				{try {
					if(temp[j].getFirtseen().before(checkX.getFirtseen())||
							temp[j].getFirtseen().after(checkY.getFirtseen()))
					{
						temp[j]=null;
					}
				}
				catch(NullPointerException e)
				{
					System.out.println();
				}
				}
			for(int q=0;q<temp.length;q++)
			{
				if(temp[q]==null) count++;
			}
			if(count==temp.length) 
			{
				nt.getLine()[i]=null;
			}
		}
		System.out.println();

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
	public static Network FilterByRadius(double radius,double lat,double lot,Network nt)
	{
		WIFI[] temp=new WIFI[10];
		int count=0;
		for(int i=0;i<nt.getReal_size();i++)
		{
			count=0;
			temp=nt.getLine()[i].getLine();
			for(int j=0;j<temp.length;j++)
				if(temp[j]!=null)
				{
					if((temp[j].getLot()>lot+radius||temp[j].getLot()<lot-radius)||
							(temp[j].getLat()>lat+radius||temp[j].getLat()<lat-radius))
					{
						temp[j]=null;
					}
				}
			for(int q=0;q<temp.length;q++)
			{
				if(temp[q]==null) count++;
			}
			if(count==temp.length) 
			{
				nt.getLine()[i]=null;
			}
		}
		System.out.println();

		return nt;
	}

}

