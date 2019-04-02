package jiguang.chat.utils;

import android.util.Log;

import com.activeandroid.query.Select;

import java.util.List;

import jiguang.chat.database.SignEntry;

public class FindObject {
    private Select mSelect = new Select();
    private String mString;
    private boolean exist = false;
    public FindObject(String string){
        this.mString = string;
    }
    public boolean ifExist() {
        List<SignEntry> signEntries = mSelect.from(SignEntry.class).where("cs=?",1).execute();
        if(signEntries.isEmpty())  {return false;}
        for(SignEntry signEntry : signEntries){
            String signlike = signEntry.getSign();
            Log.d("喜欢的句子: ", "ifExist:"+signlike+"\r原句: "+mString);
            if(mString.equals(signlike)) exist = true;
        }
        return exist;
    }
}
