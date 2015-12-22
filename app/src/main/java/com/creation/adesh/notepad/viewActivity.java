package com.creation.adesh.notepad;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.logging.Logger;

public class viewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
    }
    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences pref=getSharedPreferences("work", MODE_PRIVATE);
        int count=pref.getInt("Count", 0);
        String note;
        Log.i("viewActivity", "count is " + count);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        params.setMargins(10,10,10,10);
        if(count==0){
            TextView t = new TextView(this);
            t.setText("No notes available!\nTry adding some?");
            t.setTextSize(20);
            t.setTextColor(Color.WHITE);
            t.setGravity(Gravity.CENTER);
            LinearLayout l = (LinearLayout) findViewById(R.id.view_act);
            l.addView(t);
            return;
        }
        while(count>0) {
            Log.i("viewact", "looping");
            TextView t = new TextView(this);
            note = pref.getString("Note" + count, "");
            t.setTextSize(20);
            t.setBackground(getResources().getDrawable(R.drawable.viewback));
            t.setLayoutParams(params);
            t.setPadding(30, 0, 10, 20);
            Spannable span = new SpannableString(note);
            int end=note.indexOf("\n");
            span.setSpan(new RelativeSizeSpan(0.5f), 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            t.setText(span);
            LinearLayout l = (LinearLayout) findViewById(R.id.view_act);
            l.addView(t);
            count--;
        }
    }
}
