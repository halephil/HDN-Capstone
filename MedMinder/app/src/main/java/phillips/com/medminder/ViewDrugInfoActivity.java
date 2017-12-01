package phillips.com.medminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewDrugInfoActivity extends AppCompatActivity {

    private TextView drugName;
    private TextView drugDescription;
    //private TextView dosesPerDay = (TextView) findViewById(R.id.doses_per_day);
    //private TextView qtyPerDose = (TextView) findViewById(R.id.qty_per_dose);
    private TextView pillCount;
    //private TextView qtyPerRefill = (TextView) findViewById(R.id.qty_per_refill);
    //private TextView alertTime = (TextView) findViewById(R.id.alert_time);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drug_info);

        drugName = (TextView) findViewById(R.id.drug_name);
        drugDescription = (TextView) findViewById(R.id.additional_info);
        pillCount = (TextView) findViewById(R.id.current_qty);

        Intent intent = getIntent();
        DrugPO drug = (DrugPO) intent.getExtras().getParcelable("drug");

        populateInformation(drug);

    }

    private void populateInformation(DrugPO drug) {
        drugName.setText(drug.getmName());
        drugDescription.setText(drug.getmDescription());
        pillCount.setText(drug.getmPillCount());
    }

}
