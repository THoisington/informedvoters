package client;

/**
 * Created by hoisi on 10/27/2016.
 */
public class Candidate {

    String name;
    String office;
    String bio;

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    String party;
    int votesReceived;

    public Candidate(){}

    public int getVotesReceived() {
        return votesReceived;
    }

    public void addVotesReceived() {
        this.votesReceived++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}