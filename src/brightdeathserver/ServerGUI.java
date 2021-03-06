/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package brightdeathserver;

import javax.swing.JTextArea;

/**
 *
 * @author nicno90
 */
public class ServerGUI extends javax.swing.JFrame
{

    /**
     * Creates new form ServerGUI
     */
    public ServerGUI()
    {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        cmdPrompt = new javax.swing.JTextArea();
        inptField = new javax.swing.JTextField();
        enter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");
        setIconImages(getIconImages());
        setName("Server"); // NOI18N

        cmdPrompt.setEditable(false);
        cmdPrompt.setBackground(new java.awt.Color(231, 231, 231));
        cmdPrompt.setColumns(20);
        cmdPrompt.setRows(5);
        jScrollPane1.setViewportView(cmdPrompt);

        inptField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                inptFieldActionPerformed(evt);
            }
        });

        enter.setText("Enter");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inptField)
                        .addGap(18, 18, 18)
                        .addComponent(enter)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inptField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enter))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inptFieldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_inptFieldActionPerformed
    {//GEN-HEADEREND:event_inptFieldActionPerformed
        // TODO add your handling code here:
        String command = inptField.getText();
        
        if (command.charAt(0) == '/')
        {
            command = command.substring(1);
            if (command.equals("stop") || command.equals("quit"))
            {
                System.exit(0);
            }
            else if (command.equals("help") || command.equals("?"))
            {
                this.addCmdTxt(   "/help /? ......................commands"
                              + "\n/stop /quit ...................stop server");
            }
            else
            {
               this.addCmdTxt("Command Unknown : /" + command); 
            }
        }
        else
        {
            this.addCmdTxt("server: " + command);
            
        }
        inptField.setText("");
    }//GEN-LAST:event_inptFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new ServerGUI().setVisible(true);
            }
        });
    }

    public String getCmdPrompt()
    {
        return cmdPrompt.getText();
    }
    public void setCmdPrompt(String s)
    {
         cmdPrompt.setText(s);
    }
    public void addCmdTxt(String s)
    {
        cmdPrompt.setText(cmdPrompt.getText() + "\n" + s);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea cmdPrompt;
    private javax.swing.JButton enter;
    private javax.swing.JTextField inptField;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
