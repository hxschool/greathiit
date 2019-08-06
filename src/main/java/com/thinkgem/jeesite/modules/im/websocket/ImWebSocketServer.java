package com.thinkgem.jeesite.modules.im.websocket;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.im.admin.entity.friend.ChatFriend;
import com.thinkgem.jeesite.modules.im.admin.entity.msg.ChatMsgHistory;
import com.thinkgem.jeesite.modules.im.admin.entity.user.ChatUser;
import com.thinkgem.jeesite.modules.im.admin.service.friend.ChatFriendService;
import com.thinkgem.jeesite.modules.im.admin.service.group.ChatGroupService;
import com.thinkgem.jeesite.modules.im.admin.service.msg.ChatMsgHistoryService;
import com.thinkgem.jeesite.modules.im.admin.service.user.ChatUserService;
import com.thinkgem.jeesite.modules.im.websocket.message.MessageType;
import com.thinkgem.jeesite.modules.im.websocket.message.ToClientMessageResult;
import com.thinkgem.jeesite.modules.im.websocket.message.ToClientMessageType;
import com.thinkgem.jeesite.modules.im.websocket.message.ToClientTextMessage;
import com.thinkgem.jeesite.modules.im.websocket.message.ToServerMessageMine;
import com.thinkgem.jeesite.modules.im.websocket.message.ToServerMessageTo;
import com.thinkgem.jeesite.modules.im.websocket.message.ToServerTextMessage;

@ServerEndpoint(value = "/websocket/{uid}", configurator = ImWebSocketServerConfigurator.class)
public class ImWebSocketServer {
	private static int onlineCount = 0;
	public static ConcurrentHashMap<String, Session> mapUS = new ConcurrentHashMap<String, Session>();
	private static ConcurrentHashMap<Session, String> mapSU = new ConcurrentHashMap<Session, String>();

	// 收到消息触发事件
	@OnMessage
	public void onMessage(String message, Session session) throws IOException, InterruptedException {
		Gson gson = new Gson();
		ToServerTextMessage toServerTextMessage = gson.fromJson(message, ToServerTextMessage.class);
		ToServerMessageMine mine = toServerTextMessage.getMine();
		ToServerMessageTo toServerMessageTo = toServerTextMessage.getTo();
		String type = toServerTextMessage.getTo().getType();
		String  textMessage = getToClientMessage(toServerTextMessage,ToClientMessageType.TYPE_TEXT_MESSAGE);
		
		ChatMsgHistoryService chatMsgHistoryService = (ChatMsgHistoryService)SpringContextHolder.getBean(ChatMsgHistoryService.class);
		ChatMsgHistory chatMsgHistory = new ChatMsgHistory();
		chatMsgHistory.setFromUser(mine.getId());
		chatMsgHistory.setToUser(toServerMessageTo.getId());
		chatMsgHistory.setMsgType(type);
		chatMsgHistory.setMsg(mine.getContent());
		chatMsgHistory.setSendDate(DateUtils.getDateTime());
		
		
		switch (type) {
		case MessageType.FRIEND:// 单聊
		{
			Session session_to = mapUS.get(toServerMessageTo.getId());
			if (session_to != null) {
				session_to.getAsyncRemote().sendText(textMessage);
			}
		}
			break;
		case MessageType.GROUP:// 群聊
		{
			String to = toServerMessageTo.getId();
			
			ChatFriend chatFriend = new ChatFriend();
			chatFriend.setGid(to);
			ChatFriendService chatFriendService = (ChatFriendService)SpringContextHolder.getBean(ChatFriendService.class);
			List<ChatFriend> chatFriends = chatFriendService.findByParentIdsLike(chatFriend);
			for (ChatFriend cf : chatFriends) {
				session = mapUS.get(cf.getFid());
				if (session != null) {
					session.getAsyncRemote().sendText(textMessage);
				}
			}
		}
			break;
		case MessageType.ALL:

		{
			for (Session s : session.getOpenSessions()) {
				s.getAsyncRemote().sendText(textMessage);
			}
		}
			break;
		default:
			break;
		}
		chatMsgHistoryService.save(chatMsgHistory);
	}
	
