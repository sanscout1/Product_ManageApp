package com.ssg.productmanager.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long pno;

    @Size(min = 1,max = 100)
    private String name;

    @PositiveOrZero
    @Max(1000000)
    private int price;

    @PositiveOrZero
    @Max(99999)
    private int amount;
}
// Bean Validation API
//@NotNull: 필드 값이 null이 아니어야 함을 지정합니다.
//@NotEmpty: 필드 값이 null이 아니고, 길이가 0보다 큰 문자열이거나 컬렉션이 비어 있지 않아야 함을 지정합니다.
//@NotBlank: 문자열 필드가 null이 아니고, 공백이 아니며, 하나 이상의 공백이 아닌 문자를 포함해야 함을 지정합니다.
//@Size(min=, max=): 문자열, 컬렉션, 배열의 크기가 지정된 범위 내에 있어야 함을 지정합니다.
//@Min(value): 숫자 값이 지정된 최소값 이상이어야 함을 지정합니다.
//@Max(value): 숫자 값이 지정된 최대값 이하이어야 함을 지정합니다.
//@DecimalMin(value, inclusive): 숫자 값이 지정된 최소값 이상이어야 함을 지정합니다. inclusive는 경계값을 포함할지 여부를 지정합니다.
//@DecimalMax(value, inclusive): 숫자 값이 지정된 최대값 이하이어야 함을 지정합니다. inclusive는 경계값을 포함할지 여부를 지정합니다.
//@Positive: 숫자 값이 양수여야 함을 지정합니다.
//@PositiveOrZero: 숫자 값이 0 이상이어야 함을 지정합니다.
//@Negative: 숫자 값이 음수여야 함을 지정합니다.
//@NegativeOrZero: 숫자 값이 0 이하이어야 함을 지정합니다.
//@Email: 문자열이 이메일 주소 형식이어야 함을 지정합니다.
//@Pattern(regex=, flags=): 문자열이 지정된 정규 표현식과 일치해야 함을 지정합니다.
//@Future: 날짜와 시간이 현재보다 미래여야 함을 지정합니다.
//@FutureOrPresent: 날짜와 시간이 현재 또는 미래여야 함을 지정합니다.
//@Past: 날짜와 시간이 현재보다 과거여야 함을 지정합니다.
//@PastOrPresent: 날짜와 시간이 현재 또는 과거여야 함을 지정합니다.
