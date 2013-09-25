package com.bridgeplaces.hybridmonitor;

public class PidParameter {
	int paramId;
	String paramName_1;
	String paramName_2;
	String unit;
	int mode;
	int pid;
	String ATCommand;
	EnuODB2ID obd2id;
	Boolean isActivated;
	
	public static enum EnuODB2ID{
		Watertemp,
		BatteryVoltage
	}
	
}
