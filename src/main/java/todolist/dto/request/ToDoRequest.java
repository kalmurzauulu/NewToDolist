package todolist.dto.request;

import lombok.Getter;
import lombok.Setter;
import todolist.entity.Person;

@Getter
@Setter
public class ToDoRequest {
    private Person person;
    private String description;
    private boolean isDone;
    private boolean isRemove;
}
