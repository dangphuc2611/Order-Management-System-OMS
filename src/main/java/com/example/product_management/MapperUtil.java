package com.example.product_management;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

public class MapperUtil {
    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    public static <S, D> D map(S source, Class<D> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    /**
     * Dùng cho update: copy field từ source sang object target đã tồn tại
     * Skips mapping of 'id' field to prevent overwriting primary keys
     */
    public static <S, T> void mapToExisting(S source, T target) {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(source, target);
        modelMapper.getConfiguration().setSkipNullEnabled(false);
    }

    public static ModelMapper getModelMapper() {
        return modelMapper;
    }
}
