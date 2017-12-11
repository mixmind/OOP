import static org.junit.Assert.*;
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
		nt.add(null, null);
		nt.add(wi, null);
		if(nt.getReal_size()!=1) throw new RuntimeException("Error of input"); 
	}
}
