package ru.hogwarts.school.controller;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@RestController
@RequestMapping("/avatar/{studentID}")
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService){
        this.avatarService = avatarService;
    }

    @GetMapping
    public void getAvatar(@PathVariable long studentID, HttpServletResponse response) throws IOException{

        Optional<Avatar> avatar = avatarService.getAvatar(studentID);
        if(avatar.isEmpty()){
            response.setStatus(404);
        }
        else{
            Path path = Path.of(avatar.get().getFilePath());

            try (InputStream is = Files.newInputStream(path);
                 OutputStream os = response.getOutputStream();) {
                response.setStatus(200);
                response.setContentType(avatar.get().getMediaType());
                response.setContentLength((int) avatar.get().getFileSize());
                is.transferTo(os);
            }

        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable long studentID, @RequestParam MultipartFile avatar) throws IOException{
        if(avatarService.uploadAvatar(studentID, avatar)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateAvatar(@PathVariable long studentID, @RequestParam MultipartFile avatar) throws IOException{
        if(avatarService.uploadAvatar(studentID, avatar)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAvatar(@PathVariable long studentID){
        avatarService.deleteAvatar(studentID);
        return ResponseEntity.ok().build();
    }


}
