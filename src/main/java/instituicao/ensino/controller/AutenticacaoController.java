package instituicao.ensino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import instituicao.ensino.model.dto.DadosTokenJWT;
import instituicao.ensino.model.entity.Usuario;
import instituicao.ensino.model.service.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody Usuario usuario) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha());

        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((UserDetails) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
