package shreyas10;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
class Patient {
    String name, age, disease;
    Patient(String name, String age, String disease) {
        this.name = name;
        this.age = age;
        this.disease = disease;
    }
    @Override
    public String toString() { return "Name: " + name + ", Age: " + age + ", Disease: " + disease; }
}

class Doctor {
    String name, specialty, patientName;
    Doctor(String name, String specialty, String patientName) {
        this.name = name;
        this.specialty = specialty;
        this.patientName = patientName;
    }
    @Override
    public String toString() { return "Name: " + name + ", Specialty: " + specialty + ", Assigned Patient: " + patientName; }
}

class Appointment {
    String patientName, doctorName, date;
    double billAmount;
    Appointment(String patientName, String doctorName, String date, double billAmount) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.date = date;
        this.billAmount = billAmount;
    }
    @Override
    public String toString() { return "Patient: " + patientName + ", Doctor: " + doctorName + ", Date: " + date + ", Bill: $" + billAmount; }
}

public class HospitalManagementSystem {
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static ArrayList<Appointment> appointments = new ArrayList<>();

    public static void main(String[] args) {
        loginScreen();
    }

    private static void loginScreen() {
        JFrame frame = new JFrame("Hospital Management System");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 80, 80, 25);
        JTextField userText = new JTextField();
        userText.setBounds(140, 80, 200, 25);
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 120, 80, 25);
        JPasswordField passText = new JPasswordField();
        passText.setBounds(140, 120, 200, 25);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(160, 170, 100, 30);
        loginButton.addActionListener(e -> {
            if (userText.getText().equals("user") && new String(passText.getPassword()).equals("admin123")) {
                frame.dispose();
                mainDashboard();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid login credentials!");
            }
        });

        panel.add(userLabel); panel.add(userText);
        panel.add(passLabel); panel.add(passText);
        panel.add(loginButton);
        frame.add(panel);
        frame.setVisible(true);
    }

    private static void mainDashboard() {
        JFrame dashboardFrame = new JFrame("Hospital Dashboard");
        dashboardFrame.setSize(500, 400);
        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboardFrame.setLayout(null);

        JButton patientButton = new JButton("Manage Patients");
        patientButton.setBounds(150, 80, 200, 40);
        patientButton.addActionListener(e -> managePatients());
        JButton doctorButton = new JButton("Manage Doctors");
        doctorButton.setBounds(150, 140, 200, 40);
        doctorButton.addActionListener(e -> manageDoctors());
        JButton appointmentButton = new JButton("Manage Appointments");
        appointmentButton.setBounds(150, 200, 200, 40);
        appointmentButton.addActionListener(e -> manageAppointments());

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(180, 270, 140, 40);
        logoutButton.addActionListener(e -> {
            dashboardFrame.dispose();
            loginScreen();
        });

        dashboardFrame.add(patientButton);
        dashboardFrame.add(doctorButton);
        dashboardFrame.add(appointmentButton);
        dashboardFrame.add(logoutButton);
        dashboardFrame.setVisible(true);
    }

    private static void managePatients() {
        String[] options = {"Add Patient", "View Patients", "Delete Patient"};
        int choice = JOptionPane.showOptionDialog(null, "Choose an option", "Manage Patients", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (choice == 0) {
            patients.add(new Patient(JOptionPane.showInputDialog("Enter Name"), JOptionPane.showInputDialog("Enter Age"), JOptionPane.showInputDialog("Enter Disease")));
            JOptionPane.showMessageDialog(null, "Patient Added Successfully!");
        } else if (choice == 1) {
            JOptionPane.showMessageDialog(null, patients);
        } else if (choice == 2) {
            String name = JOptionPane.showInputDialog("Enter Name to Delete:");
            patients.removeIf(p -> p.name.equalsIgnoreCase(name));
        }
    }

    private static void manageDoctors() {
        String[] options = {"Add Doctor", "View Doctors", "Delete Doctor"};
        int choice = JOptionPane.showOptionDialog(null, "Choose an option", "Manage Doctors", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (choice == 0) {
            doctors.add(new Doctor(JOptionPane.showInputDialog("Enter Name"), JOptionPane.showInputDialog("Enter Specialty"), JOptionPane.showInputDialog("Enter Assigned Patient")));
        } else if (choice == 1) {
            JOptionPane.showMessageDialog(null, doctors);
        } else if (choice == 2) {
            String name = JOptionPane.showInputDialog("Enter Name to Delete:");
            doctors.removeIf(d -> d.name.equalsIgnoreCase(name));
        }
    }

    private static void manageAppointments() {
        String[] options = {"Add Appointment", "View Appointments", "Delete Appointment"};
        int choice = JOptionPane.showOptionDialog(null, "Choose an option", "Manage Appointments", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (choice == 0) {
            appointments.add(new Appointment(JOptionPane.showInputDialog("Enter Patient Name"), JOptionPane.showInputDialog("Enter Doctor Name"), JOptionPane.showInputDialog("Enter Date"), Double.parseDouble(JOptionPane.showInputDialog("Enter Bill"))));
        } else if (choice == 1) {
            JOptionPane.showMessageDialog(null, appointments);
        } else if (choice == 2) {
            String name = JOptionPane.showInputDialog("Enter Patient Name to Delete Appointment:");
            appointments.removeIf(a -> a.patientName.equalsIgnoreCase(name));
        }
    }
}
