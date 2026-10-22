package domain.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Participacao {
    private Usuario usuario;
    private Funcao funcao;
    private StatusParticipacao statusParticipacao;
    private String justificativaRecusa;

    public Participacao(Usuario usuario, Funcao funcao){

        this.usuario = Objects.requireNonNull(usuario, "Insira um usuário válido.");
        this.funcao = Objects.requireNonNull(funcao, "Insira uma funcão válida.");
        this.statusParticipacao = StatusParticipacao.PENDENTE;
        this.justificativaRecusa = null;
    }

    public void confirmar(){
        if (this.statusParticipacao != StatusParticipacao.PENDENTE) {
            throw new IllegalStateException("Apenas participações pendentes podem ser confirmadas.");
        }
        this.statusParticipacao = StatusParticipacao.CONFIRMADO;
    }

    public void recusar(String justificativaRecusa){
        if (this.statusParticipacao != StatusParticipacao.PENDENTE) {
            throw new IllegalStateException("Apenas participações pendentes podem ser confirmadas.");
        }
        if (justificativaRecusa == null || justificativaRecusa.isBlank()){
            throw new IllegalArgumentException("Insira uma justificativa.");
        }

        this.statusParticipacao = StatusParticipacao.RECUSADO;
        this.justificativaRecusa = justificativaRecusa;
    }
}
