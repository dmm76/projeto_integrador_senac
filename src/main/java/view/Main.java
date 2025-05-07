package view;

import model.FormaPagamento;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String[] botao = {"Cadastro", "Pedido", "Encerrar"};
        String[] botaoEscolha= {"Cliente", "Fornecedor", "ProdutoItem", "FormaPagamento", "Categoria", "Marca", "Voltar"};
        String[] botaoCadastro = {"Cadastrar", "Consultar", "Alterar", "Deletar", "Voltar"};

        FormaPagamentoView formaPagamentoView = new FormaPagamentoView();

        int opcao = 0, opcaoEscolha, opcaoCadastro;
        do{
            opcao = JOptionPane.showOptionDialog(null, "Selecione uma opção", "CADASTRO", 0,3, null, botao, 0);
            switch (opcao){
                case 0:
                    opcaoEscolha = JOptionPane.showOptionDialog(null, "Selecione uma opção", "Produto", 0,3, null, botaoEscolha, 0);
                    switch (opcaoEscolha){
                        case 0://Cliente
                            break;
                        case 1://Fornecedor
                            break;
                        case 2://ProdutoItem
                            break;
                        case 3://FormaPagamento
                            opcaoCadastro = JOptionPane.showOptionDialog(null, "Selecione uma opção", "Produto", 0,3, null, botaoCadastro, 0);
                            switch (opcaoCadastro){
                                case 0://Cadastrar
                                    break;
                                case 1://Consultar
                                    break;
                                case 2://Alterar
                                    break;
                                case 3://Deletar
                                    break;
                            }
                            break;
                        case 4://Categoria
                            break;
                        case 5://Marca
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
