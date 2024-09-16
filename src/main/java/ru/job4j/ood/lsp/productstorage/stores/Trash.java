package ru.job4j.ood.lsp.productstorage.stores;


public class Trash extends AbstractStore {

    public Trash() {
        super(exhaustion -> exhaustion >= 100);
    }
}
