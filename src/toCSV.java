import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class toCSV {
	/**
	 * 
	 * @param folder Receive folder of csv's to check and merge
	 */
	public toCSV(String folder) 	{
		Network nt;int count=0;
		String currTime = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new java.util.Date());
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			nt = csvBase.readCSV(folder);
			//nt= new Network(Sort.mergeSort(nt));
			routerAsp(nt,folder);
			if(nt.getReal_size()!=0) {
				System.out.println("Read csv complete.");
				PrintWriter pw = new PrintWriter(new File(folder+currTime+".csv"));
				StringBuilder sb = new StringBuilder();
				sb.append("Time,ID,LAT,LON,ALT,#WiFi networks,");
				try 
				{
					for(int i=1;i<=10;i++)
					{
						sb.append("SSID"+ i +",Mac"+ i +",Frequncy"+ i +",Signal"+ i +"," );
					}
					sb.append("\n");
					int max=nt.getReal_size();
					WIFI[] temp=new WIFI[10];
					int j=0;
					for(int i=0;i<max;i++)
					{
						j=0;
						if(nt.getLine()[i]!=null)
						{
							temp=nt.getLine()[i].getLine();
							if(temp[j]!=null) {
								count++;
								sb.append(format.format(nt.getLine()[i].getDataOfdot().getFirtseen())+","
										+nt.getLine()[i].getDataOfdot().getId()+","+nt.getLine()[i].getDataOfdot().getLat()+","+
										nt.getLine()[i].getDataOfdot().getLon()+","+nt.getLine()[i].getDataOfdot().getAlt()+","+temp.length+",");
								for(;j<temp.length&&temp[j]!=null;j++)
								{
									if(temp[j]!=null) 
									{
										/*String freq;
										if(temp[j].getChannel()==0)
										{
											freq="gsm";
										}
										else if(temp[j].getChannel()>35)
											freq= "5 GHZ";
										else freq= "2.4 GHZ";                               /*freq*/
										sb.append(temp[j].getSsid()+","+temp[j].getMac()+","+temp[j].getChannel()+","+temp[j].getRssi()+",");
									}
								}
							}
							sb.append("\n");
						}
					}
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
				}
				finally {
					System.out.println("Write csv file complete.");
					System.out.println(count);
				}
				pw.append(sb.toString());
				pw.close();
			}
			else System.out.println("File csv not found.");

		} catch (NumberFormatException | IOException | NullPointerException e1) {
			System.out.println(e1);
		}
	}

	private void routerAsp(Network nt,String folder)
	{
		try {
		ArrayList<RouterPlace> temp=Algo1.routerPlace(nt);
		StringBuilder sb = new StringBuilder();
		PrintWriter pw = new PrintWriter(new File(folder+"wCenter.csv"));
		
			for(int i=0;i<temp.size();i++)
			{
				sb.append(temp.get(i).getPosition().getFirtseen()+","
						+temp.get(i).getPosition().getId()+","
						+temp.get(i).getPosition().getLat()+","
						+temp.get(i).getPosition().getLon()+","
						+temp.get(i).getPosition().getAlt()+",1,"
						+temp.get(i).getSSID()+","
						+temp.get(i).getMac()+","
						+temp.get(i).getChannel());
				sb.append("\n");
			}
			pw.append(sb.toString());
			pw.close();
		}
		catch(FileNotFoundException e1)
		{
			System.out.println(e1.getMessage());
		}
		
	}
}
