package com.ssg.productmanager.mapper;

import com.ssg.productmanager.DTO.PageRequestDTO;
import com.ssg.productmanager.Domain.ProductVO;

import java.util.List;

public interface ProductMapper {

    void insert (ProductVO productVO);

    List<ProductVO> selectAll();

    ProductVO selectOne(Long pno);
    void delete(Long pno);

    void modify(ProductVO vo);

    List<ProductVO> selectList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);

}
