package Test;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

import Filter.filterCSV;
import WiFi_data.Network;
import WiFi_data.WIFI;

public class filterCSVTest {

	public  void main(String[] args) throws ParseException {
		filter();
	}
	@Test
	public void filter() throws ParseException {
		Network nt=new Network();
		WIFI wi=new WIFI();
		wi.setAlt(221);
		nt.add(wi,null);
		Date x=null;
		Date y=null;
		//filterCSV.FilterByDate(x, y, nt);
		//filterCSV.FilterByRadius(124, Integer.MIN_VALUE, Integer.MAX_VALUE, nt);
		//filterCSV.FilterId(null, nt);
	}

}
