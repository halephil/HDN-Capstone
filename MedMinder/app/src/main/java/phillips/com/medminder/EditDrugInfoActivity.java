package phillips.com.medminder;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.Button;
import android.widget.CheckBox;
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
    private EditText qtyPerDose;
    private EditText RefillAt;
    private EditText addInfo;

    private CheckBox Mon;
    private CheckBox Tues;
    private CheckBox Wed;
    private CheckBox Thurs;
    private CheckBox Fri;
    private CheckBox Sat;
    private CheckBox Sun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_drug_info);

        findViewById(R.id.save_btn).setOnClickListener(this);
        findViewById(R.id.cancel_btn).setOnClickListener(this);
        findViewById(R.id.set_alarm_button).setOnClickListener(this);
        findViewById(R.id.qr_btn).setOnClickListener(this);

        if(getIntent().getAction() == "EditExistingDrug"){
            drug = (DrugPO) getIntent().getExtras().getParcelable("drug");
            updatePageInfo(drug);
        }


    }

    @Override
    public void onClick(View view) {

        //When Save button is pressed
        if (view.getId() == R.id.save_btn) {

            String action = getIntent().getAction();

            // Editing an Exsisting Drug
            if(action == "EditExistingDrug"){
                grabPageInfo(drug);
            }

            // Creating a new Drug
            else if(action == "CreateNewDrug"){
                drug = new DrugPO();
                grabPageInfo(drug);
            }
            else{
                drug = new DrugPO();
                grabPageInfo(drug);
            }




            //saveDrugToDataBase(drug);

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

    DrugPO grabPageInfo(DrugPO drugPO){
        mDrugName = (EditText) findViewById(R.id.drug_name);
        addInfo = (EditText) findViewById(R.id.additional_info);
        DosePerDay = (EditText) findViewById(R.id.doses_per_day);
        qtyPerDose = (EditText) findViewById(R.id.qty_per_dose);
        RefillAt = (EditText) findViewById(R.id.no_of_refills);
        mPillCount = (EditText) findViewById(R.id.current_qty);

        Mon = (CheckBox) findViewById(R.id.mon_cb2);
        Tues = (CheckBox) findViewById(R.id.tues_cb2);
        Wed = (CheckBox) findViewById(R.id.wed_cb2);
        Thurs = (CheckBox) findViewById(R.id.thurs_cb2);
        Fri = (CheckBox) findViewById(R.id.fri_cb2);
        Sat = (CheckBox) findViewById(R.id.sat_cb2);
        Sun = (CheckBox) findViewById(R.id.sun_cb2);

        drugPO.setmName(mDrugName.getText().toString().trim());
        drugPO.setmDescription(addInfo.getText().toString().trim());
        drugPO.setmDosesPerDay(Integer.parseInt(DosePerDay.getText().toString().trim()));
        drugPO.setmQuantityPerDose(Integer.parseInt(qtyPerDose.getText().toString().trim()));
        drugPO.setmRefillAt(Integer.parseInt(RefillAt.getText().toString().trim()));
        drugPO.setmPillCount(Integer.parseInt(mPillCount.getText().toString().trim()));

        drugPO.setMonday(Mon.isChecked());
        drugPO.setTuesday(Tues.isChecked());
        drugPO.setWednesday(Wed.isChecked());
        drugPO.setThursday(Thurs.isChecked());
        drugPO.setFriday(Fri.isChecked());
        drugPO.setSaturday(Sat.isChecked());
        drugPO.setSunday(Sun.isChecked());




        return drugPO;
    }

    void updatePageInfo(DrugPO drugPO){
        mDrugName = (EditText) findViewById(R.id.drug_name);
        addInfo = (EditText) findViewById(R.id.additional_info);
        DosePerDay = (EditText) findViewById(R.id.doses_per_day);
        qtyPerDose = (EditText) findViewById(R.id.qty_per_dose);
        RefillAt = (EditText) findViewById(R.id.no_of_refills);
        mPillCount = (EditText) findViewById(R.id.current_qty);

        Mon = (CheckBox) findViewById(R.id.mon_cb2);
        Tues = (CheckBox) findViewById(R.id.tues_cb2);
        Wed = (CheckBox) findViewById(R.id.wed_cb2);
        Thurs = (CheckBox) findViewById(R.id.thurs_cb2);
        Fri = (CheckBox) findViewById(R.id.fri_cb2);
        Sat = (CheckBox) findViewById(R.id.sat_cb2);
        Sun = (CheckBox) findViewById(R.id.sun_cb2);

        mDrugName.setText(drugPO.getmName());
        addInfo.setText((drugPO.getmDescription()));
        DosePerDay.setText(Integer.toString(drugPO.getmDosesPerDay()));
        qtyPerDose.setText(Integer.toString(drugPO.getmQuantityPerDose()));
        RefillAt.setText(Integer.toString(drugPO.getmRefillAt()));
        mPillCount.setText(Integer.toString(drugPO.getmPillCount()));


        Mon.setClickable(true);
        Tues.setClickable(true);
        Wed.setClickable(true);
        Thurs.setClickable(true);
        Fri.setClickable(true);
        Sat.setClickable(true);
        Sun.setClickable(true);

        Mon.setChecked(drugPO.isMonday());
        Tues.setChecked(drugPO.isTuesday());
        Wed.setChecked(drugPO.isWednesday());
        Thurs.setChecked(drugPO.isThursday());
        Fri.setChecked(drugPO.isFriday());
        Sat.setChecked(drugPO.isSaturday());
        Sun.setChecked(drugPO.isSunday());

    }



    void saveDrugToDataBase(DrugPO drug){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();


        database.child("User").child(currentUser.getUid()).child("Drugs").child(mDrugName.getText().toString().trim()).child("Drug Info").setValue(drug.getmDescription());
        database.child("User").child(currentUser.getUid()).child("Drugs").child(mDrugName.getText().toString().trim()).child("Doses per Day").setValue(Integer.toString(drug.getmDosesPerDay()));
        database.child("User").child(currentUser.getUid()).child("Drugs").child(mDrugName.getText().toString().trim()).child("Quantity per Dose").setValue(Integer.toString(drug.getmQuantityPerDose()));
        database.child("User").child(currentUser.getUid()).child("Drugs").child(mDrugName.getText().toString().trim()).child("Current Quantity").setValue(Integer.toString(drug.getmPillCount()));
        database.child("User").child(currentUser.getUid()).child("Drugs").child(mDrugName.getText().toString().trim()).child("Refill At").setValue(Integer.toString(drug.getmRefillAt()));
        database.child("User").child(currentUser.getUid()).child("Drugs").child(mDrugName.getText().toString().trim()).child("Pill Count").setValue(Integer.toString(drug.getmPillCount()));
    }
}












