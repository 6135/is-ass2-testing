package mei.uc.is.SimpleProject.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import mei.uc.is.SimpleProject.entity.Employee;
import reactor.core.publisher.Mono;

public interface EmployeeRepository extends R2dbcRepository<Employee,Long>{
    Mono<Employee> findByName(String name);

}
