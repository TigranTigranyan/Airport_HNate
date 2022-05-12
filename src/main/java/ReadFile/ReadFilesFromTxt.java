package ReadFile;

import DAo.IMPL.PassengerDaoImpl;
import HibernateEntities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static DAo.IMPL.PassengerDaoImpl.*;

public class ReadFilesFromTxt {

    public static void readFileCompany(SessionFactory sf) {
         Session s1 = sf.openSession();
        Transaction trans = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("/Users/tigranyantigran/IdeaProjects/Airport_HNate/src/main/java/companies.txt")))) {

            String[] strArr;

            String str = br.readLine();

            trans = s1.beginTransaction();
            while ((str = br.readLine()) != null) {
                strArr = str.split(",");
                Company company = new Company(strArr[0], Date.valueOf(LocalDate.parse(strArr[1], formatter)));

                s1.save(company);


            }
            trans.commit();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }


    public static void readFileTrip(SessionFactory sf) {
         Session s1 = sf.openSession();
        Transaction trans = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime dateTime = LocalDateTime.parse("1909-01-01 04:00:00.000", formatter);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("/Users/tigranyantigran/IdeaProjects/Airport_HNate/src/main/java/trip.txt")))) {
            Company company;
            String[] strArr;
            String str = br.readLine();
            trans = s1.beginTransaction();

            while ((str = br.readLine()) != null) {
                strArr = str.split(",");
                company = new Company();
                company.setId(Long.parseLong(strArr[1]));
                Trip trip = new Trip(Integer.parseInt(strArr[0]), company,
                        strArr[2], strArr[3], strArr[4], Date.valueOf(LocalDate.parse(strArr[5], formatter)),
                        Date.valueOf(LocalDate.parse(strArr[6], formatter)));
                s1.merge(trip);
            }
            trans.commit();

        } catch (IOException e) {
            e.printStackTrace();
            trans.rollback();
        } finally {
            s1.close();
        }

    }


    public static void readFilePassengerAddress(SessionFactory sf) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("/Users/tigranyantigran/IdeaProjects/Airport_HNate/src/main/java/paasenger.txt")));

        Session s1 = sf.openSession()) {
            Transaction trans = null;
            trans = s1.beginTransaction();
            String[] strArr;
            String str=br.readLine() ;
            while ((str = br.readLine()) != null) {
                strArr = str.split(",");
                Address address= PassengerDaoImpl.containsAddress(strArr[2],strArr[3],s1);
                if (address==null){
                    address=new Address(strArr[2],strArr[3]);
                    s1.persist(address);
                }
                Passanger passanger=new Passanger(strArr[0],strArr[1], address);
                s1.save(passanger);
            }
            trans.commit();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readPassinTrip(SessionFactory sf) {

        Transaction trans = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime dateTime = LocalDateTime.parse("1909-01-01 04:00:00.000", formatter);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("/Users/tigranyantigran/IdeaProjects/Airport_HNate/src/main/java/passintrip.txt")));

             Session s1 = sf.openSession()) {
            String[] strArr;
            String str ;
            trans = s1.beginTransaction();
            while ((str = br.readLine()) != null) {
                strArr = str.split(",");
                Passanger passanger=containsId(strArr[1],s1);
                Trip trip=containsIdtrip(strArr[0],s1);

                PassInTrip passInTrip = new PassInTrip(trip,passanger,Date.valueOf(LocalDate.parse(strArr[2], formatter)), strArr[3]);

                s1.persist(passInTrip);

            }
            trans.commit();


        } catch (IOException e) {
            System.out.println("File not found");
        }
    }



}
