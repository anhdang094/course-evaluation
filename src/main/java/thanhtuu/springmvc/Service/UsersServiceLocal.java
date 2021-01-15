package thanhtuu.springmvc.Service;

import thanhtuu.springmvc.Domain.Users;
import thanhtuu.springmvc.Temporary.Account;

public interface UsersServiceLocal {
   //Check Email exits ?
	Users getIdByEmail(String eID);

	void createAccount(Account account);
}
