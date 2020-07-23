package com.example.pocketreceipt;


public class ReceiptsModel {


    private String date;
    private String store;
    private String total;


    private ReceiptsModel() { }

    private ReceiptsModel(String date, String store, String total) {
        this.date= date;
        this.store= store;
        this.total= total;


    }

    public String getDate() {

        return date;
    }
    public String getStore() {

        return store;
    }
    public String getTotal() {

        return total;
    }

    public void setDate(String date) {this.date = date; }
    public void setStore(String store) { this.store = store; }
    public void setTotal(String total) { this.total = total; }



}