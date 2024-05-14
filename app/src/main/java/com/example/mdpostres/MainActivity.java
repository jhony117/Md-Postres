package com.example.mdpostres;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;


import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mdpostres.databinding.ActivityMainBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private BottomSheetBehavior mBottomSheetBehavior;

    private NavController navControler;

    ActivityMainBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

      Toolbar toolbar;
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      navControler = Navigation.findNavController(this, R.id.nav_host_fragment);

        AppBarConfiguration appBarConfiguration= new AppBarConfiguration.Builder(navControler.getGraph())
                .build();

        NavigationUI.setupWithNavController(toolbar, navControler, appBarConfiguration);

        navControler.addOnDestinationChangedListener((controler, destination, arguments ) -> {
           toolbar.setTitle(destination.getLabel());
           toolbar.setNavigationIcon(null);
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


  });

        ConstraintLayout bottomSheet = findViewById(R.id.bottom_sheet);

        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }


    @Override
    protected void onStart() {
        super.onStart();

        ImageButton btnClose = findViewById(R.id.btnClose);
        MaterialButton btnExit = findViewById(R.id.btnExit);

        btnClose.setOnClickListener(v ->
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN));
        btnExit.setOnClickListener(v -> finish());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navControler) ||
                super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {


        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        super.onBackPressed();
    }

}