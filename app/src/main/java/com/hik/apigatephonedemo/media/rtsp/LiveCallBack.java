package com.hik.apigatephonedemo.media.rtsp;

import android.os.Bundle;

/**
 * 预览回调接口
 * @author huangweifeng
 * @Data 2013-10-21
 */
public interface LiveCallBack {
    /**
     * 播放引擎消息回调接口
     * 
     * @param message 消息
     * @since V1.0
     */
    public void onMessageCallback(int message);

    public void onMessagePTZCallBack(int caseId, Bundle data, Object object);
}
