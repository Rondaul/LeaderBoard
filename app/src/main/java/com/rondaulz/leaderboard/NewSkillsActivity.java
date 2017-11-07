package com.rondaulz.leaderboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewSkillsActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private EditText mAddSkillsEditText;
    private ImageButton mAddSkillsButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_skills);

        mAuth = FirebaseAuth.getInstance();
        final DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();

        mToolbar = (Toolbar) findViewById(R.id.new_skills_toolbar);
        setSupportActionBar(mToolbar);

        mAddSkillsEditText = (EditText) findViewById(R.id.skill_text_view);
        mAddSkillsButton = (ImageButton) findViewById(R.id.skill_add_image_button);

        mAddSkillsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String skill = mAddSkillsEditText.getText().toString();
                mRef.child("Students").child("Skill").child(mAuth.getCurrentUser().getUid()).push().setValue(skill);
                mAddSkillsEditText.setText(null);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.anim_stay, R.anim.slide_out_down);
    }
}
