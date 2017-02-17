package com.example.lenovo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import com.example.lenovo.myapplication.MyView.SongProgressbar;
import com.example.lenovo.myapplication.MyView.SongView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int UPDATE_PROGRESS=0;
    boolean isDownloading;
    boolean stop;
    private Button btnDownload;
    private SongView mSongView;
    private ListView mlistView;
    private ArrayList<itemBean> songArrayList;
    private String titleinfo;
    private String[] date;
    private itemBean itemBean1=new itemBean("圆圈百分比",titleinfo+"%");
    private itemBean itemBean2=new itemBean("绿色曲线","4,5,6,7");
    private itemBean itemBean3=new itemBean("灰色曲线",titleinfo+"%");

    boolean flag=false;

    SongProgressbar songProgressbar;
    Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_PROGRESS:
                    songProgressbar.setSchedule(msg.arg1);
                    if(msg.arg1==100){
                        isDownloading = false;
//                        btnDownload.setText("下载");
                    }
                    break;
//                case 1:
//                    songProgressbar.setSchedule(msg.arg1);
//                    if(msg.arg1>=){
//
//                    }
                default:
                    break;
            }
        };
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date=new String[15];

        mSongView = (SongView) findViewById(R.id.songView);
        songProgressbar = (SongProgressbar) this.findViewById(R.id.my_progress);
        songProgressbar.setMax(100);

        btnDownload =  (Button) this.findViewById(R.id.btn_download);

        songArrayList=new ArrayList<>();

//        if(titleinfo!=null){
            songArrayList.add(itemBean1);
//        }if(titleinfo==null){
//            songArrayList.add(itemBean1);
//        }


        songArrayList.add(itemBean2);
        songArrayList.add(itemBean3);


        mlistView=(ListView)findViewById(R.id.list_song_it);
        SongListAdapter liA=new SongListAdapter(this);
        mlistView.setAdapter(liA);
        liA.setData(songArrayList);




        if(!isDownloading){
            stop = false;
            isDownloading = true;
//            btnDownload.setText("停止");

//            if(titleinfo!=null){
//                SongProgressbar.percent=titleinfo;
//                stop = false;
//                isDownloading = true;
//                mydownload(songProgressbar,titleinfo);
//            }

            mydownload(songProgressbar,"25");
        }else{
            isDownloading = false;
            stop = true;
            btnDownload.setText("下载");
        }



//        btnDownload.setText("下载");
//        btnDownload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(!isDownloading){
//                    stop = false;
//                    isDownloading = true;
//                    btnDownload.setText("停止");
//                    mydownload(songProgressbar);
//                }else{
//                    isDownloading = false;
//                    stop = true;
//                    btnDownload.setText("下载");
//                }
//            }
//        });

        mSongView = (SongView) findViewById(R.id.songView);
        mSongView.setRate(5);
        mSongView.setLoop(false);
        mSongView.setTangent(false);

        if (mSongView.addPoint()) {
            mSongView.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        super.onResume();
        mydownload(songProgressbar,titleinfo);
    }

    private void mydownload(SongProgressbar songProgressbar, final String titleinfo){

        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while(!stop){
                    if(progress>=100){
                        break;
                    }
                    Message msg = handler.obtainMessage();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(titleinfo!=null){
                        if(progress<=Integer.parseInt(titleinfo)){
                            progress+=1;
//                        Message msg2=handler.obtainMessage();
//                        msg2.what=1;
//                        msg2.arg1=progress;
//                        msg2.sendToTarget();
                        }
                    }


                    msg.what= UPDATE_PROGRESS;
                    msg.arg1 = progress;
                    msg.sendToTarget();
                }
                progress = 0;
            }
        }).start();
    }

    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            boolean flag=true;
            titleinfo=data.getExtras().getString("editText_info");
            itemBean1.number=titleinfo+"%";

//            mydownload(songProgressbar,titleinfo);

            Log.e("!@#$%^&*()!@#$%^&*(", Integer.parseInt(titleinfo)+"");
        }
        if(requestCode==2){
            for(int i=0;i<15;i++){
                date[i]=data.getExtras().getString("editText_info"+i);
                Log.e("date["+i+"]"+"     777777777777777777",date[i]);
            }

        }

    }
}
