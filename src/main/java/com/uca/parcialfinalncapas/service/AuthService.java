package com.uca.parcialfinalncapas.service;

import com.uca.parcialfinalncapas.dto.request.AuthRequest;
import com.uca.parcialfinalncapas.dto.response.AuthResponse;

public interface AuthService {
    /**
     * Autentifica un usuario y genera un token JWT.
     *
     * @param authRequestDto los datos de autenticación del usuario
     * @return el token JWT si la autenticación es exitosa, o un mensaje de error si falla
     */
    AuthResponse authenticate(AuthRequest authRequestDto);
}
