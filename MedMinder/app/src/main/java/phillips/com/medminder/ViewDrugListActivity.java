package phillips.com.medminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ViewDrugListActivity extends AppCompatActivity implements View.OnClickListener {

    private List<DrugPO> DrugList = new ArrayList<DrugPO>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drug_list);
        findViewById(R.id.addDrugBtn).setOnClickListener(this);

        Bundle data = getIntent().getExtras();
        DrugPO drug = (DrugPO) data.getParcelable("student");

        addDrugToList(drug);
        populateListView();

    }

    private void addDrugToList(DrugPO drug){
        if (drug == null){
            return;
        }
        else
        DrugList.add(drug);

    }

    private void populateListView() {
        ArrayAdapter<DrugPO> adapter = new MyAdapter();
        ListView list = (ListView)findViewById(R.id.drugListView);
        list.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.addDrugBtn){
            Intent intent = new Intent(this, EditDrugInfoActivity.class);       //Intent to Check Invites
            startActivity(intent);
        }
    }

    private class MyAdapter extends ArrayAdapter<DrugPO> {
        public MyAdapter() {
            super(ViewDrugListActivity.this, R.layout.item_view, DrugList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
            }

            DrugPO currentDrug = DrugList.get(position);

            TextView textDrugName = itemView.findViewById((R.id.item_drugName));
            textDrugName.setText(currentDrug.getmName());

            TextView textPillCount = itemView.findViewById((R.id.item_pillCount));
            textPillCount.setText("Pill Count: 123");

            return itemView;

        }
    }
}
