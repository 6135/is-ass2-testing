package mei.uc.is.SimpleProject.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import mei.uc.is.SimpleProject.entity.Professor;
import reactor.core.publisher.Mono;

public interface ProfessorRepository extends R2dbcRepository<Professor,Long>{
    Mono<Professor> findByName(String name);

}
