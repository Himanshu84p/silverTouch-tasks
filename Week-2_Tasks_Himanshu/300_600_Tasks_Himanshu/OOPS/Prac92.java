import java.util.ArrayList;
import java.util.List;

class Team {
    private String name;

    public Team(String teamName) {
        name = teamName;
    }

    public String getName() {
        return name;
    }
}

class Match {
    private Team team1;
    private Team team2;
    private int scoreTeam1;
    private int scoreTeam2;
    private boolean isMatchPlayed;

    public Match(Team t1, Team t2) {
        team1 = t1;
        team2 = t2;
        scoreTeam1 = 0;
        scoreTeam2 = 0;
        isMatchPlayed = false;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public int getScoreTeam1() {
        return scoreTeam1;
    }

    public int getScoreTeam2() {
        return scoreTeam2;
    }

    public boolean getIsMatchPlayed() {
        return isMatchPlayed;
    }

    public void playMatch(int score1, int score2) {
        scoreTeam1 = score1;
        scoreTeam2 = score2;
        isMatchPlayed = true;
    }
}

class Tournament {
    private List<Match> matches;

    public Tournament() {
        matches = new ArrayList<>();
    }

    public void addMatch(Team team1, Team team2) {
        matches.add(new Match(team1, team2));
    }

    public void updateMatchResult(int matchIndex, int score1, int score2) {
        if (matchIndex >= 0 && matchIndex < matches.size()) {
            Match match = matches.get(matchIndex);
            match.playMatch(score1, score2);
        } else {
            System.out.println("Invalid match index!");
        }
    }

    public void displayResults() {
        System.out.println("Tournament Results:");
        System.out.println("-------------------------");
        for (Match match : matches) {
            System.out.print(match.getTeam1().getName() + " vs " + match.getTeam2().getName() + ": ");
            if (match.getIsMatchPlayed()) {
                System.out.println(match.getScoreTeam1() + " - " + match.getScoreTeam2());
            } else {
                System.out.println("Not played yet");
            }
            System.out.println();
        }
        System.out.println("-------------------------");
    }

    public void determineWinners() {
        System.out.println("Tournament Winners:");
        System.out.println("-------------------------");
        for (Match match : matches) {
            if (match.getIsMatchPlayed()) {
                System.out.println((match.getScoreTeam1() > match.getScoreTeam2() ? match.getTeam1().getName() : match.getTeam2().getName()) + " wins!");
            } else {
                System.out.println("Winner not determined yet for " + match.getTeam1().getName() + " vs " + match.getTeam2().getName());
            }
            System.out.println();
        }
        System.out.println("-------------------------");
    }
}

public class Prac92 {
    public static void main(String[] args) {
        Team team1 = new Team("Team A");
        Team team2 = new Team("Team B");
        Team team3 = new Team("Team C");
        Team team4 = new Team("Team D");

        Tournament tournament = new Tournament();

        tournament.addMatch(team1, team2);
        tournament.addMatch(team3, team4);

        tournament.updateMatchResult(0, 2, 1);
        tournament.updateMatchResult(1, 0, 3);

        tournament.displayResults();
        tournament.determineWinners();
    }
}