package com.example.UserLogin.Service;

import java.util.List;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UserLogin.Entity.Information;
import com.example.UserLogin.Entity.PersonalDetails;
import com.example.UserLogin.Entity.UserDetails;
import com.example.UserLogin.Repository.PersonalRepository;
import com.example.UserLogin.Repository.UserRepository;

@Service
public class ServiceClass {

	@Autowired
	private UserRepository u_Repo;
	@Autowired
	private PersonalRepository p_Repo;

	public String create(UserDetails user) {
		System.err.println("Personal details: " + user.getPersonal());
		if (user.getPersonal() != null) {
			user.getPersonal().setUser(user);
			user.setPassword(encrypt(user.getPassword()));
			u_Repo.save(user);
		} else {
			return "Person is null";
		}
		return "created";

	}

	public Optional<PersonalDetails> read(int id) {
		return p_Repo.findById(id);
	}

	public PersonalDetails readByEmail(String email, String password) {

		List<PersonalDetails> list = p_Repo.findAll();

		for (PersonalDetails pd : list) {
			if (email.equals(pd.getUser().getEmail())) {
				if (encrypt(password).equals(pd.getUser().getPassword())) {
					return pd;
				}
			}
		}

		return null;
	}

	public PersonalDetails input(Information info) {

		List<PersonalDetails> list = p_Repo.findAll();

		for (PersonalDetails pd : list) {
			if (info.getEmail().equals(pd.getUser().getEmail())) {
				if (encrypt(info.getPassword()).equals(pd.getUser().getPassword())) {
					return pd;
				}
			}
		}

		return null;
	}

	public List<PersonalDetails> show() {
		return p_Repo.findAll();
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

	public String delete(int id) {
		Optional<UserDetails> op = u_Repo.findById(id);
		if (op.isPresent()) {
			u_Repo.deleteById(id);
			p_Repo.deleteById(id);
			return "Deleted";
		}
		return "Data not found";
	}

	public String update(UserDetails user) {
		System.err.println(user.getId());
		Optional<UserDetails> op = u_Repo.findById(user.getId());

		if (op.isPresent()) {
			op.get().setId(user.getId());
			op.get().setEmail(user.getEmail().isBlank() ? op.get().getEmail() : user.getEmail());
			op.get().setPassword(user.getPassword().isBlank() ? op.get().getPassword() : encrypt(user.getPassword()));
			PersonalDetails set = new PersonalDetails();

			set.setId(user.getPersonal().getId());
			set.setName(user.getPersonal().getName().isBlank() ? op.get().getPersonal().getName()
					: user.getPersonal().getName());
			set.setAddress(user.getPersonal().getAddress().isBlank() ? op.get().getPersonal().getAddress()
					: user.getPersonal().getAddress());
			set.setPhone_number(user.getPersonal().getPhone_number() == 0 ? op.get().getPersonal().getPhone_number()
					: user.getPersonal().getPhone_number());
			set.setGender(user.getPersonal().getGender().isBlank() ? op.get().getPersonal().getGender()
					: user.getPersonal().getGender());
			set.setSalary(user.getPersonal().getSalary() == 0 ? op.get().getPersonal().getSalary()
					: user.getPersonal().getSalary());
			op.get().setPersonal(set);
			delete(user.getId());

			u_Repo.save(op.get());
		} else {
			return "Data not found";
		}

		return "Updated";

		/*
		 * 
		 * { "id":4, "email":"arvin16e@gmail.com", "password":"Arvin18.", "personal":{
		 * "name":"vijay", "id":4, "phone_number":9087564321, "address":"madurai,India",
		 * "salary":47500 }
		 * 
		 * }
		 */
	}

}
