package escalonador;

import enumConfig.EnumTipo;
import enumConfig.EnumTipoEscalonamento;
import interfaceUsuario.Dashboard;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Processador {
    public static void main(String[] args) throws InterruptedException {
        Dashboard dash = new Dashboard();
        JFrame f = new JFrame();
        f.setSize(970, 450); 
        f.setVisible(true);

        //Now add your JPanel (JPanel yourJPanelName = new JPanel();)
        f.add(dash);
        
        /*Escalonador escalonador = new Escalonador();
        Processo.escalonador = escalonador;
        Escalonador.qtdeProcesso = 4;
        Escalonador.tipoEscalonamento = EnumTipoEscalonamento.PRIORIDADE;

        Processo p1 = new Processo("NetBeans", EnumTipo.CPU, 4, 5);
        Processo p2 = new Processo("QAcademico", EnumTipo.IO, 6, 10);
        Processo p3 = new Processo("Familydoc", EnumTipo.CPU, 3, 8);
        Processo p4 = new Processo("SOAE", EnumTipo.IO, 4, 1);

        Escalonador.prontos.add(p1);
        Escalonador.prontos.add(p2);
        Escalonador.prontos.add(p3);
        Escalonador.prontos.add(p4);
        
        escalonador.escalonarProcesso();
        
        Desbloqueador_Processos thread = new Desbloqueador_Processos();
        thread.start();

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        
        p1.join();
        p2.join();
        p3.join();
        p4.join();
        thread.join();
        
        Escalonador.imprimeStatusListas();*/
            
        
    }
}
