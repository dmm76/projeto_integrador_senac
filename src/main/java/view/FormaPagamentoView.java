package view;

import dao.FormaPagamentoDao;
import model.FormaPagamento;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;

public class FormaPagamentoView {
    public void cadastrarFormaPagamento(){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        FormaPagamentoDao formaPagamentoDao = new FormaPagamentoDao(em);

        String descricao = JOptionPane.showInputDialog(null, "Digite a forma de pagamento");

        FormaPagamento formaPagamento = new FormaPagamento(descricao);

        em.getTransaction().begin();

        formaPagamentoDao.cadastrar(formaPagamento);

        em.getTransaction().commit();

        em.close();
    }
    public String consultarFormaPagamento(){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        FormaPagamentoDao formaPagamentoDao = new FormaPagamentoDao(em);

        List<FormaPagamento> todosRegistros = formaPagamentoDao.buscarTodos();
        int totalRegistros = todosRegistros.size();

        String resultado = "ID - DESCRICAO\n";
        for(int i=0; i < totalRegistros; i++){
            resultado += todosRegistros.get(i).getIdFormaPagamento() + " - " +
                    todosRegistros.get(i).getDescricao() + "\n";
        }
        return resultado;
    }
    public void alterarFormaPagamento(int id){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        FormaPagamentoDao formaPagamentoDao = new FormaPagamentoDao(em);

        String descricao = JOptionPane.showInputDialog("Digite a descricao: ");

        FormaPagamento formaPagamento = formaPagamentoDao.buscarPorID(id);

        em.getTransaction().begin();
        formaPagamento.setDescricao(descricao);
        em.getTransaction().commit();
        em.close();
    }
    public void removerFormaPagamento(int id){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        FormaPagamentoDao formaPagamentoDao = new FormaPagamentoDao(em);

        FormaPagamento formaPagamento = formaPagamentoDao.buscarPorID(id);

        em.getTransaction().begin();
        em.remove(formaPagamento);
        em.getTransaction().commit();
        em.close();
    }
}
