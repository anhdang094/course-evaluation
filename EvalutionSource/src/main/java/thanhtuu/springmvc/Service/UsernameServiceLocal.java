package thanhtuu.springmvc.Service;

import java.util.List;

import thanhtuu.springmvc.Domain.Username;

public interface UsernameServiceLocal {
	
	Username getByUserId(long id);

	Username getIDByEmail(String email);

	/*
	 * Show list username database
	 */
	List<Username> getUserList();

	/*
	 * Insert username new
	 */
	void insert(Username user);

	/*
	 * Update primary key
	 */
	void updateByPrimaryKey(Username user);

}