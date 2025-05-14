package model;

import javax.persistence.*;

@Entity
@Table(name = "itempedido")
public class ItemPedido {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idItemPedido;

    @ManyToOne
    @JoinColumn(name="idItem")
    private Item item;

    @ManyToOne
    @JoinColumn(name="idPedido")
    private Pedido pedido;

    private int quantidadeItem;
    private double valorItem;
    @Column(insertable = false, updatable = false)
    private double valorTotalItem;

    public ItemPedido() {
    }

    public ItemPedido(Item item, Pedido pedido, int quantidadeItem, double valorItem) {
        this.item = item;
        this.pedido = pedido;
        this.quantidadeItem = quantidadeItem;
        this.valorItem = valorItem;
        //this.valorTotalItem = valorTotalItem;
    }

    public int getIdItemPedido() {
        return idItemPedido;
    }

    public void setIdItemPedido(int idItemPedido) {
        this.idItemPedido = idItemPedido;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getQuantidadeItem() {
        return quantidadeItem;
    }

    public void setQuantidadeItem(int quantidadeItem) {
        this.quantidadeItem = quantidadeItem;
    }

    public double getValorItem() {
        return valorItem;
    }

    public void setValorItem(double valorItem) {
        this.valorItem = valorItem;
    }

    public double getValorTotalItem() {
        return valorTotalItem;
    }

    public void setValorTotalItem(double valorTotalItem) {
        this.valorTotalItem = valorTotalItem;
    }
}
