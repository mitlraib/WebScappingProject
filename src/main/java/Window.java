//import javax.swing.*;
//
//public class Window extends JFrame {
//    public static void main(String[] args) {
//        Window myWindow = new Window();
//    }
//
//    public void init() {
//        this.setVisible(true);
//        this.setSize(500, 500);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setLocationRelativeTo(null);
//        this.setResizable(false);
//        this.setLayout(null);
//    }
//
//    private void drawOneWindow() {
//        JLabel title = new JLabel();
//        title.setText("My name is Meital  :) ");
//        title.setBounds(20, 30, 200, 100);
//        this.add(title);
//
//        //buttons start here
//        JButton addWordBtn = new JButton("Add here a word to be ignored :) ");
//        addWordBtn.setBounds(50, 150, 300, 50);
//        this.add(addWordBtn);
//        addWordBtn.addActionListener((event) -> {
//            System.out.println("button click");
//        });
//
//        JButton button = new JButton("Start scan");
//        button.setBounds(150, 300, 150, 50);
//        this.add(button);
//        button.addActionListener((event) -> {
//            System.out.println("button click");
//
//        });
//    }
//
//    private void text() {
//        JLabel title = new JLabel();
//
//        title.setText(" The ten words that are most frequently used in this article are: ");
//        Pilot.main();
//
//        title.setBounds(20, 30, 200, 100);
//        this.add(title);
//    }
//
//    public Window() {
//        this.init();
//        this.drawOneWindow();
//    }
//}

