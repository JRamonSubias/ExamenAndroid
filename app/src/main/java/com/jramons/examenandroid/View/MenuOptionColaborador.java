package com.jramons.examenandroid.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.jramons.examenandroid.Model.Providers.AuthProvider;
import com.jramons.examenandroid.R;
import com.jramons.examenandroid.ViewModel.FileViewModel;

public class MenuOptionColaborador extends Fragment {

    private Button btnColaboradores, btnAddColaboradores;
    private ImageView btnSetting;
    private AuthProvider mAuth;
    private long dowloadid;

    private FileViewModel viewModel;

    public MenuOptionColaborador() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = AuthProvider.getInstance();
        viewModel = new ViewModelProvider(this).get(FileViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_choose_option, container, false);
        initUI(view);
        btnColaboradores.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.listColaboradores);
        });

        btnAddColaboradores.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.addColaboradores);
        });

        btnSetting.setOnClickListener(v -> {
            showMenu(v);
        });


        return view;

    }





    private void showMenu(View v){

        PopupMenu popupMenu = new PopupMenu(getContext(),v);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_settings,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            switch ((item.getItemId())){
                case R.id.menu_log_out:
                    mAuth.logOut();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    break;
                default: return false;
            }
            return true;
        });
        popupMenu.show();
    }

    private void initUI(View view) {
        btnColaboradores = view.findViewById(R.id.buttonMyColaboradores);
        btnAddColaboradores = view.findViewById(R.id.buttonToAddColaborador);
        btnSetting = view.findViewById(R.id.img_settings);
    }
}