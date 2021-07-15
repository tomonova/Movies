/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.Account;
import hr.algebra.model.MovieArchive;
import hr.algebra.model.User;
import hr.algebra.utils.JAXBUtils;
import hr.algebra.utils.MessageUtils;
import java.awt.HeadlessException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TomoNova
 */
public class MainFrame extends javax.swing.JFrame {

    private User user;
    private Account account;
    private Repository repository;
//    Tab names
    private static final String UPLOAD_MOVIES = "Upload movies";
    private static final String EDIT_MOVIES = "Edit movies";
    private static final String FAVOURITE_MOVIES = "Favourites";
    private static final String FILENAME = "moviearchive.xml";

    public MainFrame() throws HeadlessException {
    }
    
    
    
    public MainFrame(User user) {
        this.user=user;
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpMain = new javax.swing.JTabbedPane();
        lbAccount = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnArchive = new javax.swing.JMenu();
        mbExit = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbAccount.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        lbAccount.setForeground(new java.awt.Color(102, 102, 255));

        jMenu1.setText("About");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        btnArchive.setText("Archive");
        btnArchive.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnArchiveMouseClicked(evt);
            }
        });
        jMenuBar1.add(btnArchive);

        mbExit.setText("Exit");
        mbExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mbExitMouseClicked(evt);
            }
        });
        jMenuBar1.add(mbExit);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(tpMain, javax.swing.GroupLayout.PREFERRED_SIZE, 1210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbAccount, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tpMain, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mbExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mbExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_mbExitMouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        MessageUtils.showInformationMessage("ABOUT", "Java 1 projekt demo");
    }//GEN-LAST:event_jMenu1MouseClicked

    private void btnArchiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnArchiveMouseClicked
        try {
            JAXBUtils.save(new MovieArchive(repository.selectMovies()), FILENAME);
            MessageUtils.showInformationMessage("Info", "Movies archived");
        } catch (Exception ex) {
            MessageUtils.showErrorMessage("ERROR", "Unable to archive movies");
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnArchiveMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu btnArchive;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lbAccount;
    private javax.swing.JMenu mbExit;
    private javax.swing.JTabbedPane tpMain;
    // End of variables declaration//GEN-END:variables

    private void init() {
        initRepository();
        checkDBStatus();
        setHelloMessage();
        configurePanels();
    }

    private void setHelloMessage() {
        try {
            Optional<Account> optAccount =repository.getAccount(user.getUsername());
            if (optAccount.isPresent()) {
                account =optAccount.get();
                lbAccount.setText("Bok "+account.getFirstName());
            }
            
        } catch (Exception e) {
            MessageUtils.showErrorMessage("ERROR", e.getMessage());
        }
    }

    private void configurePanels() {
        switch(account.getAccountType()){
            case ADMIN:
                tpMain.add(UPLOAD_MOVIES,new UploadMoviesPanel());
                break;
            case REGULAR:
                tpMain.add(EDIT_MOVIES,new ManageMoviesPanel());  
                tpMain.add(FAVOURITE_MOVIES,new FavouritesPanel(user));
                break;
        }
    }
        private void initRepository() {
        try {
            repository = RepositoryFactory.getRepository();
        } catch (Exception ex) {
            Logger.getLogger(LoginDialog.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("ERROR", "Unable to initialize repository!");
        }
    }

    private void checkDBStatus() {
        try {
            if (!repository.checkDBStatus()) {
                repository.deleteData();
            }
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("ERROR", ex.getMessage());
        }
    }
}
