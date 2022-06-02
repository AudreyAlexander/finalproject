import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class guiCreator implements ActionListener{
    private JFrame frame;
    private JPanel top;
    private JPanel middle;
    private JButton planets;
    private JButton starships;
    private JButton vehicles;
    private JButton people;
    private JButton films;
    private JButton species;
    private JTextArea info;
    private JTextField field;
    private Networking client;
    private String prev;

    public guiCreator(){
        frame = new JFrame("Star Wars Information");
        top = new JPanel();
        middle = new JPanel();
        planets = new JButton("planets");
        starships = new JButton("starships");
        vehicles = new JButton("vehicles");
        people = new JButton("people");
        films = new JButton("films");
        species = new JButton("species");
        client = new Networking();
        field = new JTextField(10);
        info = new JTextArea("\n\n\n\n\n\n\n");
        prev = "";
        setUpGui();
    }

    private void setUpGui(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel entryText = new JLabel("Choose a Category: ");
        entryText.setFont(new Font("Helvetica", Font.BOLD, 20));
        entryText.setForeground(Color.black);

        top.add(entryText);

        middle.add(planets);
        middle.add(starships);
        middle.add(vehicles);
        middle.add(people);
        middle.add(films);
        middle.add(species);

        frame.add(top, BorderLayout.NORTH);
        frame.add(middle, BorderLayout.CENTER);

        planets.addActionListener(this);
        starships.addActionListener(this);
        vehicles.addActionListener(this);
        people.addActionListener(this);
        films.addActionListener(this);
        species.addActionListener(this);

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) (e.getSource());
        String text = button.getText();

        if (text.equals("Search")){
            info.setText(getData(prev));
        }
        else{
            prev = text;
            popUpFrame();
        }
    }

    public void popUpFrame(){
        JFrame newFrame = new JFrame();
        JPanel top = new JPanel();
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame.setBackground(Color.BLACK);
        field.setText("");
        info.setText("\n\n\n\n\n\n\n");
        JLabel entryText = new JLabel("Enter a name: ");
        JButton search = new JButton("Search");
        top.add(entryText);
        top.add(field);
        top.add(search);
        search.addActionListener(this);

        JPanel bottom = new JPanel();
        bottom.setSize(300,300);
        bottom.add(info);

        newFrame.add(top, BorderLayout.NORTH);
        newFrame.add(bottom, BorderLayout.CENTER);
        newFrame.setSize(600, 600);
        newFrame.pack();
        newFrame.setVisible(true);
    }

    private String getData(String category){
        String enteredName = field.getText();
        if (enteredName.contains(" ")){
            int num = enteredName.indexOf(" ");
            enteredName = enteredName.substring(0, num);
        }
        enteredName = enteredName.toLowerCase();
        String response = client.makeAPICall(enteredName, category);
        String str = "";
        if (category.equals("planets")){str = client.parsePlanetData(response);}
        else if (category.equals("starships")){str = client.parseSVData(response);}
        else if (category.equals("vehicles")){str = client.parseSVData(response);}
        else if (category.equals("people")){str = client.parsePeopleData(response);}
        else if (category.equals("films")){str = client.parseFilmData(response);}
        else if (category.equals("species")){str = client.parseSpeciesData(response);}

        return str;
    }
}
