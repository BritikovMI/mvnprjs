package ru.rbt.mvnprjs.app.ejb;

import ru.rbt.mvnprjs.app.jpa.Product;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by BritikovMI on 03.08.2017.
 */
@ManagedBean(name="product")
@SessionScoped
public class ProductDao extends AbstractEntityDao<Long, Product> {

    public ProductDao() {
        super(Product.class);
    }


}