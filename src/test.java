
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import Algo.*;
import Convert.*;
import DataBase.*;

public class test {

	public static void main(String[] args) throws NumberFormatException, FileNotFoundException, IOException, ParserConfigurationException, ParseException, TransformerException {
		// TODO Auto-generated method stub
// toCSV k1=new toCSV("d:\\data1\\");
	//	Network test=csvBase.readCSV("d:\\data\\");
		Algo2.clientPlaceAlgo2("d:\\data\\_comb_all_BM2_.csv","d:\\data\\_comb_no_gps_ts1.csv");
		//toKML a = new toKML("d://data1//2017-12-20 19.00.15.csv");
	}
	
	
}
