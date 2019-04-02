package jiguang.chat.controller;

import android.content.Intent;
import android.view.View;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.eventbus.EventBus;
import jiguang.chat.R;
import jiguang.chat.activity.ChatActivity;
import jiguang.chat.activity.fragment_twice.BBFragment;
import jiguang.chat.activity.fragment_twice.CCFragment;
import jiguang.chat.application.JGApplication;
import jiguang.chat.entity.Event;
import jiguang.chat.entity.EventType;
import jiguang.chat.view.BBView;
import jiguang.chat.view.CCView;

public class CCController implements View.OnClickListener {

    private CCFragment mContext;
    private CCView mCCView;
    public CCController(CCFragment context, CCView aaView){
        this.mCCView = aaView;
        this.mContext = context;
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_cc:
                Intent intent =  new Intent(mContext.getContext(),ChatActivity.class);
                String title = mCCView.getPipeiNameCC();
                intent.putExtra(JGApplication.CONV_TITLE,title);
                intent.putExtra(JGApplication.TARGET_ID,title);
                intent.putExtra(JGApplication.TARGET_APP_KEY,JMessageClient.getMyInfo().getAppKey());
                intent.putExtra("matchString",mCCView.getPipeiString());
                Conversation conv = JMessageClient.getSingleConversation(title,JMessageClient.getMyInfo().getAppKey());
                //如果会话为空，使用EventBus通知会话列表添加新会话
                if (conv == null) {
                    conv = Conversation.createSingleConversation(title,JMessageClient.getMyInfo().getAppKey());
                    EventBus.getDefault().post(new Event.Builder()
                            .setType(EventType.createConversation)
                            .setConversation(conv)
                            .build());
                }
                mContext.startActivity(intent);
                break;
        }
    }
}
