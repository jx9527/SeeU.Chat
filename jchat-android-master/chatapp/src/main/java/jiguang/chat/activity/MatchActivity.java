package jiguang.chat.activity;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

import jiguang.chat.R;

import jiguang.chat.controller.MatchController;

import jiguang.chat.utils.DialogCreator;
import jiguang.chat.utils.SharePreferenceManager;
import jiguang.chat.view.MatchView;


public class MatchActivity extends FragmentActivity {

    private MatchController mMatchController;
    private MatchView mMatchView;
    private ImageButton  mImageButton;
    private ImageButton  mRefrensh;
    private ImageButton  mReturn;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_match);
        mImageButton = findViewById(R.id.list_1);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SharePreferenceManager.getCachedHeartChoose()) {
                    Intent intent = new Intent(MatchActivity.this, LikeActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MatchActivity.this,"你还没收藏任何句子",Toast.LENGTH_SHORT).show();
                }
            }
        });
        mRefrensh =  findViewById(R.id.refresh_1);
        mMatchView = findViewById(R.id.match_view);
        mMatchView.initModule();
        mMatchController = new MatchController(MatchActivity.this,mMatchView);
        mMatchView.setOnPageChangeListener_match(mMatchController);

        mReturn = findViewById(R.id.seeu_return);
        mReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRefrensh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //第一次不点击，返回0，点击一次，给基准+5
                final Dialog dialog = DialogCreator.createLoadingDialog(MatchActivity.this,"刷新中");
                dialog.show();
                int i = SharePreferenceManager.getCachedSeed()%1000;
                SharePreferenceManager.setCachedSeed(i+5);
                final Timer t = new Timer();
                t.schedule(new TimerTask() {
                    public void run() {
                        //使用消息队列更新UI
                        Intent intent = new Intent(); // Itent就是我们要发送的内容
                        intent.setAction("com.gasFragment"); // 设置你这个广播的action
                        sendBroadcast(intent); // 发送广播
                        dialog.dismiss();
                        t.cancel();
                    }
                }, 500);
                mMatchView.setCurrentItem_match(0,true);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public FragmentManager getaSupportFragmentManger() {
        return getSupportFragmentManager();
    }


    
}
