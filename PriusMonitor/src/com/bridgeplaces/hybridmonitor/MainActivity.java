package com.bridgeplaces.hybridmonitor;

import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
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
    
    // Intent request codes
    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
    private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
    private static final int REQUEST_ENABLE_BT = 3;
    
	//Diagnosis instances
	private DiagnosisOptions diagnosis;
	private OBD2Diagnosis obd2info;
	private HybridDiagnosis hybridinfo;
	
	//Device Services
	private BluetoothAdapter mBluetoothAdapter = null;
    private BluetoothService mBluetoothService = null;
	private ELM327Device mELM327Device = null;
    
    //GUI component
    private Button button1;
    private TextView tvWaterTemp;
    private TextView tvVoltage;
	
	public MainActivity() {
		diagnosis = new DiagnosisOptions();
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//画面設定
		tvWaterTemp = (TextView)findViewById(R.id.textViewWater_temp);
		tvVoltage = (TextView)findViewById(R.id.textViewVoltage);
		
        // Get local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If the adapter is null, then Bluetooth is not supported
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
		
		
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

        // If BT is not on, request that it be enabled.
        // setupChat() will then be called during onActivityResult
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        // Otherwise, setup the chat session
        } else {
            if (mBluetoothService == null) setupBluetoothService();
        }
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
        if (mBluetoothService != null) mBluetoothService.stop();
        if(D) Log.e(TAG, "--- ON DESTROY ---");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void setupBluetoothService(){
        Log.d(TAG, "setupBluetoothService()");
        mBluetoothService = new BluetoothService(this);
	}
}
