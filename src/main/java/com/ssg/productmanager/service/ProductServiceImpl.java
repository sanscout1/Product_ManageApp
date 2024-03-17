package com.ssg.productmanager.service;

import com.ssg.productmanager.DTO.PageRequestDTO;
import com.ssg.productmanager.DTO.PageResponseDTO;
import com.ssg.productmanager.DTO.ProductDTO;
import com.ssg.productmanager.Domain.ProductVO;
import com.ssg.productmanager.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductSerive{

    private final ProductMapper productMapper;

    private final ModelMapper modelMapper;

    @Override
    public void register(ProductDTO dto) {
        ProductVO vo = modelMapper.map(dto, ProductVO.class);
        productMapper.insert(vo);
    }

//    @Override
//    public List<ProductDTO> listAll() {
//        List<ProductDTO> dtolist = productMapper.selectAll().stream()
//                .map(vo -> modelMapper.map(vo, ProductDTO.class)).toList();
//        return dtolist;
//    }

    @Override
    public ProductDTO getOne(Long pno) {
        ProductDTO dto = modelMapper.map(productMapper.selectOne(pno), ProductDTO.class);
        return dto;
    }

    @Override
    public void modify(ProductDTO dto) {
        ProductVO vo = modelMapper.map(dto,ProductVO.class);
        productMapper.modify(vo);
    }

    @Override
    public void remove(Long pno) {
        productMapper.delete(pno);
    }

    @Override
    public PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO) {
        List<ProductVO> vos = productMapper.selectList(pageRequestDTO);
        List<ProductDTO> dtoList = vos.stream().map(vo -> modelMapper.map(vo,ProductDTO.class)).toList();

        int total = productMapper.getCount(pageRequestDTO);
        PageResponseDTO<ProductDTO> pageResponseDTO = PageResponseDTO.<ProductDTO>All()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO).build();

        return pageResponseDTO;
    }
}


//생성자 주입(Constructor Injection) 방식을 더 권장하고 있습니다. 생성자 주입 방식은 다음과 같은 장점을 가지고 있습니다:
//
//불변성(Immutability): 생성자를 통해 주입된 종속성은 final로 선언할 수 있으므로, 객체가 생성된 후에 변경될 수 없습니다.
//의존성 명확성: 객체를 생성할 때 필요한 모든 의존성이 명시적으로 선언되므로, 클래스가 어떤 외부 의존성을 필요로 하는지 쉽게 파악할 수 있습니다.
//테스트 용이성: 생성자 주입을 사용하면 테스트 코드에서 실제 구현 대신 모의 객체(Mock Object)를 쉽게 주입할 수 있습니다.
//Spring Framework 4.3 이상에서는 클래스에 단 하나의 생성자만 존재하는 경우, @Autowired 애노테이션을 생략할 수 있으며, Spring이 자동으로 해당 생성자를 사용하여 의존성을 주입합니다.

//필드 주입(@Autowired를 필드에 직접 사용)을 사용할 때의 단점은 다음과 같습니다:
//
//불변성 부재: 필드 주입을 사용하면, 주입된 필드를 final로 선언할 수 없습니다. 이는 객체의 상태가 생성 후 변할 수 있다는 것을 의미하며, 이는 불변성 원칙에 어긋납니다.
//의존성 숨김: 필드 주입을 사용하면 의존성이 클래스 외부에서 쉽게 파악되지 않을 수 있습니다. 생성자나 세터 메소드를 통한 주입은 의존성을 더 명확하게 만듭니다.
//테스트의 어려움: 필드 주입을 사용하는 경우, 테스트 코드에서 해당 필드에 모의 객체를 주입하기 위해 리플렉션을 사용해야 할 수도 있습니다. 반면, 생성자 주입을 사용하면 테스트 코드에서 쉽게 모의 객체를 주입할 수 있습니다.

//코드의 명확성
//생성자 주입: 클래스의 필수 의존성이 생성자를 통해 명확하게 제공됩니다. 이로 인해 객체가 필요로 하는 의존성을 쉽게 확인할 수 있으며, 의존성이 누락되는 것을 컴파일 타임에 잡아낼 수 있습니다.
//필드 주입: 필드 주입을 사용하면 의존성이 클래스 내부에 숨겨져 있어, 클래스 외부에서는 해당 클래스가 어떤 의존성을 필요로 하는지 명확하지 않을 수 있습니다.
//        유지보수성
//생성자 주입: 모든 의존성이 불변(final)으로 설정될 수 있어 객체의 상태가 런타임에 변경되는 것을 방지할 수 있습니다. 이는 애플리케이션의 안정성을 높여줍니다.
//필드 주입: 의존성이 final로 선언되지 않아 객체의 상태가 변경될 가능성이 있습니다. 또한, 스프링이 없는 환경에서 단위 테스트를 작성할 때 어려움을 겪을 수 있습니다.
//        테스트 용이성
//생성자 주입: 생성자를 통해 목 객체(Mock Objects) 또는 다른 구현체를 쉽게 주입할 수 있어, 스프링 컨테이너 없이도 단위 테스트를 용이하게 수행할 수 있습니다.
//필드 주입: 필드 주입을 사용할 경우, 리플렉션(Reflection)을 사용하거나 스프링 테스트 컨텍스트를 사용해야만 의존성을 주입할 수 있어, 테스트가 복잡해질 수 있습니다.