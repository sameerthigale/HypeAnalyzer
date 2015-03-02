package beproject;

import static beproject.Initializer.inConn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thigale Sameer
 */
public class ScheduledMoviesList {
    Statement stmt;
    public static final String getMovieNamesString="select moviename from movienames";
    private static List<String> movieList;
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static List<String> getMovieNames(){
        if(movieList==null){
            new ScheduledMoviesList();
        }
        
        return movieList;
    }
    
    private ScheduledMoviesList(){
        movieList=new ArrayList<>();
        try{
            stmt = inConn.createStatement();
            ResultSet rs=stmt.executeQuery(getMovieNamesString);
            while(rs.next()){
                movieList.add(rs.getString(1));
            }
        }catch(SQLException e){
            ExceptionManager.handleException(e, "Something wrong in ScheduledMoviesList.ScheduledMoviesList()");
        }
    }
}
