package com.example.pocketreceipt;


public class ReceiptsModel {


    private String email;
    private String store;
    private String total;


    private ReceiptsModel() { }

    private ReceiptsModel(String email, String store, String total) {
        this.email= email;
        this.store= store;
        this.total= total;


    }

    public String getEmail() {

        return email;
    }
    public String getStore() {

        return store;
    }
    public String getTotal() {

        return total;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setStore(String store) { this.store = store; }
    public void setTotal(String total) {
        this.total = total;
    }



}

