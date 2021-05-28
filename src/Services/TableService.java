/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.TableDaoo;
import Models.Table;
import java.util.List;

/**
 *
 * @author vando
 */
public class TableService {
    private TableDaoo tableDao;
    public TableService()
    {
        tableDao=new TableDaoo();
    }
    public List<Table> getAllTable()
    {
        return tableDao.getAllTable();
    }
    public void addTable(Table table)
    {
        tableDao.addTable(table);        
    }
    public void deleteTable(String id)
    {
        tableDao.deleteTable(id);
    }
    public Table getTableById(String id)
    {
        return tableDao.getTableById(id);
    }
}
