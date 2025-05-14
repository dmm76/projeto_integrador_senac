package view;

import dao.FormaPagamentoDao;
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

    public boolean cadastrarPedido() {
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        PedidoDao pedidoDao = new PedidoDao(em);

        try {
            // Entrada de dados com parsing de tipos
            String dataStr = JOptionPane.showInputDialog(null, "Digite a data: (aaaa-mm-dd)");
            if (dataStr == null || dataStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return false;
            }
            Date dataPedido = new SimpleDateFormat("yyyy-MM-dd").parse(dataStr);

            String statusPedido = JOptionPane.showInputDialog(null, "Digite o status: ");
            if (statusPedido == null || statusPedido.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return false;
            }

            FormaPagamentoView formaPagamentoView = new FormaPagamentoView();
            String resultado = formaPagamentoView.consultarFormaPagamento();
            //JOptionPane.showMessageDialog(null, formaPagamentoView.consultarFormaPagamento());
            String idFormaStr = JOptionPane.showInputDialog(null, resultado,"Digite o ID da Forma de Pagamento:");
            if (idFormaStr == null || idFormaStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return false;
            }
            int idFormaPagamento = Integer.parseInt(idFormaStr);

            ClienteView clienteView = new ClienteView();
            resultado = clienteView.consultarCliente();
           // JOptionPane.showMessageDialog(null, clienteView.consultarCliente());
            String idClienteSrt = JOptionPane.showInputDialog(null, resultado,"Digite o ID do Cliente: ");
            if (idClienteSrt == null || idClienteSrt.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return false;
            }
            int idCliente = Integer.parseInt(idClienteSrt);

            String  valorTotalPedidoStr = JOptionPane.showInputDialog(null, "Digite o valor total do pedido: ");
            if (valorTotalPedidoStr == null || valorTotalPedidoStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return false;
            }
            double valorTotalPedido = Double.parseDouble(valorTotalPedidoStr);

            // Busca dos objetos relacionados
            FormaPagamento formaPagamento = em.find(FormaPagamento.class, idFormaPagamento);
            Cliente cliente = em.find(Cliente.class, idCliente);

            // Criação do pedido
            Pedido pedido = new Pedido(dataPedido, statusPedido, valorTotalPedido, cliente, formaPagamento);

            // Persistência
            em.getTransaction().begin();
            pedidoDao.cadastrar(pedido);
            em.getTransaction().commit();

//            JOptionPane.showMessageDialog(null, "Pedido cadastrado com sucesso!");
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Data inválida! Use o formato aaaa-mm-dd.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar pedido: " + e.getMessage());
        } finally {
            em.close();
        }
        return true;
    }
    public String consultarPedido(){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        PedidoDao pedidoDao = new PedidoDao(em);

        List<Pedido> todosRegistros = pedidoDao.buscarTodos();
        StringBuilder resultado = new StringBuilder("ID - Data - Status - Cliente - Forma Pagamento - Valor Total\n");

        for (Pedido p : todosRegistros) {
            resultado.append(p.getIdPedido()).append(" - ")
                    .append(p.getDataPedido()).append(" - ")
                    .append(p.getStatusPedido()).append(" - ")
                    .append(p.getCliente().getNomeCliente()).append(" - ")
                    .append(p.getFormaPagamento().getDescricao()).append(" - R$ ")
                    .append(String.format("%.2f", p.getValorTotalPedido()))
                    .append("\n");
        }

        em.close();
        return resultado.toString();
    }
    public boolean alterarPedido(int id){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        PedidoDao pedidoDao = new PedidoDao(em);

        FormaPagamentoDao formaPagamentoDao = new FormaPagamentoDao(em);

        Pedido pedido = pedidoDao.buscarPorID(id);

        if (pedido == null) {
            JOptionPane.showMessageDialog(null, "Pedido não encontrado!");
            return false;
        }

        try {
            String dataStr = JOptionPane.showInputDialog(null, "Digite a nova data: (aaaa-mm-dd)", pedido.getDataPedido());
            if (dataStr == null || dataStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return false;
            }
            Date dataPedido = new SimpleDateFormat("yyyy-MM-dd").parse(dataStr);

            String statusPedido = JOptionPane.showInputDialog(null, "Digite o novo status: ", pedido.getStatusPedido());
            if (statusPedido == null || statusPedido.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return false;
            }

            FormaPagamentoView formaPagamentoView = new FormaPagamentoView();
            JOptionPane.showMessageDialog(null, formaPagamentoView.consultarFormaPagamento());
            String idFormaPagamentoStr = JOptionPane.showInputDialog(null,"Digite o novo ID da Forma de Pagamento: ",pedido.getFormaPagamento().getIdFormaPagamento());            if (idFormaPagamentoStr == null || idFormaPagamentoStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return false;
            }
            int idFormaPagamento = Integer.parseInt(idFormaPagamentoStr);

            ClienteView clienteView = new ClienteView();
            JOptionPane.showMessageDialog(null, clienteView.consultarCliente());
            String idClienteStr = JOptionPane.showInputDialog(null, "Digite a nova ID do Cliente: ", pedido.getCliente().getIdCliente());
            if (idClienteStr == null || idClienteStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return false;
            }
            int idCliente = Integer.parseInt(idClienteStr);

            String  valorTotalPedidoStr = JOptionPane.showInputDialog(null, "Digite o novo valor total do pedido: ", pedido.getValorTotalPedido() );
            if (valorTotalPedidoStr == null || valorTotalPedidoStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                return false;
            }
            double valorTotalPedido = Double.parseDouble(valorTotalPedidoStr);

            FormaPagamento formaPagamento = em.find(FormaPagamento.class, idFormaPagamento);
            Cliente cliente = em.find(Cliente.class, idCliente);

            em.getTransaction().begin();
            pedido.setDataPedido(dataPedido);
            pedido.setStatusPedido(statusPedido);
            pedido.setFormaPagamento(formaPagamento);
            pedido.setCliente(cliente);
            pedido.setValorTotalPedido(valorTotalPedido);
            em.getTransaction().commit();

            //JOptionPane.showMessageDialog(null, "Pedido atualizado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        } finally {
            em.close();
        }
        return true;
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

        //JOptionPane.showMessageDialog(null, "Pedido removido com sucesso!");
    }
}
