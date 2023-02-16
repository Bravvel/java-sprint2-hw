// Дмитрий, здравствуйте. Не знаю как с вами связаться, поэтому напишу послание в комментарии.
// Я все критические ошибки исправил, но не знаю, как исправить одну не критическую.
// Можете пожалуйста подсказать, как можно вынести  checkCommand1 и checkCommand2 в классы отчётов по годам и месяцам
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        Scanner scanner = new Scanner(System.in);
        Boolean checkCommand1 = false;
        Boolean checkCommand2 = false; // нужны для проверки того, были ли считаны отчёты, чтобы не считывать лишние данные
        while(true){
            printMenu();
            String command = scanner.nextLine();
            if(command.equals("1")){
                if(checkCommand1){
                    System.out.println("Месячные отчёты уже были считатны");
                }else{
                    for(int i = 1; i <= 3; i++){
                        monthlyReport.loadFile(i, "resources/m.20210" + i + ".csv");
                    }
                }
                checkCommand1 = true;
            } else if(command.equals("2")){
                if(checkCommand2){
                    System.out.println("Годовой отчёт уже был считан");
                }else{
                    yearlyReport.loadFile(2021,"resources/y.2021.csv");
                }
                checkCommand2 = true;
            } else if(command.equals("3")){
                if(!checkCommand1 || !checkCommand2){
                    System.out.println("Годовой или месячные отчёты не были считаны, невозможно провести сверку.");
                    System.out.println("Пожалуйста, перед тем, как делать сверку, считайте годовой и месячные отчёты.");
                }else{
                    Checker checker = new Checker(monthlyReport, yearlyReport);
                    for (int i = 1; i <= 3; i++) {
                        if(!checker.check(i)){
                            if(i == 1){
                                System.out.println("Не нашлось ошибок в отчёте за Январь. ");
                            } else if(i == 2){
                                System.out.println("Не нашлось ошибок в отчёте за Февраль. ");
                            } else{
                                System.out.println("Не нашлось ошибок в отчёте за Март. ");
                            }
                        }
                    }
                }
            } else if(command.equals("4")){
                if(!checkCommand1){
                    System.out.println("Месячные отчёты не были считаны, невозможно вывести статистику по месячным отчётам.");
                    System.out.println("Пожалуйста, перед тем, как напечатать статистику, считайте месячные отчёты.");
                } else {
                    for(int i = 1; i <= 3; i++){
                        monthlyReport.printMonthStatistics(i);
                    }
                }
            } else if(command.equals("5")){
                if(!checkCommand2){
                    System.out.println("Годовой отчёт не был считан, невозможно вывести статистику по годовому отчёту.");
                    System.out.println("Пожалуйста, перед тем, как напечатать статистику, считайте годовой отчёт.");
                } else{
                    yearlyReport.printStatistis(2021);
                }
            } else if(command.equals("0")){
                break;
            } else{
                System.out.println("Извините, такой команды пока что не существует");
            }
        }
    }
    public static void printMenu(){
        System.out.println("Какое действие вы хотите выполнить ?");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Закрыть программу");
    }
}

