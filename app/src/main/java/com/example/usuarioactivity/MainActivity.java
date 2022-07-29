package com.example.usuarioactivity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.renderscript.ScriptGroup;
import android.widget.Toast;

import com.example.usuarioactivity.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Bitmap bitmap;
    ActivityResultLauncher <Intent> activityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
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
        laucher();
        binding.imgUsuario.setOnClickListener(v -> {
            abrircamara();
        });
    }

    private void abrircamara(){
        Intent camaIntent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activityResultLauncher.launch(camaIntent);

    }

    public void laucher(){
    activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode()==RESULT_OK){
                if (result.getData()!=null){
                    bitmap= result.getData().getExtras().getParcelable("data");
                    binding.imgUsuario.setImageBitmap(bitmap);
                }
            }
        }
    });
    }

    private void abrirActivityDetalle(String nom,String cla,String emai,String rol){
        Intent intent = new Intent(this,ResultadoActivity.class);

        Usuarios usuario = new Usuarios(nom,rol,cla,emai);
        intent.putExtra(ResultadoActivity.USUARIO_KEY,usuario);
        intent.putExtra(ResultadoActivity.BITMAP_KEY,bitmap);
        startActivity(intent);

    }
}