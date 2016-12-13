package sample.wesleyan.padc.ph.webservicessample.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sample.wesleyan.padc.ph.webservicessample.R;

/**
 * Created by Macadamia on 12/13/2016.
 */

public class AddStudentFragment extends Fragment implements View.OnClickListener{
    private static AddStudentFragment addStudentFragment;

    public AddStudentFragment() {

    }

    public static AddStudentFragment getInstance() {
        addStudentFragment = new AddStudentFragment();

        return addStudentFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_add_student, container, false);
        return view;
    }


    @Override
    public void onClick(View view) {

    }
}
