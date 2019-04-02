package jiguang.chat.controller;
import android.content.Intent;

import android.view.View;


import com.activeandroid.query.Select;

import java.util.List;

import jiguang.chat.R;
import jiguang.chat.activity.MResultActivity;
import jiguang.chat.activity.fragment.AFragment;
import jiguang.chat.application.JGApplication;
import jiguang.chat.database.SignEntry;
import jiguang.chat.utils.HttpConnect;
import jiguang.chat.view.AView;


public class AController implements View.OnClickListener {

    private AFragment mContext;
    private AView mAView;
    public AController(AFragment context,AView aView){
        this.mContext = context;
        this.mAView = aView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_a:
                Intent intent  = new Intent(mContext.getContext(),MResultActivity.class);
                JGApplication.mySign = mAView.getText();
                List<SignEntry> signEntries = new Select().from(SignEntry.class).where("sign=?",mAView.getText()).execute();
                JGApplication.mySignId = signEntries.get(0).getSignId();
                HttpConnect.sendRequestSignsWithOkHttpXR(JGApplication.mySignId);
                JGApplication.pageChoose = 'A';
                HttpConnect.postLikeSign(JGApplication.a);
                mContext.startActivity(intent);
                break;
            case R.id.a_heart:
                mAView.setImgColorAndOther();
               break;
        }
    }

}
