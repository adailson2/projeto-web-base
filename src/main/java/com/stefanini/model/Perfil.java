package com.stefanini.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Entity
@Table(name = "TB_PERFIL")
public class Perfil implements Serializable {

    /**
     * Serializacao da Classe
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID da Tabela
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CO_SEQ_PERFIL")
    private Long id;

    /**
     * Nome do perfil
     */
    @NotNull
    @Column(name = "NO_PERFIL")
    private String nome;

    /**
     * Descrição do perfil
     */
    @NotNull
    @Column(name = "DS_PERFIL")
    private String descricao;

    /**
     * Data e hora da inclusão do perfil
     */
    @Column(name = "DT_HORA_INCLUSAO")
    @NotNull
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH:mm:ss.SS")
    private LocalDateTime dataHoraInclusao;

    /**
     * Data e hora da alteração do perfil
     */
    @Column(name = "DT_HORA_ALTERACAO")
    @JsonFormat (shape = STRING, pattern = "yyyy-MM-dd HH:mm:ss.SS")
    private LocalDateTime dataHoraAlteracao;

    /**
     * Mapeamento de Pessoa
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "perfils")
    private Set<Pessoa> pessoas;

    public Perfil() {
    }

    public Perfil(@NotNull String nome, @NotNull String descricao, @NotNull LocalDateTime dataHoraInclusao, @NotNull LocalDateTime dataHoraAlteracao, @NotNull Set<Pessoa> pessoas) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataHoraInclusao = dataHoraInclusao;
        this.dataHoraAlteracao = dataHoraAlteracao;
        this.pessoas = pessoas;
    }

    public Set<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(Set<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHoraInclusao() {
        return dataHoraInclusao;
    }

    public void setDataHoraInclusao(LocalDateTime dataHoraInclusao) {
        this.dataHoraInclusao = dataHoraInclusao;
    }

    public LocalDateTime getDataHoraAlteracao() {
        return dataHoraAlteracao;
    }

    public void setDataHoraAlteracao(LocalDateTime dataHoraAlteracao) {
        this.dataHoraAlteracao = dataHoraAlteracao;
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataHoraInclusao=" + dataHoraInclusao +
                ", dataHoraAlteracao=" + dataHoraAlteracao +
                ", pessoas=" + pessoas +
                '}';
    }
}
