package com.deivid.manter.clientes.converter;

import com.deivid.manter.clientes.enums.RiscoEnum;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

/**
 * Converte Inteiro para {@link com.deivid.manter.clientes.enums.RiscoEnum}
 * @author Deivid - 14/11/2019
 */
public class RiscoEnumConverter implements AttributeConverter<RiscoEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RiscoEnum riscoEnum) {
        return riscoEnum != null ? riscoEnum.getCode() : null;
    }

    @Override
    public RiscoEnum convertToEntityAttribute(Integer i) {
        return Stream.of(RiscoEnum.values())
                .filter(c -> c.getCode() == i)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
