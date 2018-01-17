package Thread;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import java.text.ParseException;
import java.util.Date;
import SQL.mySql;

public abstract class sqlWatch {
	public static final int POWER = 2;
	public static final int NORM = 10000;
	public static final double SIG_DIF = 0.4;
	public static final int MIN_DIF = 3;
	public static final int NO_SIGNAL = -120;
	public static final int NO_SIG_DIF = 100;
	public static final int TIME_UP = 8;//sec

		Date lastUp;
		Toolkit toolkit;
		Timer timer;
		public sqlWatch() {
			this.lastUp = new Date();
			toolkit = Toolkit.getDefaultToolkit();
	        timer = new Timer();
	        timer.schedule(new RemindTask(),
	                       0, TIME_UP*1000);  //subsequent rate
	        new RemindTask();
		}
		 class RemindTask extends TimerTask {
			 
		        public void run() {
		        	Date up = null;
		        	try {
						up = SQL.mySql.getUpdate();
						if(up!=null && up.after(lastUp))
						{
							lastUp = up;
							onRun();
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
		        }
		       
		    }
		 protected abstract void onRun();
		
		
		
	
}
