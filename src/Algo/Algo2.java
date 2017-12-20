package Algo;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.event.CaretListener;

import DataBase.csvBase;
import WiFi_data.ClientPlace;
import WiFi_data.Hotspots;
import WiFi_data.Network;
import WiFi_data.RouterPlace;

public class Algo2 {
	private static double power=2;
	private static double sigDif=0.4;
	private static double norm=10000;
	private static double notInList=-120;
	private static double sigDifnotList=100;
	private static double minDif=3;

	public static ArrayList<RouterPlace> clientPlaceAlgo2(String file1,String file2)
	{
		File base=new File(file1);
		File test=new File(file2);
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File file, String name) {
				return name.endsWith(".csv");
			}

		};
		if(filter.accept(base, base.getAbsolutePath())&&filter.accept(test, test.getAbsolutePath())) {
			Network data= new Network();
			csvBase.check(base, data);
			Network fixGeo =new Network();
			csvBase.check(test, fixGeo);
			//ArrayList<RouterPlace> rp=add(data);
			//math1(rp, fixGeo);
			math1(data,fixGeo);
		}
		else {
			System.out.println("Input correct file : *.csv");
		}

		return null;
	}
	private static ArrayList<RouterPlace> math1(Network data,Network fix)
	{
		Network [] allData=new Network[fix.getReal_size()];
		for(int i=0;i<fix.getReal_size();i++)
		{
			checkSim(data, fix.getHotspots()[i]);
		}
		for(int i=0;i<fix.getReal_size();i++)
		{
			//solvePlace(allData[i], fix.getHotspots()[i]);
		}
		System.out.println();
		return null;
	}
	public static void checkSim(Network data,Hotspots fix)
	{
		for(int i=0;i<data.getReal_size();i++)
		{
			find(data.getHotspots()[i],fix);
		}
	}
	public static void find(Hotspots data,Hotspots fix)
	{
		
	}


	private static void sortSig(ArrayList<ClientPlace> data)
	{
		int minInd;
		for(int i=0;i<data.size();i++)
		{
			minInd=minSig(data, i);
			swapSig(data,i,minInd);
		}
	}
	private static int minSig(ArrayList<ClientPlace> data,int start)
	{
		int index=start;
		for(int i=start;i<data.size();i++)
		{
			if(data.get(i).getWifi().getRssi()>data.get(index).getWifi().getRssi())
			{
				index=i;
			}
		}
		return index;
	}
	private static void swapSig(ArrayList<ClientPlace> data,int i,int index)
	{
		ClientPlace temp=data.get(i);
		data.set(i, data.get(index));
		data.set(index, temp);
	}


	/*	
	 * 
	private static Network checkSim(Network data,Hotspots fix)
	{
		boolean test=false;
		Network nex=new Network();
		for(int i=0;i<data.getReal_size();i++)
		{
			test=false;
			for(int j=0;j<data.getHotspots()[i].getReal_size()&&!test;j++)
			{

				for(int k=0;k<fix.getReal_size()&&!test;k++)
				{
					if(data.getHotspots()[i].getWIFI()[j].getMac().equals(fix.getWIFI()[k].getMac()))
					{	
						Hotspots time=addHot(data.getHotspots()[i],fix);
						nex.add(time,time.getDataOfdot());
						test=true;
					}
				}
			}
		}
		nex.Sort();
		return nex;
	}

	private static Hotspots addHot(Hotspots data,Hotspots fix)
	{
		Hotspots temp=new Hotspots();
		temp.setDataOfdot(data.getDataOfdot());
		for(int i=0;i<fix.getReal_size();i++)
		{
			for(int j=0;j<data.getReal_size();j++)
			{
				if(fix.getWIFI()[i].getMac().equals(data.getWIFI()[j].getMac()))
				{
					temp.add(data.getWIFI()[j]);
				}
			}
		}
		return temp;
	}

	private static void solvePlace(Network data,Hotspots fix)
	{
		ArrayList<ClientPlace> temp=new ArrayList<ClientPlace>();
		if(data.getHotspots()[0].getReal_size()==1)
		{
			for(int i=0;i<data.getReal_size();i++)
			{
				ClientPlace one=new ClientPlace(data.getHotspots()[i].getDataOfdot(),
						data.getHotspots()[i].getWIFI()[0]);
				temp.add(one);
			}
			sortSig(temp);
		}
		else {
			WIFI [][] mat=new WIFI[4][data.getHotspots()[0].getReal_size()];
			for(int i=0;i<data.getHotspots()[0].getReal_size();i++)
			{
				for(int j=0;j<fix.getReal_size();j++) {
					if(data.getHotspots()[0].getWIFI()[i].getMac().equals(fix.getWIFI()[j].ma))
					{
						mat[0][i]=fix.getWIFI()[j];
					}
				}
			}
			for(int i=0;i<data.getReal_size()&&i<3;i++)
			{
				for(int j=0;j<data.getHotspots()[i].getReal_size();j++)
				{
					for(int k=0;k<mat[i].length;k++)
					{
						if(data.getHotspots()[i].getWIFI()[j].getMac().equals(mat[0][k].getMac()))
						{
							WIFI my=data.getHotspots()[i].getWIFI()[j];
							my.setPosition(data.getHotspots()[i].getDataOfdot());
							mat[i+1][k]=my;
						}
					}
				}
			}
			double weight=0;
			double wAlt=0;
			double wLat=0;
			double wLon=0;
			double tempWeight[]=new double[3];
			double tempwAlt=0;
			double tempwLon=0;
			double tempwLat=0;
			int count=0;
			int signal=0;
			double tempDiff=0;
			for(int i=1;i<mat.length;i++)
			{
				count=0;

				for(int j=0;j<mat[i].length;j++)
				{
					signal=Math.abs(mat[0][j].getRssi());
					if(mat[i][j]!=null)
					{
						tempDiff=Math.abs(mat[i][j].getRssi()+signal);
						if(tempDiff!=0) {
							if(tempWeight[j]==0) tempWeight[j]=1;
							if(tempDiff==1) tempDiff=minDif;
							tempWeight[j]*=norm/(Math.pow(tempDiff, sigDif)*Math.pow(signal, power));
						}
					}
					else
					{
						if(tempDiff!=0) {
							if(tempWeight[j]==0) tempWeight[j]=1;
							tempWeight[j]*=norm/(Math.pow(sigDifnotList, sigDif)*Math.pow(signal, power));
						}
					}
					count++;
				}
			}
		}
	}

	 * 
	 * 
	 * 
	 * private static ArrayList<RouterPlace> math1(ArrayList<RouterPlace> data,Network fix)
	{

		ArrayList<ArrayList<ArrayList<RouterPlace>>> allData=new ArrayList<ArrayList<ArrayList<RouterPlace>>>();
		for(int i=0;i<fix.getReal_size();i++)
		{

			allData.add(check(fix.getHotspots()[i],data));
			solvePlace(fix.getHotspots()[i],allData.get(i));

		}
		System.out.println();
		return null;
	}
	private static void solvePlace(Hotspots a,ArrayList<ArrayList<RouterPlace>> data)
	{
		double weight=0;
		double wAlt=0;
		double wLat=0;
		double wLon=0;
		double tempWeight[]=new double[3];
		double tempwAlt=0;
		double tempwLon=0;
		double tempwLat=0;
		int count=0;
		int signal=0;
		double tempDiff=0;
		for(int i=0;i<a.getReal_size();i++)
		{
			count=0;
			signal=Math.abs(a.getWIFI()[i].getRssi());
			for(int j=0;j<data.get(i).size()&&count<3;j++)
			{
				if(data.get(i).get(j)!=null)
				{
					tempDiff=Math.abs(data.get(i).get(j).getSignal()+signal);
					if(tempDiff!=0) {
						if(tempWeight[j]==0) tempWeight[j]=1;
						if(tempDiff==1) tempDiff=minDif;
						tempWeight[j]*=norm/(Math.pow(tempDiff, sigDif)*Math.pow(signal, power));
					}
				}
				else
				{
					if(tempDiff!=0) {
						if(tempWeight[j]==0) tempWeight[j]=1;
						tempWeight[j]*=norm/(Math.pow(sigDifnotList, sigDif)*Math.pow(signal, power));
					}
				}
				count++;
			}
		}
	}
	private static ArrayList<ArrayList<RouterPlace>> check(Hotspots a,ArrayList<RouterPlace> data)
	{
		ArrayList<ArrayList<RouterPlace>> rout=new ArrayList<ArrayList<RouterPlace>>();
		for(int i=0;i<a.getReal_size();i++) {

			ArrayList<RouterPlace> forCheck=new ArrayList<RouterPlace>();
			for(int j=0;j<data.size();j++)
			{
				if(a.getWIFI()[i].getMac().equals(data.get(j).getMac()))
				{
					forCheck.add(data.get(j));
				}

			}
			selectionSort(forCheck);
			rout.add(forCheck);
		}
		return rout;
	}*/

}
