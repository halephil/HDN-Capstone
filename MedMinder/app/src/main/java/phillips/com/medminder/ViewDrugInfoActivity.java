package phillips.com.medminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class ViewDrugInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;
    private DrugPO drug;
    private TextView drugName;
    private TextView drugDescription;
    private TextView dosesPerDay;
    private TextView qtyPerDose ;
    private TextView pillCount;
    private TextView qtyPerRefill;

    private CheckBox Mon;
    private CheckBox Tues;
    private CheckBox Wed;
    private CheckBox Thurs;
    private CheckBox Fri;
    private CheckBox Sat;
    private CheckBox Sun;


    private TextView alertTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drug_info);

        findViewById(R.id.takepill_btn).setOnClickListener(this);
        findViewById(R.id.edit_btn).setOnClickListener(this);
        findViewById(R.id.delete_btn).setOnClickListener(this);

        intent = getIntent();
        drug = (DrugPO) intent.getExtras().getParcelable("drug");

        populateInformation(drug);

    }

    private void populateInformation(DrugPO drugPO) {
        drugName = (TextView) findViewById(R.id.drug_name);
        drugDescription = (TextView) findViewById(R.id.additional_info);
        dosesPerDay = (TextView) findViewById(R.id.doses_per_day);
        qtyPerDose = (TextView) findViewById(R.id.qty_per_dose);
        pillCount = (TextView) findViewById(R.id.current_qty);
        qtyPerRefill = (TextView) findViewById(R.id.qty_per_refill);
        alertTime = (TextView) findViewById(R.id.alert_time);

        Mon = (CheckBox) findViewById(R.id.mon_cb);
        Tues = (CheckBox) findViewById(R.id.tues_cb);
        Wed = (CheckBox) findViewById(R.id.wed_cb);
        Thurs = (CheckBox) findViewById(R.id.thurs_cb);
        Fri = (CheckBox) findViewById(R.id.fri_cb);
        Sat = (CheckBox) findViewById(R.id.sat_cb);
        Sun = (CheckBox) findViewById(R.id.sun_cb);

        drugName.setText(drugPO.getmName());
        drugDescription.setText(drugPO.getmDescription());
        dosesPerDay.setText(Integer.toString(drugPO.getmDosesPerDay()));
        qtyPerDose.setText(Integer.toString(drugPO.getmQuantityPerDose()));
        pillCount.setText(Integer.toString(drugPO.getmPillCount()));
        qtyPerRefill.setText(Integer.toString(drugPO.getmRefillAt()));
        alertTime.setText(drugPO.getmAlarmTime());

        Mon.setClickable(false);
        Tues.setClickable(false);
        Wed.setClickable(false);
        Thurs.setClickable(false);
        Fri.setClickable(false);
        Sat.setClickable(false);
        Sun.setClickable(false);

        Log.d("CHECKBOX OUT PUT INFO", Boolean.toString(drugPO.isMonday()));

        Mon.setChecked(drugPO.isMonday());
        Tues.setChecked(drugPO.isTuesday());
        Wed.setChecked(drugPO.isWednesday());
        Thurs.setChecked(drugPO.isThursday());
        Fri.setChecked(drugPO.isFriday());
        Sat.setChecked(drugPO.isSaturday());
        Sun.setChecked(drugPO.isSunday());






    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.takepill_btn ){
            drug.decrementPillCount();
            populateInformation(drug);
        }
        if(v.getId() == R.id.edit_btn){
            Intent intent = new Intent(this, EditDrugInfoActivity.class);
            intent.putExtra("drug",drug );
            intent.setAction("EditExistingDrug");
            startActivityForResult(intent,2);
        }
        if(v.getId() ==  R.id.delete_btn){
            Intent intent = new Intent();       //Intent to Check Invites
            intent.putExtra("drug", drug);
            intent.setAction("Delete");
            setResult(RESULT_OK,intent);
            finish();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==2 && resultCode==RESULT_OK){
            drug = (DrugPO) data.getExtras().getParcelable("drug");
            populateInformation(drug);

        }

    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent();       //Intent to Check Invites
        intent.putExtra("drug", drug);
        setResult(RESULT_OK,intent);
        finish();
    }
}
