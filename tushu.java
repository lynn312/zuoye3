package ����;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class first {
 public static void main(String[] args) throws Exception {
  Scanner scan = new Scanner(System.in);
  boolean flag = true;
  while (flag) {
   System.out.println("\nͼ�����ϵͳ��");
   System.out.println("1.ͼ�����");
   System.out.println("2.���߰쿨");
   System.out.println("3.�޸�ͼ��");
   System.out.println("4.ɾ��ͼ��");
   System.out.println("5.�鼮��ѯ��ȫ���鼮��Ϣ�б�");
   System.out.println("6.������Ϣһ����ȫ�����鿨��Ϣ�б�");
   System.out.println("7.ͳ��ĳһʱ��εĽ�����ϸ״�� ");
   System.out.println("8.�˳�");
   System.out.println("��������Ҫ���еĲ�����ţ�\n");
   Class.forName("com.mysql.jdbc.Driver");
   Connection conn = DriverManager.getConnection(
     "jdbc:mysql://localhost:3306/library", "root", "root");
   Statement stat = conn.createStatement();
   switch (scan.next()) {
   case "1":
    System.out.println("������:��ı�ţ��������id���������"
      + "��������ߣ���ĳ����磬��ĳ������ڣ���ļ۸�");
    String sql_book_id = "SELECT books_code FROM books_info";
    String code = scan.next();
    ResultSet rs = stat.executeQuery(sql_book_id);
    while (rs.next()) {
     if (code.equals(rs.getString("books_code"))) {
      System.out.println("�Բ�����������������Ϊ���ݿ����Ѿ����ڸ���ı��");
      System.out.println("�����½������룡");
      code = scan.next();
     }
    }
    int id = scan.nextInt();
    String name = scan.next();
    String author = scan.next();
    String company = scan.next();
    String date = scan.next();
    double price = scan.nextDouble();
    String sql = "INSERT INTO books_info VALUE ('" + code + "',"
      + id + ",'+" + name + "','" + author + "','" + company
      + "','" + date + "'," + price + ")";
    if (!stat.execute(sql)) {
     System.out.println("��ӳɹ�");
    } else {
     System.out.println("���ʧ��");
    }
    break;
   case "2":
    System.out.println("������:���ţ����������������Ա𣬶������ڲ��ţ���������");
    String sql_reader_code = "SELECT card_code FROM reader_info;";
    code = scan.next();
    rs = stat.executeQuery(sql_reader_code);
    while (rs.next()) {
     if (code.equals(rs.getString("books_code"))) {
      System.out.println("�Բ�����������������Ϊ���ݿ����Ѿ����ڸ��˵ı��");
      System.out.println("�����½������룡");
      code = scan.next();
     }
    }
    name = scan.next();
    String sex = scan.next();
    String dep = scan.next();
    int age = scan.nextInt();
    sql = "INSERT INTO reader_info VALUE ('" + code + "','" + name
      + "','" + sex + "','" + dep + "'," + age + ")";
    if (!stat.execute(sql)) {
     System.out.println("��ӳɹ�");
    } else {
     System.out.println("���ʧ��");
    }
    rs.close();
    break;
   case "3":
    System.out.println("��������Ҫ�����޸ĵ�ͼ����:");
    sql_book_id = "SELECT books_code FROM books_info";
    sql = "";
    code = scan.next();
    rs = stat.executeQuery(sql_book_id);
    while (rs.next()) {
     if (code.equals(rs.getString("books_code"))) {
      System.out.println("������:��ı�ţ��������id���������"
        + "��������ߣ���ĳ����磬��ĳ������ڣ���ļ۸�");
      id = scan.nextInt();
      author = scan.next();
      company = scan.next();
      date = scan.next();
      price = scan.nextDouble();
      sql = "update books_info set" + " book_id = '" + id
        + "' book_author = " + "'" + author
        + "' publicsh_company = " + "'" + company
        + "' publish_date = '" + date + "' price = '"
        + price + "' where books_code = '" + code + "'";
      if (!stat.execute(sql)) {
       System.out.println("�޸ĳɹ�");
      } else {
       System.out.println("�޸�ʧ��");
      }
     }
    }
    rs.close();
    break;
   case "4":
    System.out.println("��������Ҫ����ɾ����ͼ����:");
    sql_book_id = "SELECT books_code FROM books_info";
    sql = "";
    code = scan.next();
    rs = stat.executeQuery(sql_book_id);
    while (rs.next()) {
     if (code.equals(rs.getString("books_code"))) {
      id = scan.nextInt();
      author = scan.next();
      company = scan.next();
      date = scan.next();
      price = scan.nextDouble();
      sql = "DELETE FROM books_info WHERE books_code = '"
        + code + "'";
      if (!stat.execute(sql)) {
       System.out.println("ɾ���ɹ�");
      } else {
       System.out.println("ɾ��ʧ��");
      }
     }
    }
    rs.close();
    break;
   case "5":
    sql = "select * from books_info";
    rs = stat.executeQuery(sql);
    while (rs.next()) {
     System.out.println("ͼ���ţ�"+rs.getObject("books_code") + "\tͼ��id"
       + rs.getObject("books_id") + "\tͼ�����ƣ�"
       + rs.getObject("book_name") + "\tͼ�����ߣ�"
       + rs.getObject("book_author") + "\tͼ������磺"
       + rs.getObject("publish_company") + "\tͼ��������ڣ�"
       + rs.getObject("publish_date") + "\tͼ��۸�:"
       + rs.getObject("price"));
    }
    break;
   case "6":
    sql = "select * from reader_info";
    rs = stat.executeQuery(sql);
    while (rs.next()) {
     System.out.println("����֤�ţ�"+rs.getObject("card_code") + "\t������������"
       + rs.getObject("reader_name") + "\t�������Ա�"
       + rs.getObject("reader_sex") + "\t�����߲��ţ�"
       + rs.getObject("reader_department") + "\t���������䣺"
       + rs.getObject("age"));
    }
    rs.close();
    break;
   case "7":
    System.out.println("��������Ҫ��ѯ����������:(��ʽ����:2012-12-12)");
    System.out.println("�������һ�����ڣ�");
    date = scan.next();
    System.out.println("������ڶ�������!");
    String date2 = scan.next();
    sql = "SELECT br.books_code,books_id,book_name,book_author,"
      + "publish_company,publish_date,price,br.card_code,reader_name,"
      + "reader_sex,reader_department,age,br.borrow_id,COUNT(br.borrow_date)"
      + ",br.borrow_date,br.return_date FROM borrow_record br "
      + "RIGHT JOIN reader_info ri ON ri.card_code "
      + "= br.card_code AND  borrow_date BETWEEN '"
      + date
      + "' AND '"
      + date2
      + "'  AND br.books_code != '' AND br.card_code != '' AND "
      + "ri.card_code != ''"
      + "RIGHT JOIN books_info bi ON bi.books_code = "
      + "br.books_code AND  borrow_date BETWEEN '" + date
      + "' AND '" + date2
      + "' AND br.books_code != '' AND br.card_code != ''"
      + "AND ri.card_code != '' "
      + "WHERE br.books_code != ''  "
      + "GROUP BY br.books_code,"
      + "br.card_code,br.borrow_id  ORDER BY borrow_id DESC";
    rs = stat.executeQuery(sql);
    while (rs.next()) {
     System.out.println("ͼ����:"+rs.getObject("br.books_code") + "\tͼ������:"
       + rs.getObject("books_id") + "\tͼ������:"
       + rs.getObject("book_name") + "\tͼ�����ߣ�"
       + rs.getObject("book_author") + "\tͼ������磺"
       + rs.getObject("publish_company") +"\tͼ��������ڣ�"
       + rs.getObject("publish_date") + "\tͼ��۸�:"
       + rs.getObject("price") + "\t����֤�ţ�"
       + rs.getObject("br.card_code") + "\t������������"
       + rs.getObject("reader_name") + "\t�������Ա�"
       + rs.getObject("reader_sex") + "\t�����߲��ţ�"
       + rs.getObject("reader_department") + "\t���������䣺"
       + rs.getObject("age") + "\t�������ڣ�"
       + rs.getObject("borrow_date") + "\t�������ڣ�"
       + rs.getObject("return_date"));
    }
    rs.close();
    break;
   case "8":
    flag = false;
    break;
   default:
    break;
   }
   stat.close();
   conn.close();
  }
 }
}