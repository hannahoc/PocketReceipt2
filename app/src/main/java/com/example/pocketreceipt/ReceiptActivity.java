package com.example.pocketreceipt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ReceiptActivity extends AppCompatActivity {

    private RecyclerView mFirestoreList;
    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);


        firebaseFirestore = firebaseFirestore.getInstance();
        mFirestoreList = findViewById(R.id.firestore_List);


        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        //query
        Query query = rootRef.collection("users")
                // .orderBy("email", Query.Direction.ASCENDING);
                .document("BtliRhtVFiQntlH2Hp1gvjG59b32")
                .collection("Receipts");


        FirestoreRecyclerOptions<ReceiptsModel> options = new FirestoreRecyclerOptions.Builder<ReceiptsModel>()
                .setQuery(query, ReceiptsModel.class)
                .build();


        //View holder class

        adapter = new FirestoreRecyclerAdapter<ReceiptsModel, ReceiptViewHolder>(options) {
            @NonNull
            @Override
            public ReceiptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
                return new ReceiptViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ReceiptViewHolder holder, int position, @NonNull ReceiptsModel model) {
                holder.view_date.setText(model.getDate());
                holder.view_store.setText(model.getStore());
                holder.view_total.setText(model.getTotal());
            }
        };

        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(adapter);

    }

    private class ReceiptViewHolder extends RecyclerView.ViewHolder {

        private TextView view_date, view_store, view_total;



        public ReceiptViewHolder(@NonNull View itemView) {
            super(itemView);

            view_date = itemView.findViewById(R.id.view_date);
            view_store = itemView.findViewById(R.id.view_store);
            view_total = itemView.findViewById(R.id.view_total);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}