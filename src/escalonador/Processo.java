package escalonador;

import enumConfig.EnumEstado;
import enumConfig.EnumTipo;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Processo extends Thread {

    public int pc = 0;
    public String nome;
    public EnumEstado estado = EnumEstado.PRONTO;
    public EnumTipo tipo;
    public int qtdePc;
    public int prioridade;
    public static Escalonador escalonador;

    public Processo(String nome, EnumTipo tipo, int qtdePc, int prioridade) {
        this.nome = nome;
        this.tipo = tipo;
        this.qtdePc = qtdePc;
        this.prioridade = prioridade;
    }

    @Override
    public void run() {        
        while (this.qtdePc > this.pc) {
            try {
                if (this.estado.equals(EnumEstado.EXECUTANDO)) {
                    this.pc++;
                    this.sleep(50);
                    escalonador.escalonarProcesso();
                } else {
                    synchronized (this) {
                        wait();
                    }
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return this.nome + "(" + this.pc + "/"+this.qtdePc+")";
    }
    
    public int getPrioridade(){
        return this.prioridade;
    }
    
    public void setPrioridade(int amount){
        this.prioridade = amount;
        System.out.println("Prioridade do processo ["+this.nome+"] diminuída para "+this.prioridade+".");
    }

}
