package com.example.pocketreceipt;


public class ReceiptsModel {


    private String date;
    private String store;
    private String total;
    private String bed;



    private ReceiptsModel() { }

    private ReceiptsModel(String date, String store, String total, String bed) {
        this.date= date;
        this.bed= bed;
        this.store= store;
        this.total= total;
    }


    public String getDate() {

        return date;
    }
    public String getBed() {

        return bed;
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
    public void setBed(String bed) { this.bed = bed; }



}