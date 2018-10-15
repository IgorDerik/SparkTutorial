import java.io.Serializable;
import java.io.StringReader;
import java.util.List;

public class Match implements Serializable {

    private static int parseInt(String s) {

        int result = 0;

        if (!s.equals("")) {
            try {
                result = Integer.parseInt(s);
            }
            catch (NumberFormatException e) {
                System.out.println(s+" not a number");
            }
        }

        return result;
    }

    private static long parseLong(String s) {

        long result = 0L;

        if (!s.equals("")) {
            try {
                result = Long.parseLong(s);
            }
            catch (NumberFormatException e) {
                System.out.println(s+" not a number");
            }
        }

        return result;
    }

    public static Match createMatch(String row) {

        StringReader readRow = new StringReader(row);
        //String[] matchElements = row.split(",");
        //List<String> matchElements = CSVUtils.parseLine(row);
        List<String> matchElements = null;
        try {
            matchElements = CSVHelper.parseLine(readRow);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int year = parseInt(matchElements.get(0));
        String datetime = matchElements.get(1);
        String stage = matchElements.get(2);
        String stadium = matchElements.get(3);
        String city = matchElements.get(4);
        String homeTeamName = matchElements.get(5);
        int homeTeamGoals = parseInt(matchElements.get(6));
        int awayTeamGoals = parseInt(matchElements.get(7));
        String awayTeamName = matchElements.get(8);
        String winConditions = matchElements.get(9);
        long attendance = parseLong(matchElements.get(10));
        int halfTimeHomeGoals = parseInt(matchElements.get(11));
        int halfTimeAwayGoals = parseInt(matchElements.get(12));
        String referee = matchElements.get(13);
        String assistant1 = matchElements.get(14);
        String assistant2 = matchElements.get(15);
        long roundID = parseLong(matchElements.get(16));
        long matchID = parseLong(matchElements.get(17));
        String homeTeamInitials = matchElements.get(18);
        String awayTeamInitials = matchElements.get(19);

        return new Match(year, datetime, stage, stadium, city, homeTeamName,
                         homeTeamGoals, awayTeamGoals, awayTeamName, winConditions, attendance,
                         halfTimeHomeGoals, halfTimeAwayGoals, referee, assistant1, assistant2,
                         roundID, matchID, homeTeamInitials, awayTeamInitials);
    }

    private int year;
    private String datetime;
    private String stage;
    private String stadium;
    private String city;
    private String homeTeamName;
    private int homeTeamGoals;
    private int awayTeamGoals;
    private String awayTeamName;
    private String winConditions;
    private long attendance;
    private int halfTimeHomeGoals;
    private int halfTimeAwayGoals;
    private String referee;
    private String assistant1;
    private String assistant2;
    private long roundID;
    private long matchID;
    private String homeTeamInitials;
    private String awayTeamInitials;

    public Match(int year, String datetime, String stage, String stadium, String city, String homeTeamName,
                 int homeTeamGoals, int awayTeamGoals, String awayTeamName, String winConditions, long attendance,
                 int halfTimeHomeGoals, int halfTimeAwayGoals, String referee, String assistant1, String assistant2,
                 long roundID, long matchID, String homeTeamInitials, String awayTeamInitials) {
        this.year = year;
        this.datetime = datetime;
        this.stage = stage;
        this.stadium = stadium;
        this.city = city;
        this.homeTeamName = homeTeamName;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.awayTeamName = awayTeamName;
        this.winConditions = winConditions;
        this.attendance = attendance;
        this.halfTimeHomeGoals = halfTimeHomeGoals;
        this.halfTimeAwayGoals = halfTimeAwayGoals;
        this.referee = referee;
        this.assistant1 = assistant1;
        this.assistant2 = assistant2;
        this.roundID = roundID;
        this.matchID = matchID;
        this.homeTeamInitials = homeTeamInitials;
        this.awayTeamInitials = awayTeamInitials;
    }

    public int getYear() {
        return year;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getStage() {
        return stage;
    }

    public String getStadium() {
        return stadium;
    }

    public String getCity() {
        return city;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public String getWinConditions() {
        return winConditions;
    }

    public long getAttendance() {
        return attendance;
    }

    public int getHalfTimeHomeGoals() {
        return halfTimeHomeGoals;
    }

    public int getHalfTimeAwayGoals() {
        return halfTimeAwayGoals;
    }

    public String getReferee() {
        return referee;
    }

    public String getAssistant1() {
        return assistant1;
    }

    public String getAssistant2() {
        return assistant2;
    }

    public long getRoundID() {
        return roundID;
    }

    public long getMatchID() {
        return matchID;
    }

    public String getHomeTeamInitials() {
        return homeTeamInitials;
    }

    public String getAwayTeamInitials() {
        return awayTeamInitials;
    }

    @Override
    public String toString() {
        return "Match{" +
                "year=" + year +
                ", datetime='" + datetime + '\'' +
                ", stage='" + stage + '\'' +
                ", stadium='" + stadium + '\'' +
                ", city='" + city + '\'' +
                ", homeTeamName='" + homeTeamName + '\'' +
                ", homeTeamGoals=" + homeTeamGoals +
                ", awayTeamGoals=" + awayTeamGoals +
                ", awayTeamName='" + awayTeamName + '\'' +
                ", winConditions='" + winConditions + '\'' +
                ", attendance=" + attendance +
                ", halfTimeHomeGoals=" + halfTimeHomeGoals +
                ", HalfTimeAwayGoals=" + halfTimeAwayGoals +
                ", referee='" + referee + '\'' +
                ", assistant1='" + assistant1 + '\'' +
                ", assistant2='" + assistant2 + '\'' +
                ", roundID=" + roundID +
                ", matchID=" + matchID +
                ", homeTeamInitials='" + homeTeamInitials + '\'' +
                ", awayTeamInitials='" + awayTeamInitials + '\'' +
                '}';
    }
}