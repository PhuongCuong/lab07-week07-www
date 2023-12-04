package fit.iuh.edu.vn.lab07week07.frontend.dto;

import fit.iuh.edu.vn.lab07week07.backend.models.Customer;
import fit.iuh.edu.vn.lab07week07.backend.models.Employee;
import fit.iuh.edu.vn.lab07week07.backend.models.Order;

public class OrderInfo {
    private Customer customer;
    private Employee employee;
    private Order order;

    public OrderInfo(Customer customer, Employee employee, Order order) {
        this.customer = customer;
        this.employee = employee;
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "customer=" + customer +
                ", employee=" + employee +
                ", order=" + order +
                '}';
    }
}
