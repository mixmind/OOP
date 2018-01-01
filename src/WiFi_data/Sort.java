package WiFi_data;

public class Sort {
	/**
	 * 
	 * @param a Network to be sorted
	 * @return array of hotspots
	 */
	public static Hotspots[] mergeSort(Network a) {
		Hotspots[] temp =new Hotspots[a.getReal_size()];
		for(int i=0;i<temp.length;i++)
		{
			temp[i]=new Hotspots(a.getHotspots()[i]);
			temp[i].setDataOfdot(a.getHotspots()[i].getDataOfdot());
		}
		mergeSort(temp,0,temp.length);
		return temp;
	}
	/**
	 * 
	 * @param a Hotspots
	 * @param low index min
	 * @param high index max
	 */
	private static void mergeSort(Hotspots[] a, int low, int high) {
		int n = high - low;
		if(n <= 1) return;
		int mid = (low + high)/2;
		mergeSort(a,low,mid);
		mergeSort(a,mid,high);
		int i = low, j = mid, k = 0;
		Hotspots[] temp = new Hotspots[n];
		while(i<mid && j<high) {
			if(a[i].getDataOfdot().getFirtseen().before(a[j].getDataOfdot().getFirtseen()))
				temp[k++] = a[i++];
			else temp[k++] = a[j++];
		}
		while(i<mid) temp[k++] = a[i++];
		while(j<high) temp[k++] = a[j++];
		for (int l = 0; l < n; l++) {
			a[low + l] = temp[l]; 
		}	
	}
}

