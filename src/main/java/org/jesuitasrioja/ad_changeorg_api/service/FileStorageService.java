package org.jesuitasrioja.ad_changeorg_api.service;


import org.jesuitasrioja.ad_changeorg_api.file.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService (FileStorageProperties fileStorageProperties){
        this.fileStorageLocation =
                Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception e){
            throw new RuntimeException("Couldn't create the directory where the"+
                    "upload files will be saved", e);
        }
    }

    public String storeFile(MultipartFile multipartFile){
        UUID uuid = UUID.randomUUID();
        String filename = StringUtils.cleanPath(uuid+"_"+
                multipartFile.getOriginalFilename());
        try {
            Path targetLocalation = this.fileStorageLocation.resolve(filename);
            Files.copy(multipartFile.getInputStream(),targetLocalation, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        }catch (Exception e){
            e.printStackTrace();
        }
        return filename;
    }

}
