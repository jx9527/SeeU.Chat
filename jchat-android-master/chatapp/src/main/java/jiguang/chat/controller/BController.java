package jiguang.chat.controller;

import android.content.Intent;
import android.view.View;

import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

import java.util.List;

import cn.jiguang.api.JAction;
import jiguang.chat.R;
import jiguang.chat.activity.MResultActivity;
import jiguang.chat.activity.fragment.AFragment;
import jiguang.chat.activity.fragment.BFragment;
import jiguang.chat.application.JGApplication;
import jiguang.chat.database.SignEntry;
import jiguang.chat.utils.HttpConnect;
import jiguang.chat.utils.SharePreferenceManager;
import jiguang.chat.view.AView;
import jiguang.chat.view.BView;

public class BController implements View.OnClickListener {
    private BFragment mContext;
    private BView mBView;
    private String mString;

    public BController(BFragment context,BView bView){
        this.mContext = context;
        this.mBView = bView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_b:
                Intent intent  = new Intent(mContext.getContext(),MResultActivity.class);
                JGApplication.mySign = mBView.getText();
                List<SignEntry> signEntries = new Select().from(SignEntry.class).where("sign=?",mBView.getText()).execute();
                JGApplication.mySignId = signEntries.get(0).getSignId();
                HttpConnect.sendRequestSignsWithOkHttpXR(JGApplication.mySignId);
                JGApplication.pageChoose = 'B';
                HttpConnect.postLikeSign(JGApplication.b);
                mContext.startActivity(intent);
                break;
            case R.id.b_heart:
                mBView.setImgColorAndOther();
                break;
        }
    }
}
