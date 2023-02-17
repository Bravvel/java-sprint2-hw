import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        Scanner scanner = new Scanner(System.in);
        Boolean checkCommand1 = false;
        Boolean checkCommand2 = false; // нужны для проверки того, были ли считаны отчёты, чтобы не считывать лишние данные
        Boolean[] checkCommand1perMonth = new Boolean[3]; // отмечаю какой месячный отчет был считан , а какой нет
        while(true){
            for(int i = 0; i < 3; i++){
                checkCommand1perMonth[i] = false;
            }
            printMenu();
            String command = scanner.nextLine();
            if(command.equals("1")){
                if(checkCommand1){
                    System.out.println("Месячные отчёты уже были считатны");
                }else{
                    for(int i = 1; i <= 3; i++){
                        monthlyReport.loadFile(i, "resources/m.20210" + i + ".csv");
                        if(monthlyReport.checkInput){ // проверяю, считался ли файл
                            checkCommand1perMonth[i-1] = true;
                        }
                    }
                    int cnt = 0;
                    for (int i = 0; i < 3; i++) { // считаю, сколько отчётов было считано
                        if(checkCommand1perMonth[i]){
                            cnt++;
                        }
                    }
                    if(cnt == 3){ // если считаны все три отчёта ,то отмечаю , что считывать месячные отчёты уже не нужно
                        checkCommand1 = true;
                    }
                }
            } else if(command.equals("2")){
                if(checkCommand2){
                    System.out.println("Годовой отчёт уже был считан");
                }else{
                    yearlyReport.loadFile(2021,"resources/y.2021.csv");
                }
                if(yearlyReport.checkInput){
                    checkCommand2 = true;
                }
            } else if(command.equals("3")){
                if(!checkCommand1 || !checkCommand2){
                    System.out.println("Годовой или месячные отчёты не были считаны, невозможно провести сверку.");
                    System.out.println("Пожалуйста, перед тем, как делать сверку, считайте годовой и месячные отчёты.");
                    System.out.println("Возможно какой-то из отчётов не находится в нужной директории");
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
                    System.out.println("Возможно какой-то из месячных отчётов не находится в нужной директории");
                } else {
                    for(int i = 1; i <= 3; i++){
                        monthlyReport.printMonthStatistics(i);
                    }
                }
            } else if(command.equals("5")){
                if(!checkCommand2){
                    System.out.println("Годовой отчёт не был считан, невозможно вывести статистику по годовому отчёту.");
                    System.out.println("Пожалуйста, перед тем, как напечатать статистику, считайте годовой отчёт.");
                    System.out.println("Возможно годовой отчёт не находится в нужной директории");
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

