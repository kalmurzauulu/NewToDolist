package todolist.dto.response;

import lombok.Getter;
import lombok.Setter;
import todolist.entity.Todo;

@Getter
@Setter
public class FileResponse {
    Long id;
    private Todo todo;
    private String fileName;
    private String type;
    private byte[] data;
}
