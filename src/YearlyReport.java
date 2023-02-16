import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    public ArrayList<InfoPerMonthYearReport> reports = new ArrayList<>(); // вся информация считанная из отчёта
    public HashMap<Integer, Integer> incomePerMonth = new HashMap<>();// месяц, прибыль
    public HashMap<Integer, Integer> profitInMonth = new HashMap<>();// месяц, доход
    public HashMap<Integer, Integer> expenceInMonth = new HashMap<>();//месяц, расход
    public void loadFile(Integer year, String path){ //сохранение данных из файла
        String content = readFileContentsOrNull(path);
        String[] lines = content.split("\r?\n"); // month,amount,is_expense
        for(int i = 1; i < lines.length; i++){
            String line = lines[i];
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            Boolean isExpense = Boolean.parseBoolean(parts[2]);
            InfoPerMonthYearReport infoPerMonthYearReport = new InfoPerMonthYearReport(month, amount, isExpense, year);
            reports.add(infoPerMonthYearReport);
        }
    }

    public Double averageProfit(){ // средние доходы за месяц в течение года
        Double sum = 0.0;
        Integer numOfProfitItems = 0;
        for(InfoPerMonthYearReport info : reports){
            if(!info.isExpense){
                sum += info.amount;
                numOfProfitItems++;
            }
        }
        return (sum/numOfProfitItems);
    }

    public Double averageExpence(){ // средние расходы за месяц в течение года
        Double sum = 0.0;
        Integer numOfExpenceItems = 0;
        for(InfoPerMonthYearReport info : reports){
            if(info.isExpense){
                sum += info.amount;
                numOfExpenceItems++;
            }
        }
        return (sum/numOfExpenceItems);
    }

    public void profitAndExpence(){ // Разделение доходов и расходов по месяцам в разные хешмап
        for(InfoPerMonthYearReport info : reports){
            if(info.isExpense){
                expenceInMonth.put(info.month, info.amount);
            }
            if(!info.isExpense){
                profitInMonth.put(info.month, info.amount);
            }
        }
    }
    public void incomePerMonth(){ // прибыль за каждый месяц в году
        profitAndExpence();
        for (int i = 1; i <= 3; i++) {
            Integer income = profitInMonth.get(i) - expenceInMonth.get(i);
            incomePerMonth.put(i, income);
        }
    }

    public void printStatistis(Integer year){ // печатаем статистику за год
        System.out.println("Годовой отчёт за " + year + " год");
        incomePerMonth();
        System.out.println("Статистика по месяцам: ");
        for(int i = 1; i <= 3; i++){
            if(i == 1){
                System.out.print("Прибыль за Январь составила: ");
            }else if(i == 2){
                System.out.print("Прибыль за Февраль составила: ");
            } else if(i == 3){
                System.out.print("Прибыль за Март составила: ");
            }
            System.out.println(incomePerMonth.get(i));
        }
        System.out.println("Средний доход за все месяцы в году составил " + averageProfit());
        System.out.println("Средний расход за все месяцы в году составил " + averageExpence());
    }

    public String readFileContentsOrNull(String path){
        try{
            return Files.readString(Path.of(path));
        } catch (IOException e){
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно файл не находится в нужной директории.");
            return "";
        }
    }
}
