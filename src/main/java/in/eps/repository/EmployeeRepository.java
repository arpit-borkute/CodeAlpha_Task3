package in.eps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.eps.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
