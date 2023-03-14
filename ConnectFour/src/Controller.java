/**
 * This file is to be completed by you.
 * @author S2064253
 */

import java.util.Scanner;

public final class Controller {
	private final Model model;
	private final TextView view;

	public Controller(Model model, TextView view) {
		this.model = model;
		this.view = view;
	}

	Scanner input = new Scanner(System.in);

	public void startSession() {

		while (true) {

			while (!model.validGridSize()) {

				view.wrongGridSize();
				view.rule();
				model.resetGame();
			}
			view.displayBoard(model);
			view.displayNewGameMessage();

			view.ai();
			String answer = input.next();

			while (!model.ifGameOver()) {

				if (!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("N")) {
					view.invalidDecisions();
					answer = input.next();
				}
				if (answer.equalsIgnoreCase("Y")) {

					if (model.n % 2 == 0) {
						view.players(model);

						int putVal = model.getRandoms();

						while (model.isColFull(putVal)) {
							putVal = input.nextInt();
						}
						model.makeMove(putVal);
						model.n++;
					}

					if (model.n % 2 == 1) {
						view.players(model);
						view.notice(model);

						int putVal = input.nextInt();

						while (!model.isMoveValid(putVal)) {
							view.invalidVal();
							putVal = input.nextInt();

						}
						while (model.isColFull(putVal)) {
							view.invalidVal();
							putVal = input.nextInt();
						}
						model.makeMove(putVal);
						model.n++;
					}
				}

				//Two human players battle;
				if (answer.equalsIgnoreCase("N")) {
					view.players(model);
					view.notice(model);

					int putVal = input.nextInt();

					while (!model.isMoveValid(putVal)) {
						view.invalidVal();
						putVal = input.nextInt();

					}
					while (model.isColFull(putVal)) {
						view.invalidVal();
						putVal = input.nextInt();
					}
					model.makeMove(putVal);
					model.n++;
				}

				if (answer.equalsIgnoreCase("Y" )||answer.equalsIgnoreCase("N")) {
					//_____________
					//Someone win;
					if (model.hvWins() || model.rDiagonalWin() || model.lDiagonalWin()) {
						view.gameOver();
						view.winner(model);
						view.winnerCheer();
						break;
					}
					//____________________________
					//Game is drawn (nobody win);
					else if (model.Full()) {
						view.gameOver();
						view.noWins();
						break;
					}
				}
					//_______________________
					//Human player give up vs AI;
					if (answer.equalsIgnoreCase("Y")) {
					if (model.aiConcede()) {

						view.aiConcede(model);
						String reStart = input.next();

						while ((!reStart.equalsIgnoreCase("Y")) &&
								(!reStart.equalsIgnoreCase("N"))) {

							view.invalidDecisions();
							reStart = input.next();
						}

						if (reStart.equalsIgnoreCase("Y")) {
							view.gameOver();
							view.aiWinner(model);
							view.aiWin();
							break;
						} else {
							view.continues();
							continue;
						}
					}
				}
					//Human Player Give up vs Human
					if (answer.equalsIgnoreCase("N")) {
						if (model.concede()) {

							view.concede(model);
							String reStart = input.next();

							while ((!reStart.equalsIgnoreCase("Y")) &&
									(!reStart.equalsIgnoreCase("N"))) {

								view.invalidDecisions();
								reStart = input.next();
							}

							if (reStart.equalsIgnoreCase("Y")) {
								view.gameOver();
								view.winner(model);
								view.winnerCheer();
								break;
							} else {
								view.continues();
								continue;
						}
					}
				}
			}

			//_____________________
			//Play another round;
			while (true) {

				view.newGame();
				String reStart = input.next();

				if (reStart.equalsIgnoreCase("Y")) {
					model.resetGame();
					break;

				} else if (reStart.equalsIgnoreCase("N")) {
					view.Thanks();
					System.exit(0);

				} else {
					view.invalidDecisions();
				}
			}
		}
	}
}








