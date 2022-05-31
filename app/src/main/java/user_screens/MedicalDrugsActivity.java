package user_screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.tabib.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import Modules.Medic;

public class MedicalDrugsActivity extends AppCompatActivity {
    FirestoreRecyclerAdapter adapter;
    Query query;
    RecyclerView recyclerView;
    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_drugs);
        recyclerView = findViewById(R.id.recyclerView);
        mFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        showMedics();

    }

    public void
    toAddMedic(View view) {
        startActivity(new Intent(getApplication(), AddMedicActivity.class));
    }

    private void showMedics() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        query = mFirestore.collection("Users").document(mAuth.getUid()).collection("Medics");
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (!value.getDocuments().toString().equals("[]")) {
                    FirestoreRecyclerOptions<Medic> options = new FirestoreRecyclerOptions.Builder<Medic>()
                            .setQuery(query, Medic.class)
                            .build();
                    adapter = new FirestoreRecyclerAdapter<Medic, MedicalDrugsActivity.MedicViewHolder>(options) {

                        @NonNull
                        @Override
                        public MedicalDrugsActivity.MedicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            View view = LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.item_medic, parent, false);
                            return new MedicalDrugsActivity.MedicViewHolder(view);
                        }

                        @Override
                        protected void onBindViewHolder(@NonNull MedicalDrugsActivity.MedicViewHolder holder, int position, @NonNull Medic model) {
                            String id = getSnapshots().getSnapshot(position).getId();
                            holder.item_txt_name.setText(model.getName());
                            holder.item_txt_uses.setText(model.getMedic_uses_count());
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

    class MedicViewHolder extends RecyclerView.ViewHolder {

        private final TextView item_txt_name;
        private final TextView item_txt_uses;

        public MedicViewHolder(@NonNull View itemView) {
            super(itemView);
            item_txt_name = itemView.findViewById(R.id.item_txt_name);
            item_txt_uses = itemView.findViewById(R.id.item_txt_uses);

        }
    }

}