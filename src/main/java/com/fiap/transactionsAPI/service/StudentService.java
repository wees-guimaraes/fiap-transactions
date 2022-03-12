package com.fiap.transactionsAPI.service;

import com.fiap.transactionsAPI.dto.StudentDTO;
import com.fiap.transactionsAPI.entity.StudentEntity;
import com.fiap.transactionsAPI.repository.StudentRepository;
import com.fiap.transactionsAPI.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }


    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream()
                .map(StudentDTO::new)
                .collect(Collectors.toList());
    }

    public StudentDTO findById(String id) {
        Optional<StudentEntity> optionalStudentEntity = studentRepository.findById(id);
        StudentEntity studentEntity = optionalStudentEntity.orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        Constants.OBJETO_NAO_ENCONTRADO));
        return new StudentDTO(studentEntity);
    }

    public StudentDTO insert(StudentDTO studentDTO) {

        return new StudentDTO(studentRepository.save(new StudentEntity(studentDTO)));
    }

    public StudentDTO update(StudentDTO studentDTO) {
        findById(String.valueOf(studentDTO.getRa()));

        return null;
    }

    public void delete(Object id) {
        try {
            Optional<StudentEntity> dto = studentRepository.findByRa(Long.parseLong((String) id));
            studentRepository.delete(dto.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        } catch (Exception e) {
            this.findById(id.toString());
            studentRepository.deleteById(id.toString());
        }
    }

    public StudentDTO findByRa(Long ra) {
        Optional<StudentEntity> optionalStudent = studentRepository.findByRa(ra);
        return new StudentDTO(optionalStudent.orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }
}
