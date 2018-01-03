
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
	//	toCSV k1=new toCSV("d:\\dd\\");
	//	Network test=csvBase.readCSV("d:\\data\\");
	//Algo.routerPlaceAlgo1("c:\\Users\\mixmind\\eclipse-workspace\\OOP matala\\files\\input\\Ex2\\_comb_all_BM2_.csv");
	Algo2.clientPlaceAlgo2("c:\\Users\\mixmind\\eclipse-workspace\\OOP matala\\files\\input\\Ex2\\_comb_all_BM2_.csv","d://dd//2017-12-21 10.45.20.csv");
		//toKML a = new toKML("d://dd//2017-12-21 10.45.20.csv");
	}
	
	
}
