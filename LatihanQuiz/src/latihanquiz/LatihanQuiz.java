/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package latihanquiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private final String FIXED_USERNAME = "123230093";
    private final String FIXED_PASSWORD = "ifkelasg";
    
    public LoginFrame(){
        setTitle("Halaman Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));
        
        add(new JLabel("Username: "));
        usernameField = new JTextField();
        add(usernameField);
        
        add(new JLabel("Password: "));
        passwordField = new JPasswordField();
        add(passwordField);
        
        JButton loginButton = new JButton("Log-in");
        add(loginButton);
        
        loginButton.addActionListener( e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            if (username.equals(FIXED_USERNAME) && password.equals(FIXED_PASSWORD)){
                JOptionPane.showMessageDialog(this, "Login Berhasil");
                new InputFrame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Username atau Password Salah", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        setVisible(true);
    }
}

class InputFrame extends JFrame{
    private JTextField nameField, nimField, tugasField, quizField;
    private JRadioButton teoriButton, praktikumButton;
    private JComboBox<String> mataKuliahBox;
    
    public InputFrame(){
        setTitle("Halaman Input NIlai");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));
        
        add(new JLabel("Nama Mahasiswa : "));
        nameField = new JTextField();
        add(nameField);
        
        add(new JLabel("NIM Mahasiswa : "));
        nimField = new JTextField();
        add(nimField);
        
        add(new JLabel("Nilai Tugas : "));
        tugasField = new JTextField();
        add(tugasField);
        
        add(new JLabel("Nilai Quiz"));
        quizField = new JTextField();
        add(quizField);
       
        add(new JLabel("Tipe Kelas:"));
        JPanel classPanel = new JPanel();
        teoriButton = new JRadioButton("Kelas Teori", true);
        praktikumButton = new JRadioButton("Kelas Praktikum");
        ButtonGroup group = new ButtonGroup();
        group.add(teoriButton);
        group.add(praktikumButton);
        classPanel.add(teoriButton);
        classPanel.add(praktikumButton);
        add(classPanel);
        
        add(new JLabel("Mata Kuliah : "));
        String[] mataKuliah = {"PBO", "SCPK", "Algo Lanjut"};
        mataKuliahBox = new JComboBox<>(mataKuliah);
        add(mataKuliahBox);
        
        JButton submitButton = new JButton("Submit");
        JButton logoutButton = new JButton("Log-out");
        add(submitButton);
        add(logoutButton);
        
        submitButton.addActionListener(e -> processInput());
        logoutButton.addActionListener(e -> confirmLogout());
        
        setVisible(true);
    }
    
    private void processInput(){
        try {
            String nama = nameField.getText();
            String nim = nimField.getText();
            double nilaiTugas = Double.parseDouble(tugasField.getText());
            double nilaiQuiz = Double.parseDouble(quizField.getText());
            String tipeKelas = teoriButton.isSelected() ? "Kelas Teori" : "Kelas Praktikum";
            String mataKuliah = (String) mataKuliahBox.getSelectedItem();
            
            double totalNilai = teoriButton.isSelected()
                    ? (0.3 * nilaiTugas) + (0.7 * nilaiQuiz)
                    : (0.7 * nilaiTugas) + (0.3 * nilaiQuiz);
            
            String hasil = totalNilai > 85 ? "Pass" : "Not Pass";
            
            JOptionPane.showMessageDialog(this, 
                    "Nama : " + nama + "\nNIM : " + nim + 
                    "\nTipe Kelas : " + tipeKelas + "\nMata Kuliah : " + mataKuliah +
                    "\nHasil : " + hasil + "\nTotal  Nilai : " +totalNilai );
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Nilai harus Berupa Angka", "Error", JOptionPane.ERROR_MESSAGE);
            
        }
    }
    private void confirmLogout() {
        int response = JOptionPane.showConfirmDialog(this, "Mau Logout Yah","Konfirmasi Logout", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION){
            new LoginFrame();
            dispose();
        }
    }
    
}

public class LatihanQuiz {
    
    public static void main(String[] args) {
        new LoginFrame();
    }
    
}

