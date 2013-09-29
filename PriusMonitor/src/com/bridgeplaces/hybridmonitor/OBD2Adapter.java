package com.bridgeplaces.hybridmonitor;

import com.bridgeplaces.hybridmonitor.ELM327Device.Odb2OutValue;
import com.bridgeplaces.hybridmonitor.PidParameter.EnuODB2ID;

public class OBD2Adapter {

	ELM327Device elmConnect;
	
	public OBD2Adapter(BluetoothService bt) {
		// TODO Auto-generated constructor stub
		elmConnect = new ELM327Device(bt);
	}
	
	public OBD2Diagnosis getOBD2Diagnosis(DiagnosisOptions options){
		
		OBD2Diagnosis diagnosis = new OBD2Diagnosis();
		Odb2OutValue outOdbValue;
		String outElmATValue;
		
		for (PidParameter param : options) {
			if(param.isActivated){
				switch (param.obd2id) {
				case BatteryVoltage:
					outElmATValue = elmConnect.sendElm327ATCommandTest(param.ATCommand);
					diagnosis.batteryVoltage = 12.5; //文字列から数値変換へ
					break;
				case Watertemp:
					outOdbValue = elmConnect.sendOdb2CommandTest(param.mode,param.pid);
					diagnosis.waterTemp = outOdbValue.A - 40;
					break;

				default:
					break;
				}
			}
		}
		
		return diagnosis;
	}
	
	public HybridDiagnosis getHybridDiagnosis(DiagnosisOptions options){
		return new HybridDiagnosis();
	}
	
}
