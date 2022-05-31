package doctor_screens.doctor_fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tabib.R;

import doctor_screens.PatientSearchActivity;


public class FragmentQueryPatient extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_query_patient, container, false);
        Button btn_query = view.findViewById(R.id.btn_query);
        EditText etPatientSSN = view.findViewById(R.id.etPatientSSN);
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), PatientSearchActivity.class);
                i.putExtra("ssn" , etPatientSSN.getText().toString());
                startActivity(i);
                ((Activity) requireActivity()).overridePendingTransition(0, 0);

            }
        });
        return  view;
    }
}