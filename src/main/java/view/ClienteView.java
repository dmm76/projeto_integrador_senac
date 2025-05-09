package view;

import dao.ClienteDao;
import model.Cliente;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;

public class ClienteView {

    public void cadastrarCliente() {
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        ClienteDao  clienteDao = new ClienteDao(em);

        String nomeCliente = JOptionPane.showInputDialog(null, "Digite o nome: ");
        String cpfCliente = JOptionPane.showInputDialog(null, "Digite o cpf: ");
        String emailCliente = JOptionPane.showInputDialog(null, "Digite o email: ");
        String telefoneCliente = JOptionPane.showInputDialog(null, "Digite o telefone: ");
        String enderecoCliente = JOptionPane.showInputDialog(null, "Digite o endereco: ");

        Cliente cliente = new Cliente(nomeCliente, cpfCliente, emailCliente, telefoneCliente, enderecoCliente);

        em.getTransaction().begin();

        clienteDao.cadastrar(cliente);

        em.getTransaction().commit();

        em.close();
    }
    public String consultarCliente(){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();

        ClienteDao clienteDao = new ClienteDao(em);

        List<Cliente> todosRegistros = clienteDao.buscarTodos();
        int totalRegistros = todosRegistros.size();

        String resultado = "ID - Nome - Cpf\n";

        for(int i=0; i < totalRegistros; i++){
            resultado += todosRegistros.get(i).getIdCliente() + " - " +
                    todosRegistros.get(i).getNomeCliente() + " - " +
                    todosRegistros.get(i).getCpfCliente() + "\n";
        }
        return resultado;
    }
    public void alterarCliente(int id){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        ClienteDao clienteDao = new ClienteDao(em);

        String nomeCliente = JOptionPane.showInputDialog(null, "Digite o nome: ");
        String cpfCliente = JOptionPane.showInputDialog(null, "Digite o cpf: ");
        String emailCliente = JOptionPane.showInputDialog(null, "Digite o email: ");
        String telefoneCliente = JOptionPane.showInputDialog(null, "Digite o telefone: ");
        String enderecoCliente = JOptionPane.showInputDialog(null, "Digite o endereco: ");

        Cliente cliente = clienteDao.buscarPorID(id);

        em.getTransaction().begin();
        cliente.setNomeCliente(nomeCliente);
        cliente.setCpfCliente(cpfCliente);
        cliente.setEmailCliente(emailCliente);
        cliente.setTelefoneCliente(telefoneCliente);
        cliente.setEnderecoCliente(enderecoCliente);

        em.getTransaction().commit();
        em.close();
    }
    public void removerCliente(int id){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        ClienteDao clienteDao = new ClienteDao(em);

        Cliente cliente = clienteDao.buscarPorID(id);

        em.getTransaction().begin();
        em.remove(cliente);
        em.getTransaction().commit();
        em.close();
    }
}
