package in.eps.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.eps.entity.Employee;
import in.eps.entity.SalarySlip;
import in.eps.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id).orElse(null);
	}

	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}

	public double calculateMonthlySalary(Employee employee) {
		return employee.getHourlyRate() * employee.getHoursWorked() * 4;
	}

	public SalarySlip generateSalarySlip(Employee employee) {
		SalarySlip salarySlip = new SalarySlip();
		salarySlip.setEmployee(employee);
		salarySlip.setMonthlySalary(calculateMonthlySalary(employee));
		salarySlip.setIssueDate(LocalDate.now());
		return salarySlip;
	}
}
