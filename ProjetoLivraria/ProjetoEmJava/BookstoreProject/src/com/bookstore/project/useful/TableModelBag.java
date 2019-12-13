/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookstore.project.useful;

import com.bookstore.project.model.Book;
import com.bookstore.project.model.PurchaseItem;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author pablo
 */

//Classe que trabalha com AbstractTableModel para instanciar metodos de uma JTable com regras de negocio.
public class TableModelBag extends AbstractTableModel{
    private List<Book> rowsB = new ArrayList<>();
    private List<PurchaseItem> rowsI = new ArrayList<>();
    private String[] columns = {"ID", "Nome Livro", "Valor", "Quantidade", "Total"};
    
    
    @Override
    public int getRowCount() {
        return rowsB.size() ;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }
    
    @Override
    public String getColumnName(int numColumn){
        return columns[numColumn];
    }

    @Override
    public Object getValueAt(int numRow, int numColumn) {
        
        switch(numColumn){
            case 0:
                return rowsB.get(numRow).getIdLivro();
            case 1:
                return rowsB.get(numRow).getNmLivro();
            case 2:
                return rowsB.get(numRow).getValorLivro();
            case 3:
                return rowsI.get(numRow).getQtdCompra();
            case 4:
                return rowsI.get(numRow).getValorCompra();
        }
        
        return null;
    }
    
    public void addRowBok(Book b){
        this.rowsB.add(b);
        this.fireTableDataChanged();
    }
    
    public void addRowItem(PurchaseItem i){
        this.rowsI.add(i);
        this.fireTableDataChanged();
    }
    
    public void removeRowBok(int row){
        this.rowsB.remove(row);
        this.fireTableRowsDeleted(row, row);
    }
    
    public void limpar() {  
        rowsB.clear();  
        rowsI.clear();
  
        fireTableDataChanged();  
    }
    
}
