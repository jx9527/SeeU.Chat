package jiguang.chat.view;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import jiguang.chat.R;

public class PiPeiView extends LinearLayout {
    private Context mContext;
    private EditText mEditText;
    private Button mButton;
    private TextView mTextView1;
    private TextView mTextView2;
    public PiPeiView(Context context, AttributeSet attrs){
        super(context,attrs);
        this.mContext = context;
    }
    public void initModule(){
        mButton = findViewById(R.id.match_btn);
        mTextView1 = findViewById(R.id.lol);
        mTextView2 = findViewById(R.id.lklk);
    }
    public void setListener(OnClickListener onClickListener){
        mButton.setOnClickListener(onClickListener);
    }
}
