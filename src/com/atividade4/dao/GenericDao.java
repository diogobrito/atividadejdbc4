package com.atividade4.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.dnoda.model.Equipment;
import br.com.dnoda.model.Patient;
import br.com.dnoda.model.Procedure;

public class GenericDao<T> implements Dao<T>{
	private final Class<T> genericObject;
	protected EntityManager entityManager;
	protected String statement;
	
	public GenericDao(Class<T> genericObject){
		this.genericObject = genericObject;
	}

	@Override
	public void add(T entity) {
		entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> list() {
		entityManager = JPAUtil.getEntityManager();
		
		System.out.println("Lista detalhes da agenda");
		statement = "select c from " + genericObject.getSimpleName() + " as c";
		List<T> returnList = entityManager.createQuery(statement).getResultList();
		
		entityManager.close();
		
		return returnList;
	}

	
	@Override
	public void update(T entidade) {
		entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(entidade); 
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public void remove(T entity) {
		entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.merge(entity));
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public T getOne(int id) {
		entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		T entity = entityManager.find(genericObject, id);
		entityManager.close();
		
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public void getOne(String patientCPF){
		System.out.println("\n\n\nLista detalhes do procedimento do paciente");
		entityManager = JPAUtil.getEntityManager();
		
		statement =  "select paci, proc, equi"
				   + "  from Patient as paci"
				   + "  join paci.procedures as proc"
				   + "  join paci.equipments as equi"
				   + " where paci.patientCPF = " + patientCPF;
		
		List<Object[]> returnList = entityManager.createQuery(statement).getResultList();
		
		entityManager.close();
		
		for (Object[] consultorio : returnList) {
			Paciente patient = (Paciente) consultorio[0];
			Procedimento procedure = (Procedimento) consultorio[1];
			MatMed equipment = (MatMed) consultorio[2];
			
			System.out.println(patient.getName());
			System.out.println(procedure.getDescription());
			System.out.println(equipment.getDescription());
		}
	}
}
