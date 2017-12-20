package Algo;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.event.CaretListener;

import DataBase.csvBase;
import WiFi_data.ClientPlace;
import WiFi_data.GeoModDat;
import WiFi_data.Hotspots;
import WiFi_data.Network;
import WiFi_data.RouterPlace;
import WiFi_data.Sort;
import WiFi_data.WIFI;

public class Algo2 {
	private static double power=2;
	private static double sigDif=0.4;
	private static double norm=10000;
	private static double notInList=-120;
	private static double sigDifnotList=100;
	private static double minDif=3;

	public static void clientPlaceAlgo2(String file1,String file2)
	{
		File base=new File(file1);
		File test=new File(file2);
		Network fixGeo =new Network();
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File file, String name) {
				return name.endsWith(".csv");
			}

		};
		if(filter.accept(base, base.getAbsolutePath())&&filter.accept(test, test.getAbsolutePath())) {
			Network data= new Network();
			csvBase.check(base, data);
			fixGeo =new Network();
			csvBase.check(test, fixGeo);
			math(data,fixGeo);
		}
		else {
			System.out.println("Input correct file : *.csv");
		}

		toCSV(fixGeo, test.getAbsolutePath());;
	}
	private static void math(Network data,Network fix)
	{
		for(int i=0;i<fix.getReal_size();i++)
		{
			checkSim(data, fix.getHotspots()[i]);
		}
	}
	public static void checkSim(Network data,Hotspots fix)
	{
		ArrayList<ClientPlace> arr=new ArrayList<ClientPlace>();
		int count=0;
		for(int i=0;i<data.getReal_size();i++)
		{
			if(find(data.getHotspots()[i],fix).getWeight()!=-100)
				arr.add(find(data.getHotspots()[i],fix));
		}
		sort(arr);
		double wAlt=0;
		double wLat=0;
		double wLon=0;
		double weight=0;
		for(int i=0;i<arr.size()&&i<3;i++)
		{
			wLat+=arr.get(i).getPosition().getLat()*arr.get(i).getWeight();
			wLon+=arr.get(i).getPosition().getLon()*arr.get(i).getWeight();
			wAlt+=arr.get(i).getPosition().getAlt()*arr.get(i).getWeight();
			weight+=arr.get(i).getWeight();
		}
		if(weight!=0) {
			wAlt/=weight;
			wLon/=weight;
			wLat/=weight;
			GeoModDat tempDat=new GeoModDat();
			tempDat.setAlt(wAlt);
			tempDat.setLat(wLat);
			tempDat.setLon(wLon);
			fix.setDataOfdot(tempDat);
		}

	}
	private static void toCSV(Network nt,String folder) 	{
		int count=0;
		String currTime = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new java.util.Date());
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		File t=new File(folder);
		try {
			if(nt.getReal_size()!=0) {
				System.out.println("Read csv complete.");
				PrintWriter pw = new PrintWriter(new File(t.getParent()+"/"+currTime+".csv"));
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
								sb.append(format.format(temp[j].getFirtseen())+","+temp[j].getId()+","+nt.getHotspots()[i].getDataOfdot().getLat()+","+
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
	public static ClientPlace find(Hotspots data,Hotspots fix)

	{
		ClientPlace emt=new ClientPlace();
		boolean test=false;
		double tempWeight=-100;
		int signal=0;
		double tempDiff=0;
		for(int i=0;i<fix.getReal_size();i++)
		{
			test=false;
			signal=Math.abs(fix.getWIFI()[i].getRssi());
			for(int j=0;j<data.getReal_size();j++)
			{
				tempDiff=Math.abs(data.getWIFI()[j].getRssi()+signal);
				if(fix.getWIFI()[i].getMac().equals(data.getWIFI()[j].getMac()))
				{
					if(tempWeight==-100) tempWeight=1;
					if(tempDiff==1||tempDiff==0) tempDiff=minDif;
					tempWeight*=norm/(Math.pow(tempDiff, sigDif)*Math.pow(signal, power));
					emt.setPosition(data.getDataOfdot());
					test=true;
				}
				else if(test)
				{
					if(tempDiff!=0) {
						if(tempWeight==-100) tempWeight=1;
						tempWeight*=norm/(Math.pow(sigDifnotList, sigDif)*Math.pow(signal, power));
					}
				}
			}
		}
		emt.setWeight(tempWeight);
		return emt;
	}

	public static void sort(ArrayList<ClientPlace> arr)
	{
		int index;
		for(int i=0;i<arr.size();i++)
		{
			index=maxInd(arr,i);
			swap(arr,i,index);
		}
	}
	public static int maxInd(ArrayList<ClientPlace> arr,int start)
	{
		int index=start;
		for(int i=start;i<arr.size();i++)
		{
			if(arr.get(i).getWeight()>arr.get(index).getWeight())
			{
				index=i;
			}
		}
		return index;
	}

	public static void swap(ArrayList<ClientPlace> arr,int i,int index)
	{
		ClientPlace temp=arr.get(i);
		arr.set(i,arr.get(index));
		arr.set(index, temp);
	}
}
