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
    private Button SaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_drug_info);

        //SaveButton = (Button) findViewById(R.id.save_btn);
        //SaveButton.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {


                drug = new DrugPO();

                mDrugName = (EditText) findViewById(R.id.drug_name);
                drug.setmName(mDrugName.getText().toString());
                findViewById(R.id.addDrugBtn).setOnClickListener(this);

                Intent intent = new Intent(this, ViewDrugListActivity.class);       //Intent to Check Invites
                intent.putExtra("drug", drug);
                startActivity(intent);

            //}
        //});
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ViewDrugListActivity.class);       //Intent to Check Invites
        intent.putExtra("drug", drug);
        startActivity(intent);
    }
}
