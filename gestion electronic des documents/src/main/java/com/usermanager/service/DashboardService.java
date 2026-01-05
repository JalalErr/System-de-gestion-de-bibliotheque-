package com.usermanager.service;

import com.usermanager.dao.impl.DashboardDAOImpl;
import com.usermanager.model.DashboardModel;

import java.util.List;

public class DashboardService {

    private final DashboardDAOImpl dao = new DashboardDAOImpl();

    public List<DashboardModel> getHistorique(String filtre) throws Exception {
        return dao.getHistorique(filtre);
    }

    public int getTotalLivres() throws Exception {
        return dao.count("livre");
    }

    public int getLivresEmpruntes() throws Exception {
        return dao.countEmpruntes("LIVRE");
    }

    public int getTotalRapports() throws Exception {
        return dao.count("rapport");
    }

    public int getRapportsEmpruntes() throws Exception {
        return dao.countEmpruntes("RAPPORT");
    }
}
