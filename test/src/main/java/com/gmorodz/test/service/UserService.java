package com.gmorodz.test.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gmorodz.test.model.Address;
import com.gmorodz.test.model.User;

@Service
public class UserService {
    private List<User> users = new ArrayList<>(); // Lista en memoria
    private Set<String> taxIds = new HashSet<>(); // Para unicidad

    public UserService() {
        // Inicializar 3 usuarios de ejemplo
        inicializarUsuarios();
    }

    private void inicializarUsuarios() {
        List<Address> addresses1 = Arrays.asList(
            new Address(1L, "workaddress", "street No. 1", "UK"),
            new Address(2L, "homeaddress", "street No. 2", "AU")
        );
        User user1 = new User("user1@mail.com", "user1", "+1 55 555 555 55", "7c4a8d09ca3762af61e59520943dc26494f8941b", "AARR990101XXX", addresses1);
        users.add(user1);
        taxIds.add(user1.tax_id);

        // Repitir para user2 y user3 con datos diferentes
        // TODO: Agrega los otros 2 usuarios
    }

    // Métodos para GET /users (sort y filter)
    public List<User> getUsers(String sortedBy, String filter) {
        List<User> result = new ArrayList<>(users);
        
        if (sortedBy != null && !sortedBy.isEmpty()) {
            result.sort(Comparator.comparing(u -> getFieldValue(u, sortedBy)));
        }
        
        if (filter != null && !filter.isEmpty()) {
            // Parse filter: attr+op+value ej name+co+user
            String[] parts = filter.split("\\+");
            if (parts.length == 3) {
                String attr = parts[0], op = parts[1], value = parts[2];
                result = result.stream()
                    .filter(u -> matchesFilter(u, attr, op, value))
                    .collect(Collectors.toList());
            }
        }
        
        // Remover passwords de respuesta
        result.forEach(u -> u.password = null);
        return result;
    }

    // POST /users
    public User createUser(User user) {
        if (taxIds.contains(user.tax_id)) {
            throw new RuntimeException("tax_id debe ser único");
        }
        // TODO: Encriptar password AES256
        // TODO: Validar phone AndresFormat
        user.id = UUID.randomUUID().toString();
        users.add(user);
        taxIds.add(user.tax_id);
        user.password = null; // Remover de respuesta
        return user;
    }

    // PATCH /users/{id}
    public User updateUser(String id, User partialUser) {
        // Buscar, actualizar campos no null, validar tax_id único si cambia
        // TODO: Implementar
        return null;
    }

    // DELETE /users/{id}
    public void deleteUser(String id) {
        users.removeIf(u -> u.id.equals(id));
        // Remover de taxIds si aplica
    }

    // /login
    public String login(String tax_id, String password) {
        // Buscar por tax_id, comparar password encriptada AES256
        // TODO: Implementar, retornar JWT o token simple si OK
        return "token";
    }

    // Helpers privados para sort/filter
    private String getFieldValue(User u, String field) {
        switch (field) {
            case "email": return u.email;
            case "id": return u.id;
            // etc...
            default: return "";
        }
    }

    private boolean matchesFilter(User u, String attr, String op, String value) {
        String fieldValue = getFieldValue(u, attr);
        return switch (op) {
            case "co" -> fieldValue.contains(value);
            case "eq" -> fieldValue.equals(value);
            case "sw" -> fieldValue.startsWith(value);
            case "ew" -> fieldValue.endsWith(value);
            default -> false;
        };
    }
}
