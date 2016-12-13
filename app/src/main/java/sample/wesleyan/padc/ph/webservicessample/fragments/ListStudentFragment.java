package sample.wesleyan.padc.ph.webservicessample.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import sample.wesleyan.padc.ph.webservicessample.R;

/**
 * Created by Macadamia on 12/13/2016.
 */

public class ListStudentFragment extends Fragment implements AdapterView.OnItemClickListener{
    private static ListStudentFragment listStudentFragment;
    private ListView lv_students;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> studentlist = new ArrayList<String>();

    public ListStudentFragment() {

    }

    public static ListStudentFragment getInstance() {
        listStudentFragment = new ListStudentFragment();

        return listStudentFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_studentlist, container, false);
        lv_students = (ListView) view.findViewById(R.id.lv_students);


        studentlist.add("Marco");
        studentlist.add("Christopher");
        studentlist.add("Flare");
        studentlist.add("Legionaire");
        studentlist.add("Athropos");
        studentlist.add("Rhelp");
        studentlist.add("Era");

        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, studentlist);
        lv_students.setAdapter(adapter);
        lv_students.setOnItemClickListener(this);
        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(getActivity().getApplicationContext(), studentlist.get(position),Toast.LENGTH_SHORT).show();
    }
}
