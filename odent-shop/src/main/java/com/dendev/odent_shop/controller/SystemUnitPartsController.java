package com.dendev.odent_shop.controller;

import com.dendev.odent_shop.dto.SystemPartsDto;
import com.dendev.odent_shop.entity.SystemUnitParts;
import com.dendev.odent_shop.service.SystemUnitPartsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("system_unit")
public class SystemUnitPartsController {
    @Autowired
    private SystemUnitPartsService systemUnitPartsService;

    @GetMapping({"", "/"})
    public String showProductList(Model model){
        model.addAttribute("system_unit", systemUnitPartsService.getAllSystemUnitParts());

        return "product/system_unit_parts";
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllSystemUnitParts(){
        List<SystemUnitParts> systemPartsDtoList = systemUnitPartsService.getAllSystemUnitParts();
        return  new ResponseEntity<>(systemPartsDtoList, HttpStatus.OK);
    }
    @GetMapping ("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        SystemUnitParts find = systemUnitPartsService.findPartById(id);

        return new ResponseEntity<>(find,HttpStatus.ACCEPTED);
    }
    @PostMapping("/create")
    public String addParts(@Valid @ModelAttribute SystemPartsDto systemPartsDto, BindingResult result){
        MultipartFile imageFile = systemPartsDto.getImage_file();
        if (imageFile == null || imageFile.isEmpty()) {
            result.addError(new FieldError("systemPartsDto", "image_file", "The image file is required!"));
        }

        if (result.hasErrors()) {
            return "product/create_system_unit_parts";
        }
        systemUnitPartsService.savePart(systemPartsDto);

        return "redirect:/system_unit";
    }
    @GetMapping("/create")
    public String showCreateCellphonePartForm(Model model){
        model.addAttribute("systemPartsDto", new SystemPartsDto());
        return "product/create_system_unit_parts";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model){
        SystemUnitParts product = systemUnitPartsService.findPartById(id);

        SystemPartsDto systemPartsDto = new SystemPartsDto();
        systemPartsDto.setBrand(product.getBrand());
        systemPartsDto.setPart_name(product.getPart_name());
        systemPartsDto.setCategory(product.getCategory());
        systemPartsDto.setStorage_size(product.getStorage_size());
        systemPartsDto.setStocks(product.getStocks());
        systemPartsDto.setPrice(product.getPrice());
        systemPartsDto.setDescription(product.getDescription());

        model.addAttribute("systemPartsDto", systemPartsDto);
        model.addAttribute("partId", id);

        return "product/edit_system_unit_parts";
    }


    @PutMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute SystemPartsDto systemPartsDto, BindingResult result, Model model){

        if (result.hasErrors()){
            model.addAttribute("partId", id);

            return "product/edit_system_unit_parts";
        }
        SystemUnitParts systemParts = systemUnitPartsService.updatePart(id, systemPartsDto);

        return "redirect:/system_unit";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        systemUnitPartsService.deletePartById(id);

        return "redirect:/system_unit";
    }

}
