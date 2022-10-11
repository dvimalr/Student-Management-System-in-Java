import org.hibernate. * ;
import org.hibernate.cfg. * ;
import java.awt. * ;
import java.awt.event. * ;
import javax.swing. * ;
import org.jfree.chart. * ;
import org.jfree.chart.plot. * ;
import org.jfree.data.category. * ;
import java.io. * ;
import java.util. * ;
import java.sql. * ;

class sms extends JFrame 
{
Container c;
JButton btnadd, btnview, btnupdate, btndelete, btncharts;

sms() 
{
c = getContentPane();
c.setLayout(null);

Font f = new Font("Times New Roman", Font.BOLD, 30);

btnadd = new JButton("Add");
btnadd.setBounds(250, 50, 300, 50);
btnadd.setFont(f);
c.add(btnadd);
    
btnview = new JButton("View");
btnview.setBounds(250, 150, 300, 50);
btnview.setFont(f);
c.add(btnview);
    
btnupdate = new JButton("Update");
btnupdate.setBounds(250, 250, 300, 50);
btnupdate.setFont(f);
c.add(btnupdate);

    
btndelete = new JButton("Delete");
btndelete.setBounds(250, 350, 300, 50);
btndelete.setFont(f);
c.add(btndelete);  
  
btncharts = new JButton("Charts");
btncharts.setBounds(250, 450, 300, 50);
btncharts.setFont(f);
c.add(btncharts);

ActionListener al = (ae) ->{ AddStud a = new AddStud(); dispose(); };
btnadd.addActionListener(al);

ActionListener alv = (ae) ->{ ViewStud a = new ViewStud(); dispose(); };
btnview.addActionListener(alv);

ActionListener alu = (ae) ->{ UpdateStud a = new UpdateStud(); dispose(); };
btnupdate.addActionListener(alu);

ActionListener ald = (ae) ->{ DeleteStud a = new DeleteStud(); dispose(); };
btndelete.addActionListener(ald);

ActionListener alc = (ae) ->{ ChartsStudent a = new ChartsStudent(); dispose(); };
btncharts.addActionListener(alc);

setTitle("S.M.S");
setSize(800, 600);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}

}


class smsMain {
public static void main(String args[]) {
Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");
SessionFactory sf = cfg.buildSessionFactory();
Transaction t = null;
Session s = null;
try {
      s = sf.openSession();
      System.out.println(" Connected hib");
      t = s.beginTransaction();

    }
    catch(Exception e) {
      System.out.println(" Error hib");
    }
    finally {
      s.close();
      System.out.println(" closed ");
    }
    sms e = new sms();
  }
}


