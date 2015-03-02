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

import static beproject.Initializer.commitOn;
import static beproject.Initializer.inConn;
import static beproject.Initializer.outConn;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.JLabel;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
/**
 *
 * @author Thigale Sameer
 */
public class myTwitter implements Runnable {
    PolarityAnalyzer polarityAnalyzer;
    private final TwitterStream ts;
    private FilterQuery fq;
    Statement stmt;
    List<String> f;
    JLabel ret;
    myStatusListener sl;
    static Configuration conf;
    public static int totalProcessedTweets;
    //UserCountry userCountry;
    
    static{
        ConfigurationBuilder cb;
        cb=new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("q9UpW0SlvRvhdyoHKut4ZP9dY")
                .setOAuthConsumerSecret("ttEyIWWXLv3pPV1Apo4WRQImomJBSf6ndyA9SXoceAdwREf2rO")
                .setOAuthAccessToken("1546403767-Nh5ojGa7Odwid63ULdxNff6UsoMHct9eV290SxE")
                .setOAuthAccessTokenSecret("d2t4oWlvkCU24ZA1oM7q6oGpZDtJDf7q00UVfpXR2DuD6")
                ;
        conf=cb.build();
    }
    
    void commitNow(){
        ts.shutdown();
        sl.i.commitNow();
        //userCountry.commitNow();
    }
    
    myTwitter(JLabel ret){
        
        ts = new TwitterStreamFactory(conf).getInstance();
        try{
            stmt = inConn.createStatement();
        }catch(SQLException e){
            ExceptionManager.handleException(e, "Serious Error at myTwitter.java method myTwitter()");
        }
        polarityAnalyzer=new PolarityAnalyzer();
        this.ret=ret;
    }
    /*WHEN ARE YOU STOPPING SCHEDULING???????????*/
    @Override
    public void run(){
        f=new ArrayList<>();
        fq=new FilterQuery();
        try {
            ResultSet rs=stmt.executeQuery("select moviename from movienames");
            while(rs.next()){
                f.add(rs.getString("movieName"));
            }
            String[] tmp=new String[f.size()];
            f.toArray(tmp);
            fq.track(tmp);
            sl=new myStatusListener();
            ts.addListener(sl);
            ts.filter(fq);
        } catch (SQLException ex) {
            ExceptionManager.handleException(ex, "");
        }
    }
    
    
    class myStatusListener implements StatusListener{
        ConcurrentLinkedQueue<Status> statusList=new ConcurrentLinkedQueue<>();
        
        Inserter i=new Inserter();
        //UserFollowerCountInserter ii=new UserFollowerCountInserter();
        
        @SuppressWarnings("CallToThreadStartDuringObjectConstruction")
        myStatusListener(){
            Thread t1=new Thread(i);
            t1.setPriority(Thread.MAX_PRIORITY);
            t1.start();
            //Thread t=new Thread(ii);
            //t.setPriority(Thread.MIN_PRIORITY);
            //t.start();
            //userCountry=new UserCountry();
            //Thread t=new Thread(userCountry);
            //t.setPriority(Thread.MAX_PRIORITY-1);
            //t.start();
        }

        @Override
        public void onDeletionNotice(StatusDeletionNotice sdn) {
            //NOT HONORING CODE OF CONDUCT :P
        }

        @Override
        public void onScrubGeo(long l, long l1) {
            //NOT HONORING CODE OF CONDUCT :P
        }
        
        class Inserter implements Runnable{
            private boolean isRunning=true;
            private PreparedStatement pstmt=null;
            
            String getMovieContext(String text){
                for(String i: f){
                    if(text.toLowerCase().contains(i.toLowerCase())){
                        return i;
                    }
                }
                return "";
            }
            
            void commitNow(){
                isRunning=false;
                while(statusList.isEmpty()==false){
                    Status arg0=statusList.poll();
                    String text=arg0.getText();
                    
                    if(!arg0.getLang().equals("en"))
                        continue;
                    
                    try {
                        pstmt.setTimestamp(1, new java.sql.Timestamp(arg0.getCreatedAt().getTime()));
                    	pstmt.setString(2, text);
                        String tmp=arg0.getUser().getScreenName();
			pstmt.setString(3, tmp);
                        //ii.userList.add(tmp);
			pstmt.setInt(4, polarityAnalyzer.getSentiment(text));
			pstmt.setString(5, getMovieContext(text));
                        if(arg0.getPlace()==null){
                            pstmt.setString(6, "");
                            //if(!UserCountry.userList.contains(tmp))
                              //  UserCountry.userList.add(tmp);
                        }else{
                            pstmt.setString(6, arg0.getPlace().getCountry());
                        }
                        
	                pstmt.addBatch();
                    } catch (Exception e) {
                        ExceptionManager.handleException(e, "Something went wrong!");
                    }
		}
                
                try{
                pstmt.executeBatch();
                }catch(SQLException e){
                    ExceptionManager.handleException(e, "Something went wrong!");
                }
            }

