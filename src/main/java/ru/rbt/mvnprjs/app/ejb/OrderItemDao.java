package ru.rbt.mvnprjs.app.ejb;

import ru.rbt.mvnprjs.app.jpa.*;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by BritikovMI on 03.08.2017.
 */
@ManagedBean(name="orderit")
@SessionScoped
public class OrderItemDao extends AbstractEntityDao<Long, OrderItem> {

    public OrderItemDao() {
        super(OrderItem.class);
    }

    public List<Product> getProductsByCustomerIdentifier(Long customerId) {
        System.out.println("customerId = " + customerId);
//        EntityManager entityManager = getEntityManager();
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<OrderItem> criteria = builder.createQuery(OrderItem.class);
        Root<OrderItem> root = criteria.from(OrderItem.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(OrderItem_.order).get(Order_.customer).get(Customer_.id), customerId));
        TypedQuery<OrderItem> query = getEntityManager().createQuery(criteria);
        List<OrderItem> list = query.getResultList();
        TreeSet<Product> products = new TreeSet<>((o1, o2) -> {
            if (o1 == null && o2 == null) return 0;
            else if (o1 == null) return -1;
            else if (o2 == null) return 1;
            return o1.getProductType().compareTo(o2.getProductType());
        });
        list.forEach(item -> products.add(item.getProduct()));
        return new ArrayList<>(products);
    }
}
