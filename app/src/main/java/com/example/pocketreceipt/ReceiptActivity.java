package com.example.pocketreceipt;

import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ReceiptActivity extends AppCompatActivity {

    private RecyclerView mFirestoreList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ReceiptAdapter adapter;
    private CollectionReference receiptRef = db.collection( "users" )
            .document( "BtliRhtVFiQntlH2Hp1gvjG59b32" )
            .collection( "Receipts" );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_receipt );

        mFirestoreList = findViewById(R.id.firestore_List);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        Query query = receiptRef = db.collection( "users" )
                .document( "BtliRhtVFiQntlH2Hp1gvjG59b32" )
                .collection( "Receipts" );

        FirestoreRecyclerOptions<ReceiptsModel> options = new FirestoreRecyclerOptions.Builder<ReceiptsModel>()
                .setQuery( query, ReceiptsModel.class )
                .build();

        //View holder class

        adapter = new ReceiptAdapter( options );
        mFirestoreList  = findViewById( R.id.firestore_List );
        mFirestoreList.setHasFixedSize( true );
        mFirestoreList.setLayoutManager( new LinearLayoutManager( this ) );
        mFirestoreList.setAdapter( adapter );

        new ItemTouchHelper( new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.deleteItem( viewHolder.getAdapterPosition() );

            }
        } ).attachToRecyclerView( mFirestoreList );

        adapter.setOnItemClickListener(new ReceiptAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                ReceiptsModel receiptsModel = documentSnapshot.toObject(ReceiptsModel.class);
                String id = documentSnapshot.getId();
                String path = documentSnapshot.getReference().getPath();
                Toast.makeText(ReceiptActivity.this,
                        "Position: " + position + " ID: " + id, Toast.LENGTH_SHORT).show();

            }
        });
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