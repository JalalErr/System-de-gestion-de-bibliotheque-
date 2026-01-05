package com.usermanager.dao;

import com.usermanager.model.SanctionModel;
import java.util.List;

public interface SanctionDAO {
    void save(SanctionModel sanction);
    boolean existsByEmpruntId(int empruntId);
    List<SanctionModel> findAll();
    public void addSanction(SanctionModel sanction);
}
