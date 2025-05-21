package br.com.dio.ui.custom.panel;

import br.com.dio.ui.custom.input.NumberText;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class SudokuSector extends JPanel {
    public SudokuSector(final List<NumberText> textFields) {
        var dimension = new Dimension(170, 170);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setBorder(new LineBorder(Color.black, 2, true));
        this.setVisible(true);
        textFields.forEach(this::add);
    }
}
