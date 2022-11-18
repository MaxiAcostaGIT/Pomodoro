import com.ventana.Ventana;
import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Ventana ventana = new Ventana();
        while(true){
            if(ventana.trabajo.isIniciar()){
                while(!ventana.trabajo.finTiempo() && ventana.trabajo.isIniciar()){
                    ventana.cambiarTiempo();
                    ventana.trabajo.reducirTiempo();
                    sleep(1000);
                }
            }
            if(ventana.descanso.isIniciar()){
                while(!ventana.descanso.finTiempo() && ventana.descanso.isIniciar()){
                    ventana.cambiarTiempo();
                    ventana.descanso.reducirTiempo();
                    sleep(1000);
                }
            }
            ventana.cambiarTiempo();
            ventana.finalTiempo();

        }
    }
}
