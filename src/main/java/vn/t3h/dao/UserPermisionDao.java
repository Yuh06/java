
package vn.t3h.dao;

import vn.t3h.model.UserPermision;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userPermision")
@Transactional
public class UserPermisionDao {
    
    Logger logger = LoggerFactory.getLogger(UserPermisionDao.class);
    
    public List<UserPermision> getAllPermision() {
        
		return EntityQuery.create(entityManager, UserPermission.class).list();
    }
    
    public void save(UserPermision userPermision) {
        
    }
    
    public void deleteById(int id) {
        
    }
}
