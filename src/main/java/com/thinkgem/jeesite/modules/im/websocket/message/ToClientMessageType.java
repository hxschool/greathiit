package com.thinkgem.jeesite.modules.im.websocket.message;

/**
 * Created by pz on 16/11/24.
 */
public enum ToClientMessageType {

    TYPE_SYSTEM("系统消息",0),
    ONLINE_ON("上线消息",2),
    ONLINE_OFF("离线消息",3),
    TYPE_TEXT_MESSAGE("普通文本消息",1);

    ToClientMessageType(String s,int i){

    }
}
