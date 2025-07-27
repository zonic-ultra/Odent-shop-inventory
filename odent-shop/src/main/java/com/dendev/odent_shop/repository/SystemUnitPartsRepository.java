package com.dendev.odent_shop.repository;


import com.dendev.odent_shop.dto.SystemPartsDto;
import com.dendev.odent_shop.entity.SystemUnitParts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUnitPartsRepository extends JpaRepository<SystemUnitParts,Long> {
}
