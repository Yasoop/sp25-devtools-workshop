package model;

import java.util.List;

/**
 * Class for the ReverseModel. A decorator class that adds the reverse rule to the game.
 */
public class ReverseModel extends ThreeTrioGame implements ThreeTrioModel<ThreeTrioCard> {
  private ThreeTrioModel<ThreeTrioCard> model;

  /**
   * Constructor for the ReverseModel class.
   *
   * @param model the model to be decorated
   */
  public ReverseModel(ThreeTrioModel<ThreeTrioCard> model) {
    super(model.getBoardConfig(), model.getDeckConfig());
    System.out.println("has model started:" + model.hasGameStarted());
    this.board = model.getBoard();
    this.playerOneHand = model.getPlayerOneHand();
    this.playerTwoHand = model.getPlayerTwoHand();
    this.model = model;
    // gameStarted = true;
  }

  @Override
  public boolean hasGameStarted() {
    return true;
  }

  @Override
  public void checkAndFlip(ThreeTrioCard[][] board, int adjRow, int adjCol,
                           Direction dirFrom, Direction dirTo, int row, int col,
                           ThreeTrioCard lastCard, List<ThreeTrioCard> flippedCards) {

    if (adjRow >= 0 && adjRow < board.length && adjCol >= 0 && adjCol < board[0].length) {
      ThreeTrioCard adjacentCard = board[adjRow][adjCol];

      if (adjacentCard.getColor() != lastCard.getColor()
              && !adjacentCard.isHole()
              && adjacentCard.getName() != null
              && compareAttacks(adjacentCard, lastCard, dirFrom, dirTo)) {
        flippedCards.add(adjacentCard);
        adjacentCard.setColor(lastCard.getColor());
        recursivelyFlip(board, adjRow, adjCol, adjacentCard, flippedCards, dirTo);
      }
    }
  }

  @Override
  public boolean compareAttacks(ThreeTrioCard adjacentCard, ThreeTrioCard lastCard,
                                Direction dirFrom, Direction dirTo) {
    return !model.compareAttacks(adjacentCard, lastCard, dirFrom, dirTo);
  }

}
