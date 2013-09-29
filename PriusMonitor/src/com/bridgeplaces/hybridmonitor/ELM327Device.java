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
	private BluetoothService mBluetoothService;
	
	public ELM327Device(BluetoothService btDevice) {
		// TODO Auto-generated constructor stub
		mBluetoothService = btDevice;
	}
	
	private void initializeDevice(){
		
	}
	
	public Odb2OutValue sendOdb2CommandTest(int mode, int pid){
		Odb2OutValue odb2returned = new Odb2OutValue();
		odb2returned.A = 0x1;
		odb2returned.B = 0x2;
		odb2returned.C = 0xA;
		odb2returned.D = 0xB;
		return odb2returned;
	}
	
	public String sendElm327ATCommandTest(String command){
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
	
    // The Handler that gets information back from the BluetoothChatService
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case MESSAGE_STATE_CHANGE:
                if(D) Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                switch (msg.arg1) {
                case BluetoothService.STATE_CONNECTED:
//                    setStatus(getString(R.string.title_connected_to, mConnectedDeviceName));
//                    mConversationArrayAdapter.clear();
                    break;
                case BluetoothService.STATE_CONNECTING:
//                    setStatus(R.string.title_connecting);
                    break;
                case BluetoothService.STATE_LISTEN:
                case BluetoothService.STATE_NONE:
//                    setStatus(R.string.title_not_connected);
                    break;
                }
                break;
            case MESSAGE_WRITE:
                byte[] writeBuf = (byte[]) msg.obj;
                // construct a string from the buffer
                String writeMessage = new String(writeBuf);
//                mConversationArrayAdapter.add("Me:  " + writeMessage);
                break;
            case MESSAGE_READ:
                byte[] readBuf = (byte[]) msg.obj;
                // construct a string from the valid bytes in the buffer
                String readMessage = new String(readBuf, 0, msg.arg1);
//                mConversationArrayAdapter.add(mConnectedDeviceName+":  " + readMessage);
                break;
            case MESSAGE_DEVICE_NAME:
                // save the connected device's name
//                mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
//                Toast.makeText(getApplicationContext(), "Connected to "
//                               + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                break;
            case MESSAGE_TOAST:
//                Toast.makeText(getApplicationContext(), msg.getData().getString(TOAST),
//                               Toast.LENGTH_SHORT).show();
                break;
            }
        }
    };
}
