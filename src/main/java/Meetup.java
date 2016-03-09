import org.joda.time.DateTime;

public class Meetup {

    private DateTime date;

    public Meetup(int month, int year) {
        this.date = new DateTime(year, month, 1, 0, 0);
    }

    public DateTime day(int dw, MeetupSchedule ms) {
        int shift = Math.abs(date.getDayOfWeek() - dw);
        if (date.getDayOfWeek() < dw) date = (date.plusDays(shift));
        if (date.getDayOfWeek() > dw) date = (date.minusDays(shift - 7));

        if (ms.equals(MeetupSchedule.TEENTH)) {
            int i = 1;
            while (date.getDayOfMonth() + i * 7 < 20) i++;
            date = date.plusWeeks(i - 1);
        }
        if (!(ms.equals(MeetupSchedule.TEENTH) || ms.equals(MeetupSchedule.LAST))) date = date.plusWeeks(ms.ordinal());
        if (ms.equals(MeetupSchedule.LAST)) {
            date = date.dayOfMonth().withMaximumValue();
            shift = Math.abs(date.getDayOfWeek() - dw);
            if (date.getDayOfWeek() < dw) date = (date.plusDays(shift - 7));
            if (date.getDayOfWeek() > dw) date = (date.minusDays(shift));
        }
        return date;
    }
}