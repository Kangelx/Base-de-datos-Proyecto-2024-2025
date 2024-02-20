package com.api.reto.security;

import com.api.reto.models.PerfilesEntity;
import com.api.reto.repositories.IPerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private IPerfilRepository perfilRepository; // Utiliza IPerfilRepository para operaciones de perfil

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginData loginData) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginData.getUsername(), loginData.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(authentication);

        return ResponseEntity.ok(new AuthTokenResponse(token));
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('administrador')")
    public ResponseEntity<?> registerUser(@RequestBody RegistroUsuarioDto usuarioDto) {
        // Verificar si el usuario ya existe
        if (perfilRepository.findByEducantabria(usuarioDto.getEducantabria()) != null) {
            return ResponseEntity.badRequest().body("Error: El nombre de usuario ya está en uso!");
        }

        // Crear una nueva instancia de PerfilesEntity con la información proporcionada
        PerfilesEntity nuevoPerfil = new PerfilesEntity();
        nuevoPerfil.setPersonalId(usuarioDto.getPersonalId()); // Asegúrate de que este ID sea único
        nuevoPerfil.setEducantabria(usuarioDto.getEducantabria());
        nuevoPerfil.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));
        nuevoPerfil.setPerfil(usuarioDto.getPerfil()); // Asegúrate de que el valor del perfil sea válido

        // Guardar el nuevo perfil en la base de datos
        perfilRepository.save(nuevoPerfil);

        return ResponseEntity.ok("Usuario registrado exitosamente");
    }
}