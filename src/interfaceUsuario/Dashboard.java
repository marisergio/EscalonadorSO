/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceUsuario;

import enumConfig.EnumTipo;
import escalonador.Escalonador;
import escalonador.Processo;
import java.awt.GridLayout;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author arthurcvm
 */
public class Dashboard extends javax.swing.JPanel {
 
    private static Escalonador escalonador;
    private static DefaultTableModel modelExe;
    private static DefaultTableModel modelProntos;
    private static DefaultTableModel modelBloq;
    private static DefaultTableModel modelFin;

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        escalonador = new Escalonador();
        Processo.escalonador = escalonador;
        Escalonador.qtdeProcesso = 4;
        initComponents();
        modelExe = (DefaultTableModel) tableExe.getModel();
        modelProntos = (DefaultTableModel) tableProntos.getModel();
        modelBloq = (DefaultTableModel) tableBloq.getModel();
        modelFin = (DefaultTableModel) tableFin.getModel();
    }
    
    private static void display() {
        
        new Thread() {
            //Sobrescreve o método que roda a thread
            @Override
            public void run() {
                String[] items = {"CPU", "IO"};
                JComboBox<String> combo = new JComboBox<>(items);
                JTextField field1 = new JTextField("Nome do processo");
                JTextField field2 = new JTextField("100");
                JPanel panel = new JPanel(new GridLayout(0, 1));
                panel.add(combo);
                panel.add(new JLabel("Nome:"));
                panel.add(field1);
                panel.add(new JLabel("Quantidade de ciclos:"));
                panel.add(field2);
                int result = JOptionPane.showConfirmDialog(null, panel, "Novo Processo",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {

                    String nome = field1.getText();
                    int qtdCiclos = Integer.parseInt(field2.getText());

                    if(combo.getSelectedIndex() == 0){
                        Processo processo = new Processo(nome, EnumTipo.CPU, qtdCiclos);
                        addProcess(processo);
                    }
                    else{
                        Processo processo = new Processo(nome, EnumTipo.IO, qtdCiclos);
                        addProcess(processo);
                    }
//                    System.out.println(combo.getSelectedItem()
//                        + " " + field1.getText()
//                        + " " + field2.getText());

                    modelExe.setNumRows(0);
                    modelFin.setNumRows(0);
                    for(Processo p : Escalonador.finalizados){
                        Object[] linhaFinalizados = {"###", p.nome,
                            p.tipo, p.pc+"/"
                                +p.qtdePc};
                        
                        modelFin.addRow(linhaFinalizados);
                    }
                } else {
                    System.out.println("Cancelled");
                }
            }
        }.start(); //executa a thread    
    }
    
    private static void addProcess(Processo processo){
        Escalonador.prontos.add(processo);
        
        escalonador.escalonarProcesso();
        
        processo.start();
        refreshGUI();
        try {
            processo.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
//        Escalonador.imprimeStatusListas();
        
    }
    
    private static void refreshGUI(){
        new Thread() {
            //Sobrescreve o método que roda a thread
            @Override
            public void run() {
                int qtdEscalonamento = -1;
                while(qtdEscalonamento < Escalonador.qtdeEscalonamento){
                    qtdEscalonamento = Escalonador.qtdeEscalonamento;
                    
                    Object[] linhaExe = {"###", Escalonador.executando.nome,
                        Escalonador.executando.tipo, Escalonador.executando.pc+"/"
                            +Escalonador.executando.qtdePc};
                    
                    if(modelExe.getRowCount() > 0){
                        modelExe.setNumRows(0);
                    }
                    modelExe.addRow(linhaExe);
                    
                    if( modelProntos.getRowCount() > 0){
                        modelProntos.setNumRows(0);
                    }
                    
                    List<Processo> prontos = Escalonador.prontos;
                    for(Processo p : prontos){
                        Object[] linhaProntos = {"###", p.nome,
                            p.tipo, p.pc+"/"
                                +p.qtdePc};
                        
                        modelProntos.addRow(linhaProntos);
                    }
                    
                    if( modelBloq.getRowCount() > 0){
                        modelBloq.setNumRows(0);
                    }
                    for(Processo p : Escalonador.bloqueados){
                        Object[] linhaBloqueados = {"###", p.nome,
                            p.tipo, p.pc+"/"
                                +p.qtdePc};
                        
                        modelBloq.addRow(linhaBloqueados);
                    }
                    
//                    for(Processo p : Escalonador.finalizados){
//                        Object[] linhaFinalizados = {"###", p.nome,
//                            p.tipo, p.pc+"/"
//                                +p.qtdePc};
//                        
//                        modelFin.addRow(linhaFinalizados);
//                    }
                    
                    try {
                        this.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start(); //executa a thread  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        novoProcButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBloq = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableProntos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableExe = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableFin = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();

        novoProcButton.setText("Novo processo");
        novoProcButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novoProcButtonActionPerformed(evt);
            }
        });

        tableBloq.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PID", "Nome", "Tipo", "Ciclos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableBloq);

        tableProntos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PID", "Nome", "Tipo", "Ciclos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableProntos);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("Prontos");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("Bloqueados");

        tableExe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PID", "Nome", "Tipo", "Ciclos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableExe);

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("Executando");

        tableFin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PID", "Nome", "Tipo", "Ciclos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tableFin);

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setText("Finalizados");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addContainerGap(19, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(novoProcButton)
                        .addGap(43, 43, 43))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(novoProcButton)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(286, 286, 286))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(244, 244, 244)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void novoProcButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novoProcButtonActionPerformed
        display();
//        NovoProcessoForm form = new NovoProcessoForm();
//        JFrame f = new JFrame();
//        f.setSize(350, 210); 
//        f.setVisible(true);
//        
//        //Now add your JPanel (JPanel yourJPanelName = new JPanel();)
//        f.add(form);
    }//GEN-LAST:event_novoProcButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton novoProcButton;
    private javax.swing.JTable tableBloq;
    private javax.swing.JTable tableExe;
    private javax.swing.JTable tableFin;
    private javax.swing.JTable tableProntos;
    // End of variables declaration//GEN-END:variables
}
