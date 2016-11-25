package client;

import java.util.Scanner;

/**
 * Created by hoisi on 11/5/2016.
 */
public class Main {

    public static void main(String args[]) {
        //Official has to create tally first
        Tally tally = new Tally();
        tally.read();
        //System.out.println("done");
        Scanner anotherVote = new Scanner(System.in);
        System.out.println("Please press Y to start the polling or X to cancel");
        while (anotherVote.next().equals("Y")) {
            Ballot ballot = new Ballot(tally.getCandidates());

            Scanner scannie = new Scanner(System.in);
            System.out.println("Who are you voting for?");
            for (int i = 0; i < ballot.getCandidates().size(); i++) {
                Candidate x = (Candidate) ballot.getCandidates().get(i);
                System.out.println(x.getName());
            }

            //Maybe the best way to do this in the future is have each button a Candidate Object and after submission build the array
            String name = scannie.next();
            for (int i = 0; i < ballot.getCandidates().size(); i++) {
                Candidate y = (Candidate) ballot.getCandidates().get(i); //This can't be help due to arrayList.get() right?
                if (name.equals(y.getName())) {
                    y.addVotesReceived();
                }
            }

            ballot.setBallotComplete(true);
            tally.addBallot(ballot);

            //Print test.txt
            System.out.println("Press Y to allow another vote to cast their vote, or X to stop the polling");
        }
        //Ballot readBallot= new Ballot(tally.getCandidates());
        System.out.println("Total Tally\n");
        for (int i = 0; i < tally.getCandidates().size(); i++) {
            Candidate y = (Candidate) tally.getCandidates().get(i);
            System.out.println("Candidate: "+ y.getName() + " Votes Recieved: "+ y.getVotesReceived()+"\n");

        }

    }
}