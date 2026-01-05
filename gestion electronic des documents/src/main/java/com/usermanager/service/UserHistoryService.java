package com.usermanager.service;

import com.usermanager.dao.UserHistoriqueDAO;
import com.usermanager.dao.impl.UserHistoriqueDAOImpl;
import com.usermanager.model.UserHistoriqueModel;
import com.usermanager.model.UserModel;
import com.usermanager.model.UserRole;

import java.util.List;

public class UserHistoryService {

        private final UserHistoriqueDAO historyDAO = new UserHistoriqueDAOImpl() {};

    public List<UserHistoriqueModel> getHistoriqueByAdmin(int adminId) {

        return historyDAO.findByAdmin(adminId);
    }

        public void log(String action, String description) {

            UserModel admin = UserSession.getUser();

            if (admin == null || admin.getRole() != UserRole.ADMIN) {
                return; // sécurité
            }

            UserHistoriqueModel history = new UserHistoriqueModel();
            history.setUserId(admin.getId());
            history.setAction(action);
            history.setDescription(description);

            historyDAO.saveHistorique(history);
        }

}
