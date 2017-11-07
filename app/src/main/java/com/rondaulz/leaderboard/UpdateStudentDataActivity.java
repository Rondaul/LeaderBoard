package com.rondaulz.leaderboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateStudentDataActivity extends AppCompatActivity {
    private EditText mEmailEditText, mPasswordEditText, mNameEditText, mRegNoEditText, mIdEditText,
            mDigitalElectronicsEditText, mEnglishEditText, mDataStructuresEditText, mDbmsEditText, mCPlusEditText,
            mOperatingSystemEditText, mUnixEditText, mVbEditText, mAtDigitalElectronicsEditText, mAtEnglishEditText,
            mAtDataStructuresEditText, mAtDbmsEditText, mAtCPlusEditText,
            mAtOperatingSystemEditText, mAtUnixEditText, mAtVbEditText;
    private Button mAddButton;
    private Toolbar mToolbar;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private DatabaseReference mAtReference;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student_data);
        key = getIntent().getStringExtra("key");
        mDatabase = FirebaseDatabase.getInstance();

        mEmailEditText = (EditText) findViewById(R.id.edit_new_stuent_email);
        mPasswordEditText = (EditText) findViewById(R.id.edit_new_student_password);
        mNameEditText = (EditText) findViewById(R.id.edit_new_student_name);
        mRegNoEditText = (EditText) findViewById(R.id.edit_new_student_regno);
        mIdEditText = (EditText) findViewById(R.id.edit_new_student_id);
        mDigitalElectronicsEditText = (EditText) findViewById(R.id.edit_new_student_digitalElectronics);
        mEnglishEditText = (EditText) findViewById(R.id.edit_new_student_english);
        mDataStructuresEditText = (EditText) findViewById(R.id.edit_new_student_data_structures);
        mDbmsEditText = (EditText) findViewById(R.id.edit_new_student_dbms);
        mCPlusEditText = (EditText) findViewById(R.id.edit_new_student_cPlus);
        mOperatingSystemEditText = (EditText) findViewById(R.id.edit_new_student_operating_system);
        mUnixEditText = (EditText) findViewById(R.id.edit_new_student_unix);
        mVbEditText = (EditText) findViewById(R.id.edit_new_student_vb);

        //Initializing the EditText for attendance
        mAtDigitalElectronicsEditText = (EditText) findViewById(R.id.edit_new_student_at_digitalElectronics);
        mAtEnglishEditText = (EditText) findViewById(R.id.edit_new_student_at_english);
        mAtDataStructuresEditText = (EditText) findViewById(R.id.edit_new_student_at_data_structures);
        mAtDbmsEditText = (EditText) findViewById(R.id.edit_new_student_at_dbms);
        mAtCPlusEditText = (EditText) findViewById(R.id.edit_new_student_at_cPlus);
        mAtOperatingSystemEditText = (EditText) findViewById(R.id.edit_new_student_at_operating_system);
        mAtUnixEditText = (EditText) findViewById(R.id.edit_new_student_at_unix);
        mAtVbEditText = (EditText) findViewById(R.id.edit_new_student_at_vb);

        mAddButton = (Button) findViewById(R.id.edit_new_student_add_button);
        mToolbar = (Toolbar) findViewById(R.id.edit_new_student_toolbar);

        setSupportActionBar(mToolbar);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                mRef.child("Students").child("Marks").child(key).setValue(newStudent);
                mRef.child("Students").child("Attendance").child(key).setValue(newAtStudent);
                Toast.makeText(UpdateStudentDataActivity.this, "Student details updated successfully",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //Get Firebase database path reference
        mReference = mDatabase.getReference().child("Students").child("Marks");
        mReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                updateData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mAtReference = mDatabase.getReference().child("Students").child("Attendance");
        mAtReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                updateAtData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void updateData(DataSnapshot dataSnapshot) {
        StudentData studentData = dataSnapshot.getValue(StudentData.class);
        studentData.setKey(dataSnapshot.getKey());
        //set up all edit text
        if (key.equals(studentData.getKey())) {
            mNameEditText.setText(studentData.getName());
            mRegNoEditText.setText(studentData.getRegno());
            mIdEditText.setText("" + studentData.getId());
            mDigitalElectronicsEditText.setText("" + studentData.getFirstSemester().getDigitalElectronics());
            mEnglishEditText.setText("" + studentData.getFirstSemester().getEnglish());
            mDataStructuresEditText.setText("" + studentData.getSecondSemester().getDataStructures());
            mDbmsEditText.setText("" + studentData.getSecondSemester().getDbms());
            mCPlusEditText.setText("" + studentData.getThirdSemester().getcPlus());
            mOperatingSystemEditText.setText("" + studentData.getThirdSemester().getOperatingSystem());
            mUnixEditText.setText("" + studentData.getFourthSemester().getUnix());
            mVbEditText.setText("" + studentData.getFourthSemester().getVb());
        }
    }

    public void updateAtData(DataSnapshot dataSnapshot) {
        StudentData studentData = dataSnapshot.getValue(StudentData.class);
        studentData.setKey(dataSnapshot.getKey());
        if (key.equals(studentData.getKey())) {
            //set up all edit text
            mAtDigitalElectronicsEditText.setText("" + studentData.getFirstSemester().getDigitalElectronics());
            mAtEnglishEditText.setText("" + studentData.getFirstSemester().getEnglish());
            mAtDataStructuresEditText.setText("" + studentData.getSecondSemester().getDataStructures());
            mAtDbmsEditText.setText("" + studentData.getSecondSemester().getDbms());
            mAtCPlusEditText.setText("" + studentData.getThirdSemester().getcPlus());
            mAtOperatingSystemEditText.setText("" + studentData.getThirdSemester().getOperatingSystem());
            mAtUnixEditText.setText("" + studentData.getFourthSemester().getUnix());
            mAtVbEditText.setText("" + studentData.getFourthSemester().getVb());
        }
    }
}
