package com.stefanini.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "TB_PESSOA_PERFIL")
public class PessoaPerfil implements Serializable {

    /**
     * Serializacao da Classe
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID da Tabela
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CO_SEQ_PESSOA_PERFIL")
    private Long id;

    /**
     * Relacionamento de perfil
     */
    @ManyToOne
    @JoinColumn(name = "CO_SEQ_PERFIL", referencedColumnName = "CO_SEQ_PERFIL", nullable = false)
    private Perfil perfil;

    /**
     * Relacionamento de pessoa
     */
    @ManyToOne
    @JoinColumn(name = "CO_SEQ_PESSOA", referencedColumnName = "CO_SEQ_PESSOA", nullable = false)
    private Pessoa pessoa;

    public PessoaPerfil() {
		// TODO Auto-generated constructor stub
	}

    public PessoaPerfil(@NotNull Perfil perfil, @NotNull Pessoa pessoa) {
        this.perfil = perfil;
        this.pessoa = pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
