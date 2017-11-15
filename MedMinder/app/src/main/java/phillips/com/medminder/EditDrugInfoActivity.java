package phillips.com.medminder;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;

public class EditDrugInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private DrugPO drug;
    private EditText mDrugName;
    private EditText mPillCount;
    private Button setAlarmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_drug_info);


        findViewById(R.id.save_btn).setOnClickListener(this);
        findViewById(R.id.cancel_btn).setOnClickListener(this);
        findViewById(R.id.set_alarm_button).setOnClickListener(this);
        findViewById(R.id.qr_btn).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        //When Save button is pressed
        if (view.getId() == R.id.save_btn) {

            drug = new DrugPO();

            mDrugName = (EditText) findViewById(R.id.drug_name);
            drug.setmName(mDrugName.getText().toString());

            mPillCount = (EditText) findViewById(R.id.current_qty);
            drug.setmPillCount(mPillCount.getText().toString());

            Intent intent = new Intent();       //Intent to Check Invites
            intent.putExtra("drug", drug);
            setResult(RESULT_OK,intent);
            finish();

        }

        //When Cancel Button is pressed
        if (view.getId() == R.id.cancel_btn){
            finish();
        }

        //When Set Alarm button is pressed
        if(view.getId() == R.id.set_alarm_button){
            Intent alarmIntent = new Intent(this, AlarmActivity.class);
            startActivity(alarmIntent);
        }

        //When Scan QR Button is pressed
        if(view.getId() == R.id.qr_btn){


            Intent qrScanIntent = new Intent(this, QRScanActivity.class);
            startActivityForResult(qrScanIntent,1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==1 && resultCode==RESULT_OK){

            Barcode barcode = (Barcode) data.getParcelableExtra("barcode");
          
            Toast.makeText(getApplicationContext(),barcode.rawValue, Toast.LENGTH_LONG).show();

        }
    }
}












