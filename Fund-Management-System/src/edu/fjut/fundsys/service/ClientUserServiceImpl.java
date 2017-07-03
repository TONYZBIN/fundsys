package edu.fjut.fundsys.service;

import java.util.List;

import edu.fjut.fundsys.dao.ClientUserDao;
import edu.fjut.fundsys.domain.ClientPicture;
import edu.fjut.fundsys.domain.ClientUser;
import edu.fjut.fundsys.domain.ClientUserTrans;
import edu.fjut.fundsys.exception.ApplicationException;
import edu.fjut.fundsys.exception.DataAccessException;
import edu.fjut.fundsys.helper.CLientUserQueryhelper;

public class ClientUserServiceImpl implements ClientUserService {
	private ClientUserDao clientUserDao;

	public void setClientUserDao(ClientUserDao clientUserDao) {
		this.clientUserDao = clientUserDao;
	}

	@Override
	public void checkUser(String no, String pwd) {
		clientUserDao.checkUser(no, pwd);
	}

	@Override
	public void addClientUser(ClientUser clientUser) {
		clientUserDao.addclientUser(clientUser);
	}

	@Override
	public ClientUser getClientUserById(String clientUserId) {
		return clientUserDao.getClientUserById(clientUserId);
	}

	@Override
	public void updateClientUser(ClientUser clientUser) {
		clientUserDao.updateClientUser(clientUser);
	}

	@Override
	public void modifyClientUserPwd(String clientUserId, String pwd) {
		clientUserDao.modifyClientUserPwd(clientUserId, pwd);
	}

	@Override
	public void clientUserSaveBalance(String clientUserId, double balance) {
		ClientUser clientUser = clientUserDao.getClientUserById(clientUserId);
		if (clientUser == null) {
			throw new ApplicationException("���Ѿ�����,�����µ�¼");
		}
		if (!clientUser.getActive()) {
			throw new ApplicationException("���˻��Ѿ�������,����ϵrxc");
		}
		clientUser.setBalance((Double) clientUser.getBalance() + balance);
		clientUserDao.updateClientUser(clientUser);
	}

	@Override
	public void clientUserGetBalance(String clientUserId, double balance) {
		ClientUser clientUser = clientUserDao.getClientUserById(clientUserId);
		if (clientUser == null) {
			throw new ApplicationException("���Ѿ�����,�����µ�¼");
		}
		if (!clientUser.getActive()) {
			throw new ApplicationException("����˻��Ѿ�������,����ϵrxc");
		}
		if (clientUser.getBalance() - balance < 0) {
			throw new ApplicationException("�������Ѿ�����,����������");
		}
		clientUser.setBalance(clientUser.getBalance() - balance);
		clientUserDao.updateClientUser(clientUser);
	}

	@Override
	public void clientUserTransBalance(String anotherClientUserId,
			String anotherClientUserName, double transBalance,
			String clientUserId) {
		ClientUser anotherClientUser = clientUserDao
				.getClientUserById(anotherClientUserId);
		ClientUser clientUser = clientUserDao.getClientUserById(clientUserId);
		if (clientUser == null) {
			throw new ApplicationException("���Ѿ�����,�����µ�¼");
		}
		if (anotherClientUser == null) {
			throw new ApplicationException("����ĶԷ��˻�������,����");
		}
		if (!clientUser.getActive()) {
			throw new ApplicationException("����˻��Ѿ�������,����ϵrxc");
		}
		if (!anotherClientUser.getActive()) {
			throw new ApplicationException("�Է��˻��Ѿ�������,ת��ʧ��");
		}
		if (clientUser.getBalance() - transBalance < 0) {
			throw new ApplicationException("�������Ѿ�����,����������");
		}
		if (!anotherClientUser.getClientName().equals(anotherClientUserName)) {
			throw new ApplicationException("������˻������ֲ�ƥ��,������");
		}
		anotherClientUser.setBalance(anotherClientUser.getBalance()
				+ transBalance);
		System.out.println("++:" + anotherClientUser.getBalance());
		clientUser.setBalance(clientUser.getBalance() - transBalance);
		clientUserDao.updateClientUser(clientUser);
		clientUserDao.updateClientUser(anotherClientUser);
	}

	@Override
	public List<ClientUserTrans> clientUserFindAll(String clientUserId) {
		return clientUserDao.clientUserFindAll(clientUserId);
	}

	@Override
	public void addClientUserTrans(ClientUserTrans clientUserTrans) {
		clientUserDao.addClientUserTrans(clientUserTrans);
	}

	@Override
	public int totalNumber(CLientUserQueryhelper helper) {
		return clientUserDao.totalNumber(helper);
	}

	@Override
	public List<ClientUserTrans> totalClientUserTrans(
			CLientUserQueryhelper helper, int beginIdx) {
		return clientUserDao.totalClientUserTrans(helper, beginIdx);
	}

	@Override
	public void addClientPicture(ClientPicture clientPicture) {
		clientUserDao.addClientPicture(clientPicture);

	}

	@Override
	public ClientPicture getClientPicture(String clientId) {
		return clientUserDao.getClientPicture(clientId);
	}

	@Override
	public void updateClientPicture(ClientPicture clientPicture) {
		clientUserDao.updateClientPicture(clientPicture);

	}

}
