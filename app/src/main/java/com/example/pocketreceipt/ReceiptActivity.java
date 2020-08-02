package com.example.pocketreceipt;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
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
    //declare Firebase variables
    private RecyclerView mFirestoreList;

//    private String convertDateToString (Date date) {
//        //change according to your supported formate
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy" );
//        return dateFormat.format(date);
//    }
    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //Declare my adapter
    private ReceiptAdapter adapter;
    private CollectionReference receiptRef = db.collection( "users" )
            .document( userID )
            .collection( "Receipts" );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_receipt );

        mFirestoreList = findViewById(R.id.firestore_List);
        setUpRecyclerView();
    }

    //Show Recycler view the path to query in Firestore to access all receipts and print them out in a cardview
    private void setUpRecyclerView() {
        Query query = receiptRef = db.collection( "users" )
                .document( userID )
                .collection( "Receipts" );
        //pass model class in query
        FirestoreRecyclerOptions<ReceiptsModel> options = new FirestoreRecyclerOptions.Builder<ReceiptsModel>()
                .setQuery( query, ReceiptsModel.class )
                .build();


        adapter = new ReceiptAdapter( options );
        mFirestoreList = findViewById( R.id.firestore_List );
        mFirestoreList.setHasFixedSize( true );
        mFirestoreList.setLayoutManager( new LinearLayoutManager( this ) );//responsible for aligning single items in the list
        mFirestoreList.setAdapter( adapter );

        //deleting receipt items by swiping either left or right 0 deletes from android and firebase
        new ItemTouchHelper( new ItemTouchHelper.SimpleCallback( 0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.deleteItem( viewHolder.getAdapterPosition() );

            }
        } ).attachToRecyclerView( mFirestoreList );
//set on click listener to tap on receipt list
        adapter.setOnItemClickListener(new ReceiptAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                ReceiptsModel model = documentSnapshot.toObject(ReceiptsModel.class);

                //using intent method to start a new activity and print out the fields related to my receipt layout
                Intent intent = new Intent(ReceiptActivity.this,DisplayActivity.class);
                intent.putExtra("date",model.getDate());
                intent.putExtra("store_location",model.getStore_location());
                intent.putExtra("store_no",model.getStore_no());
                intent.putExtra("item_1",model.getItem_1());
                intent.putExtra("item_2",model.getItem_2());
                intent.putExtra("item_1_price",model.getItem_1_price());
                intent.putExtra("item_2_price",model.getItem_2_price());
                intent.putExtra("store",model.getStore());
                intent.putExtra("total",model.getTotal());
                intent.putExtra("receiptNo",model.getReceiptNo());
                startActivity(intent);
            }
        } );
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