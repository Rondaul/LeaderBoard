package com.rondaulz.leaderboard;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private FirebaseAuth mAuth;
    private DatabaseReference mReference;
    private DatabaseReference mDatabaseReference;
    private Context mContext;
    private List<StudentData> MarksList;
    private List<StudentData> AttendanceList;
    private String title;
    private String uid;
    private int number;
    private String key;
    private FirebaseUser mUser;

    public MyAdapter(Context mContext, List<StudentData> MarksList, List<StudentData> AttendanceList, String title, int number) {
        this.mContext = mContext;
        this.MarksList = MarksList;
        this.AttendanceList = AttendanceList;
        this.title = title;
        this.number = number;
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mReference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        if (mUser != null) {
            uid = mUser.getUid();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView mItemName, mItemRegNo, mItemNo, mTotal;
        CardView mCardView;
        CircleImageView mImageView;
        Context mContext;
        List<StudentData> MarksList;
        List<StudentData> AttendanceList;
        int number;
        StudentData studentData;

        public MyViewHolder(View view, Context mContext, List<StudentData> MarksList, List<StudentData> AttendanceList, int number) {
            super(view);
            this.mContext = mContext;
            this.MarksList = MarksList;
            this.AttendanceList = AttendanceList;
            this.number = number;
            view.setOnClickListener(this);
            if (mUser != null) {
                if (uid.equals("CZ2vRYzd4BUGYj8TdA2TvrcNkYa2")) {
                    view.setOnLongClickListener(this);
                }
            }

            mItemName = (TextView) view.findViewById(R.id.card_name);
            mItemRegNo = (TextView) view.findViewById(R.id.card_regno);
            mItemNo = (TextView) view.findViewById(R.id.item_id);
            mImageView = (CircleImageView) view.findViewById(R.id.item_photo);
            mTotal = view.findViewById(R.id.card_total);
            mCardView = view.findViewById(R.id.card_view);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            StudentData studentData;
            if (number == 0) {
                studentData = this.MarksList.get(position);
            } else {
                studentData = this.AttendanceList.get(position);
            }
            Intent intent = new Intent(mContext, StudentDataDetailsActivity.class);
            intent.putExtra("img_id", studentData.getImageId());
            intent.putExtra("name", studentData.getName());
            intent.putExtra("regno", studentData.getRegno());
            intent.putExtra("total", "" + studentData.getTotal());

            intent.putExtra("DE", "" + studentData.getFirstSemester().getDigitalElectronics());
            intent.putExtra("Eng", "" + studentData.getFirstSemester().getEnglish());
            intent.putExtra("DS", "" + studentData.getSecondSemester().getDataStructures());
            intent.putExtra("dbms", "" + studentData.getSecondSemester().getDbms());
            intent.putExtra("CPlus", "" + studentData.getThirdSemester().getcPlus());
            intent.putExtra("OS", "" + studentData.getThirdSemester().getOperatingSystem());
            intent.putExtra("unix", "" + studentData.getFourthSemester().getUnix());
            intent.putExtra("vb", "" + studentData.getFourthSemester().getVb());
            intent.putExtra("title", title);
            this.mContext.startActivity(intent);
        }

        @Override
        public boolean onLongClick(View v) {
            int position = getAdapterPosition();
            if (number == 0) {
                studentData = this.MarksList.get(position);
            } else {
                studentData = this.AttendanceList.get(position);
            }
            //creating a popup menu
            PopupMenu popupMenu = new PopupMenu(mContext, v);
            popupMenu.inflate(R.menu.menu_action_mode);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.id_edit:
                            //handle edit click
                            Intent intent = getIntent(studentData);
                            mContext.startActivity(intent);
                            break;
                        case R.id.id_delete:
                            //handle delete click
                            AlertDialog.Builder alert = new AlertDialog.Builder(mContext);

                            LayoutInflater dataInflater = LayoutInflater.from(mContext);
                            alert.setTitle("Warning!");
                            alert.setCancelable(false);

                            alert.setMessage("Are you sure?");
                            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //Cancel delete
                                }
                            });

                            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    deleteRecyclerViewItem(studentData);
                                }
                            });
                            AlertDialog mDialog = alert.create();
                            mDialog.show();
                            mDialog.getButton(mDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                            mDialog.getButton(mDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                            break;
                    }
                    return false;
                }
            });
            popupMenu.show();
            return true;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new MyViewHolder(itemView, mContext, MarksList, AttendanceList, number);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final StudentData studentData;
        if (number == 0) {
            studentData = MarksList.get(position);
        } else {
            studentData = AttendanceList.get(position);
        }
        int count = position + 1;
        holder.mItemName.setText("" + studentData.getName());
        holder.mItemRegNo.setText("Reg No: " + studentData.getRegno());
        holder.mItemNo.setText("" + count);
        holder.mTotal.setText("" + studentData.getTotal() + " out of 800");
        holder.mImageView.setImageResource(R.drawable.aims_logo);
    }

    @Override
    public int getItemCount() {
        if (number == 0) {
            return MarksList.size();
        } else {
            return AttendanceList.size();
        }
    }

    public void deleteRecyclerViewItem(StudentData studentData) {
        key = studentData.getKey();
        mReference.child("Students").child("Marks").child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //Delete successfull then delete attendance node
                if (task.isSuccessful()) {
                    mDatabaseReference.child("Students").child("Attendance").child(key).removeValue().addOnCompleteListener(
                            new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        notifyDataSetChanged();
                                    }
                                }
                            });
                }
            }
        });
    }

    public Intent getIntent(StudentData studentData) {
        Intent intent = new Intent(mContext, UpdateStudentDataActivity.class);
        intent.putExtra("key", "" + studentData.getKey());
        return intent;
    }
}