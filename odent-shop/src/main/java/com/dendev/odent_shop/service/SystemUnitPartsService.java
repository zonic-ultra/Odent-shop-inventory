package com.dendev.odent_shop.service;

import com.dendev.odent_shop.dto.SystemPartsDto;
import com.dendev.odent_shop.entity.SystemUnitParts;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SystemUnitPartsService {
    SystemUnitParts savePart(SystemPartsDto systemPartsDto);
    SystemUnitParts updatePart(Long partId, SystemPartsDto systemPartsDto);
    String deletePartById(Long partId);
    SystemUnitParts findPartById(Long partId);
    List<SystemUnitParts> getAllSystemUnitParts();
    String handleFileUpload(MultipartFile image_file);
    SystemUnitParts mapToEntity(SystemPartsDto systemPartsDto);
    void deleteImageFile(String filename);
    void updateEntity(SystemUnitParts systemUnitParts, SystemPartsDto systemPartsDto);
}
