package com.example.pocketreceipt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReceiptAdapter extends FirestoreRecyclerAdapter<ReceiptsModel, ReceiptAdapter.ReceiptHolder> {
    private OnItemClickListener listener;

//    private String convertDateToString (Date date) {
//        //change according to your supported formate
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy" );
//        return dateFormat.format(date);
//    }

    public ReceiptAdapter(@NonNull FirestoreRecyclerOptions<ReceiptsModel> options) {
        super( options );
    }
//create view a holder to hold memory of each entry
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

    class ReceiptHolder extends RecyclerView.ViewHolder {
        TextView view_date;
        TextView view_total;
        TextView view_store;

//creat constructor
        public ReceiptHolder(@NonNull View itemView) {
            super( itemView );
            view_date = itemView.findViewById(R.id.view_date);
            view_store = itemView.findViewById(R.id.view_store);
            view_total = itemView.findViewById(R.id.view_total);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick( getSnapshots().getSnapshot( position ), position);
                    }

                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);

    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}