package com.usermanager.dao;

import com.usermanager.model.UserHistoriqueModel;

import java.util.List;

public interface UserHistoriqueDAO {

    void saveHistorique(UserHistoriqueModel history);
    List<UserHistoriqueModel> findByAdmin(int adminId);
    List<UserHistoriqueModel> findAllHistorique();
}
