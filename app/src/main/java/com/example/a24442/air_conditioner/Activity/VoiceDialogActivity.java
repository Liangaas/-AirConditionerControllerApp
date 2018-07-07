package com.example.a24442.air_conditioner.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.idst.nls.realtime.NlsClient;
import com.alibaba.idst.nls.realtime.NlsListener;
import com.alibaba.idst.nls.realtime.StageListener;
import com.alibaba.idst.nls.realtime.internal.protocol.NlsRequest;
import com.alibaba.idst.nls.realtime.internal.protocol.NlsResponse;
import com.example.a24442.air_conditioner.R;

import java.util.HashMap;

public class VoiceDialogActivity extends AppCompatActivity {

    private Button start;
    private Button over;

    private TextView voice_dialog_text;

    private Context context;
    private NlsClient mNlsClient;
    private NlsRequest mNlsRequest;
    private HashMap<Integer,String> resultMap = new HashMap<Integer, String>();
    private int sentenceId = 0;
    private boolean isRecognizing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_dialog);

        context = VoiceDialogActivity.this;

        start = (Button) findViewById(R.id.start);
        over = (Button) findViewById(R.id.over);
        voice_dialog_text = (TextView) findViewById(R.id.voice_dialog_text);

        String appkey = "nls-service-shurufa16khz"; //请设置文档中Appkey

        mNlsRequest = new NlsRequest();
        // 使用热词功能，指定热词词表id即可

        mNlsRequest.setAppkey(appkey);    //appkey请从 "快速开始" 帮助页面的appkey列表中获取
        mNlsRequest.setResponseMode("normal");//流式为streaming,非流式为normal

        NlsClient.configure(getApplicationContext()); //全局配置
        mNlsClient = NlsClient.newInstance(this, mRecognizeListener, mStageListener,mNlsRequest);

        mNlsClient.setMaxRecordTime(60000);  //设置最长语音
        mNlsClient.setMaxStallTime(1000);    //设置最短语音
        mNlsClient.setMinRecordTime(500);    //设置最大录音中断时间
        mNlsClient.setRecordAutoStop(false);  //设置VAD
        mNlsClient.setMinVoiceValueInterval(200); //设置音量回调时长

        initStartRecognizing();
        initStopRecognizing();

    }

    private void initStartRecognizing(){
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRecognizing = true;
                mNlsRequest.authorize("LTAILzr2xVsOJrZZ", "EppiCZG0mYavgldp1TYbgStwETLBOF"); //请替换为用户申请到的数加认证key和密钥
                mNlsClient.start();
                start.setText("录音中");
            }
        });

    }

    private void initStopRecognizing(){
        over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRecognizing = false;
                mNlsClient.stop();
                start.setText("开始录音");
            }
        });

    }

    private NlsListener mRecognizeListener = new NlsListener() {

        @Override
        public void onRecognizingResult(int status, NlsResponse result) {
            switch (status) {
                case NlsClient.ErrorCode.SUCCESS:

                    if (result!=null){
                        if(result.getResult()!=null) {
                            //获取句子id对应结果。
                            if (sentenceId != result.getSentenceId()) {
                                sentenceId = result.getSentenceId();
                            }
                            resultMap.put(sentenceId,result.getText());

                            Log.i("asr", "[demo] callback onRecognizResult :" + result.getResult().getText());
                            voice_dialog_text.setText(resultMap.get(sentenceId));

                        }
                    }else {
                        Log.i("asr", "[demo] callback onRecognizResult finish!" );
//                        theResult.setText("Recognize finish!");
//
                    }
                    break;
                case NlsClient.ErrorCode.RECOGNIZE_ERROR:
                    Toast.makeText(context, "recognizer error", Toast.LENGTH_LONG).show();
                    break;
                case NlsClient.ErrorCode.RECORDING_ERROR:
                    Toast.makeText(context,"recording error", Toast.LENGTH_LONG).show();
                    break;
                case NlsClient.ErrorCode.NOTHING:
                    Toast.makeText(context,"nothing", Toast.LENGTH_LONG).show();
                    break;
            }
            isRecognizing = false;
        }


    } ;




    private StageListener mStageListener = new StageListener() {
        @Override
        public void onStartRecognizing(NlsClient recognizer) {
            super.onStartRecognizing(recognizer);    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        public void onStopRecognizing(NlsClient recognizer) {
            super.onStopRecognizing(recognizer);    //To change body of overridden methods use File | Settings | File Templates.
            mNlsClient.stop();

        }

        @Override
        public void onStartRecording(NlsClient recognizer) {
            super.onStartRecording(recognizer);    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        public void onStopRecording(NlsClient recognizer) {
            super.onStopRecording(recognizer);    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        public void onVoiceVolume(int volume) {
            super.onVoiceVolume(volume);
        }

    };

}
