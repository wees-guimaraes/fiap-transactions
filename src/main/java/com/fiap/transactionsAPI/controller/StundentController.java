package com.fiap.transactionsAPI.controller;

import com.fiap.transactionsAPI.dto.StudentDTO;
import com.fiap.transactionsAPI.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("students")
public class StundentController {

    private StudentService studentService;

    public StundentController(StudentService studentService){

        this.studentService = studentService;
    }

//    @GetMapping
//    public List<StudentDTO> findAllStudents() {
//        return studentService.findAll();
//    }

    @GetMapping(value = "{id}")
    public StudentDTO findById(@PathVariable String id) {
        return studentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO insertStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.insert(studentDTO);
    }

    @PutMapping(value = "{id}")
    public StudentDTO updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable String id) {
        studentDTO.setRa(Long.valueOf(id));
        return studentService.update(studentDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public void deleteStudent(@PathVariable Object id) {
        studentService.delete(id);
    }

    @GetMapping(value = "/ra/{ra}")
    public StudentDTO findByRa(@PathVariable Long ra){
        return studentService.findByRa(ra);
    }

    @GetMapping
    public Object find(@RequestParam(value = "ra", defaultValue = "") Long ra){
        return ra == null? studentService.findAll() : studentService.findByRa(ra);
    }

    @GetMapping(value = "teste")
    public String teste(){
        return "teste";
    }

}
