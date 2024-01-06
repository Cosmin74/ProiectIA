import argumente.Cariera;
import argumente.Domeniu;
import argumente.Elev;
import argumente.Materie;
import predicate.CarieraPred;
import predicate.InteresPred;
import predicate.PredPlace;
import reguli.Regula1;
import reguli.Regula2;
import reguli.Regula3;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends JFrame {

    private JTextField numeElevField;
    private JComboBox<String> materieComboBox;
    private JComboBox<String> domeniuComboBox;
    private JButton button;
    private JTextArea  rezultatTextField;

    public Main() {
        super("Chestionar");

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Chestionar pentru cariere", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        container.add(titleLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        numeElevField = new JTextField(15);
        materieComboBox = new JComboBox<>(new String[]{"Limba engleză", "Limba franceză", "Matematică", "Biologie", "Chimie", "Arte plastice", "Economie", "Fizică", "Geografie", "Informatică", "Istorie", });
        domeniuComboBox = new JComboBox<>();
        button = new JButton("Verifică cariera");
        rezultatTextField = new JTextArea(5, 35);
        rezultatTextField.setEditable(false);
        

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nume Elev:"), gbc);
        gbc.gridx = 1;
        panel.add(numeElevField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Materie:"), gbc);
        gbc.gridx = 1;
        panel.add(materieComboBox, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Domeniu:"), gbc);
        gbc.gridx = 1;
        panel.add(domeniuComboBox, gbc);
        gbc.gridy++;
        gbc.gridx = 1;
        panel.add(button, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Rezultat:"), gbc);
        gbc.gridx = 1;
        panel.add(new JScrollPane(rezultatTextField), gbc);


        materieComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDomeniuComboBox();
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeElev = numeElevField.getText();
                String materieInput = (String) materieComboBox.getSelectedItem();
                String domeniuInput = (String) domeniuComboBox.getSelectedItem();

                if (numeElev.isEmpty() || materieInput == null || domeniuInput == null) {
                    JOptionPane.showMessageDialog(null, "Te rog să completezi toate câmpurile.", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Elev elev = new Elev(numeElev);
                Materie materie = new Materie(materieInput);
                Domeniu domeniu = new Domeniu(domeniuInput);

                Regula1 regula1 = new Regula1();
                PredPlace rezultatRegula1 = regula1.verificaRegula1(elev, materie);

                if (rezultatRegula1 != null) {
                    Regula2 regula2 = new Regula2();
                    InteresPred rezultatRegula2 = regula2.verificaRegula2(elev, materie, domeniu);

                    if (rezultatRegula2 != null) {
                        Regula3 regula3 = new Regula3();
                        Boolean rezultatRegula3 = regula3.verificaRegula3(elev, domeniu);

                        if (rezultatRegula3) {
                            String regex = materieInput + " - " + domeniuInput + " - (.+)";

                            byte[] continutBytes = new byte[0];
                            try {
                                continutBytes = Files.readAllBytes(Paths.get("C:\\Users\\User\\Desktop\\ProiectIA\\src\\input\\seturi-date.txt"));
                                String continutString = new String(continutBytes);

                                Pattern pattern = Pattern.compile(regex);
                                Matcher matcher = pattern.matcher(continutString);

                                if (matcher.find()) {
                                    String[] cuvinte = matcher.group().split("\\s*-\\s*");
                                    String carieraInput = cuvinte[2];

                                    Cariera cariera = new Cariera(carieraInput);
                                    CarieraPred carieraPred = new CarieraPred();

                                    rezultatTextField.setText("Elevului " + numeElev + " i se potrivește o carieră de " + cariera.getCariera().toLowerCase() + ".");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Te rog alege o materie si un domeniu ", "Eroare", JOptionPane.ERROR_MESSAGE);
                                }

                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Domeniul ales nu sunt potrivite, alege din nou", "Eroare", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Materia aleasa nu se potriveste, alege din nou", "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        updateDomeniuComboBox();

        add(panel);
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);


        setSize(800, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateDomeniuComboBox() {
        String materieInput = (String) materieComboBox.getSelectedItem();
        if (materieInput != null) {
            List<String> domenii = getDomeniiForMaterie(materieInput);
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(domenii.toArray(new String[0]));
            domeniuComboBox.setModel(model);
        }
    }

    private List<String> getDomeniiForMaterie(String materie) {
        List<String> domenii = new ArrayList<>();
        switch (materie) {
            case "Matematică":
                domenii.add("Cercetare");
                domenii.add("Statistică");
                domenii.add("Analiză financiară");
                domenii.add("Finanțe");
                domenii.add("Educație");
                break;
            case "Arte plastice":
                domenii.add("Design");
                domenii.add("Film");
                domenii.add("Design de produs");
                domenii.add("Publicitate");
                domenii.add("Restaurare culturală");
                break;
            case "Biologie":
                domenii.add("Medicină");
                domenii.add("Biotehnologie");
                domenii.add("Genomică");
                domenii.add("Ecologie");
                break;
            case "Chimie":
                domenii.add("Farmacie");
                domenii.add("Industrie farmaceutică");
                domenii.add("Industrie alimentară");
                break;
            case "Economie":
                domenii.add("Finanțe");
                domenii.add("Consultanță");
                domenii.add("Consultanță de afaceri");
                domenii.add("Managementul resurselor umane");
                break;
            case "Fizică":
                domenii.add("Inginerie");
                domenii.add("Energie");
                domenii.add("Astronomie");
                domenii.add("Inginerie aerospațială");
                break;
            case "Geografie":
                domenii.add("Urbanism");
                domenii.add("Conservare a mediului");
                domenii.add("Planificare urbană");
                domenii.add("Geoinformatică");
                break;
            case "Informatică":
                domenii.add("Tehnologie");
                domenii.add("Securitate cibernetică");
                domenii.add("Inteligență artificială");
                domenii.add("Dezvoltare web");
                domenii.add("Analiză de date");
                break;
            case "Istorie":
                domenii.add("Muzeologie");
                domenii.add("Arheologie");
                domenii.add("Restaurare culturală");
                break;
            case "Limba engleză":
            case "Limba franceză":
                domenii.add("Diplomație");
                break;
        }
        return domenii;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}