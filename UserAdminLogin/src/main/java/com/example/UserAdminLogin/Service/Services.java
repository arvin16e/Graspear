package com.example.UserAdminLogin.Service;

import java.beans.BeanProperty;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.example.UserAdminLogin.Entity.Admin;
import com.example.UserAdminLogin.Entity.Personal;
import com.example.UserAdminLogin.Entity.User;
import com.example.UserAdminLogin.Repository.AdminRepo;
import com.example.UserAdminLogin.Repository.PersonalRepo;
import com.example.UserAdminLogin.Repository.UserRepo;

@Service
public class Services {

	@Autowired(required = true)
	private UserRepo u_repo;
	@Autowired(required = true)
	private PersonalRepo p_repo;
	@Autowired(required = true)
	private AdminRepo a_repo;

	public String createAdminDetails(Admin admin) {
		// validation
		Iterable<Admin> op = a_repo.findAll();
		for (Admin n : op) {
			if (n.getUsername().equalsIgnoreCase(admin.getUsername())) {
				return "username already exits";
			}
			if (n.getEmail().equalsIgnoreCase(admin.getEmail())) {
				return "Account already exits";
			}
		}
		admin.setUuid(UUID.randomUUID());
		// save to db
		a_repo.save(admin);
		return "created";
	}

	public String createUser(User user) {
		if (user.getPersonal() != null) {
			UUID uuid=UUID.randomUUID();
			user.setUuid(uuid);
			user.setPassword(encrypt(user.getPassword()));
			user.setCreatedDate(LocalDate.now());
			user.getPersonal().setUuid(uuid);
			u_repo.save(user);
		}else {
			return "Personal details not given";
		}
		return "created";
	}

	public String createPersonalDetails(Personal personal) {
		return "created";
	}

	public String update(User user) {
		
		u_repo.save(user);
		return "updated";
	}

	public Iterable<PersonalRepo> show() {
		return p_repo.findAll();
	}

	public String delete(UUID uuid) {
		Optional<User> op = u_repo.findByuuid(uuid);
		if (op.isPresent()) {
			u_repo.deleteByuuid(uuid);
			p_repo.deleteByuuid(uuid);
			return "Deleted";
		}
		return "Data not found";
	}

	public Object read(String username) {
		
		return null;
	}

	public void readPersonal() {

	}

	public String encrypt(String password) {
		String encrypt = "";
		for (int i = 0; i < password.length(); i++) {
			encrypt += (char) (password.charAt(i) + 3);
		}
		return encrypt;
	}

	public String decrypt(String password) {
		String decrypt = "";
		for (int i = 0; i < password.length(); i++) {
			decrypt += password.charAt(i) - 3;
		}
		return decrypt;
	}

	public Iterable<User> showAllUsers() {
		return u_repo.findAll();
		
	}

	public Iterable<User> loginAdmin(String username, String password) {
		String pass=encrypt(password);
		Admin adminLogin=a_repo.findByusernameAndpassword(username,pass);
		if(adminLogin != null) {
			return showAllUsers();
		}
		return null;
	}
	public Iterable<User> loginUser(String username, String password) {
		String pass=encrypt(password);
		User userlogin=u_repo.findByusernameAndpassword(username, pass);
		if(userlogin != null) {
			return showAllUsers();
		}
		return null;
	}
	
}
