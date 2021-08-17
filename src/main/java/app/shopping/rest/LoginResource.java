package app.shopping.rest;


import app.shopping.entity.dto.AccountDTO;
import app.shopping.entity.dto.LoginRequest;
import app.shopping.entity.dto.Response;
import app.shopping.rest.exception.LoginFailedException;
import app.shopping.rest.exception.ResourceNotFoundException;
import app.shopping.rest.exception.UsernameNotFound;
import app.shopping.services.AccountService;
import app.shopping.services.jwt.JwtTokenUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginResource {


    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final AccountService accountService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        // Xác thực từ username và password.
        Authentication authentication;
        AccountDTO accountDTO = accountService.findByUsername(loginRequest.getUsername());
        if (accountDTO == null) {
            throw new UsernameNotFound("Username " + loginRequest.getUsername() + " does not exist");
        }
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (Exception e) {
            throw new LoginFailedException("Username or password wrong!");
        }

        // Trả về jwt cho người dùng.
        String jwt = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());
        Response response = new Response();
        response.setJwt(jwt);
        response.setMessage("Login Success!");
        return ResponseEntity.ok(response);
    }

}
