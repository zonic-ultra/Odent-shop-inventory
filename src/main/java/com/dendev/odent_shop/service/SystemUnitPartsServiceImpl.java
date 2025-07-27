package com.dendev.odent_shop.service;

import com.dendev.odent_shop.dto.SystemPartsDto;
import com.dendev.odent_shop.entity.SystemUnitParts;
import com.dendev.odent_shop.exception.SystemUnitPartsException;
import com.dendev.odent_shop.repository.SystemUnitPartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Service
public class SystemUnitPartsServiceImpl implements SystemUnitPartsService{


    @Autowired
    private SystemUnitPartsRepository systemUnitPartsRepository;

    @Override
    public SystemUnitParts savePart(SystemPartsDto systemPartsDto) {
        String storage_file_name = handleFileUpload(systemPartsDto.getImage_file());
        SystemUnitParts systemParts = mapToEntity(systemPartsDto);

        systemParts.setCreatedAt(new Date());
        systemParts.setImage_file_name(storage_file_name);

        return systemUnitPartsRepository.save(systemParts);
    }

    @Override
    public SystemUnitParts updatePart(Long partId, SystemPartsDto systemPartsDto) {
        SystemUnitParts systemPart = systemUnitPartsRepository.findById(partId).orElseThrow(()-> new SystemUnitPartsException("System Unit Part with Id: " +partId+ " not found!"));
        updateEntity(systemPart,systemPartsDto);
        if (systemPartsDto.getImage_file() != null && !systemPartsDto.getImage_file().isEmpty()){
            String storage_file_name = handleFileUpload(systemPartsDto.getImage_file());
            systemPart.setImage_file_name(storage_file_name);
        }

        return systemUnitPartsRepository.save(systemPart);
    }

    @Override
    public String deletePartById(Long partId) {
        SystemUnitParts systemPartsDto = systemUnitPartsRepository.findById(partId).orElseThrow(()-> new SystemUnitPartsException("System Unit Part with Id: " +partId+ " not found!"));
        deleteImageFile(systemPartsDto.getImage_file_name());
        systemUnitPartsRepository.delete(systemPartsDto);
        return "system Unit Part with Id: " +partId+ " was successfully updated";

    }

    @Override
    public SystemUnitParts findPartById(Long partId) {
        return systemUnitPartsRepository.findById(partId)
                .orElseThrow(()-> new SystemUnitPartsException("System Unit Part with Id: " +partId+ " not found!"));
    }

    @Override
    public List<SystemUnitParts> getAllSystemUnitParts() {
        return systemUnitPartsRepository.findAll(Sort.by(Sort.Direction.DESC,"partId"));
    }

    @Override
    public String handleFileUpload(MultipartFile image_file) {

        if (image_file == null || image_file.isEmpty()){
            return null;
        }

        try{
            String upload_dir = "public/images";
            Path upload_path = Paths.get(upload_dir);

            if (!Files.exists((upload_path))){
                Files.createDirectories(upload_path);
            }
            String file_name = new Date().getTime() + "_" + image_file.getOriginalFilename();
            try(InputStream inputStream = image_file.getInputStream()){
                Files.copy(inputStream, upload_path.resolve(file_name), StandardCopyOption.REPLACE_EXISTING);
            }

            return file_name;
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file: " + e.getMessage());
        }

    }

    @Override
    public SystemUnitParts mapToEntity(SystemPartsDto systemPartsDto) {

        SystemUnitParts systemParts = new SystemUnitParts();
        systemParts.setBrand(systemPartsDto.getBrand());
        systemParts.setPart_name(systemPartsDto.getPart_name());
        systemParts.setCategory(systemPartsDto.getCategory());
        systemParts.setStorage_size(systemPartsDto.getStorage_size());
        systemParts.setStocks(systemPartsDto.getStocks());
        systemParts.setPrice(systemPartsDto.getPrice());
        systemParts.setDescription(systemPartsDto.getDescription());
        return systemParts;
    }

    @Override
    public void deleteImageFile(String filename) {
        if (filename != null && !filename.isEmpty()){
                try {
                    String upload_dir = "public/images";
                    Path filePath = Paths.get(upload_dir + filename);
                }catch (Exception e){
                    System.out.println("Error deleting file "+ e.getMessage());
                }
        }
    }

    @Override
    public void updateEntity(SystemUnitParts systemUnitParts, SystemPartsDto systemPartsDto) {
        systemUnitParts.setPart_name(systemPartsDto.getPart_name());
        systemUnitParts.setBrand(systemPartsDto.getBrand());
        systemUnitParts.setCategory(systemPartsDto.getCategory());
        systemUnitParts.setStorage_size(systemPartsDto.getStorage_size());
        systemUnitParts.setStocks(systemPartsDto.getStocks());
        systemUnitParts.setPrice(systemPartsDto.getPrice());
        systemUnitParts.setDescription(systemPartsDto.getDescription());
    }
}
