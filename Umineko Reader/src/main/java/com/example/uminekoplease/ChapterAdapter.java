package com.example.uminekoplease;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ChapterAdapter extends BaseAdapter{

    //Attributes
    private Context context;
    private List<Chapter> chapterList;
    private LayoutInflater inflater;

    //Constructor
    public ChapterAdapter(Context context, List<Chapter> chapterList)
    {
        this.chapterList=chapterList;
        this.context=context;
        this.inflater=LayoutInflater.from(context);
    }

    //Get the view we need
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.adapter_item,null);
        //get info
        Chapter current = getItem(i);
        String name= current.getName();
        String date= current.getDate();
        //get view
        TextView myView= view.findViewById(R.id.chapter_name);
        myView.setText(name);

        //Get date
        TextView dateView = view.findViewById(R.id.chapter_date);
        dateView.setText(date);
        //Get The Intent to the new Activity from Context
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(current.getIntent());
               // ((Activity)context).finish();
            }
        });
        return view;
    }

    @Override
    public int getCount() {
        return chapterList.size();
    }

    @Override
    public Chapter getItem(int position) {
        return chapterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}
