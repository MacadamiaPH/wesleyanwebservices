package sample.wesleyan.padc.ph.webservicessample.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import sample.wesleyan.padc.ph.webservicessample.model.Student;

/**
 * Created by Macadamia on 12/13/2016.
 */

public class StudentListAdapter extends ArrayAdapter<Student> {
    List studentList;

    public StudentListAdapter(Context context, int viewResourceId,
                           List<Student> studentList) {
        super(context, viewResourceId, studentList);
        this.studentList = studentList;
    }

    static class ViewHolder {
        TextView tv_name;
        TextView tv_details;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }
}
