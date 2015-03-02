/*
 * Copyright (C) 2015 Thigale Sameer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package beproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Thigale Sameer
 */
@SuppressWarnings("CallToPrintStackTrace")
public class Initializer {
    public static String DB_URL ;//= "jdbc:mysql://localhost/HypeAnalyzer";
    static String USER ;//= "root";
    static String PASS ;//= "";
    public static final String createMovieNamesString = "create table movieNames(movieName varchar(30) not null primary key, releaseDate Date not null, isSequel boolean not null, distribution integer not null)ENGINE = MyISAM";
    public static final String createTweetsString = "create table tweets(ts timestamp not null, tweet varchar(200) not null, userScreenName varchar(20) not null, polarity integer not null, moviename varchar(30) not null, country varchar(30), foreign key(moviename) references movienames(moviename))ENGINE = MyISAM";
    public static final String createActorsListString="create table actorslist(name varchar(30) primary key, twitterhandle varchar(20), followercount integer(10))ENGINE=MyISAM";
    public static final String createCategoryString="create table category(moviename varchar(30) not null, action boolean not null, animation boolean not null, thriller boolean not null, romance boolean not null, comedy boolean not null, constraint foreign key(moviename) references movienames(moviename))ENGINE=MyISAM";
    public static final String createActorMovieAssocString="create table movieactorassoc(moviename varchar(30), actorname varchar(30), constraint foreign key(actorname) references actorslist(name), constraint foreign key(moviename) references movienames(moviename))ENGINE = MyISAM";
    public static final String createUserDBString="create table userDB(userScreenName varchar(20) primary key, followercount integer(10) not null)ENGINE=MyISAM";
    public static final String createRegressionString="create table regression(a double, p double, d double, c double, e double, s double, u double)ENGINE=MyISAM";
    public static Connection inConn;
    public static Connection inConn2;
    public static Connection outConn;
    public static Connection outConn2;
    public static final int commitOn=30;
    
    @SuppressWarnings("null")
    /**
     * Public function to Initialize and check status of MySql database. Detects if database and the required tables exists.
     */
    public static void checkStatus(){    
        Statement stmt=null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            BufferedReader br=new BufferedReader(new FileReader("D:\\hypeanalyzer.txt"));
            DB_URL=br.readLine();
            USER=br.readLine();
            PASS=br.readLine();
            inConn=DriverManager.getConnection(DB_URL,USER,PASS);
            inConn.setReadOnly(true);
            inConn2=DriverManager.getConnection(DB_URL,USER,PASS);
            inConn2.setReadOnly(true);
            outConn=DriverManager.getConnection(DB_URL,USER,PASS);
            outConn2=DriverManager.getConnection(DB_URL,USER,PASS);
            outConn.setAutoCommit(true);
            stmt = inConn.createStatement();
        }catch(Exception e){
            ExceptionManager.handleException(e, "Problem with either connection or username, password");
        }
        try{
            stmt.executeQuery("select * from movienames limit 1");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Detected first time installation! "
                    + "Please be patient while we create required databases", "INITIALIZING", JOptionPane.INFORMATION_MESSAGE);
            try {
                stmt.execute(createMovieNamesString);
                stmt.execute(createTweetsString);
                stmt.execute(createActorsListString);
                stmt.execute(createCategoryString);
                stmt.execute(createActorMovieAssocString);
                stmt.execute(createUserDBString);
                stmt.execute(createRegressionString);
            } catch (SQLException ex) {
                ExceptionManager.handleException(ex, "Problem with creating tables in MySql");
            }
        }
    }
}
