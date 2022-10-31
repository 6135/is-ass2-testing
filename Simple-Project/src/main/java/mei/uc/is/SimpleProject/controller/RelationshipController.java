package mei.uc.is.SimpleProject.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mei.uc.is.SimpleProject.entity.Relationship;
import mei.uc.is.SimpleProject.handler.CreateRelationship;
import mei.uc.is.SimpleProject.repository.RelationshipRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@RestController
@RequestMapping(value="/api/relationship",produces = "application/json", method = {RequestMethod.GET, RequestMethod.PUT,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PATCH})
@RequiredArgsConstructor
@Slf4j
public class RelationshipController {
    private final RelationshipRepository relationshipRepository;

    /* Create */
    @PutMapping(value = "add")
    public Mono<Relationship> addRelation(@RequestBody CreateRelationship createRelationship){
        Relationship s = Relationship.builder()
            .studentId(createRelationship.getStudentId())
            .professorId(createRelationship.getProfessorId())
            .build();
        return relationshipRepository.save(s);
    }
    /* Read */
    @GetMapping(value = "byProfessorId/{id}")
    public Flux<Relationship> getByProfessorId(@PathVariable Long id){
        return relationshipRepository.findAllByProfessorId(id);
    }
    @GetMapping(value = "byStudentId/{id}")
    public Flux<Relationship> getByStudentId(@PathVariable Long id){
        return relationshipRepository.findAllByStudentId(id);
    }
    @GetMapping(value = "byStudentId/{sid}/byProfessorId/{pid}")
    public Mono<Relationship> getByBothId(@PathVariable Long sid,@PathVariable long pid){
        return relationshipRepository.findByStudentIdAndProfessorId(sid,pid);
    }
    /* Delete */
    @DeleteMapping(value = "delete/byProfessorId/{id}")
    public Flux<Void> deleteByProfessorId(@PathVariable Long id){
        return relationshipRepository.deleteAllByProfessorId(id);
    }
    @DeleteMapping(value = "delete/byStudentId/{id}")
    public Flux<Void> deleteByStudentId(@PathVariable Long id){
        return relationshipRepository.deleteAllByStudentId(id);
    }
    @DeleteMapping(value = "delete/byStudentId/{sid}/byProfessorId/{pid}")
    public Mono<Void> deleteByBothId(@PathVariable Long sid,@PathVariable long pid){
        return relationshipRepository.deleteByStudentIdAndProfessorId(sid,pid);
    }
}
