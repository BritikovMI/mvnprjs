package ru.rbt.mvnprjs.app.jpa;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by BritikovMI on 03.08.2017.
 */
@Entity
@Table(name = "BRM_ORDER_ITEM")
public class OrderItem implements Serializable {//implements serializable -abstractdbhelper - dbhelperentity//embdeded
    private static final long serialVersionUID = -6576973010070221989L;
    /**
     * Идентификатор
     */
    private Long id;
    private Order order;
    private Product product;


    @Id
    @Column(name = "ORDER_ITEM_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID_PK", nullable = false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID_PK", nullable = false)
    public Product getProduct() {
        return product;
    } //list orders //one to many// user employee

    public void setProduct(Product product) {
        this.product = product;
    }


}