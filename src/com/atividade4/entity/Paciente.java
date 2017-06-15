package com.atividade4.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="paciente", schema="clinica")
public class Paciente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="cpf_paciente")
	private String pacienteCpf;
	
	@Column(name="nome")
	private String nome;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dt_nascimento")
	private Date dtNascimento;
	
	@Column(name="telefone")
	private String telefone;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="agenda_paciente", joinColumns=@JoinColumn(name="cpf_paciente"), inverseJoinColumns=@JoinColumn(name="id_agenda"))
	private List<Agenda> listaAgenda = new ArrayList<Agenda>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="patient")
	private List<Procedimento> listaProcedimento = new ArrayList<Procedimento>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="patient")
	private List<MatMed> equipamento = new ArrayList<MatMed>();
	
	

	public String getPacienteCpf() {
		return pacienteCpf;
	}

	public void setPacienteCpf(String pacienteCpf) {
		this.pacienteCpf = pacienteCpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Agenda> getListaAgenda() {
		return listaAgenda;
	}

	public void setListaAgenda(List<Agenda> listaAgenda) {
		this.listaAgenda = listaAgenda;
	}

	public List<Procedimento> getListaProcedimento() {
		return listaProcedimento;
	}

	public void setListaProcedimento(List<Procedimento> listaProcedimento) {
		this.listaProcedimento = listaProcedimento;
	}

	public List<MatMed> getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(List<MatMed> equipamento) {
		this.equipamento = equipamento;
	}

	
}
