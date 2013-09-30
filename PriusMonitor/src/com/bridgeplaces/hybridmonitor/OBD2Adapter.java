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

public class OBD2Adapter{

    // Debugging
    private static final String TAG = "OBD2Adapter";
    private static final boolean D = true;
    
    // Intent request codes
    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
    private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
    private static final int REQUEST_ENABLE_BT = 3;
    
	//Device Services
	private BluetoothAdapter mBluetoothAdapter = null;
    private BluetoothDevice mBluetoothService = null;
	private ELM327Device mELM327Device = null;
	private ELMBluetoothService mELMBluetoothService = null;
	
	public OBD2Adapter(BluetoothDevice btDevice) {

		if ( OBD2DiagnosisOptions.USE_BLUETOOTH_DEVICE){
			initializeBluetoothDevice();
		}else if (OBD2DiagnosisOptions.USE_WIFI_DEVICE){
			//to do
		}
		
	}
	
	private void initializeBluetoothDevice(){
		
        // Get local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If the adapter is null, then Bluetooth is not supported
        if (mBluetoothAdapter == null) {
            throw new Exception("Bluetooth is not available");
        }
        
        // If BT is not on, request that it be enabled.
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        } else {
            if (mBluetoothService == null) setupBluetoothService();
        }
        
	}
	
    private void connectDevice(Intent data, boolean secure) {
        // Get the device MAC address
        String address = data.getExtras()
            .getString(BluetoothDeviceListActivity.EXTRA_DEVICE_ADDRESS);
        // Get the BluetoothDevice object
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
        // Attempt to connect to the device
        mBluetoothService.connect(device, secure);
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(D) Log.d(TAG, "onActivityResult " + resultCode);
        switch (requestCode) {
        case REQUEST_CONNECT_DEVICE_SECURE:
            // When DeviceListActivity returns with a device to connect
            if (resultCode == Activity.RESULT_OK) {
                connectDevice(data, true);
            }
            break;
        case REQUEST_CONNECT_DEVICE_INSECURE:
            // When DeviceListActivity returns with a device to connect
            if (resultCode == Activity.RESULT_OK) {
                connectDevice(data, false);
            }
            break;
        case REQUEST_ENABLE_BT:
            // When the request to enable Bluetooth returns
            if (resultCode == Activity.RESULT_OK) {
                // Bluetooth is now enabled, so set up a chat session
            	setupBluetoothService();
            } else {
                // User did not enable Bluetooth or an error occurred
                Log.d(TAG, "BT not enabled");
                Toast.makeText(this, R.string.bt_not_enabled_leaving, Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
    
	private void setupBluetoothService(){
        Log.d(TAG, "setupBluetoothService()");
        mBluetoothService = new BluetoothDevice();
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
