package com.creation.adesh.notepad;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class viewActivity extends AppCompatActivity {
    public void addNote(){
        Intent i = new Intent(getBaseContext(), addActivity.class);
        startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.addmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fab:
                addNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    protected void onResume(){
        super.onResume();
        SharedPreferences pref=getSharedPreferences("work", MODE_PRIVATE);
        int count=pref.getInt("Count", 0);
        String note;
        Log.i("viewActivity", "count is " + count);
        RelativeLayout.LayoutParams params;
        RelativeLayout l = (RelativeLayout) findViewById(R.id.view_act);
        l.removeAllViews();
        if(count==0){
            TextView t = new TextView(this);
            t.setText("\tNo notes available.");
            t.setPadding(30, 30, 30, 30);
            t.setTextSize(20);
            t.setTextColor(Color.WHITE);
            t.setGravity(Gravity.CENTER);
            l.addView(t);
            return;
        }
        int origcount=count;
        while(count>0) {
            Log.i("viewact", "looping");
            TextView t = new TextView(this);
            note = pref.getString("Note" + count, "Error: Not Found!");
            t.setTextSize(20);
            t.setBackground(getResources().getDrawable(R.drawable.viewback));
            t.setPadding(30, 0, 10, 20);
            Spannable span = new SpannableString(note);
            int end=note.indexOf("\n");
            span.setSpan(new RelativeSizeSpan(0.5f), 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            t.setText(span);
            t.setId(count);
            params= new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,  RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10,10,10,10);
            if(count!=origcount)
                params.addRule(RelativeLayout.BELOW,count+1);
            t.setLayoutParams(params);
            l.addView(t,params);
            count--;
        }
    }
}
