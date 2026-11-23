import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Connection con = DBConnection.getConnection();

        if (con == null) {
            return;
        }

        try {
            System.out.println("\nREAL ESTATE BOOKING SYSTEM");
            System.out.println("1. View Properties");
            System.out.println("2. Book Property");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {

                Statement st = con.createStatement();

                ResultSet rs = st.executeQuery(
                    "SELECT * FROM properties"
                );

                while (rs.next()) {
                    System.out.println("\nProperty ID: " +
                        rs.getInt("property_id"));

                    System.out.println("Title: " +
                        rs.getString("title"));

                    System.out.println("Location: " +
                        rs.getString("location"));

                    System.out.println("Price: " +
                        rs.getDouble("price"));

                    System.out.println("Status: " +
                        rs.getString("status"));
                }

            } else if (choice == 2) {

                System.out.print("Property ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Customer Name: ");
                String name = sc.nextLine();

                System.out.print("Phone: ");
                String phone = sc.nextLine();

                System.out.print("Email: ");
                String email = sc.nextLine();

                System.out.print("Visit Date (YYYY-MM-DD): ");
                String date = sc.nextLine();

                con.setAutoCommit(false);

                PreparedStatement check = con.prepareStatement(
                    "UPDATE properties SET status='Booked' " +
                    "WHERE property_id=? AND status='Available'"
                );

                check.setInt(1, id);

                int updated = check.executeUpdate();

                if (updated == 0) {
                    con.rollback();
                    System.out.println("Property unavailable!");
                } else {

                    PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO bookings " +
                        "(property_id, customer_name, phone, email, visit_date, booking_status) " +
                        "VALUES (?, ?, ?, ?, ?, 'Pending')"
                    );

                    ps.setInt(1, id);
                    ps.setString(2, name);
                    ps.setString(3, phone);
                    ps.setString(4, email);
                    ps.setDate(5, Date.valueOf(date));

                    ps.executeUpdate();

                    con.commit();

                    System.out.println("Booking Saved Successfully!");
                }

            } else {
                System.out.println("Invalid Choice!");
            }

            con.close();
            sc.close();

        } catch (Exception e) {
            try {
                con.rollback();
            } catch (Exception ignored) {
            }
            e.printStackTrace();
        }
    }
}