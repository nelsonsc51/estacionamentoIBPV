package br.com.reposiytory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.entidades.Pessoa;
import br.com.jpautil.JPAUtil;

public class IDaoPessoaImpl implements IDAOPessoa {

	@Override
	public Pessoa consultarUsuario(String login, String senha) {

		Pessoa pessoa = null;

		// Preciso de uma forma de prover a persistência e chamar o JPAUtil
		EntityManager entityManager = JPAUtil.getEntityManager();
		// Uma transação para a operação no BD
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		 try {
		//.getSingleResult(); - só pode ser retornado um resultado, somente um usuário único
		pessoa = (Pessoa) entityManager
				.createQuery("select p from Pessoa p where p.login = '" + login + "' and p.senha = '" + senha + "'")
				.getSingleResult();
		 }catch (javax.persistence.NoResultException e) { // tratamento se não encontrar o usuário e senha
		}
		entityTransaction.commit();
		entityManager.close();

		return pessoa;
	}

	@Override
	public Pessoa consultaPlaca(String placaCarro) {
		Pessoa pessoa = null;

		// Preciso de uma forma de prover a persistência e chamar o JPAUtil
		EntityManager entityManager = JPAUtil.getEntityManager();
		// Uma transação para a operação no BD
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		 try {
		//.getSingleResult(); - só pode ser retornado um resultado, somente uma placa única
		pessoa = (Pessoa) entityManager
				.createQuery("select p from Pessoa p where p.placacarro = '" + placaCarro+"'")
				.getSingleResult();
		 }catch (javax.persistence.NoResultException e) { // tratamento se não encontrar o usuário e senha
		e.getMessage();
		 }
		entityTransaction.commit();
		entityManager.close();

		return pessoa;
	}

	

}
