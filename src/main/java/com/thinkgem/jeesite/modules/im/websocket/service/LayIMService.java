package com.thinkgem.jeesite.modules.im.websocket.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.thinkgem.jeesite.modules.im.admin.dao.friend.ChatFriendDao;
import com.thinkgem.jeesite.modules.im.admin.dao.group.ChatGroupDao;
import com.thinkgem.jeesite.modules.im.admin.dao.user.ChatUserDao;
import com.thinkgem.jeesite.modules.im.admin.entity.friend.ChatFriend;
import com.thinkgem.jeesite.modules.im.admin.entity.group.ChatGroup;
import com.thinkgem.jeesite.modules.im.admin.entity.user.ChatUser;
import com.thinkgem.jeesite.modules.im.websocket.data.SNSFriend;
import com.thinkgem.jeesite.modules.im.websocket.data.SNSGroup;
import com.thinkgem.jeesite.modules.im.websocket.data.SNSInit;
import com.thinkgem.jeesite.modules.im.websocket.data.SNSMData;
import com.thinkgem.jeesite.modules.im.websocket.data.SNSMember;
import com.thinkgem.jeesite.modules.im.websocket.data.SNSUser;
import com.thinkgem.jeesite.modules.im.websocket.data.SNSdata;
import com.thinkgem.jeesite.modules.storage.service.StorageService;

@Service
public class LayIMService {
	@Autowired
	private ChatUserDao  chatUserDao;
	@Autowired
	private ChatFriendDao  chatFriendDao;
	@Autowired
	private ChatGroupDao chatGroupDao;
	@Autowired
	private StorageService storageService;
	
	public SNSMember nickname(String uid,String nickname) {
		SNSMember SNSMember = new SNSMember();
		if(StringUtils.isEmpty(uid)) {
			SNSMember.setCode(1);
			SNSMember.setMsg("非法参数");
			return SNSMember;
		}
		if(!StringUtils.isEmpty(nickname) && nickname.length()>200) {
			SNSMember.setCode(1);
			SNSMember.setMsg("昵称不适合显示");
			return SNSMember;
		}
		ChatUser chatUser = chatUserDao.get(uid);
		chatUser.setUsername(nickname);
		chatUserDao.update(chatUser);
		return SNSMember;
	}
	public SNSMember head(String uid,String imageBase64) {
		SNSMember SNSMember = new SNSMember();
		if(StringUtils.isEmpty(uid)) {
			SNSMember.setCode(1);
			SNSMember.setMsg("非法参数");
			return SNSMember;
		}
		if(StringUtils.isEmpty(imageBase64) ) {
			SNSMember.setCode(1);
			SNSMember.setMsg("头像不信息不全");
			return SNSMember;
		}
		String filename = "chat_face_".concat(uid);
		InputStream inputStream = new ByteArrayInputStream(imageBase64.getBytes());
		storageService.save(inputStream, "image/jpeg", filename);
		
		ChatUser chatUser = chatUserDao.get(uid);
		chatUser.setAvatar(filename);
		chatUserDao.update(chatUser);
		return SNSMember;
	}
	public SNSMember sign(String uid,String sign) {
		SNSMember SNSMember = new SNSMember();
		if(StringUtils.isEmpty(uid)) {
			SNSMember.setCode(1);
			SNSMember.setMsg("非法参数");
			return SNSMember;
		}
		if(!StringUtils.isEmpty(sign) && sign.length()>200) {
			SNSMember.setCode(1);
			SNSMember.setMsg("签名不适合显示");
			return SNSMember;
		}
		ChatUser chatUser = chatUserDao.get(uid);
		chatUser.setSign(sign);
		chatUserDao.update(chatUser);
		return SNSMember;
	}
	
