package doctor_screens.doctor_fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tabib.R;

import java.util.Objects;

import doctor_screens.PatientProfileActivity;


public class FragmentQueryPatient extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_query_patient, container, false);
        Button btn_query = view.findViewById(R.id.btn_query);
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), PatientProfileActivity.class);
                startActivity(i);
                ((Activity) requireActivity()).overridePendingTransition(0, 0);

            }
        });
        return  view;
    }
}