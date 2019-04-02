package jiguang.chat.view;

import android.content.Context;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import jiguang.chat.R;

public class MResultView extends RelativeLayout {
    private ImageView[] mImageViewList;

    private ImageView mRefresh;
    private int[] mImgListID;
    private ScrollControlViewPager mViewPagerContainer_mresult;
    public MResultView(Context context, AttributeSet atrrs){
        super(context,atrrs);
    }
    public void initModule(){

        mRefresh = findViewById(R.id.mresult_refresh_1);

        mImgListID = new int[]{
                R.id.mresult_a1, R.id.mresult_a2,R.id.mresult_b1,R.id.mresult_b2,
                R.id.mresult_c1,R.id.mresult_c2,R.id.mresult_d1,R.id.mresult_d2,
                R.id.mresult_e1,R.id.mresult_e2,
                R.id.mresult_rect1_1,R.id.mresult_rect1_2,R.id.mresult_rect1_3,
                R.id.mresult_rect1_4,R.id.mresult_rect1_5
        };
        mImageViewList = new ImageView[15];
        for(int i = 0;i < 15;i++){
            mImageViewList[i] = findViewById(mImgListID[i]);
        }
        mViewPagerContainer_mresult = findViewById(R.id.mresult_match_viewpager);
        mImageViewList[10].setSelected(true);
        mImageViewList[1].setVisibility(View.INVISIBLE);
        mImageViewList[2].setVisibility(View.INVISIBLE);
        mImageViewList[4].setVisibility(View.INVISIBLE);
        mImageViewList[6].setVisibility(View.INVISIBLE);
        mImageViewList[8].setVisibility(View.INVISIBLE);
    }
    public void setOnClickListener(OnClickListener onclickListener) {
        for (int i = 0; i < mImageViewList.length; i++) {
            mImageViewList[i].setOnClickListener(onclickListener);
        }
        mRefresh.setOnClickListener(onclickListener);
    }

    public void setOnPageChangeListener_mresult(ViewPager.OnPageChangeListener onPageChangeListener) {
        mViewPagerContainer_mresult.addOnPageChangeListener(onPageChangeListener);
    }

    public void setViewPagerAdapter_mresult(FragmentPagerAdapter adapter) {
        mViewPagerContainer_mresult.setAdapter(adapter);
    }

    public void setCurrentItem_mresult(int index, boolean scroll) {
        mViewPagerContainer_mresult.setCurrentItem(index, scroll);
    }
    public void setVisiable(){
        for(int i=0; i<10;i++){
            mImageViewList[i].setVisibility(View.VISIBLE);
        }
    }

    public void setImageViewVisiable(int index){
        setVisiable();
        for(int i =0;i<5;i++){
            if (index == i){
                mImageViewList[i+10].setSelected(true);
            }else{
                mImageViewList[i+10].setSelected(false);
            }
        }
        switch(index){
            case 0:
                mImageViewList[1].setVisibility(View.INVISIBLE);
                mImageViewList[2].setVisibility(View.INVISIBLE);
                mImageViewList[4].setVisibility(View.INVISIBLE);
                mImageViewList[6].setVisibility(View.INVISIBLE);
                mImageViewList[8].setVisibility(View.INVISIBLE);
                break;
            case 1:
                mImageViewList[0].setVisibility(View.INVISIBLE);
                mImageViewList[3].setVisibility(View.INVISIBLE);
                mImageViewList[4].setVisibility(View.INVISIBLE);
                mImageViewList[6].setVisibility(View.INVISIBLE);
                mImageViewList[8].setVisibility(View.INVISIBLE);
                break;
            case 2:
                mImageViewList[5].setVisibility(View.INVISIBLE);
                mImageViewList[0].setVisibility(View.INVISIBLE);
                mImageViewList[2].setVisibility(View.INVISIBLE);
                mImageViewList[6].setVisibility(View.INVISIBLE);
                mImageViewList[8].setVisibility(View.INVISIBLE);
                break;
            case 3:
                mImageViewList[0].setVisibility(View.INVISIBLE);
                mImageViewList[2].setVisibility(View.INVISIBLE);
                mImageViewList[4].setVisibility(View.INVISIBLE);
                mImageViewList[7].setVisibility(View.INVISIBLE);
                mImageViewList[8].setVisibility(View.INVISIBLE);
                break;
            case 4:
                mImageViewList[0].setVisibility(View.INVISIBLE);
                mImageViewList[2].setVisibility(View.INVISIBLE);
                mImageViewList[4].setVisibility(View.INVISIBLE);
                mImageViewList[6].setVisibility(View.INVISIBLE);
                mImageViewList[9].setVisibility(View.INVISIBLE);
                break;
            default:
                break;

        }
    }
}
