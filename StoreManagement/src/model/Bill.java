package src.model;
import java.util.ArrayList;

public class Bill {
    private String IDOrder;
    private int total;

    private String date;

    private Customer customer;

    ArrayList<Product> listOrderProduct;

    public Bill(){

    }

    public Bill(String IDOrder, int total, String date){
        this.IDOrder = IDOrder;
        this.total = total;
        this.date = date;
//        this.customer = customer;
    }

    public String getIDOrder() {
        return IDOrder;
    }

    public int getTotal() {
        return total;
    }

    public String getDate() {
        return date;
    }

    public void setIDOrder(String IDOrder) {
        this.IDOrder = IDOrder;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setDate(String date) {
        this.date = date;
    }

//    public ArrayList<Product> getListOrderProduct() {
//        return listOrderProduct;
//    }
//
//    public void setListOrderProduct(ArrayList<Product> listOrderProduct) {
//        this.listOrderProduct = listOrderProduct;
//    }
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
}

