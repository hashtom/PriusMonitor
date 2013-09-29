package com.bridgeplaces.hybridmonitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DiagnosisOptions implements Iterable<PidParameter> {

	private List<PidParameter> diagnosisParameters;
	private int paramCount;
	
	public DiagnosisOptions() {
		// TODO Auto-generated constructor stub
		diagnosisParameters = new ArrayList<PidParameter>();
		initializeParameters();
	}

	@Override
	public Iterator<PidParameter> iterator() {
        return new Iterator<PidParameter>() {
            int index;

            public boolean hasNext() {
                return index < paramCount;
            }

            public PidParameter next() {
                return diagnosisParameters.get(index);
            }
            
			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}
        };
	}
		
	public void addParameter(PidParameter param){
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
		PidParameter param;
		
		//Water Temp
		param = new PidParameter();
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
		param = new PidParameter();
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
		param = new PidParameter();
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
