package com.wzh.aidlservicedemo;

import android.os.Parcel;
import android.os.Parcelable;

import static android.os.Looper.getMainLooper;

/**
 * Created by 99210 on 2017/7/8.
 */

public class HelloMsg implements Parcelable{

    private String msg;
    private int pid;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.msg);
        dest.writeInt(this.pid);
    }

    public HelloMsg(String msg, int pid) {
        this.msg = msg;
        this.pid = pid;
    }

    public String printAll(){
        return "msg ="+getMsg()+";pid ="+getPid()+";thread ="+Thread.currentThread()+";threadId ="+Thread.currentThread().getId()+";main threadId ="+getMainLooper().getThread().getId();
    }

    protected HelloMsg(Parcel in) {
        this.msg = in.readString();
        this.pid = in.readInt();
    }

    public static final Creator<HelloMsg> CREATOR = new Creator<HelloMsg>() {
        @Override
        public HelloMsg createFromParcel(Parcel source) {
            return new HelloMsg(source);
        }

        @Override
        public HelloMsg[] newArray(int size) {
            return new HelloMsg[size];
        }
    };
}
