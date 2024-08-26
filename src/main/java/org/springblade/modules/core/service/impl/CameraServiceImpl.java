package org.springblade.modules.core.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.core.dto.CameraDto;
import org.springblade.modules.core.entity.*;
import org.springblade.modules.core.service.CameraService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CameraServiceImpl implements CameraService {

    //萤石appKey
    private static final String appKey = "3d40228fb7be41d48cd53086caf92dab";
    //萤石Secret
    private static final String appSecret = "ec5b5f9fc640fc4eb918e21af0c7d213";
    //萤石获取accessToken url
    private static final String getTokenUrl = "https://open.ys7.com/api/lapp/token/get";
    //萤石获取摄像头流  url
    private static final String getLiveUrl = "https://open.ys7.com/api/lapp/v2/live/address/get";
    //萤石分页查询设备列表
    private static final String getDeviceList = "https://open.ys7.com/api/lapp/device/list";
    //萤石分页查询设备通道列表
    private static final String getDeviceCameraList = "https://open.ys7.com/api/lapp/camera/list";
    //萤石分页查询指定服务区设备通道列表
    private static final String getAreaDeviceCameraList = "https://open.ys7.com/api/lapp/device/camera/list";

    @Override
    public StringBuilder getCameraInfo(CameraDto cameraDto) {
        String token = getHkTokenApiInfo();
        String url = getLiveUrl(cameraDto, token);
        //拼接视频流
        StringBuilder cameraUrl = new StringBuilder();
        cameraUrl.append("https://open.ys7.com/ezopen/h5/iframe?url=")
                .append(url)
                .append("&autoplay=1&accessToken=")
                .append(token);

        return cameraUrl;
    }

    @Override
    public R getDeviceList(Query query) {
		if(0 > query.getCurrent()){
			return R.fail("Current页码小于0");
		}

		if(10 > query.getSize() || 50 < query.getSize()){
			return R.fail("Size页码小于10或大于50");
		}

        String token = getHkTokenApiInfo();
        List<Device> hkDeviceList = getHkDeviceList(query, token);
        return R.data(hkDeviceList);
    }

    @Override
    public R getDeviceCameraList(Query query) {
        if(0 > query.getCurrent()){
            return R.fail("pageNum页码小于0");
        }

        if(10 > query.getSize() || 50 < query.getSize()){
            return R.fail("pageSize页码小于10或大于50");
        }

        String token = getHkTokenApiInfo();
        List<DeviceCamera> hkDeviceList = getHkDeviceCameraList(query, token);
        return R.data(hkDeviceList);
    }

	@Override
	public R getAreaDeviceCameraList(CameraDto cameraDto) {
		if(0 > cameraDto.getCurrent()){
			return R.fail("Current页码小于0");
		}

		if(10 > cameraDto.getSize() || 50 < cameraDto.getSize()){
			return R.fail("Size页码小于10或大于50");
		}

		String token = getHkTokenApiInfo();
		List<AssignDeviceCamera> hkDeviceList = getAreaDeviceCameraList(cameraDto, token);
		return R.data(hkDeviceList);
	}

	//获取设备列表
    private List<Device> getHkDeviceList(Query query, String token) {
        List<Device> devices = new ArrayList<>();
        try {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS) // 设置连接超时时间
                    .readTimeout(30, TimeUnit.SECONDS) // 设置读超时时间
                    .writeTimeout(30, TimeUnit.SECONDS) // 设置写超时时间
                    .retryOnConnectionFailure(true) // 是否自动重连
                    .build();

            FormBody body = new FormBody.Builder()
                    .add("accessToken", token)
                    .add("pageStart", query.getCurrent().toString())
                    .add("pageSize", query.getSize().toString())
                    .build();

            // 创建Request对象
            Request request = new Request.Builder()
                    .url(getDeviceList)
                    .post(body)
                    .build();

            // 得到Response对象
            Response response1 = client.newCall(request).execute();

            ObjectMapper mapper = new ObjectMapper();

            if(response1.isSuccessful()){
                String string = response1.body().string();
                JSONArray array = new JSONArray(string);
                for(int i = 0; i<array.size();i++) {//循环打印
                    JSONObject obj = array.getJSONObject(i);
                    String data = obj.getString("data");
                    if (StringUtils.isNotEmpty(data)){
                        devices = mapper.readValue(data, new TypeReference<List<Device>>(){});
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return devices;
    }

    //获取设备列表
    private List<DeviceCamera> getHkDeviceCameraList(Query query, String token) {
        List<DeviceCamera> deviceCamera = new ArrayList<>();
        try {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS) // 设置连接超时时间
                    .readTimeout(30, TimeUnit.SECONDS) // 设置读超时时间
                    .writeTimeout(30, TimeUnit.SECONDS) // 设置写超时时间
                    .retryOnConnectionFailure(true) // 是否自动重连
                    .build();

            FormBody body = new FormBody.Builder()
                    .add("accessToken", token)
                    .add("pageStart", query.getCurrent().toString())
                    .add("pageSize", query.getSize().toString())
                    .build();

            // 创建Request对象
            Request request = new Request.Builder()
                    .url(getDeviceCameraList)
                    .post(body)
                    .build();

            // 得到Response对象
            Response response1 = client.newCall(request).execute();

            ObjectMapper mapper = new ObjectMapper();

            if(response1.isSuccessful()){
                String string = response1.body().string();
                JSONArray array = new JSONArray(string);
                for(int i = 0; i<array.size();i++) {//循环打印
                    JSONObject obj = array.getJSONObject(i);
                    String data = obj.getString("data");
                    if (StringUtils.isNotEmpty(data)){
                        deviceCamera = mapper.readValue(data, new TypeReference<List<DeviceCamera>>(){});
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deviceCamera.stream().sorted(Comparator.comparingInt(DeviceCamera::getChannelNo)).collect(Collectors.toList());
    }

    private String getLiveUrl(CameraDto cameraDto, String token){
        String url = "";
        try {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS) // 设置连接超时时间
                    .readTimeout(30, TimeUnit.SECONDS) // 设置读超时时间
                    .writeTimeout(30, TimeUnit.SECONDS) // 设置写超时时间
                    .retryOnConnectionFailure(true) // 是否自动重连
                    .build();

            FormBody body = new FormBody.Builder()
                    .add("accessToken", token)
                    .add("deviceSerial", cameraDto.getDeviceSerial())
                    .add("channelNo",cameraDto.getChannelNo())
                    .build();

            // 创建Request对象
            Request request = new Request.Builder()
                    .url(getLiveUrl)
                    .post(body)
                    .build();

            // 得到Response对象
            Response response = client.newCall(request).execute();

            if(response.isSuccessful()){
                String string = response.body().string();
                JSONArray array = new JSONArray(string);
                for(int i = 0; i<array.size();i++) {//循环打印
                    JSONObject obj = array.getJSONObject(i);
                    String data = obj.getString("data");
                    if (StringUtils.isNotEmpty(data)){
                        Camera camera = JSONObject.parseObject(data, Camera.class);
                        url = camera.getUrl();
                    }
                }
            }
            // 清除并关闭线程池
            client.dispatcher().executorService().shutdown();
            // 清除并关闭连接池
            client.connectionPool().evictAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

    private String getHkTokenApiInfo(){
        String token = "";
        try {
            //获取萤石摄像头通道流
            // 创建OkHttpClient对象
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS) // 设置连接超时时间
                    .readTimeout(30, TimeUnit.SECONDS) // 设置读超时时间
                    .writeTimeout(30, TimeUnit.SECONDS) // 设置写超时时间
                    .retryOnConnectionFailure(true) // 是否自动重连
                    .build();

            FormBody body1 = new FormBody.Builder()
                    .add("appKey", appKey)
                    .add("appSecret", appSecret)
                    .build();

            // 创建Request对象
            Request request1 = new Request.Builder()
                    .url(getTokenUrl)
                    .post(body1)
                    .build();

            // 得到Response对象
            Response response1 = client.newCall(request1).execute();

            if(response1.isSuccessful()){
                String string = response1.body().string();
                JSONArray array = new JSONArray(string);
                for(int i = 0; i<array.size();i++) {//循环打印
                    JSONObject obj = array.getJSONObject(i);
                    String data = obj.getString("data");
                    if (StringUtils.isNotEmpty(data)){
                        Cameras camera = JSONObject.parseObject(data, Cameras.class);
                        token = camera.getAccessToken();
                    }
                }
            }
            // 清除并关闭线程池
            client.dispatcher().executorService().shutdown();
            // 清除并关闭连接池
            client.connectionPool().evictAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }

	//获取设备列表
	private List<AssignDeviceCamera> getAreaDeviceCameraList(CameraDto dto, String token) {
		List<AssignDeviceCamera> deviceCamera = new ArrayList<>();
		try {
			OkHttpClient client = new OkHttpClient.Builder()
				.connectTimeout(30, TimeUnit.SECONDS) // 设置连接超时时间
				.readTimeout(30, TimeUnit.SECONDS) // 设置读超时时间
				.writeTimeout(30, TimeUnit.SECONDS) // 设置写超时时间
				.retryOnConnectionFailure(true) // 是否自动重连
				.build();

			FormBody body = new FormBody.Builder()
				.add("accessToken", token)
				.add("deviceSerial", dto.getDeviceSerial())
				.build();

			// 创建Request对象
			Request request = new Request.Builder()
				.url(getAreaDeviceCameraList)
				.post(body)
				.build();

			// 得到Response对象
			Response response1 = client.newCall(request).execute();

			ObjectMapper mapper = new ObjectMapper();

			if(response1.isSuccessful()){
				String string = response1.body().string();
				JSONArray array = new JSONArray(string);
				for(int i = 0; i<array.size();i++) {//循环打印
					JSONObject obj = array.getJSONObject(i);
					String data = obj.getString("data");
					if (StringUtils.isNotEmpty(data)){
						deviceCamera = mapper.readValue(data, new TypeReference<List<AssignDeviceCamera>>(){});
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return deviceCamera.stream().sorted(Comparator.comparingInt(AssignDeviceCamera::getChannelNo)).collect(Collectors.toList());
	}
}
