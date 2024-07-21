package com.ecommerce.sportscentre.service;

import com.ecommerce.sportscentre.entity.Type;
import com.ecommerce.sportscentre.model.BrandResponse;
import com.ecommerce.sportscentre.model.TypeResponse;
import com.ecommerce.sportscentre.repository.TypeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2

public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public List<TypeResponse> getAllTypes() {
        log.info("Fetching All Types.......");
        List<Type> typeList = typeRepository.findAll();
        List<TypeResponse> typeResponses = typeList.stream()
                .map(this::convertToTypeResponse)
                .toList();
        log.info("Fetched All Types........");
        return null;
    }

    private TypeResponse convertToTypeResponse(Type type) {
        return TypeResponse.builder()
                .id(type.getId())
                .name(type.getName())
                .build();
    }
}
