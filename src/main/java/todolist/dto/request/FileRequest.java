package todolist.dto.request;

import lombok.Getter;
import lombok.Setter;
import todolist.entity.Todo;

@Getter
@Setter
public class FileRequest {

    private Todo todo;
    private String fileName;
    private String type;
    private byte[] data;
}
