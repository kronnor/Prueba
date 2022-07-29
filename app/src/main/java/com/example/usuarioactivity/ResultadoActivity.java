package com.example.usuarioactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.usuarioactivity.databinding.ActivityResultadoBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultadoActivity extends AppCompatActivity {

    public static final String USUARIO_KEY = "Usuarios";
    public static final String BITMAP_KEY ="Bitmap";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityResultadoBinding binding = ActivityResultadoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Bundle extras = getIntent().getExtras();
        Usuarios user = extras.getParcelable(USUARIO_KEY);
        //binding.rtbValoracioin.setRating(Usuarios.Ni);

        binding.setUsuarios(user);
        Bitmap bitmap = extras.getParcelable(BITMAP_KEY);

        String contra = user.getClave();

        float valor = 1;

        int st = 0;
        String seguridad = "";

        Pattern p = Pattern.compile("\\W");
        Matcher m = p.matcher(contra);
        while (m.find()) st++;

        if(contra.length()>=12&&st>=4){
            valor = 5;
            seguridad = "La clave de seguridad se considera Alta";
        }else if(contra.length()>=10&&st>=2){
            valor = 4;
            seguridad = "La clave de seguridad se considera Media-Alta";
        }else if(contra.length()>=8&&st>=1){
            valor = 3;
            seguridad = "La clave de seguridad se considera Media";
        }else if(contra.length()>=8){
            valor = 2;
            seguridad = "La clave de seguridad se considera Baja";
        }else{
            valor = 1;
            seguridad = "La clave de seguridad se considera Insegura";
        }

        if (bitmap!=null){
            binding.imgUserR.setImageBitmap(bitmap);
        }

        binding.txtNombreD.setText(user.getUsario());
        binding.rtbValoracioin.setRating(valor);
        binding.txtMensaje.setText(seguridad);
        binding.txtEmail.setText(user.getEmail());
       // binding.txt.setText(registro.getRol());




    }
}