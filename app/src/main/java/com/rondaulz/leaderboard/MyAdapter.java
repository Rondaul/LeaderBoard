package com.rondaulz.leaderboard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Ron on 06-08-2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private FirebaseAuth mAuth;
    private Context mContext;
    private List<StudentData> studentDataList;
    private String title;


    public MyAdapter(Context mContext, List<StudentData> studentDataList, String title) {
        this.mContext = mContext;
        this.studentDataList = studentDataList;
        this.title = title;
        mAuth = FirebaseAuth.getInstance();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView mItemName, mItemRegNo, mItemNo, mTotal;
        CardView mCardView;
        CircleImageView mImageView;
        Context mContext;
        List<StudentData> studentDataList;


        public MyViewHolder(View view, Context mContext, List<StudentData> studentDataList) {
            super(view);
            this.mContext = mContext;
            this.studentDataList = studentDataList;
            view.setOnClickListener(this);
            if(mAuth.getCurrentUser().getUid().equals("CZ2vRYzd4BUGYj8TdA2TvrcNkYa2")) {
                view.setOnLongClickListener(this);
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
            StudentData studentData = this.studentDataList.get(position);
            Intent intent = new Intent(mContext, StudentDataDetailsActivity.class);
            intent.putExtra("img_id", studentData.getImageId());
            intent.putExtra("name", studentData.getName());
            intent.putExtra("regno", studentData.getRegno());
            intent.putExtra("total", "" + studentData.getTotal());

            intent.putExtra("DE", "" + studentData.getFirstSemester().getDigitalElectronics());
            intent.putExtra("Eng", "" + studentData.getFirstSemester().getEnglish());
            intent.putExtra("DS" , "" + studentData.getSecondSemester().getDataStructures());
            intent.putExtra("dbms", "" + studentData.getSecondSemester().getDbms());
            intent.putExtra("CPlus" , "" + studentData.getThirdSemester().getcPlus());
            intent.putExtra("OS" , "" + studentData.getThirdSemester().getOperatingSystem());
            intent.putExtra("unix" , "" + studentData.getFourthSemester().getUnix());
            intent.putExtra("vb" , "" + studentData.getFourthSemester().getVb());
            intent.putExtra("title", title);
            this.mContext.startActivity(intent);
        }

        @Override
        public boolean onLongClick(View v) {
            int position = getAdapterPosition();
            StudentData studentData = this.studentDataList.get(position);
            //creating a popup menu
            PopupMenu popupMenu = new PopupMenu(mContext, v);
            popupMenu.inflate(R.menu.menu_action_mode);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch(item.getItemId()) {
                        case R.id.id_edit:
                            //handle edit click
                            break;
                        case R.id.id_delete:
                            //handle delete click
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

        return new MyViewHolder(itemView, mContext, studentDataList);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final StudentData studentData = studentDataList.get(position);
        int count = position + 1;
        holder.mItemName.setText("" + studentData.getName());
        holder.mItemRegNo.setText("Reg No: " + studentData.getRegno());
        holder.mItemNo.setText("" + count);
        holder.mTotal.setText(""+ studentData.getTotal() + " out of 800");
        Picasso.with(mContext)
                .load(studentData.getImageId())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(holder.mImageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(mContext)
                                .load(studentData.getImageId())
                                .error(R.drawable.android_party)
                                .into(holder.mImageView, new Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError() {
                                        Log.v("Picasso","Could not fetch image");
                                    }
                                });
                    }
                });
    }

    @Override
    public int getItemCount() {
        return (null != studentDataList ? studentDataList.size() : 0);
    }
}