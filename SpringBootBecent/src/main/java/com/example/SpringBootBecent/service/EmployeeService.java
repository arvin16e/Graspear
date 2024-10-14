package com.example.SpringBootBecent.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBootBecent.Entity.Employee;
import com.example.SpringBootBecent.Repo.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	public EmployeeRepository empRepo;

	public String createStudent(Employee emp) {
		empRepo.save(emp);
		return "Created";
	}

	public List<Employee> getAll(Set<Integer> age) {
		if(age==null) {
			return empRepo.findAll();
		}
		return empRepo.findAllByageIn(age);
	}

	public Employee getById(int id) {
		Optional<Employee> emp = empRepo.findById(id);
		if (!emp.isPresent()) {
			return null;
		}
		return empRepo.findById(id).get();
	}

	public String delete(int id) {
		Optional<Employee> emp = empRepo.findById(id);
		if (!emp.isPresent()) {
			return "No Data Found";
		}
		empRepo.deleteById(id);
		return "Deleted";
	}

	public String update(Employee emp) {
		Optional<Employee> op = empRepo.findById(emp.getId());

		if (op.isPresent()) {
			op.get().setName(emp.getName().isBlank() ? op.get().getName() : emp.getName());
			op.get().setAge(emp.getAge()== 0 ? op.get().getAge() : emp.getAge());

			empRepo.save(op.get());
			return "Updated";
		}

		return "DATA NOT FOUND!";
	}
}
