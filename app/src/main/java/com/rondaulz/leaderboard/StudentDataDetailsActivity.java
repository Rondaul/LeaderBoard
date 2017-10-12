package com.rondaulz.leaderboard;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class StudentDataDetailsActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView mImageView;
    TextView mNameText, mRegnoText, mTotalText;
    TabLayout mTablayout;
    ViewPager mViewPager;
    String title;
    private List<String> marks= new ArrayList<>();
    private static final String VALUE = "digitalElectronics";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_data_details);


        toolbar = (Toolbar) findViewById(R.id.details_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        marks.add(getIntent().getStringExtra("DE"));
        marks.add(getIntent().getStringExtra("Eng"));
        marks.add(getIntent().getStringExtra("DS"));
        marks.add(getIntent().getStringExtra("dbms"));
        marks.add(getIntent().getStringExtra("CPlus"));
        marks.add(getIntent().getStringExtra("OS"));
        marks.add(getIntent().getStringExtra("unix"));
        marks.add(getIntent().getStringExtra("vb"));

        title = getIntent().getStringExtra("title");

        mViewPager = (ViewPager) findViewById(R.id.student_details_viewpager);
        setupViewPager(mViewPager, marks);

        mTablayout = (TabLayout) findViewById(R.id.student_details_tabs);
        mTablayout.setupWithViewPager(mViewPager);

        mImageView = (ImageView) findViewById(R.id.details_student_image);
        mNameText = (TextView) findViewById(R.id.details_student_name);
        mRegnoText = (TextView) findViewById(R.id.details_regno);
        mTotalText = (TextView) findViewById(R.id.details_total);

        Picasso.with(this)
                .load(getIntent().getStringExtra("img_id"))
                .into(mImageView);
        mNameText.setText(getIntent().getStringExtra("name"));
        mRegnoText.setText("Reg No: " + getIntent().getStringExtra("regno"));
        mTotalText.setText("Total: " + getIntent().getStringExtra("total"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager, List<String> marks) {
        Log.i(VALUE, marks.get(0));
        StudentDetailsViewPagerAdapter adapter = new StudentDetailsViewPagerAdapter(getSupportFragmentManager(), marks);
        adapter.addFragment(new DatabaseDetailsFragment(), title);
        adapter.addFragment(new SkillDetailsFragment(), "Skills & Achievements");
        viewPager.setAdapter(adapter);
    }

}
