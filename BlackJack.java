package com.company;

import java.lang.*;
import java.util.*;

public class BlackJack {
    public static void main(String [] args){
        int playGame = 0; //controls outer while loop
        int gameNumber = 2; //reference for game number when it prints out game number
        int cardSum = 0; //keeps track of user's hand
        int continueGame = 0; // controls inner while loop
        int playerWins = 0; // keeps track of the number of user wins
        int dealerWins = 0; // keeps track of the number of dealer wins
        int tieGames = 0; // keeps track of the number of tie games

        Scanner input = new Scanner(System.in);
        System.out.println("START GAME # 1"); // first thing that shows when the program starts

        //outer while loop keeps does everything except gets user a card
        //inner while loop gets user a card so it only does this if option number 1 and when the program is first run
        while(playGame==0){
            System.out.println(); // prints blank line so spacing matches expected output
            while(continueGame==0) {
                int firstCard = ((int) Math.round(Math.random() * 12.0)) + 1; //generates users card between 1 and 13

                HashMap allCards = new HashMap();// puts face cards in a hashmap so i can access it using the key
                allCards.put(1, "ACE");
                allCards.put(11, "JACK");
                allCards.put(12, "QUEEN");
                allCards.put(13, "KING");

                //if its a face card so greater than 10 or 1 it'll enter here
                if (firstCard > 10 || firstCard == 1) {
                    System.out.println("Your card is a " + allCards.get(firstCard) + "!"); // displays face card name
                    //if the card is an ace then it enters this if statement
                    if (firstCard == 1) {
                        cardSum++; //adds one to player's hand
                        //all if statements break this inner while loop because it'll only get another card if player chooses option 1
                        //if player's hand is 21 then it'll do all this
                        if (cardSum == 21) {
                            System.out.println("Your hand is: " + cardSum);
                            System.out.println();
                            System.out.println("BLACKJACK! You win!");
                            System.out.println();
                            continueGame++;//breaks loop
                            cardSum = 0;//restarts hand for next game
                            playerWins++;//adds one to player wins
                        }
                        //if player busts then it'll do this
                        else if (cardSum > 21) {
                            System.out.println("Your hand is: " + cardSum);
                            System.out.println();
                            System.out.println("You exceeded 21! You lose :(");
                            System.out.println();
                            continueGame++;//breaks loop
                            cardSum = 0;//restarts hand for next game
                            dealerWins++;//adds one to dealer's wins
                        }
                        //if player can still get another card it'll do this
                        else {
                            System.out.println("Your hand is: " + cardSum);
                            System.out.println();
                            continueGame++;//breaks loop

                        }
                    }
                    //if its not an ace but still a face card it'll do this
                    else {
                        cardSum += 10;// adds ten to player's hand
                        //all if statements break this inner while loop because it'll only get another card if player chooses option 1

                        if (cardSum == 21) {
                            System.out.println("Your hand is: " + cardSum);
                            System.out.println();
                            System.out.println("BLACKJACK! You win!");
                            System.out.println();
                            continueGame++;//breaks loop
                            cardSum = 0;//restarts hand for next game
                            playerWins++;//adds to player wins
                        }

                        else if (cardSum > 21) {
                            System.out.println("Your hand is: " + cardSum);
                            System.out.println();
                            System.out.println("You exceeded 21! You lose :(");
                            System.out.println();
                            continueGame++;//breaks loop
                            cardSum = 0;//restart hand
                            dealerWins++;//dealer wins add one
                        }
                        //if player can get another card it'll do this
                        else {
                            System.out.println("Your hand is: " + cardSum);
                            System.out.println();
                            continueGame++;//break loop
                        }
                    }
                }
                //all normal cards that are not a face card or ace
                else {
                    cardSum += firstCard;
                    System.out.println("Your card is a " + firstCard + "!");
                    System.out.println("Your hand is: " + cardSum);
                    System.out.println();
                    //all if statements break inner while loop

                    if (cardSum == 21) {
                        System.out.println("BLACKJACK! You win!");
                        System.out.println();
                        continueGame++;//break loop
                        cardSum = 0;//restart hand
                        playerWins++;//add to player wins
                    }

                    else if (cardSum > 21) {
                        System.out.println("You exceeded 21! You lose :(");
                        System.out.println();
                        continueGame++;
                        cardSum = 0;
                        dealerWins++;
                    }
                    //if player can get another card
                    else {
                        continueGame++;
                    }
                }
            }

            //if hand was reset to zero(so if user got 21 or busted)
            if(cardSum==0){
                System.out.println("START GAME #" + gameNumber);//starts next game
                gameNumber++;//adds one to display game number
                continueGame--; //enters inner while loop again so user can get another card for next game
            }

            //if user did not win or lose yet then it'll display all the options
            else {
                System.out.println("1. Get another card");
                System.out.println("2. Hold hand");
                System.out.println("3. Print statistics");
                System.out.println("4. Exit");
                System.out.println();
                System.out.print("Choose an option: ");
                int nextMove = 0;

                try {
                    nextMove = input.nextInt(); //gets user's option

                    if (nextMove == 1) {
                        continueGame--; // if user chooses option 1 it will go back through the inner while loop to get another card
                    }
                    //if user chooses option 2
                    else if (nextMove == 2) {
                        Random ran = new Random();
                        int dealersHand = ran.nextInt(11) + 16; //gets random card for dealer
                        System.out.println();
                        System.out.println("Dealer's hand: " + dealersHand);
                        System.out.println("Your hand is: " + cardSum);
                        System.out.println();
                        //if dealer busts or user's hand is greater than the dealer's
                        if (dealersHand > 21 || cardSum > dealersHand) {
                            System.out.println("You win!");
                            System.out.println();
                            playerWins++;//add to player wins
                        }
                        //if dealer's hand = user's hand
                        else if (dealersHand == cardSum) {
                            System.out.println("It's a tie! No one wins!");
                            System.out.println();
                            tieGames++;//adds one to tie games
                        }
                        //if dealers hand is greater than the user's and it is under 21
                        else if (dealersHand > cardSum && dealersHand <= 21) {
                            System.out.println("Dealer wins!");
                            System.out.println();
                            dealerWins++;//add one to dealer wins
                        }
                        cardSum = 0;//resets user's hand for next game
                        System.out.println("START GAME #" + gameNumber);//starts next game
                        System.out.println();
                        gameNumber++;//adds to game numbers
                        continueGame--;//goes back into inner while loop so user gets a new card for next game
                    }
                    //if user chooses option 3
                    else if (nextMove == 3) {
                        double percentWins = ((double) playerWins / (double) (gameNumber - 2)) * 100; //calculates percentage of player wins
                        System.out.println("Number of Player wins: " + playerWins);
                        System.out.println("Number of Dealer wins: " + dealerWins);
                        System.out.println("Number of tie games: " + tieGames);
                        System.out.println("Total # of games played is: " + (gameNumber - 2));
                        System.out.println("Percentage of Player wins: " + String.format("%.1f",percentWins) + " %"); //formats percent to one decimal point
                    }
                    //if user chooses option 4
                    else if (nextMove == 4) {
                        playGame++;//breaks outer while loop so game ends
                    }
                    //if user chooses an integer but its not 1,2,3, or 4 then it'll do this
                    else {
                        System.out.println();
                        System.out.println("Invalid input!");
                        System.out.println("Please enter an integer between 1 and 4");
                    }
                }
                //if user does not enter an integer it'll catch it
                catch (Exception e) {
                    System.out.println();
                    System.out.println("Invalid input!");
                    System.out.println("Please enter an integer between 1 and 4.");
                    input.nextLine();//clears user input so they can choose another option
                }
            }

        }
    }
}
