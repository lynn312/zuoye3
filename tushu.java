package 来啊;
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
   System.out.println("\n图书管理系统！");
   System.out.println("1.图书添加");
   System.out.println("2.读者办卡");
   System.out.println("3.修改图书");
   System.out.println("4.删除图书");
   System.out.println("5.书籍查询（全部书籍信息列表）");
   System.out.println("6.读者信息一览（全部借书卡信息列表）");
   System.out.println("7.统计某一时间段的借书详细状况 ");
   System.out.println("8.退出");
   System.out.println("请输入你要进行的操作标号：\n");
   Class.forName("com.mysql.jdbc.Driver");
   Connection conn = DriverManager.getConnection(
     "jdbc:mysql://localhost:3306/library", "root", "root");
   Statement stat = conn.createStatement();
   switch (scan.next()) {
   case "1":
    System.out.println("请输入:书的编号，书的索引id，输的名称"
      + "，书的作者，书的出版社，书的出版日期，书的价格");
    String sql_book_id = "SELECT books_code FROM books_info";
    String code = scan.next();
    ResultSet rs = stat.executeQuery(sql_book_id);
    while (rs.next()) {
     if (code.equals(rs.getString("books_code"))) {
      System.out.println("对不起，您的输入有误，因为数据库中已经存在该书的编号");
      System.out.println("请重新进行输入！");
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
     System.out.println("添加成功");
    } else {
     System.out.println("添加失败");
    }
    break;
   case "2":
    System.out.println("请输入:卡号，读者姓名，读者性别，读者所在部门，读者年龄");
    String sql_reader_code = "SELECT card_code FROM reader_info;";
    code = scan.next();
    rs = stat.executeQuery(sql_reader_code);
    while (rs.next()) {
     if (code.equals(rs.getString("books_code"))) {
      System.out.println("对不起，您的输入有误，因为数据库中已经存在该人的编号");
      System.out.println("请重新进行输入！");
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
     System.out.println("添加成功");
    } else {
     System.out.println("添加失败");
    }
    rs.close();
    break;
   case "3":
    System.out.println("请输入你要进行修改的图书编号:");
    sql_book_id = "SELECT books_code FROM books_info";
    sql = "";
    code = scan.next();
    rs = stat.executeQuery(sql_book_id);
    while (rs.next()) {
     if (code.equals(rs.getString("books_code"))) {
      System.out.println("请输入:书的编号，书的索引id，输的名称"
        + "，书的作者，书的出版社，书的出版日期，书的价格");
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
       System.out.println("修改成功");
      } else {
       System.out.println("修改失败");
      }
     }
    }
    rs.close();
    break;
   case "4":
    System.out.println("请输入你要进行删除的图书编号:");
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
       System.out.println("删除成功");
      } else {
       System.out.println("删除失败");
      }
     }
    }
    rs.close();
    break;
   case "5":
    sql = "select * from books_info";
    rs = stat.executeQuery(sql);
    while (rs.next()) {
     System.out.println("图书编号："+rs.getObject("books_code") + "\t图书id"
       + rs.getObject("books_id") + "\t图书名称："
       + rs.getObject("book_name") + "\t图书作者："
       + rs.getObject("book_author") + "\t图书出版社："
       + rs.getObject("publish_company") + "\t图书出版日期："
       + rs.getObject("publish_date") + "\t图书价格:"
       + rs.getObject("price"));
    }
    break;
   case "6":
    sql = "select * from reader_info";
    rs = stat.executeQuery(sql);
    while (rs.next()) {
     System.out.println("借书证号："+rs.getObject("card_code") + "\t借书者姓名："
       + rs.getObject("reader_name") + "\t借书者性别："
       + rs.getObject("reader_sex") + "\t借书者部门："
       + rs.getObject("reader_department") + "\t借书者年龄："
       + rs.getObject("age"));
    }
    rs.close();
    break;
   case "7":
    System.out.println("请输入你要查询的日期区间:(格式例如:2012-12-12)");
    System.out.println("请输入第一个日期！");
    date = scan.next();
    System.out.println("请输入第二个日期!");
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
     System.out.println("图书编号:"+rs.getObject("br.books_code") + "\t图书索引:"
       + rs.getObject("books_id") + "\t图书名称:"
       + rs.getObject("book_name") + "\t图书作者："
       + rs.getObject("book_author") + "\t图书出版社："
       + rs.getObject("publish_company") +"\t图书出版日期："
       + rs.getObject("publish_date") + "\t图书价格:"
       + rs.getObject("price") + "\t借书证号："
       + rs.getObject("br.card_code") + "\t借书者姓名："
       + rs.getObject("reader_name") + "\t借书者性别："
       + rs.getObject("reader_sex") + "\t借书者部门："
       + rs.getObject("reader_department") + "\t借书者年龄："
       + rs.getObject("age") + "\t借书日期："
       + rs.getObject("borrow_date") + "\t还书日期："
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