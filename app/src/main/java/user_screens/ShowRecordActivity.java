package user_screens;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tabib.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.Objects;

import Modules.Disease;
import Modules.Record;

public class ShowRecordActivity extends AppCompatActivity {
    FirestoreRecyclerAdapter adapter;
    Query query;
    RecyclerView recyclerView;
    private FirebaseFirestore mFirestore;
    private String strType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_record);
        strType = getIntent().getStringExtra("type");
        recyclerView = findViewById(R.id.recyclerView);
        mFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        showRecord();
    }

    private void showRecord() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        query = mFirestore.collection("Users").document(Objects.requireNonNull(mAuth.getUid())).collection("Records")
                .whereEqualTo("type" , strType);
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (!value.getDocuments().toString().equals("[]")) {
                    FirestoreRecyclerOptions<Record> options = new FirestoreRecyclerOptions.Builder<Record>()
                            .setQuery(query, Record.class)
                            .build();
                    adapter = new FirestoreRecyclerAdapter<Record,  ShowRecordActivity.RecordViewHolder>(options) {

                        @Override
                        protected void onBindViewHolder(@NonNull  ShowRecordActivity.RecordViewHolder holder, int position, @NonNull Record model) {
                            String id = getSnapshots().getSnapshot(position).getId();
                            holder.item_txt_type.setText(model.getType());
                            holder.item_txt_date.setText(model.getDate());
                            holder.item_txt_notes.setText(model.getAdditional_notes());
                            Glide.with(getApplication()).load(model.getImageUri()).into(holder.item_img_record);
                            holder.item_btn_delete.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

                        }

                        @NonNull
                        @Override
                        public ShowRecordActivity.RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            View view = LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.item_record, parent, false);
                            return new  ShowRecordActivity.RecordViewHolder(view);
                        }


                    };
                    try {
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
                        SnapHelper snapHelper = new PagerSnapHelper();
                        recyclerView.setLayoutManager(layoutManager);
                        snapHelper.attachToRecyclerView(recyclerView);
                    } catch (Exception e) {
                        Toast.makeText(getApplication(), "تحديث", Toast.LENGTH_SHORT).show();

                    }

                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
                    recyclerView.setAdapter(adapter);
                    adapter.startListening();


                }
            }
        });
    }
    private class RecordViewHolder extends RecyclerView.ViewHolder{
private final RoundedImageView item_img_record ;
private final TextView item_txt_type;
private final TextView item_txt_date;
private final TextView item_txt_notes;
private final Button item_btn_delete ;

        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            item_img_record = itemView.findViewById(R.id.item_img_record);
            item_txt_type = itemView.findViewById(R.id.item_txt_type);
            item_txt_date = itemView.findViewById(R.id.item_txt_date);
            item_txt_notes = itemView.findViewById(R.id.item_txt_notes);
            item_btn_delete = itemView.findViewById(R.id.item_btn_delete);


        }
    }
}