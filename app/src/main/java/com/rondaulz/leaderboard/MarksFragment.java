package com.rondaulz.leaderboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MarksFragment extends Fragment {

    private List<StudentData> mMarksList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private int total = 0;

    public MarksFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_marks, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.marks_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //get Firebase Reference
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference().child("Students").child("Marks");

        mReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //Remove data from the ArrayList after deletion
                String key = dataSnapshot.getKey();
                for (StudentData studentData : mMarksList) {
                    if (key.equals(studentData.getKey())) {
                        mMarksList.remove(studentData);
                        break;
                    }
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
        return view;
    }

    public void findTotal(StudentData value) {
        total = value.getFirstSemester().getDigitalElectronics() + value.getFirstSemester().getEnglish() + value.getSecondSemester().getDataStructures()
                + value.getSecondSemester().getDbms() + value.getThirdSemester().getcPlus() + value.getThirdSemester().getOperatingSystem() + value.getFourthSemester().getUnix()
                + value.getFourthSemester().getVb();
        value.setTotal(total);
    }

    private void fetchData(DataSnapshot dataSnapshot) {
        StudentData value = dataSnapshot.getValue(StudentData.class);
        Log.v("StudentData Fragment", "" + dataSnapshot.getValue());
        value.setKey(dataSnapshot.getKey());
        Log.v("Key Values", value.getKey());
        findTotal(value);

        // Get an iterator.
        Iterator<StudentData> ite = mMarksList.iterator();

        while (ite.hasNext()) {
            StudentData iteValue = ite.next();
            if (iteValue.equals(value))
                ite.remove();
        }
        mMarksList.add(value);
        Collections.sort(mMarksList, new MarksComparator());
        String title = mReference.getKey();

        // specify an adapter
        mAdapter = new MyAdapter(getContext(), mMarksList, null, title, 0);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);
    }
}

