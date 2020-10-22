import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class App extends JFrame {


    /**
     * <p> dodanie limitu wypisanej wartości JTextFieldLimit - ustawione na 11 znakow
     * <p> eliminacja znakow niepoządanych np. "-"; "*"; "/" addKeyListener
     */
    private static final long serialVersionUID = 1L;
    private final JLabel lblVariable;
    private JLabel lblScore;
    private JTextField textFieldVariable;
    private JTextField textFieldScore;
    private JComboBox<?> comboBox;
    private JTable table;
    private Locale locale;
    private ResourceBundle bundle;

    private String filename = "log.txt";

    /**
     * Konstruktor umozliwiajacy tworzenie okienkowego programu
     */
    App() {
        setType(Type.UTILITY);
        setTitle("Basic Units Converter");

        int width = Toolkit.getDefaultToolkit().getScreenSize().width; // pobieranie zestawu narzedzi i przypisanie do zmniennej szer
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;

        this.setSize(460, 537);

        int widthFrame = this.getSize().width;
        int heightFrame = this.getSize().height;

        Locale.setDefault(new Locale("pl"));
        locale = Locale.getDefault();
        bundle = ResourceBundle.getBundle("text", locale);

        setResizable(false);
        setBounds(((width - widthFrame) / 2), (height - heightFrame) / 2, 460, 530);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);


        JLabel lblChooseOptions = new JLabel(bundle.getString("choose_option"));
        lblChooseOptions.setBounds(28, 11, 375, 34);
        getContentPane().add(lblChooseOptions);

        textFieldVariable = new JTextField();
        textFieldVariable.setDocument(new JTextFieldLimit(11));  // wywołanie klasy odpowiedzilanej za limit znaków w polu
        textFieldVariable.setBounds(28, 184, 152, 30);

        textFieldVariable.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = textFieldVariable.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE || ke.getKeyChar() == '.') {
                    textFieldVariable.setEditable(true);
                } else {
                    textFieldVariable.setEditable(false);
                }
            }
        });
        textFieldVariable.setText("1");
        getContentPane().add(textFieldVariable);
        textFieldVariable.setColumns(10);
        textFieldVariable.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    Menu menu = new Menu();
                    textFieldScore.setText(menu.menu(comboBox.getSelectedIndex(), Double.parseDouble(textFieldVariable.getText())));
                } catch (Exception e1) {
                    textFieldScore.setText("Bad record");
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(textFieldVariable.getText().length() < 1) {
                    textFieldScore.setText("");
                }
                else {
                    try {
                        Menu menu = new Menu();
                        textFieldScore.setText(menu.menu(comboBox.getSelectedIndex(), Double.parseDouble(textFieldVariable.getText())));
                    } catch (Exception e1) {
                        textFieldScore.setText("Bad record");
                    }
                }

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                textFieldScore.setText("");
            }
        });

        textFieldScore = new JTextField();
        textFieldScore.setText("0,22");
        textFieldScore.setEditable(false);
        textFieldScore.setBounds(251, 184, 152, 30);
        getContentPane().add(textFieldScore);

        textFieldScore.setColumns(10);

        JLabel lblNewLabel = new JLabel("<==>");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(190, 184, 51, 30);
        getContentPane().add(lblNewLabel);

        lblVariable = new JLabel("PLN");
        lblVariable.setHorizontalAlignment(SwingConstants.LEFT);
        lblVariable.setBounds(28, 159, 152, 14);
        getContentPane().add(lblVariable);

        lblScore = new JLabel("EURO");
        lblScore.setHorizontalAlignment(SwingConstants.RIGHT);
        lblScore.setBounds(251, 159, 152, 14);
        getContentPane().add(lblScore);

        JLabel lblNewLabelInformation = new JLabel("Compilator - Aleksander Micyk IT sem. 4");
        lblNewLabelInformation.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabelInformation.setBounds(20, 455, 383, 14);
        getContentPane().add(lblNewLabelInformation);


        String[] comboBoxMenu =
                {"PLN --> EURO", "EURO --> PLN",
                        "Square meters --> Square inches", "Square inches > Square meters",
                        "Kilograms --> Ounces", "Ounces --> Kilograms"};

        JComboBox jComboBox = new JComboBox(comboBoxMenu);
        comboBox = jComboBox;
        comboBox.setBounds(28, 84, 383, 47);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (comboBox.getSelectedIndex()) {
                    case 0:
                        lblVariable.setText("PLN");
                        lblScore.setText("EURO");
                        break;
                    case 1:
                        lblVariable.setText("EURO");
                        lblScore.setText("PLN");
                        break;
                    case 2:
                        lblVariable.setText("Square meters");
                        lblScore.setText("Square inches");
                        break;
                    case 3:
                        lblVariable.setText("Square inches");
                        lblScore.setText("Square meters");
                        break;
                    case 4:
                        lblVariable.setText("Kilograms");
                        lblScore.setText("Ounces");
                        break;
                    case 5:
                        lblVariable.setText("Ounces");
                        lblScore.setText("Kilograms");
                        break;

                    default:
                        ;

                }


            }
        });
        getContentPane().add(comboBox);

        JLabel lblNewLabel_1 = new JLabel(bundle.getString("example_units"));
        lblNewLabel_1.setEnabled(false);
        lblNewLabel_1.setBounds(28, 44, 375, 14);
        getContentPane().add(lblNewLabel_1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(10, 265, 424, 150);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "from", "to", "before", "after"
                }
        ));
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.setEnabled(false);
        scrollPane.setViewportView(table);


        /**
         * @see file wywołanie klasy odpowiedzialnej za sprawdzenie istnienia pliku
         */
        File file = new File(filename);
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
            }


        readLogFromFile(table);

        JButton btnNewButtonSave = new JButton("Save");
        btnNewButtonSave.setBounds(314, 225, 89, 23);
        btnNewButtonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                changeList(comboBox, textFieldScore);

            }
        });
        getContentPane().add(btnNewButtonSave);

        JComboBox comboBoxLanguage = new JComboBox();
        comboBoxLanguage.setBounds(329, 426, 105, 23);
        comboBoxLanguage.addItem(new Locale("pl"));
        comboBoxLanguage.addItem(new Locale("en"));
        comboBoxLanguage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                locale = (Locale) comboBoxLanguage.getSelectedItem();
                bundle = ResourceBundle.getBundle("text", locale);
                System.out.println(bundle.getString("choose_option"));
                lblChooseOptions.setText(bundle.getString("choose_option"));
                lblNewLabel_1.setText(bundle.getString("example_units"));

            }
        });
        getContentPane().add(comboBoxLanguage);
    }

    void changeList(JComboBox comboBox, JTextField textField) {
        Menu menu = new Menu();
        try {
            textField.setText(menu.menu(comboBox.getSelectedIndex(), Double.parseDouble(textFieldVariable.getText())));
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.insertRow(0, new Object[]{lblVariable.getText(), lblScore.getText(), textFieldVariable.getText(), textFieldScore.getText()});
            writeLogToFile(table);
        } catch (Exception e) {
            textField.setText("Bad record");
        }

        try {

        } catch (Exception e) {
            textField.setText("");
        }


    }


    void showApp() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * @param table operuje wartościami w tabeli
     * @see metoda readLogFromFile wczytuje historię z pliku
     */
    void readLogFromFile(JTable table) {

        try {
            BufferedReader buffer = new BufferedReader(new FileReader(filename));
            String line;
            int point = 0;
            String[] values = new String[4];
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            while ((line = buffer.readLine()) != null) {
                values[point] = line;
                point++;
                if (point == 4) {
                    point = 0;
                    model.addRow(new Object[]{values[0], values[1], values[2], values[3]});
                }
            }
            buffer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * @param table operuje wartościami w tabeli
     * @see metoda writeLogToFile zapisuje wartości do pliku
     */

    void writeLogToFile(JTable table) {


        try {
            File file = new File(filename);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < 4; j++) {
                    bufferedWriter.write(table.getModel().getValueAt(i, j).toString());
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * @see klasa wprowadzająca limit znaków
 */
class JTextFieldLimit extends PlainDocument {
    private int limit;

    JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    JTextFieldLimit(int limit, boolean upper) {
        super();
        this.limit = limit;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null)
            return;
        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}


