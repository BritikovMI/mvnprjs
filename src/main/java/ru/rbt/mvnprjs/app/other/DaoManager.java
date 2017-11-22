package ru.rbt.mvnprjs.app.other;

import ru.rbt.mvnprjs.app.ejb.CustomerDao;
import ru.rbt.mvnprjs.app.ejb.OrderDao;
import ru.rbt.mvnprjs.app.ejb.OrderItemDao;
import ru.rbt.mvnprjs.app.ejb.ProductDao;
import ru.rbt.mvnprjs.app.jpa.Order;
import ru.rbt.mvnprjs.app.jpa.Product;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Это CDI bean тут еще нет транзакций
 * тут мы еще не лезе в базульку
 * тут оперируем DAO-layer
 * <p>
 * Created by er23887 on 22.08.2017.
 */
@ManagedBean(name="daoMan")
@SessionScoped
public class DaoManager implements Serializable{

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{customer}")
    private CustomerDao customerDao;
    @ManagedProperty(value = "#{order}")
    private OrderDao orderDao;
    @ManagedProperty(value = "#{product}")
    private ProductDao productDao;

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public OrderItemDao getOrderItemDao() {
        return orderItemDao;
    }

    public void setOrderItemDao(OrderItemDao orderItemDao) {
        this.orderItemDao = orderItemDao;
    }

    @ManagedProperty(value = "#{orderit}")
    private OrderItemDao orderItemDao;

    public List<String> handleRequest(String name, Long num) {
        List<String> result = new ArrayList<>();
        if (name.equals("customer-order")) {
            List<Order> orders = orderDao.getOrdersByCustomerId(num);
            result.add("Получаем заказы определенного кастомера: ");
            result.add("\n");
            orders.forEach(order -> result.add(order.toString()));
        } else if (name.equals("product-customer")) {
            List<Product> products = orderItemDao.getProductsByCustomerIdentifier(num);
            result.add("Получаем продукты, купленные определенным кастомером: ");
            result.add("\n");
            products.forEach(product -> result.add(product.getProductType()));
        } else {
            result.add("Необходимо ввести команду, в командную строку после ?name=");
        }
        return result;
    }
}
