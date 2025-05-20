package view;
import dao.FornecedorDao;
import model.Fornecedor;
import util.JPAUtil;
import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;
public class FornecedorView {
    public boolean cadastrarFornecedor() {
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        FornecedorDao fornecedorDao = new FornecedorDao(em);
        String nomeFornecedor = JOptionPane.showInputDialog(null, "Digite o nome: ");
        if (nomeFornecedor == null || nomeFornecedor.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            em.close();
            return false;
        }
        String cnpjFornecedor = JOptionPane.showInputDialog(null, "Digite o cnpj: ");
        if (cnpjFornecedor == null || cnpjFornecedor.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            em.close();
            return false;
        }
        String emailFornecedor = JOptionPane.showInputDialog(null, "Digite o email: ");
        if (emailFornecedor == null || emailFornecedor.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            em.close();
            return false;
        }
        String telefoneFornecedor = JOptionPane.showInputDialog(null, "Digite o telefone: ");
        if (telefoneFornecedor == null || telefoneFornecedor.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            em.close();
            return false;
        }
        String enderecoFornecedor = JOptionPane.showInputDialog(null, "Digite o endereco: ");
        if (enderecoFornecedor == null || enderecoFornecedor.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            em.close();
            return false;
        }
        Fornecedor fornecedor = new Fornecedor(nomeFornecedor, cnpjFornecedor, emailFornecedor, telefoneFornecedor, enderecoFornecedor);
        em.getTransaction().begin();
        fornecedorDao.cadastrar(fornecedor);
        em.getTransaction().commit();
        em.close();
        return true;
    }
    public String consultarFornecedor(){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        FornecedorDao fornecedorDao = new FornecedorDao(em);
        String resultado = null;

        List<Fornecedor> todosRegistros = fornecedorDao.buscarTodos();
        if (todosRegistros.isEmpty()) {
            em.close();
            return "Nenhum fornecedor cadastrado.";
        }else{
            int totalRegistros = todosRegistros.size();
            resultado = "ID - Nome - CNPJ\n";
            for(int i=0; i < totalRegistros; i++){
                resultado += todosRegistros.get(i).getIdFornecedor() + " - " +
                        todosRegistros.get(i).getNomeFornecedor() + " - " +
                        todosRegistros.get(i).getCnpjFornecedor() + "\n";
            }
        }
        return resultado;
    }
    public boolean alterarFornecedor(int id){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        FornecedorDao fornecedorDao = new FornecedorDao(em);
        Fornecedor fornecedor = fornecedorDao.buscarPorID(id);
        if (fornecedor == null) {
            JOptionPane.showMessageDialog(null, "Fornecedor não encontrado.");
            em.close();
            return false;
        }
        String nomeFornecedor = JOptionPane.showInputDialog("Novo nome:", fornecedor.getNomeFornecedor());
        if (nomeFornecedor == null || nomeFornecedor.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alteração cancelada.");
            em.close();
            return false;
        }
        String cnpjFornecedor = JOptionPane.showInputDialog("Novo CNPJ:", fornecedor.getCnpjFornecedor());
        if (cnpjFornecedor == null || cnpjFornecedor.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alteração cancelada.");
            em.close();
            return false;
        }
        String emailFornecedor = JOptionPane.showInputDialog("Novo e-mail:", fornecedor.getEmailFornecedor());
        if (emailFornecedor == null || emailFornecedor.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alteração cancelada.");
            em.close();
            return false;
        }
        String telefoneFornecedor = JOptionPane.showInputDialog("Novo telefone:", fornecedor.getTelefoneFornecedor());
        if (telefoneFornecedor == null || telefoneFornecedor.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alteração cancelada.");
            em.close();
            return false;
        }
        String enderecoFornecedor = JOptionPane.showInputDialog("Novo endereço:", fornecedor.getEnderecoFornecedor());
        if (enderecoFornecedor == null || enderecoFornecedor.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alteração cancelada.");
            em.close();
            return false;
        }
        em.getTransaction().begin();
        fornecedor.setNomeFornecedor(nomeFornecedor);
        fornecedor.setCnpjFornecedor(cnpjFornecedor);
        fornecedor.setEmailFornecedor(emailFornecedor);
        fornecedor.setTelefoneFornecedor(telefoneFornecedor);
        fornecedor.setEnderecoFornecedor(enderecoFornecedor);
        em.getTransaction().commit();
        em.close();
        return true;
    }
    public boolean removerFornecedor(int id){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        FornecedorDao fornecedorDao = new FornecedorDao(em);

        Fornecedor fornecedor = fornecedorDao.buscarPorID(id);
        if (fornecedor == null) {
            JOptionPane.showMessageDialog(null, "Fornecedor não encontrado.");
            em.close();
            return false;
        }
        em.getTransaction().begin();
        em.remove(fornecedor);
        em.getTransaction().commit();
        em.close();
        return true;
    }
}