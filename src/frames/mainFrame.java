package frames;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dimitri
 */
public class mainFrame extends javax.swing.JFrame {

    Scanner objSc;
    String textoFinal = "";

    public mainFrame() {
        initComponents();
        this.setLocationRelativeTo(null); //colocando a jframe no centro da tela
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnOk = new javax.swing.JButton();
        txtArquivo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnOk.setText("Abrir");
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
                .addComponent(txtArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOk)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        String texto = txtArquivo.getText();

        File objFile = new File(texto);

        //quando o scanner for abrir o arquivo, ele pode n ão existir, portanto, tenta ai, se não rolar, me mostra ai no system.out qual foi o erro.
        try {
            objSc = new Scanner(objFile);
            lerTxt();
            criarNovoTxt();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Algo deu errado ao abrir o txt");
        }
    }//GEN-LAST:event_btnOkActionPerformed

    public void criarNovoTxt() {
        String nomeDoArquivo = "novo_" + txtArquivo.getText();
        
        try {
            PrintWriter escritorDeTxt = new PrintWriter(nomeDoArquivo, "UTF-8"); //criando um objeto da classe de escrever txt's
            escritorDeTxt.print(textoFinal); //adicionando linhas no txt
            escritorDeTxt.close(); //destruindo o objeto
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void lerTxt() {
        String linha;
        boolean chegouL1 = false;

        while (objSc.hasNextLine()) {
            linha = objSc.nextLine();

            if (chegouL1 == false) {
                String primeiroCaracter = String.valueOf(linha.charAt(0));

                if (primeiroCaracter.equals("1")) {
                    chegouL1 = true; //chegamos na primira linha!
                    System.out.println(linha);
                    textoFinal = textoFinal + linha;
                }
            } else {
                //antes de exibir a linha, eu tenho que verificar se ela não é a última. Se for, eu não devo exibi-la
                if (objSc.hasNextLine()) { //se tiver mais uma linha, significa que a linha atual não é a ultima, portanto, exiba-a
                    System.out.println(linha);
                    textoFinal = textoFinal + "\n" + linha;
                }
            }
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
    private javax.swing.JTextField txtArquivo;
    // End of variables declaration//GEN-END:variables
}
