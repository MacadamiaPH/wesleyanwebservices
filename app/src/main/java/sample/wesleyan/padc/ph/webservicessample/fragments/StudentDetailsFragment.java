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

public class StudentDetailsFragment extends Fragment implements View.OnClickListener{
    private static StudentDetailsFragment studentDetailsFragment;

    public StudentDetailsFragment() {

    }

    public static StudentDetailsFragment getInstance() {
        studentDetailsFragment = new StudentDetailsFragment();

        return studentDetailsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_student_details, container, false);
        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
