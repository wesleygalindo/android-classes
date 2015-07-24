package br.com.wesleygalindo.activitysample;

import java.io.Serializable;

/**
 * Created by wesley on 7/24/15.
 */
public class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nome;
    private String matricula;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
