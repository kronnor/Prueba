package com.example.usuarioactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.usuarioactivity.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGuardar.setOnClickListener(v -> {
            String nombre = binding.txtNombre.getText().toString();
            String clave = binding.txtClave.getText().toString();
            String email = binding.txtEmail.getText().toString();
            String rol = binding.txtRol.getText().toString();
            String rclave = binding.txtRclave.getText().toString();
            String remail = binding.txtREmail.getText().toString();


            if (clave.equals(rclave)){
                if(clave.length()>5){
                    if (email.equals(remail)){
                        if (rol.equalsIgnoreCase("admin"))
                        abrirActivityDetalle(nombre,clave,email,rol);

                    }else {
                        Context context = MainActivity.this;
                        CharSequence text = "debe ser el mismo email";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }

                }else{
                    Context context = MainActivity.this;
                    CharSequence text = "Minimo 5 caracteres";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }


            }else {
                Context context = MainActivity.this;
                CharSequence text ="CLAVES DEBEN COINCIDIR";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }





        });
    }
    private void abrirActivityDetalle(String nom,String cla,String emai,String rol){
        Intent intent = new Intent(this,ResultadoActivity.class);

        Usuarios usuario = new Usuarios(nom,rol,cla,emai);
        intent.putExtra(ResultadoActivity.USUARIO_KEY,usuario);
        startActivity(intent);

    }
}