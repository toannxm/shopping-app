package app.shopping.rest;


import app.shopping.entity.dto.LoginRequest;
import app.shopping.entity.dto.Response;
import app.shopping.services.AccountService;
import app.shopping.services.jwt.JwtTokenUtil;
import lombok.AllArgsConstructor;
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
@RequestMapping("/")
@AllArgsConstructor
public class LoginResource {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil  jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        // Xác thực từ username và password.
        Authentication authentication;
       try {
           authentication = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           loginRequest.getUsername(),
                           loginRequest.getPassword()
                   )
           );
       }catch (Exception e){
           Map<Integer,String> errorMap = new HashMap<>();
           errorMap.put(400,"Tên đăng nhập hoặc mật khẩu không chính xác.");
           return ResponseEntity.badRequest().body(errorMap);
       }

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());
        Response response = new Response();
        response.setJwt(jwt);
        response.setMessage("Bạn đã đăng nhập thành công.");
        return ResponseEntity.ok(response);
    }

}
