package com.hibernate.ferreteria;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//Clase para generar la contraseña encriptada
public class GeneradorPassword {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("jd1234"));
    }
}
