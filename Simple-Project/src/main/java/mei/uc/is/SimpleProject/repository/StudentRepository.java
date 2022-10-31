package mei.uc.is.SimpleProject.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import mei.uc.is.SimpleProject.entity.Student;
import reactor.core.publisher.Mono;

public interface StudentRepository extends R2dbcRepository<Student,Long>{
    Mono<Student> findByName(String name);

}
