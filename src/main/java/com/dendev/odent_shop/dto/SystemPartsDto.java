package com.dendev.odent_shop.dto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemPartsDto {
    private Long partId;

    @NotEmpty(message = "The brand is required!")
//    @Pattern(regexp = "Asus|Acer|Dell|Hp|Lenovo|MSI|", message = "Invalid brand selected")
    private String brand;

    @NotEmpty(message = "The part name is required!")
//    @Pattern(regexp = "LCD|Keyboard|Trackpad|Ram|SSD|M.2", message = "Invalid part name selected")
    private String part_name;

    @NotEmpty(message = "The category is required!")
    @Pattern(regexp = "Memory|Storage|Notebook|Processor|Power Supply|Motherboard|", message = "Invalid category selected")
    private String category;


    @NotEmpty(message = "The storage size is required!")
//    @Pattern(regexp = "None Applicable|120 GB|240 GB|500 GB|1 TB|2 TB", message = "Invalid storage size selected")
    private String storage_size;

    @Min(value = 1, message = "The stocks must be greater than 0!")
    private int stocks;

    @Min(0)
    private double price;

    @Size(min = 10, message = "The description should be at least 10 characters")
    @Size(max = 2000, message = "The description cannot exceed 2000 characters")
    private String description;

    private Date createdAt;

    private String image_file_name;

    private MultipartFile image_file;

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPart_name() {
        return part_name;
    }

    public void setPart_name(String part_name) {
        this.part_name = part_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStorage_size() {
        return storage_size;
    }

    public void setStorage_size(String storage_size) {
        this.storage_size = storage_size;
    }

    public int getStocks() {
        return stocks;
    }

    public void setStocks(int stocks) {
        this.stocks = stocks;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getImage_file_name() {
        return image_file_name;
    }

    public void setImage_file_name(String image_file_name) {
        this.image_file_name = image_file_name;
    }

    public MultipartFile getImage_file() {
        return image_file;
    }

    public void setImage_file(MultipartFile image_file) {
        this.image_file = image_file;
    }
}
