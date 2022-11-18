package com.ventana;
import javax.sound.sampled.*;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;


public class Ventana implements ActionListener{
    private JPanel Main;
    private JButton editarTrabajoBoton;
    private JButton editarDescansoBoton;
    private JLabel trabajoPomo;
    private JLabel descansoPomo;
    private JLabel tiempoTrabajo;
    private JLabel tiempoDescanso;
    private JButton iniciarTrabajoBoton;
    private JButton detenerTrabajoBoton;
    private JButton iniciarDescansoBoton;
    private JButton detenerDescansoBoton;
    private JLabel instruccionesLabel;
    private JLabel instrucciones1;
    private JLabel instrucciones2;
    private JLabel instrucciones3;
    private JComboBox idiomaComboBox;
    private JLabel instrucciones4;
    private JFrame frame;
    public Temporizador trabajo;
    public Temporizador descanso;
    public Idiomas idioma = new Idiomas();
    public String idiomaSeleccionado;
    private AudioInputStream audioInputStream;
    private Clip clip;
    private File guardado;
    private Scanner leerG;

    public Ventana() {
        this.frame = new JFrame("PomoTime | Productividad al máximo");
        this.frame.setContentPane(Main);
        this.frame.pack();
        this.frame.setResizable(false);
        this.frame.setSize(475, 475);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.frame.setVisible(true);
        this.leerGuardado();
        this.comboBox();
        this.cambiarIdioma(this.idiomaSeleccionado);
        this.cambiarTiempo();
        this.accionBotones();
        this.leeAudio();

        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt){
                int valor = JOptionPane.showOptionDialog(null, new JLabel(idioma.salidaTexto, JLabel.CENTER), idioma.salidaTitulo,
                        JOptionPane.DEFAULT_OPTION, -1,
                        null, idioma.opciones, idioma.opciones[0]);
                if (valor == JOptionPane.YES_NO_OPTION){
                    leerG.close();
                    try{
                        FileWriter guardar = new FileWriter(guardado);
                        guardar.write("MinutosTrabajo="+trabajo.minutosIniciales+"\n");
                        guardar.write("MinutosDescanso="+descanso.minutosIniciales+"\n");
                        guardar.write("Idioma="+idiomaSeleccionado+"\n");
                        guardar.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    System.exit(0);
                }
            }
        });

    }

    public void leeAudio() {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File("../GUI Test/Notificacion.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
    }

    public void leerGuardado(){
        try{
            this.guardado = new File("../Gui Test/save.txt");
            this.leerG = new Scanner(this.guardado);
            this.trabajo = new Temporizador(Integer.parseInt(leerG.nextLine().split("=")[1]));
            this.descanso = new Temporizador(Integer.parseInt(leerG.nextLine().split("=")[1]));
            this.idiomaSeleccionado = leerG.nextLine().split("=")[1];
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void accionBotones(){
        this.iniciarTrabajoBoton.addActionListener(this);
        this.detenerTrabajoBoton.addActionListener(this);
        this.iniciarDescansoBoton.addActionListener(this);
        this.detenerDescansoBoton.addActionListener(this);
        this.editarTrabajoBoton.addActionListener(this);
        this.editarDescansoBoton.addActionListener(this);
        this.idiomaComboBox.addActionListener(this);
    }

    public void comboBox(){
        this.idiomaComboBox.addItem("Español");
        this.idiomaComboBox.addItem("English");
        this.idiomaComboBox.addItem("Português");
        if (this.idiomaSeleccionado.equals("es")){
            this.idiomaComboBox.setSelectedIndex(0);
        }
        if (this.idiomaSeleccionado.equals("en")){
            this.idiomaComboBox.setSelectedIndex(1);
        }
        if (this.idiomaSeleccionado.equals("pr")){
            this.idiomaComboBox.setSelectedIndex(2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==this.iniciarTrabajoBoton){
            this.iniciarTiempo(this.iniciarTrabajoBoton,this.iniciarDescansoBoton,this.trabajo, this.descanso, this.idioma.infoTrabajoTexto);
        }
        if (e.getSource()==this.detenerTrabajoBoton){
            this.detenerTiempo(this.iniciarTrabajoBoton, this.trabajo);
        }
        if (e.getSource()==this.iniciarDescansoBoton){
            this.iniciarTiempo(this.iniciarDescansoBoton,this.iniciarTrabajoBoton,this.descanso,this.trabajo, this.idioma.infoDescansoTexto);
        }
        if (e.getSource()==this.detenerDescansoBoton){
            this.detenerTiempo(this.iniciarDescansoBoton, this.descanso);
        }
        if (e.getSource()==this.editarTrabajoBoton){
            this.editarTiempo(this.iniciarTrabajoBoton, this.trabajo, this.idioma.trabajoTitulo);
        }
        if (e.getSource()==this.editarDescansoBoton){
            this.editarTiempo(this.iniciarDescansoBoton, this.descanso, this.idioma.descansoTitulo);
        }
        if (e.getSource()==this.idiomaComboBox){
            if(this.idiomaComboBox.getSelectedItem()=="Español"){
                this.cambiarIdioma("es");
                this.idiomaSeleccionado = "es";
            }
            if(this.idiomaComboBox.getSelectedItem()=="English"){
                this.cambiarIdioma("en");
                this.idiomaSeleccionado = "en";
            }
            if(this.idiomaComboBox.getSelectedItem()=="Português"){
                this.cambiarIdioma("pr");
                this.idiomaSeleccionado = "pr";
            }
        }
    }

    public void iniciarTiempo(JButton boton1, JButton boton2, Temporizador t1, Temporizador t2, String texto){
        if (boton2.getText().equals(this.idioma.pausar)){
            JOptionPane.showOptionDialog(null, texto, this.idioma.infoTitulo, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, this.idioma.aceptar2, this.idioma.aceptar2[0]);
            this.detenerTiempo(boton2,t2);
            boton1.setText(this.idioma.pausar);
            t1.cambiarIniciar(true);
        }else {
            if (boton1.getText().equals(this.idioma.iniciar)) {
                boton1.setText(this.idioma.pausar);
                t1.cambiarIniciar(true);
            } else {
                boton1.setText(this.idioma.iniciar);
                t1.cambiarIniciar(false);
                t1.sumarSegundo();
            }
        }
    }

    public void detenerTiempo(JButton boton, Temporizador t){
        if (boton.getText().equals(this.idioma.pausar)){
            boton.setText(this.idioma.iniciar);
        }
        t.cambiarIniciar(false);
        t.reiniciarTemporizador();
    }

    public void editarTiempo(JButton boton1, Temporizador t, String titulo){
        try {
            JTextField texto = new JTextField();
            JLabel label = new JLabel(this.idioma.editarMinutos);
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
            panel.add(label);
            panel.add(texto);
            int condicion = JOptionPane.showOptionDialog(null,panel,titulo,JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,this.idioma.aceptar,this.idioma.aceptar[0]);
            if (condicion == JOptionPane.YES_OPTION) {
                int auxiliar = Integer.parseInt(texto.getText());
                if (auxiliar > 60 || auxiliar < 1) {
                    JOptionPane.showOptionDialog(null,this.idioma.errorM1,this.idioma.errorTitulo,JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,this.idioma.aceptar2,this.idioma.aceptar2[0]);
                } else {
                    if (boton1.getText().equals(this.idioma.pausar)) {
                        boton1.setText(this.idioma.iniciar);
                    }
                    t.cambiarIniciar(false);
                    t.cambiarTiempo(auxiliar);
                    this.cambiarTiempo();
                }
            }
        }catch (Exception exception){
            if (exception.getLocalizedMessage().contains("For input string")) {
                JOptionPane.showOptionDialog(null,this.idioma.errorM2,this.idioma.errorTitulo,JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,this.idioma.aceptar2,this.idioma.aceptar2[0]);
            }
        }
    }

    public void cambiarTiempo(){
        this.tiempoTrabajo.setText(this.trabajo.tiempo());
        this.tiempoDescanso.setText(this.descanso.tiempo());
    }

    public void finalTiempo(){
        if (this.trabajo.finTiempo()){
            this.procesoFinalTiempo(this.iniciarTrabajoBoton, this.iniciarDescansoBoton, this.trabajo, this.descanso,this.idioma.finTrabajoTexto);
        }
        if (this.descanso.finTiempo()){
            this.procesoFinalTiempo(this.iniciarDescansoBoton,this.iniciarTrabajoBoton,this.descanso,this.trabajo,this.idioma.finDescansoTexto);
        }
    }

    public void procesoFinalTiempo(JButton boton1, JButton boton2, Temporizador t1, Temporizador t2,String texto){
        boton1.setText(this.idioma.iniciar);
        t1.cambiarIniciar(false);
        t1.reiniciarTemporizador();

        clip.loop(60);
        JOptionPane.showOptionDialog(null, texto, this.idioma.infoTitulo, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, this.idioma.aceptar2, this.idioma.aceptar2[0]);
        clip.stop();

        t2.cambiarIniciar(true);
        boton2.setText(this.idioma.pausar);
    }

    public void cambiarIdioma(String idioma){
        this.idioma.elejirIdioma(idioma);
        this.trabajoPomo.setText(this.idioma.trabajoTitulo);
        this.descansoPomo.setText(this.idioma.descansoTitulo);
        this.instruccionesLabel.setText(this.idioma.instruccionesTitulo);
        this.instrucciones1.setText(this.idioma.instrucciones1);
        this.instrucciones2.setText(this.idioma.instrucciones2);
        this.instrucciones3.setText(this.idioma.instrucciones3);
        this.instrucciones4.setText(this.idioma.instrucciones4);
        this.editarTrabajoBoton.setText(this.idioma.editarTrabajo);
        this.editarDescansoBoton.setText(this.idioma.editarDescanso);
        this.iniciarTrabajoBoton.setText(this.idioma.iniciar);
        this.iniciarDescansoBoton.setText(this.idioma.iniciar);
        this.detenerTrabajoBoton.setText(this.idioma.detener);
        this.detenerDescansoBoton.setText(this.idioma.detener);
    }
}
