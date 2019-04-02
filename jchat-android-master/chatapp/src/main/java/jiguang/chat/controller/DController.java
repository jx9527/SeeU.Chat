package jiguang.chat.controller;


import android.content.Intent;
import android.view.View;

import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

import java.util.List;

import jiguang.chat.R;
import jiguang.chat.activity.MResultActivity;
import jiguang.chat.activity.fragment.BFragment;
import jiguang.chat.activity.fragment.DFragment;
import jiguang.chat.application.JGApplication;
import jiguang.chat.database.SignEntry;
import jiguang.chat.utils.HttpConnect;
import jiguang.chat.utils.SharePreferenceManager;
import jiguang.chat.view.BView;
import jiguang.chat.view.DView;

public class DController implements View.OnClickListener {
    private DFragment mContext;
    private DView mDView;
    private String mString;

    public DController(DFragment context,DView dView){
        this.mContext = context;
        this.mDView = dView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_d:
                Intent intent  = new Intent(mContext.getContext(),MResultActivity.class);
                JGApplication.mySign = mDView.getText();
                List<SignEntry> signEntries = new Select().from(SignEntry.class).where("sign=?",mDView.getText()).execute();
                JGApplication.mySignId = signEntries.get(0).getSignId();
                HttpConnect.sendRequestSignsWithOkHttpXR(JGApplication.mySignId);
                JGApplication.pageChoose = 'D';
                HttpConnect.postLikeSign(JGApplication.d);
                mContext.startActivity(intent);
                break;
            case R.id.d_heart:
                mDView.setImgColorAndOther();
                break;
        }
    }
}
