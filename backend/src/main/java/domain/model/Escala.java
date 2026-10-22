package domain.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class Escala {
    private Long id;
    private Evento evento;
    private Ministerio ministerio;
    private StatusEscala statusEscala;
    private List<Participacao> participacoes;

    public Escala(Long id, Evento evento, Ministerio ministerio){

        this.evento = Objects.requireNonNull(evento, "Insira um evento válido.");
        this.ministerio = Objects.requireNonNull(ministerio, "Insira um ministério válido.");
        this.statusEscala = StatusEscala.EM_ANDAMENTO;
        this.participacoes = new ArrayList<Participacao>();
    }

    private void validarAlteracao(StatusEscala status){
        if (status != StatusEscala.EM_ANDAMENTO){
            throw new IllegalArgumentException("Não é possível alterar uma escala que não esteja em andamento.");
        }
    }

    public void adicionarParticipacao(Usuario usuario, Funcao funcao){
        validarAlteracao(this.statusEscala);
        // verifica se o usuário já existe
        boolean usuarioJaEscalado = this.participacoes.stream()
                .anyMatch(p -> p.getUsuario().equals(usuario));

        if (usuarioJaEscalado){
            throw new IllegalArgumentException("Este usuário já está escalado nesta escala.");
        }

        if (funcao.getMinisterio() != this.ministerio){
            throw new IllegalArgumentException("A função precisa corresponder ao ministério.");
        }

        Participacao novaParticipacao = new Participacao(usuario, funcao);
    }

    public void removerParticipacao(Participacao participacao){
        validarAlteracao(this.statusEscala);
        // verifica se o usuário já existe
        boolean usuarioJaEscalado = this.participacoes.stream()
                .anyMatch(p -> p.getUsuario().equals(usuario));

        // se o usuário não existir, lança exceção
        if (!usuarioJaEscalado){
            throw new IllegalArgumentException("Este usuário não existe nesta escala.");
        }

        participacoes.remove(participacao);
    }

}
