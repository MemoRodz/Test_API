package com.gmorodz.test.service;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import com.gmorodz.test.model.Address;
import com.gmorodz.test.model.User;

import javax.crypto.Cipher;
// import javax.crypto.spec.SecretKeySpec;

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

        // User 2
        User user2 = new User("carlo@mail.org", "magnocarlo", "+52 555 123 4567", "hash_password_2", "BBBS990202YYY", addresses1);
        users.add(user2);
        taxIds.add(user2.tax_id);

        // User 3 - Usuario con password encriptada AES256 para prueba de login
        User user3 = new User("magno@mail.net", "carlomagno", "+1 555 987 6543", "mipassword123", "AARR990101XXX", addresses1);
        user3.setPassword(encriptarAES256(user3.getPassword(), "miClaveSecreta32bytes123"));    // Encriptación de password AES256 para usuario con dato duro.
        users.add(user3);
        taxIds.add(user3.tax_id);
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
        
        // Remover password de respuesta
        result.forEach(u -> u.setPassword(null));
        return result;
    }

    // POST /users
    public User createUser(User user) {
        if (taxIds.contains(user.tax_id)) {
            throw new RuntimeException("tax_id debe ser único");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new RuntimeException("Password es requerido");
        }

        // TODO: Validar phone AndresFormat

        // Encriptar password
        user.setPassword(encriptarAES256(user.getPassword(), "miClaveSecreta32bytes123")); 
        user.id = UUID.randomUUID().toString(); // Generar ID único
        users.add(user);
        taxIds.add(user.tax_id);
        user.setPassword(null); // Remover de respuesta
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
        return switch (field.toLowerCase()) {  // Case-insensitive
        case "id" -> u.getId();  // Agrega id
        case "email" -> u.getEmail();
        case "name" -> u.getName();
        case "phone" -> u.getPhone();
        case "tax_id" -> u.getTaxId();
        case "created_at" -> u.getCreatedAt();
        default -> "";
        };
    }

    private boolean matchesFilter(User u, String attr, String op, String value) {
        String fieldValue = getFieldValue(u, attr);
        String val = value.toLowerCase();  // Case-insensitive
        return switch (op) {
            case "co" -> fieldValue.contains(val);
            case "eq" -> fieldValue.equals(val);
            case "sw" -> fieldValue.startsWith(val);
            case "ew" -> fieldValue.endsWith(val);
            default -> false;
        };
    }

        private String encriptarAES256(String plainPassword, String secretKey) {
        if (plainPassword == null) {
        throw new IllegalArgumentException("Password no puede ser null");
        }
        try {
            SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(plainPassword.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error encriptación AES256", e);
        }
    }
}
