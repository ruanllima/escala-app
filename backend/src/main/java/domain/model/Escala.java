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
        this.id = id;
        this.evento = Objects.requireNonNull(evento, "Insira um evento válido.");
        this.ministerio = Objects.requireNonNull(ministerio, "Insira um ministério válido.");
        this.statusEscala = StatusEscala.EM_ANDAMENTO;
        this.participacoes = new ArrayList<Participacao>();
    }

    public List<Participacao> getParticipacoes() {
        return java.util.Collections.unmodifiableList(this.participacoes);
    }

    private void validarAlteracao(){
        if (this.statusEscala != StatusEscala.EM_ANDAMENTO){
            throw new IllegalStateException("Não é possível alterar uma escala que não esteja em andamento.");
        }
    }

    public void adicionarParticipacao(Usuario usuario, Funcao funcao){
        validarAlteracao();
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
        this.participacoes.add(novaParticipacao);
    }

    public void removerParticipacao(Usuario usuario) {
        validarAlteracao();
        boolean removido = this.participacoes.removeIf(p -> p.getUsuario().equals(usuario));

        if (!removido) {
            throw new IllegalArgumentException("Este usuário não está escalado nesta escala.");
        }
    }

    public void concluir() {
        validarAlteracao();
        this.statusEscala = StatusEscala.CONCLUIDA;
    }

    public void cancelar() {
        validarAlteracao();
        this.statusEscala = StatusEscala.CANCELADA;
    }
 }


