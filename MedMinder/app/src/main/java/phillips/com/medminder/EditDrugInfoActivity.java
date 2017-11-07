package phillips.com.medminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class EditDrugInfoActivity extends AppCompatActivity {

    private Drug drug;
    private EditText mDrugName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_drug_info);

        drug = new Drug();

        mDrugName = (EditText) findViewById(R.id.drug_name);
        drug.setName(mDrugName.getText().toString());
    }
}
