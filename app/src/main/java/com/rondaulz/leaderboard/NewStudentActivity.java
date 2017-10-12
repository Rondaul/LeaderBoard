package com.rondaulz.leaderboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class NewStudentActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private EditText mEmailEditText, mPasswordEditText, mNameEditText;
    private Button mAddButton;
    private ProgressBar mProgressBar;
    private Toolbar mToolbar;

    private static final String TAG = ".NewStudentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);
        mAuth = FirebaseAuth.getInstance();

        mEmailEditText = (EditText) findViewById(R.id.new_stuent_email);
        mPasswordEditText = (EditText) findViewById(R.id.new_student_password);
        mNameEditText = (EditText) findViewById(R.id.new_student_name);
        mAddButton = (Button) findViewById(R.id.new_student_add_button);
        mProgressBar = (ProgressBar) findViewById(R.id.new_student_progressBar);
        mToolbar =(Toolbar) findViewById(R.id.new_student_toolbar);

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

                mProgressBar.setVisibility(View.VISIBLE);

                //create user
                /*mAuth.createUserWithEmailAndPassword(email, password)
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

                                    //DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();

                                    //SampleClass newStudent = new SampleClass(name);

                                    //mRef.child("Students").child("Marks").child(UserId).setValue(newStudent);
                                    mAuth.signOut();
                                    mAuth.signInWithEmailAndPassword("ronxdaulz@gmail.com","15sksb7059")
                                            .addOnCompleteListener(NewStudentActivity.this, new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    //admin sign in again
                                                }
                                            });
                                } else {
                                    Toast.makeText(NewStudentActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });*/
            }
        });
    }

        @Override
    protected void onPause()
    {
        super.onPause();
        overridePendingTransition(R.anim.anim_stay, R.anim.slide_out_down);
    }
}
