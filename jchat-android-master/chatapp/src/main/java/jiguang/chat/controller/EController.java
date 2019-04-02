package jiguang.chat.controller;

import android.content.Intent;
import android.view.View;

import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

import java.util.List;
import java.util.jar.JarEntry;

import jiguang.chat.R;
import jiguang.chat.activity.MResultActivity;
import jiguang.chat.activity.fragment.BFragment;
import jiguang.chat.activity.fragment.EFragment;
import jiguang.chat.application.JGApplication;
import jiguang.chat.database.SignEntry;
import jiguang.chat.utils.HttpConnect;
import jiguang.chat.utils.SharePreferenceManager;
import jiguang.chat.view.BView;
import jiguang.chat.view.EView;

public class EController implements View.OnClickListener {
    private EFragment mContext;
    private EView mEView;
    private String mString;

    public EController(EFragment context, EView eView){
        this.mContext = context;
        this.mEView = eView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_e:
                Intent intent  = new Intent(mContext.getContext(),MResultActivity.class);
                JGApplication.mySign = mEView.getText();
                List<SignEntry> signEntries = new Select().from(SignEntry.class).where("sign=?",mEView.getText()).execute();
                JGApplication.mySignId = signEntries.get(0).getSignId();
                HttpConnect.sendRequestSignsWithOkHttpXR(JGApplication.mySignId);
                JGApplication.pageChoose = 'E';
                HttpConnect.postLikeSign(JGApplication.e);
                mContext.startActivity(intent);
                break;
            case R.id.e_heart:
                mEView.setImgColorAndOther();
                break;
        }
    }
}
