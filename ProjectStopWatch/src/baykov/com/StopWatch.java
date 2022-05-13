package baykov.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopWatch implements ActionListener {

    private static final JFrame frame = new JFrame();
    private static final JButton startButton = new JButton("Start");
    private static final JButton stopButton = new JButton("Stop");
    private static final JButton resetButton = new JButton("Reset");
    private static final JLabel timeLabel = new JLabel();
    private static int elapsedTime = 0;
    private static int milliseconds = 0;
    private static int seconds = 0;
    private static int minutes = 0;
    private static String seconds_string = String.format("%02d", seconds);
    private static String minutes_string = String.format("%02d", minutes);
    private static String milliseconds_string = String.format("%02d", milliseconds);
    private static final Font textFont = new Font("Calibri", Font.PLAIN, 60);
    private static final Font buttonFont = new Font("Verdana", Font.ITALIC, 20);

    private static final Timer timer = new Timer(1, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime += 1;
            // elapsedTime updates every 1 millisecond
            minutes = (elapsedTime / 60000) % 60;
            // 60 000 --> milliseconds in 1 minute
            seconds = (elapsedTime / 1000) % 60;
            milliseconds = elapsedTime % 100;
            milliseconds_string = String.format("%02d", milliseconds);
            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            timeLabel.setText(minutes_string + ":" + seconds_string + "," + milliseconds_string);

//            hours = elapsedTime / 3600000;
//            3 600 000 --> milliseconds in 1 hour
        }
    });

    StopWatch() {

        timeLabel.setText(minutes_string + ":" + seconds_string + "," + milliseconds_string);
        // 00:00,00
        timeLabel.setBounds(70, 70, 350, 100);
        timeLabel.setFont(textFont);
        timeLabel.setForeground(Color.WHITE);
        // sets the font color to white
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        startButton.setBounds(80, 200, 80, 40);
        startButton.setFont(buttonFont);
        startButton.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        if (startButton.getModel().isArmed()) {
            startButton.setBackground(Color.lightGray);
        } else {
            startButton.setBackground(Color.GREEN);
        }
        startButton.setForeground(Color.BLACK);
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        stopButton.setBounds(320, 200, 80, 40);
        stopButton.setFont(buttonFont);
        stopButton.setBorder(BorderFactory.createLineBorder(Color.RED));
        if (stopButton.getModel().isArmed()) {
            stopButton.setBackground(Color.lightGray);
        } else {
            stopButton.setBackground(Color.RED);
        }
        stopButton.setForeground(Color.BLACK);
        stopButton.setFocusable(false);
        stopButton.addActionListener(this);

        resetButton.setBounds(200, 200, 80, 40);
        resetButton.setFont(buttonFont);
        resetButton.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        if (resetButton.getModel().isArmed()) {
            resetButton.setBackground(Color.lightGray);
        } else {
            resetButton.setBackground(Color.GRAY);
        }
        resetButton.setForeground(Color.BLACK);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.getContentPane().setBackground(Color.BLACK);
        // sets the background color to black
        frame.setLayout(null);
        // makes the background color (black) visible

        frame.add(stopButton);
        frame.add(resetButton);
        frame.add(startButton);
        frame.add(timeLabel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == startButton) {
            start();
        }

        if (e.getSource() == stopButton) {
            stop();
        }

        if (e.getSource() == resetButton) {
            reset();
        }

    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void reset() {
        timer.stop();
        elapsedTime = 0;
        milliseconds = 0;
        seconds = 0;
        minutes = 0;
        milliseconds_string = String.format("%02d", milliseconds);
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        timeLabel.setText(minutes_string + ":" + seconds_string + "," + milliseconds_string);
    }
}
