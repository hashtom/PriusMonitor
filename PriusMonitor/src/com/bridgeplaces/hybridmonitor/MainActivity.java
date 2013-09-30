package com.bridgeplaces.hybridmonitor;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // Debugging
    private static final String TAG = "HybridMonitor";
    private static final boolean D = true;
    
	//Diagnosis instances

	private OBD2DiagnosisOptions diagnosis;
	private OBD2Diagnosis obd2info;
	
    //GUI component
    private Button button1;
    private TextView tvWaterTemp;
    private TextView tvVoltage;
	
	public MainActivity() {
		diagnosis = new OBD2DiagnosisOptions();
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//画面設定
		tvWaterTemp = (TextView)findViewById(R.id.textViewWater_temp);
		tvVoltage = (TextView)findViewById(R.id.textViewVoltage);
		
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tvWaterTemp.setText("50");
				tvVoltage.setText("12.6");
			}
		});
	}

	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
        if(D) Log.e(TAG, "++ ON START ++");

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
        if(D) Log.e(TAG, "--- ON DESTROY ---");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
