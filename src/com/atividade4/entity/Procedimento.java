package com.atividade4.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="procedimento", schema="consultorio")
public class Procedimento implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_procedimento")
	private int id;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="valor")
	private double preco;
	
	@ManyToOne
	@JoinColumn(name="cpf_paciente")
	private Paciente pacienteId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Paciente getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Paciente pacienteId) {
		this.pacienteId = pacienteId;
	}
	
}
