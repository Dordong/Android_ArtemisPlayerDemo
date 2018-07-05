package com.hik.apigatephonedemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class CameraPlayDemoActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnGetUrl, btnPlay;
    private EditText etIndexCode;
    private TextView tvResponse;
    private String strResult;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            if (what == 0x1) {
                tvResponse.setText(strResult);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_play_demo);
        btnGetUrl = (Button) findViewById(R.id.btnGetUrl);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        etIndexCode = (EditText) findViewById(R.id.etIndexCode);
        tvResponse = (TextView) findViewById(R.id.tvResponse);
        btnGetUrl.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnGetUrl) {
            if (TextUtils.isEmpty(etIndexCode.getText().toString().trim())) {
                Toast.makeText(CameraPlayDemoActivity.this, "请输入indexCode", Toast.LENGTH_SHORT).show();
            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        /**
                         * 获取预览URL有两种情况，
                         * 一根据监控点编号获取视频预览url
                         * 请求路径：MainActivity.ARTEMIS + "/api/video/v1/preview
                         */
                        String url = MainActivity.ARTEMIS + "/api/video/v1/preview";
                        Map<String, String> queries = new HashMap<String, String>();
                        //cameraIndexCode,监控点编号
                        queries.put("cameraIndexCode", etIndexCode.getText().toString().trim());
                        //subStream,码流类型 默认为主码流 说明：0，主码流 1，表示子码流
                        queries.put("subStream", "0");
                        //protocol,协议类型（目前支持rtsp和hls协议,默认为rtsp协议) 说明：0，rtsp协议 1，hls协议
                        queries.put("subStream", "0");
                        strResult = HttpClient.doGet(url, queries);

                    /*
                    //二根据监控点编号获取RTSP流地址(向下兼容用)
                    //获得播放URL，如rtsp://xxxxx
                    String url = MainActivity.ARTEMIS + "/api/vms/v1/rtsp/basic/" + etIndexCode.getText().toString().trim();
                    Map<String, String> queries = new HashMap<String, String>();
                    strResult = HttpClient.doGet(url, queries);
                    */
                        Message message = new Message();
                        message.what = 0x1;
                        message.obj = strResult;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        } else if (v.getId() == R.id.btnPlay) {
            //预览的url,根据上一步返回数据替换为你的预览URL
            String strUrl = "你的预览URL";
            if (!TextUtils.isEmpty(strUrl)) {
                //视频预览功能暂时用不到参数indexCode,故可先不填写
                handleGetRtsp(strUrl,"");
            }
        }
    }

    /**
     *
     * @param strRtsp
     * @param indexcode
     */
    private void handleGetRtsp(String strRtsp,String indexcode){
        if (null == strRtsp || "".equals(strRtsp)){
            Toast.makeText(CameraPlayDemoActivity.this, "RTSP错误", Toast.LENGTH_SHORT).show();
            return;
        }
        if (strRtsp.contains("rtsp")){
            Toast.makeText(CameraPlayDemoActivity.this, "RTSP正确", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CameraPlayDemoActivity.this, AudioVideoActivity.class);
            intent.putExtra("URL", strRtsp);
            intent.putExtra(MainActivity.INDEX_CODE, indexcode);
            intent.putExtra("showPTZ", true);
            startActivity(intent);
        }else {
            Toast.makeText(CameraPlayDemoActivity.this, strRtsp, Toast.LENGTH_SHORT).show();
        }
    }
}
