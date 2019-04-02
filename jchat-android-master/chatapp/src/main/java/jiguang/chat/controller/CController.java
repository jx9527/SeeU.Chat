package jiguang.chat.controller;


import android.content.Intent;
import android.view.View;

import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.eventbus.EventBus;
import jiguang.chat.R;
import jiguang.chat.activity.ChatActivity;
import jiguang.chat.activity.MResultActivity;
import jiguang.chat.activity.fragment.BFragment;
import jiguang.chat.activity.fragment.CFragment;
import jiguang.chat.activity.fragment_twice.BBFragment;
import jiguang.chat.activity.fragment_twice.CCFragment;
import jiguang.chat.application.JGApplication;
import jiguang.chat.database.SignEntry;
import jiguang.chat.entity.Event;
import jiguang.chat.entity.EventType;
import jiguang.chat.utils.HttpConnect;
import jiguang.chat.utils.SharePreferenceManager;
import jiguang.chat.view.BBView;
import jiguang.chat.view.BView;
import jiguang.chat.view.CCView;
import jiguang.chat.view.CView;

public class CController implements View.OnClickListener {
    private CFragment mContext;
    private CView mCView;
    private String mString;

    public CController(CFragment context,CView cView){
        this.mContext = context;
        this.mCView = cView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_c:
                Intent intent  = new Intent(mContext.getContext(),MResultActivity.class);
                JGApplication.mySign = mCView.getText();
                List<SignEntry> signEntries = new Select().from(SignEntry.class).where("sign=?",mCView.getText()).execute();
                JGApplication.mySignId = signEntries.get(0).getSignId();
                HttpConnect.sendRequestSignsWithOkHttpXR(JGApplication.mySignId);
                JGApplication.pageChoose = 'C';
                HttpConnect.postLikeSign(JGApplication.c);
                mContext.startActivity(intent);
                break;
            case R.id.c_heart:
                mCView.setImgColorAndOther();
                break;
        }
    }
}

