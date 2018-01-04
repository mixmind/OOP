package Convert;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Algo.Algo;
import DataBase.csvBase;
import WiFi_data.Network;
import WiFi_data.RouterPlace;
import WiFi_data.Sort;
import WiFi_data.WIFI;

public class toCSV {
	Network nt;
	/**
	 * 
	 * @param folder Receive folder of csv's to check and merge
	 */
	public toCSV(Network nt) 	{
		int count=0;
		this.nt=nt;
		String currTime = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new java.util.Date());
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			nt= new Network(Sort.mergeSort(nt));
			if(nt.getReal_size()!=0) {
				System.out.println("Read csv complete.");
				PrintWriter pw = new PrintWriter(new File(currTime+".csv"));
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
						if(nt.getHotspots()[i]!=null)
						{
							temp=nt.getHotspots()[i].getWIFI();
							if(temp[j]!=null) {
								count++;
								sb.append(format.format(nt.getHotspots()[i].getDataOfdot().getFirtseen())+","
										+nt.getHotspots()[i].getDataOfdot().getId()+","+nt.getHotspots()[i].getDataOfdot().getLat()+","+
										nt.getHotspots()[i].getDataOfdot().getLon()+","+nt.getHotspots()[i].getDataOfdot().getAlt()+","+temp.length+",");
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


}
