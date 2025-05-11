package view;

import dao.ItemDao;
import model.FormaPagamento;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String resultadoRetorno = "";
        String[] botao = {"Cadastro", "Pedido", "Itens Pedidos", "Encerrar"};
        String[] botaoEscolha= {"Cliente", "Fornecedor", "ProdutoItem", "FormaPagamento", "Categoria", "Marca", "Voltar"};
        String[] botaoCadastro = {"Cadastrar", "Consultar", "Alterar", "Deletar", "Voltar"};

        ClienteView clienteView = new ClienteView();
        FornecedorView fornecedorView = new FornecedorView();
        ItemView itemView = new ItemView();
        FormaPagamentoView formaPagamentoView = new FormaPagamentoView();
        CategoriaView categoriaView = new CategoriaView();
        MarcaView marcaView = new MarcaView();
        PedidoView pedidoView = new PedidoView();
       ItemPedidoView itemPedidoView = new ItemPedidoView();

        int opcao = 0, opcaoEscolha, opcaoCadastro;
        do{
            opcao = JOptionPane.showOptionDialog(null, "Selecione uma opção", "CADASTRO", 0,3, null, botao, 0);
            switch (opcao){
                case 0://Cadastro
                    opcaoEscolha = JOptionPane.showOptionDialog(null, "Selecione uma opção", "MENU CADASTRO", 0,3, null, botaoEscolha, 0);
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
                                    resultadoRetorno = fornecedorView.consultarFornecedor();
                                    id = Integer.parseInt(JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id do fornecedor para atualizar: "));
                                    fornecedorView.removerFornecedor(id);
                                    JOptionPane.showMessageDialog(null,"Registro removido com sucesso!");
                                    break;
                            }
                            break;
                        case 2://ProdutoItem
                            opcaoCadastro = JOptionPane.showOptionDialog(null, "Selecione uma opção", "Item", 0,3, null, botaoCadastro, 0);
                            switch (opcaoCadastro){
                                case 0://Cadastrar
                                    itemView.cadastrarItem();
                                    JOptionPane.showMessageDialog(null, "Registro cadastrado com sucesso");
                                    break;
                                case 1://Consultar
                                    resultadoRetorno = itemView.consultarItens();
                                    JOptionPane.showMessageDialog(null, resultadoRetorno);
                                    break;
                                case 2://Alterar
                                    resultadoRetorno = itemView.consultarItens();
                                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id do item para atualizar: "));
                                    itemView.alterarItem(id);
                                    JOptionPane.showMessageDialog(null,"Registro atualizado com sucesso!");
                                    break;
                                case 3://Deletar
                                    resultadoRetorno = itemView.consultarItens();
                                    id = Integer.parseInt(JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id do item para atualizar: "));
                                    itemView.removerItem(id);
                                    JOptionPane.showMessageDialog(null,"Registro removido com sucesso!");
                                    break;
                            }
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
                                    if (categoriaView.cadastrarCategoria()) {
                                        JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso!");
                                    }
                                    break;
                                case 1://Consultar
                                    resultadoRetorno = categoriaView.consultarCategoria();
                                    JOptionPane.showMessageDialog(null, resultadoRetorno);
                                    break;
                                case 2://Alterar
                                    resultadoRetorno = categoriaView.consultarCategoria();
                                    String input = JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id da categoria para atualizar:");
                                    if (input == null || input.trim().isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "Operação cancelada.");
                                        break;
                                    }
                                    int id = Integer.parseInt(input);
                                    if (categoriaView.alterarCategoria(id)) {
                                        JOptionPane.showMessageDialog(null, "Categoria atualizada com sucesso!");
                                    }
                                    break;
                                case 3://Deletar
                                    resultadoRetorno = categoriaView.consultarCategoria();
                                    id = Integer.parseInt(JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id da categoria para atualizar: "));
                                    if (categoriaView.removerCategoria(id)) {
                                        JOptionPane.showMessageDialog(null, "Categoria removida com sucesso!");
                                    }
                                    break;
                            }
                            break;
                        case 5://Marca
                            opcaoCadastro = JOptionPane.showOptionDialog(null, "Selecione uma opção", "Marca", 0,3, null, botaoCadastro, 0);
                            switch (opcaoCadastro){
                                case 0://Cadastrar
                                    if (marcaView.cadastrarMarca()) {
                                        JOptionPane.showMessageDialog(null, "Marca cadastrada com sucesso!");
                                    }
                                    break;
                                case 1://Consultar
                                    resultadoRetorno = marcaView.consultarMarca();
                                    JOptionPane.showMessageDialog(null, resultadoRetorno);
                                    break;
                                case 2://Alterar
                                    resultadoRetorno = marcaView.consultarMarca();
                                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id da marca para atualizar: "));
                                    if (marcaView.alterarMarca(id)) {
                                        JOptionPane.showMessageDialog(null, "Marca atualizada com sucesso!");
                                    }
                                    break;
                                case 3://Deletar
                                    resultadoRetorno = marcaView.consultarMarca();
                                    id = Integer.parseInt(JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id da marca para deletar: "));
                                    if (marcaView.removerMarca(id)) {
                                        JOptionPane.showMessageDialog(null, "Marca removida com sucesso!");
                                    }
                                    break;
                            }
                            break;
                    }
                    break;
                case 1://Pedido
                    opcaoEscolha = JOptionPane.showOptionDialog(null, "Selecione uma opção", "PEDIDO", 0,3, null, botaoCadastro, 0);
                    switch (opcaoEscolha){
                        case 0://Cadastrar
                            pedidoView.cadastrarPedido();
                            int resposta = JOptionPane.showConfirmDialog(
                                    null,
                                    "Deseja adicionar itens a este pedido agora?",
                                    "Adicionar Itens",
                                    JOptionPane.YES_NO_OPTION
                            );
                            if (resposta == JOptionPane.YES_OPTION) {
                                itemPedidoView.cadastrarItemPedido();
                            }
                            JOptionPane.showMessageDialog(null, "Pedido cadastrado com sucesso");
                            break;
                        case 1://Consultar
                            resultadoRetorno = pedidoView.consultarPedido();
                            JOptionPane.showMessageDialog(null, resultadoRetorno);
                            break;
                        case 2://Alterar
                            resultadoRetorno = pedidoView.consultarPedido();
                            int id = Integer.parseInt(JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id do pedido para atualizar: "));
                            pedidoView.alterarPedido(id);
                            break;
                        case 3://Deletar
                            resultadoRetorno = pedidoView.consultarPedido();
                            id = Integer.parseInt(JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id do pedido para atualizar: "));
                            pedidoView.removerPedido(id);
                            break;
                    }
                    break;
                case 2://Item Pedido
                    opcaoEscolha = JOptionPane.showOptionDialog(null, "Selecione uma opção", "ITEM PEDIDO", 0,3, null, botaoCadastro, 0);
                    switch (opcaoEscolha){
                        case 0://Cadastrar
                            itemPedidoView.cadastrarItemPedido();
                            JOptionPane.showMessageDialog(null, "Item pedido cadastrado com sucesso");
                            break;
                        case 1://Consultar
                            resultadoRetorno = itemPedidoView.consultarItemPedido();
                            JOptionPane.showMessageDialog(null, resultadoRetorno);
                            break;
                        case 2://Alterar
                            resultadoRetorno = itemPedidoView.consultarItemPedido();
                            int id = Integer.parseInt(JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id do item pedido para atualizar: "));
                            itemPedidoView.alterarItemPedido(id);
                            break;
                        case 3://Deletar
                            resultadoRetorno = itemPedidoView.consultarItemPedido();
                            id = Integer.parseInt(JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id do item pedido para atualizar: "));
                            itemPedidoView.removerPedido(id);
                            break;
                    }
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Obrigado pela preferência!");
                    break;
            }
        }while(opcao !=3);
    }
}
