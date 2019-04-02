package jiguang.chat.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import jiguang.chat.R;
import jiguang.chat.application.JGApplication;
import jiguang.chat.controller.MResultController;
import jiguang.chat.utils.DialogCreator;
import jiguang.chat.utils.HttpConnect;
import jiguang.chat.utils.SharePreferenceManager;
import jiguang.chat.view.MResultView;

public class MResultActivity extends FragmentActivity {
     private MResultController mMResultController;
     private MResultView mMResultView;
     private ImageButton mButton;
     private ImageButton mRefreshButton;
     private ImageButton mListButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mresult_veiw);
        mButton = findViewById(R.id.seeu_return1);
        Intent intent = new Intent();
        intent.setAction("HHHH");
        sendBroadcast(intent);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mListButton = findViewById(R.id.mresult_list_1);
        mListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SharePreferenceManager.getCachedHeartChoose()) {
                    Intent intent = new Intent(MResultActivity.this, LikeActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MResultActivity.this,"你还没收藏任何句子",Toast.LENGTH_SHORT).show();
                }
            }
        });
        mRefreshButton = findViewById(R.id.mresult_refresh_1);
        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //第一次不点击，返回0，点击一次，给基准+5
                final Dialog dialog = DialogCreator.createLoadingDialog(MResultActivity.this,"刷新中");
                dialog.show();
                int i = SharePreferenceManager.getCachedSeed()%1000;
                SharePreferenceManager.setCachedSeed(i+5);
                JGApplication.matchSignsBeans.clear();
                HttpConnect.sendRequestSignsWithOkHttpXR(JGApplication.mySignId);
                final Timer t = new Timer();
                t.schedule(
                        new TimerTask() {
                    public void run() {
                        //使用消息队列更新UI
                        Intent intent = new Intent(); // Itent就是我们要发送的内容
                        intent.setAction("HHHH"); // 设置你这个广播的action
                        sendBroadcast(intent); // 发送广播
                        Log.d("真假值", "run: "+JGApplication.randomFlag);
                        dialog.dismiss();
                        t.cancel();
                    }
                }, 500);
                mMResultView.setCurrentItem_mresult(0,true);
            }
        });
        mMResultView = findViewById(R.id.mresult_view);
        mMResultView.initModule();
        mMResultController = new MResultController(MResultActivity.this,mMResultView);
        mMResultView.setOnPageChangeListener_mresult(mMResultController);
    }
    public FragmentManager getbSupportFragmentManger() {
        return getSupportFragmentManager();
    }

}
