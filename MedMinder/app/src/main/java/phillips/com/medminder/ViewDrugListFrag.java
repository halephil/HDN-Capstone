package phillips.com.medminder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewDrugListFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewDrugListFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewDrugListFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private List<DrugPO> DrugList = new ArrayList<DrugPO>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ViewDrugListFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewDrugListFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewDrugListFrag newInstance(String param1, String param2) {
        ViewDrugListFrag fragment = new ViewDrugListFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        addDrugToList(new DrugPO("Drug1","Hello", "123"));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_view_drug_list, container, false);

        populateListView(view);

        Button btnAddDrug = (Button)view.findViewById(R.id.addDrugBtn);
        ListView lv = (ListView)view.findViewById(R.id.drugListView);

        btnAddDrug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), EditDrugInfoActivity.class);
                startActivityForResult(intent,1);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DrugPO drugPO = DrugList.get(position);
                Intent intent = new Intent(getActivity().getApplicationContext(), ViewDrugInfoActivity.class);
                intent.putExtra("drug", drugPO);
                startActivityForResult(intent,2);
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void addDrugToList(DrugPO drug){
        if (drug == null){
            return;
        }
        else
            DrugList.add(drug);
    }

    private void populateListView(View view) {
        ArrayAdapter<DrugPO> adapter = new MyAdapter(getContext());
        ListView list = (ListView)view.findViewById(R.id.drugListView);
        list.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1 && resultCode==RESULT_OK){
            DrugPO drug = (DrugPO) data.getExtras().getParcelable("drug");
            addDrugToList(drug);
            populateListView(getView());
        }

    }

    private class MyAdapter extends ArrayAdapter<DrugPO> {
        public MyAdapter(Context context) {
            super(context, R.layout.item_view, DrugList);
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
            textPillCount.setText("Pill Count: " + currentDrug.getmPillCount());

            return itemView;
        }
    }

}






