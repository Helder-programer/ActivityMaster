import activities.Activity;
import activities.LeisureActivity;
import activities.PhysicalActivity;
import activities.WorkActivity;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.IsoFields;

public class ActivityManager {
    private List<Activity> database = new ArrayList<Activity>();
    private Scanner input = new Scanner(System.in);

    private void menu() {
        int chosenOption = 0;
        String message = """


                ***BEM VINDO AO ACTIVITY MASTER***;
                1-Cadastrar nova atividade;
                2-Mostrar todas as atividades;
                3-Editar atividade;
                4-Deletar atividade;
                5-Filtrar atividades;
                6-Top 3 atividades com mais gasto de energia;
                7-Sair
                """;

        while (chosenOption != 7) {
            System.out.println(message);
            try {
                chosenOption = this.input.nextInt();
            } catch (Exception error) {
                this.input.nextLine();
            }

            switch (chosenOption) {
                case 1:
                    addNewActivity();
                    break;
                case 3:
                    updateActivity();
                    break;
                case 2:
                    showAllActivities();
                    break;
                case 4:
                    removeActivity();
                    break;
                case 5:
                    filterActivities();
                    break;
                case 6:
                    activitiesRanking();
                    break;
                case 7:
                    System.out.println("Saindo...");
                    this.input.close();
                    break;
                default:
                    System.out.println("informe uma opcao valida");
            }
        }
    }

    public void init() {
        this.menu();
    }

    private void addNewActivity() {
        int activityCategory = 0;
        Activity newActivity;
        String dateText = "";
        int activityDuration = 0;
        int activitySatisfaction = 0;
        String activityDescription = "";

        try {
            String message = """

                    ***Adicionar Nova Atividade***
                    Informe qual atividade deseja cadastrar:
                    1-Atividade Física
                    2-Atividade de Lazer
                    3-Atividade de Trabalho
                    """;
            System.out.println(message);
            activityCategory = this.input.nextInt();
            boolean isValidActivitycategory = activityCategory == 1 || activityCategory == 2 || activityCategory == 3;

            if (!isValidActivitycategory) {
                System.out.println("Digite apenas categorias de atividades validas");
                return;
            }

            System.out.print("Digite a data da atividade: ");
            dateText = this.input.next();
            System.out.print("Digite a duracao da atividade: ");
            activityDuration = this.input.nextInt();
            System.out.print("Digite a satisfacao que voce teve na atividade: ");
            activitySatisfaction = this.input.nextInt();
            System.out.print("Digite a descricao da atividade: ");
            activityDescription = this.input.next();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate activityDate = LocalDate.parse(dateText, formatter);

            switch (activityCategory) {
                case 1:
                    System.out.print("Informe a intensidade da atividade: ");
                    int activityIntensivity = this.input.nextInt();
                    newActivity = new PhysicalActivity(activityDate, activityDuration, activitySatisfaction,
                            activityDescription, activityIntensivity);
                    this.database.add(newActivity);
                    break;
                case 2:
                    newActivity = new LeisureActivity(activityDate, activityDuration, activitySatisfaction,
                            activityDescription);
                    this.database.add(newActivity);
                    break;
                case 3:

                    System.out.print("Informe a dificuldade da atividade: ");
                    int activityDificultity = this.input.nextInt();
                    newActivity = new WorkActivity(activityDate, activityDuration, activitySatisfaction,
                            activityDescription, activityDificultity);
                    this.database.add(newActivity);

                    break;
            }
            System.out.print("ATIVIDADE CADASTRADA COM SUCESSO!");

        } catch (Exception error) {
            System.out.println("Erro: " + error + ". Voltando ao menu...");
            this.input.nextLine();
            return;
        }
    }

    private void showAllActivities() {
        int currentId = 1;
        System.out.println("***ATIVIDADES PRESENTES NO BANCO DE DADOS:***\n");
        for (Activity activity : this.database) {
            System.out.println("ID: " + currentId + ";\n" + activity.toString());
            currentId++;
        }
    }

