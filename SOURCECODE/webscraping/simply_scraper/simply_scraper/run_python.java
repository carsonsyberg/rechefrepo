// to compile and run me, type in this directory: javac run_python.java then java run_python

import java.io.*;
 
class run_python {
    public static void main(String a[]) {
        try {
            
            // Run the scrapy script first
           // System.out.println("scraping");
            ProcessBuilder pba = new ProcessBuilder("python","./spiders/__init__.py");
            Process pa = pba.start();
            pa.waitFor(); // waits until this process is terminated

            // Then run the json_to_sql script
            //System.out.println("converting");
            ProcessBuilder pbb = new ProcessBuilder("python3","json_to_sql.py");
            Process pb = pbb.start();
            pb.waitFor();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}
