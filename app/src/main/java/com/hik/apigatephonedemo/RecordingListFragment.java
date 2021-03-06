package com.hik.apigatephonedemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hik.apigatephonedemo.bean.RecordingInfo;
import com.hik.apigatephonedemo.utils.TimerUtil;
import com.hik.apigatephonedemo.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 【说明】：
 *
 * @author zhangchuanyi
 * @data 2017/11/27 19:26
 */

public class RecordingListFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener{
    private ListView mListView;
    private Button searchBtn;
    private EditText etQueryType;
    private EditText etRecordPos;
    private EditText etBCascade;
    private EditText etBeginTime;
    private EditText etEndTime;
    private RecordingAdapter recordingAdapter;

    private String queryType = "";
    private String recordPos = "";
    private String bCascade = "";
    private String beginTime = "";
    private String endTime = "";
    private String indexcode = "";

    private List<RecordingInfo> list= new ArrayList<>();
    private final static int SUCCESS = 0;
    private final static int FAILED = 1;
    public static final String TAG = "RecordingListFragment";
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    recordingAdapter.setList((List<RecordingInfo>) msg.obj);
                    recordingAdapter.notifyDataSetChanged();
                    break;
                case FAILED:
                    UIUtil.showToast(getActivity(), "No Record");
                    break;
                default:
                    break;
            }

        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_recording_list, null);
        mListView = (ListView) view.findViewById(R.id.lv_recording);
        searchBtn = (Button) view.findViewById(R.id.searchBtn);
        etQueryType = (EditText) view.findViewById(R.id.et_queryType);
        etRecordPos = (EditText) view.findViewById(R.id.tv_recordPos);
        etBCascade = (EditText) view.findViewById(R.id.tv_bCascade);
        etBeginTime = (EditText) view.findViewById(R.id.beginTime);
        etEndTime = (EditText) view.findViewById(R.id.endTime);
        searchBtn.setOnClickListener(this);
        recordingAdapter=new RecordingAdapter(getActivity(),list);
        mListView.setAdapter(recordingAdapter);
        mListView.setOnItemClickListener(this);
        initData();
        return view;
    }

    private void initData() {
        Bundle args=getArguments();
        indexcode = args.getString(MainActivity.INDEX_CODE);
        Log.e(TAG, "监控点indexcode:" + indexcode);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        getRecordingList();
    }

    private void getRecordingList() {
        if (list != null) {
            list.clear();
            recordingAdapter.setList(list);
            recordingAdapter.notifyDataSetChanged();
        }
        queryType=etQueryType.getText().toString().trim();
        recordPos=etRecordPos.getText().toString().trim();
        bCascade=etBCascade.getText().toString().trim();
        beginTime= TimerUtil.dateToMill(etBeginTime.getText().toString().trim());
        endTime=TimerUtil.dateToMill(etEndTime.getText().toString().trim());
        if(TextUtils.isEmpty(beginTime)||TextUtils.isEmpty(endTime)){
            UIUtil.showToast(getActivity(), "参数不完整");
        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    list = ApiGate.getInstance().loadRecordingList(indexcode, beginTime, endTime, queryType, recordPos, bCascade);
                    if (list != null) {
                        sendMessage(SUCCESS, null, list);
                    } else {
                        sendMessage(FAILED, null, null);
                    }
                }
            }).start();
        }
    }



    public static Fragment newInstance(String cameraID){
        Bundle args = new Bundle();
        args.putString(MainActivity.INDEX_CODE,cameraID);
        Fragment fragment = new RecordingListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void sendMessage(int what, Bundle data, Object object)
    {
        if (mHandler == null)
        {
            Log.e(TAG, "sentMessage,param error,mHandler is null");
            return;
        }
        Message msg = Message.obtain();
        msg.what = what;
        if (data != null)
        {
            msg.setData(data);
        }
        if (object != null){
            msg.obj = object;
        }
        mHandler.sendMessage(msg);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RecordingInfo recordingInfo = list.get(position);
        String playbackUrl = recordingInfo.getPlaybackUrl();
        if (TextUtils.isEmpty(playbackUrl)) {
            Toast.makeText(getActivity(), "获取RTSP失败", Toast.LENGTH_SHORT).show();
            return;
        }

        if (playbackUrl.contains("rtsp")) {
            Toast.makeText(getActivity(), "获取RTSP成功", Toast.LENGTH_SHORT).show();

            if (getActivity() instanceof SearchRecordingActivity) {
                Intent intent = new Intent(getActivity(), AudioVideoActivity.class);
                intent.putExtra("URL", playbackUrl);
                intent.putExtra("showPTZ", false);
                startActivity(intent);

            } else if (getActivity() instanceof AudioVideoActivity) {
                ((AudioVideoActivity) getActivity()).initPlayer(playbackUrl);
            }
        }
    }


}
