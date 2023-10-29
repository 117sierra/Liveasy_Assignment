package com.liveasy.Assignment.Db.Service;

import com.liveasy.Assignment.Db.LoadData;
import com.liveasy.Assignment.Db.Repository.Ilog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class service {
    @Autowired
    private Ilog ilog;
    public LoadData adddata(LoadData loadData){
        return ilog.adddata(loadData);
    }
    public List<LoadData> getPersonData(String shipperid) throws SQLException {
        return ilog.getdata(shipperid);
    }
    public String delete(String loadid){
        return ilog.delete(loadid);
    }
    public Integer update(String loadid, String shipperid){
        return  ilog.updatetable(loadid, shipperid);
    }

}
