package domain.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Ministerio {
    private Long id;
    private String nome;
    private String lider;

    public Ministerio(Long id, String nome, String lider){
        if (nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Insira um nome válido.");
        }

        this.id = id;
        this.nome = Objects.requireNonNull(nome, "O Ministério é obrigatório.");
        this.lider = lider;
    }
}
