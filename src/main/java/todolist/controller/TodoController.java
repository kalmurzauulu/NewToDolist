package todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todolist.entity.Todo;
import todolist.repository.TodoRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/todo")
public class TodoController {
    @Autowired
    private TodoRepository repository;
    @GetMapping
    public List<Todo> findAll(){
        return repository.findAll();
    }
    @PostMapping
    public Todo save(@RequestBody Todo entity){
       return repository.save(entity);
    }
    @PutMapping
    public Todo update(@RequestBody Todo entity){
        return repository.save(entity);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
         repository.deleteById(id);
    }

}
