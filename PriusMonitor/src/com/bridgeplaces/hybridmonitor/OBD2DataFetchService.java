package com.bridgeplaces.hybridmonitor;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.bridgeplaces.hybridmonitor.ELM327Device.Odb2OutValue;
import com.bridgeplaces.hybridmonitor.OBD2PidParameter.EnuATCommand;
import com.bridgeplaces.hybridmonitor.OBD2PidParameter.EnuODB2ID;
import com.example.android.BluetoothChat.DeviceListActivity;
import com.example.android.BluetoothChat.R;

public class OBD2DataFetchService{

    // Debugging
    private static final String TAG = "OBD2DataFetchService";
    private static final boolean D = true;
    
	//Device Services
	private BluetoothAdapter mBluetoothAdapter = null;
    private BluetoothDevice mBluetoothService = null;
	private ELM327Device mELM327Device = null;
	private ELMBluetoothService mELMBluetoothService = null;
	
	public OBD2DataFetchService(BluetoothDevice btDevice) {

		if ( OBD2DiagnosisOptions.USE_BLUETOOTH_DEVICE){
			initializeBluetoothDevice();
		}else if (OBD2DiagnosisOptions.USE_WIFI_DEVICE){
			//to do
		}
		
	}
	

	
	private void stopBluetoothService(){
        if (mBluetoothService != null) mBluetoothService.stop();
	}
	
	public OBD2Diagnosis getOBD2Diagnosis(OBD2DiagnosisOptions options){
		
		OBD2Diagnosis diagnosis = new OBD2Diagnosis();
		Odb2OutValue outOdbValue;
		String outElmATValue;
		
		for (OBD2PidParameter param : options) {
			if(param.isActivated){
				if (param.isATCommand){
					switch (param.ATCommandType) {
					case BatteryVoltage:
						
						break;

					default:
						break;
					}
					
				}else{
					switch (param.obd2idType) {
					
					case BatteryVoltage:
						outElmATValue = mELM327Device.sendATCommandTest(param.ATCommand);
						diagnosis.batteryVoltage = 12.5; //文字列から数値変換へ
						break;
						
					case WaterTemp:
						outOdbValue = mELM327Device.sendOdb2CommandTest(param.mode,param.pid);
						diagnosis.waterTemp = outOdbValue.A - 40;
						break;
	
					default:
						break;
				}
				}
			}
		}
		
		return diagnosis;
	}
	
	
}
