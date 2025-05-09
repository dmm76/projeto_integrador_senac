package view;

import dao.PedidoDao;
import model.Cliente;
import model.FormaPagamento;
import model.Pedido;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PedidoView {
    public void cadastrarPedido() {
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        PedidoDao pedidoDao = new PedidoDao(em);

        try {
            // Entrada de dados com parsing de tipos
            String dataStr = JOptionPane.showInputDialog(null, "Digite a data: (aaaa-mm-dd)");
            Date dataPedido = new SimpleDateFormat("yyyy-MM-dd").parse(dataStr);

            String statusPedido = JOptionPane.showInputDialog(null, "Digite o status: ");
            int idFormaPagamento = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID da Forma de Pagamento: "));
            int idCliente = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID do Cliente: "));
            double valorTotalPedido = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor total do pedido: "));

            // Busca dos objetos relacionados
            FormaPagamento formaPagamento = em.find(FormaPagamento.class, idFormaPagamento);
            Cliente cliente = em.find(Cliente.class, idCliente);

            // Criação do pedido
            Pedido pedido = new Pedido(dataPedido, statusPedido, valorTotalPedido, cliente, formaPagamento);

            // Persistência
            em.getTransaction().begin();
            pedidoDao.cadastrar(pedido);
            em.getTransaction().commit();

            JOptionPane.showMessageDialog(null, "Pedido cadastrado com sucesso!");
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Data inválida! Use o formato aaaa-mm-dd.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar pedido: " + e.getMessage());
        } finally {
            em.close();
        }

    }
    public String consultarPedido(){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        PedidoDao pedidoDao = new PedidoDao(em);

        List<Pedido> todosRegistros = pedidoDao.buscarTodos();
        StringBuilder resultado = new StringBuilder("ID - Data - Status - Cliente - Forma Pagamento\n");

        for (Pedido p : todosRegistros) {
            resultado.append(p.getIdPedido()).append(" - ")
                    .append(p.getDataPedido()).append(" - ")
                    .append(p.getStatusPedido()).append(" - ")
                    .append(p.getCliente().getNomeCliente()).append(" - ")
                    .append(p.getFormaPagamento().getDescricao()).append("\n");
        }

        em.close();
        return resultado.toString();
    }
    public void alterarPedido(int id){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        PedidoDao pedidoDao = new PedidoDao(em);

        Pedido pedido = pedidoDao.buscarPorID(id);

        if (pedido == null) {
            JOptionPane.showMessageDialog(null, "Pedido não encontrado!");
            return;
        }

        try {
            String dataStr = JOptionPane.showInputDialog(null, "Digite a nova data: (aaaa-mm-dd)");
            Date dataPedido = new SimpleDateFormat("yyyy-MM-dd").parse(dataStr);

            String statusPedido = JOptionPane.showInputDialog(null, "Digite o novo status: ");
            int idFormaPagamento = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o novo ID da Forma de Pagamento: "));
            int idCliente = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o novo ID do Cliente: "));
            double valorTotalPedido = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o novo valor total: "));

            FormaPagamento formaPagamento = em.find(FormaPagamento.class, idFormaPagamento);
            Cliente cliente = em.find(Cliente.class, idCliente);

            em.getTransaction().begin();
            pedido.setDataPedido(dataPedido);
            pedido.setStatusPedido(statusPedido);
            pedido.setFormaPagamento(formaPagamento);
            pedido.setCliente(cliente);
            pedido.setValorTotalPedido(valorTotalPedido);
            em.getTransaction().commit();

            JOptionPane.showMessageDialog(null, "Pedido atualizado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    public void removerPedido(int id){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        PedidoDao pedidoDao = new PedidoDao(em);

        Pedido pedido = pedidoDao.buscarPorID(id);

        if (pedido == null) {
            JOptionPane.showMessageDialog(null, "Pedido não encontrado!");
            return;
        }

        em.getTransaction().begin();
        pedidoDao.remover(pedido);
        em.getTransaction().commit();
        em.close();

        JOptionPane.showMessageDialog(null, "Pedido removido com sucesso!");
    }
}
