package util.common;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
   public class Path{
      private static final String PROP_FILE="../../pathConfig.properties";
      
      public void readPropertiesFile(){
          try{
             InputStream is = Path.class.getResourceAsStream(PROP_FILE);
             Properties prop = new Properties();
                 prop.load(is);
            String directory = prop.getProperty("Directory");
            System.out.println(directory);
                String numberOfFiles = prop.getProperty("NumberOfFiles");
            System.out.println(numberOfFiles);
            String  fileExtension = prop.getProperty("Extension");
            System.out.println(fileExtension);
            is.close();
         /* code to use values read from the file*/
          }catch(Exception e){
            System.out.println("Failed to read from " + PROP_FILE + " file.");
          }
      }
      public static String getPath(){
        try {
            InputStream is = Path.class.getResourceAsStream(PROP_FILE);
            Properties prop = new Properties();
            prop.load(is);
            String path = prop.getProperty("pdfPath");
            //System.out.println(path);
            return  path;
        } catch (IOException ex) {
            Logger.getLogger(Path.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      }
      
      public String getReportPath(String pathx){
          try {
              InputStream is = Path.class.getResourceAsStream(PROP_FILE);
              Properties prop = new Properties();
              prop.load(is);
              String path = prop.getProperty(pathx);
              //System.out.println(path);
              return  path;
          } catch (IOException ex) {
              Logger.getLogger(Path.class.getName()).log(Level.SEVERE, null, ex);
              return null;
          }
        }
      
      public String getDBUrl(String region)
      {
    	  try {
              InputStream is = Path.class.getResourceAsStream(PROP_FILE);
              Properties prop = new Properties();
              prop.load(is);
              
              String db_url = null;
              if (region.equals("R1")){
  				db_url = prop.getProperty("db_url_R1");  				 
  			  } else if (region.equals("R2")){
  				db_url = prop.getProperty("db_url_R2");  			 
  			  }else if (region.equals("R3")){
  				db_url = prop.getProperty("db_url_R3");  			 
  			  }else if (region.equals("R4")){
  				db_url = prop.getProperty("db_url_R4");  			 
  			  }else if (region.equals("AM")){
  				db_url = prop.getProperty("db_url_AM");  			 
  			  }else {
   				db_url = prop.getProperty("db_url_RX");  			 
   			  }
             
               
              return  db_url;
          } catch (IOException ex) {
              Logger.getLogger(Path.class.getName()).log(Level.SEVERE, null, ex);
              return null;
          }
      }
      
      public String getDBUserName(String region)
      {
    	  try {
              InputStream is = Path.class.getResourceAsStream(PROP_FILE);
              Properties prop = new Properties();
              prop.load(is);
              
              String db_url = null;
            if (region.equals("R1")){
  				db_url = prop.getProperty("db_userName_R1");  				 
  			} else if (region.equals("R2")){
  				db_url = prop.getProperty("db_userName_R2");  			 
  			} else if (region.equals("R3"))	{
  				db_url = prop.getProperty("db_userName_R3");  			 
  			} else if (region.equals("R4")){
  				db_url = prop.getProperty("db_userName_R4");  			 
  			}else if (region.equals("AM")){
  				db_url = prop.getProperty("db_userName_AM");  			 
  			}else {
  				db_url = prop.getProperty("db_userName_RX");  			 
  			}
             
               
              return  db_url;
          } catch (IOException ex) {
              Logger.getLogger(Path.class.getName()).log(Level.SEVERE, null, ex);
              return null;
          }
      }
      
      public String getDBPassword(String region)
      {
    	  try {
              InputStream is = Path.class.getResourceAsStream(PROP_FILE);
              Properties prop = new Properties();
              prop.load(is);
              
              String db_url = null;
              if (region.equals("R1")){
  				db_url = prop.getProperty("db_password_R1");  				 
  			  } else if (region.equals("R2"))	{
  				db_url = prop.getProperty("db_password_R2");  			 
  			  } else if (region.equals("R3"))	{
  				db_url = prop.getProperty("db_password_R3");  			 
  			  }else if (region.equals("R4")){
  				db_url = prop.getProperty("db_password_R4");  			 
  			  }else if (region.equals("AM")){
    				db_url = prop.getProperty("db_password_AM");  			 
    		  }else {
  				db_url = prop.getProperty("db_password_RX");  			 
  			  }
               
              return  db_url;
          } catch (IOException ex) {
              Logger.getLogger(Path.class.getName()).log(Level.SEVERE, null, ex);
              return null;
          }
      }
      
      /*public static String getWebAppName(){
          try {
              InputStream is = Path.class.getResourceAsStream(PROP_FILE);
              Properties prop = new Properties();
              prop.load(is);
              String path = prop.getProperty("webAppName");
              //System.out.println(path);
              return  path;
          } catch (IOException ex) {
              Logger.getLogger(Path.class.getName()).log(Level.SEVERE, null, ex);
              return null;
          }
        }*/
      
      public static String getBillExportPath(){
          try {
              InputStream is = Path.class.getResourceAsStream(PROP_FILE);
              Properties prop = new Properties();
              prop.load(is);
              String path = prop.getProperty("BillExportPath");
              //System.out.println(path);
              return  path;
          } catch (IOException ex) {
              Logger.getLogger(Path.class.getName()).log(Level.SEVERE, null, ex);
              return null;
          }
        }
      
      
      public static void main(String[] args){
         // Path path= new Path();
          //System.out.println("hgfsdhafjhsa");
          //path.readPropertiesFile();
          System.out.println(Path.getBillExportPath());
          System.out.println(Path.getPath());
      }
     }