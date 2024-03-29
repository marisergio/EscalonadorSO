package escalonador;

import enumConfig.EnumEstado;
import enumConfig.EnumTipo;
import enumConfig.EnumTipoEscalonamento;
import interfaceUsuario.Dashboard;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Escalonador {

    public static List<Processo> prontos = new ArrayList<>();
    public static List<Processo> bloqueados = new ArrayList<>();
    public static List<Processo> finalizados = new ArrayList<>();
    public static Processo executando;
    public static EnumTipoEscalonamento tipoEscalonamento;
    public static int qtdeProcesso;
    public static int qtdeEscalonamento = 0;

    public static void imprimeStatusListas() {
        System.out.println("--------------- ESCALONAMENTO " + qtdeEscalonamento + " ---------------");
        System.out.println("PRONTOS: " + prontos.size());
        for (Processo pronto : prontos) {
            System.out.print(" | " + pronto.toString());
        }
        System.out.println("\nBLOQUEADOS: " + bloqueados.size());
        for (Processo bloqueado : bloqueados) {
            System.out.print(" | " + bloqueado.toString());
        }
        System.out.println("\nFINALIZADOS: " + finalizados.size());
        for (Processo finalizado : finalizados) {
            System.out.print(" | " + finalizado.toString());
        }
        if (executando != null) {
            System.out.println("\nEXECUTANDO: " + executando.toString());
        } else {
            System.out.println("\nEXECUTANDO: null");
        }
        System.out.println("\n");
    }
    
    public static void RoundRobin(){
        if (prontos.size() > 0) {
            prontoToExetucando(0);
            qtdeEscalonamento++;
            imprimeStatusListas();
            Dashboard.refreshGUI();
            try {
                synchronized (executando) {
                    executando.notifyAll();
                }
            } catch (Exception e) {
            }
        }
    }
    
    public static void Prioridade(){
        int processNumber = 0;
        if (prontos.size() > 0) {
            if (prontos.size() > 1){
                int higher = 0;        
                for (Processo p : prontos){            
                    if(p.getPrioridade() >= higher){
                        higher = p.getPrioridade();
                        processNumber = prontos.indexOf(p);
                    }
                }
            }
            prontoToExetucando(processNumber);
            qtdeEscalonamento++;
            imprimeStatusListas();
            Dashboard.refreshGUI();
            try {
                synchronized (executando) {
                    executando.notifyAll();
                }
               } catch (Exception e) {
            }
        }
    }

    public static void bloqueadoToPronto() {
        Processo p = bloqueados.get(0);
        p.estado = EnumEstado.PRONTO;
        prontos.add(p);
        bloqueados.remove(0);
    }

    public static void executandoToBloqueado() {
        executando.estado = EnumEstado.BLOQUEADO;
        bloqueados.add(executando);
    }

    public static void prontoToExetucando(int processNumber) {
        executando = prontos.get(processNumber);
        if (executando.getPrioridade() >= 1){
            executando.setPrioridade(executando.getPrioridade() - 1);
        }
        executando.estado = EnumEstado.EXECUTANDO;
        prontos.remove(prontos.get(processNumber));
    }

    public static void executandoToPronto() {
        executando.estado = EnumEstado.PRONTO;
        prontos.add(executando);
    }

    public static void executandoToFinalizado() {
        executando.estado = EnumEstado.FINALIZADO;
        finalizados.add(executando);
        Dashboard.refreshGUI();
    }

    public synchronized void escalonarProcesso() {
        if (this.executando != null) {
            if (executando.pc >= executando.qtdePc) {
                executandoToFinalizado();
            } else if (executando.tipo.equals(EnumTipo.CPU)) {
                this.executandoToPronto();
            } else {
                this.executandoToBloqueado();
            }
        }

        // Zerando o executando
        executando = null;
          
        if (this.tipoEscalonamento == EnumTipoEscalonamento.ROUNDROBIN){
            RoundRobin();
        }else if(this.tipoEscalonamento == EnumTipoEscalonamento.PRIORIDADE){
            Prioridade();
        }
    }
}
