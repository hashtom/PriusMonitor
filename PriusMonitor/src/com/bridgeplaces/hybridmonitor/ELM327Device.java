package com.bridgeplaces.hybridmonitor;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class ELM327Device {

    // Debugging
    private static final String TAG = "ELM327Device";
    private static final boolean D = true;
    
    // Message types sent from the BluetoothChatService Handler
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;
    
    //Bluetooth service
	private ELMBluetoothService mBluetoothService;
	
	public ELM327Device(ELMBluetoothService btService) {
		// TODO Auto-generated constructor stub
		mBluetoothService = btService;
	}
	
	private void initializeELM327(){
		
	}
	
	public Odb2OutValue sendOdb2CommandTest(int mode, int pid){
		Odb2OutValue odb2returned = new Odb2OutValue();
		odb2returned.A = 0x1;
		odb2returned.B = 0x2;
		odb2returned.C = 0xA;
		odb2returned.D = 0xB;
		return odb2returned;
	}
	
	public String sendATCommandTest(String command){
		String returnString = "ABC";
		return returnString;
	}
	
	public class Odb2OutValue{
		int A;
		int B;
		int C;
		int D;
		public Odb2OutValue() {
			A = 0x0;
			B = 0x0;
			C = 0x0;
			D = 0x0;
		}
	}


}
