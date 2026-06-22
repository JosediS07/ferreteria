package com.hibernate.ferreteria.seguridad;


import com.hibernate.ferreteria.repositorios.UsuariosRepositorio;
import com.hibernate.ferreteria.servicios.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.HttpSecurityDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity //Habilita las caracteristicas de Spring Security para que SecurityFilterChain sustituya la config por defecto
@RequiredArgsConstructor //Para que userService tenga su contructor automaticamente gracias a lombok
public class SecurityConfig {

    private final UsuarioService userService;

    //Metodo para comparar y comprobar contraseañas
    @Bean
    public PasswordEncoder codificaPass(){
        return new BCryptPasswordEncoder();
    }

    //Metodo que lanza una excepcion en caso de fallo con las autentificaciones
    @Bean
    public AuthenticationManager autenticacion(AuthenticationConfiguration authConfig)throws Exception{
            return authConfig.getAuthenticationManager();

    }


    @Bean
    public SecurityFilterChain securityChain(HttpSecurity http, AuthenticationManager authManager)throws Exception{
        http.csrf(csrf ->csrf.disable()) //Desactivamos csrf (protecion de seguridad de algunos navegadores a peticiones http)
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/api/auth/**").permitAll() //Permite acceso publico a las rutas de autenticacion
                                .requestMatchers("/api/articulos/**").hasAnyRole("ADMIN","USER") //Este solo permite a los admin o los user
                                .anyRequest().authenticated()
                        )
                .authenticationManager(authManager) //Pasamos el authManager que verificara el usuario y contraseña
                .userDetailsService(userService) //Con este metodo permitimos que se puedan buscar los detalles del usuario logeado
                .formLogin(form -> form.permitAll())
                .httpBasic(basic->{});
        return http.build(); //Retornamos el resultado de todo lo que hemos establecido
    }



}
