package com.liveasy.Assignment.Db;

import lombok.*;

import java.sql.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoadData {
    private String loadingpoint;
    private String unloadingpoint;
    private String producttype;
    private String trucktype;
    private int    nooftrucks;
    private int weight;
    private String comment;
    private String shipperid;
    private String  date;
}
