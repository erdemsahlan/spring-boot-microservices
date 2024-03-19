package com.homeproject.homeprojectfortrainingspringframework.utilities;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    private static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public static <D,T> D map(final T entity,Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    public static <D,T> List<D> mapAll(final Collection<T> entityList, Class<D> outClass){
        return entityList.stream().map(e -> map(e,outClass)).collect(Collectors.toList());
    }

    public static <S,D> D map(final S source,D destination) {
        modelMapper.map(source, destination);
        return destination;
    }
}