	private String getToClientMessage(ToServerTextMessage message,ToClientMessageType msgType) {
		ToClientTextMessage toClientTextMessage = new ToClientTextMessage();
		ToServerMessageMine mine = message.getMine();
		toClientTextMessage.setUsername(mine.getUsername());
		toClientTextMessage.setAvatar(mine.getAvatar());
		toClientTextMessage.setContent(mine.getContent());
		toClientTextMessage.setType(message.getTo().getType());

		// 群组和好友直接聊天不同，群组的id 是 组id，否则是发送人的id
		if (toClientTextMessage.getType().equals(MessageType.GROUP)) {
			toClientTextMessage.setId(message.getTo().getId());
		}else if (toClientTextMessage.getType().equals(MessageType.ALL)) {
			
		} else {
			toClientTextMessage.setId(mine.getId());
		}
		toClientTextMessage.setTimestamp(new Date().getTime());

		// 返回统一消息接口
		ToClientMessageResult result = new ToClientMessageResult();
		result.setMsg(toClientTextMessage);
		result.setType(msgType);
		Gson gson = new Gson();
		String  textMessage = gson.toJson(result);
		return textMessage;
	}
	private String getToClientMessage(String uid,ToClientMessageType msgType) {
		ChatUserService chatUserService = (ChatUserService)SpringContextHolder.getBean(ChatUserService.class);
		ChatUser chatUser = chatUserService.get(uid);
		ToClientTextMessage toClientTextMessage = new ToClientTextMessage();
		toClientTextMessage.setUsername(chatUser.getUsername());
		toClientTextMessage.setAvatar(chatUser.getAvatar());
		toClientTextMessage.setContent("消息提醒");
		toClientTextMessage.setType("friend");
		toClientTextMessage.setId(uid);
		toClientTextMessage.setTimestamp(new Date().getTime());

		// 返回统一消息接口
		ToClientMessageResult result = new ToClientMessageResult();
		result.setMsg(toClientTextMessage);
		result.setType(msgType);
		Gson gson = new Gson();
		String  textMessage = gson.toJson(result);
		return textMessage;
	}
	
	
	@OnOpen
	public void onOpen(Session session, EndpointConfig config, @PathParam("uid") String uid) {
		
		ChatUserService chatUserService = (ChatUserService)SpringContextHolder.getBean(ChatUserService.class);
		ChatUser chatUser = chatUserService.get(uid);
		if(!StringUtils.isEmpty(chatUser)) {
			chatUser.setOnline("online");
			chatUserService.save(chatUser);
			ChatFriendService chatFriendService = (ChatFriendService)SpringContextHolder.getBean(ChatFriendService.class);
			ChatFriend chatFriend = new ChatFriend();
			chatFriend.setUid(uid);
			List<ChatFriend>  chatFriends = chatFriendService.findByParentIdsLike(chatFriend);
			String textMessage = getToClientMessage(uid,ToClientMessageType.ONLINE_ON);
			for(ChatFriend cf : chatFriends) {
				//if(Integer.valueOf(cf.getGid())<10) {
					Session session_to = mapUS.get(cf.getFid());
					if (session_to != null) {
						session_to.getAsyncRemote().sendText(textMessage);
					}
				//}
				
			}
		}
		
		mapUS.put(uid, session);
		mapSU.put(session, uid);
		onlineCount++;
	}
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		String uid = mapSU.get(session);
		if (uid != null && uid != "") {
			
			ChatUserService chatUserService = (ChatUserService)SpringContextHolder.getBean(ChatUserService.class);
			ChatUser chatUser = chatUserService.get(uid);
			if(!StringUtils.isEmpty(chatUser)) {
				chatUser.setOnline("offline");
				chatUserService.save(chatUser);
				ChatFriendService chatFriendService = (ChatFriendService)SpringContextHolder.getBean(ChatFriendService.class);
				ChatFriend chatFriend = new ChatFriend();
				chatFriend.setUid(uid);
				List<ChatFriend>  chatFriends = chatFriendService.findByParentIdsLike(chatFriend);
				String textMessage = getToClientMessage(uid,ToClientMessageType.ONLINE_OFF);
				for(ChatFriend cf : chatFriends) {
						Session session_to = mapUS.get(cf.getFid());
						if (session_to != null) {
							session_to.getAsyncRemote().sendText(textMessage);
						}
				}
			}
			
			onlineCount--;
			mapUS.remove(uid);
			mapSU.remove(session);
		}
	}
	
	

	// 传输消息错误触发事件
	@OnError
	public void onError(Throwable error) {
		error.printStackTrace();
	}
}
