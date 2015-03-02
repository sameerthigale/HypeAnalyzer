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

import static beproject.Initializer.inConn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Thigale Sameer
 */
public class Regression {
    static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Statement stmt;
    Regression(){
        try{
            stmt=inConn.createStatement();
        }catch(SQLException e){
            ExceptionManager.handleException(e, "");
        }
    }
    
    List getRegressionValues() throws SQLException{
        List<Double> list=new ArrayList<>();
        ResultSet rs=stmt.executeQuery("select * from regression limit 1");
        rs.next();
        list.add(rs.getDouble(1));
        list.add(rs.getDouble(2));
        list.add(rs.getDouble(3));
        list.add(rs.getDouble(4));
        list.add(rs.getDouble(5));
        list.add(rs.getDouble(6));
        list.add(rs.getDouble(7));
        return list;
    }
    
    double getPrediction(String movieName){
        double gross=0, a, p, d, e, c, s;
        a=calcA(movieName);
        //p=calcP(movieName);
        d=calcD(movieName);
        //e=calcE(movieName);
        //c=calcC(movieName);
        //s=calcS(movieName);
        List<Double> reg=null;
        try{
            reg=getRegressionValues();
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        gross=
                reg.get(0)* (a+d) +
                //reg.get(1) * p +
                //reg.get(2) * d +
                reg.get(6);
        return gross;
    }
    
    
    
    /*ResultSet getOneHourTweets(int hrs, String movieName){
        ResultSet rs=null;
        Date i=new Date(), j=new Date();
        i.setHours(hrs);
        i.setSeconds(0);
        j.setHours(hrs+1);
        j.setSeconds(0);
        String tmp="select * from tweets where moviename='"+movieName+"' and ts between '"+sdf.format(i)+"' and '"+sdf.format(j)+"'";
        //System.out.print(tmp);
        try{
            rs=stmt.executeQuery(tmp);
        }catch(SQLException e){
            
        }
        return rs;
    }*/
    
    double calcA(String movieName){
        double a=0;
        ResultSet rs=null;
        java.sql.Timestamp min=null, max=null;
        try{
            rs=stmt.executeQuery("select count(*) from tweets where moviename='"+movieName+"'");
            rs.next();
            a=rs.getInt(1);
            rs=stmt.executeQuery("select min(ts) from tweets where movieName='"+movieName+"'");
            rs.next();
            min=rs.getTimestamp(1);
            rs=stmt.executeQuery("select max(ts) from tweets where movieName='"+movieName+"'");
            rs.next();
            max=rs.getTimestamp(1);
        }catch(SQLException e){
            e.printStackTrace();
        }
        long l=max.getTime()-min.getTime();
        l=l/1000/60/60;
        return a/l;
    }
    
    double calcP(String movieName){
        ResultSet rs=null;
        try{
            rs=stmt.executeQuery("select sum(polarity) from tweets where moviename='"+movieName+"'");
            rs.next();
            return rs.getInt(1);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
    
    double calcD(String movieName){
        ResultSet rs;
        try{
            rs=stmt.executeQuery("select distribution from movienames where moviename='"+movieName+"'");
            rs.next();
            return rs.getInt(1);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
    
    double calcE(String movieName){
        double e=0;
        return e;
    }
    
    double calcC(String movieName){
        double c=0;
        return c;
    }
    
    double calcS(String movieName){
        ResultSet rs;
        try{
            rs=stmt.executeQuery("select isSequel from movienames where moviename='"+movieName+"'");
            rs.next();
            return rs.getInt(1);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
    
    double calcA(int hrs, String movieName){
        ResultSet rs=null;
        Date i=new Date(), j=new Date();
        i.setHours(hrs);
        i.setSeconds(0);
        j.setHours(hrs+1);
        j.setSeconds(0);
        String tmp="select count(distinct userscreenname) from tweets where moviename='"+movieName+"' and ts between '"+sdf.format(i)+"' and '"+sdf.format(j)+"'";
        
        String tmp1="select count(*) from tweets where moviename='"+movieName+"' and ts between '"+sdf.format(i)+"' and '"+sdf.format(j)+"'";
        
        try{
            rs=stmt.executeQuery(tmp);
            rs.next();
            int a=rs.getInt(1);
            rs=stmt.executeQuery(tmp1);
            rs.next();
            int b=rs.getInt(1);
            
            return a/(float)b+b;
        }catch(SQLException e){
            ExceptionManager.handleException(e, tmp+"\n"+tmp1);
        }
        return 0.0f;
    }
    
    double calcP(int hrs, String movieName){
        ResultSet rs=null;
        Date i=new Date(), j=new Date();
        i.setHours(hrs);
        i.setSeconds(0);
        j.setHours(hrs+1);
        j.setSeconds(0);
        String pos="select count(*) from tweets where moviename='"+movieName+"' and ts between '"+sdf.format(i)+"' and '"+sdf.format(j)+"' and polarity>0";
        String neg="select count(*) from tweets where moviename='"+movieName+"' and ts between '"+sdf.format(i)+"' and '"+sdf.format(j)+"' and polarity<0";
        try{
            rs=stmt.executeQuery(pos);
            rs.next();
            int tmp=rs.getInt(1);
            System.out.print(tmp+"/");
            rs=stmt.executeQuery(neg);
            rs.next();
            int tmp2=rs.getInt(1);
            if(tmp2==0)return 0.0f;
            return tmp/(float)tmp2;
        }catch(SQLException e){
            ExceptionManager.handleException(e, "");
        }
        return 0.0f;
    }
    
    float calcD(int hrs, String movieName){
        ResultSet rs=null;
        Date i=new Date(), j=new Date();
        i.setHours(hrs);
        i.setMinutes(0);
        i.setSeconds(0);
        
        j.setHours(hrs+1);
        j.setMinutes(0);
        j.setSeconds(0);
        String tmp="select count(*), sum(u.followercount) from tweets t, userdb u where moviename='"+movieName+"' and ts between '"+sdf.format(i)+"' and '"+sdf.format(j)+"' and t.userscreenname=u.userscreenname";
        try{
            rs=stmt.executeQuery(tmp);
            rs.next();
            return (rs.getInt(2)-(rs.getInt(2)/rs.getInt(1))/rs.getInt(2));
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    
}
