package ru.job4j.ood.lsp.productstorage.stores;

public class Warehouse extends AbstractStore {

    public Warehouse() {
        super(exhaustion -> exhaustion <= 25);
    }
}
