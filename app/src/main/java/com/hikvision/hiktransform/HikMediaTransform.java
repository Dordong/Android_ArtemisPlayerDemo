package com.hikvision.hiktransform;


/**
 * Created by chenzhengjun on 2018/1/4.
 */

public class HikMediaTransform {
    static {
        System.loadLibrary("SystemTransform");
        System.loadLibrary("HikMediaTransform");
    }

    /**
     * start transform media file
     *
     * @return 1 success, 0 fail
     */
    public static native int startTransform(String srcFilePath, String dstFilePath, int srcSysFormat, int
            dstSysFormat, int srcVideoFormat, int srcHasAudio, int srcAudioFormat, int
                                                    srcAudioChannel, int srcAudioSamplerate);

    /**
     * start transform media file
     * @param param
     * @return 1 success, 0 fail
     */
    /*public native int startTransform(TransformParam param);*/

}
