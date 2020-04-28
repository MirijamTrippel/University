import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/**
 * Write a description of class NotebookGUI here.
 *
 * @author Barne Kleinen
 * @version Sommersemester 2018
 */
public class NotebookGUI
{
    public static final int TEXTFIELD_WIDTH = 20;
    Notebook notebook;
    JTextArea content;

    public NotebookGUI(){
        notebook = new Notebook();
        makeFrame();
    }

    private void makeFrame(){
        JFrame frame = new JFrame("Notebook");
        JPanel mainPanel = (JPanel)frame.getContentPane();

        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(createControlPanel(),BorderLayout.NORTH);
        mainPanel.add(createContentPanel(),BorderLayout.CENTER);

        // building is done - arrange the components      

        frame.pack();

        // place the frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);

    }

    private JPanel createControlPanel(){
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridBagLayout());
        // textarea for new note
        TextArea newNote  = new TextArea(4,TEXTFIELD_WIDTH);
        placeComp(newNote, controlPanel, 0, 0, 1, 4);

        // add note button
        JButton addButton = new JButton("add");
        placeComp(addButton, controlPanel, 1, 0, 1, 1);

        // add search field
        JTextField searchField = new JTextField(TEXTFIELD_WIDTH);
        placeComp(searchField, controlPanel, 2, 0, 1, 1);

        // add search button
        JButton searchButton = new JButton("search");
        placeComp(searchButton,controlPanel,3,0,1,1);

        // add remove button
        JTextField removeField = new JTextField(TEXTFIELD_WIDTH);
        placeComp(removeField, controlPanel, 2, 1, 1, 1);

        JButton removeButton = new JButton("remove");
        placeComp(removeButton,controlPanel,3,1,1,1);
        // add remove button

        JTextField removeFieldI = new JTextField(TEXTFIELD_WIDTH);
        placeComp(removeFieldI, controlPanel, 2, 2, 1, 1);

        JButton removeButtonI = new JButton("remove with index");
        placeComp(removeButtonI,controlPanel,3,2,1,1);

        // add showNumbers button
        JButton showNumbersButton = new JButton("show numbers");
        placeComp(showNumbersButton,controlPanel,3,3,1,1);

        // add create testdata button
        JButton testDataButton = new JButton("create test data");
        placeComp(testDataButton,controlPanel,2,3,1,1);

        
        
        
        
        addButton.addActionListener(e -> 
            {
                notebook.storeNote(newNote.getText());
                newNote.setText("");
                content.setText(notebook.getAll());
            });
        searchButton.addActionListener(
            e -> content.setText(
                    notebook.getNotesWith(searchField.getText())));

        showNumbersButton.addActionListener(
            e -> content.setText(notebook.getAllNumbered())); 

        removeButton.addActionListener(
            e -> {
                notebook.removeNotes(removeField.getText());
                content.setText(notebook.getAll());
            }
        );

        removeButtonI.addActionListener(
            e -> {
                notebook.removeNotesWithIndex(removeFieldI.getText());
                content.setText(notebook.getAll());
            }
        );

        testDataButton.addActionListener(
            e -> {
                notebook.storeNote("eins");
                notebook.storeNote("zwei");
                notebook.storeNote("drei");
                notebook.storeNote("vier");
                notebook.storeNote("fünf");
                notebook.storeNote("sechs");
                notebook.storeNote("sieben");
                notebook.storeNote("acht");
                notebook.storeNote("neun");
                notebook.storeNote("zehn");
                content.setText(notebook.getAll());
            });


        return controlPanel;
    }

    private JPanel createContentPanel(){
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        content = new JTextArea(16, 58);
        content.setEditable(false); 
        JScrollPane scroll = new JScrollPane(content);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        p.add(scroll);
        return p;
    }

    private static void placeComp(Component comp, JPanel panel, int x, int y, int w, int h) {
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = x;
        cons.gridy = y;
        cons.gridwidth = w;
        cons.gridheight = h;
        panel.add(comp, cons);
    }
}
