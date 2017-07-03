package edu.fjut.fundsys.service;

import java.util.List;

import edu.fjut.fundsys.domain.ClientPicture;
import edu.fjut.fundsys.domain.ClientUser;
import edu.fjut.fundsys.domain.ClientUserTrans;
import edu.fjut.fundsys.helper.CLientUserQueryhelper;

public interface ClientUserService {
	void checkUser(String no, String pwd);

	void addClientUser(ClientUser clientUser);

	ClientUser getClientUserById(String clientUserId);

	void updateClientUser(ClientUser clientUser);

	void modifyClientUserPwd(String clientUserId, String pwd);

	/*
	 * �û����,ȡ��,ת��,���׼�¼
	 */
	void clientUserSaveBalance(String clientUserId, double balance);

	void clientUserGetBalance(String clientUserId, double balance);

	void clientUserTransBalance(String anotherClientUserId,
			String anotherClientUserName, double transBalance,
			String clientUserId);

	List<ClientUserTrans> clientUserFindAll(String clientUserId);

	void addClientUserTrans(ClientUserTrans clientUserTrans);

	/*
	 * ��ϲ�ѯ
	 */
	int totalNumber(CLientUserQueryhelper helper);

	List<ClientUserTrans> totalClientUserTrans(CLientUserQueryhelper helper,
			int beginIdx);

	/****/
	void addClientPicture(ClientPicture clientPicture);

	ClientPicture getClientPicture(String clientId);

	void updateClientPicture(ClientPicture clientPicture);

}