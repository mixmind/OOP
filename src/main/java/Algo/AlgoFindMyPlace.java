	package Algo;

    import WiFi_data.*;

    import java.util.ArrayList;


public class AlgoFindMyPlace {

		private static double power=2;
		private static double sigDif=0.4;
		private static double norm=10000;
		private static double notInList=-120;
		private static double sigDifnotList=100;
		private static double minDif=3;

	/**
	 *
	 * @param mac1 mac to compare
	 * @param mac2 mac to compare
	 * @param mac3 mac to compare
	 * @param signal1 signal to compare
	 * @param signal2 signal to compare
	 * @param signal3 signal to compare
	 * @param data data for save
	 * @return Geomodata
	 */
		public static GeoModDat clientPlaceAlgo2(String mac1,String mac2,String mac3,String signal1,String signal2,String signal3,Network data)
		{
				Hotspots fix=new Hotspots();
				WIFI a =new WIFI();a.setMac(mac1);a.setRssi(Integer.parseInt(signal1));
				WIFI b =new WIFI();b.setMac(mac2);b.setRssi(Integer.parseInt(signal2));
				WIFI c =new WIFI();c.setMac(mac1);c.setRssi(Integer.parseInt(signal3));
				fix.add(a);fix.add(b);fix.add(c);
				System.out.println("AlgoPlace ended.");
				checkSim(data, fix);
			return fix.getDataOfdot();
		}
		
		/**
		 * 
		 * @param data Database of wifis
		 * @param fix line of bugged wifis
		 */
		public static void checkSim(Network data,Hotspots fix)
		{
			ArrayList<ClientPlace> arr=new ArrayList<ClientPlace>();
			for(int i=0;i<data.getReal_size();i++)
			{
				ClientPlace temp=find(data.getHotspots()[i],fix);
				if(temp.getWeight()!=-100) arr.add(temp);
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
			if(weight==0)
			{
				GeoModDat tempDat=new GeoModDat();
				tempDat.setAlt(0);
				tempDat.setLat(0);
				tempDat.setLon(0);
				tempDat.setFirtseen(fix.getDataOfdot().getFirtseen());
				tempDat.setId(fix.getDataOfdot().getId());
				fix.setDataOfdot(tempDat);
				System.out.println("mac: "+fix.getWIFI()[0].getMac()+" not found. His place will be 0,0,0.");
			}

		}
		/**
		 * 
		 * @param data Line of wifis for check
		 * @param fix LIne of wifis to fix
		 * @return Database of fix information
		 */
		public static ClientPlace find(Hotspots data,Hotspots fix)

		{
			ClientPlace emt=new ClientPlace();
			boolean test=false;
			double tempWeight=-100;
			int signal=0;
			double tempDiff=0;
			int count=0;
			int countCH=3;
			for(int i=0;i<fix.getReal_size();i++)
			{
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
						count++;
					}
					if(test&&(i==fix.getReal_size()-1&&j==data.getReal_size()-1))
					{
						while(count++<countCH) {
							if(tempDiff!=0)
							{
								if(tempWeight==-100) tempWeight=1;
								tempWeight*=norm/(Math.pow(sigDifnotList, sigDif)*Math.pow(signal, power));

							}
						}
					}
				}

			}
			emt.setWeight(tempWeight);
			return emt;
		}
		/**
		 * 
		 * @param arr Arraylist to sort
		 */
		public static void sort(ArrayList<ClientPlace> arr)
		{
			int index;
			for(int i=0;i<arr.size();i++)
			{
				index=maxInd(arr,i);
				swap(arr,i,index);
			}
		}
		/**
		 * 
		 * @param arr Arraylist to sort
		 * @param start start point
		 * @return max index
		 */
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
		/**
		 * 
		 * @param arr Arraylist for check
		 * @param i place from swap
		 * @param index place to swap
		 */
		public static void swap(ArrayList<ClientPlace> arr,int i,int index)
		{
			ClientPlace temp=arr.get(i);
			arr.set(i,arr.get(index));
			arr.set(index, temp);
		}
		
	}


