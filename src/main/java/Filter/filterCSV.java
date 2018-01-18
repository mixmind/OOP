package Filter;

import WiFi_data.Network;


public interface filterCSV
{
	public Network runOn(Network nt);
	public Network runNot(Network nt);
}

