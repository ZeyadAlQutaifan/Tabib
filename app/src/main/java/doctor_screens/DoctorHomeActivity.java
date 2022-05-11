package doctor_screens;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.tabib.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import doctor_screens.doctor_fragments.FragmentChats;
import doctor_screens.doctor_fragments.FragmentQueryPatient;
import doctor_screens.doctor_fragments.FragmentHome;


public class DoctorHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        BottomBar bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if (tabId == R.id.tab_home) {
                    FragmentHome fragment = new FragmentHome();

                    fragmentTransaction.replace(R.id.container, fragment);
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                }else if (tabId == R.id.tab_search){
                    FragmentQueryPatient fragment = new FragmentQueryPatient();

                    fragmentTransaction.replace(R.id.container, fragment);
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                }else if (tabId == R.id.tab_chats){
                    FragmentChats fragment = new FragmentChats();
                    fragmentTransaction.replace(R.id.container, fragment);
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                }
                fragmentTransaction.commit();
            }
        });

     }
    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentHome fragment = new FragmentHome();

        fragmentTransaction.replace(R.id.container, fragment);
        // fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        fragmentTransaction.commit();
        System.out.println("back");
    }
}