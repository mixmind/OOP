package Algo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Convert.*;
import DataBase.csvBase;
import WiFi_data.GeoModDat;
import WiFi_data.Network;
import WiFi_data.RouterPlace;
import WiFi_data.WIFI;

public class Algo {

	/**
	 * 
	 * @param file File to find approx location of router
	 */
	public static void routerPlaceAlgo1(String file)
	{
		Network nt=new Network();
		File t=new File(file);
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File file, String name) {
				return name.endsWith(".csv");
			}

		};
		if(filter.accept(t, t.getAbsolutePath()))
		{
			System.out.println("Starting working on: "+t);
			csvBase.check(t, nt);
			ArrayList<RouterPlace> rp=add(nt);
			List<RouterPlace> list = rp;
			Collections.sort(list, new Comparator<RouterPlace>() {
				public int compare(RouterPlace router1, RouterPlace router2)
				{
					return  router1.getMac().compareTo(router2.getMac());
				}
			});
			ArrayList<ArrayList<RouterPlace>> routSorted=sortMac(list);
			sortSignal(routSorted);
			ArrayList<RouterPlace> alg=math(routSorted);
			routerAsp(alg, t.getParent()+"/");
			System.out.println("AlgoRouter ended");
		}
		else System.out.println("Invalid input");


	}

	/**
	 * 
	 * @param al Do algo insert of data
	 * @return Sorted data
	 */
	private static ArrayList<RouterPlace> math(ArrayList<ArrayList<RouterPlace>> al)
	{
		ArrayList<RouterPlace> temp=new ArrayList<>();
		for(int i=0;i<al.size();i++)
		{
			RouterPlace w_center=solve(al.get(i));
			w_center.setMac(al.get(i).get(0).getMac());
			w_center.setSSID(al.get(i).get(0).getSSID());
			w_center.setChannel(al.get(i).get(0).getChannel());
			w_center.setNum_of_macs(al.get(i).size());
			temp.add(w_center);
		}
		return temp;
	}
	/**
	 * 
	 * @param check Data for math
	 * @return Router data 
	 */
	private static RouterPlace solve(ArrayList<RouterPlace> check)
	{
		RouterPlace w_sum=new RouterPlace();
		double weight=0;
		double wAlt=0;
		double wLat=0;
		double wLon=0;
		int count=0;
		for(int i=0;i<check.size();i++)
		{
			if(check.get(i).getSignal()!=0&&count<3) {
				double tempWeight=1/(double)(check.get(i).getSignal()*check.get(i).getSignal());
				double tempwAlt=check.get(i).getPosition().getAlt()*tempWeight;
				double tempwLon=check.get(i).getPosition().getLon()*tempWeight;
				double tempwLat=check.get(i).getPosition().getLat()*tempWeight;
				weight+=tempWeight;
				wLat+=tempwLat;
				wLon+=tempwLon;
				wAlt+=tempwAlt;
			}
			if(i==check.size()-1)
			{
				GeoModDat position=new GeoModDat(wLat/weight, wLon/weight, wAlt/weight);
				position.setId(check.get(i).getPosition().getId());
				position.setFirtseen(check.get(i).getPosition().getFirtseen());
				w_sum.setPosition(position);
			}
		}
		return w_sum;
	}
	/**
	 * 
	 * @param nt Add router to network
	 * @return full network
	 */
	private static  ArrayList<RouterPlace> add(Network nt)
	{
		ArrayList<RouterPlace> temp=new ArrayList<RouterPlace>();
		for(int i=0;i<nt.getReal_size()&&nt.getHotspots()!=null;i++)
		{
			for(int j=0;j<nt.getHotspots()[i].getReal_size()&&nt.getHotspots()[i]!=null&&nt.getHotspots()[i].getWIFI()[j]!=null;j++)
			{				
				GeoModDat position=new GeoModDat(nt.getHotspots()[i].getDataOfdot().getLat(), 
						nt.getHotspots()[i].getDataOfdot().getLon(), 
						nt.getHotspots()[i].getDataOfdot().getAlt());
				position.setFirtseen(nt.getHotspots()[i].getDataOfdot().getFirtseen());
				position.setId(nt.getHotspots()[i].getDataOfdot().getId());
				temp.add(addRouter(nt.getHotspots()[i].getWIFI()[j],position));

			}
		}
		return temp;
	}
	/**
	 * 
	 * @param rp Routers to sort
	 * @return sorted database
	 */
	private static ArrayList<ArrayList<RouterPlace>> sortMac(List<RouterPlace> rp)
	{
		ArrayList<ArrayList<RouterPlace>> temp=new ArrayList<ArrayList<RouterPlace>>();
		for(int i=0;i<rp.size();i++)
		{
			ArrayList<RouterPlace> router=new ArrayList<RouterPlace>();
			router.add(rp.get(i));
			for(int j=i+1;j<rp.size();j++)
			{
				if(rp.get(j).getMac().equals(rp.get(i).getMac()))
				{
					router.add(rp.get(j));
					if(j+1<rp.size())
					{
						if(!rp.get(j+1).getMac().equals(rp.get(i).getMac()))
						{
							i=j;
						}
					}
					else if(j+1>=rp.size())
					{
						i=j;
					}
				}

			}
			temp.add(router);
		}
		return temp;
	}
	/**
	 * 
	 * @param check Routers to sort
	 * @return sorted by signal
	 */
	private static ArrayList<RouterPlace> sortSignal(ArrayList<ArrayList<RouterPlace>> check)
	{
		for(int i=0;i<check.size();i++)
		{
			selectionSort(check.get(i));
		}
		return null;
	}
	/**
	 * 
	 * @param a Routers to sort
	 */
	private static void selectionSort(ArrayList<RouterPlace> a) {
		int minIndex;
		for (int i = 0; i < a.size(); i++) {
			minIndex = getMinIndex(a,i);
			swap(a,i,minIndex);
		}
	}
	/**
	 * 
	 * @param a Routers for swap
	 * @param i swap from
	 * @param j swap to
	 */
	private static void swap(ArrayList<RouterPlace> a, int i, int j) {
		RouterPlace t = a.get(i);
		a.set(i, a.get(j));
		a.set(j, t);
	}
	/**
	 * 
	 * @param a Routers for find
	 * @param start place to search
	 * @return max index
	 */
	private static int getMinIndex(ArrayList<RouterPlace> a, int start) {
		int index = start;
		for (int i = start; i < a.size(); i++) {
			if(a.get(i).getSignal()>a.get(index).getSignal()){
				index = i;
			}
		}
		return index;
	}
	/**
	 * 
	 * @param a WIfi
	 * @param position Geodata
	 * @return Router data fixed
	 */
	private static RouterPlace addRouter(WIFI a,GeoModDat position)
	{
		RouterPlace temp=new RouterPlace(
				position.getId(),
				a.getSsid(),
				position.getFirtseen(),
				a.getMac(),
				a.getRssi(),
				a.getChannel(),
				position.getLat(),
				position.getLon(),
				position.getAlt());
		return temp;
	}
	/**
	 * 
	 * @param temp Routers data
	 * @param folder place to print
	 */
	private static void routerAsp(ArrayList<RouterPlace> temp,String folder)
	{
		try {
			StringBuilder sb = new StringBuilder();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currTime = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new java.util.Date());
			PrintWriter pw = new PrintWriter(new File(folder+currTime+"-wCenter.csv"));
			for(int i=0;i<temp.size();i++)
			{
				sb.append(format.format(temp.get(i).getPosition().getFirtseen())+","
						+temp.get(i).getPosition().getId()+","
						+temp.get(i).getPosition().getLat()+","
						+temp.get(i).getPosition().getLon()+","
						+temp.get(i).getPosition().getAlt()+","
						+temp.get(i).getNum_of_macs()+","
						+temp.get(i).getSSID()+","
						+temp.get(i).getMac()+","
						+temp.get(i).getChannel()+",1");
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