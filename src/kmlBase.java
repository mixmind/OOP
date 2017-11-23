import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class kmlBase {
	/**
	 * Reading csv file and placing data into Wifi class and Network
	 * @param file Csv file
	 * @return database with wifi's
	 */
	public static Network inputCsv(File file)
	{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
							temp.setFirtseen(format.parse(power[0]));
							temp.setId(power[1]);
							temp.setLat(Double.parseDouble(power[2]));
							temp.setLot(Double.parseDouble(power[3]));
							temp.setAlt(Double.parseDouble(power[4]));
							temp.setSsid(power[i]);
							temp.setMac(power[i+1]);
							temp.setFreq(power[i+2]);
							temp.setRssi(Integer.parseInt(power[i+3]));
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
