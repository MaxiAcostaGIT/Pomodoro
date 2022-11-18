package com.ventana;

public class Temporizador {
    int minutos;
    int segundos;
    int minutosIniciales;
    boolean iniciar;

    public Temporizador(int min){
        this.minutosIniciales = min;
        this.minutos = min;
        this.segundos = 0;
        this.iniciar = false;
    }

    public void reducirTiempo(){
        this.segundos--;
        if (this.segundos < 0) {
            this.minutos--;
            if (this.minutos < 0) {
                this.minutos = 0;
                this.segundos = 0;
                return;
            }
            this.segundos = 59;
        }
    }

    public String tiempo(){
        String min = String.valueOf(this.minutos);
        String seg = String.valueOf(this.segundos);
        if (this.minutos<10){
            min = "0"+min;
        }
        if (this.segundos<10){
            seg = "0"+seg;
        }
        return min+":"+seg;
    }

    public void reiniciarTemporizador(){
        this.minutos = this.minutosIniciales;
        this.segundos = 0;
    }

    public boolean finTiempo(){
        if(this.segundos == 0 && this.minutos == 0){
            return true;
        }
        return false;
    }

    public void cambiarTiempo(int minutos){
        this.minutosIniciales = minutos;
        this.reiniciarTemporizador();
    }

    public boolean isIniciar(){
        return this.iniciar;
    }

    public void cambiarIniciar(boolean band){
        this.iniciar = band;
    }

    public void sumarSegundo(){
        this.segundos++;
        if (this.segundos >59){
            this.segundos = 59;
        }
    }

}
