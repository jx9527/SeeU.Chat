package jiguang.chat.utils;
import android.util.Log;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.jpush.im.android.api.JMessageClient;
import jiguang.chat.activity.Info;
import jiguang.chat.activity.Info2;
import jiguang.chat.activity.PostRoute;
import jiguang.chat.activity.PostRoute2;
import jiguang.chat.activity.postChooseRoute;
import jiguang.chat.application.JGApplication;
import jiguang.chat.database.CatchUserEntry;
import jiguang.chat.database.SignEntry;
import jiguang.chat.utils.gson.Signs;
import jiguang.chat.utils.gson.matchSignsBean;
import jiguang.chat.utils.gson.matchUsers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import uk.co.senab.photoview.log.LoggerDefault;

/**
 *
 */
public class HttpConnect {
    //post登录的用户信息
    public static void sendPostRetrofit(String name, long userId){
        Log.d("post 用户信息:", "sendPostRetrofit: "+name+"ID"+userId);
        Info info = new Info(name,userId);
        Gson gson = new Gson();
        String obj = gson.toJson(info);
        Log.d("输出的用户信息json",""+obj);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://106.13.58.3:8080/").build();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),obj);
        final PostRoute login = retrofit.create(PostRoute.class);
        retrofit2.Call<ResponseBody> data = login.getMessage(body);
        data.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                Log.d("post用户信息，有相应", "onResponse: "+response.body());
                try {
                    Log.d("post 用户信息的反馈", "onResponse: --ok--"+response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("post失败", "onFailure: ");
            }
        });
    }

     //2 句子的获取
    public static void sendRequestSignsWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("获取sign执行了", "run: ");
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://106.13.58.3:8080/signs?size=1000")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    jiexi(responseData);
                }catch (IOException e ){
                    e.printStackTrace();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void jiexi(String responseData){
        try {
            JSONObject jsonObject = new JSONObject(responseData);
            JSONObject jsonObject2 = jsonObject.getJSONObject("_embedded");
            JSONArray jsonArray = jsonObject2.getJSONArray("signs");
            if(jsonArray.length()>0) {
                Log.d("jsonlength", "jiexi: "+jsonArray.length());
                for(int i=0;i<jsonArray.length();i++) {
                    JSONObject hhh = jsonArray.getJSONObject(i);
                    Signs signs = new Signs(hhh.getInt("sentenceId"), hhh.getString("sentence"));
                    Log.d("Id", "jiexi: "+hhh.getInt("sentenceId")+"sss"+ hhh.getString("sentence"));
                    SignEntry signEntry = new SignEntry(signs.getSentenceId(),signs.getSentence());
                    signEntry.save();
                }

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    //相似句子的获取
    public static void sendRequestSignsWithOkHttpXR(final int signId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("matchSign执行了", "run: "+ signId);
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(" http://106.13.58.3:8080/similarSigns?signId="+signId)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    if (responseData.equals("[]")){
                        //随机拉取句子 加个flag
                         Log.d("得到的是空值", "run:");
                         JGApplication.randomFlag = true;
                    }else{
                        Log.d("相似句子的获取：", "run: "+responseData);
                        jiexiXR(responseData);
                    }
                }catch (IOException e ){
                    e.printStackTrace();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void jiexiXR(String responseData){

            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(responseData).getAsJsonArray();
            Gson gson = new Gson();
            //遍历JsonArray
            for(JsonElement signs : jsonArray){
                matchSignsBean matchSignsBean = gson.fromJson(signs,matchSignsBean.class);
                JGApplication.matchSignsBeans.add(matchSignsBean);
            }
            if(JGApplication.matchSignsBeans.size()<5) JGApplication.randomFlag = true;
            else{
                JGApplication.randomFlag = false;
                Log.d("得到相似的句子", "jiexiXR: "+JGApplication.matchSignsBeans.get(0).getSentenceId());
            }

   }

    // 用户获取
    public static void sendRequestUsersWithOkHttp(){
        Log.d("默认方法执行了", "sendRequestUsersWithOkHttp: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://106.13.58.3:8080/users?size=1000")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d("得到的用户数据。。。。。", "run: "+responseData);
                    jiexi2(responseData);
                }catch (IOException e ){
                    e.printStackTrace();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void jiexi2(String responseData){
        try {
            JSONObject jsonObject = new JSONObject(responseData);
            JSONObject jsonObject2 = jsonObject.getJSONObject("_embedded");
            JSONArray jsonArray = jsonObject2.getJSONArray("users");
            if(jsonArray.length()>0) {
                if(SharePreferenceManager.getCachedLaunch() == 0){
                   for(int i=0;i<jsonArray.length();i++){
                            Log.d("首次拉取用户", "11111");
                           JSONObject hhh = jsonArray.getJSONObject(i);
                           long userId = hhh.getLong("userId");
                           String name = hhh.getString("name");
                           CatchUserEntry mCatchUserEntry = new CatchUserEntry(name,userId,0);
                           mCatchUserEntry.save();
                          }
                    SharePreferenceManager.setCachedLaunch(1);
                }else {
                    Log.d("不是首次拉取用户", "11111");
                    List<CatchUserEntry> catchUserEntries = new Select().from(CatchUserEntry.class).execute();
                    Log.d("拉取数据的长度", ""+jsonArray.length());
                    Log.d("当前存取用户的长度", ""+catchUserEntries.size());
                    if(jsonArray.length()> catchUserEntries.size()){
                        for(int i=catchUserEntries.size()-1;i<jsonArray.length();i++){
                            JSONObject hhh = jsonArray.getJSONObject(i);
                            long userId = hhh.getLong("userId");
                            String name = hhh.getString("name");
                            CatchUserEntry mCatchUserEntry = new CatchUserEntry(name,userId,0);
                            mCatchUserEntry.save();
                        }
                    }
                }
              }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public static void sendRequestMatchUsersWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://132.232.177.233:8080/matchUsers?id="+JMessageClient.getMyInfo().getUserID())
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    JSONArray jsonArray = new JSONArray(responseData);
                    Log.d("名字", "run: "+jsonArray.length());
                    if(jsonArray.length()<6) sendRequestUsersWithOkHttp();
                    else {
                        Log.d("默认不会出现这种情况，哈哈哈", "run: ");
                    }

                }catch (IOException e ){
                    e.printStackTrace();
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

/*

    //match到和自己相似的用户
    public static void sendPostRetrofitRX(int signid) {
        Map map = new HashMap();
        map.put("userid", JMessageClient.getMyInfo().getUserID());
        map.put("signid", signid);
        String params = new Gson().toJson(map);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://106.13.58.3:8080/match1/").build();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), params);
        final PostRoute2 matchUsers = retrofit.create(PostRoute2.class);
        retrofit2.Call<ResponseBody> data = matchUsers.getMessage(body);
        data.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    if (response.body().string().equals("null")) sendRequestUsersWithOkHttp();
                    Log.d("发送自己喜欢的句子得到的用户信息：", "onResponse: " + response.body().string());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("match用户失败的原因", "onFailure: " + call);
            }
        });
    }
*/


    public static void postLikeSign(int signid){
        Map map = new HashMap();
        map.put("userid",JMessageClient.getMyInfo().getUserID());
        map.put("signid",signid);
        map.put("type","like");
        String params = new Gson().toJson(map);
        Log.d("选择句子后发送的参数", "postLikeSign: "+params);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://106.13.58.3:8080/user2sign/").build();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),params);
        final postChooseRoute choose = retrofit.create(postChooseRoute.class);
        retrofit2.Call<ResponseBody> data = choose.getMessage(body);
        data.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                     try{
                         Log.d("OKOKOKO", "onResponse: "+response.body().string());
                     }catch (Exception e){
                         e.printStackTrace();
                     }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("发送CHOOSE的句子失败", "onFailure: "+call);
            }
        });
    }
}



