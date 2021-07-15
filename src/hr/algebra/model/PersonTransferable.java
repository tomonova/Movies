/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author TomoNova
 */
public class PersonTransferable implements Transferable{
    public static final DataFlavor PERSON_FLAVOR = new DataFlavor(Person.class, "Person");
    private static final DataFlavor[] SUPPORTED_FLAVORS = {PERSON_FLAVOR};

    private final Person person;

    public PersonTransferable(Person person) {
        this.person = person;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return SUPPORTED_FLAVORS;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(PERSON_FLAVOR);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
       if (isDataFlavorSupported(flavor)) {
            return person;
        }
        throw new UnsupportedFlavorException(flavor);        
    }
}
