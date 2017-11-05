package phillips.com.medminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditDrugInfoActivity extends AppCompatActivity implements View.onClickListener {

    private Drug drug;
    private EditText mDrugName;
    private Button SaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_drug_info);

        drug = new Drug();

        mDrugName = (EditText) findViewById(R.id.drug_name);
        drug.setName(mDrugName.getText().toString());

        SaveButton = (Button) findViewById(R.id.save_btn);
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
