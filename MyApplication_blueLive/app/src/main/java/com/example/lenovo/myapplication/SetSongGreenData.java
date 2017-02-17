package com.example.lenovo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class SetSongGreenData extends AppCompatActivity {
    private EditText editText1,editText2,editText3,editText4,editText5,editText6,editText7,editText8,editText9,editText10,editText11,editText12,editText13,editText14,editText15;
    private Button button;
    public ArrayList<EditText> song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_song_green_data);

        song=new ArrayList<>();

        init();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_title_green=new Intent();
                for(int i=0;i<song.size();i++){
                    intent_title_green.putExtra("editText_info"+i,song.get(i).getText().toString());
                }

                Log.e("SSSSDFIS",editText1.getText().toString());
                SetSongGreenData.this.setResult(RESULT_OK,intent_title_green);
                SetSongGreenData.this.finish();
            }
        });


    }
    public void init(){
        editText1=(EditText)findViewById(R.id.edit_green_1);
        song.add(editText1);
        editText2=(EditText)findViewById(R.id.edit_green_2);
        song.add(editText2);
        editText3=(EditText)findViewById(R.id.edit_green_3);
        song.add(editText3);
        editText4=(EditText)findViewById(R.id.edit_green_4);
        song.add(editText4);
        editText5=(EditText)findViewById(R.id.edit_green_5);
        song.add(editText5);
        editText6=(EditText)findViewById(R.id.edit_green_6);
        song.add(editText6);
        editText7=(EditText)findViewById(R.id.edit_green_7);
        song.add(editText7);
        editText8=(EditText)findViewById(R.id.edit_green_8);
        song.add(editText8);
        editText9=(EditText)findViewById(R.id.edit_green_9);
        song.add(editText9);
        editText10=(EditText)findViewById(R.id.edit_green_10);
        song.add(editText10);
        editText11=(EditText)findViewById(R.id.edit_green_11);
        song.add(editText11);
        editText12=(EditText)findViewById(R.id.edit_green_12);
        song.add(editText12);
        editText13=(EditText)findViewById(R.id.edit_green_13);
        song.add(editText13);
        editText14=(EditText)findViewById(R.id.edit_green_14);
        song.add(editText14);
        editText15=(EditText)findViewById(R.id.edit_green_15);
        song.add(editText15);

        button=(Button)findViewById(R.id.button_green);

    }
}
