package controller;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import model.Player;
import model.RowGameModel;
import view.RowGameGUI;

public class RowGameController {
    public RowGameModel gameModel;
    public RowGameGUI gameView;

    /**
     * Creates a new game initializing the GUI.
     */
    public RowGameController() {
	gameModel = new RowGameModel();
	gameView = new RowGameGUI(this);

	// Apply the Observer design pattern
	//
	// In this case, the RowGameGUI (the composite) is registered.
	// Alternatively, the RowBoardGameView and RowGameStatusView 
	// (the components) could be registered.
	gameModel.register(gameView);

	this.resetGame();
    }

    private BlockIndex getBlockIndex(JButton block) {
	for (int row = 0; row < 3; row++) {
	    for (int column = 0; column < 3; column++) {
		if (block == gameView.boardGameView.blocks[row][column]) {
		    return new BlockIndex(row, column);
		}
	    } // end for column
	} // end for row

	return null;
    }

    /**
     * Moves the current player into the given block.
     *
     * @param block The block to be moved to by the current player
     */
    public void move(JButton block) {
	// Check the pre-conditions for a legal move
	BlockIndex blockIndex = this.getBlockIndex(block);
	if ((blockIndex == null) || 
	    (gameModel.blocksData[blockIndex.getRow()][blockIndex.getColumn()].getIsLegalMove() == false)) {
	    return;
	}

	gameModel.setMovesLeft(gameModel.getMovesLeft() - 1);
	
	if(gameModel.getPlayer().equals(Player.PLAYER_1)) {
	    // Check whether player 1 won
	    if(block==gameView.boardGameView.blocks[0][0]) {
		gameModel.blocksData[0][0].setContents("X");
		gameModel.blocksData[0][0].setIsLegalMove(false);
		gameModel.setPlayer(Player.PLAYER_2);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.blocksData[0][0].getContents().equals(gameModel.blocksData[0][1].getContents()) &&
			gameModel.blocksData[0][1].getContents().equals(gameModel.blocksData[0][2].getContents())) ||
		       (gameModel.blocksData[0][0].getContents().equals(gameModel.blocksData[1][0].getContents()) &&
			gameModel.blocksData[1][0].getContents().equals(gameModel.blocksData[2][0].getContents())) ||
		       (gameModel.blocksData[0][0].getContents().equals(gameModel.blocksData[1][1].getContents()) &&
			gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[2][2].getContents()))) {
			gameModel.setFinalResult("Player 1 wins!");
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.boardGameView.blocks[0][1]) {
		gameModel.blocksData[0][1].setContents("X");
		gameModel.blocksData[0][1].setIsLegalMove(false);
		gameModel.setPlayer(Player.PLAYER_2);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.blocksData[0][1].getContents().equals(gameModel.blocksData[0][0].getContents()) &&
			gameModel.blocksData[0][0].getContents().equals(gameModel.blocksData[0][2].getContents())) ||
		       (gameModel.blocksData[0][1].getContents().equals(gameModel.blocksData[1][1].getContents()) &&
			gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[2][1].getContents()))) {
			gameModel.setFinalResult("Player 1 wins!");
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.boardGameView.blocks[0][2]) {
		gameModel.blocksData[0][2].setContents("X");
		gameModel.blocksData[0][2].setIsLegalMove(false);
		gameModel.setPlayer(Player.PLAYER_2);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.blocksData[0][2].getContents().equals(gameModel.blocksData[0][1].getContents()) &&
			gameModel.blocksData[0][1].getContents().equals(gameModel.blocksData[0][0].getContents())) ||
		       (gameModel.blocksData[0][2].getContents().equals(gameModel.blocksData[1][2].getContents()) &&
			gameModel.blocksData[1][2].getContents().equals(gameModel.blocksData[2][2].getContents())) ||
		       (gameModel.blocksData[0][2].getContents().equals(gameModel.blocksData[1][1].getContents()) &&
			gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[2][0].getContents()))) {
			gameModel.setFinalResult("Player 1 wins!");
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.boardGameView.blocks[1][0]) {
		gameModel.blocksData[1][0].setContents("X");
		gameModel.blocksData[1][0].setIsLegalMove(false);
		gameModel.setPlayer(Player.PLAYER_2);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.blocksData[1][0].getContents().equals(gameModel.blocksData[1][1].getContents()) &&
			gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[1][2].getContents())) ||
		       (gameModel.blocksData[1][0].getContents().equals(gameModel.blocksData[0][0].getContents()) &&
			gameModel.blocksData[0][0].getContents().equals(gameModel.blocksData[2][0].getContents()))) {
			gameModel.setFinalResult("Player 1 wins!");
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.boardGameView.blocks[1][1]) {
		gameModel.blocksData[1][1].setContents("X");
		gameModel.blocksData[1][1].setIsLegalMove(false);
		gameModel.setPlayer(Player.PLAYER_2);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[1][0].getContents()) &&
			gameModel.blocksData[1][0].getContents().equals(gameModel.blocksData[1][2].getContents())) ||
		       (gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[0][1].getContents()) &&
			gameModel.blocksData[0][1].getContents().equals(gameModel.blocksData[2][1].getContents())) ||
		       (gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[0][0].getContents()) &&
			gameModel.blocksData[0][0].getContents().equals(gameModel.blocksData[2][2].getContents())) ||
		       (gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[0][2].getContents()) &&
			gameModel.blocksData[0][2].getContents().equals(gameModel.blocksData[2][0].getContents()))) {
			gameModel.setFinalResult("Player 1 wins!");
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.boardGameView.blocks[1][2]) {
		gameModel.blocksData[1][2].setContents("X");
		gameModel.blocksData[1][2].setIsLegalMove(false);
		gameModel.setPlayer(Player.PLAYER_2);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.blocksData[1][2].getContents().equals(gameModel.blocksData[0][2].getContents()) &&
			gameModel.blocksData[0][2].getContents().equals(gameModel.blocksData[2][2].getContents())) ||
		       (gameModel.blocksData[1][2].getContents().equals(gameModel.blocksData[1][1].getContents()) &&
			gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[1][0].getContents()))) {
			gameModel.setFinalResult("Player 1 wins!");
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.boardGameView.blocks[2][0]) {
		gameModel.blocksData[2][0].setContents("X");
		gameModel.blocksData[2][0].setIsLegalMove(false);
		gameModel.setPlayer(Player.PLAYER_2);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.blocksData[2][0].getContents().equals(gameModel.blocksData[2][1].getContents()) &&
			gameModel.blocksData[2][1].getContents().equals(gameModel.blocksData[2][2].getContents())) ||
		       (gameModel.blocksData[2][0].getContents().equals(gameModel.blocksData[1][0].getContents()) &&
			gameModel.blocksData[1][0].getContents().equals(gameModel.blocksData[0][0].getContents())) ||
		       (gameModel.blocksData[2][0].getContents().equals(gameModel.blocksData[1][1].getContents()) &&
			gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[0][2].getContents()))) {
			gameModel.setFinalResult("Player 1 wins!");
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.boardGameView.blocks[2][1]) {
		gameModel.blocksData[2][1].setContents("X");
		gameModel.blocksData[2][1].setIsLegalMove(false);
		gameModel.setPlayer(Player.PLAYER_2);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.blocksData[2][1].getContents().equals(gameModel.blocksData[2][0].getContents()) &&
			gameModel.blocksData[2][0].getContents().equals(gameModel.blocksData[2][2].getContents())) ||
		       (gameModel.blocksData[2][1].getContents().equals(gameModel.blocksData[1][1].getContents()) &&
			gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[0][1].getContents()))) {
			gameModel.setFinalResult("Player 1 wins!");
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.boardGameView.blocks[2][2]) {
		gameModel.blocksData[2][2].setContents("X");
		gameModel.blocksData[2][2].setIsLegalMove(false);
		gameModel.setPlayer(Player.PLAYER_2);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.blocksData[2][2].getContents().equals(gameModel.blocksData[2][1].getContents()) &&
			gameModel.blocksData[2][1].getContents().equals(gameModel.blocksData[2][0].getContents())) ||
		       (gameModel.blocksData[2][2].getContents().equals(gameModel.blocksData[1][2].getContents()) &&
			gameModel.blocksData[1][2].getContents().equals(gameModel.blocksData[0][2].getContents())) ||
		       (gameModel.blocksData[2][2].getContents().equals(gameModel.blocksData[1][1].getContents()) &&
			gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[0][0].getContents()))) {
			gameModel.setFinalResult("Player 1 wins!");
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
		    }
		}
	    }
	} else {
	    // Check whether player 2 won
	    if(block==gameView.boardGameView.blocks[0][0]) {
		gameModel.blocksData[0][0].setContents("O");
		gameModel.blocksData[0][0].setIsLegalMove(false);
		gameModel.setPlayer(Player.PLAYER_1);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.blocksData[0][0].getContents().equals(gameModel.blocksData[0][1].getContents()) &&
			gameModel.blocksData[0][1].getContents().equals(gameModel.blocksData[0][2].getContents())) ||
		       (gameModel.blocksData[0][0].getContents().equals(gameModel.blocksData[1][0].getContents()) &&
			gameModel.blocksData[1][0].getContents().equals(gameModel.blocksData[2][0].getContents())) ||
		       (gameModel.blocksData[0][0].getContents().equals(gameModel.blocksData[1][1].getContents()) &&
			gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[2][2].getContents()))) {
			gameModel.setFinalResult("Player 2 wins!");
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.boardGameView.blocks[0][1]) {
		gameModel.blocksData[0][1].setContents("O");
		gameModel.blocksData[0][1].setIsLegalMove(false);
		gameModel.setPlayer(Player.PLAYER_1);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.blocksData[0][1].getContents().equals(gameModel.blocksData[0][0].getContents()) &&
			gameModel.blocksData[0][0].getContents().equals(gameModel.blocksData[0][2].getContents())) ||
		       (gameModel.blocksData[0][1].getContents().equals(gameModel.blocksData[1][1].getContents()) &&
			gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[2][1].getContents()))) {
			gameModel.setFinalResult("Player 2 wins!");
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.boardGameView.blocks[0][2]) {
		gameModel.blocksData[0][2].setContents("O");
		gameModel.blocksData[0][2].setIsLegalMove(false);
		gameModel.setPlayer(Player.PLAYER_1);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.blocksData[0][2].getContents().equals(gameModel.blocksData[0][1].getContents()) &&
			gameModel.blocksData[0][1].getContents().equals(gameModel.blocksData[0][0].getContents())) ||
		       (gameModel.blocksData[0][2].getContents().equals(gameModel.blocksData[1][2].getContents()) &&
			gameModel.blocksData[1][2].getContents().equals(gameModel.blocksData[2][2].getContents())) ||
		       (gameModel.blocksData[0][2].getContents().equals(gameModel.blocksData[1][1].getContents()) &&
			gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[2][0].getContents()))) {
			gameModel.setFinalResult("Player 2 wins!");
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.boardGameView.blocks[1][0]) {
		gameModel.blocksData[1][0].setContents("O");
		gameModel.blocksData[1][0].setIsLegalMove(false);
		gameModel.setPlayer(Player.PLAYER_1);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.blocksData[1][0].getContents().equals(gameModel.blocksData[1][1].getContents()) &&
			gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[1][2].getContents())) ||
		       (gameModel.blocksData[1][0].getContents().equals(gameModel.blocksData[0][0].getContents()) &&
			gameModel.blocksData[0][0].getContents().equals(gameModel.blocksData[2][0].getContents()))) {
			gameModel.setFinalResult("Player 2 wins!");
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.boardGameView.blocks[1][1]) {
		gameModel.blocksData[1][1].setContents("O");
		gameModel.blocksData[1][1].setIsLegalMove(false);
		gameModel.setPlayer(Player.PLAYER_1);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[1][0].getContents()) &&
			gameModel.blocksData[1][0].getContents().equals(gameModel.blocksData[1][2].getContents())) ||
		       (gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[0][1].getContents()) &&
			gameModel.blocksData[0][1].getContents().equals(gameModel.blocksData[2][1].getContents())) ||
		       (gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[0][0].getContents()) &&
			gameModel.blocksData[0][0].getContents().equals(gameModel.blocksData[2][2].getContents())) ||
		       (gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[0][2].getContents()) &&
			gameModel.blocksData[0][2].getContents().equals(gameModel.blocksData[2][0].getContents()))) {
			gameModel.setFinalResult("Player 2 wins!");
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.boardGameView.blocks[1][2]) {
		gameModel.blocksData[1][2].setContents("O");
		gameModel.blocksData[1][2].setIsLegalMove(false);
		gameModel.setPlayer(Player.PLAYER_1);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.blocksData[1][2].getContents().equals(gameModel.blocksData[0][2].getContents()) &&
			gameModel.blocksData[0][2].getContents().equals(gameModel.blocksData[2][2].getContents())) ||
		       (gameModel.blocksData[1][2].getContents().equals(gameModel.blocksData[1][1].getContents()) &&
			gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[1][0].getContents()))) {
			gameModel.setFinalResult("Player 2 wins!");
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.boardGameView.blocks[2][0]) {
		gameModel.blocksData[2][0].setContents("O");
		gameModel.blocksData[2][0].setIsLegalMove(false);
		gameModel.setPlayer(Player.PLAYER_1);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.blocksData[2][0].getContents().equals(gameModel.blocksData[2][1].getContents()) &&
			gameModel.blocksData[2][1].getContents().equals(gameModel.blocksData[2][2].getContents())) ||
		       (gameModel.blocksData[2][0].getContents().equals(gameModel.blocksData[1][0].getContents()) &&
			gameModel.blocksData[1][0].getContents().equals(gameModel.blocksData[0][0].getContents())) ||
		       (gameModel.blocksData[2][0].getContents().equals(gameModel.blocksData[1][1].getContents()) &&
			gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[0][2].getContents()))) {
			gameModel.setFinalResult("Player 2 wins!");
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.boardGameView.blocks[2][1]) {
		gameModel.blocksData[2][1].setContents("O");
		gameModel.blocksData[2][1].setIsLegalMove(false);
		gameModel.setPlayer(Player.PLAYER_1);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.blocksData[2][1].getContents().equals(gameModel.blocksData[2][0].getContents()) &&
			gameModel.blocksData[2][0].getContents().equals(gameModel.blocksData[2][2].getContents())) ||
		       (gameModel.blocksData[2][1].getContents().equals(gameModel.blocksData[1][1].getContents()) &&
			gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[0][1].getContents()))) {
			gameModel.setFinalResult("Player 2 wins!");
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.boardGameView.blocks[2][2]) {
		gameModel.blocksData[2][2].setContents("O");
		gameModel.blocksData[2][2].setIsLegalMove(false);
		gameModel.setPlayer(Player.PLAYER_1);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.blocksData[2][2].getContents().equals(gameModel.blocksData[2][1].getContents()) &&
			gameModel.blocksData[2][1].getContents().equals(gameModel.blocksData[2][0].getContents())) ||
		       (gameModel.blocksData[2][2].getContents().equals(gameModel.blocksData[1][2].getContents()) &&
			gameModel.blocksData[1][2].getContents().equals(gameModel.blocksData[0][2].getContents())) ||
		       (gameModel.blocksData[2][2].getContents().equals(gameModel.blocksData[1][1].getContents()) &&
			gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[0][0].getContents()))) {
			gameModel.setFinalResult("Player 2 wins!");
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
		    }
		}
	    }
	}

	// The Observer design pattern is now doing the following.
	//gameView.update(gameModel);
    }

    /**
     * Ends the game disallowing further player turns.
     */
    public void endGame() {
	for(int row = 0;row<3;row++) {
	    for(int column = 0;column<3;column++) {
		gameModel.blocksData[row][column].setIsLegalMove(false);
	    }
	}

	// The Observer design pattern is now doing the following. 
	//gameView.update(gameModel);
    }

    /**
     * Resets the game to be able to start playing again.
     */
    public void resetGame() {
        for(int row = 0;row<3;row++) {
            for(int column = 0;column<3;column++) {
                gameModel.blocksData[row][column].reset();
		gameModel.blocksData[row][column].setIsLegalMove(true);
            }
        }
        gameModel.setPlayer(Player.PLAYER_1);
        gameModel.setMovesLeft(9);
	gameModel.setFinalResult(null);

	// The Observer design pattern is now doing the following. 
	//gameView.update(gameModel);
    }
}
