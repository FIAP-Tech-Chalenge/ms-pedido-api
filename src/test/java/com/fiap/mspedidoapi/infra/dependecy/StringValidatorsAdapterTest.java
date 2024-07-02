package com.fiap.mspedidoapi.infra.dependecy;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class StringValidatorsAdapterTest {

    @Test
    public void deveValidarUUIDValido() {
        String validUuid = "123e4567-e89b-12d3-a456-426614174000";
        boolean isValid = StringValidatorsAdapter.isValidUUID(validUuid);

        assertThat(isValid).isTrue();
    }

    @Test
    public void deveInvalidarUUIDInvalido() {
        String invalidUuid = "invalid-uuid";
        boolean isValid = StringValidatorsAdapter.isValidUUID(invalidUuid);

        assertThat(isValid).isFalse();
    }

    @Test
    public void deveInvalidarUUIDNulo() {
        String nullUuid = null;
        boolean isValid = StringValidatorsAdapter.isValidUUID(nullUuid);

        assertThat(isValid).isFalse();
    }

    @Test
    public void deveConverterStringParaUUIDValido() {
        String validUuid = "123e4567-e89b-12d3-a456-426614174000";
        UUID uuid = StringValidatorsAdapter.toUUID(validUuid);

        assertThat(uuid).isNotNull();
        assertThat(uuid.toString()).isEqualTo(validUuid);
    }

    @Test
    public void deveRetornarNullParaUUIDInvalido() {
        String invalidUuid = "invalid-uuid";
        UUID uuid = StringValidatorsAdapter.toUUID(invalidUuid);

        assertThat(uuid).isNull();
    }

//    @Test
//    public void deveRetornarNullParaUUIDNulo() {
//        String nullUuid = null;
//        UUID uuid = StringValidatorsAdapter.toUUID(nullUuid);
//
//        assertThat(uuid).isNull();
//    }
}
