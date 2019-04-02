package jiguang.chat.database;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "sign",id = "_id")
public class SignEntry extends Model {
      @Column
      public int signId;
      @Column
      public String sign;
      @Column
      public boolean choose = false;
      @Column
      public int cs = 0;

      public SignEntry(){}
      public SignEntry(int id,String sign){
            this.signId = id;
            this.sign =sign;
      }
      public int getSignId() {
            return signId;
      }

      public void setSignId(int signId) {
            this.signId = signId;
      }

      public String getSign() {
            return sign;
      }

      public void setSign(String sign) {
            this.sign = sign;
      }

      public boolean isChoose() {
            return choose;
      }

      public void setChoose(boolean choose) {
            this.choose = choose;
      }

      public int getCs() {
            return cs;
      }

      public void setCs(int cs) {
            this.cs = cs;
      }
}
