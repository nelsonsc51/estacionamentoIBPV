package br.com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.entidades.Pessoa;
import br.com.jpautil.JPAUtil;
//intercepta todas as páginas
@WebFilter(urlPatterns = {"/*"})
public class FilterAutenticacao implements Filter {

	// executado em todas as requuisições
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//quando clica no botão é feito uma requisiçao, com um no objeto 
		HttpServletRequest req = (HttpServletRequest) request;
		// e tem uma sessão que é única
		HttpSession session = req.getSession();
		
		//dentro da sessão, carrego o atributo do usuarioLogado, a variável Pessoa recebe esse atributo
		Pessoa usuarioLogado = (Pessoa) session.getAttribute("usuarioLogado");
		
		
		//é o endereço do nosso sistema na url
		String url = req.getServletPath();
		
		
		if(!url.equalsIgnoreCase("index.jsf") && usuarioLogado == null ){
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsf");
				dispatcher.forward(request, response);
				//o return é para parar a execução do Java
				//return;
			
			
		} else {
			chain.doFilter(request, response);
		}
		
		
	}
	
	// é executado quando sobe o servidor
	@Override
	public void init(FilterConfig args) throws ServletException{
		JPAUtil.getEntityManager();
	}
	
	
}
