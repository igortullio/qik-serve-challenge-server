package com.igortullio.server.adapter.mapper;

import com.igortullio.server.adapter.dto.TotalDto;
import com.igortullio.server.core.domain.Total;
import org.mapstruct.Mapper;

@Mapper
public interface TotalMapper {

    TotalDto domainToDto(Total total);

}
