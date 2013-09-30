package com.bridgeplaces.hybridmonitor;

import android.R.string;

public class ELMOBD2PidParameter {
	int paramId;
	String paramName_1;
	String paramName_2;
	EnuODB2ID obd2idType;
	String unit;
	int mode;
	int pid;
	String equation;
	EnuATCommand ATCommandType;
	String ATCommand;
	Boolean isATCommand;
	Boolean isActivated;
	
	public static enum EnuODB2ID{
		EngineRpm,
		BatteryVoltage,
		WaterTemp,
		ViecleSpeed,
		ThrottlePosition,
		FuelLevel,
		EngineLoad,
		MAF,
		SOC,
		MotorTorque,
		MotorRpm,
		HybridBatteryVoltage,
		HybridBatteryTemp
	}

	public static enum EnuATCommand{
		BatteryVoltage
	}
}
