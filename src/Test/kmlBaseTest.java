package Test;
import java.io.File;

import org.junit.Test;

import DataBase.kmlBase;

public class kmlBaseTest {
	
	public void main(String[] args) {
		add();
	}
	
	@Test
	public void add()
	{
		File fl=new File("C:\\gmon\\complete.kml");
		kmlBase.inputCsv(fl);
		kmlBase.inputCsv(null);
	}
}
