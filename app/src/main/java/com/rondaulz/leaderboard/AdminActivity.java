package com.rondaulz.leaderboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class AdminActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Toolbar adminToolbar;
    private TabLayout adminTabLayout;
    private ViewPager adminViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        mAuth= FirebaseAuth.getInstance();
        adminToolbar = (Toolbar) findViewById(R.id.admin_toolbar);
        setSupportActionBar(adminToolbar);

        adminViewPager = (ViewPager) findViewById(R.id.admin_viewpager);
        setupViewPager(adminViewPager);

        adminTabLayout = (TabLayout) findViewById(R.id.admin_tabs);
        adminTabLayout.setupWithViewPager(adminViewPager);

        FloatingActionButton adminfab = (FloatingActionButton) findViewById(R.id.admin_fab);


        adminfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, NewStudentActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_up, R.anim.anim_stay);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.item_logout) {
            mAuth.signOut();
            // this listener will be called when there is change in firebase user session
            FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user == null) {
                        // user auth state is changed - user is null
                        // launch login activity
                        startActivity(new Intent(AdminActivity.this, LoginActivity.class));
                        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                        finish();

                    }
                }
            };
            FirebaseAuth.getInstance().addAuthStateListener(authListener);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MarksFragment(), "MARKS");
        adapter.addFragment(new AttendanceFragment(), "ATTENDANCE");
        viewPager.setAdapter(adapter);
    }
}