    private void updateActivity() {
        int activityId = 0;
        String dateText = "";
        int activityDuration = 0;
        int activitySatisfaction = 0;
        String activityDescription = "";
        int activityDificultity = 0;
        int activityIntensivity = 0;

        try {
            System.out.println("Informe o id da atividade que deseja editar");
            activityId = this.input.nextInt();
            activityId -= 1;

            // Input Section
            System.out.print("Digite a data da atividade: ");
            dateText = this.input.next();
            System.out.print("Digite a duracao da atividade: ");
            activityDuration = this.input.nextInt();
            System.out.print("Digite a satisfacao que voce teve na atividade: ");
            activitySatisfaction = this.input.nextInt();
            System.out.print("Digite a descricao da atividade: ");
            activityDescription = this.input.next();

            DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate activityDate = LocalDate.parse(dateText, date);

            if (this.database.get(activityId) instanceof PhysicalActivity) {
                System.out.print("Informe a intensidade da atividade: ");
                activityIntensivity = this.input.nextInt();

                Activity currentActivity = new PhysicalActivity(activityDate, activityDuration, activitySatisfaction,
                        activityDescription, activityIntensivity);
                this.database.set(activityId, currentActivity);

            } else

            if (this.database.get(activityId) instanceof WorkActivity) {
                System.out.print("Informe a dificuldade da atividade: ");
                activityDificultity = this.input.nextInt();

                Activity currentActivity = new WorkActivity(activityDate, activityDuration, activitySatisfaction,
                        activityDescription, activityDificultity);

                this.database.set(activityId, currentActivity);

            } else {
                Activity currentActivity = new LeisureActivity(activityDate, activityDuration, activitySatisfaction,
                        activityDescription);

                this.database.set(activityId, currentActivity);
            }

            System.out.println("ATIVIDADE EDITADA COM SUCESSO!");
        } catch (Exception error) {
            System.out.println("Erro: " + error + ". Voltando ao menu...");
            input.nextLine();
            return;
        }
    }

    private void removeActivity() {
        try {
            System.out.println("Informe o id da atividade que deseja remover");
            int activityId = this.input.nextInt();

            activityId -= 1;

            this.database.remove(activityId);
            System.out.print("ATIVIDADE REMOVIDA COM SUCESSO!");
        } catch (Exception error) {
            System.out.println("Erro: " + error + ". Voltando ao menu...");
        }
    }

    private void filterActivities() {
        String message = """

                ***INFORME O FILTRO QUE DESEJAR FAZER***
                1-Dia
                2-Semana
                3-Mes
                4-Categoria
                """;

        try {
            System.out.println(message);
            int chosenFilter = this.input.nextInt();

            switch (chosenFilter) {
                case 1:
                    filterAcvitiesPerDay();
                    break;
                case 2:
                    // filterAcvitiesPerWeek();
                    break;
                case 3:
                    filterActiviesPerMonth();
                    break;
                case 4:
                    filterWithCategories();
                    break;
            }
        } catch (Exception error) {
            System.out.println("Erro: " + error + ". Voltando ao menu...");
            this.input.nextLine();
            return;
        }

    }

    private void filterWithCategories() {
        List<Activity> activitiesFilter = new ArrayList<Activity>();
        try {
            String message = """

                    ***FILTRO POR CATEGORIA***
                    1-Atividade Física
                    2-Atividade de Lazer
                    3-Atividade de Trabalho
                    """;
            System.out.println(message);
            int activityCategory = this.input.nextInt();

            switch (activityCategory) {
                case 1:
                    for (Activity activity : this.database) {
                        if (activity instanceof PhysicalActivity)
                            activitiesFilter.add(activity);
                    }
                    break;
                case 2:
                    for (Activity activity : this.database) {
                        if (activity instanceof LeisureActivity)
                            activitiesFilter.add(activity);
                    }
                    break;
                case 3:
                    for (Activity activity : this.database) {
                        if (activity instanceof WorkActivity)
                            activitiesFilter.add(activity);
                    }
                    break;
                default:
                    System.out.println("Informe uma opcao valida");
            }

            System.out.println("ATIVIDADES FILTRADAS:\n");
            for (Activity activity : activitiesFilter) {
                System.out.println(activity.toString());
            }

        } catch (Exception error) {
            System.out.println("Erro: " + error + ". Voltando ao menu...");
            this.input.nextLine();
            return;
        }
    }

