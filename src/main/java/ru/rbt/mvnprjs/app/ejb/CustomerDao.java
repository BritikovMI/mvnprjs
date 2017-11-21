package ru.rbt.mvnprjs.app.ejb;

import ru.rbt.mvnprjs.app.jpa.Customer;

import javax.ejb.Stateless;

/**
 * Created by BritikovMI on 03.08.2017.
 */
@Stateless
public class CustomerDao extends AbstractEntityDao<Long, Customer> {
    public CustomerDao() {
        super(Customer.class);
    }
}