package todolist.dto.response;

import lombok.Getter;
import lombok.Setter;
import todolist.entity.Person;

@Getter
@Setter
public class ToDoResponse {
    Long id;
    private Person person;
    private String description;
    private boolean isDone;
    private boolean isRemove;
}
