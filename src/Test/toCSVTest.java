package Test;
import org.junit.Test;

import Convert.toCSV;

public class toCSVTest {

	public void main(String[] args) {
		check();
	}

	@Test
	public void check()
	{
		toCSV check=new toCSV("c://");
		toCSV check1=new toCSV(null);
	}
}
