package ru.rbt.mvnprjs.app.ejb;

import ru.rbt.mvnprjs.app.jpa.Customer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by BritikovMI on 03.08.2017.
 */
@ManagedBean(name="customer")
@SessionScoped
public class CustomerDao extends AbstractEntityDao<Long, Customer> {
    public CustomerDao() {
        super(Customer.class);
    }
}