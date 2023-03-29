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
                7-Incluir 07 atividades para teste
                8-Sair
                """;

        while (chosenOption != 8) {
            System.out.println(message);
            try {
                chosenOption = this.input.nextInt();
            } catch (Exception error) {
                this.input.nextLine();
            }

            switch (chosenOption) {
                case 1:
                    this.addNewActivity();
                    break;
                case 3:
                    this.updateActivity();
                    break;
                case 2:
                    this.showAllActivities();
                    break;
                case 4:
                    this.removeActivity();
                    break;
                case 5:
                    this.filterActivities();
                    break;
                case 6:
                    this.activitiesRanking();
                    break;
                case 7:
                    this.addActivitiesToTest();
                    break;
                case 8:
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
            input.nextLine();
            return;
        }
    }

    private void filterActivities() {
        String message = """

                ***INFORME O FILTRO QUE DESEJAR FAZER***
                1-Data
                2-Categoria
                """;

        try {
            System.out.println(message);
            int chosenFilter = this.input.nextInt();

            switch (chosenFilter) {
                case 1:
                    this.filterActivitiesPerDate();
                    break;
                case 2:
                    this.filterWithCategories();
                    break;
                default:
                    System.out.println("Escolha uma opcao valida. voltando ao menu...");
            }
        } catch (Exception error) {
            System.out.println("Erro: " + error + ". Voltando ao menu...");
            this.input.nextLine();
            return;
        }

    }

    private void filterActivitiesPerDate() {
        try {
            System.out.println("Informe da data inicial");
            System.out.print("Dia: ");
            int initialDay = this.input.nextInt();
            System.out.print("Mês: ");
            int initialMonth = this.input.nextInt();
            System.out.print("Ano: ");
            int initialYear = this.input.nextInt();

            System.out.println("Informe da data final");
            System.out.print("Dia: ");
            int finalDay = this.input.nextInt();
            System.out.print("Mês: ");
            int finalMonth = this.input.nextInt();
            System.out.print("Ano: ");
            int finalYear = this.input.nextInt();

            // Construtor utilizado para validar as datas inseridas
            LocalDate validDate = LocalDate.of(initialYear, initialMonth, initialDay);
            validDate = LocalDate.of(finalYear, finalMonth, finalDay);

            System.out.println("\nATIVIDADES FILTRADAS\n");
            for (Activity activity : this.database) {
                int activityDay = activity.getDateWithoutFormat().getDayOfMonth();
                int activityMonth = activity.getDateWithoutFormat().getMonthValue();
                int activityYear = activity.getDateWithoutFormat().getYear();

                boolean isMatchedActvity = (activityDay >= initialDay && activityDay <= finalDay)
                        && (activityMonth >= initialMonth && activityMonth <= finalMonth)
                        && (activityYear >= initialYear && activityYear <= finalYear);

                if (isMatchedActvity)
                    System.out.println(activity.toString());
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

    private void activitiesRanking() {
        try {
            System.out.println("***ATIVIDADES COM MAIS GASTO DE ENERGIA***");
            List<Activity> activityList = new ArrayList<Activity>();
            activityList = this.database;

            Collections.sort(activityList);
            Collections.reverse(activityList);

            System.out.println("TOP 3 ATIVIDADES COM MAIS GASTO DE");

            for (int counter = 0; counter < 3; counter++) {
                System.out.println("RANKING: " + (counter + 1));
                System.out.println(activityList.get(counter).toString());
            }
        } catch (Exception error) {
            System.out.println("Erro: Voce nao possui 3 atividades ou mais. Voltando ao menu...");
            return;
        }
    }

    private void addActivitiesToTest() {
        try {
            Activity activity01 = new PhysicalActivity(LocalDate.of(2023, 1, 1), 10, 1, "Correr", 2);
            Activity activity02 = new LeisureActivity(LocalDate.of(2023, 1, 1), 50, 1, "Jogar bola");
            Activity activity03 = new WorkActivity(LocalDate.of(2023, 2, 10), 50, 1, "Programar", 2);
            Activity activity04 = new PhysicalActivity(LocalDate.of(2023, 1, 10), 50, 1, "Flexoes", 2);
            Activity activity05 = new WorkActivity(LocalDate.of(2023, 3, 10), 50, 1, "Resolver bugs", 2);
            Activity activity06 = new PhysicalActivity(LocalDate.of(2023, 3, 10), 50, 1, "Academia", 2);
            Activity activity07 = new LeisureActivity(LocalDate.of(2023, 4, 5), 50, 1, "Jogar games");
            this.database.add(activity01);
            this.database.add(activity02);
            this.database.add(activity03);
            this.database.add(activity04);
            this.database.add(activity05);
            this.database.add(activity06);
            this.database.add(activity07);
        } catch (Exception error) {
            System.out.println("Erro: " + error + ". Voltando ao menu...");
            this.input.nextLine();
            return;
        }
    }




}