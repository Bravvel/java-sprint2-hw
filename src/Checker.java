public class Checker {
    public MonthlyReport monthlyReport;
    public YearlyReport yearlyReport;

    public Checker(MonthlyReport monthlyReport, YearlyReport yearlyReport){
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;
    }

    public boolean check(Integer monthNow){ // сверка отчётов
        Boolean isDifferece = false;
        monthlyReport.profitAndExpencePerMonth(monthNow);
        yearlyReport.profitAndExpence();
        if( (monthlyReport.expenceInMonth.get(monthNow) - yearlyReport.expenceInMonth.get(monthNow))  != 0 ){
            isDifferece = true;
        }
        if((monthlyReport.profitInMonth.get(monthNow) - yearlyReport.profitInMonth.get(monthNow)) != 0){
            isDifferece = true;
        }
        if(isDifferece){
            if(monthNow == 1){
                System.out.println("Наблюдаются расхождения в отчёте за Январь: ");
                if( (monthlyReport.expenceInMonth.get(monthNow) - yearlyReport.expenceInMonth.get(monthNow))  != 0 ){
                    System.out.println("Расходы за месяц в месячном отчёте составили " + monthlyReport.expenceInMonth.get(monthNow) + ", а в годовом " + yearlyReport.expenceInMonth.get(monthNow));
                }
                if( (monthlyReport.profitInMonth.get(monthNow) - yearlyReport.profitInMonth.get(monthNow))  != 0 ){
                    System.out.println("Доходы за месяц в месячном отчёте составили " + monthlyReport.profitInMonth.get(monthNow) + ", а в годовом " + yearlyReport.profitInMonth.get(monthNow));
                }
            } else if(monthNow == 2){
                System.out.println("Наблюдаются расхождения в отчёте за Февраль: ");
                if( (monthlyReport.expenceInMonth.get(monthNow) - yearlyReport.expenceInMonth.get(monthNow))  != 0 ){
                    System.out.println("Расходы за месяц в месячном отчёте составили " + monthlyReport.expenceInMonth.get(monthNow) + ", а в годовом " + yearlyReport.expenceInMonth.get(monthNow));
                }
                if( (monthlyReport.profitInMonth.get(monthNow) - yearlyReport.profitInMonth.get(monthNow))  != 0 ){
                    System.out.println("Доходы за месяц в месячном отчёте составили " + monthlyReport.profitInMonth.get(monthNow) + ", а в годовом " + yearlyReport.profitInMonth.get(monthNow));
                }
            } else if(monthNow == 3){
                System.out.println("Наблюдаются расхождения в отчёте за Март: ");
                if( (monthlyReport.expenceInMonth.get(monthNow) - yearlyReport.expenceInMonth.get(monthNow))  != 0 ){
                    System.out.println("Расходы за месяц в месячном отчёте составили " + monthlyReport.expenceInMonth.get(monthNow) + ", а в годовом " + yearlyReport.expenceInMonth.get(monthNow));
                }
                if( (monthlyReport.profitInMonth.get(monthNow) - yearlyReport.profitInMonth.get(monthNow))  != 0 ){
                    System.out.println("Доходы за месяц в месячном отчёте составили " + monthlyReport.profitInMonth.get(monthNow) + ", а в годовом " + yearlyReport.profitInMonth.get(monthNow));
                }
            }
        }
        monthlyReport.profitInMonth.clear();
        monthlyReport.expenceInMonth.clear();
        return isDifferece;
    }

}
