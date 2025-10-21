/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author Administrator
 */
public class Mahasiswa {
    private int id;
    private String nim;
    private String nama;
    private int semester;
    private float ipk;

    public Mahasiswa(String nim, String nama, int semester, float ipk) {
        this.nim = nim;
        this.nama = nama;
        this.semester = semester;
        this.ipk = ipk;
    }

    
    public Mahasiswa(int id, String nim, String nama, int semester, float ipk) {
        this.id = id;
        this.nim = nim;
        this.nama = nama;
        this.semester = semester;
        this.ipk = ipk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public float getIpk() {
        return ipk;
    }

    public void setIpk(float ipk) {
        this.ipk = ipk;
    }
    
}
