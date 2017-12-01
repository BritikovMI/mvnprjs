package ru.rbt.mvnprjs.app.ejb;

import ru.rbt.mvnprjs.app.jpa.Product;


/**
 * Created by BritikovMI on 03.08.2017.
 */
public class ProductDao extends AbstractEntityDao<Long, Product> {

    public ProductDao() {
        super(Product.class);
    }


}