package doctor_screens;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.tabib.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import doctor_screens.doctor_fragments.FragmentTest1;
import doctor_screens.doctor_fragments.FragmentTest2;


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

                if (tabId == R.id.tab_favorites) {
                    FragmentTest1 fragment = new FragmentTest1();
                    fragmentTransaction.replace(R.id.container, fragment);
                   // fragmentTransaction.addToBackStack(fragment.toString());
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                }else if (tabId == R.id.tab_recents){
                    FragmentTest2 fragment = new FragmentTest2();
                    fragmentTransaction.replace(R.id.container, fragment);
                   // fragmentTransaction.addToBackStack(fragment.toString());
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                }
                fragmentTransaction.commit();
            }
        });

     }
//    @Override
//    public void onBackPressed() {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        FragmentTest2 fragment = new FragmentTest2();
//
//        fragmentTransaction.replace(R.id.container, fragment);
//        // fragmentTransaction.addToBackStack(fragment.toString());
//        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
//        fragmentTransaction.commit();
//        System.out.println("back");
//    }
}