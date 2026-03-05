package com.gmorodz.test.service;

import com.gmorodz.test.model.Address;
import com.gmorodz.test.model.User;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        // Usamos el constructor real (lista en memoria)
        userService = new UserService();
    }

    @Test
    void createUser_deberiaAgregarUsuarioNuevo() {
        // Arrange
        User nuevo = new User(
            "junit@mail.com",
            "JUnit User",
            "1234567890",
            "pass123",
            "JUNI010101AAA",
            List.of(new Address(10L, "test", "street test", "MX"))
        );

        // Act
        User creado = userService.createUser(nuevo);

        // Assert
        assertNotNull(creado.getId());
        assertEquals("junit@mail.com", creado.getEmail());
        assertEquals("JUnit User", creado.getName());
        // Password debe estar oculto (null en respuesta)
        assertNull(creado.getPassword());
    }

    @Test
    void createUser_conTaxIdDuplicado_debeLanzarExcepcion() {
        // Arrange: reutilizamos un tax_id ya cargado en inicializarUsuarios()
        User duplicado = new User(
            "otro@mail.com",
            "Otro User",
            "1234567890",
            "pass123",
            "AARR990101XXX", // ya existe en tus usuarios iniciales
            List.of(new Address(10L, "test", "street test", "MX"))
        );

        // Act + Assert
        RuntimeException ex = assertThrows(
            RuntimeException.class,
            () -> userService.createUser(duplicado)
        );
        assertTrue(ex.getMessage().contains(" ya existe"));
    }
}