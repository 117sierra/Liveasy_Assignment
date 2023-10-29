package com.liveasy.Assignment.Db.Controller;
import com.liveasy.Assignment.Db.LoadData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.liveasy.Assignment.Db.Service.service;

import java.sql.SQLException;
import java.util.List;

@RestController                             // instead of loadid in the Path variable I used shipperid because in the payload there was no mention of loadid
public class LiveasyController {
    @Autowired
    private service serv;

    @PostMapping("/load")
    public ResponseEntity<LoadData> addperson(@RequestBody LoadData loadData) throws SQLException {
//        if(loadData.getShipperid()==null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        LoadData p = serv.adddata(loadData);
        LoadData p = serv.adddata(loadData);

        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @GetMapping("load")
    public ResponseEntity<List<LoadData>> getPersonData(@RequestBody LoadData loadData) throws SQLException {
        List<LoadData> list = serv.getPersonData(loadData.getShipperid());
        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @GetMapping("load/{loadid}")
    public ResponseEntity<List<LoadData>> getPersondata(@PathVariable String loadid) throws SQLException {
        List<LoadData> list = serv.getPersonData(loadid);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @DeleteMapping("load/{loadid}")
    public ResponseEntity<String> del(@PathVariable String loadid){
        String p = serv.delete(loadid);
        return new ResponseEntity<>(p,HttpStatus.OK);
    }
    @PutMapping("load/{loadid}")
    public ResponseEntity<Integer>update(@PathVariable String loadid, @RequestBody LoadData loadData){
        Integer x= serv.update(loadid,loadData.getLoadingpoint());
        return  new ResponseEntity<>(x,HttpStatus.OK);
    }

}

