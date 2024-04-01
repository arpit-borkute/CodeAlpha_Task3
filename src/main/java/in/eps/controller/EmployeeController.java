package in.eps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.eps.entity.Employee;
import in.eps.entity.SalarySlip;
import in.eps.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeService.getEmployeeById(id);
		return ResponseEntity.ok(employee);
	}

	@PostMapping
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		Employee createdEmployee = employeeService.createEmployee(employee);
		return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}/calculate-salary")
	public ResponseEntity<Double> calculateSalary(@PathVariable Long id) {
		Employee employee = employeeService.getEmployeeById(id);
		if (employee == null) {
			return ResponseEntity.notFound().build();
		}
		double monthlySalary = employeeService.calculateMonthlySalary(employee);
		return ResponseEntity.ok(monthlySalary);
	}

	@PostMapping("/{id}/generate-salary-slip")
	public ResponseEntity<SalarySlip> generateSalarySlip(@PathVariable Long id) {
		Employee employee = employeeService.getEmployeeById(id);
		if (employee == null) {
			return ResponseEntity.notFound().build();
		}
		SalarySlip salarySlip = employeeService.generateSalarySlip(employee);

		return new ResponseEntity<>(salarySlip, HttpStatus.CREATED);
	}
}
