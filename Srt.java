import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cuongnb14 on 12/12/2017.
 */

public class Srt {
    String srtText;
    String[] lines;
    ArrayList<SrtLine> alLines;

    public Srt(String srtText) {
        this.srtText = srtText;
        this.lines = srtText.split("\n\r\n");
        if (this.lines.length < 3) {
            this.lines = srtText.split("\n\n");
        }
        this.alLines = new ArrayList<>();
        int lines_len = this.lines.length;
        for (int i = 0; i < lines_len; i++) {
            SrtLine srtLine = new SrtLine();
            String line = lines[i];
            String[] pieces = line.split("\n");
            if (pieces.length >= 3) {
                // Counter
                srtLine.setCounter(Integer.parseInt(pieces[0]));

                // Time
                String startText;
                String endText;

                String timeLine = pieces[1];
                Pattern pattern = Pattern.compile("^[0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9]");
                Matcher matcher = pattern.matcher(timeLine);
                if (matcher.find()) {
                    startText = matcher.group(0);
                }

                pattern = Pattern.compile("\\s[0-9][0-9]:[0-9][0-9]:[0-9][0-9],[0-9][0-9][0-9]");
                matcher = pattern.matcher(timeLine);
                if (matcher.find()) {
                    endText = matcher.group(0).replaceAll(" ", "");
                }

                // TODO; string to date

                // Subtitle
                StringBuilder subtitleBuilder = new StringBuilder("");
                for (int j = 2; j < pieces.length; j++) {
                    subtitleBuilder.append(pieces[j]);
                    subtitleBuilder.append("\n");
                }
                srtLine.setSubtitle(subtitleBuilder.toString());
                this.alLines.add(srtLine);
            }
        }

    }

     public static void main(String[] args){
        System.out.println("ok");
        Srt srt = new Srt("1\n00:00:08,280 --> 00:00:12,300\nI want you to know that it's our time\n\n2\n00:00:12,300 --> 00:00:15,520\nYou and me bleed the same light\n\n3\n00:00:15,520 --> 00:00:19,700\nI want you to know that I'm all yours\n\n4\n00:00:19,700 --> 00:00:22,810\nYou and me run the same course\n\n5\n00:00:22,810 --> 00:00:26,360\nI'm slippin' down a chain reaction\n\n6\n00:00:26,360 --> 00:00:30,230\nAnd here I go, here I go, here I go, go\n\n");
        for (SrtLine line: srt.alLines) {
            System.out.println(line.getSubtitle());
        }
        
    }


}


class SrtLine {
    private int counter;
    private String subtitle;

    public SrtLine() {
    }

    public SrtLine(int counter, String subtitle){
        this.counter = counter;
        this.subtitle = subtitle;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}