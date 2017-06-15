package com.atividade4.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.atividade4.dao.GenericDao;
import com.atividade4.dao.JPAUtil;
import com.atividade4.entity.Agenda;
import com.atividade4.entity.MatMed;
import com.atividade4.entity.Paciente;
import com.atividade4.entity.Procedimento;

public class Main {

	public static void main(String[] args) {

		CrudData();

		buscarPacienteEProcedimentos();

		buscarPacientesEmaterial();

	}

	public static void CrudData() {

		Calendar dtRefTst = Calendar.getInstance();

		Agenda agenda = new Agenda();
		agenda.setDataAgendamento(dtRefTst.getTime());
		agenda.setDescricao("Agenda X");
		agenda.setHoraAgendamento(dtRefTst.getTime());
		
		List<Agenda> listAgenda = new ArrayList();
		listAgenda.add(agenda);	
		

		Paciente paciente = new Paciente();
		paciente.setPacienteCpf("33344455566");
		paciente.setNome("Jose Amadeu");
		paciente.setDtNascimento(dtRefTst.getTime());
		paciente.setTelefone("3144441234");

		List<Paciente> listaPaciente = new ArrayList();
		listaPaciente.add(paciente);


		agenda.setPacientes(listaPaciente);
		paciente.setListaAgenda(listAgenda);

		GenericDao<Agenda> daoAgenda = new GenericDao<Agenda>(Agenda.class);
		daoAgenda.add(agenda);
		
		GenericDao<Procedimento> prcdtDao = new GenericDao<Procedimento>(Procedimento.class);

		Procedimento procedimento = new Procedimento();
		procedimento.setDescricao("Teste 1");
		procedimento.setPacienteId(paciente);
		procedimento.setPreco(5000.00);

		Procedimento procedimento2 = new Procedimento();
		procedimento2.setDescricao("Teste 2");
		procedimento2.setPacienteId(paciente);
		procedimento2.setPreco(10000.00);

		Procedimento procedimento3 = new Procedimento();
		procedimento3.setDescricao("Teste 3");
		procedimento3.setPacienteId(paciente);
		procedimento3.setPreco(20000.00);

		prcdtDao.add(procedimento);
		prcdtDao.add(procedimento2);
		prcdtDao.add(procedimento3);

		//Removendo procedimento 2
		prcdtDao.remove(procedimento2);

		GenericDao<MatMed> matMedDao = new GenericDao<MatMed>(MatMed.class);

		MatMed MatMed1 = new MatMed();
		MatMed1.setDescricao("Luvas");
		MatMed1.setFabricante("Silicon");
		MatMed1.setPreco(50.00);
		MatMed1.setPaciente(paciente);

		MatMed MatMed2 = new MatMed();
		MatMed2.setDescricao("Gase");
		MatMed2.setFabricante("Jhonson & Jhonson");
		MatMed2.setPreco(10.00);
		MatMed2.setPaciente(paciente);

		matMedDao.add(MatMed1);
		matMedDao.add(MatMed2);

		MatMed2.setPreco(15.90);
		matMedDao.update(MatMed1);
		
	
	}

	public static void buscarPacienteEProcedimentos() {
		List<Procedimento> query = JPAUtil.getEntityManager()
				.createQuery("SELECT p FROM Procedimento p, Paciente c WHERE c.cpf = p.paciente.cpf",
						Procedimento.class)
				.getResultList();

		for (Procedimento procedimento : query) {
			System.out.println("Paciente: " + procedimento.getPacienteId().getNome() + " Procedimento realizado: " +
					 procedimento.getDescricao() + " no valor de: " + procedimento.getPreco() );
		}
	}
	

	public static void buscarPacientesEmaterial() {
		List<MatMed> query = JPAUtil.getEntityManager()
				.createQuery("SELECT m FROM MatMed m, Paciente c WHERE c.cpf = m.paciente.cpf", MatMed.class)
				.getResultList();

		for (MatMed MatMed : query) {
			System.out.println("Paciente: " + MatMed.getPaciente().getNome() + " utilizou o material " + MatMed.getDescricao()
					+ " no valor de " + MatMed.getPreco() );
		}
	}

}
