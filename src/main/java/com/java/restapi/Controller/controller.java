package com.java.restapi.Controller;


import com.java.restapi.Services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
public class controller {
    @Autowired
    FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<?> UploadImageToFileSystem(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(fileService.UploadImageToFileSystem(file));
    }

    @GetMapping("/{imageName}")
    public ResponseEntity<?> DownloadImageFromFileSystem(@PathVariable String imageName) throws IOException {
        byte[] imageData=fileService.downloadImageFromFileSyste(imageName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }

}
