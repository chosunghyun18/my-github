package com.cho.system.global.file.domain.application;

import com.cho.system.global.file.domain.model.ImageFile;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.cho.system.global.file.domain.model.ImageFile;

public interface ImageFileHandle {
    ImageFile uploadImageFile(MultipartFile file, String folderName);
    List<ImageFile> uploadImageFiles(MultipartFile[] file, String folderName);
    void deleteImage(String fileName, String folderName);
}
