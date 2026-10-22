package domain.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Evento {
    private Long id;
    private String titulo;
    private LocalDateTime data;

    public Evento(Long id, String titulo, LocalDateTime data){

        if (titulo == null || titulo.isBlank()){
            throw new IllegalArgumentException("Insira um título válido.");
        }
        if (data == null || data.isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Insira uma data futura válida.");
        }

        this.id = id;
        this.titulo = titulo;
        this.data = data;
    }
}
