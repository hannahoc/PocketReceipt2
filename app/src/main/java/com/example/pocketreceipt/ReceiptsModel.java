package com.example.pocketreceipt;

public class ReceiptsModel {

        private String email;

        private ReceiptsModel() { }

        private ReceiptsModel(String email) {
            this.email= email;

        }

        public String getEmail() {

            return email;
        }

    public void setEmail(String email) {
        this.email = email;
    }
}

