package jiguang.chat.controller;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

import jiguang.chat.R;
import jiguang.chat.activity.LikeActivity;
import jiguang.chat.activity.MatchActivity;
import jiguang.chat.activity.fragment.AFragment;
import jiguang.chat.activity.fragment.BFragment;
import jiguang.chat.activity.fragment.CFragment;
import jiguang.chat.activity.fragment.DFragment;
import jiguang.chat.activity.fragment.EFragment;
import jiguang.chat.adapter.ViewPagerAdapter;
import jiguang.chat.view.MatchView;

public class MatchController implements  ViewPager.OnPageChangeListener{
    private MatchView mMatchView;
    private MatchActivity mContext;
    private AFragment mAFragment;
    private BFragment mBFragment;
    private CFragment mCFragment;
    private DFragment mDFragment;
    private EFragment mEFragment;
    public MatchController(MatchActivity context, MatchView matchView){
        this.mContext = context;
        this.mMatchView = matchView;
        setViewPager();
    }
    private void setViewPager(){
        final List<Fragment> fragments = new ArrayList<>();
        //添加五个fragment
        mAFragment = new AFragment();
        mBFragment = new BFragment();
        mCFragment = new CFragment();
        mDFragment = new DFragment();
        mEFragment = new EFragment();

       fragments.add(mAFragment);
       fragments.add(mBFragment);
       fragments.add(mCFragment);
       fragments.add(mDFragment);
       fragments.add(mEFragment);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(mContext.getaSupportFragmentManger(),fragments);
        mMatchView.setViewPagerAdapter_match(viewPagerAdapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {mMatchView.setImageViewVisiable(position);}

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
