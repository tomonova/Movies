/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.Movie;
import hr.algebra.model.MovieAddable;
import hr.algebra.model.MovieTransferable;
import hr.algebra.model.User;
import hr.algebra.utils.MessageUtils;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
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
public class FavouritesPanel extends javax.swing.JPanel implements MovieAddable {

    private final User user;
    private Set<Movie> allMovies;
    private Set<Movie> favouriteMovies;
    private final DefaultListModel<Movie> allMoviesModel = new DefaultListModel<>();
    private final DefaultListModel<Movie> favouriteMoviesModel = new DefaultListModel<>();
    private Repository repository;

    FavouritesPanel(User user) {
        this.user = user;
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

        popRemoveFavorites = new javax.swing.JPopupMenu();
        popItemSaveFavorite = new javax.swing.JMenuItem();
        popItemRemove = new javax.swing.JMenuItem();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lbFavouriteMovies = new javax.swing.JList<>();
        lblFavouriteImg = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lbAllMovies = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();

        popItemSaveFavorite.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        popItemSaveFavorite.setForeground(new java.awt.Color(0, 51, 255));
        popItemSaveFavorite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hr/algebra/resources/icons8-save-48.png"))); // NOI18N
        popItemSaveFavorite.setText("Save favorites");
        popItemSaveFavorite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popItemSaveFavoriteActionPerformed(evt);
            }
        });
        popRemoveFavorites.add(popItemSaveFavorite);

        popItemRemove.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        popItemRemove.setForeground(new java.awt.Color(0, 51, 255));
        popItemRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hr/algebra/resources/icons8-delete-trash-48.png"))); // NOI18N
        popItemRemove.setText("Remove selected from favorites");
        popItemRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popItemRemoveActionPerformed(evt);
            }
        });
        popRemoveFavorites.add(popItemRemove);

        setMaximumSize(new java.awt.Dimension(1210, 853));
        setMinimumSize(new java.awt.Dimension(1210, 853));
        setPreferredSize(new java.awt.Dimension(1210, 853));

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Choose your favourite movies");

        jScrollPane1.setMaximumSize(new java.awt.Dimension(400, 500));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(400, 500));

        lbFavouriteMovies.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbFavouriteMovies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lbFavouriteMoviesMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(lbFavouriteMovies);

        lblFavouriteImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hr/algebra/resources/favourite_movie.png"))); // NOI18N

        jScrollPane2.setMaximumSize(new java.awt.Dimension(400, 500));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(400, 500));

        jScrollPane2.setViewportView(lbAllMovies);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Favourite movies");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("All movies");

        lblStatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(0, 51, 255));
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(337, 337, 337)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(385, 385, 385)
                .addComponent(lblFavouriteImg, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addComponent(lblFavouriteImg, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lbFavouriteMoviesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbFavouriteMoviesMouseReleased
        if (evt.isPopupTrigger()) {
            popRemoveFavorites.show(this, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_lbFavouriteMoviesMouseReleased

    private void popItemRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popItemRemoveActionPerformed
        try {
            int[] selectedMoviesIndex = lbFavouriteMovies.getSelectedIndices();
            Set<Movie> selectedMovies = extractSelectedMovies(selectedMoviesIndex);
            repository.DeleteFavouriteMovies(user,selectedMovies);
            loadFavourites(user);
            loadFavouritesModel();
            lblStatus.setText("<html><p>Favorites</p><br><p>removed</p></html>");
        } catch (Exception ex) {
            MessageUtils.showErrorMessage("ERROR", "Favorites not removed" + ex.getMessage());
            Logger.getLogger(FavouritesPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_popItemRemoveActionPerformed

    private void popItemSaveFavoriteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popItemSaveFavoriteActionPerformed
        try {
            repository.SaveFavorites(favouriteMovies, user);
            lblStatus.setText("<html><p>Favorites</p><br><p>&nbsp&nbsp Saved</p></html>");
        } catch (Exception ex) {
            lblStatus.setText("<html><p>Favorites</p><br><p>not Saved</p></html>");
            MessageUtils.showErrorMessage("ERROR", "Favorites not saved");
            Logger.getLogger(FavouritesPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_popItemSaveFavoriteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<Movie> lbAllMovies;
    private javax.swing.JList<Movie> lbFavouriteMovies;
    private javax.swing.JLabel lblFavouriteImg;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JMenuItem popItemRemove;
    private javax.swing.JMenuItem popItemSaveFavorite;
    private javax.swing.JPopupMenu popRemoveFavorites;
    // End of variables declaration//GEN-END:variables

    private void init() {
        initRepository();
        initDragNDrop();
        loadMovies();
        loadFavourites(user);
        loadAllMoviesModel();
        loadFavouritesModel();
    }

    private void loadAllMoviesModel() {
        allMoviesModel.clear();
        allMovies.forEach(allMoviesModel::addElement);
        lbAllMovies.setModel(allMoviesModel);
    }

    private void loadFavouritesModel() {
        favouriteMoviesModel.clear();
        favouriteMovies.forEach(favouriteMoviesModel::addElement);
        lbFavouriteMovies.setModel(favouriteMoviesModel);
    }

    private void initDragNDrop() {
        lbAllMovies.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lbAllMovies.setDragEnabled(true);
        lbAllMovies.setTransferHandler(new ExportTransferHandler());

        lbFavouriteMovies.setDropMode(DropMode.ON);
        lbFavouriteMovies.setTransferHandler(new ImportTransferHandler());
    }

    private void loadMovies() {
        try {
            allMovies = repository.GetMovies();
            allMoviesModel.clear();
            lbAllMovies.setModel(allMoviesModel);
        } catch (Exception ex) {
            MessageUtils.showErrorMessage("ERROR", "Couldn't load movies");
            Logger.getLogger(UploadMoviesPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadFavourites(User user) {
        try {
            favouriteMovies = repository.GetMovies(user);
            favouriteMoviesModel.clear();
            lbFavouriteMovies.setModel(favouriteMoviesModel);
        } catch (Exception ex) {
            MessageUtils.showErrorMessage("ERROR", "Couldn't load favourite movies");
            Logger.getLogger(UploadMoviesPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initRepository() {
        try {
            repository = RepositoryFactory.getRepository();
        } catch (Exception ex) {
            MessageUtils.showErrorMessage("ERROR", "Unable to initialize repository \n" + ex.getMessage());
            Logger.getLogger(FavouritesPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private class ExportTransferHandler extends TransferHandler {

        @Override
        public int getSourceActions(JComponent c) {
            return COPY;
        }

        @Override
        public Transferable createTransferable(JComponent c) {
            return new MovieTransferable(lbAllMovies.getSelectedValue());
        }
    }

    private class ImportTransferHandler extends TransferHandler {

        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            return support.isDataFlavorSupported(MovieTransferable.MOVIE_FLAVOR);
        }

        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            Transferable transferable = support.getTransferable();
            try {
                Movie add = (Movie) transferable.getTransferData(MovieTransferable.MOVIE_FLAVOR);
                if (favouriteMovies.add(add)) {
                    loadFavouritesModel();
                    return true;
                }
            } catch (UnsupportedFlavorException | IOException ex) {
                MessageUtils.showErrorMessage("ERROR", ex.getMessage());
                Logger.getLogger(FavouritesPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }

    private Set<Movie> extractSelectedMovies(int[] selectedMoviesIndex) {
        Set<Movie> selectedMovies = new TreeSet<>();
        for (Integer index : selectedMoviesIndex) {
            Movie item = lbFavouriteMovies.getModel().getElementAt(index);
            selectedMovies.add(item);
        }
        return selectedMovies;
    }

    @Override
    public boolean addMovie(Movie movie) {
        if (allMovies.add(movie)) {
            loadAllMoviesModel();
            return true;
        }
        return false;
    }
}
