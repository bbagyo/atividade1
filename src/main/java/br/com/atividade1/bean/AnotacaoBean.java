package br.com.atividade1.bean;

import br.com.atividade1.dao.AnotacaoDao;
import br.com.atividade1.model.Anotacao;
import br.com.atividade1.model.Prioridade;
import br.com.atividade1.service.AnotacaoService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
@ViewScoped
public class AnotacaoBean {

    private Anotacao anotacao;
    private List<Anotacao> anotacoes;
    private AnotacaoService anoServ;
    
    @PostConstruct
    public void init() {
        anoServ = new AnotacaoService();
        anotacao = new Anotacao();

        try {
            anotacoes = this.anoServ.listarTudo();
            
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
            context.getExternalContext().getFlash().setKeepMessages(true);
        }
    }

    public Anotacao getanotacao() {
        return anotacao;
    }

    public List<Anotacao> getanotacoes() {
        return anotacoes;
    }

    public String inserir() {  	
    	anoServ.inserir(anotacao);
        return "home";
    }

    public String atualizar() {
    	
    	this.anotacoes = anoServ.atualizar(anotacao);
        return "home";
    }

    public String excluir() {
    	
        try {
        	this.anotacoes = anoServ.excluir(anotacao);
           
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
            context.getExternalContext().getFlash().setKeepMessages(true);
        }
        return "home";
    }

    public void selecionar() {
        anoServ.excluir(anotacao);

    }

    public Prioridade[] getPrioridades() {
        return Prioridade.values();
    }
}
