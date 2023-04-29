//Usually you will require both swing and awt packages
// even if you are working with just swings.
import javax.swing.*;

import Models.Department;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.*;

public class Test {
    JFrame frame;
    JPanel bootScreen, adminPanel, artPanel, artDataModal, moderatorDataModal;
    int i = 0;
    boolean editFlag = false;
     
    Test() throws SQLException{
        frame = new JFrame();
        ArrayList<Department> departments = DepartmentRepository.getDepartment();
        ArrayList<Employee> employees = EmployeeRepository.getEmployees();
        ArrayList<Artist> artists = ArtistRepository.getArtists();
        ArrayList<Art> art = ArtRepository.getArt();

        //#region utilityButtons
        JButton backButton = new JButton("Back to boot screen");
        backButton.addActionListener( new ActionListener() {
             
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.setContentPane(bootScreen);
                artPanel.revalidate();
                artPanel.repaint();
            }
        });
        //#endregion
        
        //#region Account Selection Page


        bootScreen = new JPanel(new GridLayout(0, 1));
         
        JLabel l1 = new JLabel("Account Selection Panel", JLabel.CENTER);

        JButton museumModeratorButton = new JButton("Museum Administrator - " + employees.get(0).Name);
        JButton potteryModeratorButton = new JButton(departments.get(0).Title + " Department Moderator - " + employees.get(1).Name);
        JButton paintingsModeratorButton = new JButton(departments.get(1).Title + " Department Moderator - " + employees.get(2).Name);
        JButton writingModeratorButton = new JButton(departments.get(2).Title + " Department Moderator - " + employees.get(3).Name);
        JButton sculpturesModeratorButton = new JButton(departments.get(3).Title + " Department Moderator - " + employees.get(4).Name);
        
