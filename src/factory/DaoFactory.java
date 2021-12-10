package factory;

import dao.Dao;
import dao.VueloDao;

/**
 *
 * @author federico cotrena
 */
public class DaoFactory {


    public enum DaoType{
        VUELO
    }
    /**
     * @param daoType recibe el tipo de dao que quieres crear. VUELO |
     * @return una instancia del dao que indicamos
     */
    public static Dao getDao (DaoType daoType){
        Dao dao = null;
        switch (daoType){
            case VUELO:
                dao = new VueloDao();
                break;
        }
        return dao;
    }

}