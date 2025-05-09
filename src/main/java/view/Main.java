package view;

import model.FormaPagamento;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String resultadoRetorno = "";
        String[] botao = {"Cadastro", "Pedido", "Encerrar"};
        String[] botaoEscolha= {"Cliente", "Fornecedor", "ProdutoItem", "FormaPagamento", "Categoria", "Marca", "Voltar"};
        String[] botaoCadastro = {"Cadastrar", "Consultar", "Alterar", "Deletar", "Voltar"};

        ClienteView clienteView = new ClienteView();
        FornecedorView fornecedorView = new FornecedorView();
        FormaPagamentoView formaPagamentoView = new FormaPagamentoView();
        CategoriaView categoriaView = new CategoriaView();
        MarcaView marcaView = new MarcaView();

        int opcao = 0, opcaoEscolha, opcaoCadastro;
        do{
            opcao = JOptionPane.showOptionDialog(null, "Selecione uma opção", "CADASTRO", 0,3, null, botao, 0);
            switch (opcao){
                case 0:
                    opcaoEscolha = JOptionPane.showOptionDialog(null, "Selecione uma opção", "Produto", 0,3, null, botaoEscolha, 0);
                    switch (opcaoEscolha){
                        case 0://Cliente
                            opcaoCadastro = JOptionPane.showOptionDialog(null, "Selecione uma opção", "Cliente", 0,3, null, botaoCadastro, 0);
                            switch (opcaoCadastro){
                                case 0://Cadastrar
                                    clienteView.cadastrarCliente();
                                    JOptionPane.showMessageDialog(null, "Registro cadastrado com sucesso");
                                    break;
                                case 1://Consultar
                                    resultadoRetorno = clienteView.consultarCliente();
                                    JOptionPane.showMessageDialog(null, resultadoRetorno);
                                    break;
                                case 2://Alterar
                                    resultadoRetorno = clienteView.consultarCliente();
                                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id do cliente para atualizar: "));
                                    clienteView.alterarCliente(id);
                                    JOptionPane.showMessageDialog(null,"Registro atualizado com sucesso!");
                                    break;
                                case 3://Deletar
                                    resultadoRetorno = clienteView.consultarCliente();
                                    id = Integer.parseInt(JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id do cliente para atualizar: "));
                                    clienteView.removerCliente(id);
                                    JOptionPane.showMessageDialog(null,"Registro removido com sucesso!");
                                    break;
                            }
                            break;
                        case 1://Fornecedor
                            opcaoCadastro = JOptionPane.showOptionDialog(null, "Selecione uma opção", "Fornecedor", 0,3, null, botaoCadastro, 0);
                            switch (opcaoCadastro){
                                case 0://Cadastrar
                                    fornecedorView.cadastrarFornecedor();
                                    JOptionPane.showMessageDialog(null, "Registro cadastrado com sucesso");
                                    break;
                                case 1://Consultar
                                    resultadoRetorno = fornecedorView.consultarFornecedor();
                                    JOptionPane.showMessageDialog(null, resultadoRetorno);
                                    break;
                                case 2://Alterar
                                    resultadoRetorno = fornecedorView.consultarFornecedor();
                                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id do fornecedor para atualizar: "));
                                    fornecedorView.alterarFornecedor(id);
                                    JOptionPane.showMessageDialog(null,"Registro atualizado com sucesso!");
                                    break;
                                case 3://Deletar
                                    resultadoRetorno = clienteView.consultarCliente();
                                    id = Integer.parseInt(JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id do fornecedor para atualizar: "));
                                    fornecedorView.removerFornecedor(id);
                                    JOptionPane.showMessageDialog(null,"Registro removido com sucesso!");
                                    break;
                            }
                            break;
                        case 2://ProdutoItem
                            break;
                        case 3://FormaPagamento
                            opcaoCadastro = JOptionPane.showOptionDialog(null, "Selecione uma opção", "Forma de Pagamento", 0,3, null, botaoCadastro, 0);
                            switch (opcaoCadastro){
                                case 0://Cadastrar
                                    formaPagamentoView.cadastrarFormaPagamento();
                                    JOptionPane.showMessageDialog(null, "Registro cadastrado com sucesso");
                                    break;
                                case 1://Consultar
                                    resultadoRetorno = formaPagamentoView.consultarFormaPagamento();
                                    JOptionPane.showMessageDialog(null, resultadoRetorno);
                                    break;
                                case 2://Alterar
                                    resultadoRetorno = formaPagamentoView.consultarFormaPagamento();
                                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id do produto para atualizar: "));
                                    formaPagamentoView.alterarFormaPagamento(id);
                                    JOptionPane.showMessageDialog(null,"Registro atualizado com sucesso!");
                                    break;
                                case 3://Deletar
                                    resultadoRetorno = formaPagamentoView.consultarFormaPagamento();
                                    id = Integer.parseInt(JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id do produto para atualizar: "));
                                    formaPagamentoView.removerFormaPagamento(id);
                                    JOptionPane.showMessageDialog(null,"Registro removido com sucesso!");
                                    break;
                            }
                            break;
                        case 4://Categoria
                            opcaoCadastro = JOptionPane.showOptionDialog(null, "Selecione uma opção", "Categoria", 0,3, null, botaoCadastro, 0);
                            switch (opcaoCadastro){
                                case 0://Cadastrar
                                    categoriaView.cadastrarCategoria();
                                    JOptionPane.showMessageDialog(null, "Registro cadastrado com sucesso");
                                    break;
                                case 1://Consultar
                                    resultadoRetorno = categoriaView.consultarCategoria();
                                    JOptionPane.showMessageDialog(null, resultadoRetorno);
                                    break;
                                case 2://Alterar
                                    resultadoRetorno = categoriaView.consultarCategoria();
                                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id da categoria para atualizar: "));
                                    categoriaView.alterarCategoria(id);
                                    JOptionPane.showMessageDialog(null,"Registro atualizado com sucesso!");
                                    break;
                                case 3://Deletar
                                    resultadoRetorno = categoriaView.consultarCategoria();
                                    id = Integer.parseInt(JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id da categoria para atualizar: "));
                                    categoriaView.removerCategoria(id);
                                    JOptionPane.showMessageDialog(null,"Registro removido com sucesso!");
                                    break;
                            }
                            break;
                        case 5://Marca
                            opcaoCadastro = JOptionPane.showOptionDialog(null, "Selecione uma opção", "Categoria", 0,3, null, botaoCadastro, 0);
                            switch (opcaoCadastro){
                                case 0://Cadastrar
                                    marcaView.cadastrarMarca();
                                    JOptionPane.showMessageDialog(null, "Registro cadastrado com sucesso");
                                    break;
                                case 1://Consultar
                                    resultadoRetorno = marcaView.consultarMarca();
                                    JOptionPane.showMessageDialog(null, resultadoRetorno);
                                    break;
                                case 2://Alterar
                                    resultadoRetorno = marcaView.consultarMarca();
                                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id da categoria para atualizar: "));
                                    marcaView.alterarMarca(id);
                                    JOptionPane.showMessageDialog(null,"Registro atualizado com sucesso!");
                                    break;
                                case 3://Deletar
                                    resultadoRetorno = marcaView.consultarMarca();
                                    id = Integer.parseInt(JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id da categoria para atualizar: "));
                                    marcaView.removerMarca(id);
                                    JOptionPane.showMessageDialog(null,"Registro removido com sucesso!");
                                    break;
                            }
                            break;
                    }
                    break;
                case 1:
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Obrigado pela preferência!");
                    break;
            }
        }while(opcao !=2);
    }
}
