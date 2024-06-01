package src;

import java.awt.*;
import java.awt.event.*;

public class CardLayoutDemo implements ActionListener {
    Frame frame;
    Panel p1, p2;
    CardLayout card;
    Button next, previous, first, last, show;    
    
    public static void main(String[] args) {
        new CardLayoutDemo();
    }
    
    public CardLayoutDemo() {
        Frame frame = new Frame("AWTDemo");
        frame.addWindowListener(new AdapterDemo());
        frame.setSize(400, 200);
        
        p1 = new Panel();
        card = new CardLayout();
        p1.setLayout(card);
        p1.add(new Label("one"), "one");
        p1.add(new Label("two"), "two");
        p1.add(new Label("three"), "three");
        p1.add(new Label("four"), "four");
        p1.add(new Label("five"), "five");
        
        p2 = new Panel();
        p2.setLayout(new FlowLayout());
        next = new Button("NEXT");
        next.setActionCommand("next");
        next.addActionListener(this);
        previous = new Button("PREVIOUS");
        previous.setActionCommand("previous");
        previous.addActionListener(this);
        first = new Button("FIRST");
        first.setActionCommand("first");
        first.addActionListener(this);
        last = new Button("LAST");
        last.setActionCommand("last");
        last.addActionListener(this);
        show = new Button("SHOW");
        show.setActionCommand("show");
        show.addActionListener(this);
        p2.add(next);
        p2.add(previous);
        p2.add(first);
        p2.add(last);
        p2.add(show);
        
        frame.add(p1, BorderLayout.CENTER);
        frame.add(p2, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd == "next") {
            card.next(p1);
        }
        if (cmd == "previous") {
            card.previous(p1);
        }
        if (cmd == "first") {
            card.first(p1);
        }
        if (cmd == "last") {
            card.last(p1);
        }
        if (cmd == "show") {
            card.show(p1, "three");
        }
    }
}

class AdapterDemo extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}