package cn.duckr.duckr;

import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakeVideoActivity extends Activity implements View.OnClickListener,SurfaceHolder.Callback{

//    File file = new File(Environment.getExternalStorageDirectory()+File.separator + "mivideo");
    private SurfaceView surface = null;
    private SurfaceHolder surfaceHolder = null;
    private MediaRecorder recorder = null;
    private DisplayMetrics display = null;
    private Button play,stop = null;
    private TextView playtext = null;
    int width = 0;
    int height = 0;

    boolean isStart = true;

    private int s=0,m=0;
    private TextView time,oldtime = null;
    Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);
            if (msg.what == 1){
                if (s>=60) {
                    s=0;
                    m++;
                }
                time.setText(m+":"+s);
                s++;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_video);

        initView();

        initDate();

    }

    private void initDate() {
        display = new DisplayMetrics();
        width = display.widthPixels;
        height = display.heightPixels;
        play.setOnClickListener(this);
        stop.setOnClickListener(this);
        surfaceHolder = surface.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }

    private void initView() {

        play = (Button) findViewById(R.id.button1);
        stop = (Button) findViewById(R.id.button3);
        surface = (SurfaceView) findViewById(R.id.surface);

        time = (TextView) findViewById(R.id.time);
        oldtime = (TextView) findViewById(R.id.oldtime);
        playtext = (TextView) findViewById(R.id.playtext);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        surfaceHolder = surfaceHolder;

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        surfaceHolder = surfaceHolder;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        surface = null;
        surfaceHolder = null;
        recorder = null;
    }

    @Override
    public void onClick(View view) {

        play.setVisibility(View.INVISIBLE);
        stop.setVisibility(View.VISIBLE);
        playtext.setText("点击结束录制");


        switch (view.getId()){
            case R.id.button1:

                File file = new File(Environment.getExternalStorageDirectory()+File.separator + "mivideo");
                //如果文件不存在……exists（存在）
                if (!file.exists()){
                    //mkdir……建立一个新的子目录
                    file.mkdir();
                }

                recorder = new MediaRecorder();
                // 设置从摄像头采集图像
                recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                // 设置从麦克风采集声音(或来自录像机的声音AudioSource.CAMCORDER)
                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                //设置视频文件的输出格式
                //必须在设置声音编码格式，图像编码格式之前设置
                recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                //设置图像编码格式
                recorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
                //设置视频输出分辨率
                recorder.setVideoSize(1024,768);
                //设置声音编码格式
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);

//                //1单声道 2 多声道
//                recorder.setAudioChannels(2);
//
//                //设置录制的音频采样率——频率越高，音质越好
//                recorder.setAudioSamplingRate(12500);

                // 设置录制的音频编码比特率
                recorder.setAudioEncodingBitRate(16);

                // 输出旋转90度，保持竖屏录制
                recorder.setOrientationHint(90);

                //设置录制文件的最大文件大小。(2GB)单位字节
                recorder.setMaxFileSize(2*1024*1024*1024);

                // 每秒 30帧
                recorder.setVideoFrameRate(30);

                //输出的视频码率
                recorder.setVideoEncodingBitRate(3200000);

                recorder.setPreviewDisplay(surfaceHolder.getSurface());

                //存放的文件名
                //获得当前时间赋予文件名，避免重复造成文件丢失
                SimpleDateFormat formate = new SimpleDateFormat("yyMMddHHmmss");
                String format = formate.format(new Date());
                String filename = format+".mp4";

                //生成文件
                File filepath = new File(file,filename);
                //保存文件
                recorder.setOutputFile(filepath.getAbsolutePath());

                try {
                    //准备
                    recorder.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //开始
                recorder.start();
//------------------------------------------------------

                //开启线程，将计数器归零
                new Mythread().start();
                isStart = true;
                m=0;
                s=0;


                break;
            case R.id.button3:

                play.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                playtext.setText("点击再次录制");

                //停止录像
                recorder.stop();
                //释放录像器
                recorder.release();
                //使之放空
                recorder = null;
//--------------------------------------------------------
                //顺带使线程暂停
                isStart =false;
                //将视频总时长显示
                oldtime.setText(m+":"+s);
                m=0;
                s=0;
                //计时器归零
                time.setText(m+":"+s);

                break;

        }

    }

    //开启一个新线程，每隔一秒发送一次信息给主线程
    class Mythread extends Thread{
        @Override
        public void run() {
            while (isStart){
                mHandler.sendEmptyMessage(1);
                SystemClock.sleep(1000);
            }
        }
    }

    public void onclick(View v){
        switch (v.getId()){
            case R.id.video_activity_viewback:
                finish();
                break;
            case R.id.video_save:
//                //存放的文件名
//                //获得当前时间赋予文件名，避免重复造成文件丢失
//                SimpleDateFormat formate = new SimpleDateFormat("yyMMddHHmmss");
//                String format = formate.format(new Date());
//                String filename = format+".mp4";
//
//                //生成文件
//                File filepath = new File(file,filename);
//                //保存文件
//                recorder.setOutputFile(filepath.getAbsolutePath());
                break;
        }

    }

}
