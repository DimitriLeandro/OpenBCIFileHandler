package frames;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author dimitri
 */
public class mainFrame extends javax.swing.JFrame {

    Scanner objSc;
    String textoFinal;
    JFileChooser objFC;

    public mainFrame() {
        initComponents();
        this.setLocationRelativeTo(null); //colocando a jframe no centro da tela
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Open BCI File Handler");
        setResizable(false);

        btnOk.setText("Selecionar Arquivos");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnOk, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        objFC = new JFileChooser();
        objFC.setMultiSelectionEnabled(true);
        //objFC.showOpenDialog(this); 

        int returnVal = objFC.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File objFile[] = objFC.getSelectedFiles();
                for (int i = 0; i < objFile.length; i++) {
                    System.out.println("EXECUTANDO CÓDIGO PARA O ARQUIVO " + objFile[i].getName());
                    textoFinal = "";
                    lerTxt(objFile[i]);
                    criarNovoTxt(objFile[i]);
                }
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Algo deu errado ao abrir os txts");
            }
        }
    }//GEN-LAST:event_btnOkActionPerformed

    public void criarNovoTxt(File objFile) {
        //o .getParent pega a pasta em que o arquivo está e o .getName pega o nome do arquivo. Então a variável novoArquivo vai ficar tipo "/pasta/para/o/arquivo/novo_nomedoarquivo.txt"
        String novoArquivo = objFile.getParent() + "/novo_" + objFile.getName();

        try {
            PrintWriter escritorDeTxt = new PrintWriter(novoArquivo, "UTF-8"); //criando um objeto da classe de escrever txt's
            escritorDeTxt.print(textoFinal); //adicionando linhas no txt
            escritorDeTxt.close(); //destruindo o objeto
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.out.println(e);
        }
    }

    public void lerTxt(File objFile) {
        try {
            objSc = new Scanner(objFile);
            String linha;
            boolean chegouL1 = false;

            while (objSc.hasNextLine()) {
                linha = objSc.nextLine();

                if (chegouL1 == false) {
                    String primeiroCaracter = String.valueOf(linha.charAt(0));

                    if (primeiroCaracter.equals("1")) {
                        chegouL1 = true; //chegamos na primira linha!
                        //System.out.println(linha);
                        textoFinal = textoFinal + linha;
                    }
                } else {
                    //antes de exibir a linha, eu tenho que verificar se ela não é a última. Se for, eu não devo exibi-la
                    if (objSc.hasNextLine()) { //se tiver mais uma linha, significa que a linha atual não é a ultima, portanto, exiba-a
                        //System.out.println(linha);
                        textoFinal = textoFinal + "\n" + linha;
                    }
                }
            }
            
            objSc = null; //limpando a memória do objeto scanner
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOk;
    // End of variables declaration//GEN-END:variables
}
