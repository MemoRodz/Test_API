package com.gmorodz.test.controller;

// TODO: Agregar pruebas unitarias para UserController

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
// TODO: Correguir excepción por importar WebMvcTest, existe referencia pero no se encuentra la clase, revisar dependencias.
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.gmorodz.test.service.UserService;

//@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void getUsers_debeResponder200() throws Exception {
        mockMvc.perform(get("/users"))
               .andExpect(status().isOk());
    }    
}
