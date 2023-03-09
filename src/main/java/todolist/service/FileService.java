package todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import todolist.dto.request.FileRequest;
import todolist.dto.request.PersonRequest;
import todolist.dto.response.FileResponse;
import todolist.dto.response.PersonResponse;
import todolist.entity.File;
import todolist.entity.Person;
import todolist.repository.FileRepository;
import todolist.repository.TodoRepository;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
    private final TodoRepository todoRepository;

    public FileResponse create(FileRequest fileRequest){
        File file = mapToEntity(fileRequest);
        fileRepository.save(file);
        return mapToResponse(file);
    }
    public FileResponse getById(long id){
        Optional<File> person = fileRepository.findById(id);
        return mapToResponse(fileRepository.findById(id).get());
    }

    public File mapToEntity(FileRequest fileRequest){
        File file = new File();
        file.setFileName(fileRequest.getFileName());
        file.setType(file.getType());
        file.setTodo(file.getTodo());
        file.setData(file.getData());

        return file;
    }

    public File mapToUpdate(File file,FileRequest fileRequest){
        file.setFileName(fileRequest.getFileName());
        file.setType(file.getType());
        file.setTodo(file.getTodo());
        file.setData(file.getData());

        return file;
    }

    public FileResponse mapToResponse(File file){
        FileResponse fileResponse = new FileResponse();
        fileResponse.setFileName(file.getFileName());
        fileResponse.setType(file.getType());
        fileResponse.setTodo(file.getTodo());
        fileResponse.setData(file.getData());
        return fileResponse;
    }


}
