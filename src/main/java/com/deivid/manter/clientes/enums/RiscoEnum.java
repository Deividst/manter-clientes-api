package com.deivid.manter.clientes.enums;

import com.deivid.manter.clientes.util.MessagesUtil;

import java.util.Arrays;

/**
 * @author Deivid Thomé - 14/11/2019
 */
public enum RiscoEnum {

    A(1, "A"),B(2, "B"),C(3 , "C");

    RiscoEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    private int code;
    private String value;

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static RiscoEnum getValue(int code) {
        return Arrays.stream(RiscoEnum.values())
                .filter(tipo -> tipo.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        MessagesUtil.getMessage("erro.argumento.invalido.tipo.dado", code)));
    }
}
