package jiguang.chat.controller;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import jiguang.chat.activity.MResultActivity;

import jiguang.chat.activity.fragment_twice.AAFragment;
import jiguang.chat.activity.fragment_twice.BBFragment;
import jiguang.chat.activity.fragment_twice.CCFragment;
import jiguang.chat.activity.fragment_twice.DDFragment;
import jiguang.chat.activity.fragment_twice.EEFragment;
import jiguang.chat.adapter.ViewPagerAdapter;
import jiguang.chat.view.MResultView;


public class MResultController implements ViewPager.OnPageChangeListener {

    private MResultView mResultView;
    private MResultActivity mContext;
    private AAFragment mAAFragment;
    private BBFragment mBBFragment;
    private CCFragment mCCFragment;
    private DDFragment mDDFragment;
    private EEFragment mEEFragment;

    public MResultController(MResultActivity context, MResultView mresultview){
        this.mContext = context;
        this.mResultView = mresultview ;
        setViewPager();
    }
    private void setViewPager(){
      final List<Fragment> fragmentsResult = new ArrayList<>();
      mAAFragment = new AAFragment();
      mBBFragment = new BBFragment();
      mCCFragment = new CCFragment();
      mDDFragment = new DDFragment();
      mEEFragment = new EEFragment();

      fragmentsResult.add(mAAFragment);
      fragmentsResult.add(mBBFragment);
      fragmentsResult.add(mCCFragment);
      fragmentsResult.add(mDDFragment);
      fragmentsResult.add(mEEFragment);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(mContext.getbSupportFragmentManger(),fragmentsResult);

        mResultView.setViewPagerAdapter_mresult(viewPagerAdapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {mResultView.setImageViewVisiable(position);}

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
