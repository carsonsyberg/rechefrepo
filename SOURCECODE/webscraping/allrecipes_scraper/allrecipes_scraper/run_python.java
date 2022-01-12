// to compile and run me, type (in the spiders directory): javac run_python.java then java run_python

import java.io.*;
 
class run_python {
    public static void main(String a[]) {
        try {
            
            // Run the scrapy script first
            ProcessBuilder pba = new ProcessBuilder("python","__init__.py");
            Process pa = pba.start();
            pa.waitFor(); // waits until this process is terminated

            // Then run the json_to_sql script
            ProcessBuilder pbb = new ProcessBuilder("python","json_to_sql.py");
            Process pb = pbb.start();

        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}