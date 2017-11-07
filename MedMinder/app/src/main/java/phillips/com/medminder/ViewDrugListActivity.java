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

    private List<Drug> DrugList = new ArrayList<Drug>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drug_list);
        findViewById(R.id.addDrugBtn).setOnClickListener(this);

        populateDrugList();
        populateListView();

    }


    private void populateDrugList(){
        DrugList.add(new Drug("Drug1","This is Drug 1.", 10));
        DrugList.add(new Drug("Drug2","This is Drug 1.", 10));
        DrugList.add(new Drug("Drug3","This is Drug 1.", 10));
        DrugList.add(new Drug("Drug4","This is Drug 1.", 10));
        DrugList.add(new Drug("Drug5","This is Drug 1.", 10));
        DrugList.add(new Drug("Drug6","This is Drug 1.", 10));
        DrugList.add(new Drug("Drug7","This is Drug 1.", 10));
        DrugList.add(new Drug("Drug8","This is Drug 1.", 10));
        DrugList.add(new Drug("Drug9","This is Drug 1.", 10));
        DrugList.add(new Drug("Drug10","This is Drug 1.", 10));
    }

    private void populateListView() {
        ArrayAdapter<Drug> adapter = new MyAdapter();
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

    private class MyAdapter extends ArrayAdapter<Drug> {
        public MyAdapter() {
            super(ViewDrugListActivity.this, R.layout.item_view, DrugList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
            }

            Drug currentDrug = DrugList.get(position);

            TextView textDrugName = itemView.findViewById((R.id.item_drugName));
            textDrugName.setText(currentDrug.getName());

            TextView textPillCount = itemView.findViewById((R.id.item_pillCount));
            textPillCount.setText("Pill Count: 123");

            return itemView;

        }
    }
}
