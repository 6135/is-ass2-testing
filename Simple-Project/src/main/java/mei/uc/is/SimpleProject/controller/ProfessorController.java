package mei.uc.is.SimpleProject.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mei.uc.is.SimpleProject.handler.CreateProfessor;
// import mei.uc.is.SimpleProject.RequestHandler.CreateProfessor;
import mei.uc.is.SimpleProject.entity.Professor;
import mei.uc.is.SimpleProject.repository.ProfessorRepository;
import reactor.core.publisher.*;

@RestController
@RequestMapping(value="/api/professor",produces = "application/json", method = {RequestMethod.GET, RequestMethod.PUT,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PATCH})
@RequiredArgsConstructor
@Slf4j
public class ProfessorController {
    private final ProfessorRepository professorRepository;

    /* Create  */
        //Create one
    @PutMapping(value = "add")
    public Mono<Professor> createProfessor(@RequestBody CreateProfessor createProfessor){
        Professor s = Professor.builder()
            .name(createProfessor.getName())
            .build();
        return professorRepository.save(s);
    }

    /* Read */
        //Read all
    @GetMapping(value = "getAll")
    public Flux<Professor> getAllProfessors(){
        return professorRepository.findAll();
    }
        //Read Specific (by name?)
    @GetMapping(value = "getByName/{name}")
    public Mono<Professor> getByName(@PathVariable String name){
        System.out.println(name);
        return professorRepository.findByName(name);
    }
    @GetMapping(value = "{id}")
    public Mono<Professor> getById(@PathVariable long id){
        return professorRepository.findById(id);
    }
    /* Update */
        //Update one
    @PatchMapping(value = "update/{id}")
    public Mono<Professor> updateProfessor(@PathVariable Long id, @RequestBody CreateProfessor createProfessor){
        Professor s = Professor.builder()
            .id(id)
            .name(createProfessor.getName())
            .build();
        return professorRepository.save(s);
    }
    /* Delete */
        //Delete Specific
    @DeleteMapping(value = "delete/{id}")
    public Mono<Void> deleteById(@PathVariable Long id){
        return professorRepository.deleteById(id);
    }

}
