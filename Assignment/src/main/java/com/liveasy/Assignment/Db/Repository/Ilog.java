package com.liveasy.Assignment.Db.Repository;

import com.liveasy.Assignment.Db.LoadData;

import java.sql.SQLException;
import java.util.List;

public interface Ilog {

    LoadData adddata(LoadData loadData);


    List<LoadData> getdata(String shipperid) throws SQLException;
    String delete(String loadid);

    int updatetable(String loadingpoint, String shipperid);
}
