package com.ventana;

import java.io.File;
import java.util.Scanner;

public class Idiomas {
    File archivo;
    Scanner leerArchivo;

    public String trabajoTitulo;
    public String descansoTitulo;
    public String editarTrabajo;
    public String editarDescanso;
    public String iniciar;
    public String pausar;
    public String detener;
    public String salidaTitulo;
    public String salidaTexto;
    public String instruccionesTitulo;
    public String instrucciones1;
    public String instrucciones2;
    public String instrucciones3;
    public String instrucciones4;
    public String editarMinutos;
    public String errorTitulo;
    public String errorM1;
    public String errorM2;
    public String finTrabajoTexto;
    public String finDescansoTexto;
    public String infoTitulo;
    public String infoTrabajoTexto;
    public String infoDescansoTexto;
    public String[] opciones;
    public String[] aceptar = new String[2];
    public String[] aceptar2 = new String[1];

    public void elegirIdioma(String idioma){
        String ruta = "../GUI Test/idiomas/"+idioma+".txt";
        try{
            archivo = new File(ruta);
            leerArchivo = new Scanner(archivo);

            this.trabajoTitulo = leerArchivo.nextLine();
            this.descansoTitulo = leerArchivo.nextLine();
            this.editarTrabajo = leerArchivo.nextLine();
            this.editarDescanso = leerArchivo.nextLine();
            this.iniciar = leerArchivo.nextLine();
            this.pausar = leerArchivo.nextLine();
            this.detener = leerArchivo.nextLine();
            this.salidaTitulo = leerArchivo.nextLine();
            this.salidaTexto = leerArchivo.nextLine();
            this.instruccionesTitulo = leerArchivo.nextLine();
            this.instrucciones1 = leerArchivo.nextLine();
            this.instrucciones2 = leerArchivo.nextLine();
            this.instrucciones3 = leerArchivo.nextLine();
            this.instrucciones4 = leerArchivo.nextLine();
            this.editarMinutos = leerArchivo.nextLine();
            this.errorTitulo = leerArchivo.nextLine();
            this.errorM1 = leerArchivo.nextLine();
            this.errorM2 = leerArchivo.nextLine();
            this.finTrabajoTexto = leerArchivo.nextLine();
            this.finDescansoTexto = leerArchivo.nextLine();
            this.infoTitulo = leerArchivo.nextLine();
            this.infoTrabajoTexto = leerArchivo.nextLine();
            this.infoDescansoTexto = leerArchivo.nextLine();
            this.opciones = leerArchivo.nextLine().split("/");
            this.aceptar = leerArchivo.nextLine().split("/");
            this.aceptar2[0] = aceptar[0];

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                leerArchivo.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }
}
