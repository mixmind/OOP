import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class filterCSVTest {

	public  void main(String[] args) throws ParseException {
		filter();
	}
	@Test
	public void filter() throws ParseException {
		Network nt=new Network();
		WIFI wi=new WIFI();
		wi.setAlt(221);
		nt.add(wi);
		Date x=null;
		Date y=null;
		filterCSV.FilterByDate(x, y, nt);
		filterCSV.FilterByRadius(124, Integer.MIN_VALUE, Integer.MAX_VALUE, nt);
		filterCSV.FilterId(null, nt);
	}

}
