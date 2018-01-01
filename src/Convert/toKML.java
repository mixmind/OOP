package Convert;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import DataBase.*;
import Filter.*;
import WiFi_data.*;
import de.micromata.opengis.kml.v_2_2_0.*;


public class toKML {
	/**
	 * 
	 * @param file Take file for convert to KML
	 * @throws ParseException error of parsing
	 * @throws IOException 
	 */
	public toKML(String file) throws ParseException, IOException
	{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String currTime = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new java.util.Date());
		File fileT;
		try{
			fileT=new File(file);
			Network nt=kmlBase.inputCsv(fileT);
			String id="";
			Date dat=null;
			Date dat1=null;
			double radius=0;
			int choise=0;
			Scanner sc=new Scanner(System.in);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String line = "";
			if(nt.getHotspots()[0]!=null) {
				System.out.println("What filter do you want to do?\n1.Id\n2.Date\n3.Radius");
				while (!sc.hasNextInt()) {
					System.out.println("That's not a number!");
					sc.next(); // this is important!
				}
				choise=Integer.parseInt(sc.next());
				switch (choise)
				{
				case 1:
					System.out.println("Enter id:");
					line=br.readLine();
					id=line;
					FilterId filterById =new FilterId(id);
					filterById.runOn(nt);
					break;
				case 2:
					System.out.println("Enter date in format:yyyy-MM-dd HH:mm\nBefore and after");
					while(dat==null||dat1==null) {
						try{
							dat=format.parse(sc.next()+" "+sc.next());
							dat1=format.parse(sc.next()+" "+sc.next());
						}
						catch(ParseException e){
							System.out.println(e.getMessage());
							System.out.println("Enter date in format:yyyy-MM-dd HH:mm\nBefore and after");
						}
					}
					sc.close();
					FilterDate filterByDate =new FilterDate(dat,dat1);
					filterByDate.runOn(nt);
					break;
				case 3:
					System.out.println("Enter Lat");
					while (!sc.hasNextDouble()) {
						System.out.println("That's not a number!");
						sc.next(); // this is important!
					}
					double lat=sc.nextDouble();
					System.out.println("Enter Lon");
					while (!sc.hasNextDouble()) {
						System.out.println("That's not a number!");
						sc.next(); // this is important!
					}
					double lon=sc.nextDouble();
					System.out.println("Enter radius");
					while (!sc.hasNextDouble()) {
						System.out.println("That's not a number!");
						sc.next(); // this is important!
					}
					radius=sc.nextDouble();
					sc.close();
					FilterRadius filterByRadius =new FilterRadius(radius, lat, lon);
					filterByRadius.runOn(nt);
					break;
				default :
					System.out.println("Making without filter.");
					sc.close();
				}
				br.close();
				try {
					//create document for kml with dot colors
					Kml kml = KmlFactory.createKml();
					Document doc=new Document();
					Icon iconRed=new Icon();Style red=new Style();IconStyle icRed=new IconStyle();
					iconRed.setHref("http://maps.google.com/mapfiles/ms/icons/red-dot.png");
					icRed.setScale(1);icRed.setIcon(iconRed);red.setId("red");red.setIconStyle(icRed);
					doc.addToStyleSelector(red);
					Icon iconGreen=new Icon();Style green=new Style();IconStyle icgreen=new IconStyle();
					iconGreen.setHref("http://maps.google.com/mapfiles/ms/icons/green-dot.png");
					icgreen.setScale(1);icgreen.setIcon(iconGreen);green.setId("green");green.setIconStyle(icgreen);
					doc.addToStyleSelector(green);
					Icon iconYellow=new Icon();Style yellow=new Style();IconStyle icyellow=new IconStyle();
					iconYellow.setHref("http://maps.google.com/mapfiles/ms/icons/yellow-dot.png");
					icyellow.setScale(1);icyellow.setIcon(iconYellow);yellow.setId("yellow");yellow.setIconStyle(icyellow);
					doc.addToStyleSelector(yellow);
					Folder dir=new Folder();
					dir.setName("Wifi");	
					Date dot=new Date();
					WIFI[] wifiT=new WIFI[10];
					// Create <Placemark> and set values.
					for (int i = 0; i <nt.getReal_size(); i++) {
						if(nt.getHotspots()[i]!=null) {
							wifiT=nt.getHotspots()[i].getWIFI();
							if(wifiT!=null) {
								for(int j=0;j<wifiT.length&&wifiT[j]!=null;j++) {
									Placemark placemark = KmlFactory.createPlacemark();
									placemark.setName(wifiT[j].getSsid());
									placemark.setVisibility(true);
									placemark.setOpen(false);
									dot=nt.getHotspots()[i].getDataOfdot().getFirtseen();
									String str=format.format(dot);
									str=str.replace(' ', 'T')+'Z';
									placemark.createAndSetTimeStamp().setWhen(str);
									placemark.setDescription("\nSSID: "+wifiT[j].getSsid()+"\nId of phone: "
											+nt.getHotspots()[i].getDataOfdot().getId()+
											"\nMac: "+wifiT[j].getMac()+"\nFrequency: "+wifiT[j].getFreq()+
											"\nSignal: "+wifiT[j].getRssi()+"\nDate: "+nt.getHotspots()[i].getDataOfdot().getFirtseen()+"\n");
									if(wifiT[j].getRssi()>-85) placemark.setStyleUrl("green");
									else if(wifiT[j].getRssi()<-85&&wifiT[j].getRssi()>-95) 
										placemark.setStyleUrl("yellow");
									else placemark.setStyleUrl("red");
									// Create <Point> and set values.
									Point point = KmlFactory.createPoint();
									point.setExtrude(false);
									point.setAltitudeMode(AltitudeMode.CLAMP_TO_GROUND);
									// Add coordinates>.
									point.getCoordinates().add(new Coordinate(
											nt.getHotspots()[i].getDataOfdot().getLon()
											,nt.getHotspots()[i].getDataOfdot().getLat(),
											nt.getHotspots()[i].getDataOfdot().getAlt()));
									placemark.setGeometry(point);    
									dir.addToFeature(placemark); 
								}
							}
						}

					}
					doc.addToFeature(dir);
					kml.setFeature(doc);
					kml.marshal(new File(fileT.getParent()+"/"+currTime+" - completed.kml"));
					System.out.println("Done creating kml.");
				} 

				catch(FileNotFoundException e)
				{
					System.out.println("Can't create kml file.");
				}
			}

		}
		catch(NullPointerException e)
		{
			System.out.println(e.getMessage()+" exception");
		}
		finally {
			System.out.println("Function toKml ended.");
		}
	}
}
