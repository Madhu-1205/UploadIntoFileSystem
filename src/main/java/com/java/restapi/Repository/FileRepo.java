package com.java.restapi.Repository;

import com.java.restapi.Entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepo extends JpaRepository<FileData,Integer> {
    Optional<FileData> findByName(String imageName);
}
