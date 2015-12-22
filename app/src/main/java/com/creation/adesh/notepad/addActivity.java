package com.creation.adesh.notepad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class addActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }
    public void addNewNote(View view){
        String note=((EditText) findViewById(R.id.editNote)).getText().toString();
        if(note.length()==0)
            return;
        String time=((TextClock) findViewById(R.id.time)).getText().toString();
        String date = new SimpleDateFormat("dd MMM yyyy").format(Calendar.getInstance().getTime());
        note=date+" "+time+"\n"+note;
        SharedPreferences sharedPref=getSharedPreferences("work", MODE_PRIVATE);
        SharedPreferences.Editor edit=sharedPref.edit();
        int count=sharedPref.getInt("Count", 0);
        count++;
        edit.putString("Note" + count, note);
        edit.putInt("Count", count);
        Log.i("addact","new count is "+count+" note received is "+note);
        edit.commit();
//        Intent intent=new Intent(this,Init.class);
//       startActivity(intent);
        finish();
    }
}
