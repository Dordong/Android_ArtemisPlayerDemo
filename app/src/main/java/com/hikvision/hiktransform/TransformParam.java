package com.hikvision.hiktransform;

/**
 * Created by chenzhengjun on 2018/1/4.
 */

public class TransformParam {
    /**
     * 源文件路径
     */
    public String srcFilePath;
    /**
     * 转换后保存的文件路径
     */
    public String dstFilePath;
    /**
     * 错误日志输出文件路径
     */
    public String logFilePath;
    /**
     * 最终转换到的目标流类型（0-raw, 1-hik, 2-ps, 3-ts, 4-rtp, 5-mp4, 6-asf, 7-avi, 8-GB_ps, 9-hls_ts,
     * 10-flv, 11-mp4_front, 12-mp4_frag, 13-rtmp
     */
    public int dstSysFormat;
    /**
     * 源文件流类型（0-raw, 1-hik, 2-ps, 3-ts, 4-rtp, 5-mp4, 6-asf, 7-avi, 8-GB_ps, 9-hls_ts, 10-flv,
     * 11-mp4_front, 13-rtmp）
     */
    public int srcSysFormat;
    /**
     * 源文件视频编码格式（0x0001-hik264, 0x0002-mpeg2, 0x0003-mpeg4, 0x0004-mjpeg, 0x0005-h265,
     * 0x0006-svac, 0x0100-h264）
     */
    public int srcVideoFormat;
    /**
     * 是否存在声音（0无，1有）
     */
    public int hasAudio;
    /**
     * 声音编码格式（0x1000-adpcm, 0x2000-mpega, 0x2001-aac, 0x7110-g711u, 0x7111-g711a, 0x7001-pcm,
     * 0x7221-g7221, 0x7231-g7231, 0x7261-g726a, 0x7260-g726_32, 0x7262-g726_16, 0x7290-g729）
     */
    public int audioFormat;
    /**
     * 声音通道数（影响转码后音频是否正常）
     */
    public int audioChannel;
    /**
     * 声音采样率（目测没有啥影响，可瞎传）
     */
    public int audioSamplerate;

    public String getSrcFilePath() {
        return srcFilePath;
    }

    public void setSrcFilePath(String srcFilePath) {
        this.srcFilePath = srcFilePath;
    }

    public String getDstFilePath() {
        return dstFilePath;
    }

    public void setDstFilePath(String dstFilePath) {
        this.dstFilePath = dstFilePath;
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public int getDstSysFormat() {
        return dstSysFormat;
    }

    public void setDstSysFormat(int dstSysFormat) {
        this.dstSysFormat = dstSysFormat;
    }

    public int getSrcSysFormat() {
        return srcSysFormat;
    }

    public void setSrcSysFormat(int srcSysFormat) {
        this.srcSysFormat = srcSysFormat;
    }

    public int getSrcVideoFormat() {
        return srcVideoFormat;
    }

    public void setSrcVideoFormat(int srcVideoFormat) {
        this.srcVideoFormat = srcVideoFormat;
    }

    public int getHasAudio() {
        return hasAudio;
    }

    public void setHasAudio(int hasAudio) {
        this.hasAudio = hasAudio;
    }

    public int getAudioFormat() {
        return audioFormat;
    }

    public void setAudioFormat(int audioFormat) {
        this.audioFormat = audioFormat;
    }

    public int getAudioChannel() {
        return audioChannel;
    }

    public void setAudioChannel(int audioChannel) {
        this.audioChannel = audioChannel;
    }

    public int getAudioSamplerate() {
        return audioSamplerate;
    }

    public void setAudioSamplerate(int audioSamplerate) {
        this.audioSamplerate = audioSamplerate;
    }
}
