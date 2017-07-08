// IRemoteService.aidl
package com.wzh.aidlservicedemo;

import com.wzh.aidlservicedemo.HelloMsg;

// Declare any non-default types here with import statements

interface IRemoteService {

    HelloMsg sayHello(String type);
    HelloMsg getMsg();
}
