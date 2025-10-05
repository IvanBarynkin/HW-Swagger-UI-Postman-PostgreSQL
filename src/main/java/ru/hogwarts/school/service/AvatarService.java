package ru.hogwarts.school.service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.student;
import ru.hogwarts.school.repositories.AvatarRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {

    @Value("${students.avatar.dir.path}")
    private String avatarsDir;

    private StudentService studentService;
    private AvatarRepository avatarRepository;

    public AvatarService(AvatarRepository avatarRepository, StudentService studentService){
        this.avatarRepository = avatarRepository;
        this.studentService = studentService;
    }

    public boolean uploadAvatar(long id, MultipartFile file) throws IOException {

        student student;

        if(studentService.get(id).isEmpty()){
            return false;
        }
        else{
             student = studentService.get(id).get();
        }

        Path filePath = Path.of(avatarsDir, id + " " + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os,1024);
        ){
            bis.transferTo(bos);
        }
        Avatar avatar = findExistAvatar(id);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatarRepository.save(avatar);
        return true;
    }

    public Avatar findExistAvatar(long id){
        return avatarRepository.findByStudentId(id).orElse(new Avatar());
    }

    public Optional<Avatar> getAvatar(long id){
        return avatarRepository.findByStudentId(id);
    }

    public void deleteAvatar(long id){
        avatarRepository.deleteById(id);
    }

    public String getExtension(String fileName){
        return fileName.substring(fileName.lastIndexOf("1") + 1);
    }






}
