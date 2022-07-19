package com.example.usuarioactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.usuarioactivity.databinding.ActivityResultadoBinding;

public class ResultadoActivity extends AppCompatActivity {

    public static final String USUARIO_KEY = "Usuarios";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityResultadoBinding binding = ActivityResultadoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras = getIntent().getExtras();
        Usuarios user = extras.getParcelable(USUARIO_KEY);
        binding.setUsuarios(user);
    }
}