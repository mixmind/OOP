package Algo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import WiFi_data.GeoModDat;
import WiFi_data.Network;
import WiFi_data.RouterPlace;
import WiFi_data.WIFI;

public class Algo {


	public static ArrayList<RouterPlace> routerPlaceAlgo1(Network nt)
	{
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
		return alg;

	}

	
	private static ArrayList<RouterPlace> math(ArrayList<ArrayList<RouterPlace>> al)
	{
		ArrayList<RouterPlace> temp=new ArrayList<>();
		for(int i=0;i<al.size();i++)
		{
			RouterPlace w_center=solve(al.get(i));
			w_center.setMac(al.get(i).get(0).getMac());
			w_center.setSSID(al.get(i).get(0).getSSID());
			w_center.setChannel(al.get(i).get(0).getChannel());
			temp.add(w_center);
		}
		return temp;
	}
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

	private static ArrayList<RouterPlace> sortSignal(ArrayList<ArrayList<RouterPlace>> check)
	{
		for(int i=0;i<check.size();i++)
		{
			selectionSort(check.get(i));
		}
		return null;
	}

	private static void selectionSort(ArrayList<RouterPlace> a) {
		int minIndex;
		for (int i = 0; i < a.size(); i++) {
			minIndex = getMinIndex(a,i);
			swap(a,i,minIndex);
		}
	}

	private static void swap(ArrayList<RouterPlace> a, int i, int j) {
		RouterPlace t = a.get(i);
		a.set(i, a.get(j));
		a.set(j, t);
	}

	private static int getMinIndex(ArrayList<RouterPlace> a, int start) {
		int index = start;
		for (int i = start; i < a.size(); i++) {
			if(a.get(i).getSignal()>a.get(index).getSignal()){
				index = i;
			}
		}
		return index;
	}
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
}