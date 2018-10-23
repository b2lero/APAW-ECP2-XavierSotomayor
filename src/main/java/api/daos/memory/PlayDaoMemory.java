package api.daos.memory;

import api.daos.PlayDao;
import api.entities.Play;

import java.util.HashMap;

public class PlayDaoMemory extends GenericDaoMemory<Play> implements PlayDao {

    public PlayDaoMemory(){
        super(new HashMap<>());
    }

    @Override
    public String getId(Play play) {
        return play.getId();
    }

    @Override
    public void setId(Play play, String id) {
        play.setId(id);
    }

}
