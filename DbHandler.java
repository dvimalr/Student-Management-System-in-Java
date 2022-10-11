
class DbHandler
{
int rno;
String name;
int s1, s2, s3;
public DbHandler(){}

public DbHandler(int rno,String name,int s1,int s2,int s3){
this.rno = rno;
this.name = name;
this.s1 = s1;
this.s2 = s2;
this.s3 = s3;
}
public void setrno(int rno){ 	this.rno = rno;}
public int getrno() { return rno;}
public void setname(String name){ this.name = name;}
public String getname(){ return name;}
public void sets1(int s1){ this.s1 = s1;}
public int gets1(){return s1;}
public void sets2(int s2){ this.s2 = s2;}
public int gets2(){return s2;}
public void sets3(int s3){ this.s3 = s3;}
public int gets3(){return s3;}


}