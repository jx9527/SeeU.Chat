package jiguang.chat.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "catchusers",id="_id")
public class CatchUserEntry extends Model {
    @Column
    public String username;

    @Column
    public long userId;

    @Column
    public int send = 0;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSend() {
        return send;
    }

    public void setSend(int send) {
        this.send = send;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public CatchUserEntry(){
        super();
    }

    public CatchUserEntry(String username,long userId,int send){
        this.username = username;
        this.send = send;
        this.userId = userId;
    }
}
