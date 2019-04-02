package jiguang.chat.view;

import android.content.Context;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import jiguang.chat.R;

public class MatchView extends RelativeLayout {
    private ImageView[] mImageViewList;

    private ImageView mRefresh;
    private int[] mImgListID;
    private ScrollControlViewPager mViewPagerContainer_match;
    public MatchView(Context context, AttributeSet atrrs){
        super(context,atrrs);
    }
    public void initModule(){

        mImgListID = new int[]{
                R.id.a1, R.id.a2,R.id.b1,R.id.b2,
                R.id.c1,R.id.c2,R.id.d1,R.id.d2,
                R.id.e1,R.id.e2,
                R.id.rect1_1,R.id.rect1_2,R.id.rect1_3,
                R.id.rect1_4,R.id.rect1_5
        };
        mImageViewList = new ImageView[15];
        for(int i = 0;i < 15;i++){
            mImageViewList[i] = findViewById(mImgListID[i]);
        }
        mViewPagerContainer_match = findViewById(R.id.match_viewpager);
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
    }

    public void setOnPageChangeListener_match(ViewPager.OnPageChangeListener onPageChangeListener) {
        mViewPagerContainer_match.addOnPageChangeListener(onPageChangeListener);
    }

    public void setViewPagerAdapter_match(FragmentPagerAdapter adapter) {
        mViewPagerContainer_match.setAdapter(adapter);
    }

    public void setCurrentItem_match(int index, boolean scroll) {
        mViewPagerContainer_match.setCurrentItem(index, scroll);
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
