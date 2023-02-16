import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class MonthlyReport {
    public ArrayList<Product> products = new ArrayList<>(); // вся информация считанная из отчёта
    public HashMap<Integer, Integer> profitInMonth = new HashMap<>(); // месяц, доход
    public HashMap<Integer, Integer> expenceInMonth = new HashMap<>(); // месяц, расход

    public void loadFile(int nowMonth, String path){ //сохранение данных из файла
        String content = readFileContentsOrNull(path);
        String[] lines = content.split("\r?\n");
        for(int i = 1; i < lines.length; i++) {
            String line = lines[i];//item_name,is_expense,quantity,sum_of_one
            String[] parts = line.split(",");
            String itemName = parts[0];
            Boolean isExpence = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);
            Product monthInfo = new Product(itemName, isExpence, quantity, sumOfOne, nowMonth);
            products.add(monthInfo);
        }
    }

    public ProductAndCost getTopProduct(int monthNow){ // самый доходный продукт в месяце
        HashMap<String, Integer> profitProduct = new HashMap<>();
        for (Product product : products) {
            if(!product.isExpence && product.month == monthNow){
                profitProduct.put(product.itemName, (profitProduct.getOrDefault(product.itemName, 0) + product.quantity)*product.sumOfOne);
            }
        }
        String maxItemName = null;
        for (String itemName : profitProduct.keySet()) {
            if(maxItemName == null){
                maxItemName = itemName;
                continue;
            }
            if(profitProduct.get(maxItemName) < profitProduct.get(itemName)){
                maxItemName = itemName;
            }
        }
        ProductAndCost maxProductAndCost = new ProductAndCost(maxItemName, profitProduct.get(maxItemName));
        return maxProductAndCost;
    }
    public ProductAndCost getLowProduct(int monthNow){ // продукт, на который было соверщено больше всего расходов
        HashMap<String, Integer> expenceProduct = new HashMap<>();
        for (Product product : products) {
            if(product.isExpence && product.month == monthNow){
                expenceProduct.put(product.itemName, (expenceProduct.getOrDefault(product.itemName, 0) + product.quantity)*product.sumOfOne);
            }
        }
        String maxItemName = null;
        for (String itemName : expenceProduct.keySet()) {
            if(maxItemName == null){
                maxItemName = itemName;
                continue;
            }
            if(expenceProduct.get(maxItemName) < expenceProduct.get(itemName)){
                maxItemName = itemName;
            }
        }
        ProductAndCost maxProductAndCost = new ProductAndCost(maxItemName, expenceProduct.get(maxItemName));
        return maxProductAndCost;
    }

    public void printMonthStatistics(Integer monthNow){ //печатаем статистику за определенный месяц
        if(monthNow == 1){
            System.out.println("Месячный отчет за Январь: ");
        } else if(monthNow == 2){
            System.out.println("Месячный отчет за Февраль: ");
        } else if(monthNow == 3){
            System.out.println("Месячный отчет за Март: ");
        }
        System.out.println("Самый прибыльный продукт в этом месяце \"" + getTopProduct(monthNow).productName + "\", его прибыль составила " + getTopProduct(monthNow).costPrice);
        System.out.println("Самый затратный продукт в этом месяце \"" + getLowProduct(monthNow).productName+ "\", затраты на этот продукт составили " + getLowProduct(monthNow).costPrice);
    }

    public void profitAndExpencePerMonth(Integer monthNow){ // сохраняем доходы и расходы по месяцам по разным map
       for(Product product : products){
           if(product.month == monthNow){
               if(product.isExpence){
                   expenceInMonth.put(monthNow, (expenceInMonth.getOrDefault(product.month, 0)) + product.quantity * product.sumOfOne);
               } else{
                   profitInMonth.put(monthNow, (profitInMonth.getOrDefault(product.month, 0)) + product.quantity * product.sumOfOne);
               }
           }
       }
    }

    public String readFileContentsOrNull(String path){
        try{
            return Files.readString(Path.of(path));
        } catch (IOException e){
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            System.out.println("Проблемы с файлом в указанном пути " + path);
            return "";
        }
    }
}
