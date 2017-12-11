import org.junit.Test;

public class csvBaseTest {

	
	public void main(String[] args) {
		add();
	}
	@Test
	public void add()
	{
		csvBase.readCSV(null);
		csvBase.readCSV("C:\\gmon\\complete.kml");
	}
}
