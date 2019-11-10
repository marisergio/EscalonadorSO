package escalonador;

import static escalonador.Escalonador.bloqueadoToPronto;
import static escalonador.Escalonador.bloqueados;
import static escalonador.Escalonador.executando;
import static escalonador.Escalonador.prontoToExetucando;
import static escalonador.Escalonador.prontos;
import static escalonador.Escalonador.qtdeEscalonamento;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Desbloqueador_Processos extends Thread {

    @Override
    public void run() {

        while (Escalonador.qtdeProcesso > Escalonador.finalizados.size()) {

            if (Escalonador.bloqueados.size() > 0) {
                Escalonador.bloqueadoToPronto();
                if (prontos.size() > 0) {
                    prontoToExetucando();
                    qtdeEscalonamento++;
//            imprimeStatusListas();
                    try {
                        synchronized (executando) {
                            executando.notifyAll();
                        }
                    } catch (Exception e) {
                    }
                }
            }
            try {

                Thread.sleep(6000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Desbloqueador_Processos.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
