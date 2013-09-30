package com.bridgeplaces.hybridmonitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OBD2DiagnosisOptions implements Iterable<OBD2PidParameter> {

	private List<OBD2PidParameter> diagnosisParameters;
	private int paramCount;

	public static Boolean USE_GPS_DEVICE = true;
	public static final Boolean USE_BLUETOOTH_DEVICE = true;
	public static final Boolean USE_WIFI_DEVICE = false;
	
	public OBD2DiagnosisOptions() {
		// TODO Auto-generated constructor stub
		diagnosisParameters = new ArrayList<OBD2PidParameter>();
		initializeParameters();
	}

	@Override
	public Iterator<OBD2PidParameter> iterator() {
        return new Iterator<OBD2PidParameter>() {
            int index;

            public boolean hasNext() {
                return index < paramCount;
            }

            public OBD2PidParameter next() {
                return diagnosisParameters.get(index);
            }
            
			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}
        };
	}
		
	public void addParameter(OBD2PidParameter param){
		diagnosisParameters.add(param);
		this.paramCount = this.diagnosisParameters.size();
	}
	
	public void removeParameter(String paramId){
		this.paramCount = this.diagnosisParameters.size();
	}
	
	public void activateParameter(int paramId){
		
	}
	
	public void deActivateParameter(int paramId){
		
	}
	
	//Initial data setting
	private void initializeParameters(){
		int paramid = 0;
		OBD2PidParameter param;
		
		//Water Temp
		param = new OBD2PidParameter();
		param.paramId = paramid;
		param.paramName_1 = "水温";
		param.paramName_2 = "Water Temp";
		param.unit = "C";
		param.mode = 0x01;
		param.pid = 0x05;
		param.equation = "A-40";
		param.ATCommand = null;
		param.isActivated = true;
		this.addParameter(param);
		
		//Battery Voltage
		paramid ++ ;
		param = new OBD2PidParameter();
		param.paramId = paramid;
		param.paramName_1 = "電圧";
		param.paramName_2 = "Voltage";
		param.unit = "V";
		param.mode = 0;
		param.pid = 0;
		param.equation = null;
		param.ATCommand ="RV";
		param.isActivated = true;
		this.addParameter(param);
		
		//SOC
		paramid ++ ;
		param = new OBD2PidParameter();
		param.paramId = paramid;
		param.paramName_1 = "SOC";
		param.paramName_2 = "SOC";
		param.unit = "%";
		param.mode = 0x01;
		param.pid = 0x5B;
		param.equation = "A * 20 / 51";
		param.ATCommand =null;
		param.isActivated = true;
		this.addParameter(param);
	
	}
}
