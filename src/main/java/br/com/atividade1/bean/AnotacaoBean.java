package br.com.atividade1.bean;

import br.com.atividade1.dao.AnotacaoDao;
import br.com.atividade1.model.Anotacao;
import br.com.atividade1.model.Prioridade;

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
    private AnotacaoDao anotacaoDao;
    private List<Anotacao> anotacoes;

    @PostConstruct
    public void init() {
        anotacaoDao = new AnotacaoDao();
        anotacao = new Anotacao();

        try {
            anotacoes = anotacaoDao.listarTodos();
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
        try {
            anotacaoDao.inserir(anotacao);

            anotacoes = anotacaoDao.listarTodos();

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("anotação adicionada com sucesso!"));
            context.getExternalContext().getFlash().setKeepMessages(true);
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
            context.getExternalContext().getFlash().setKeepMessages(true);
        }

        return "home";
    }

    public String atualizar() {
        try {
            anotacaoDao.atualizar(anotacao);

            anotacoes = anotacaoDao.listarTodos();

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("anotação editada com sucesso!"));
            context.getExternalContext().getFlash().setKeepMessages(true);
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
            context.getExternalContext().getFlash().setKeepMessages(true);
        }

        return "home";
    }

    public String excluir() {
        try {
            anotacaoDao.excluir(anotacao.getId());

            anotacoes = anotacaoDao.listarTodos();

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("anotação removida com sucesso!"));
            context.getExternalContext().getFlash().setKeepMessages(true);
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
            context.getExternalContext().getFlash().setKeepMessages(true);
        }

        return "home";
    }

    public void selecionar() {
        try {
            anotacao = anotacaoDao.selecionar(anotacao.getId());

            if (anotacao == null || anotacao.getId() == 0) {
                anotacao = new Anotacao();

                throw new Exception("anotação não encontrada.");
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(e.getMessage());
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public Prioridade[] getPrioridades() {
        return Prioridade.values();
    }
}
