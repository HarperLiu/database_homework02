package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * @author: harperliu
 * @description: 产生项目的测试数据
 * @date: create in 10:05 2018/10/27
 */
public class testDataGenerator {
    private static final String[] Surname = {
            "James", "John", "Robert", "Michael", "William", "David", "Richard", "Charles", "Joseph",
            "Thomas", "Christopher", "Daniel", "Paul", "Mark", "Donald", "George", "Kenneth", "Steven",
            "Edward", "Brian", "Ronald", "Anthony", "Kevin", "Jason", "Matthew", "Gary", "Timothy",
            "Jose", "Larry", "Jeffrey", "Frank", "Scott", "Eric", "Stephen", "Andrew", "Raymond",
            "Gregory", "Joshua", "Jerry", "Dennis", "Walter", "Patrick", "Peter", "Harold", "Douglas",
            "Henry", "Carl", "Arthur", "Ryan", "Roger", "Joe", "Juan", "Jack", "Albert", "Jonathan",
            "Justin", "Terry", "Gerald", "Keith", "Samuel", "Willie", "Ralph", "Lawrence", "Nicholas",
            "Roy", "Ben"};
    static final double phoneFee = 0.5;
    static final double messageFee = 0.1;
    static final int localDataFee = 2;
    static final int domesticDataFee = 5;
    static final String[] dataType = {"local","domestic"};



    public static void main(String[] args) {
        /*
        //生成10万给用户
        generateUser();
        //为id为奇数的用户生成一个套餐
        generateUserDiscount();
        //为每个用户生成两个历史套餐
        generateUserOrder();
        //为每个用户生成一个月账单（每25000个用户分别订购了1，2，3，4，5套餐
        generateUserBill();
        */
        generateDiscountPackage();
        generatephoneRecord();
        generateDataRecord();
        generateUserPackage();

    }

    public static String getName(){
        int n = Surname.length;
        return Surname[(int)(Math.random()*(n-1))];
    }

    private static void generateDiscountPackage(){
        DecimalFormat df = new DecimalFormat("0.00");//保留两位
        File file = new File("discountPackage.sql");
        StringBuilder str;
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            str = new StringBuilder("lock tables `discount_package` write;\r\n");
            bufferedWriter.write(str.toString());
            str = new StringBuilder("insert into `discount_package` values ");
            //加入话费套餐
            str.append("(1,100,0,0,0,20),");
            str.append("(2,0,200,0,0,10),");
            str.append("(3,0,0,0,2048,20),");
            str.append("(4,0,0,2048,0,30),");
            str.deleteCharAt(str.length() - 1).append(";\r\n");
            bufferedWriter.write(str.toString());
            str = new StringBuilder("unlock tables;\r\n");
            bufferedWriter.write(str.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generatephoneRecord(){
        File file = new File("phoneRecord.sql");
        StringBuilder str;
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
            str = new StringBuilder("lock tables `phone_record` write;\r\n");
            bufferedWriter.write(str.toString());
            str = new StringBuilder("insert into `phone_record` values ");
            for (int i = 0; i < 10000; i++) {
                str.append("(").append(i + 1).append(",'").append(getName())
                        .append("',").append((int) (Math.random() * 50 + 1)).append(",")
                        .append( date.toString().replaceAll("[[\\s-:punct:]]","")).append("),");
            }
            str.deleteCharAt(str.length() - 1).append(";\r\n");
            bufferedWriter.write(str.toString());
            str = new StringBuilder("unlock tables;\r\n");
            bufferedWriter.write(str.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void generateDataRecord(){
        File file = new File("dataRecord.sql");
        StringBuilder str;
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
            str = new StringBuilder("lock tables `data_record` write;\r\n");
            bufferedWriter.write(str.toString());
            str = new StringBuilder("insert into `data_record` values ");
            for (int i = 0; i < 10000; i++) {
                str.append("(").append(i + 1).append(",'").append(getName())
                        .append("',").append((int) (Math.random() * 100 + 1)).append(",")
                        .append( date.toString().replaceAll("[[\\s-:punct:]]","")).append(",").append("'").append(dataType[(int)(Math.random()*10%2)]).append("'").append("),");
            }
            str.deleteCharAt(str.length() - 1).append(";\r\n");
            bufferedWriter.write(str.toString());
            str = new StringBuilder("unlock tables;\r\n");
            bufferedWriter.write(str.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateUserPackage(){
        File file = new File("userPackage.sql");
        StringBuilder str;
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
            str = new StringBuilder("lock tables `user_package` write;\r\n");
            bufferedWriter.write(str.toString());
            str = new StringBuilder("insert into `user_package` values ");
            for (int i = 0; i < 10000; i++) {
                str.append("(").append(i + 1).append(",'").append(getName())
                        .append("',").append( date.toString().replaceAll("[[\\s-:punct:]]","")).append(',').append((int)(Math.random()*4+1)).append(",").append("'").append((int)(Math.random()*10%2)).append("'").append("),");
            }
            str.deleteCharAt(str.length() - 1).append(";\r\n");
            bufferedWriter.write(str.toString());
            str = new StringBuilder("unlock tables;\r\n");
            bufferedWriter.write(str.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
