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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditDrugInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private DrugPO drug;
    private EditText mDrugName;
    private EditText mPillCount;
    private Button setAlarmBtn;
    private EditText DosePerDay;
    private EditText qtyPerDay;
    private EditText CurrentQty;
    private EditText RefillAt;
    private EditText addInfo;


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
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();

            drug = new DrugPO();

            addInfo = (EditText) findViewById(R.id.additional_info);

            DosePerDay = (EditText) findViewById(R.id.doses_per_day);
            String DPD = DosePerDay.getText().toString().trim();

            qtyPerDay = (EditText) findViewById(R.id.qty_per_dose);
            String QPD = qtyPerDay.getText().toString().trim();

            CurrentQty = (EditText) findViewById(R.id.current_qty);
            String CQTY = CurrentQty.getText().toString().trim();

            RefillAt = (EditText) findViewById(R.id.no_of_refills);
            String RA = RefillAt.getText().toString().trim();

            mDrugName = (EditText) findViewById(R.id.drug_name);
            drug.setmName(mDrugName.getText().toString());

            mPillCount = (EditText) findViewById(R.id.current_qty);
            drug.setmPillCount(mPillCount.getText().toString());

            database.child("User").child(currentUser.getUid()).child("Drugs").child(mDrugName.getText().toString().trim()).child("Drug Info").setValue(addInfo.getText().toString().trim());
            database.child("User").child(currentUser.getUid()).child("Drugs").child(mDrugName.getText().toString().trim()).child("Doses per Day").setValue(DPD);
            database.child("User").child(currentUser.getUid()).child("Drugs").child(mDrugName.getText().toString().trim()).child("Quantity per Dose").setValue(QPD);
            database.child("User").child(currentUser.getUid()).child("Drugs").child(mDrugName.getText().toString().trim()).child("Current Quantity").setValue(CQTY);
            database.child("User").child(currentUser.getUid()).child("Drugs").child(mDrugName.getText().toString().trim()).child("Refill At").setValue(RA);
            database.child("User").child(currentUser.getUid()).child("Drugs").child(mDrugName.getText().toString().trim()).child("Pill Count").setValue(mPillCount.getText().toString().trim());

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

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("PrescriptID");
        DataSnapshot dataSnapshot;



        addInfo = (EditText) findViewById(R.id.additional_info);

        if(requestCode ==1 && resultCode==RESULT_OK){

            Barcode barcode = (Barcode) data.getParcelableExtra("barcode");
            database.child(barcode.rawValue.toString()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String prescriptInfo = dataSnapshot.getValue().toString();
                    addInfo.setText(prescriptInfo);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(),"Read failed:" + databaseError.getCode(), Toast.LENGTH_LONG).show();
                }
            });
            //Toast.makeText(getApplicationContext(),barcode.rawValue, Toast.LENGTH_LONG).show();

        }
    }
}












