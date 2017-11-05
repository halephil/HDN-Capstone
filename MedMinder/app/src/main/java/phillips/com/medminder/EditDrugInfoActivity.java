package phillips.com.medminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditDrugInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private DrugPO drug;
    private EditText mDrugName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_drug_info);

        drug = new DrugPO();
        findViewById(R.id.save_btn).setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.save_btn) {

            mDrugName = (EditText) findViewById(R.id.drug_name);
            drug.setmName(mDrugName.getText().toString());

            Intent intent = new Intent();       //Intent to Check Invites
            intent.putExtra("drug", drug);
            setResult(RESULT_OK,intent);
            finish();

        }
    }
}
