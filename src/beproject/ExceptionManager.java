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


import java.awt.Toolkit;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Thigale Sameer
 */
public class ExceptionManager {
    private static PrintWriter fw;
    static{
        try{
            fw=new PrintWriter(new FileWriter("hypeanalyzerlog.txt"));
        }catch(IOException e){
            System.err.println("FATAL ERROR: COULD NOT CREATE LOG FILE");
            System.exit(-1);
        }
        
    }
    public static void handleException(Exception e, String msg){
        e.printStackTrace(fw);
        fw.println(msg);
        fw.close();
        
        Thread t=new Thread(new Runnable(){
            @Override
            public void run(){
                while(true)
                    Toolkit.getDefaultToolkit().beep();
            }
        });
        t.setPriority(Thread.MIN_PRIORITY);
        t.start();
        
        JOptionPane.showMessageDialog(null, "This program has encountered an Exception. Please check out the hypeanalyzerlog.txt in PWD", "FATAL ERROR: PROGRAM TERMINATES", JOptionPane.ERROR_MESSAGE);
        System.exit(-1);
    }
}