	public SNSInit getInitList(String uid) {
		SNSInit SNSInit = new SNSInit();
		SNSdata SNSdata = new SNSdata();
		ChatUser chatUser = chatUserDao.get(uid);
		if(!StringUtils.isEmpty(chatUser)) {
			SNSUser SNSUser  = new SNSUser();
			SNSUser.setId(chatUser.getId());
			SNSUser.setUsername(chatUser.getUsername());
			SNSUser.setAvatar(chatUser.getAvatar());
			SNSUser.setStatus(chatUser.getOnline());
			SNSUser.setSign(chatUser.getSign());
			SNSdata.setMine(SNSUser);
			
			
			//设置好友
			List<ChatGroup> friendGroups = chatGroupDao.myGroupByUid("0","");
			List<SNSFriend> friends = new ArrayList<SNSFriend>();
			for(ChatGroup chatGroup : friendGroups) {
				SNSFriend SNSFriend = new SNSFriend();
				SNSFriend.setId(chatGroup.getId());
				SNSFriend.setGroupname(chatGroup.getGroupname());
				SNSFriend.setOnline(chatGroup.getOnline());
				List<ChatFriend> chatFriends = chatFriendDao.getChatFriendByUserid(uid);
				List<SNSUser> list = new ArrayList<SNSUser>();
				for(ChatFriend chatFriend : chatFriends) {
					if(chatFriend.getGid().equals(chatGroup.getId())) {
						ChatUser cu = chatUserDao.get(chatFriend.getFid());
						
						if(!StringUtils.isEmpty(cu)) {
							SNSUser su = new SNSUser();
							su.setId(cu.getId());
							su.setUsername(cu.getUsername());
							su.setAvatar(cu.getAvatar());
							su.setStatus(cu.getOnline());
							su.setSign(cu.getSign());
							list.add(su);
						}
					}
				}
				SNSFriend.setList(list);
				friends.add(SNSFriend);
			}
			
			
			//设置组信息
			List<SNSGroup> groups = new ArrayList<SNSGroup>();
			List<ChatGroup> chatGroups = chatGroupDao.myGroupByUid("1",uid);
			for(ChatGroup chatGroup:chatGroups) {
				SNSGroup SNSGroup = new SNSGroup();
				SNSGroup.setId(chatGroup.getId());
				SNSGroup.setAvatar(chatGroup.getAvatar());
				SNSGroup.setGroupname(chatGroup.getGroupname());
				groups.add(SNSGroup);
			}
			SNSdata.setFriend(friends);
			SNSdata.setGroup(groups);
			SNSInit.setData(SNSdata);
		}
		
		return SNSInit;
	}
	
	
	public SNSMember GroupFriend(String groupId) {
		ChatFriend chatFriend = new ChatFriend();
		chatFriend.setGid(groupId);
		List<ChatFriend> chatFriends = chatFriendDao.findList(chatFriend);
		ChatUser groupMaster = null;
		
		List<SNSUser> userList = new ArrayList<SNSUser>(); 
		
		for(ChatFriend cf:chatFriends) {
			if(!StringUtils.isEmpty(cf.getMaster())) {
				groupMaster = chatUserDao.get(cf.getMaster());
			}
			ChatUser chatUser = chatUserDao.get(cf.getFid());
			SNSUser snsUser =new SNSUser();
			snsUser.setId(chatUser.getId());
			snsUser.setAvatar(chatUser.getAvatar());
			snsUser.setSign(chatUser.getSign());
			snsUser.setUsername(chatUser.getUsername());
			userList.add(snsUser);
		}
		SNSMember member = new SNSMember();
		
		SNSMData data = new SNSMData();
		data.setList(userList);
		if(!StringUtils.isEmpty(groupMaster)) {
			SNSUser snsMaster = new SNSUser();
			snsMaster.setAvatar(groupMaster.getAvatar());
			snsMaster.setId(groupMaster.getId());
			snsMaster.setSign(groupMaster.getSign());
			snsMaster.setUsername(groupMaster.getUsername());
			data.setOwner(snsMaster);
		}
		member.setCode(0);
		member.setData(data);
		return member;
	}
	public List<String> getMemberListOnlyIds(String groupId) {
		return null;
	}
}
