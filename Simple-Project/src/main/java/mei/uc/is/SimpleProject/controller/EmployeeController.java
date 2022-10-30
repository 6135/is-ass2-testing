package mei.uc.is.SimpleProject.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mei.uc.is.SimpleProject.dto.CreateEmployee;
import mei.uc.is.SimpleProject.entity.Employee;
import mei.uc.is.SimpleProject.repository.EmployeeRepository;
import reactor.core.publisher.*;

@RestController
@RequestMapping(value="/api",produces = "application/json", method = {RequestMethod.GET, RequestMethod.PUT,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PATCH})
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {
    private final EmployeeRepository memberRepository;

    /**
     * Creates or updates employees
     * @param request
     * @return new employee, or updated
     */
    @PostMapping(value = "employee")
    public Mono<Employee> createEmployee(@RequestBody CreateEmployee request){
        System.out.println(request);
        final Employee employee = Employee.builder()
            .name(request.getName())
            .id(request.getId())
            .build();
        return memberRepository.save(employee);
                                         
    }

    /**
     *  Creates new employee
     * @param name
     * @return new employee via name, auto id
     */
    @GetMapping(value = "employee/set/{name}")
    public Mono<Employee> createEmployeeGet(@PathVariable String name){
        final Employee employee = Employee.builder()
            .name(name)
            .build();
        return memberRepository.save(employee);
                                         
    }
    /**
     * 
     * @return All avaiable employees
     */
    @GetMapping(value="employees")
    public Flux<Employee> getAll() {
        return memberRepository.findAll();
    }

    /**
     * Finds employee by name
     * @param name
     * @return The found employee
     */
    @GetMapping(value="employee/{name}")
    public Mono<Employee> getOne(@PathVariable String name){
        return memberRepository.findByName(name);

    }
    /**
     * Updates employees
     * @param request
     * @return new employee, or updated
     */
    @PatchMapping(value = "employee/update/{id}")
    public Mono<Employee> updateEmployee(@PathVariable long id, @RequestBody CreateEmployee request){
        // memberRepository.findById(id).subscribe(val->{
        //     if (val == null) {
        //         throw new IllegalAccessError("No employee");
        //     }
        // });
        final Employee employee = Employee.builder()
            .name(request.getName())
            .id(request.getId())
            .build();
        return memberRepository.save(employee);
                                         
    }
    

    @DeleteMapping(value = "employee/delete/{id}")
    public Mono<Void> deleteEmployee(@PathVariable long id){
        return memberRepository.deleteById(id);
    }

}
