public class InfoPerMonthYearReport {
    public int month;
    public int amount;
    public Boolean isExpense;
    public int year;

    public InfoPerMonthYearReport(int month, int amount, Boolean isExpense, int year) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
        this.year = year;
    }
}
