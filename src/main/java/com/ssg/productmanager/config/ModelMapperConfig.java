package com.ssg.productmanager.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean  // 해당 메서드의 실행 결과로 반환 된 객체를 스프링의 빈으로 등록시키는 역할
    public ModelMapper getMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}

// 여기는 bean 등록을 다양한 설정의 메서드를 하여서 선택해서 고를 수 있다
// 그것을 구분하는 것은 qualifer로 하면된다.

//@Configuration 어노테이션을 사용하여 클래스를 정의하는 이유는, 해당 클래스가 스프링의
//애플리케이션 컨텍스트에서 빈 정의를 제공하는 구성 클래스임을 나타내기 위함입니다.
//즉, 이는 스프링 프레임워크에게 이 클래스 안에 하나 이상의 @Bean 어노테이션이 붙은 메소드가 있으며,
//이 메소드들을 통해 애플리케이션 컨텍스트에 빈을 등록할 수 있음을 알려줍니다

//@Bean 어노테이션을 사용해 메서드를 설정함으로써, 해당 메서드가 생성하는 객체(ModelMapper 인스턴스)는
//스프링의 빈 컨테이너에 등록됩니다. 이후, 스프링 프레임워크는 이 빈(bean)을
//필요로 하는 다른 컴포넌트에 자동으로 의존성 주입(Dependency Injection)을 통해 제공합니다.
// 의존성 주입의 규칙을 우리가 재설정해서 메서드로 돌려주는 것이다
//@Configuration 클래스에서 @Bean 어노테이션이 붙은 메소드를 통해 빈을 정의하고 구성하는 과정은
// 의존성 주입(Dependency Injection, DI)의 규칙을 개발자가 재설정하고 제어할 수 있게 하는 것입니다.

//의존성 주입 과정
//빈 등록: 애플리케이션 시작 시, 스프링 컨테이너는 @Configuration 클래스를 찾아 @Bean
//어노테이션이 붙은 모든 메소드를 실행합니다.
//이 과정에서 getMapper() 같은 메소드가 실행되어 ModelMapper 객체가 생성되고, 해당 객체는
//스프링 컨테이너에 빈으로 등록됩니다.
//
//의존성 검색 및 주입: 다른 컴포넌트에서 ModelMapper 타입의 객체가 필요할 경우
// (예를 들어, @Autowired를 사용하여 주입 요청한 경우), 스프링 컨테이너는 앞서 등록된 ModelMapper
//타입의 빈을 찾아 해당 컴포넌트에 주입합니다.


// 개발자가 정의한 모든 @Bean 메소드를 실행하여 각각의 빈을 생성하고 관리합니다. 빈 간의 충돌이나
// 선택이 필요한 상황에서는 @Qualifier와 @Primary 같은 메커니즘을 통해 해결할 수 있습니다.


//@Configuration 클래스 내에서 여러 개의 @Bean을 설정하는 경우는 실제로 매우 흔합니다.
//이러한 접근 방식은 애플리케이션의 구성을 관리하는 데 있어 유연성과 조직성을 제공합니다.
//
//서로 다른 용도의 빈 설정
//하나의 @Configuration 클래스에서 서로 다른 용도를 가진 여러 빈을 정의할 수 있습니다.
//예를 들어, 데이터베이스 연결, 서비스 레이어, 메시징 서비스 등 다양한 구성요소를 위한 빈을 설정할 수 있습니다.
//이러한 방식으로, 관련된 설정들을 한 곳에서 관리하여 애플리케이션의 구조를 명확하게 유지할 수 있습니다.
//
//동일한 타입의 빈을 다르게 설정
//동일한 타입의 객체에 대해 다른 구성이나 의존성을 주입해야 하는 경우도 있습니다.
// 예를 들어, 애플리케이션이 다양한 데이터 소스를 사용하거나, 특정 타입의 빈을 여러 환경(
//개발, 테스트, 프로덕션 등)에 맞게 다르게 구성해야 할 때 여러 @Bean 메소드를 정의할 수 있습니다.
//
//조건부 빈 설정
//@Profile 어노테이션을 사용하여 특정 프로필이 활성화됐을 때만 빈이 생성되도록 설정할 수 있습니다.
//이를 통해 개발, 테스트, 프로덕션 환경 등에서 서로 다른 빈 구성을 쉽게 관리할 수 있습니다.
//
//라이브러리 또는 프레임워크 확장
//애플리케이션이 사용하는 외부 라이브러리나 프레임워크의 컴포넌트를 확장하거나 조정해야 할 경우에도
//여러 빈을 설정할 수 있습니다. 이는 기존 컴포넌트의 기능을 커스터마이즈하거나, 추가 기능을 제공하기
//위해 필요할 수 있습니다.
//
//빈의 재사용 및 조합
//다른 빈을 조합하거나, 특정 빈에 의존하는 새로운 빈을 생성할 때도 여러 @Bean 메소드를 사용할 수 있습니다.
//이는 복잡한 의존성 구조를 가진 애플리케이션에서 특히 유용할 수 있습니다.