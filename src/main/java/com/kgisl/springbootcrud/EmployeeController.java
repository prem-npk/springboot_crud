package com.kgisl.springbootcrud;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @CrossOrigin("*")
// @RestController
// @RequestMapping("/api/students")
// public class StudentController {
//     @Autowired
//     private StudentRepository studentRepository;

//     @GetMapping
//     public List<Student> getAllStudents() {
//         return studentRepository.findAll();
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Student> getStudentById(@PathVariable String id) {
//         Optional<Student> student = studentRepository.findById(id);
//         return student.map(ResponseEntity::ok)
//                 .orElse(ResponseEntity.notFound().build());
//     }

//     @PostMapping
//     public ResponseEntity<Student> createStudent(@RequestBody Student student) {
//         Student savedStudent = studentRepository.save(student);
//         return ResponseEntity.ok(savedStudent);
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<Student> updateStudent(@PathVariable String id, @RequestBody Student student) {
//         Optional<Student> existingStudent = studentRepository.findById(id);
//         if (!existingStudent.isPresent()) {
//             return ResponseEntity.notFound().build();
//         }
//         student.setId(id);
//         Student savedStudent = studentRepository.save(student);
//         return ResponseEntity.ok(savedStudent);
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
//         Optional<Student> existingStudent = studentRepository.findById(id);
//         if (!existingStudent.isPresent()) {
//             return ResponseEntity.notFound().build();
//         }
//         studentRepository.deleteById(id);
//         return ResponseEntity.noContent().build();
//     }
// }
import org.springframework.http.HttpStatus;
@CrossOrigin("*")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setLastName(employee.getLastName());
            existingEmployee.setEmailId(employee.getEmailId());
            Employee savedEmployee = employeeRepository.save(existingEmployee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            employeeRepository.delete(optionalEmployee.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

