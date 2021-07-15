/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.Enums.Occupation;
import hr.algebra.model.Movie;
import hr.algebra.model.Person;
import hr.algebra.model.PersonAddable;
import hr.algebra.model.PersonTransferable;
import hr.algebra.utils.MessageUtils;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import static javax.swing.TransferHandler.COPY;

/**
 *
 * @author TomoNova
 */
public class PersonManagementDialog extends javax.swing.JDialog implements PersonAddable{

    private Movie movie;
    private Repository repository;
    private Set<Person> allPersons;
    private Set<Person> selectedPersons;
    private final DefaultListModel<Person> allPersonsModel = new DefaultListModel<>();
    private final DefaultListModel<Person> selectedPersonsModel = new DefaultListModel<>();
    private final Occupation occupation;
    
    public PersonManagementDialog(java.awt.Frame parent, boolean modal,Movie movie,Occupation occupation) {
        super(parent,modal);
        this.movie = movie;
        this.occupation=occupation;
        setLocationRelativeTo(null);
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

        btnOK = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lbAllPersons = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lbSelectedPersons = new javax.swing.JList<>();
        lblSelectedPersons = new javax.swing.JLabel();
        lblAllPersons1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnOK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancel.setText("CANCEL");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 51, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbAllPersons.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(lbAllPersons);

        lbSelectedPersons.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(lbSelectedPersons);

        lblSelectedPersons.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSelectedPersons.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblAllPersons1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAllPersons1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(lblSelectedPersons, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblAllPersons1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSelectedPersons, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAllPersons1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        List<Person> tempList = new ArrayList<>(selectedPersons);
        switch(occupation){
            case GLUMAC:               
                movie.setGlumci(tempList);
                break;
            case REDATELJ:
                movie.setRedatelj(tempList);
                break;
        }
        dispose();
    }//GEN-LAST:event_btnOKActionPerformed


    private void init() {
        initRepository();
        initLabels();
        initDragNDrop();
        loadAllPersons();
        loadSelectedPersons();
        loadAllPersonsModel();
        loadSelectedPersonsModel();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<Person> lbAllPersons;
    private javax.swing.JList<Person> lbSelectedPersons;
    private javax.swing.JLabel lblAllPersons1;
    private javax.swing.JLabel lblSelectedPersons;
    private javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables

    private void initRepository() {
        try {
            repository = RepositoryFactory.getRepository();
        } catch (Exception ex) {
            MessageUtils.showErrorMessage("ERROR", "Unable to initialize repository " + ex.getMessage());
            Logger.getLogger(PersonManagementDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadAllPersons() {
        try {
            switch(occupation){
            case GLUMAC:
                allPersons= repository.GetAllPersons(Occupation.GLUMAC);
                break;
            case REDATELJ:
                allPersons= repository.GetAllPersons(Occupation.REDATELJ);
                break;
        }
            allPersonsModel.clear();
            lbAllPersons.setModel(allPersonsModel);
        } catch (Exception ex) {
            MessageUtils.showErrorMessage("ERROR", "Couldn't load movies");
            Logger.getLogger(PersonManagementDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadSelectedPersons() {
        Set<Person> persons = new TreeSet<>(); 
        switch(occupation){
            case GLUMAC:
                persons= new TreeSet<>(movie.getGlumci());
                break;
            case REDATELJ:
                persons= new TreeSet<>(movie.getRedatelj());
                break;
        }

        selectedPersons = persons;
        selectedPersonsModel.clear();
        lbSelectedPersons.setModel(selectedPersonsModel);
    }

    private void loadAllPersonsModel() {
        allPersonsModel.clear();
        allPersons.forEach(allPersonsModel::addElement);
        lbAllPersons.setModel(allPersonsModel);
    }

    private void loadSelectedPersonsModel() {
        selectedPersonsModel.clear();
        selectedPersons.forEach(selectedPersonsModel::addElement);
        lbSelectedPersons.setModel(selectedPersonsModel);
    }

    private void initLabels() {
        switch(occupation){
            case GLUMAC:
                lblTitle.setText("Choose actors for a movie");
                lblAllPersons1.setText("All actors");
                lblSelectedPersons.setText("Selected actors");
                break;
            case REDATELJ:
                lblTitle.setText("Choose directors for a movie");
                lblAllPersons1.setText("All directors");
                lblSelectedPersons.setText("Selected directors");
                break;
        }
    }
    private void initDragNDrop() {
        lbAllPersons.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lbAllPersons.setDragEnabled(true);
        lbAllPersons.setTransferHandler(new PersonManagementDialog.ExportTransferHandler());

        lbSelectedPersons.setDropMode(DropMode.ON);
        lbSelectedPersons.setTransferHandler(new PersonManagementDialog.ImportTransferHandler());
    }

    @Override
    public boolean addPerson(Person person) {
        if (allPersons.add(person)) {
            loadAllPersonsModel();
            return true;
        }
        return false;
    }
    
    private class ExportTransferHandler extends TransferHandler {

        @Override
        public int getSourceActions(JComponent c) {
            return COPY;
        }

        @Override
        public Transferable createTransferable(JComponent c) {
            return new PersonTransferable(lbAllPersons.getSelectedValue());
        }
    }

    private class ImportTransferHandler extends TransferHandler {

        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            return support.isDataFlavorSupported(PersonTransferable.PERSON_FLAVOR);
        }

        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            Transferable transferable = support.getTransferable();
            try {
                Person add = (Person) transferable.getTransferData(PersonTransferable.PERSON_FLAVOR);
                if (selectedPersons.add(add)) {
                    loadSelectedPersonsModel();
                    return true;
                }
            } catch (UnsupportedFlavorException | IOException ex) {
                MessageUtils.showErrorMessage("ERROR", ex.getMessage());
                Logger.getLogger(PersonManagementDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }
}
