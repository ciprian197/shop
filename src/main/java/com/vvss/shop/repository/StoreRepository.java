package com.vvss.shop.repository;

import com.vvss.shop.model.Product;

import java.io.*;
import java.util.ArrayList;

public class StoreRepository {
    private ArrayList<Product> allProducts = new ArrayList<Product>();

    public ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public void readFile(final String fname) throws NumberFormatException, IOException {
        final FileInputStream f = new FileInputStream(fname);
        final DataInputStream in = new DataInputStream(f);
        final BufferedReader buf = new BufferedReader(new InputStreamReader(in));
        String rd;
        String[] line;
        while ((rd = buf.readLine()) != null) {
            line = rd.split(" ");
            try {
                final Product product = new Product(Integer.parseInt(line[0]), line[1], line[2], Integer.parseInt(line[3]));
                this.allProducts.add(product);
            } catch (final NumberFormatException ex) {

            }
        }
        in.close();
    }

    public String addNewProduct(final Product p) throws IOException {
        if (p.getCode() > 0 && p.getQuantity() >= 0 && p.getCode() < Integer.MAX_VALUE && p.getQuantity() < Integer.MAX_VALUE && !illegal(p.getName())) {
            final BufferedWriter out = new BufferedWriter(new FileWriter("products.txt", true));
            int k = 1;
            for (final Product i : allProducts) {
                if (i.getCode() == p.getCode()) {
                    k = 0;
                }
            }
            if (k == 1) {
                out.newLine();
                out.write(p.getCode() + " " + p.getName() + " " + p.getCategory() + " " + p.getQuantity());
                out.close();
                allProducts.add(p);
            } else {
                System.err.println("This code already exists");
                out.close();
                return ("This code already exists");
            }
        } else {
            return ("code q");
        }
        System.out.println("Product added");
        return ("success");
    }

    private boolean illegal(final String name) {
        char c;
        for (int i = 0; i < name.length(); ++i) {
            c = name.charAt(i);
            if (!((c <= 'z' && c >= 'a') || (c <= 'Z' && c >= 'A') || (c <= '9' && c >= '1')))
                return true;
        }
        return false;
    }

    public ArrayList<Product> getProductsCategory(final String cat) {
        final ArrayList<Product> cProducts = new ArrayList<Product>();
        for (final Product p : allProducts) {
            if (p.getCategory().compareTo(cat) == 0) {
                cProducts.add(p);
            }
        }
        return cProducts;
    }

    public ArrayList<Product> stockSituationProduct(final String pname) {
        final ArrayList<Product> prods = new ArrayList<Product>();
        for (final Product p : allProducts)
            if (p.getName().compareTo(pname) == 0)
                prods.add(p);
        return prods;
    }

    public ArrayList<Product> stockSituation() {
        return allProducts;
    }

}
