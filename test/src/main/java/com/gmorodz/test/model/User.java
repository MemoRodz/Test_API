package com.gmorodz.test.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.Pattern; // Para validaciones

public class User {
    public String id = UUID.randomUUID().toString();
    public String email;
    public String name;
    @Pattern(regexp = "^\\+?\\d{10,15}$", message = "Teléfono inválido: 10 dígitos min, con código país opcional") // Pendiente AndresFormat
    public String phone;
    private String password; // Privado, se encripta
    @Pattern(regexp = "^[A-Z&Ñ]{3,4}\\d{6}[A-Z0-9]{3}$", message = "tax_id debe ser formato RFC") // Validación RFC básico
    public String tax_id;
    public String created_at;
    public List<Address> addresses;

    // Constructor vacío
    public User() {}

    // Constructor con params
    public User(String email, String name, String phone, String password, String tax_id, List<Address> addresses) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.password = password; // Se encriptará al guardar
        this.tax_id = tax_id;
        this.addresses = addresses;
        this.created_at = LocalDateTime.now() // Madagascar timezone pendiente
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    // Getters (sin password)
    public String getPassword() { return password; } // Solo para login interno
    // Setters
    public void setPassword(String password) { this.password = password; }
}