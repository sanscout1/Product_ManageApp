package com.ssg.productmanager.service;

import com.ssg.productmanager.DTO.PageRequestDTO;
import com.ssg.productmanager.DTO.PageResponseDTO;
import com.ssg.productmanager.DTO.ProductDTO;

import java.util.List;

public interface ProductSerive {

    void register(ProductDTO dto);

//    List<ProductDTO> listAll();

    ProductDTO getOne(Long pno);

    void modify(ProductDTO dto);

    void remove(Long pno);

    PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO);
}