        potteryModeratorButton.addActionListener( new ActionListener() {
             
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.setContentPane(artPanel);
                artPanel.revalidate();
                artPanel.repaint();
            }
        });

        paintingsModeratorButton.addActionListener( new ActionListener() {
             
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.setContentPane(artPanel);
                artPanel.revalidate();
                artPanel.repaint();
            }
        });

        writingModeratorButton.addActionListener( new ActionListener() {
             
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.setContentPane(artPanel);
                artPanel.revalidate();
                artPanel.repaint();
            }
        });

        sculpturesModeratorButton.addActionListener( new ActionListener() {
             
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.setContentPane(artPanel);
                artPanel.revalidate();
                artPanel.repaint();
            }
        });

        museumModeratorButton.addActionListener( new ActionListener() {
             
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.setContentPane(adminPanel);
                artPanel.revalidate();
                artPanel.repaint();
            }
        });

        bootScreen.add(l1);
        bootScreen.add(potteryModeratorButton);
        bootScreen.add(paintingsModeratorButton);
        bootScreen.add(writingModeratorButton);
        bootScreen.add(sculpturesModeratorButton);
        bootScreen.add(museumModeratorButton);

        //#endregion
         
        //#region artDataModal
        artDataModal = new JPanel(new GridLayout(0, 2));
        


        JLabel artInfo = new JLabel("Art Info", JLabel.CENTER);
        JLabel artistInfo = new JLabel("Artist Info", JLabel.CENTER);

        JLabel departmentLabel = new JLabel("Department", JLabel.CENTER);
        JLabel artistNameLabel = new JLabel("Artist Name", JLabel.CENTER);

        JTextField departmentInput = new JTextField();
        JTextField artistNameInput = new JTextField();

        JLabel artTitleLabel = new JLabel("Art Title", JLabel.CENTER);
        JLabel artistDOBLabel = new JLabel("Artist Year Of Birth", JLabel.CENTER);

        JTextField artTitleInput = new JTextField();
        JTextField artistDOBInput = new JTextField();

        JLabel artDateLabel = new JLabel("Year Created", JLabel.CENTER);
        JLabel artistDODLabel = new JLabel("Artist Year Of Death", JLabel.CENTER);

        JTextField artDateInput = new JTextField();
        JTextField artistDODInput = new JTextField();

        JLabel artFilePathLabel = new JLabel("Path to Photo", JLabel.CENTER);
        JLabel artistInfoLabel = new JLabel("General Artist Information", JLabel.CENTER);

        String[] departmentListArray = {departments.get(0).Title, departments.get(1).Title, departments.get(2).Title, departments.get(3).Title};
        JComboBox<String> departmentList = new JComboBox<String>(departmentListArray);
        JTextField artistInfoInput = new JTextField();

        JButton artModalSubmit = new JButton("Submit Entry");
        JButton artModalEscape = new JButton("Cancel Operation");

        artModalSubmit.addActionListener(new ActionListener() {
             
            @Override
            public void actionPerformed(ActionEvent arg0) {

                if(editFlag){
                    try {
                        ArtistRepository.updateArtist((i+1), artistNameInput.getText(), artistDOBInput.getText(), artistDODInput.getText(), artistInfoInput.getText());
                        ArtRepository.updateArt((i+1), (i+1), "Code\\Code\\Images\\" + departmentList.getSelectedItem().toString() +"\\" + artTitleInput.getText() + ".jpg", artTitleInput.getText(), artDateInput.getText(), departmentList.getSelectedItem().toString());
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else{
                    try {
                        ArtistRepository.createArtist(artistNameInput.getText(), artistDOBInput.getText(), artistDODInput.getText(), artistInfoInput.getText());
                        ArtRepository.createArt((i + 1), "Code\\Code\\Images\\" + departmentList.getSelectedItem().toString() +"\\" + artTitleInput.getText() + ".jpg", artTitleInput.getText(), artDateInput.getText(), departmentList.getSelectedItem().toString());
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } 

                frame.setContentPane(artPanel);
                artPanel.revalidate();
                artPanel.repaint();
            }
        });

        artModalEscape.addActionListener( new ActionListener() {
             
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.setContentPane(artPanel);
                artPanel.revalidate();
                artPanel.repaint();
            }
        });

        artDataModal.add(artInfo);
        artDataModal.add(artistInfo);

        artDataModal.add(departmentLabel);
        artDataModal.add(artistNameLabel);

        artDataModal.add(departmentInput);
        artDataModal.add(artistNameInput);

        artDataModal.add(artTitleLabel);
        artDataModal.add(artistDOBLabel);

        artDataModal.add(artTitleInput);
        artDataModal.add(artistDOBInput);

        artDataModal.add(artDateLabel);
        artDataModal.add(artistDODLabel);

        artDataModal.add(artDateInput);
        artDataModal.add(artistDODInput);

        artDataModal.add(artFilePathLabel);
        artDataModal.add(artistInfoLabel);

        artDataModal.add(departmentList);
        artDataModal.add(artistInfoInput);

        artDataModal.add(artModalSubmit);
        artDataModal.add(artModalEscape);

        //#endregion

        //#region Art Panel Page

        artPanel = new JPanel(new BorderLayout());

        JPanel headerSubPanel = new JPanel();
        JPanel leftSubPanel = new JPanel();
        JPanel bottomSubPanel = new JPanel();
        
        // s[0] = new ImageIcon("Code\\Code\\Images\\Paintings\\TestPhoto.jpg");
        // s[1] = new ImageIcon("Code\\Code\\Images\\Pottery\\TestPhoto.jpg");
        // s[2] = new ImageIcon("Code\\Code\\Images\\Sculptures\\TestPhoto.jpg");
        // s[3] = new ImageIcon("Code\\Code\\Images\\Writing\\TestPhoto.jpg");
        
        
        JLabel artTitle = new JLabel("ArtTitle");
        JLabel artDate = new JLabel("ArtDate");
        JButton createArt = new JButton("Create Art");
       
        headerSubPanel.add(artTitle);
        headerSubPanel.add(artDate);
        headerSubPanel.add(createArt);

        JLabel artPiece = new JLabel("");
        
        JLabel artistName = new JLabel("artistName");
        JLabel artistBirthDeath = new JLabel("Birth - Death");
        
        leftSubPanel.add(artistName);
        leftSubPanel.add(artistBirthDeath);
        
        JLabel artistInfoBlock = new JLabel("Please Refer to Console for Artist Info");

        JButton artBack = new JButton("<<");
        JButton artForward = new JButton(">>");
        JButton deleteArt = new JButton("Delete Art");
        JButton editArt = new JButton("Edit Art");

        bottomSubPanel.add(deleteArt);
        bottomSubPanel.add(artBack);
        bottomSubPanel.add(artForward);
        bottomSubPanel.add(editArt);

        artPiece.setIcon(new ImageIcon(art.get(i).FilePath));
        artTitle.setText(art.get(i).Title);
        artDate.setText(art.get(i).Date);
        artistName.setText(artists.get(i).Name);
        artistBirthDeath.setText(artists.get(i).DOB + " - " + artists.get(i).DOD);

        artBack.addActionListener( new ActionListener() {
             
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(arg0.getSource()==artBack)
                {
                    if(i==0)
                    {
                        JOptionPane.showMessageDialog(null,"This is First Image");
                    }
                    else
                        {
                        i=i-1;
                        artPiece.setIcon(new ImageIcon(art.get(i).FilePath));
                        artTitle.setText(art.get(i).Title);
                        artDate.setText(art.get(i).Date);
                        artistName.setText(artists.get(i).Name);
                        artistBirthDeath.setText(artists.get(i).DOB + " - " + artists.get(i).DOD);
                        System.out.println(artists.get(i).Info + "\n");
                    }
                }
            }
        });

        artForward.addActionListener( new ActionListener() {
             
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(arg0.getSource()==artForward)
                {
                    if(i==art.size() - 1)
                    {
                        JOptionPane.showMessageDialog(null,"This is Last Image");
                    }
                    else
                        {
                        i=i+1;
                        artPiece.setIcon(new ImageIcon(art.get(i).FilePath));
                        artTitle.setText(art.get(i).Title);
                        artDate.setText(art.get(i).Date);
                        artistName.setText(artists.get(i).Name);
                        artistBirthDeath.setText(artists.get(i).DOB + " - " + artists.get(i).DOD);
                        System.out.println(artists.get(i).Info + "\n");
                    }
                }
            }
        });

        
        createArt.addActionListener( new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                editFlag = false;
                frame.setContentPane(artDataModal);
                artDataModal.revalidate();
                artDataModal.repaint();
            }
        });

        deleteArt.addActionListener( new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Piece of art deleted. Please close and reopen application");
                ArtistRepository.deleteArtist(i + 1);
                ArtRepository.deleteArt(i + 1);
            }
        });

        editArt.addActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0){
                editFlag = true;
                frame.setContentPane(artDataModal);
                artDataModal.revalidate();
                artDataModal.repaint();
            }
        });


        editArt.addActionListener(departmentList);

        artPanel.add(headerSubPanel, BorderLayout.PAGE_START);

        artPanel.add(leftSubPanel, BorderLayout.LINE_START);

        artPanel.add(artPiece, BorderLayout.CENTER);
        
        artPanel.add(artistInfoBlock, BorderLayout.LINE_END);

        artPanel.add(bottomSubPanel, BorderLayout.PAGE_END);


        //#endregion
          
        //#region Moderator Data Modal
        moderatorDataModal = new JPanel(new GridLayout(0, 1));

        JLabel moderatorDataModalLabel = new JLabel("Replace Department Moderator", JLabel.CENTER);
        JLabel moderatorSSNLabel = new JLabel("  New Moderator SSN", JLabel.LEFT);
        JTextField moderatorSSN = new JTextField();
        JLabel moderatorNameLabel = new JLabel("  New Moderator Name", JLabel.LEFT);
        JTextField moderatorName = new JTextField();
        JTextField oldModeratorName = new JTextField();

        JButton submitModerator = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        submitModerator.addActionListener( new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    EmployeeRepository.editEmployee(moderatorSSN.getText(), oldModeratorName.getText(), moderatorName.getText());
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                frame.setContentPane(bootScreen);
                bootScreen.revalidate();
                bootScreen.repaint();
            }
        });

        cancelButton.addActionListener( new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.setContentPane(adminPanel);
                adminPanel.revalidate();
                adminPanel.repaint();
            }
        });

        moderatorDataModal.add(moderatorDataModalLabel);
        moderatorDataModal.add(moderatorSSNLabel);
        moderatorDataModal.add(moderatorSSN);
        moderatorDataModal.add(moderatorNameLabel);
        moderatorDataModal.add(moderatorName);
        moderatorDataModal.add(submitModerator);
        moderatorDataModal.add(cancelButton);

        //#endregion
 
        //#region admin panel
 
        adminPanel = new JPanel(new GridLayout(0, 2));
         
        JLabel adminLabelPanel = new JLabel("Admin Panel", JLabel.CENTER);

        JButton paintingDepartmentButton = new JButton("Painting Department");
        JButton paintingDepartmentEdit = new JButton("Edit Employee Information");
        JButton potteryDepartmentButton = new JButton("Pottery Department");
        JButton potteryDepartmentEdit = new JButton("Edit Employee Information");
        JButton sculptureDepartmentButton = new JButton("Sculptures Department");
        JButton sculpturesDepartmentEdit = new JButton("Edit Employee Information");
        JButton writingDepartmentButton = new JButton("Writing Department");
        JButton writingDepartmentEdit = new JButton("Edit Employee Information");
        
        
        
        paintingDepartmentButton.addActionListener( new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.setContentPane(artPanel);
                artPanel.revalidate();
                artPanel.repaint();
            }
        });

        paintingDepartmentEdit.addActionListener( new ActionListener() {
             
            @Override
            public void actionPerformed(ActionEvent arg0) {
                moderatorSSN.setText(employees.get(1).SSN);
                moderatorName.setText(employees.get(1).Name);
                oldModeratorName.setText(employees.get(1).Name);

                frame.setContentPane(moderatorDataModal);
                moderatorDataModal.revalidate();
                moderatorDataModal.repaint();
            }
        });

        potteryDepartmentButton.addActionListener( new ActionListener() {
             
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.setContentPane(artPanel);
                artPanel.revalidate();
                artPanel.repaint();
            }
        });

        potteryDepartmentEdit.addActionListener( new ActionListener() {
             
            @Override
            public void actionPerformed(ActionEvent arg0) {

                moderatorSSN.setText(employees.get(2).SSN);
                moderatorName.setText(employees.get(2).Name);
                oldModeratorName.setText(employees.get(2).Name);

                frame.setContentPane(moderatorDataModal);
                moderatorDataModal.revalidate();
                moderatorDataModal.repaint();
            }
        });
        
       sculptureDepartmentButton.addActionListener( new ActionListener() {
            
           @Override
           public void actionPerformed(ActionEvent arg0) {
               frame.setContentPane(artPanel);
               artPanel.revalidate();
               artPanel.repaint();
           }
       });

       sculpturesDepartmentEdit.addActionListener( new ActionListener() {
            
           @Override
           public void actionPerformed(ActionEvent arg0) {
               moderatorSSN.setText(employees.get(3).SSN);
               moderatorName.setText(employees.get(3).Name);
               oldModeratorName.setText(employees.get(3).Name);

               frame.setContentPane(moderatorDataModal);
               moderatorDataModal.revalidate();
               moderatorDataModal.repaint();
           }
       });
        
        writingDepartmentButton.addActionListener( new ActionListener() {
             
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.setContentPane(artPanel);
                artPanel.revalidate();
                artPanel.repaint();
            }
        });

        writingDepartmentEdit.addActionListener( new ActionListener() {
             
            @Override
            public void actionPerformed(ActionEvent arg0) {
                moderatorSSN.setText(employees.get(4).SSN);
                moderatorName.setText(employees.get(4).Name);
                oldModeratorName.setText(employees.get(4).Name);

                frame.setContentPane(moderatorDataModal);
                moderatorDataModal.revalidate();
                moderatorDataModal.repaint();
            }
        });

        adminPanel.add(adminLabelPanel);
        adminPanel.add(backButton);
        adminPanel.add(paintingDepartmentButton);
        adminPanel.add(paintingDepartmentEdit);
        adminPanel.add(potteryDepartmentButton);
        adminPanel.add(potteryDepartmentEdit);
        adminPanel.add(sculptureDepartmentButton);
        adminPanel.add(sculpturesDepartmentEdit);
        adminPanel.add(writingDepartmentButton);
        adminPanel.add(writingDepartmentEdit);


        //#endregion
        
      
       
        
        frame.setPreferredSize(new Dimension(750, 500));
        frame.setContentPane(bootScreen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Museum Management System");
        frame.pack();
        frame.setVisible(true);
         
         
         
    }
     
    public static void main(String[] args) throws SQLException {

        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                try {
                    new Test();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
}
