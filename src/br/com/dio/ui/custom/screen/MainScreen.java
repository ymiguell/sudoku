package br.com.dio.ui.custom.screen;

import br.com.dio.service.BoardService;
import br.com.dio.ui.custom.button.FinishGameButton;
import br.com.dio.ui.custom.button.ResetButton;
import br.com.dio.ui.custom.frame.MainFrame;
import br.com.dio.ui.custom.panel.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainScreen {

    private final static Dimension dimension = new Dimension(600, 600);

    private final BoardService boardService;

    private JButton checkGameStatusButton;
    private JButton FinishGameButton;
    private JButton resetButton;



    public MainScreen(final Map<String, String> gameConfig) {
        this.boardService = new BoardService(gameConfig);
    }

    public void buildMainScreen() {
        JPanel mainPanel = new MainPanel(dimension);
        JFrame mainFrame = new MainFrame(dimension, mainPanel);
        addResetButton(mainPanel);
        addCheckGameStatusButton(mainPanel);
        addFinishGameButton(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private void addFinishGameButton(JPanel mainPanel) {
        JButton finishGameButton = new FinishGameButton(e -> {
           if (boardService.gameIsFinished()) {
               JOptionPane.showMessageDialog(null, "Parabéns voce concluiu o jogo");
                resetButton.setEnabled(false);
                checkGameStatusButton.setEnabled(false);
                FinishGameButton.setEnabled(false);
           } else {
               JOptionPane.showMessageDialog(null, "Seu jogo tem alguma inconsistencia, ajuste e tente novamente");
           }
        });
        mainPanel.add(finishGameButton);
    }

    private void addCheckGameStatusButton(JPanel mainPanel) {
        JButton checkGameStatusButton = new FinishGameButton(e -> {
            var hasErrors = boardService.hasErrors();
            var gameStatus = boardService.getStatus();
            var message = switch (gameStatus) {
                case NON_STARTED -> "O jogo não foi iniciado";
                case INCOMPLETE -> "O jogo esta incompleto";
                case COMPLETE -> "O jogo esta completo";
            };
            message += hasErrors ? "e contem erros" : " e nao contem erros";
            JOptionPane.showMessageDialog(null, message);
        });

        mainPanel.add(checkGameStatusButton);
    }

    private void addResetButton(JPanel mainPanel) {
        JButton resetButton = new ResetButton(e -> {
            var dialogResult = JOptionPane.showConfirmDialog(
                    null,
                    "Deseja realmente reiniciar o jogo?",
                    "Limpar o jogo",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            if (dialogResult == 0) {
                boardService.reset();
            }
        });
        mainPanel.add(resetButton);
    }

}
