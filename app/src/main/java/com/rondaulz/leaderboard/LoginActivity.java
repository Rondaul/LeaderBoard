package com.rondaulz.leaderboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText mInputEmail, mInputPassword;
    private FirebaseAuth mAuth;
    private ProgressBar mProgressBar;
    private Button mBtnLogin, mBtnReset;

    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Get Firebase Instance
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            if(mAuth.getCurrentUser().getUid().equals("CZ2vRYzd4BUGYj8TdA2TvrcNkYa2")) {
                startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                finish();
            }else {
                startActivity(new Intent(LoginActivity.this, StudentActivity.class));
                finish();
            }
        }

        setContentView(R.layout.activity_login);


        mInputEmail = (EditText) findViewById(R.id.email);
        mInputPassword = (EditText) findViewById(R.id.password);
        mProgressBar = (ProgressBar) findViewById(R.id.main_progressBar);
        mBtnLogin = (Button) findViewById(R.id.student_login);
        mBtnReset = (Button) findViewById(R.id.btn_reset_password);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mInputEmail.getText().toString();
                final String password = mInputPassword.getText().toString();
                if(CheckNetwork.isInternetAvailable(LoginActivity.this)) //returns true if internet available
                {
                    if (TextUtils.isEmpty(email)) {
                        Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(password)) {
                        Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    mProgressBar.setVisibility(View.VISIBLE);

                    //authenticate user
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    mProgressBar.setVisibility(View.GONE);
                                    if (!task.isSuccessful()) {
                                        // there was an error
                                        if (password.length() < 6) {
                                            mInputPassword.setError(getString(R.string.minimum_password));
                                        } else {
                                            Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        FirebaseUser user = mAuth.getInstance().getCurrentUser();
                                        if (user.getUid().equals("CZ2vRYzd4BUGYj8TdA2TvrcNkYa2")) {
                                            Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Intent intent = new Intent(LoginActivity.this, StudentActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                }
                            });

                }
                else
                {
                    try {
                        AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();

                        alertDialog.setTitle("No Internet");
                        alertDialog.setMessage("Check your internet connectivity and try again");
                        alertDialog.setIcon(android.R.drawable.stat_sys_warning);
                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int n) {
                                dialog.cancel();

                            }
                        });

                        alertDialog.show();
                    }
                    catch(Exception e)
                    {
                        //Log.d(Constants.TAG, "Show Dialog: "+e.getMessage());
                    }
                }

            }
        });
    }
}
