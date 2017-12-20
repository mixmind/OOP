package Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;

import Convert.toKML;

public class toKMLTest {

	public void main(String[] args) throws ParseException, IOException {
		check();
	}

	@Test
	public void check() throws ParseException, IOException
	{
		File temp=new File("c://");
		toKML check=new toKML("c://");
		toKML check1=new toKML(null);
	}

}
