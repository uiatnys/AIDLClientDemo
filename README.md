本文参考：http://blog.csdn.net/fwt336/article/details/52587133
# 客户端编码步骤
## aidl描述：
> AIDL (Android Interface Definition Language) 是一种IDL 语言，用于生成可以在Android设备上两个进程之间进行进程间通信(interprocess communication, IPC)的代码。如果在一个进程中（例如Activity）要调用另一个进程中（例如Service）对象的操作，就可以使用AIDL生成可序列化的参数。

## 操作步骤
#### 1.新建一个android项目
#### 2.将服务端aidl目录完整复制到main目录下
#### 3.Build - Make Project，执行完成后完成界面部分搭建，本文中只说明绑定服务。
#### 4.声明`ServiceConnection`与`IRemoteService`对象（IRemoteService由Make Project之后在build目录中生成
#### 5.点击绑定服务按钮，执行`bingService`，传入的intent我进行如下初始化
```
 Intent intent = new Intent();
 intent.setPackage(MainActivity.this.getPackageName());
 intent.setComponent(newComponentName("com.wzh.aidlservicedemo","com.wzh.aidlservicedemo.RemoteService"));
```
#### 如果bindService执行成功，会回调onServiceConnected，通过`IRemoteService.Stub.asInterface`方法，我们可以将IBinder对象转成IRemoteService，至此我们已经拿到了服务端的IRemoteService对象，拥有此对象后即可调用IRemoteService中的方法，具体使用不再赘述。
## 项目结构如下所示


![build](/screenshots/build.png "build")


![mainactivity](/screenshots/mainactivity.png "mainactivity")


![src](/screenshots/src.png "src")

[^_^]:
![src.png](http://upload-images.jianshu.io/upload_images/1941925-b4f9d0c81021346b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

[^_^]:
![build.png](http://upload-images.jianshu.io/upload_images/1941925-3fc467a175ac6879.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

[^_^]:
![mainactivity.png](http://upload-images.jianshu.io/upload_images/1941925-088ffd908dc68983.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)