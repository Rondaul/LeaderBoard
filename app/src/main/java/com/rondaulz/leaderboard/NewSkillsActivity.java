package com.rondaulz.leaderboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class NewSkillsActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private EditText mAddSkillsEditText;
    private ImageButton mAddSkillsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_skills);

        mToolbar = (Toolbar) findViewById(R.id.new_skills_toolbar);
        setSupportActionBar(mToolbar);

        mAddSkillsEditText = (EditText) findViewById(R.id.skill_text_view);
        mAddSkillsButton = (ImageButton) findViewById(R.id.skill_add_image_button);

        mAddSkillsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = new EditText(NewSkillsActivity.this);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.anim_stay, R.anim.slide_out_down);
    }
}
