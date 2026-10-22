package domain.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Usuario {
    private Long id;
    private String name;
    private String email;
    private Perfil perfil;

    public Usuario(Long id, String name, String email, Perfil perfil){
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("O nome de usuário não pode ser vazio.");
        }
        if (email == null || !email.contains("@")){
            throw new IllegalArgumentException("Insira um e-mail válido.");
        }

        this.id = id;
        this.name = name;
        this.email = email;
        this.perfil = Objects.requireNonNull(perfil, "O perfil é obrigatório.");
    }



}
