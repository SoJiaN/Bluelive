package com.example.lenovo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetSongTitle extends AppCompatActivity {

    public EditText editText;
    public Button songButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_song_title);

        editText=(EditText)findViewById(R.id.setSongTitle_editText);
        songButton=(Button)findViewById(R.id.button_set_songtitle);
        songButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_title=new Intent();
                intent_title.putExtra("editText_info",editText.getText().toString());
                Log.e("SSSSDFIS",editText.getText().toString());
                SetSongTitle.this.setResult(RESULT_OK,intent_title);
                SetSongTitle.this.finish();
            }
        });

    }
}
