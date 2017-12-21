
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
// toCSV k1=new toCSV("d:\\dd\\");
	//	Network test=csvBase.readCSV("d:\\data\\");
	Algo.routerPlaceAlgo1("d:\\dd\\2017-12-21 09.03.28.csv");
	//Algo2.clientPlaceAlgo2("d:\\data\\_comb_all_BM2_.csv","d:\\data\\_comb_no_gps_ts1.csv");
		//toKML a = new toKML("d://data//2017-12-20 22.43.19CLientPlace.csv");
	}
	
	
}
