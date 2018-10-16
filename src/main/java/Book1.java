import java.io.StringReader;
import java.util.List;

public class Book1 {

    public static Book1 createBook(String row) {

        StringReader readRow = new StringReader(row);
        List<String> bookEl = null;
        try {
            bookEl = CSVHelper.parseLine(readRow);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String date = bookEl.get(0);
        int dupes = Integer.parseInt(bookEl.get(1));

        return new Book1(date,dupes);
    }

    private String date;
    private int dupes;

    public Book1(String date, int dupes) {
        this.date = date;
        this.dupes = dupes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDupes() {
        return dupes;
    }

    public void setDupes(int dupes) {
        this.dupes = dupes;
    }
}
