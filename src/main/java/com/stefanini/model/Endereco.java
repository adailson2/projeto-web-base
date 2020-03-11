package com.stefanini.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "TB_ENDERECO")
public class Endereco implements Serializable {

    /**
     * Serializacao da Classe
     */
    private static final long serialVersionUID = 1L;
    /**
     * ID da Tabela
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CO_SEQ_ENDERECO")
    private Long id;

    @Column(name = "DS_CEP")
    private String cep;

    @Column(name = "CO_UF")
    private String uf;

    @Column(name = "DS_CIDADE")
    private String localidade;

    @Column(name = "DS_BAIRRO")
    private String bairro;

    @Column(name = "DS_COMPLEMENTO")
    private String complemento;

    @Column(name = "DS_LOGRADOURO")
    private String logradouro;

    /**
     * Unidirecional
     * Somente Pessoa acessa endereco
     */
    @Column(name = "CO_SEQ_PESSOA")
    private Long idPessoa;

    public Endereco() {
    }

    /**
     * Construtor da Classe, Obrigando receber todos os parametros
     * @param cep
     * @param uf
     * @param localidade
     * @param bairro
     * @param complemento
     * @param logradouro
     * @param idPessoa
     */
    public Endereco(@NotNull String cep, @NotNull String uf, @NotNull String localidade, @NotNull String bairro, @NotNull String complemento, @NotNull String logradouro, @NotNull Long idPessoa) {
        super();
        this.cep = cep;
        this.uf = uf;
        this.localidade = localidade;
        this.bairro = bairro;
        this.complemento = complemento;
        this.logradouro = logradouro;
        this.idPessoa = idPessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", cep='" + cep + '\'' +
                ", uf='" + uf + '\'' +
                ", localidade='" + localidade + '\'' +
                ", bairro='" + bairro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", idPessoa=" + idPessoa +
                '}';
    }
}
