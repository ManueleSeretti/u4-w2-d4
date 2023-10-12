package manueleSeretti;

import com.github.javafaker.Faker;
import manueleSeretti.shop.Customer;
import manueleSeretti.shop.Order;
import manueleSeretti.shop.Product;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        Product p1 = new Product("libro1", "Books", 101.5);
        Product p2 = new Product("libro2", "Books", 23.5);
        Product p3 = new Product("libro3", "Books", 121.5);
        Product p4 = new Product("libro4", "Books", 71.5);
        Product p5 = new Product("libro5", "Books", 41.5);
        Product p6 = new Product("ciuccio", "Baby", 52.5);
        Product p7 = new Product("pannolini", "Baby", 47.5);
        Product p8 = new Product("scarpe", "Boy", 60);
        Product p9 = new Product("pantaloni", "Boy", 23);
        Product p10 = new Product("maglietta", "Boy", 28);

        List<Product> listaProdotti = new ArrayList<>();
        listaProdotti.add(p1);
        listaProdotti.add(p2);
        listaProdotti.add(p3);
        listaProdotti.add(p4);
        listaProdotti.add(p5);
        listaProdotti.add(p6);
        listaProdotti.add(p7);
        listaProdotti.add(p8);
        listaProdotti.add(p9);
        listaProdotti.add(p10);

        Faker fake = new Faker(Locale.ITALY);
        Random rndm = new Random();
        Supplier<Customer> customerSupplier = () -> new Customer(fake.name().firstName(), rndm.nextInt(1, 3));
        List<Customer> customerList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            customerList.add(customerSupplier.get());
        }

        Supplier<Order> orderSupplier = () -> new Order("consegnato", LocalDate.of(2023, 10, 15), creaOrdine(listaProdotti), customerList.get(rndm.nextInt(0, 5)));

        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            orderList.add(orderSupplier.get());
        }

        //ESERCIZIO 1

        Map<Customer, List<Order>> lista1 = orderList.stream().collect(Collectors.groupingBy(Order::getCustomer));

        lista1.forEach((c, o) -> System.out.println("Cliente : " + c.getName() + " ordini:" + o));

        //ESERCIZIO 2
        // Map<Customer, double> lista2 = orderList.stream().collect(Collectors.groupingBy(Order::getCustomer, orderList.stream().map(o -> o.getProduct.mapToDouble(Product::getPrice)).sum()));


        //ESERCIZIO 3


        // Product costoso = listaProdotti.stream().map(p-> { })


        //ESERCIZIO 4

        // double mediaordini = orderList.stream().map(Order::getProduct).mapToDouble(p -> p.getPrice());

    }

    public static List<Product> creaOrdine(List<Product> l) {
        Random rndm = new Random();
        List<Product> lista = new ArrayList<>();
        int c = rndm.nextInt(1, 5);
        for (int i = 0; i < c; i++) {
            int x = rndm.nextInt(0, 9);
            lista.add(l.get(x));
        }
        return lista;
    }
}