    private void filterActiviesPerMonth() {
        try {
            double totalEnergyExpense = 0;
            double totalWellbeing = 0;
            System.out.println("***FILTRAR ATIVIDADES POR MES***");
            System.out.println("Informe o mes pelo qual deseja filtrar:");
            int mounth = this.input.nextInt();
            System.out.println("Informe o ano pelo qual deseja filtrar:");
            int year = this.input.nextInt();

            for (Activity activity : this.database) {
                boolean isEqualMonth = activity.getDateWithoutFormat().getMonthValue() == mounth
                        && activity.getDateWithoutFormat().getYear() == year;

                if (isEqualMonth) {
                    totalEnergyExpense += activity.calculateEnergyExpense();
                    totalWellbeing += activity.calculateWellBeing();
                    System.out.println(activity.toString());
                }
            }
            System.out.println("Gasto de energia total: " + totalEnergyExpense);
            System.out.println("Bem-estar total: " + totalWellbeing);
        } catch (Exception error) {
            System.out.println("Erro: " + error + ". Voltando ao menu...");
            return;
        }
    }

    private void filterAcvitiesPerDay() {
        try {
            double totalEnergyExpense = 0;
            double totalWellbeing = 0;
            System.out.println("***FILTRAR ATIVIDADES POR DIA***");
            System.out.println("Informe o dia pelo qual deseja filtrar:");
            int day = this.input.nextInt();
            System.out.println("Informe o mes pelo qual deseja filtrar:");
            int month = this.input.nextInt();
            System.out.println("Informe o ano pelo qual deseja filtrar:");
            int year = this.input.nextInt();

            for (Activity activity : this.database) {
                boolean isEqualDay = activity.getDateWithoutFormat().getDayOfMonth() == day
                        && activity.getDateWithoutFormat().getMonthValue() == month
                        && activity.getDateWithoutFormat().getYear() == year;

                if (isEqualDay) {
                    totalEnergyExpense += activity.calculateEnergyExpense();
                    totalWellbeing += activity.calculateWellBeing();
                    System.out.println(activity.toString());
                }
            }
            System.out.println("Gasto de energia total: " + totalEnergyExpense);
            System.out.println("Bem-estar total: " + totalWellbeing);
        } catch (Exception error) {
            System.out.println("Erro: " + error + ". Voltando ao menu...");
            return;
        }
    }

    // private void filterAcvitiesPerWeek() {
        // try {
        //     System.out.println("***FILTRAR ATIVIDADES POR SEMANA***");
        //     System.out.println("Informe a semana pela qual deseja filtrar:");
        //     int week = this.input.nextInt();
        //     System.out.println("Informe o ano pelo qual deseja filtrar:");
        //     int year = this.input.nextInt();

        //     for (Activity activity : this.database) {

        //         // ZoneId zoneId = ZoneId.systemDefault();
        //         // Date date = Date.from(activity.getDateWithoutFormat().atStartOfDay(zoneId).toInstant());

        //         // Calendar calendar = Calendar.getInstance();
        //         // calendar.setTime(date);

        //         // System.out.println(calendar.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));

        //         // System.out.print("\nConversion of LocalDate to java.util.Calendar is :- \n"
        //         //         + date);

        //         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //         String dateText = activity.getDateWithoutFormat().format(formatter);
        //         dateText += " 10:15:30 am -03:00";

        //         formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z");
                
        //         ZonedDateTime date = ZonedDateTime.parse("2019-03-27 10:15:30 am -05:00", formatter);

        //         System.out.println(date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));

        //         // boolean isEqualWeek = calendar.getWeekYear() == week
        //         //         && activity.getDateWithoutFormat().getYear() == year;

        //         // if (isEqualWeek)
        //         //     System.out.println(activity.toString());
    //         }

    //     } catch (Exception error) {
    //         System.out.println("Erro: " + error + ". Voltando ao menu...");
    //         return;
    //     }
    // }

    private void activitiesRanking() {
        try {
            System.out.println("***ATIVIDADES COM MAIS GASTO DE ENERGIA***");
            List<Activity> activityList = new ArrayList<Activity>();
            activityList = this.database;

            Collections.sort(activityList);
            Collections.reverse(activityList);

            System.out.println("TOP 3 ATIVIDADES COM MAIS GASTO DE");

            for (int counter = 0; counter < 3; counter++) {
                System.out.println(activityList.get(counter).toString());
            }
        } catch (Exception error) {
            System.out.println("Erro: Voce nao possui 3 atividades ou mais. Voltando ao menu...");
            return;
        }
    }
}