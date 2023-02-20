package com.java.restapi.Services;

import com.java.restapi.Entity.FileData;
import com.java.restapi.Repository.FileRepo;
import com.java.restapi.Util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class FileService {

    public final String FOLDER_PATH="/Users/venkt/Desktop/imagesUpload/";

    @Autowired
    FileRepo fileRepo;

    public String UploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath =FOLDER_PATH+file.getOriginalFilename();
        fileRepo.save(FileData.builder()
                              .name(file.getOriginalFilename())
                               .type(file.getContentType())
                        .filePath(filePath)
                .build());
        file.transferTo( new File(filePath));
        return "File uploaded successfully at: "+ filePath;
    }

    public byte[] downloadImageFromFileSyste(String imageName) throws IOException {
        Optional<FileData> fileData= fileRepo.findByName(imageName);
     byte[] imagedata= Files.readAllBytes(new File(fileData.get().getFilePath()).toPath());
     return imagedata;
    }
}