class AddStud extends JFrame 
{
Container c;
JLabel label1, label2, label3, label4, label5;
JTextField tf1, tf2, tf3, tf4, tf5;
JButton btnsave, btnback;


AddStud() {

c = getContentPane();
c.setLayout(null);

Font f = new Font("Times New Roman", Font.BOLD, 30);

label1 = new JLabel("Enter rno:");
label1.setBounds(200, 20, 300, 50);
label1.setFont(f);
c.add(label1);

tf1 = new JTextField(20);
tf1.setBounds(200, 80, 300, 40);
tf1.setFont(f);
c.add(tf1);
    
label2 = new JLabel("Enter name:");
label2.setBounds(200, 130, 300, 50);
label2.setFont(f);
c.add(label2);
    
tf2 = new JTextField(20);
tf2.setBounds(200, 180, 300, 40);
tf2.setFont(f);
c.add(tf2);
    
label3 = new JLabel("Enter sub marks 1:");
label3.setBounds(200, 240, 300, 50);
label3.setFont(f);
c.add(label3);
    
tf3 = new JTextField(20);
tf3.setBounds(200, 290, 300, 40);
tf3.setFont(f);
c.add(tf3);
    
label4 = new JLabel("Enter sub marks 2:");
label4.setBounds(200, 350, 300, 50);
label4.setFont(f);
c.add(label4);
    
tf4 = new JTextField(20);
tf4.setBounds(200, 400, 300, 40);
tf4.setFont(f);
c.add(tf4);
    
label5 = new JLabel("Enter sub marks 3:");
label5.setBounds(200, 460, 300, 50);
label5.setFont(f);
c.add(label5);
    
tf5 = new JTextField(20);
tf5.setBounds(200, 510, 300, 40);
tf5.setFont(f);
c.add(tf5);
    
btnsave = new JButton("Save");
btnsave.setBounds(200, 580, 300, 50);
btnsave.setFont(f);
c.add(btnsave);
    
btnback = new JButton("Back");
btnback.setBounds(200, 640, 300, 50);
btnback.setFont(f);
c.add(btnback);

ActionListener al = (ae) ->{ new sms(); dispose(); };
btnback.addActionListener(al);

ActionListener a = (ae) ->{
      int rno = 0;
      int s1 = 0,
      s2 = 0,
      s3 = 0;
      String name = "";

      try {
        rno = Integer.parseInt(tf1.getText());
        name = tf2.getText();
        s1 = Integer.parseInt(tf3.getText());
        s2 = Integer.parseInt(tf4.getText());
        s3 = Integer.parseInt(tf5.getText());
      }
      catch(NumberFormatException e) {
        System.out.println(e);
      }

      if (rno == 0 && name.isEmpty() && s1 == 0 && s2 == 0 && s3 == 0) {
        JOptionPane.showMessageDialog(new JDialog(), "Enter All Details Correctly");
      }

      int len=0;
      len = name.length();
      Session s = null;
      try {

        if (rno > 0 && !name.isEmpty() && len >= 2) {
          if (s1 > 0 && s1 < 100 && s2 > 0 && s2 < 100 && s3 > 0 && s3 < 100) {
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            SessionFactory sf = cfg.buildSessionFactory();
            Transaction t = null;
            s = sf.openSession();
            System.out.println(" Connected hib");
            t = s.beginTransaction();
            DbHandler db = (DbHandler) s.get(DbHandler.class, rno);
            
            DbHandler d = new DbHandler(rno, name, s1, s2, s3);

            if (d == null || db != null) {
              JOptionPane.showMessageDialog(new JDialog(), "Record not Added!!");
            } else if (db == null) {
              JOptionPane.showMessageDialog(new JDialog(), "Record Added");
              s.save(d);
              t.commit();
            }

            System.out.println("Record added hib");
          } else {
            JOptionPane.showMessageDialog(new JDialog(), "Enter Valid Marks");
          }
        }
        else if (rno <= 0 && rno != ' ') {
          JOptionPane.showMessageDialog(new JDialog(), "Enter Valid Roll NO");
        }
        else if (name == null) {
          JOptionPane.showMessageDialog(new JDialog(), "Enter Valid Name");
        }
        else if (len < 2) {
          JOptionPane.showMessageDialog(new JDialog(), "Enter Valid Name");
        }
      } catch(Exception e) {
        System.out.println(" Error hib");
      }
      finally {
        s.close();
        System.out.println(" closed ");
      }

    };
    btnsave.addActionListener(a);
    setTitle("Add St.");
    setSize(750, 750);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
}

class ViewStud extends JFrame 
{
Container c;
JTextArea tadata;
JButton btnback;

ViewStud()
{
c = getContentPane();
c.setLayout(null);

Font f = new Font("Times New Roman", Font.BOLD, 30);

tadata = new JTextArea(10, 50);
tadata.setBounds(50, 40, 900, 400);
tadata.setFont(f);
c.add(tadata);

    
btnback = new JButton("Back");
btnback.setBounds(400, 500, 200, 50);
btnback.setFont(f);
c.add(btnback);

ActionListener al = (ae) ->{ new sms(); dispose(); };
btnback.addActionListener(al);

StringBuffer sb = new StringBuffer();
Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");
SessionFactory sf = cfg.buildSessionFactory();
Session s = null;
try {
      s = sf.openSession();
      System.out.println(" Connected hib");
      java.util.List < DbHandler > data = new ArrayList < >();
      data = s.createQuery("from DbHandler").list();
      for (DbHandler db: data) {
        System.out.println(db.getrno() + " " + db.getname() + " " + db.gets1() + " " + db.gets2() + " " + db.gets3());
        sb.append(" Rno: " + db.getrno() + " Student name: " + db.getname() + " Marks1: " + db.gets1() + " Marks2: " + db.gets2() + " Marks3: " + db.gets3() + "\n");
      }
      tadata.setText(sb.toString());
    } catch(Exception e) {
      System.out.println(" Error hib");
    }
    finally {
      s.close();
      System.out.println(" closed ");
    }
    //String data = new DbHandler().viewStudent();
    //tadata.setText(data);

setTitle("View St.");
setSize(1000, 600);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}

}


class UpdateStud extends JFrame
{
Container c;
JLabel label1, label2, label3, label4, label5;
JTextField tf1, tf2, tf3, tf4, tf5;
JButton btnsave, btnback;

UpdateStud() {
c = getContentPane();
c.setLayout(null);

Font f = new Font("Times New Roman", Font.BOLD, 30);

label1 = new JLabel("Enter rno:");
label1.setBounds(200, 20, 300, 50);
label1.setFont(f);
c.add(label1);
    
tf1 = new JTextField(20);
tf1.setBounds(200, 80, 300, 40);
tf1.setFont(f);
c.add(tf1);
    
label2 = new JLabel("Enter name:");
label2.setBounds(200, 130, 300, 50);
label2.setFont(f);
c.add(label2);
    
tf2 = new JTextField(20);
tf2.setBounds(200, 180, 300, 40);
tf2.setFont(f);
c.add(tf2);
    
label3 = new JLabel("Enter sub marks 1:");
label3.setBounds(200, 240, 300, 50);
label3.setFont(f);
c.add(label3);
    
tf3 = new JTextField(20);
tf3.setBounds(200, 290, 300, 40);
tf3.setFont(f);
c.add(tf3);
    
label4 = new JLabel("Enter sub marks 2:");
label4.setBounds(200, 350, 300, 50);
label4.setFont(f);
c.add(label4);
    
tf4 = new JTextField(20);
tf4.setBounds(200, 400, 300, 40);
tf4.setFont(f);
c.add(tf4);
    
label5 = new JLabel("Enter sub marks 3:");
label5.setBounds(200, 460, 300, 50);
label5.setFont(f);
c.add(label5);
    
tf5 = new JTextField(20);
tf5.setBounds(200, 510, 300, 40);
tf5.setFont(f);
c.add(tf5);
    
btnsave = new JButton("Update");
btnsave.setBounds(200, 580, 300, 50);
btnsave.setFont(f);
c.add(btnsave);
    
btnback = new JButton("Back");
btnback.setBounds(200, 640, 300, 50);
btnback.setFont(f);
c.add(btnback);

ActionListener al = (ae) ->{ new sms(); dispose(); };
btnback.addActionListener(al);

ActionListener a = (ae) ->{
      int rno = 0;
      int s1 = 0,
      s2 = 0,
      s3 = 0;
      String name = null;

      try {
        rno = Integer.parseInt(tf1.getText());
        name = tf2.getText();
        s1 = Integer.parseInt(tf3.getText());
        s2 = Integer.parseInt(tf4.getText());
        s3 = Integer.parseInt(tf5.getText());
      }
      catch(NumberFormatException e) {
        System.out.println(e);
      }

      if (rno == 0 && name.isEmpty() && s1 == 0 && s2 == 0 && s3 == 0) {
        System.out.println("count 5");
        JOptionPane.showMessageDialog(new JDialog(), "Enter All Details Correctly");
      }

      int len=0;
      len = name.length();
      Session s = null;
       System.out.println("count 4");
      try {
        if (rno > 0 && !name.isEmpty() && len >= 2) {
          System.out.println("count 1");
          if (s1 > 0 && s1 < 100 && s2 > 0 && s2 < 100 && s3 > 0 && s3 < 100) {
           Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            SessionFactory sf = cfg.buildSessionFactory();
            Transaction t = null;
            s = sf.openSession();
            System.out.println(" Connected hib");
            t = s.beginTransaction();
            DbHandler db = (DbHandler) s.get(DbHandler.class, rno);
            if (db == null) {
              System.out.println("value=" + db);
              JOptionPane.showMessageDialog(new JDialog(), "Student Does not Exist!!");
            } else {
              db.setname(name);
              db.sets1(s1);
              db.sets2(s2);
              db.sets3(s3);
              JOptionPane.showMessageDialog(new JDialog(), "Record Updated");
            }

            s.save(db);
            t.commit();
            System.out.println("Record Updated hib");
          }else {
            JOptionPane.showMessageDialog(new JDialog(), "Enter Valid Marks");
          }
          //new DbHandler().updateStudent(rno,sname,m1,m2,m3);
        }
        else if (rno <= 0 && rno != ' ') {
           System.out.println("count 7");
          JOptionPane.showMessageDialog(new JDialog(), "Enter Valid Roll NO");
        }
        else if (name == null) {
          System.out.println("count 2");
          JOptionPane.showMessageDialog(new JDialog(), "Enter Valid Name");
        }
        else if (len < 2) {
           System.out.println("count 8");
          JOptionPane.showMessageDialog(new JDialog(), "Enter Valid Name");
        }
      }
      catch(Exception e) {
        System.out.println(" Error hib" + e);
      }
      finally {
      // s.close();
        System.out.println(" closed ");
      }
    };
btnsave.addActionListener(a);

setTitle("Update St.");
setSize(750, 750);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
  }
}

class DeleteStud extends JFrame 
{
Container c;
JLabel label1;
JTextField tadata;
JButton btnsave, btnback;

DeleteStud() {
c = getContentPane();
c.setLayout(null);

Font f = new Font("Times New Roman", Font.BOLD, 30);

label1 = new JLabel("Enter rno:");
label1.setBounds(100, 20, 300, 50);
label1.setFont(f);
c.add(label1);

tadata = new JTextField(20);
tadata.setBounds(100, 80, 300, 40);
tadata.setFont(f);
c.add(tadata);
        
btnsave = new JButton("Delete");
btnsave.setBounds(100, 150, 300, 50);
btnsave.setFont(f);
c.add(btnsave);

btnback = new JButton("Back");
btnback.setBounds(100, 220, 300, 50);
btnback.setFont(f);
c.add(btnback);

ActionListener al = (ae) ->{ new sms(); dispose(); };
btnback.addActionListener(al);

ActionListener a = (ae) ->{
      int rno = 0;
      try {
        rno = Integer.parseInt(tadata.getText());
      }
      catch(NumberFormatException e) {
        System.out.println(e);
      }
      Configuration cfg = new Configuration();
      cfg.configure("hibernate.cfg.xml");
      SessionFactory sf = cfg.buildSessionFactory();
      Transaction t = null;
      Session s = null;
      try {
        s = sf.openSession();
        System.out.println(" Connected hib");
        t = s.beginTransaction();
        DbHandler db = (DbHandler) s.get(DbHandler.class, rno);
        if (db == null) {
          JOptionPane.showMessageDialog(new JDialog(), "Student Does not Exist!!");
        } else {
          s.delete(db);
          JOptionPane.showMessageDialog(new JDialog(), "Record Deleted");
          t.commit();
          System.out.println("Record Deleted hib");
        }
      } catch(Exception e) {
        System.out.println(" Error hib");
      }
      finally {
        s.close();
        System.out.println(" closed ");
      }

      //new DbHandler().deleteStudent(rno);
    };
btnsave.addActionListener(a);

setTitle("Delete St.");
setSize(500, 500);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}

}

class ChartsStudent extends JFrame {

ChartsStudent() {

DefaultCategoryDataset ds1 = new DefaultCategoryDataset();

    Configuration cfg = new Configuration();
    cfg.configure("hibernate.cfg.xml");
    SessionFactory sf = cfg.buildSessionFactory();
    Session s = null;
    try {
      s = sf.openSession();
      System.out.println(" Connected hib");
      java.util.List < DbHandler > data = new ArrayList < >();
      data = s.createQuery("from DbHandler").list();
      for (DbHandler db: data) {
        String name = db.getname();
        int Marks1 = db.gets1();;
        int Marks2 = db.gets2();
        int Marks3 = db.gets3();

        ds1.addValue(Marks1, name, "Marks1");
        ds1.addValue(Marks2, name, "Marks2");
        ds1.addValue(Marks3, name, "Marks3");
      }

    } catch(Exception e) {
      System.out.println("db issue " + e);
    }

    JFreeChart chart = ChartFactory.createBarChart("Student Performance", "Subjects", "Marks", ds1, PlotOrientation.VERTICAL, true, false, false);

    ChartPanel p = new ChartPanel(chart);
    setContentPane(p);

    try {
      File img = new File("perform.jpeg");
      ChartUtilities.saveChartAsJPEG(img, chart, 400, 300);
    } catch(IOException e) {
      System.out.println("issue " + e);
    } finally {
      s.close();
      System.out.println(" closed ");
    }

setTitle("Marks Charts");
setSize(600, 600);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);

  }
}