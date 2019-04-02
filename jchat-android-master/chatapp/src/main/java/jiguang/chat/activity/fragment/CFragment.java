package jiguang.chat.activity.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.List;
import java.util.Random;

import cn.jiguang.api.JAction;
import jiguang.chat.R;
import jiguang.chat.application.JGApplication;
import jiguang.chat.controller.CController;
import jiguang.chat.database.SignEntry;
import jiguang.chat.utils.SharePreferenceManager;
import jiguang.chat.view.CView;

public class CFragment extends  BaseFragment {
    private View mRootView;
    private CView mCView;
    private Context mContext;
    private CController mCController;
    private CFragment.ReceiveBroadCast receiveBroadCast;
    private TextView mTextView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getActivity();
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        mRootView = layoutInflater.inflate(R.layout.fragment_c,
                (ViewGroup)getActivity().findViewById(R.id.match_view),false);
        mCView = mRootView.findViewById(R.id.cview);
        mCView.initModule();
        mCController = new CController(this,mCView);
        mCView.setListener(mCController);
        init();
    }

    @Override
    public void onResume() {
        mCView.setImgColor();
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mTextView = mRootView.findViewById(R.id.left_msg_1c);
        ViewGroup p = (ViewGroup) mRootView.getParent();
        if (p != null) {
            p.removeAllViewsInLayout();
        }
        return mRootView;
    }

    private void init(){
        receiveBroadCast = new CFragment.ReceiveBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.gasFragment");    //只有持有相同的action的接受者才能接收此广播
        mContext.registerReceiver(receiveBroadCast, filter);
    }

    class ReceiveBroadCast extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            //得到广播中得到的数据，并显示出来

            Select select = new Select();
            List<SignEntry> signEntries = select.from(SignEntry.class).execute();
            Random random1 = new Random(SharePreferenceManager.getCachedSeed()+2);
            JGApplication.c = random1.nextInt(signEntries.size());
            String hhh = signEntries.get(JGApplication.c).getSign();
            mTextView.setText(hhh);
        }
    }

    @Override
    public void onDestroy() {
        mContext.unregisterReceiver(receiveBroadCast);
        super.onDestroy();
    }

}
