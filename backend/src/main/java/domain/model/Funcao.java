package domain.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Funcao {
    private Long id;
    private String nome;
    private Ministerio ministerio;

    public Funcao(Long id, String nome, Ministerio ministerio){
        if (nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Insira um nome válido.");
        }

        this.id = id;
        this.nome = nome;
        this.ministerio = Objects.requireNonNull(ministerio, "O ministério é obrigatório.");
    }
}
