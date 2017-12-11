
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class WIFITest {


	public void main(String[] args) throws ParseException 
	{
		test_set_get();
		test_add();

	}
	@Test
	public void test_set_get() throws ParseException
	{
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		WIFI test=new WIFI();
		test.setType(null);
		test.setAccm(21);
		test.setAlt(39);
		test.setAuthmode("TEsting");
		test.setChannel(21);
		test.setFirtseen(format.parse("2017-10-19 15:36:99"));
		test.setFreq("241");
		test.setId("nice?");
		test.setLat(312);
		test.setLon(1221);
		test.setMac("23421");
		test.setRssi(124);
		test.setSsid("check");
		test.setType("wifi");
		if(test.getAccm()!=21) {
			System.err.println(test.getAccm()+" != "+"21");
			throw new RuntimeException("Error: Wifi get isn't working fine");		
		}
		if(test.getAlt()!=39) {
			System.err.println(test.getAlt()+" != "+"39");
			throw new RuntimeException("Error: Wifi get isn't working fine");		
		}
		if(!test.getAuthmode().equals("TEsting")) {
			System.err.println(test.getAuthmode()+" != "+"TEsting");
			throw new RuntimeException("Error: Wifi get isn't working fine");		
		}
		if(test.getChannel()!=21) {
			System.err.println(test.getChannel()+" != "+"21");
			throw new RuntimeException("Error: Wifi get isn't working fine");		
		}
		if(!test.getFirtseen().equals(format.parse("2017-10-19 15:36:99"))) {
			System.err.println(test.getFirtseen()+" != "+"2017-10-19 15:36:99");
			throw new RuntimeException("Error: Wifi get isn't working fine");		
		}
		if(!test.getFreq().equals("241")) {
			System.err.println(test.getFreq()+" != "+"241");
			throw new RuntimeException("Error: Wifi get isn't working fine");		
		}
		if(!test.getid().equals("nice?")) {
			System.err.println(test.getid()+" != "+"nice?");
			throw new RuntimeException("Error: Wifi get isn't working fine");		
		}
		if(!test.getMac().equals("23421")) {
			System.err.println(test.getMac()+" != "+"23421");
			throw new RuntimeException("Error: Wifi get isn't working fine");		
		}
		if(test.getLon()!=1221) {
			System.err.println(test.getLon()+" != "+"1221");
			throw new RuntimeException("Error: Wifi get isn't working fine");		
		}
		if(test.getLat()!=312) {
			System.err.println(test.getLat()+" != "+"312");
			throw new RuntimeException("Error: Wifi get isn't working fine");		
		}
		if(test.getRssi()!=124) {
			System.err.println(test.getRssi()+" != "+"124");
			throw new RuntimeException("Error: Wifi get isn't working fine");		
		}
		if(!test.getSsid().equals("check")) {
			System.err.println(test.getSsid()+" != "+"check");
			throw new RuntimeException("Error: Wifi get isn't working fine");		
		}
		if(!test.getType().equals("wifi")) {
			System.err.println(test.getType()+" != "+"124");
			throw new RuntimeException("Error: Wifi get isn't working fine");		
		}



	}

	@Test
	public void test_add() throws ParseException
	{
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		WIFI test=new WIFI();

		test.setAccm(21);
		test.setAuthmode("TEsting");
		test.setChannel(21);
		test.setFreq("241");
		test.setId("nice?");
		test.setMac("23421");
		test.setRssi(124);
		test.setSsid("check");
		test.setType("wifi");
		WIFI test1=new WIFI(test);
		WIFI test2=new WIFI(test.getMac(), test.getSsid(), test.getAuthmode(),
				test.getChannel(), test.getRssi(),test.getAccm(),test.getType(), null);
		test2.setFreq(test1.getFreq());
		test2.setId(test.getid());
		if(!test.equals(test1))
		{
			System.err.println(test.toString()+"\n != "+test1.toString());
			throw new RuntimeException("Error: Wifi add isn't working fine");	
		}
		if(!test1.equals(test2))
		{
			System.err.println(test1.toString()+"\n != "+test2.toString());
			throw new RuntimeException("Error: Wifi add isn't working fine");	
		}

	}
}
