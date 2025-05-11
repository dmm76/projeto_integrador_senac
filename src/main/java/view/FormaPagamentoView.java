package view;

import dao.FormaPagamentoDao;
import model.FormaPagamento;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;

public class FormaPagamentoView {
    public boolean cadastrarFormaPagamento(){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        FormaPagamentoDao formaPagamentoDao = new FormaPagamentoDao(em);

        String descricao = JOptionPane.showInputDialog(null, "Digite a descrição da forma de pagamento");
        if (descricao == null || descricao.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            em.close();
            return false;
        }
        FormaPagamento formaPagamento = new FormaPagamento(descricao);
        em.getTransaction().begin();
        formaPagamentoDao.cadastrar(formaPagamento);
        em.getTransaction().commit();
        em.close();
        return true;
    }
    public String consultarFormaPagamento(){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        FormaPagamentoDao formaPagamentoDao = new FormaPagamentoDao(em);
        String resultado = "";

        List<FormaPagamento> todosRegistros = formaPagamentoDao.buscarTodos();
        if (todosRegistros.isEmpty()) {
            em.close();
            return "Nenhuma forma de pagamento cadastrada.";
        }else{
            int totalRegistros = todosRegistros.size();

            resultado = "ID - DESCRICAO\n";
            for(int i=0; i < totalRegistros; i++){
                resultado += todosRegistros.get(i).getIdFormaPagamento() + " - " +
                        todosRegistros.get(i).getDescricao() + "\n";
            }
        }
        return resultado;
    }
    public boolean alterarFormaPagamento(int id){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        FormaPagamentoDao formaPagamentoDao = new FormaPagamentoDao(em);

        FormaPagamento formaPagamento = formaPagamentoDao.buscarPorID(id);
        if (formaPagamento == null) {
            JOptionPane.showMessageDialog(null, "Forma de pagamento não encontrada.");
            em.close();
            return false;
        }
        String novaDescricao = JOptionPane.showInputDialog("Digite a nova descricao: ", formaPagamento.getDescricao());
        if (novaDescricao == null || novaDescricao.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alteração cancelada.");
            em.close();
            return false;
        }

        em.getTransaction().begin();
        formaPagamento.setDescricao(novaDescricao);
        em.getTransaction().commit();
        em.close();
        return true;
    }
    public boolean removerFormaPagamento(int id){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        FormaPagamentoDao formaPagamentoDao = new FormaPagamentoDao(em);

        FormaPagamento formaPagamento = formaPagamentoDao.buscarPorID(id);
        if (formaPagamento == null) {
            JOptionPane.showMessageDialog(null, "Forma de pagamento não encontrada.");
            em.close();
            return false;
        }

        em.getTransaction().begin();
        em.remove(formaPagamento);
        em.getTransaction().commit();
        em.close();
        return true;
    }
}
