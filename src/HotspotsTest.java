import org.junit.Test;

public class HotspotsTest {

	public  void main(String[] args) {
		test();
	}
	@Test
	public void test() {
		Hotspots hs =new Hotspots();
		WIFI wi=new WIFI();
		wi.setAlt(221);
		hs.add(wi);
		if(hs.getReal_size()!=1) throw new RuntimeException("Error of input");
		if(!hs.getLine()[0].equals(wi)) throw new RuntimeException("Error of input");
	}

}
