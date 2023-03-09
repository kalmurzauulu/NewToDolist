package todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import todolist.dto.request.FileRequest;
import todolist.dto.request.ToDoRequest;
import todolist.dto.response.ToDoResponse;
import todolist.entity.Person;
import todolist.entity.Todo;
import todolist.repository.FileRepository;
import todolist.repository.PersonRepository;
import todolist.repository.TodoRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final TodoRepository todoRepository;
    private final FileRepository fileRepository;
    private final PersonRepository personRepository;

    public Todo createToDo(String description, long userId){
        Person person = personRepository.getById(userId);
        Todo todo = new Todo(person,description);
        return todoRepository.save(todo);
    }
    public Todo setDescription(long todoId, String description){
        Todo todo = todoRepository.findById(todoId).get();
        todo.setDescription(description);
        return todoRepository.save(todo);
    }
    public Todo setDone(long todoId, boolean isDone){
        Todo todo = todoRepository.findById(todoId).get();
        todo.setDone(isDone);
        return todoRepository.save(todo);
    }
    public Todo setRemove(long todoId, boolean isRemove){
        Todo todo = todoRepository.findById(todoId).get();
        todo.setRemove(isRemove);
        return todoRepository.save(todo);
    }
    public void deleteToDo(long id){
        todoRepository.deleteById(id);
    }

    public Todo mapToEntity(FileRequest fileRequest){
        Todo todo = new Todo();
        todo.setPerson(todo.getPerson());
        todo.setDescription(todo.getDescription());
        todo.setDone(todo.isDone());
        todo.setRemove(todo.isRemove());
        return todo;
    }

    public Todo mapToUpdate(Todo todo, ToDoRequest toDoRequest){
        todo.setPerson(todo.getPerson());
        todo.setDescription(todo.getDescription());
        todo.setDone(todo.isDone());
        todo.setRemove(todo.isRemove());
        return todo;

    }

    public ToDoResponse mapToResponse(Todo todo){
        ToDoResponse todoResponse = new ToDoResponse();
        todoResponse.setPerson(todo.getPerson());
        todoResponse.setDescription(todo.getDescription());
        todoResponse.setDone(todo.isDone());
        todoResponse.setRemove(todo.isRemove());
        return todoResponse;
    }

    public List<Todo> getDoneUserToDo(long userId){
        List<Todo> list = todoRepository.findByPersonIdAndIsDoneTrueAndIsRemovedFalse(userId);
        return list;
    }
    public List<Todo> getNotDoneUserToDo(long userId){
        List<Todo> list = todoRepository.findByPersonIdAndIsDoneFalseAndIsRemovedFalse(userId);
        return list;
    }
    public List<Todo> getUserToDo(long userId){
        List<Todo> list = todoRepository.findByPersonId(userId);
        return list;
    }
    public Todo getToDoById(long id){
        Optional<Todo> toDo = todoRepository.findById(id);
        if(toDo.isPresent()){
            Todo td = toDo.get();
            return td;
        }else{
            throw new EntityNotFoundException("Todo not found!");
        }
    }
}
