package com.example.pocketreceipt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReceiptAdapter extends FirestoreRecyclerAdapter<ReceiptsModel, ReceiptAdapter.ReceiptHolder> {


    public ReceiptAdapter(@NonNull FirestoreRecyclerOptions<ReceiptsModel> options) {
        super( options );
    }

    @Override
    protected void onBindViewHolder(@NonNull ReceiptHolder holder, int i, @NonNull ReceiptsModel model) {
        holder.view_date.setText(model.getDate());
        holder.view_store.setText(model.getStore());
        holder.view_total.setText(String.valueOf(model.getTotal()));

    }


    @NonNull
    @Override
    public ReceiptHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        return new ReceiptHolder( v );
    }

    //delete receipt in the card view
    public void deleteItem(int position) {
        getSnapshots().getSnapshot( position ).getReference().delete();
    }

    static class ReceiptHolder extends RecyclerView.ViewHolder {
        TextView view_date;
        TextView view_total;
        TextView view_store;


        public ReceiptHolder(@NonNull View itemView) {
            super( itemView );
            view_date = itemView.findViewById(R.id.view_date);
            view_store = itemView.findViewById(R.id.view_store);
            view_total = itemView.findViewById(R.id.view_total);

            }
    }

        }
