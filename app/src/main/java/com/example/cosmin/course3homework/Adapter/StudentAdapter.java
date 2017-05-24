package com.example.cosmin.course3homework.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cosmin.course3homework.Model.Student;
import com.example.cosmin.course3homework.R;

import java.util.List;

/**
 * Created by Cosmin on 5/21/2017.
 */

public class StudentAdapter extends BaseAdapter implements View.OnClickListener{
    private List<Student> mStudentList;
    private Context mContext;

    public StudentAdapter(List<Student> mStudentList, Context mContext) {
        this.mStudentList = mStudentList;
        this.mContext = mContext;
    }

    //get the number of the list
    @Override
    public int getCount() {
        if (mStudentList == null)
            return 0;
        else
            return mStudentList.size();
    }

    //get the object from a specified position
    @Override
    public Object getItem(int position) {
        if (mStudentList == null)
            return null;
        else
            return mStudentList.get(position);
    }

    //get the item it from a specified position
    @Override
    public long getItemId(int position) {
        return 0;
    }

    //build the view of the item from a specified position
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        final int currentPosition = position;
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            int layoutId = R.layout.student_item;
            view = layoutInflater.inflate(layoutId, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        Student student = (Student) getItem(currentPosition);
        viewHolder.mNumePrenumeTextView.setText(student.getNume().toString()+" "+student.getPrenume());
        viewHolder.mFacultateTextView.setText(student.getuniversitate());
        viewHolder.mTelefonImagieView.setOnClickListener(this);


        return view;

    }

    @Override
    public void onClick(View v) {

    }

    //Design pattern ViewHolder

    class ViewHolder {
        protected TextView mNumePrenumeTextView;
        protected TextView mFacultateTextView;
        protected ImageView mTelefonImagieView;

        public ViewHolder(View view){
           mNumePrenumeTextView=(TextView)view.findViewById(R.id.tv_numeprenume);
            mFacultateTextView=(TextView)view.findViewById(R.id.tv_facultate);
            mTelefonImagieView=(ImageView)view.findViewById(R.id.im_telefon);

        }
    }

}