            @Override
            public void run() {
                int count=0;
                try {
			pstmt=outConn.prepareStatement("INSERT INTO tweets values(?,?,?,?,?,?)");
		} catch (SQLException e) {
                        ExceptionManager.handleException(e, "");
		}
                
                while(isRunning){
		if(statusList.isEmpty()==false){
                    Status arg0=statusList.poll();
                    if(!arg0.getLang().equals("en"))
                        continue;
                    ret.setText("STREAMING IN TWEETS | Received " + totalProcessedTweets + " tweets");
                    MainGUI.setFormTitle(String.valueOf(totalProcessedTweets++));
                    count++;
                    String text=arg0.getText();
                    try {
                        pstmt.setTimestamp(1, new java.sql.Timestamp(arg0.getCreatedAt().getTime()));
                    	pstmt.setString(2, text);
                        String str=arg0.getUser().getScreenName();
                        //ii.userList.add(str);
			pstmt.setString(3, str);
                        pstmt.setDouble(4, polarityAnalyzer.getSentiment(text));
			pstmt.setString(5, getMovieContext(text));
                        if(arg0.getPlace()==null){
                            pstmt.setString(6, "");
                            //if(!UserCountry.userList.contains(str))
                              //  UserCountry.userList.add(str);
                        }else{
                            pstmt.setString(6, arg0.getPlace().getCountry());
                        }
	                pstmt.addBatch();
	                if(count>=commitOn){
                            pstmt.executeBatch();
                            count=0;
	                }
                    } catch (Exception e) {
                        ExceptionManager.handleException(e, "Something went wrong!");
                    }
		}
		}
                try{
                    pstmt.executeBatch();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        
        @Override
        public void onStatus(Status status) {
            statusList.add(status);
        }

        @Override
        public void onTrackLimitationNotice(int i) {
            System.out.println(new java.util.Date().toString()+": Number of tweets missed: "+i);
        }

        @Override
        public void onStallWarning(StallWarning sw) {
            System.out.println(new java.util.Date().toString()+": Your connection is falling behind and messages are being queued for delivery to you. Your queue is now over 60% full. You will be disconnected when the queue is full.");
        }

        @Override
        public void onException(Exception e) {
            e.printStackTrace();
        }
        
    }
}

class MyRestAPI{
    static Twitter t;
    static{
        t=new TwitterFactory(myTwitter.conf).getInstance();
    }
    
    
    static int getFollowerCount(String userScreenName) throws TwitterException{
        return t.showUser(userScreenName).getFollowersCount();
    }
    
    static String getUserCountry(String userScreenName) {
        try{
        String str=t.showUser(userScreenName).getLocation();
        if(str==null)
            return "";
        str=str.replaceAll(" ", "%20");
        URLConnection con = new URL("http://maps.googleapis.com/maps/api/geocode/json?address="+str+"&sensor=false").openConnection();
        BufferedReader is=new BufferedReader(new InputStreamReader(con.getInputStream()));
        String[] a=new String[100];
        int i=0;
        while(is.ready()){
            a[i]=is.readLine();
            if(a[i].contains("country")){
                return (a[i-2].substring(a[i-2].indexOf(":")+3)).replace("\",", "");
            }
            i++;
        }
        return "";
        }catch(Exception e){
            return "";
        }
    }
}
/*
class UserCountry implements Runnable{
    static LinkedList<String> userList=new LinkedList<>();
    private PreparedStatement pstmt;
    private Statement stmt;
    private boolean isRunning=true;
    
    void commitNow(){
        isRunning=false;
        int count=0;
        while(userList.isEmpty()==false){
            try{
                String str=userList.poll();
                ResultSet rs=stmt.executeQuery("select country from tweets where userscreenname='"+str+"' limit 1");
                if(rs.first() &&  !rs.getString(1).equals("")){
                    pstmt.setString(1, rs.getString(1));
                    pstmt.setString(2, str);
                    pstmt.addBatch();
                    continue;
                }
                count++;
                String tmp=MyRestAPI.getUserCountry(str);
                if(tmp.equals(""))
                    continue;
                pstmt.setString(1, tmp);
                pstmt.setString(2, str);
                pstmt.addBatch();
                if(count>commitOn){
                    pstmt.executeBatch();
                    count=0;
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        try{
            pstmt.executeBatch();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        int count=0;
        try{
            pstmt=outConn.prepareStatement("update tweets set country=? where userscreenname=? and country='' limit 1");
            stmt=inConn.createStatement();
            }catch(SQLException e){
            e.printStackTrace();
            return;
        }
            while(isRunning){
                if(userList.isEmpty()==false){
                    try{
                        String str=userList.poll();
                        ResultSet rs=stmt.executeQuery("select country from tweets where userscreenname='"+str+"' limit 1");
                        if(rs.first() &&  !rs.getString(1).equals("")){
                            pstmt.setString(1, rs.getString(1));
                            pstmt.setString(2, str);
                            pstmt.addBatch();
                            continue;
                        }
                        String tmp=MyRestAPI.getUserCountry(str);
                        if(tmp.equals(""))
                            continue;
                        count++;
                        pstmt.setString(1, tmp);
                        pstmt.setString(2, str);
                        pstmt.addBatch();
                        if(count>=commitOn){
                            pstmt.executeBatch();
                            count=0;
                    }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
            }
            try{
                pstmt.executeBatch();
            }catch(SQLException e){
                e.printStackTrace();
            }
        
    }
    
}

/*
class UserFollowerCountInserter implements Runnable{
    ConcurrentLinkedQueue<String> userList=new ConcurrentLinkedQueue<>();
    
    @Override
    public void run() {
        Statement stmt=null;
        int count=0;
        try{
            stmt=outConn.createStatement();
        }catch(SQLException e){
            e.printStackTrace();
            return;
        }
        while(true){
            if(userList.isEmpty()==false){
                count++;
                try{
                    String str=userList.poll();
                    int tmp=MyRestAPI.getFollowerCount(str);
                    stmt.addBatch("insert into userdb values('"+str+"',"+tmp+")");
                    if(count>=commitOn+5){
                        stmt.executeBatch();
                        count=0;
                }
                }catch(SQLException | TwitterException e){
                    //DUPLICATE USER!!
                }
            }
        }
    }
    
}*/