package com.bridgeplaces.hybridmonitor;

public class ELM327Device {

	private BluetoothDevice bluetoothDev;
	
	public ELM327Device(BluetoothDevice bt) {
		// TODO Auto-generated constructor stub
		bluetoothDev = bt;
	}
	
	
	private void initializeDevice(){
		
	}
	
	public Odb2OutValue sendOdb2Command(int mode, int pid){
		Odb2OutValue odb2returned = new Odb2OutValue();
		odb2returned.A = 0x1;
		odb2returned.B = 0x2;
		odb2returned.C = 0xA;
		odb2returned.D = 0xB;
		return odb2returned;
	}
	
	public String sendElm327ATCommand(String command){
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
