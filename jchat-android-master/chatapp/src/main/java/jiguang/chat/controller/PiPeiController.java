package jiguang.chat.controller;

import android.content.Intent;
import android.view.View;

import jiguang.chat.R;
import jiguang.chat.activity.MatchActivity;
import jiguang.chat.activity.fragment.PiPeiFragment;
import jiguang.chat.utils.HttpConnect;

public class PiPeiController implements View.OnClickListener{
    private PiPeiFragment mContext;
    public PiPeiController(PiPeiFragment context){
        this.mContext = context;
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.match_btn:
                Intent intent = new Intent(mContext.getContext(),MatchActivity.class);
                mContext.startActivity(intent);
                HttpConnect.sendRequestUsersWithOkHttp();
                break;
            default:
                break;
        }
    }

}
