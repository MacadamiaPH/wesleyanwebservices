package sample.wesleyan.padc.ph.webservicessample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import sample.wesleyan.padc.ph.webservicessample.fragments.AddStudentFragment;
import sample.wesleyan.padc.ph.webservicessample.fragments.ListStudentFragment;

/**
 * Created by Macadamia on 12/13/2016.
 */

public class MainActivity extends AppCompatActivity {

    private View.OnClickListener fabOnClickListener;
    private Snackbar sb_message;
    private CoordinatorLayout cd_main;
    private FloatingActionButton fab;
    private ListView lv_students;
    private FrameLayout fl_fragmentholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fl_fragmentholder = (FrameLayout) findViewById(R.id.fl_fragmentholder);
        cd_main = (CoordinatorLayout) findViewById(R.id.cd_main);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fabOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                setFragment(ListStudentFragment.getInstance());
            }
        };

        sb_message = Snackbar.make(cd_main, "Add new student", Snackbar.LENGTH_LONG)
                             .setAction("CANCEL", fabOnClickListener);
        View snackbarView = sb_message.getView();
        snackbarView.setBackgroundColor(Color.DKGRAY);
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setFragment(AddStudentFragment.getInstance());
                sb_message.show();
            }
        });

        setFragment(ListStudentFragment.getInstance());
    }

    public void setFragment(Fragment newFragment){
        fl_fragmentholder.removeAllViews();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fl_fragmentholder, newFragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        setFragment(ListStudentFragment.getInstance());
    }
}
