package DataBase;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import WiFi_data.Network;
import WiFi_data.WIFI;

public class kmlBase {
	/**
	 * Reading csv file and placing data into Wifi class and Network
	 * @param file Csv file
	 * @return database with wifi's
	 */
	public static Network inputCsv(File file)
	{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//	DateFormat format1 = new SimpleDateFormat("mm/dd/yy hh:mm aa",Locale.getDefault());
		Network nt=new Network();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {
			String csvFile = file.toString();
			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();
			try
			{
				if(line!=null) {
					String[] power=line.split(cvsSplitBy);
					while ((line = br.readLine()) != null) {
						for(int i=6;i<power.length&&power[i]!=null;) {
							power = line.split(cvsSplitBy);
							WIFI temp=new WIFI();
							temp.setLat(Double.parseDouble(power[2]));
							temp.setLon(Double.parseDouble(power[3]));
							temp.setAlt(Double.parseDouble(power[4]));
							temp.setId(power[1]);
							temp.setFirtseen(format.parse(power[0]));
							temp.setSsid(power[i+1]);
							temp.setMac(power[i]);
							temp.setFreq(power[i+2]);
							temp.setRssi((int)Double.parseDouble(power[i+3]));
							nt.add(temp);
							i+=4;
						}
					}

				}
			} catch (ParseException e) {
				System.out.println(e.getMessage());
			}

			finally {
				System.out.println("Function kmlBase ended.");
			}	
		}
		catch (Exception e) {
			System.out.println(e.getLocalizedMessage()+" error!");
		}

		return nt;

	}
}
