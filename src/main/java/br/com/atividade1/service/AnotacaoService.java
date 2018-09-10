package br.com.atividade1.service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.atividade1.dao.AnotacaoDao;
import br.com.atividade1.model.Anotacao;

public class AnotacaoService {

    private Anotacao anotacao = new Anotacao();
    private AnotacaoDao anotacaoDao = new AnotacaoDao();
    private List<Anotacao> anotacoes = new ArrayList<>();
    
    public List<Anotacao> listarTudo(){
    	try {
			this.anotacoes = anotacaoDao.listarTodos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return this.anotacoes;
    }
	
	   public void inserir(Anotacao ano) {
	        try {
	            anotacaoDao.inserir(ano);

	            anotacoes = anotacaoDao.listarTodos();

	            FacesContext context = FacesContext.getCurrentInstance();
	            context.addMessage(null, new FacesMessage("anota��o adicionada com sucesso!"));
	            context.getExternalContext().getFlash().setKeepMessages(true);
	        } catch (Exception e) {
	            FacesContext context = FacesContext.getCurrentInstance();
	            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
	            context.getExternalContext().getFlash().setKeepMessages(true);
	        }
	    }

	    public List<Anotacao> atualizar(Anotacao ano) {
	        try {
	            anotacaoDao.atualizar(ano);

	            anotacoes = anotacaoDao.listarTodos();

	            FacesContext context = FacesContext.getCurrentInstance();
	            context.addMessage(null, new FacesMessage("anota��o editada com sucesso!"));
	            context.getExternalContext().getFlash().setKeepMessages(true);
	        } catch (Exception e) {
	            FacesContext context = FacesContext.getCurrentInstance();
	            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
	            context.getExternalContext().getFlash().setKeepMessages(true);
	        }
	        return anotacoes;
	    }

	    public List<Anotacao> excluir(Anotacao ano) {
	        try {
	            anotacaoDao.excluir(ano.getId());

	            anotacoes = anotacaoDao.listarTodos();

	            FacesContext context = FacesContext.getCurrentInstance();
	            context.addMessage(null, new FacesMessage("anota��o excluida com sucesso!"));
	            context.getExternalContext().getFlash().setKeepMessages(true);	           
	        } catch (Exception e) {
	            FacesContext context = FacesContext.getCurrentInstance();
	            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
	            context.getExternalContext().getFlash().setKeepMessages(true);
	        }
	        return anotacoes;
	    }

	    public void selecionar(Anotacao ano) {
	        try {
	            anotacao = anotacaoDao.selecionar(ano.getId());

	            if (anotacao == null || anotacao.getId() == 0) {
	                anotacao = new Anotacao();

	                throw new Exception("anota��o n�o encontrada.");
	            }
	        } catch (Exception e) {
	            FacesMessage message = new FacesMessage(e.getMessage());
	            message.setSeverity(FacesMessage.SEVERITY_ERROR);
	            FacesContext.getCurrentInstance().addMessage(null, message);
	        }

	    }

}
