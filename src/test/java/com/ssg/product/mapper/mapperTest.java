package com.ssg.product.mapper;

import com.ssg.productmanager.Domain.ProductVO;
import com.ssg.productmanager.mapper.ProductMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class mapperTest {

    @Autowired(required = false)
    private ProductMapper productMapper;

    @Test
    public void testInsert(){
        ProductVO vo = ProductVO.builder().name("테스트").price(5800).amount(6).build();
        log.info(vo);
        productMapper.insert(vo);
    }
    @Test
    public void testSelectAll(){
        List<ProductVO> volist = productMapper.selectAll();
        log.info(volist);
    }
    @Test
    public void testSelectOne(){
        ProductVO vo = productMapper.selectOne(2L);
        log.info(vo);
    }

    @Test
    public void testDelete(){
        productMapper.delete(3L);
        testSelectAll();
    }

    @Test
    public void testModify(){
        ProductVO vo = ProductVO.builder().pno(4L).name("테스트수정").price(3000).amount(20).build();
        productMapper.modify(vo);
        testSelectAll();
    }

}
