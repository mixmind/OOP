# OOP
My mission was to collect data about Wi-FI Networks in areas that I was.Sort it to 10 powerfull wifi in every time of collecting ,and after that to create 
Kml file that will work with google earth.
To succed in that challenge I gethered data from application Wigle wifi (https://play.google.com/store/apps/details?id=net.wigle.wigleandroid&hl=ru);
That program make's csv file with all data I needed to know.
It's collect Signal,SSID,RSSI,Channel,Geo data(lat,lon,alt),Metod of encryption and Type of spot(Wifi or Gsm).
With all csv files that I received,it's needed to be filtered by My program to make the best data base of spots.
My program contains toCsv class that receive folder and takes from it only csv files that can be imported and makes sort on it.
To make sort most efficient I needed to store data in the program,for that option I created csvBase class that use object's to do collection:
Object Wifi-that collect all data about spot,
Object Hotspots - dinamic array of Wifi that can collect only up to 10 powerfull spots,
Object Network - dinamical array of Hotspots that collect all time's of scan.

After all that manipulations like :
Take many csv files, collect from them in every part of scan 10 most powerfull dots,toCSV function creates
csv file that can be imported on stage 2 of my challenge.

2.Funtion toKml takes csv file that created in last time,gethering data into  kmlBase(it's same csvBase).After collecting all data back to kmlBase,
user would ask for filter that he want to pick on that data.The filters are in filterCSV class.Its could be filtered by ID,Time from-to time,Radius near spot.
After filtering data base toKML creates kml file that user could place on google earth and he will see all information about spots that has gethered with wigle wifi.

