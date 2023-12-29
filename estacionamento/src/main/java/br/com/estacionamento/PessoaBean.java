package br.com.estacionamento;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dao.DaoGeneric;
import br.com.entidades.Pessoa;

@ViewScoped
@ManagedBean(name = "pessoaBean")
public class PessoaBean {
	
	private Pessoa pessoa = new Pessoa();
	private DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
	// pra carregar uma lista de pessoas é preciso ter um list de pessoas
	List<Pessoa> pessoas = new ArrayList<Pessoa>();	
	
	// agora salva e retorna a entidade na tela JSF
		public String salvar() {
			
			
			
			if(pessoa.getPlacaCarro().isEmpty()) {
				mostrarmsg("Por Favor digitar a placa do carro");
			} else if(pessoa.getNome().isEmpty()) {
				mostrarmsg("Por Favor digitar o nome");
			} else if(pessoa.getSobreNome().isEmpty()) {
				mostrarmsg("Por Favor digitar o sobreNome");
			} else {
				// atribui-se a pessoa, a entidade que o JPA irá retornar
				pessoa = daoGeneric.merge(pessoa);
				// quando existem alterações no BD, involve-se o carregar a lista de pessoas
				carregarPessoas();
				mostrarmsg("CADASTRADO COM SUCESSO");
			}
			
			
			pessoa = new Pessoa();
			return "";
		}
		
	private void mostrarmsg(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(msg);
		context.addMessage(null, message);
			
		}

	@PostConstruct
	private void carregarPessoas() {
		// Atribuido a lista de pessoas a consulta de dados que feita no BD
			pessoas = daoGeneric.getListEntity(Pessoa.class);
		
	}
	
	public String novo() {
		pessoa = new Pessoa();
		// retorno que permite, permanecer na mesma tela
		return "";
	}
	
	// Método é String para permanecer na mesma tela JSF com return ""
		public String remover() {
			daoGeneric.removerporId(pessoa);
			// retorno que permite, permanecer na mesma tela
			pessoa = new Pessoa();
			// quando existem alterações no BD, invo-se o carregar a lista de pessoas
			carregarPessoas();
			mostrarmsg("REMOVIDO COM SUCESSO");
			return "";
		}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public DaoGeneric<Pessoa> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<Pessoa> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	


}
