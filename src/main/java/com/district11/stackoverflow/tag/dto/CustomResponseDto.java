package com.district11.stackoverflow.tag.dto;

import com.district11.stackoverflow.dto.PageInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CustomResponseDto {
    @NonNull
    private String name;

    private List<OneTagResponseDto> data;
    private PageInfo pageInfo;

    public CustomResponseDto(String name,  List<OneTagResponseDto> data, Page page) {
        this.name = name;
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber()+1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}

