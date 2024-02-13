package com.example.bankingapplication.Service;

import com.example.bankingapplication.Dto.LoginResponseDto;
import com.example.bankingapplication.Model.Roles;
import com.example.bankingapplication.Repo.RoleRepository;
import com.example.bankingapplication.Repo.UserRepository;
import com.example.bankingapplication.Response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.bankingapplication.Model.Users;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    public final ModelMapper modelMapper;

    public String registerUser(String username, String password){
        String encodedPassword = passwordEncoder.encode(password);
        Users users = new Users();
        users.setUsername(username);
        users.setPassword(encodedPassword);
        userRepository.save(users);
        Roles roles = new Roles();
        roles.setAuthority("USER");
        roles.setUserId(users.getUserId());
        roleRepository.save(roles);
        return "Your username is  ".concat(users.getUsername());
    }


    public String updateRoles(Integer roleId, String authority){
        Optional<Roles> rolesOptional = roleRepository.findRolesByRoleId(roleId);
        if(rolesOptional.isEmpty()){
            throw new RuntimeException("Roles Not Found");
        }else {
            Roles roles =rolesOptional.get();
            roles.setAuthority(authority.toUpperCase());
            roleRepository.save(roles);
            return "Your roles is ".concat(authority);
        }
    }

    public LoginResponseDto loginUser(String username, String password){

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDto(userRepository.findByUsername(username).get(), token);

        } catch(AuthenticationException e){
            return new LoginResponseDto(null, "");
        }
    }

}
