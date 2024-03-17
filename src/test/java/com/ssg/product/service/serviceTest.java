package com.ssg.product.service;

import com.ssg.productmanager.DTO.PageRequestDTO;
import com.ssg.productmanager.DTO.ProductDTO;
import com.ssg.productmanager.service.ProductSerive;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class serviceTest {

    @Autowired(required = false)
    private ProductSerive productSerive;

    @Test
    public void testRegister(){
        ProductDTO dto = ProductDTO.builder().name("서비스테스트")
                .price(1000).amount(30).build();
        productSerive.register(dto);
    }

    @Test
    public void testListall(){
//        log.info(productSerive.listAll());
        log.info(productSerive.getList(PageRequestDTO.builder().build()));
    }


    @Test
    public void testGetone(){
        log.info(productSerive.getOne(1L));
    }
    @Test
    public void testDelete(){
        productSerive.remove(4L);
    }

    @Test
    public void testModify(){
        ProductDTO dto = ProductDTO.builder().pno(5L).name("서비스테스트수정")
                .amount(22).price(2345).build();
        productSerive.modify(dto);
    }

}
