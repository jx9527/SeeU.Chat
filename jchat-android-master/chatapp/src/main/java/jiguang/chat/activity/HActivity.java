package jiguang.chat.activity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import jiguang.chat.Interceptor.*;
import com.baidu.mapapi.http.HttpClient;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Console;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.jpush.im.android.api.JMessageClient;
import jiguang.chat.R;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

public class HActivity extends BaseActivity implements View.OnClickListener{
   private  TextView responseText;
   private  TextView mShowString;
   private TextView mUserInfo;
   private static final MediaType JSON = MediaType.parse("application/hal+json;charset=UTF-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hhh);
        Button sendRequest = (Button) findViewById(R.id.send_request);
        Button sendPost1 = (Button)findViewById(R.id.send_post1);
        Button sendPost2 = (Button)findViewById(R.id.send_post2);
        responseText  = (TextView)findViewById(R.id.response_text);
        mShowString = (TextView)findViewById(R.id.showString);
        mUserInfo = (TextView)findViewById(R.id.showUserInfo);
        mUserInfo.setText(""+JMessageClient.getMyInfo().getUserID()+""+JMessageClient.getMyInfo().getUserName());
        sendRequest.setOnClickListener(this);
        sendPost1.setOnClickListener(this);
        sendPost2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.send_request){
            //
        }
        if(v.getId() == R.id.send_post1){
            Toast.makeText(HActivity.this,"OKhttpclient",Toast.LENGTH_SHORT).show();

        }
        if(v.getId() == R.id.send_post2){
            Toast.makeText(HActivity.this,"Xutils",Toast.LENGTH_SHORT).show();

        }
    }




//    private void sendPostWithOkhttp() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //得到一个JSON,使用转义字符
//                try {
//                    Interceptor interceptor = new RedirectInterceptor();
//                    OkHttpClient client = new OkHttpClient().newBuilder()
//                                                .addInterceptor(interceptor)
//                                                .followRedirects(false)
//                                                .followSslRedirects(false)
//                                                .build();
//                    String json = "{\"name\":\"jx\",\"userId\":22221}";
//                    Log.d("json", "" + json);
//                    RequestBody body = RequestBody.create(JSON, json);
//                    Request request = new Request.Builder()
//                            .url("http://132.232.177.233:8080/users")
//                            .post(body)
//                            .build();
//                    Response response = client.newCall(request).execute();
//                    Log.d("返回结果", "返回结果：" + response.header("Location"));
//                    Log.d("返回信息", "run: "+response.body().string());
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
//
//    private void sendPostXutils3(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//               try {
//                   JSONObject js_request = new JSONObject();
//                   js_request.put("name", "jx3333");
//                   js_request.put("userId",1111);
//                   RequestParams params = new RequestParams("http://132.232.177.233:8080/users");
//                   params.setAsJsonContent(true);
//                   params.setBodyContent(js_request.toString());
//                   Log.d("请求的参数", "请求："+js_request.toString());
//                   x.http().post(params, new Callback.CacheCallback<String>() {
//
//                       @Override
//                       public boolean onCache(String result) {
//                           return false;
//                       }
//
//                       @Override
//                       public void onSuccess(String result) {
//                           Log.d("X", "onSuccess: "+result);
//                       }
//
//                       @Override
//                       public void onError(Throwable ex, boolean isOnCallback) {
//                           Log.d("X", "onError: "+ex.getMessage());
//                       }
//
//                       @Override
//                       public void onCancelled(CancelledException cex) {
//                           Log.d("X", "onCancelled: "+cex.getMessage());
//                       }
//
//                       @Override
//                       public void onFinished() {
//                           Log.d("X", "onFinished: "+"完成");
//                       }
//                   });
//               }catch (JSONException e){
//                   e.printStackTrace();
//               }
//
//            }
//        }).start();
//    }
//    class RedirectInterceptor implements Interceptor {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Request request = chain.request();
//            HttpUrl beforeUrl = request.url();
//            Response response = chain.proceed(request);
//            HttpUrl afterUrl = response.request().url();
//            //1.根据url判断是否是重定向
//            if(!beforeUrl.equals(afterUrl)) {
//                //处理两种情况 1、跨协议 2、,原先不是GET请求。
//                if (!beforeUrl.scheme().equals(afterUrl.scheme())||!request.method().equals("GET")) {
//                    //重新请求
//                    Request newRequest = request.newBuilder().url(response.request().url()).build();
//                    response = chain.proceed(newRequest);
//                }
//            }
//            return response;
//        }
//    }
//
//    private void sendPostRetrofit(){
//        Info info = new Info("jx8888",1111);
//        Gson gson = new Gson();
//        String obj = gson.toJson(info);
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://132.232.177.233:8080/").build();
//        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),obj);
//        final PostRoute login = retrofit.create(PostRoute.class);
//        retrofit2.Call<ResponseBody> data = login.getMessage(body);
//        data.enqueue(new retrofit2.Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
//                Log.d("有相应", "onResponse: "+response.body());
//                try {
//                    Log.d("下一句话", "onResponse: --ok--"+response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.d("失败", "onFailure: ");
//            }
//        });
//
//    }


}


