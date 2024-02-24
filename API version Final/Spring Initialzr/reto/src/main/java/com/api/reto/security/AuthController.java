package com.api.reto.security;

import com.api.reto.dto.IncidenciaDTO;
import com.api.reto.dto.PersonalDTO;
import com.api.reto.models.IncidenciasEntity;
import com.api.reto.models.PerfilesEntity;
import com.api.reto.models.PersonalEntity;
import com.api.reto.repositories.IIncidenciasRepository;
import com.api.reto.repositories.IPerfilRepository;
import com.api.reto.repositories.IPersonalRepository;
import com.api.reto.repositories.IUsuarioApiRepository;
import com.api.reto.services.basics.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IncidenciaService incidenciaService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private IIncidenciasRepository incidenciasRepository;
    @Autowired
    private IPerfilRepository perfilRepository; // Utiliza IPerfilRepository para operaciones de perfil

    @Autowired
    private IPersonalRepository personalRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUsuarioApiRepository usuarioApiRepository;

    @PostMapping("/login")
    public ResponseEntity<DevolverTodoDTO> login(@RequestBody LoginData loginData) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginData.getUsername(), loginData.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(authentication);

        UsuariosApiEntity usuarioApi = usuarioApiRepository.findByUsername(loginData.getUsername());
        if (usuarioApi == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        DevolverTodoDTO devolver = new DevolverTodoDTO();
        PerfilesEntity perfil = perfilRepository.findByEducantabria(loginData.getUsername());
        List<IncidenciaDTO> listaAMeter = new ArrayList<>();
        Integer personalId = null;
        String nombre = null;
        if (perfil != null) {
            personalId = perfil.getPersonalId();
            PersonalEntity persona = personalRepository.findById(personalId).orElse(null);
            nombre = persona != null ? persona.getNombre() : null;
            List<IncidenciasEntity> listaIncidencias = incidenciaService.getIncidencias();
            for (IncidenciasEntity inci : listaIncidencias) {
                if (inci.getCreadorId() != null && inci.getCreadorId().getId() == personalId) {
                    IncidenciaDTO incidenciaAMeter = new IncidenciaDTO(inci);
                    incidenciaAMeter.setCreadorId(inci.getCreadorId() != null ? inci.getCreadorId().getId() : null);
                    incidenciaAMeter.setEquipoId(inci.getEquipoId() != null ? inci.getEquipoId().getId() : null);
                    incidenciaAMeter.setResponsableId(inci.getResponsableId() != null ? inci.getResponsableId().getId() : null);
                    incidenciaAMeter.setSubtipoId(inci.getSubtipoId() != null ? inci.getSubtipoId().getId() : null);
                    listaAMeter.add(incidenciaAMeter);
                }
            }
        }
        devolver.setId(personalId);
        devolver.setListaIncidencias(listaAMeter);
        devolver.setToken(new AuthTokenResponse(token));
        devolver.setNombre(nombre);
        return ResponseEntity.ok().body(devolver);
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistroUsuarioDto usuarioDto) {
        // Verificar si el usuario ya existe
        if (usuarioApiRepository.findByUsername(usuarioDto.getEducantabria()) != null) {
            return ResponseEntity.badRequest().body("Error: El nombre de usuario ya está en uso!");
        }

        // Crear una nueva instancia de PerfilesEntity con la información proporcionada
        UsuariosApiEntity nuevoPerfil = new UsuariosApiEntity();
        nuevoPerfil.setUsername(usuarioDto.getEducantabria());
        nuevoPerfil.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));
        nuevoPerfil.setRol(usuarioDto.getPerfil());

        // Guardar el nuevo perfil en la base de datos
        usuarioApiRepository.save(nuevoPerfil);

        return ResponseEntity.ok("Usuario registrado exitosamente");
    }
}