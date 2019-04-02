package jiguang.chat.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

import java.util.List;
import java.util.Random;

import jiguang.chat.R;
import jiguang.chat.application.JGApplication;
import jiguang.chat.database.SignEntry;
import jiguang.chat.utils.FindObject;
import jiguang.chat.utils.SharePreferenceManager;

public class CView extends LinearLayout {
    private Context mContext;
    private Button mButton;
    private TextView mTextView;
    private ImageView mImageView;
    private int i = 0;
    public CView(Context context, AttributeSet attrs){
        super(context,attrs);
        this.mContext = context;
    }
    public void initModule(){
        mButton = findViewById(R.id.btn_c);
        mButton.setAllCaps(false);
       mTextView = findViewById(R.id.left_msg_1c);
       mImageView = findViewById(R.id.c_heart);
        Select select = new Select();
        List<SignEntry> signEntries = select.from(SignEntry.class).execute();
        Random random1 = new Random(SharePreferenceManager.getCachedSeed()+2);
        JGApplication.c = random1.nextInt(signEntries.size());
        String hhh = signEntries.get(JGApplication.c).getSign();
        Log.d("C", ""+random1.nextInt(signEntries.size()));
        Log.d("C1", ""+random1.nextInt(signEntries.size()));
        mTextView.setText(hhh);
    }
    public void setListener(OnClickListener onClickListener) {
        mButton.setOnClickListener(onClickListener);
        mImageView.setOnClickListener(onClickListener);
    }
    public void setImgColor(){
        FindObject findObject = new FindObject(mTextView.getText().toString());
        if(findObject.ifExist()){
            mImageView.setSelected(true);
        }else{
            mImageView.setSelected(false);
        }
    }

    public void setImgColorAndOther() {
        FindObject findObject = new FindObject(mTextView.getText().toString());
        if (findObject.ifExist()) {
            mImageView.setSelected(false);
            Toast.makeText(mContext, "取消收藏", Toast.LENGTH_SHORT).show();
            //使用数据库判定替换
            Update update = new Update(SignEntry.class);
            update.set("cs=?", 0).where("sign=?", mTextView.getText().toString()).execute();
        }else {
            mImageView.setSelected(true);
            SharePreferenceManager.setCachedHeartChoose(true);
            Toast.makeText(mContext, "收藏成功", Toast.LENGTH_LONG).show();
            Update update = new Update(SignEntry.class);
            update.set("cs=?",1).where("sign=?",mTextView.getText().toString()).execute();
        }
    }
    public String getText(){
        return mTextView.getText().toString();
    }


}
