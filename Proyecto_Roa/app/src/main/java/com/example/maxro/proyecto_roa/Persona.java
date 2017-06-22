package com.example.maxro.proyecto_roa;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by maxro on 20/06/2017.
 */

public class Persona implements Parcelable{

    private long id;
    private String nomPer;
    private String apePer;
    private String dirPer;
    private String edadPer;
    private String dniPer ;
    private Date fecNacPer;

    protected Persona(Parcel in) {
        id = in.readLong();
        nomPer = in.readString();
        apePer = in.readString();
        dirPer = in.readString();
        edadPer = in.readString();
        dniPer = in.readString();
    }

    public static final Creator<Persona> CREATOR = new Creator<Persona>() {
        @Override
        public Persona createFromParcel(Parcel in) {
            return new Persona(in);
        }

        @Override
        public Persona[] newArray(int size) {
            return new Persona[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomPer() {
        return nomPer;
    }

    public void setNomPer(String nomPer) {
        this.nomPer = nomPer;
    }

    public String getApePer() {
        return apePer;
    }

    public void setApePer(String apePer) {
        this.apePer = apePer;
    }

    public String getDirPer() {
        return dirPer;
    }

    public void setDirPer(String dirPer) {
        this.dirPer = dirPer;
    }

    public String getEdadPer() {
        return edadPer;
    }

    public void setEdadPer(String edadPer) {
        this.edadPer = edadPer;
    }

    public String getDniPer() {
        return dniPer;
    }

    public void setDniPer(String dniPer) {
        this.dniPer = dniPer;
    }

    public Date getFecNacPer() {
        return fecNacPer;
    }

    public void setFecNacPer(Date fecNacPer) {
        this.fecNacPer = fecNacPer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(nomPer);
        dest.writeString(apePer);
        dest.writeString(dirPer);
        dest.writeString(edadPer);
        dest.writeString(dniPer);
    }
}
