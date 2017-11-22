package ru.rbt.mvnprjs.app.ejb;


import ru.rbt.mvnprjs.app.jpa.Customer_;
import ru.rbt.mvnprjs.app.jpa.Order;
import ru.rbt.mvnprjs.app.jpa.Order_;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by BritikovMI on 03.08.2017.
 */
@ManagedBean(name="order")
@SessionScoped
public class OrderDao extends AbstractEntityDao<Long, Order> {

    public OrderDao() {
        super(Order.class);
    }

    public List<Order> getOrdersByCustomerId(Long customerId) {//Получаем заказы определенного кастомера
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
        Root<Order> root = criteria.from(Order.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(Order_.customer).get(Customer_.id), customerId));
        TypedQuery<Order> query = getEntityManager().createQuery(criteria);
        List<Order> result = query.getResultList();
        return result;
//        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
//        Root<Customer> root = query.from(Customer.class);
//        Join<Order,Customer> joinTab = root.join(String.valueOf(Order_.customer));
//        query.select(joinTab).where(builder.equal(root.get(Customer_.id), id));
//        List<Customer> custOrders = getEntityManager().createQuery(query).getResultList();
//        return custOrders;
    }
}
