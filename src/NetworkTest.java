import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class NetworkTest {

	public void main(String[] args) {
		add();
	}

	@Test
	public void add()
	{
		WIFI wi=new WIFI();
		wi.setAlt(221);
		Network nt=new Network();
		nt.add(null);
		nt.add(wi);
		if(nt.getReal_size()!=1) throw new RuntimeException("Error of input"); 
	}
}
