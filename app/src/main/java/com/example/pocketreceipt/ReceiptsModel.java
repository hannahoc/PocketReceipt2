package com.example.pocketreceipt;


public class ReceiptsModel {


    private String date;
    private String store;
    private String store_location;
    private String store_phone;
    private String total;
    private String item_1;
    private String item_2;
    private String item_1_price;
    private String item_2_price;
    private String receiptNo;



    private ReceiptsModel() { }

    private ReceiptsModel(String date, String store,String store_location,String store_phone, String total,String receiptNo, String item_1, String item_2,String item_1_price,String item_2_price) {
        this.date= date;
        this.item_1= item_1;
        this.item_2= item_2;
        this.item_1_price= item_1_price;
        this.item_2_price= item_2_price;
        this.store= store;
        this.store_location= store_location;
        this.store_phone= store_phone;
        this.store= store;
        this.total= total;
        this.receiptNo= receiptNo;
    }


    public String getDate() {

        return date;
    }
    public String getStore_location() {

        return store_location;
    }
    public String getStore_phone() {

        return store_phone;
    }
    public String getReceiptNo() {

        return receiptNo;
    }
    public String getItem_1() {

        return item_1;
    }

    public String getItem_2() {

        return item_2;
    }

    public String getItem_1_price() {

        return item_1_price;
    }
    public String getItem_2_price() {

        return item_2_price;
    }
    public String getStore() {

        return store;
    }
    public String getTotal() {

        return total;
    }

    public void setDate(String date) {this.date = date; }
    public void setStore_location(String store_location) { this.store_location = store_location; }
    public void setStore_phone(String store_phone) { this.store_phone = store_phone; }
    public void setStore(String store) { this.store = store; }
    public void setTotal(String total) { this.total = total; }
    public void setItem_1(String item_1) { this.item_1 = item_1; }
    public void setItem_2(String item_2) { this.item_2 = item_2; }
    public void setItem_1_price(String item_1_price) { this.item_1_price = item_1_price; }
    public void setItem_2_price(String item_2_price) { this.item_2_price = item_2_price; }
    public void setReceiptNo(String receiptNo) { this.receiptNo = receiptNo; }



}