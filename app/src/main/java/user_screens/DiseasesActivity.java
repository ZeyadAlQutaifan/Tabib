package user_screens;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabib.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

import Modules.Disease;

public class DiseasesActivity extends AppCompatActivity {
    FirestoreRecyclerAdapter adapter;
    Query query;
    RecyclerView recyclerView;
    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases);
        recyclerView = findViewById(R.id.recyclerView);
        mFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        showDiseases();
    }

    public void toAddDisease(View view) {
        startActivity(new Intent(getApplication() , AddDiseaseActivity.class));
    }
    private void showDiseases(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        query = mFirestore.collection("Users").document(Objects.requireNonNull(mAuth.getUid())).collection("Diseases");
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (!value.getDocuments().toString().equals("[]")) {
                    FirestoreRecyclerOptions<Disease> options = new FirestoreRecyclerOptions.Builder<Disease>()
                            .setQuery(query, Disease.class)
                            .build();
                    adapter = new FirestoreRecyclerAdapter<Disease, DiseasesActivity.DiseasesViewHolder>(options) {

                        @Override
                        protected void onBindViewHolder(@NonNull DiseasesViewHolder holder, int position, @NonNull Disease model) {
                            String id = getSnapshots().getSnapshot(position).getId();
                            holder.item_txt_name.setText(model.getName());
                        }

                        @NonNull
                        @Override
                        public DiseasesActivity.DiseasesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            View view = LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.item_diseases, parent, false);
                            return new DiseasesActivity.DiseasesViewHolder(view);
                        }


                    };
                    try {
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
                        SnapHelper snapHelper = new PagerSnapHelper();
                        recyclerView.setLayoutManager(layoutManager);
                        snapHelper.attachToRecyclerView(recyclerView);
                    } catch (Exception e) {
                        Toast.makeText(getApplication(), "??????????", Toast.LENGTH_SHORT).show();

                    }

                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
                    recyclerView.setAdapter(adapter);
                    adapter.startListening();


                }
            }
        });
    }
    private static class DiseasesViewHolder extends RecyclerView.ViewHolder{

        private final TextView item_txt_name;
        public DiseasesViewHolder(@NonNull View itemView) {
            super(itemView);
            item_txt_name = itemView.findViewById(R.id.item_txt_name);
        }
    }
}