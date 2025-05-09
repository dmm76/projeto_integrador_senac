package view;

import dao.ClienteDao;
import dao.FornecedorDao;
import model.Cliente;
import model.Fornecedor;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;

public class FornecedorView {
    public void cadastrarFornecedor() {
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        FornecedorDao fornecedorDao = new FornecedorDao(em);

        String nomeFornecedor = JOptionPane.showInputDialog(null, "Digite o nome: ");
        String cnpjFornecedor = JOptionPane.showInputDialog(null, "Digite o cnpj: ");
        String emailFornecedor = JOptionPane.showInputDialog(null, "Digite o email: ");
        String telefoneFornecedor = JOptionPane.showInputDialog(null, "Digite o telefone: ");
        String enderecoFornecedor = JOptionPane.showInputDialog(null, "Digite o endereco: ");

       Fornecedor fornecedor = new Fornecedor(nomeFornecedor, cnpjFornecedor, emailFornecedor, telefoneFornecedor, enderecoFornecedor);

        em.getTransaction().begin();

        fornecedorDao.cadastrar(fornecedor);

        em.getTransaction().commit();

        em.close();
    }
    public String consultarFornecedor(){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();

        FornecedorDao fornecedorDao = new FornecedorDao(em);

        List<Fornecedor> todosRegistros = fornecedorDao.buscarTodos();
        int totalRegistros = todosRegistros.size();

        String resultado = "ID - Nome - CNPJ\n";

        for(int i=0; i < totalRegistros; i++){
            resultado += todosRegistros.get(i).getIdFornecedor() + " - " +
                    todosRegistros.get(i).getNomeFornecedor() + " - " +
                    todosRegistros.get(i).getCnpjFornecedor() + "\n";
        }
        return resultado;
    }
    public void alterarFornecedor(int id){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        FornecedorDao fornecedorDao = new FornecedorDao(em);

        String nomeFornecedor = JOptionPane.showInputDialog(null, "Digite o nome: ");
        String cnpjFornecedor = JOptionPane.showInputDialog(null, "Digite o cnpj: ");
        String emailFornecedor = JOptionPane.showInputDialog(null, "Digite o email: ");
        String telefoneFornecedor = JOptionPane.showInputDialog(null, "Digite o telefone: ");
        String enderecoFornecedor = JOptionPane.showInputDialog(null, "Digite o endereco: ");

        Fornecedor fornecedor = fornecedorDao.buscarPorID(id);

        em.getTransaction().begin();
        fornecedor.setNomeFornecedor(nomeFornecedor);
        fornecedor.setCnpjFornecedor(cnpjFornecedor);
        fornecedor.setEmailFornecedor(emailFornecedor);
        fornecedor.setTelefoneFornecedor(telefoneFornecedor);
        fornecedor.setEnderecoFornecedor(enderecoFornecedor);

        em.getTransaction().commit();
        em.close();
    }
    public void removerFornecedor(int id){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        FornecedorDao fornecedorDao = new FornecedorDao(em);

        Fornecedor fornecedor = fornecedorDao.buscarPorID(id);

        em.getTransaction().begin();
        em.remove(fornecedor);
        em.getTransaction().commit();
        em.close();
    }
}
