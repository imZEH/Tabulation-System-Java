package Import_Export;


 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
 
 

// try{ 
//           JFileChooser f = new JFileChooser();
//        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
//        f.showSaveDialog(null);
//       File g = new File("/");
//        File path = f.getSelectedFile();
//        String a = path.toString()+""+g;
//       //System.out.print(""+"hello");
//         new Export_Database().Export(a);
//       JOptionPane.showMessageDialog(null,"Successfully Exported");
//       }catch(Exception e){
//           JOptionPane.showMessageDialog(null,e);
//       }

 
public class Export_Database {
 
    /**
     * @param args
     */
    public void Export(String path) throws Exception {
          
          //usual database connection part
          Connection con = null;
          
          FileWriter fw ;
          try{
          
          con =  (java.sql.Connection) Tabulation.Connections.Conn.getMySqlConnection();
          Statement st = con.createStatement();
           
          //this query gets all the tables in your database(put your db name in the query)
            ResultSet res = st.executeQuery("SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = 'auto' ");
           
          //Preparing List of table Names
          List <String> tableNameList = new ArrayList<String>();
          while(res.next())
          {
              tableNameList.add(res.getString(1));
          }
           
          //path to the folder where you will save your csv files
          
          /*JFrame frame = null;
            JFileChooser chooser;
            String path = "";
            JFileChooser fc = new JFileChooser();
            if (fc.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            File selFile = fc.getSelectedFile();
            path = selFile.getPath();
        
        }*/
         
          String filename = path;
           
          //star iterating on each table to fetch its data and save in a .csv file
          for(String tableName:tableNameList)
            {
                int k=0;
                 
                int j=1;
                 
                System.out.println(tableName);
                 
                List<String> columnsNameList  = new ArrayList<String>();
                 
                //select all data from table
                res = st.executeQuery("select * from auto."+tableName);
                 
                //colunm count is necessay as the tables are dynamic and we need to figure out the numbers of columns
                int colunmCount = getColumnCount(res);
                 
                 try {
                    fw = new FileWriter(filename+""+tableName+".csv");
                     
                     
                    //this loop is used to add column names at the top of file , if you do not need it just comment this loop
                    for(int i=1 ; i<= colunmCount ;i++)
                    {
                        fw.append(res.getMetaData().getColumnName(i));
                        fw.append(",");
             
                    }
                     
                    fw.append(System.getProperty("line.separator"));
                     
                    while(res.next())
                    {
                        for(int i=1;i<=colunmCount;i++)
                        {
                             
                            //you can update it here by using the column type but i am fine with the data so just converting
                            //everything to string first and then saving
                            if(res.getObject(i)!=null)
                            {
                            String data= res.getObject(i).toString();
                            fw.append(data) ;
                            fw.append(",");
                            }
                            else
                            {
                                String data= "null";
                                fw.append(data) ;
                                fw.append(",");
                            }
                             
                        }
                        //new line entered after each row
                        fw.append(System.getProperty("line.separator"));
                    }
                     
                     fw.flush();
                      fw.close();
                     
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                 
            }
                 
          con.close();
          }
          catch (ClassNotFoundException e){
          System.err.println(e);
          e.printStackTrace();
          }
          catch(SQLException ex){
          System.err.println(ex);
          }
 }
     
    //to get numbers of rows in a result set
    public static int  getRowCount(ResultSet res) throws SQLException
    {
          res.last();
          int numberOfRows = res.getRow();
          res.beforeFirst();
          return numberOfRows;
    }
 
    //to get no of columns in result set
     
    public static int  getColumnCount(ResultSet res) throws SQLException
    {
        return res.getMetaData().getColumnCount();
    }
     
 
}