package com.rondaulz.leaderboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewStudentActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText mEmailEditText, mPasswordEditText, mNameEditText, mRegNoEditText, mIdEditText,
            mDigitalElectronicsEditText, mEnglishEditText, mDataStructuresEditText, mDbmsEditText, mCPlusEditText,
            mOperatingSystemEditText, mUnixEditText, mVbEditText, mAtDigitalElectronicsEditText, mAtEnglishEditText,
            mAtDataStructuresEditText, mAtDbmsEditText, mAtCPlusEditText,
            mAtOperatingSystemEditText, mAtUnixEditText, mAtVbEditText;
    private Button mAddButton;
    private ProgressBar mProgressBar;
    private Toolbar mToolbar;
    static String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);
        mAuth = FirebaseAuth.getInstance();

        mEmailEditText = (EditText) findViewById(R.id.new_stuent_email);
        mPasswordEditText = (EditText) findViewById(R.id.new_student_password);
        mNameEditText = (EditText) findViewById(R.id.new_student_name);
        mRegNoEditText = (EditText) findViewById(R.id.new_student_regno);
        mIdEditText = (EditText) findViewById(R.id.new_student_id);
        mDigitalElectronicsEditText = (EditText) findViewById(R.id.new_student_digitalElectronics);
        mEnglishEditText = (EditText) findViewById(R.id.new_student_english);
        mDataStructuresEditText = (EditText) findViewById(R.id.new_student_data_structures);
        mDbmsEditText = (EditText) findViewById(R.id.new_student_dbms);
        mCPlusEditText = (EditText) findViewById(R.id.new_student_cPlus);
        mOperatingSystemEditText = (EditText) findViewById(R.id.new_student_operating_system);
        mUnixEditText = (EditText) findViewById(R.id.new_student_unix);
        mVbEditText = (EditText) findViewById(R.id.new_student_vb);

        //Initializing the EditText for attendance
        mAtDigitalElectronicsEditText = (EditText) findViewById(R.id.new_student_at_digitalElectronics);
        mAtEnglishEditText = (EditText) findViewById(R.id.new_student_at_english);
        mAtDataStructuresEditText = (EditText) findViewById(R.id.new_student_at_data_structures);
        mAtDbmsEditText = (EditText) findViewById(R.id.new_student_at_dbms);
        mAtCPlusEditText = (EditText) findViewById(R.id.new_student_at_cPlus);
        mAtOperatingSystemEditText = (EditText) findViewById(R.id.new_student_at_operating_system);
        mAtUnixEditText = (EditText) findViewById(R.id.new_student_at_unix);
        mAtVbEditText = (EditText) findViewById(R.id.new_student_at_vb);

        mAddButton = (Button) findViewById(R.id.new_student_add_button);
        mProgressBar = (ProgressBar) findViewById(R.id.new_student_progressBar);
        mToolbar = (Toolbar) findViewById(R.id.new_student_toolbar);

        setSupportActionBar(mToolbar);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mNameEditText.getText() == null) {
                    mNameEditText.setError("Enter the name!");
                    return;
                }

                mProgressBar.setVisibility(View.VISIBLE);

                //create user
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(NewStudentActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (task.isSuccessful()) {
                                    Toast.makeText(NewStudentActivity.this, "User added Successfully", Toast.LENGTH_SHORT).show();
                                    mProgressBar.setVisibility(View.GONE);
                                    String UserId = mAuth.getCurrentUser().getUid();
                                    String name = mNameEditText.getText().toString();
                                    String regNo = mRegNoEditText.getText().toString();

                                    //Get the value of marks from editText
                                    int id = Integer.parseInt(mIdEditText.getText().toString());
                                    int digitalElectronics = Integer.parseInt(mDigitalElectronicsEditText.getText().toString());
                                    int english = Integer.parseInt(mEnglishEditText.getText().toString());
                                    int dataStructures = Integer.parseInt(mDataStructuresEditText.getText().toString());
                                    int dbms = Integer.parseInt(mDbmsEditText.getText().toString());
                                    int cPlus = Integer.parseInt(mCPlusEditText.getText().toString());
                                    int operatingSystem = Integer.parseInt(mOperatingSystemEditText.getText().toString());
                                    int unix = Integer.parseInt(mUnixEditText.getText().toString());
                                    int vb = Integer.parseInt(mVbEditText.getText().toString());

                                    //Get the values fo attendance from EditText
                                    int atDigitalElectronics = Integer.parseInt(mAtDigitalElectronicsEditText.getText().toString());
                                    int atEnglish = Integer.parseInt(mAtEnglishEditText.getText().toString());
                                    int atDataStructures = Integer.parseInt(mAtDataStructuresEditText.getText().toString());
                                    int atDbms = Integer.parseInt(mAtDbmsEditText.getText().toString());
                                    int atCPlus = Integer.parseInt(mAtCPlusEditText.getText().toString());
                                    int atOperatingSystem = Integer.parseInt(mAtOperatingSystemEditText.getText().toString());
                                    int atUnix = Integer.parseInt(mAtUnixEditText.getText().toString());
                                    int atVb = Integer.parseInt(mAtVbEditText.getText().toString());

                                    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();

                                    //Semester values for marks
                                    FirstSemester firstSemester = new FirstSemester(digitalElectronics, english);
                                    SecondSemester secondSemester = new SecondSemester(dataStructures, dbms);
                                    ThirdSemester thirdSemester = new ThirdSemester(cPlus, operatingSystem);
                                    FourthSemester fourthSemester = new FourthSemester(unix, vb);

                                    //Semester values for attendance
                                    FirstSemester atFirstSemester = new FirstSemester(atDigitalElectronics, atEnglish);
                                    SecondSemester atSecondSemester = new SecondSemester(atDataStructures, atDbms);
                                    ThirdSemester atThirdSemester = new ThirdSemester(atCPlus, atOperatingSystem);
                                    FourthSemester atFourthSemester = new FourthSemester(atUnix, atVb);

                                    NewStudentData newStudent = new NewStudentData(name, regNo, id, firstSemester, secondSemester,
                                            thirdSemester, fourthSemester);

                                    NewStudentData newAtStudent = new NewStudentData(name, regNo, id, atFirstSemester, atSecondSemester,
                                            atThirdSemester, atFourthSemester);

                                    mRef.child("Students").child("Marks").child(UserId).setValue(newStudent);
                                    mRef.child("Students").child("Attendance").child(UserId).setValue(newAtStudent);

                                    //If user added successfully, remove the value from all editText
                                    mEmailEditText.setText(null);
                                    mPasswordEditText.setText(null);
                                    mNameEditText.setText(null);
                                    mRegNoEditText.setText(null);
                                    mIdEditText.setText(null);
                                    mDigitalElectronicsEditText.setText(null);
                                    mEnglishEditText.setText(null);
                                    mDataStructuresEditText.setText(null);
                                    mDbmsEditText.setText(null);
                                    mCPlusEditText.setText(null);
                                    mOperatingSystemEditText.setText(null);
                                    mUnixEditText.setText(null);
                                    mVbEditText.setText(null);

                                    mAtDigitalElectronicsEditText.setText(null);
                                    mAtEnglishEditText.setText(null);
                                    mAtDataStructuresEditText.setText(null);
                                    mAtDbmsEditText.setText(null);
                                    mAtCPlusEditText.setText(null);
                                    mAtOperatingSystemEditText.setText(null);
                                    mAtUnixEditText.setText(null);
                                    mAtVbEditText.setText(null);

                                    mAuth.signOut();
                                    mAuth.signInWithEmailAndPassword("ronxdaulz@gmail.com", "15sksb7059")
                                            .addOnCompleteListener(NewStudentActivity.this, new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    //admin sign in again
                                                    key = mAuth.getCurrentUser().getUid();
                                                    Log.v("UserId", mAuth.getCurrentUser().getUid());
                                                    Intent intent = new Intent(NewStudentActivity.this, AdminActivity.class);
                                                    startActivity(intent);
                                                }
                                            });
                                } else {
                                    Toast.makeText(NewStudentActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.anim_stay, R.anim.slide_out_down);
    }
}
