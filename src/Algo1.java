import java.util.ArrayList;
import java.util.Arrays;

import de.micromata.opengis.kml.v_2_2_0.Placemark;



public class Algo1 {
	private final int numDots=3;

	public static ArrayList<RouterPlace> routerPlace(Network nt)
	{
		ArrayList<RouterPlace> rp=add(nt);
		ArrayList<ArrayList<RouterPlace>> routSorted=sortMac(rp);
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
		for(int i=0;i<check.size();i++)
		{
			if(check.get(i).getSignal()!=0) {
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
				w_sum.setPosition(position);
			}
		}
		return w_sum;
	}

	private static  ArrayList<RouterPlace> add(Network nt)
	{
		ArrayList<RouterPlace> temp=new ArrayList<RouterPlace>();
		for(int i=0;i<nt.getReal_size()&&nt.getLine()!=null;i++)
		{
			for(int j=0;j<nt.getLine()[i].getReal_size()&&nt.getLine()[i]!=null&&nt.getLine()[i].getLine()[j]!=null;j++)
			{				
				temp.add(new RouterPlace(
						nt.getLine()[i].getLine()[j].getMac(),
						nt.getLine()[i].getLine()[j].getRssi(),
						nt.getLine()[i].getDataOfdot().getLat(),
						nt.getLine()[i].getDataOfdot().getLon(),
						nt.getLine()[i].getDataOfdot().getAlt()));
			}
		}
		return temp;
	}
	private static ArrayList<ArrayList<RouterPlace>> sortMac(ArrayList<RouterPlace> rp)
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
					rp.remove(j);
				}
			}
			rp.remove(i);
			if(router.size()>1)
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




}
