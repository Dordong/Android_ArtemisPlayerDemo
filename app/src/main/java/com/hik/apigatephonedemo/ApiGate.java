package com.hik.apigatephonedemo;

import android.text.TextUtils;

import com.hik.apigatephonedemo.bean.ControlUnit;
import com.hik.apigatephonedemo.bean.RecordingInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangkuilin on 2017/9/14.
 */

public class ApiGate {

    private  static ApiGate instance = null;
    private final String ARTEMIS_PATH = "/artemis";

    public static ApiGate getInstance(){
        if (null == instance){
            instance = new ApiGate();
        }

        return instance;
    }


    /**
     * 根据父组织编号查询组织树
     * @return
     */
    public List<ControlUnit> findControlUnitByUnitCode(String unitCode){


        String url = ARTEMIS_PATH + "/api/common/v1/remoteControlUnitRestService/findControlUnitByUnitCode";

        Map<String, String> querys = new HashMap<String, String>();
        querys.put("unitCode", unitCode);

        String strResult = HttpClient.doGet(url, querys);
//        strResult =  "{\n" +
//                "    \"code\": \"200\",\n" +
//                "    \"data\": [\n" +
//                "    {\n" +
//                "    \"createTime\": 1492668561000,\n" +
//                "    \"indexCode\": \"14\",\n" +
//                "    \"name\": \"演示设备\",\n" +
//                "    \"parentIndexCode\": \"0\",\n" +
//                "    \"parentTree\": \"0\",\n" +
//                "    \"unitLevel\": 1,\n" +
//                "    \"unitType\": 1,\n" +
//                "    \"updateTime\": 1506043063826\n" +
//                "    },\n" +
//                "    {\n" +
//                "    \"createTime\": 1492671861167,\n" +
//                "    \"indexCode\": \"71\",\n" +
//                "    \"name\": \"科澜项目\",\n" +
//                "    \"parentIndexCode\": \"0\",\n" +
//                "    \"parentTree\": \"0\",\n" +
//                "    \"unitLevel\": 1,\n" +
//                "    \"unitType\": 1,\n" +
//                "    \"updateTime\": 1506043063826\n" +
//                "    },\n" +
//                "    {\n" +
//                "    \"createTime\": 1379320437000,\n" +
//                "    \"description\": \"系统初始化时创建\",\n" +
//                "    \"indexCode\": \"0\",\n" +
//                "    \"name\": \"主控制中心\",\n" +
//                "    \"parentIndexCode\": \"0\",\n" +
//                "    \"parentTree\": \"0\",\n" +
//                "    \"unitLevel\": 0,\n" +
//                "    \"unitType\": 1,\n" +
//                "    \"updateTime\": 1506043063826\n" +
//                "    }\n" +
//                "    ],\n" +
//                "    \"msg\": \"成功\"\n" +
//                "    }";
        List<ControlUnit> list = JsonUtil.getInstance().parseControlUnit(strResult);
        return list;
    }
    /**
     * 分页获取监控点
     * @return
     */
    public List<ControlUnit> findCameraListByUser(String start,String size,ControlUnit mControlUnit){


        String url = ARTEMIS_PATH + "/api/common/v1/remoteCameraInfoRestService/findCameraInfoPage";

        Map<String, String> querys = new HashMap<String, String>();
        querys.put("start", start);
        querys.put("size", size);

        String strResult = HttpClient.doGet(url, querys);
        //        strResult =  "{\n" +
        //                "    \"code\": \"200\",\n" +
        //                "    \"data\": [\n" +
        //                "    {\n" +
        //                "    \"createTime\": 1492668561000,\n" +
        //                "    \"indexCode\": \"14\",\n" +
        //                "    \"name\": \"演示设备\",\n" +
        //                "    \"parentIndexCode\": \"0\",\n" +
        //                "    \"parentTree\": \"0\",\n" +
        //                "    \"unitLevel\": 1,\n" +
        //                "    \"unitType\": 1,\n" +
        //                "    \"updateTime\": 1506043063826\n" +
        //                "    },\n" +
        //                "    {\n" +
        //                "    \"createTime\": 1492671861167,\n" +
        //                "    \"indexCode\": \"71\",\n" +
        //                "    \"name\": \"科澜项目\",\n" +
        //                "    \"parentIndexCode\": \"0\",\n" +
        //                "    \"parentTree\": \"0\",\n" +
        //                "    \"unitLevel\": 1,\n" +
        //                "    \"unitType\": 1,\n" +
        //                "    \"updateTime\": 1506043063826\n" +
        //                "    },\n" +
        //                "    {\n" +
        //                "    \"createTime\": 1379320437000,\n" +
        //                "    \"description\": \"系统初始化时创建\",\n" +
        //                "    \"indexCode\": \"0\",\n" +
        //                "    \"name\": \"主控制中心\",\n" +
        //                "    \"parentIndexCode\": \"0\",\n" +
        //                "    \"parentTree\": \"0\",\n" +
        //                "    \"unitLevel\": 0,\n" +
        //                "    \"unitType\": 1,\n" +
        //                "    \"updateTime\": 1506043063826\n" +
        //                "    }\n" +
        //                "    ],\n" +
        //                "    \"msg\": \"成功\"\n" +
        //                "    }";
        List<ControlUnit> list = JsonUtil.getInstance().parseCameraInfo(strResult,mControlUnit);
        return list;
    }
    /**
     * 根据组织编号分页获取监控点信息
     * @param strTreeCode: 组织或区域编号
     * @return
     */
    public List<ControlUnit> findCameraInfoPageByTreeNode(String strTreeCode, ControlUnit parentControlUnit){
        if (null == strTreeCode || "".equals(strTreeCode)){
            return null;
        }

        String url = ARTEMIS_PATH + "/api/common/v1/remoteControlUnitRestService/findCameraInfoPageByTreeNode";

        Map<String, String> querys = new HashMap<String, String>();
        querys.put("treeNode", strTreeCode);
        querys.put("start", "0");                               // 第几页开始,从0开始，缺省值为0
        querys.put("size", "1000");                             // 每页大小

        String strResult = HttpClient.doGet(url, querys);
        List<ControlUnit> list = JsonUtil.getInstance().parseCameraInfo(strResult, parentControlUnit);
        return list;
    }

