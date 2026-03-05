package com.gmorodz.test.model;


import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
        this.created_at = ZonedDateTime.now(ZoneId.of("Indian/Antananarivo")) // Madagascar timezone
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    // Getters (sin password)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getTaxId() { return tax_id; }
    public void setTaxId(String tax_id) { this.tax_id = tax_id; }
    public String getCreatedAt() { return created_at; }
    public void setCreatedAt(String created_at) { this.created_at = created_at; }
    public List<Address> getAddresses() { return addresses; }
    public void setAddresses(List<Address> addresses) { this.addresses = addresses; }

    // Para limpiar password
    public void clearPassword() { this.password = null; }
    // Solo para servicio interno (no expone en JSON) 
    @JsonIgnore  // Oculta password en JSON responses
    public String getPassword() { return password; }
    // Setters
    public void setPassword(String password) { this.password = password; }
}