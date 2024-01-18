package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jpautil.JPAUtil;

//<E> - que vai receber qualquer classe
// classe onde terá os métodos do BD
public class DaoGeneric<E> {
	
	public void salvar(E entidade) {
		// Preciso de uma forma forma de prover a  persistência e chamar  o JPAUtil
		EntityManager entityManeger = JPAUtil.getEntityManager();
		
		// Uma transação para a operação no BD
		EntityTransaction entityTransaction = entityManeger.getTransaction();
		entityTransaction.begin();
		
		
		// agora está pronto para salvar no JPA
		// persist - só irá salvar
		entityManeger.persist(entidade);
		
		entityTransaction.commit();
		entityManeger.close();
	}

	
	public E merge(E entidade) {
		// Preciso de uma forma forma de prover a  persistência e chamar  o JPAUtil
		EntityManager entityManeger = JPAUtil.getEntityManager();
		
		// Uma transação para a operação no BD
		EntityTransaction entityTransaction = entityManeger.getTransaction();
		entityTransaction.begin();
		
		
		// agora está pronto para salvar no JPA
		// merge - irá salver e retornar uma entidade, vai no BD e retorna os dados que lá estiverem dessa entidade
		 E retorno = entityManeger.merge(entidade);
		
		entityTransaction.commit();
		entityManeger.close();
		
		return retorno;
	}
	
	public void removerporId(E entidade) {
		// Preciso de uma forma forma de prover a  persistência e chamar  o JPAUtil
		EntityManager entityManeger = JPAUtil.getEntityManager();
		
		// Uma transação para a operação no BD
		EntityTransaction entityTransaction = entityManeger.getTransaction();
		entityTransaction.begin();
		
		// para retornar a primarykey da entidade 
		Object id = JPAUtil.getPrimaryKey(entidade);
		// dar um comando delete para retornar a classe da entidade,seu nome e o id  e atualiza
		entityManeger.createQuery("delete from "+entidade.getClass().getCanonicalName()+" where id = "+id).executeUpdate();
		
		entityTransaction.commit();
		entityManeger.close();
	}
	
	// carrega os dados da tabela
	public List<E> getListEntity(Class<E> entidade) {
		// Preciso de uma forma de prover a  persistência e chamar  o JPAUtil
		EntityManager entityManeger = JPAUtil.getEntityManager();
		
		// Uma transação para a operação no BD
		EntityTransaction entityTransaction = entityManeger.getTransaction();
		entityTransaction.begin();
		
		//irá fazer select list da tabela pessoa - forma genérica entidade.getClass().getCanonicalName()
		//List<E> retorno = entityManeger.createQuery("from "+entidade.getName()).getResultList();
		List<E> retorno = entityManeger.createQuery("from "+entidade.getName()).getResultList();
		
		entityTransaction.commit();
		entityManeger.close();
		//depois que injetou a lista de pessoas, faz o retorno
		return retorno;
	}

	
	
}
