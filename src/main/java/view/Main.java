package view;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String resultadoRetorno = "";
        String[] botao = {"Gerenciar", "Pedido", "Pedido Itens", "Encerrar"};
        String[] botaoEscolha= {"Cliente", "Fornecedor", "ProdutoItem", "FormaPagamento", "Categoria", "Marca", "Voltar"};
        String[] botaoCadastro = {"Cadastrar", "Consultar", "Alterar", "Deletar", "Voltar"};

        ClienteView clienteView = new ClienteView();
        FornecedorView fornecedorView = new FornecedorView();
        ItemView itemView = new ItemView();
        FormaPagamentoView formaPagamentoView = new FormaPagamentoView();
        CategoriaView categoriaView = new CategoriaView();
        MarcaView marcaView = new MarcaView();
        PedidoView pedidoView = new PedidoView();
       PedidoItemView pedidoItemView = new PedidoItemView();

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
                                    if (clienteView.cadastrarCliente()) {
                                        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
                                    }
                                    break;
                                case 1://Consultar
                                    resultadoRetorno = clienteView.consultarCliente();
                                    JOptionPane.showMessageDialog(null, resultadoRetorno);
                                    break;
                                case 2://Alterar
                                    resultadoRetorno = clienteView.consultarCliente();
                                    String inputAlt = JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o ID do cliente para atualizar:");
                                    if (inputAlt == null || inputAlt.trim().isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "Operação cancelada.");
                                        break;
                                    }
                                    int idAlt = Integer.parseInt(inputAlt);
                                    if (clienteView.alterarCliente(idAlt)) {
                                        JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
                                    }
                                    break;
                                case 3://Deletar
                                    resultadoRetorno = clienteView.consultarCliente();
                                    String inputDel = JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o ID do cliente para remover:");
                                    if (inputDel == null || inputDel.trim().isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "Operação cancelada.");
                                        break;
                                    }
                                    int idDel = Integer.parseInt(inputDel);
                                    if (clienteView.removerCliente(idDel)) {
                                        JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
                                    }
                                    break;
                            }
                            break;
                        case 1://Fornecedor
                            opcaoCadastro = JOptionPane.showOptionDialog(null, "Selecione uma opção", "Fornecedor", 0,3, null, botaoCadastro, 0);
                            switch (opcaoCadastro){
                                case 0://Cadastrar
                                    if (fornecedorView.cadastrarFornecedor()) {
                                        JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!");
                                    }
                                    break;
                                case 1://Consultar
                                    resultadoRetorno = fornecedorView.consultarFornecedor();
                                    JOptionPane.showMessageDialog(null, resultadoRetorno);
                                    break;
                                case 2://Alterar
                                    resultadoRetorno = fornecedorView.consultarFornecedor();
                                    String inputAlt = JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o ID do fornecedor para atualizar:");
                                    if (inputAlt == null || inputAlt.trim().isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "Operação cancelada.");
                                        break;
                                    }
                                    int idAlt = Integer.parseInt(inputAlt);
                                    if (fornecedorView.alterarFornecedor(idAlt)) {
                                        JOptionPane.showMessageDialog(null, "Fornecedor atualizado com sucesso!");
                                    }
                                    break;
                                case 3://Deletar
                                    resultadoRetorno = fornecedorView.consultarFornecedor();
                                    String inputDel = JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o ID do fornecedor para remover:");
                                    if (inputDel == null || inputDel.trim().isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "Operação cancelada.");
                                        break;
                                    }
                                    int idDel = Integer.parseInt(inputDel);
                                    if (fornecedorView.removerFornecedor(idDel)) {
                                        JOptionPane.showMessageDialog(null, "Fornecedor removido com sucesso!");
                                    }
                                    break;
                            }
                            break;
                        case 2://ProdutoItem
                            opcaoCadastro = JOptionPane.showOptionDialog(null, "Selecione uma opção", "Item", 0,3, null, botaoCadastro, 0);
                            switch (opcaoCadastro){
                                case 0://Cadastrar
                                    if (itemView.cadastrarItem()) {
                                        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                                    }
                                    break;
                                case 1://Consultar
                                    resultadoRetorno = itemView.consultarItens();
                                    JOptionPane.showMessageDialog(null, resultadoRetorno);
                                    break;
                                case 2://Alterar
                                    resultadoRetorno = itemView.consultarItens();
                                    String inputAlt = JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o ID do produto para atualizar:");
                                    if (inputAlt == null || inputAlt.trim().isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "Operação cancelada.");
                                        break;
                                    }
                                    int idAlt = Integer.parseInt(inputAlt);
                                    if (itemView.alterarItem(idAlt)) {
                                        JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
                                    }
                                    break;
                                case 3://Deletar
                                    resultadoRetorno = itemView.consultarItens();
                                    String inputDel = JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o ID do produto para remover:");
                                    if (inputDel == null || inputDel.trim().isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "Operação cancelada.");
                                        break;
                                    }
                                    int idDel = Integer.parseInt(inputDel);
                                    if (itemView.removerItem(idDel)) {
                                        JOptionPane.showMessageDialog(null, "Produto removido com sucesso!");
                                    }
                                    break;
                            }
                            break;
                        case 3://FormaPagamento
                            opcaoCadastro = JOptionPane.showOptionDialog(null, "Selecione uma opção", "Forma de Pagamento", 0,3, null, botaoCadastro, 0);
                            switch (opcaoCadastro){
                                case 0://Cadastrar
                                    if (formaPagamentoView.cadastrarFormaPagamento()) {
                                        JOptionPane.showMessageDialog(null, "Forma de pagamento cadastrada com sucesso!");
                                    }
                                    break;
                                case 1://Consultar
                                    resultadoRetorno = formaPagamentoView.consultarFormaPagamento();
                                    JOptionPane.showMessageDialog(null, resultadoRetorno);
                                    break;
                                case 2://Alterar
                                    resultadoRetorno = formaPagamentoView.consultarFormaPagamento();
                                    String inputAlt = JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o ID da forma de pagamento para atualizar:");
                                    if (inputAlt == null || inputAlt.trim().isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "Operação cancelada.");
                                        break;
                                    }
                                    int id = Integer.parseInt(inputAlt);
                                    if (formaPagamentoView.alterarFormaPagamento(id)) {
                                        JOptionPane.showMessageDialog(null, "Forma de pagamento atualizada com sucesso!");
                                    }
                                    break;
                                case 3://Deletar
                                    resultadoRetorno = formaPagamentoView.consultarFormaPagamento();
                                    String inputDel = JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o ID da forma de pagamento para remover:");
                                    if (inputDel == null || inputDel.trim().isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "Operação cancelada.");
                                        break;
                                    }
                                    id = Integer.parseInt(inputDel);
                                    if (formaPagamentoView.removerFormaPagamento(id)) {
                                        JOptionPane.showMessageDialog(null, "Forma de pagamento removida com sucesso!");
                                    }
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
                            if (pedidoView.cadastrarPedido()) {
                                int resposta = JOptionPane.showConfirmDialog(null,
                                        "Deseja adicionar itens a este pedido agora?",
                                        "Adicionar Itens",
                                        JOptionPane.YES_NO_OPTION);
                                if (resposta == JOptionPane.YES_OPTION) {
                                    if (pedidoItemView.cadastrarItemPedido()) {
                                        JOptionPane.showMessageDialog(null, "Item Pedido cadastrado com sucesso");
                                    }
                                } else if(resposta == JOptionPane.NO_OPTION){
                                    JOptionPane.showMessageDialog(null, "Item Pedido cancelado");
                                }
                                JOptionPane.showMessageDialog(null, "Pedido registrado com sucesso!");
                            }
                            break;
                        case 1://Consultar
                            resultadoRetorno = pedidoView.consultarPedido();
                            JOptionPane.showMessageDialog(null, resultadoRetorno);
                            break;
                        case 2://Alterar
                            resultadoRetorno = pedidoView.consultarPedido();
                            String inputId = JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id do pedido para atualizar:");
                            if (inputId == null || inputId.trim().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Operação cancelada.");
                                break;
                            }
                            int id = Integer.parseInt(inputId);
                            if (pedidoView.alterarPedido(id)) {
                                JOptionPane.showMessageDialog(null, "Pedido atualizado com sucesso!");

                            }
                            break;
                        case 3://Deletar
                            resultadoRetorno = pedidoView.consultarPedido();
                            inputId = JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id do pedido para remoção:");
                            if (inputId == null || inputId.trim().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Operação cancelada.");
                                break;
                            }
                            id = Integer.parseInt(inputId);
                            if (pedidoView.removerPedido(id)) {
                                JOptionPane.showMessageDialog(null, "Pedido removido com sucesso!");

                            }
                            break;
                    }
                    break;
                case 2://Item Pedido
                    opcaoEscolha = JOptionPane.showOptionDialog(null, "Selecione uma opção", "ITEM PEDIDO", 0,3, null, botaoCadastro, 0);
                    switch (opcaoEscolha){
                        case 0://Cadastrar
                            if (pedidoItemView.cadastrarItemPedido()) {
                                JOptionPane.showMessageDialog(null, "Item pedido cadastrado com sucesso");
                            }
                            break;
                        case 1://Consultar
                            resultadoRetorno = pedidoItemView.consultarItemPedido();
                            JOptionPane.showMessageDialog(null, resultadoRetorno);
                            break;
                        case 2://Alterar
                            resultadoRetorno = pedidoItemView.consultarItemPedido();
                            String inputId = JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id do item pedido para atualizar:");
                            if (inputId == null || inputId.trim().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Operação cancelada.");
                                break;
                            }
                            int id = Integer.parseInt(inputId);
                            if (pedidoItemView.alterarItemPedido(id)) {
                                JOptionPane.showMessageDialog(null, "Pedido atualizado com sucesso!");
                            }
                            break;
                        case 3://Deletar
                            resultadoRetorno = pedidoItemView.consultarItemPedido();
                            inputId = JOptionPane.showInputDialog(null, resultadoRetorno + "Informe o id do item pedido para remoção:");
                            if (inputId == null || inputId.trim().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Operação cancelada.");
                                break;
                            }
                            id = Integer.parseInt(inputId);
                            if (pedidoItemView.removerItemPedido(id)) {
                                JOptionPane.showMessageDialog(null, "Item Pedido removido com sucesso!");

                            }
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
