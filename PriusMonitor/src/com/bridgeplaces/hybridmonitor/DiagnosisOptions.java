package com.bridgeplaces.hybridmonitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DiagnosisOptions implements Iterable<PidParameter> {

	private List<PidParameter> diagnosisParameters;
	private int paramCount;
	
	public DiagnosisOptions() {
		// TODO Auto-generated constructor stub
		initializeParameters();
		diagnosisParameters = new ArrayList<PidParameter>();
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
		
		//Water temp
		param = new PidParameter();
		param.paramId = paramid;
		param.paramName_1 = "水温";
		param.paramName_2 = "Water Temp";
		param.unit = "C";
		param.mode = 0x01;
		param.pid = 0x05;
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
		param.mode = 0x0;
		param.pid = 0x0;
		param.ATCommand ="RV";
		param.isActivated = true;
		this.addParameter(param);
	
	}
}