    /**
     * 根据监控点编号获取HLS地址
     * @param strIndexCode
     * @return
     */
    public String getHLSByIndexCode(String strIndexCode){
        if (null == strIndexCode || "".equals(strIndexCode)){
            return null;
        }

        String url = ARTEMIS_PATH + "/api/mss/v1/hls/" + strIndexCode;

        Map<String, String> querys = new HashMap<String, String>();
        querys.put("indexCode", strIndexCode);

        String strResult = HttpClient.doGet(url, querys);
        return JsonUtil.getInstance().parseHls(strResult);
    }

    /**
     * 根据监控点编号获取RTSP流地址
     * @param strIndexCode
     * @return
     */
    public String getRTSPByIndexCode(String strIndexCode){
        if (null == strIndexCode || "".equals(strIndexCode)){
            return null;
        }

        String url = ARTEMIS_PATH + "/api/vms/v1/rtsp/basic/" + strIndexCode;

        Map<String, String> querys = new HashMap<String, String>();
        querys.put("indexCode", strIndexCode);

        String strResult = HttpClient.doGet(url, querys);
        return JsonUtil.getInstance().parseHls(strResult);
    }
    /**
     * 根据监控点编号获取RTSP流地址
     * @param strIndexCode
     * @return
     */
    public String findPreviewUrlByUserAndCamera(String strIndexCode){
        if (null == strIndexCode || "".equals(strIndexCode)){
            return null;
        }

        String url = ARTEMIS_PATH + "/api/vms/v1/rtsp/basic/" + strIndexCode;

        Map<String, String> querys = new HashMap<String, String>();
//        querys.put("indexCode", strIndexCode);

        String strResult = HttpClient.doGet(url, querys);
        return JsonUtil.getInstance().parseHls(strResult);
    }
    /**
     *  开始云台控制
     * @param commend 指令 上下左右等
     * @param indexcode 监控点ID
     * @return
     */
    public String startPTZ(String commend,String indexcode) {
        String url = ARTEMIS_PATH + "/api/PtzQueryService/v1/queryServer";
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("cameraId", indexcode);
        querys.put("command", commend);
        querys.put("action", "1");
        querys.put("speed", "20");
        String strResult = HttpClient.doGet(url, querys);
        return JsonUtil.getInstance().parsePTZBack(strResult);
    }

    /**
     *
     * @param commend 指令 上下左右等
     * @param indexcode 监控点ID
     * @return
     */
    public String stopPTZ(String commend,String indexcode) {
        String url = ARTEMIS_PATH + "/api/PtzQueryService/v1/queryServer";
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("cameraId", indexcode);
        querys.put("command", commend);
        querys.put("action", "0");
        querys.put("speed", "20");
        String strResult = HttpClient.doGet(url, querys);
        return JsonUtil.getInstance().parsePTZBack(strResult);
    }

    /**
     *  获取录像列表
     * @param indexcode 监控点id
     * @param beginTime 开始查询时间
     * @param endTime 结束查询时间

     * @return
     */
    public List<RecordingInfo> loadRecordingList(String indexcode,String beginTime,String endTime,String queryType,String recordPos,String bCascade){
        String url = ARTEMIS_PATH + "/api/RecordQueryService/v1/queryServer";

        Map<String, String> querys = new HashMap<String, String>();
        querys.put("cameraId", indexcode);//00000000001310012847
        querys.put("beginTime", beginTime);//"1512489600"
        querys.put("endTime", endTime);//"1512575999"
        if (!TextUtils.isEmpty(queryType)) {
            querys.put("queryType", queryType);
        }
        if (!TextUtils.isEmpty(recordPos)) {
            querys.put("recordPos", recordPos);
        }
        if (!TextUtils.isEmpty(bCascade)) {
            querys.put("bCascade", bCascade);
        }
        String strResult = HttpClient.doGet(url, querys);
        List<RecordingInfo> list = JsonUtil.getInstance().parseRecordingInfo(strResult);
        return list;
    }

    public String getPlayBackUrl(String indexcode,String beginTime,String endTime,String bCascade) {
        String url = ARTEMIS_PATH + "/api/PlaybackUrlQueryService/v1/queryServer";
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("cameraId", indexcode);
        querys.put("beginTime", beginTime);
        querys.put("endTime", endTime);
        if (!TextUtils.isEmpty(bCascade)) {
            querys.put("bCascade", bCascade);
        }
        String strResult = HttpClient.doGet(url, querys);
        String playBackUrl = JsonUtil.getInstance().parsePlayBackUrl(strResult);
        return playBackUrl;
    }
}
