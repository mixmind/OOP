import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class csvBase {
	/**
	 * Reading Csv files before input into dbase
	 * @param folder Take folder for input into database
	 * @return Object with all data about Wifi 
	 * @throws NumberFormatException Error of Data format	
	 */
	public static Network readCSV(String folder) 
	{
		File dir = new File(folder);
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		};
		Network nt=new Network();
		try {
			for (File file : dir.listFiles(filter)) {
				check(file,nt);
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage()+" I cant reach a file");
		}
		
		finally {
			System.out.println("Function csvBase ended");
		}
		return nt;
		
	}
	/**
	 * Adding data from wifi hotspot into object
	 * @param power Array of data for input into Wifi
	 * @return WIfi with data inside
	 * @throws NumberFormatException Error of Data format	
	 * @throws ParseException Error while parsing
	 */
	private static WIFI add(String []power) 
	{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		WIFI temp=null;
		try {
			temp = new WIFI(power[0],power[1],power[2],format.parse(power[3]),Integer.parseInt(power[4]),
					Integer.parseInt(power[5]),Double.parseDouble(power[6]),
					Double.parseDouble(power[7]),Double.parseDouble(power[8]),
					Double.parseDouble(power[9]),power[10]);
		} catch (NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		return temp;
	}
	/**
	 * Reading csv file and placing data into Wifi class and Network
	 * @param file File csv to check if it have data to input	
	 * @param nt Network to store data
	 * @throws NumberFormatException Error of Data format	
	 * @throws ParseException Error while parsing
	 * @throws IOException Error of I/O
	 */
	private static void check(File file,Network nt)
	{
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		String csvFile = file.toString();
		try {
			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();
			try
			{
				if(line!=null) {
					String[] power=line.split(cvsSplitBy);
					String id="";
					if(power.length==8)
					{
						id=power[2];
						line = br.readLine();
						line = br.readLine();
						power=line.split(cvsSplitBy);

						WIFI temp=add(power);
						temp.setId(id);
						nt.add(temp);

					}
					if(power.length>10&&power.length!=46)
					{	
						while ((line = br.readLine()) != null) {
							power = line.split(cvsSplitBy);
							WIFI temp=add(power);
							temp.setId(id);
							nt.add(temp);
						}
					}
				}
			}
			catch (FileNotFoundException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);

			}
			catch (Exception e) {
			
			}
			finally {

				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						System.out.println(e);
					}
				}
			}		

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


	}
}