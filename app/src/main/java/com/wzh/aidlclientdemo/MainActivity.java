package com.wzh.aidlclientdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wzh.aidlservicedemo.HelloMsg;
import com.wzh.aidlservicedemo.IRemoteService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnBindService;
    private Button mBtnUnbindService;
    private Button mBtnSendMsg;
    private Button mBtnGetSendedMsg;

    private IRemoteService mIRemoteService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnBindService = (Button) findViewById(R.id.btn_bindservice);
        mBtnUnbindService = (Button) findViewById(R.id.btn_unbindservice);
        mBtnSendMsg = (Button) findViewById(R.id.btn_sendmsg);
        mBtnGetSendedMsg = (Button) findViewById(R.id.btn_getsendedmsg);
        mBtnBindService.setOnClickListener(this);
        mBtnUnbindService.setOnClickListener(this);
        mBtnSendMsg.setOnClickListener(this);
        mBtnGetSendedMsg.setOnClickListener(this);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("==","onServiceConnected");
            Toast.makeText(MainActivity.this,"onServiceConnected",Toast.LENGTH_LONG).show();
            mIRemoteService = IRemoteService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("==","onServiceDisconnected");
            Toast.makeText(MainActivity.this,"onServiceDisconnected",Toast.LENGTH_LONG).show();
            mIRemoteService = null;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_bindservice:
                Intent intent = new Intent();
                intent.setPackage(MainActivity.this.getPackageName());
                intent.setComponent(new ComponentName("com.wzh.aidlservicedemo","com.wzh.aidlservicedemo.RemoteService"));
                bindService(intent,mServiceConnection,0);
                break;
            case R.id.btn_unbindservice:
                unbindService(mServiceConnection);
                break;
            case R.id.btn_sendmsg:
                try {
                   mIRemoteService.sayHello("type is from client");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_getsendedmsg:
                try {
                    HelloMsg msg  = mIRemoteService.getMsg();
                    Log.e("==",msg.printAll()+"<<is service thread id");
                    Toast.makeText(this,msg.printAll()+"<<is service thread id",Toast.LENGTH_LONG).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
