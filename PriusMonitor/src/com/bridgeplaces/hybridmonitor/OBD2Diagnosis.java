package com.bridgeplaces.hybridmonitor;

import java.util.Date;

import android.content.Context;
import android.os.Handler;

public class OBD2Diagnosis {

	public Date UpdatedTime;
	
	//Standard items
	double engineRpm;
	double batteryVoltage;
	double waterTemp;
	double viecleSpeed;
	double throttlePosition;
	double fuelLevel;
	double engineLoad;
	double maf;
	
	//Hybrid Specific items
	double soc;
	double motorTorque;
	double motorRpm;
	double hybridBatteryVoltage;
	double hybridBatteryTemp;
	
	/*
    * @param context  The UI Activity Context
    * @param handler  A Handler to send messages back to the UI Activity
    */
	public OBD2Diagnosis(Context context, Handler handler) {

		//Standard
		engineRpm = 0;
		batteryVoltage = 0;
		waterTemp = 0;
		viecleSpeed = 0;
		throttlePosition = 0;
		fuelLevel = 0;
		engineLoad = 0;
		maf = 0;
		
		//Hybrid
		soc=0;
		motorTorque = 0;
		motorRpm = 0;
		hybridBatteryVoltage = 0;
		hybridBatteryTemp = 0;
	}
}
