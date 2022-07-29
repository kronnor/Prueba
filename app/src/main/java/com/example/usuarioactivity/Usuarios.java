package com.example.usuarioactivity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuarios implements Parcelable {
    private String Rol;
    private String Clave;
    private String usario;
    private String Email;

    public Usuarios(String rol, String clave, String usario, String email) {
        Rol = rol;
        Clave = clave;
        this.usario = usario;
        Email = email;
    }

    protected Usuarios(Parcel in) {
        Rol = in.readString();
        Clave = in.readString();
        usario = in.readString();
        Email = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Rol);
        dest.writeString(Clave);
        dest.writeString(usario);
        dest.writeString(Email);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Usuarios> CREATOR = new Creator<Usuarios>() {
        @Override
        public Usuarios createFromParcel(Parcel in) {
            return new Usuarios(in);
        }

        @Override
        public Usuarios[] newArray(int size) {
            return new Usuarios[size];
        }
    };

    public String getRol() {
        return Rol;
    }

    public void setRol(String rol) {
        Rol = rol;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

    public String getUsario() {
        return usario;
    }

    public void setUsario(String usario) {
        this.usario = usario;
    }

    public String getEmail() {
        return Email;
    }

    public float NivelSeguridad(String clave) {
        float resp = 0;
        Pattern p = Pattern.compile("\\W");
        Matcher m = p.matcher(clave);
        while (m.find()) resp++;

        if (clave.length() >= 12 && resp >= 4) return 5;
        else if (clave.length() >= 10 && resp >= 2) return 4;
        else if (clave.length() >= 8 && resp >= 1) return 3;
        else if (clave.length() >= 8) return 2;
        else return 1;

    }

}
