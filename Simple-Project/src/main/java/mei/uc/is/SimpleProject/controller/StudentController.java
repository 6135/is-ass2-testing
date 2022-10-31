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
import mei.uc.is.SimpleProject.handler.CreateStudent;
// import mei.uc.is.SimpleProject.RequestHandler.CreateStudent;
import mei.uc.is.SimpleProject.entity.Student;
import mei.uc.is.SimpleProject.repository.StudentRepository;
import reactor.core.publisher.*;

@RestController
@RequestMapping(value="/api/student",produces = "application/json", method = {RequestMethod.GET, RequestMethod.PUT,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PATCH})
@RequiredArgsConstructor
@Slf4j
public class StudentController {
    private final StudentRepository studentRepository;

    /* Create  */
        //Create one
    @PutMapping(value = "add")
    public Mono<Student> createStudent(@RequestBody CreateStudent createStudent){
        
        Student s = Student.builder()
            .name(createStudent.getName())
            .birthdate(createStudent.getBirthdate())
            .credits(createStudent.getCredits())
            .gpa(createStudent.getGpa())
            .build();
        return studentRepository.save(s);
    }

    /* Read */
        //Read all
    @GetMapping(value = "getAll")
    public Flux<Student> getAllStudents(){
        return studentRepository.findAll();
    }
        //Read Specific (by name?)
    @GetMapping(value = "getByName/{name}")
    public Mono<Student> getByName(@PathVariable String name){
        return studentRepository.findByName(name);
    }
    @GetMapping(value = "{id}")
    public Mono<Student> getById(@PathVariable long id){
        return studentRepository.findById(id);
    }
    /* Update */
        //Update one
    @PatchMapping(value = "update/{id}")
    public Mono<Student> updateStudent(@PathVariable Long id, @RequestBody CreateStudent createStudent){
        System.out.println("----------------------------");
        System.out.println(createStudent);
        System.out.println("----------------------------");
        Student s = Student.builder()
            .id(id)
            .name(createStudent.getName())
            .birthdate(createStudent.getBirthdate())
            .credits(createStudent.getCredits())
            .gpa(createStudent.getGpa())
            .build();
        return studentRepository.save(s);
    }
    /* Delete */
        //Delete Specific
    @DeleteMapping(value = "delete/{id}")
    public Mono<Void> deleteById(@PathVariable Long id){
        return studentRepository.deleteById(id);
    }

}
