package library;

import library.entity.Lending;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class LendingService {

    public Lending addLending(Lending lending) throws Exception {
        EntityServerDao entityServerDao = new EntityServerDao();
        return entityServerDao.addEntity(lending);
    }

    public Lending getLending(int id) throws Exception {

        List<Lending> lend = null;
        String query = "from Lending where bookID = ?1 and status = 0";
        EntityServerDao entityServerDao = new EntityServerDao();
        lend = entityServerDao.getEntity(query, id);
        return lend.get(0);
    }

    public Lending getLending(String firstname, String lastname, String bookID) throws Exception {

        List<Lending> lend = null;
        String query = "from Lending where bookID=?1 and firstname=?2 and lastname=?3";
        List list= new ArrayList();
        list.add(bookID);
        list.add(firstname);
        list.add(lastname);
        EntityServerDao entityServerDao = new EntityServerDao();
        lend = entityServerDao.getEntity(query, Integer.parseInt(bookID), firstname, lastname);
        if(lend.size() > 0){
            return lend.get(0);
        }
        return null;
    }

    public Lending deleteLending(Lending lending) throws Exception{

        EntityServerDao entityServerDao = new EntityServerDao();
        return entityServerDao.deleteEntity(lending);
    }
}