package com.rondaulz.leaderboard;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class DatabaseDetailsFragment extends Fragment {
    private Button mFirstSemButton, mSecondSemButton, mThirdSemButton, mFourthSemButton;
    private String de;
    private String eng;
    private String ds;
    private String dbms;
    private String cplus;
    private String os;
    private String unix;
    private String vb;
    private Bundle bundle;

    private static final String DIG = "digitalElectronics";
    private static final String ENG = "English";
    private static final String DS = "DataStructures";
    private static final String DBMS = "DatabaseManagementSystem";
    private static final String CPlUS = "CPlus";
    private static final String OS = "OperatingSystem";
    private static final String UNIX = "unix";
    private static final String VB = "VisualBasic";

    public DatabaseDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        if (bundle != null) {
            de = bundle.getString(DIG);
            eng = bundle.getString(ENG);
            ds = bundle.getString(DS);
            dbms = bundle.getString(DBMS);
            cplus = bundle.getString(CPlUS);
            os = bundle.getString(OS);
            unix = bundle.getString(UNIX);
            vb = bundle.getString(VB);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_database_details, container, false);

        mFirstSemButton = (Button) view.findViewById(R.id.first_sem_button);
        mSecondSemButton = view.findViewById(R.id.second_sem_button);
        mThirdSemButton = view.findViewById(R.id.third_sem_button);
        mFourthSemButton = view.findViewById(R.id.fourth_sem_button);

        mFirstSemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                LayoutInflater dataInflater = getActivity().getLayoutInflater();
                alert.setTitle("First Semester");
                alert.setCancelable(false);

                View dialog = dataInflater.inflate(R.layout.activity_final_data_details, null);

                TextView mFirst = (TextView) dialog.findViewById(R.id.first_mark);
                TextView mSecond = dialog.findViewById(R.id.second_mark);

                mFirst.setText("Digital Electronic: " + de);
                mSecond.setText("English: " + eng);

                alert.setView(dialog);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //Cancel alert Dialog
                    }
                });
                AlertDialog mDialog = alert.create();
                mDialog.show();
                mDialog.getButton(mDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
            }
        });

        mSecondSemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                LayoutInflater dataInflater = getActivity().getLayoutInflater();
                alert.setTitle("Second Semester");
                alert.setCancelable(false);

                View dialog = dataInflater.inflate(R.layout.activity_final_data_details, null);

                TextView mFirst = (TextView) dialog.findViewById(R.id.first_mark);
                TextView mSecond = dialog.findViewById(R.id.second_mark);

                mFirst.setText("Data Structures: " + ds);
                mSecond.setText("Database Management System: " + dbms);

                alert.setView(dialog);
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //Cancel alert Dialog
                    }
                });
                AlertDialog mDialog = alert.create();
                mDialog.show();
                mDialog.getButton(mDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
            }
        });

        mThirdSemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

                LayoutInflater dataInflater = getActivity().getLayoutInflater();
                alert.setTitle("Third Semester");
                alert.setCancelable(false);

                View dialog = dataInflater.inflate(R.layout.activity_final_data_details, null);

                TextView mFirst = (TextView) dialog.findViewById(R.id.first_mark);
                TextView mSecond = dialog.findViewById(R.id.second_mark);

                mFirst.setText("C++: " + cplus);
                mSecond.setText("Operating System: " + os);

                alert.setView(dialog);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //Cancel alert Dialog
                    }
                });
                AlertDialog mDialog = alert.create();
                mDialog.show();
                mDialog.getButton(mDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
            }
        });

        mFourthSemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

                LayoutInflater dataInflater = getActivity().getLayoutInflater();
                alert.setTitle("Fourth Semester");
                alert.setCancelable(false);

                View dialog = dataInflater.inflate(R.layout.activity_final_data_details, null);

                TextView mFirst = (TextView) dialog.findViewById(R.id.first_mark);
                TextView mSecond = dialog.findViewById(R.id.second_mark);

                mFirst.setText("Unix: " + unix);
                mSecond.setText("Visual Basic: " + vb);

                alert.setView(dialog);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //Cancel alert Dialog
                    }
                });
                AlertDialog mDialog = alert.create();
                mDialog.show();
                mDialog.getButton(mDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
            }
        });
        return view;
    }
}

