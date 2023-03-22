import activities.Activity;
import activities.LeisureActivity;
import activities.PhysicalActivity;
import activities.WorkActivity;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

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
        int activityDate = 0;
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
            activityDate = this.input.nextInt();
            System.out.print("Digite a duracao da atividade: ");
            activityDuration = this.input.nextInt();
            System.out.print("Digite a satisfacao que voce teve na atividade: ");
            activitySatisfaction = this.input.nextInt();
            System.out.print("Digite a descricao da atividade: ");
            activityDescription = this.input.next();

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
        int activityDate = 0;
        int activityDuration = 0;
        int activitySatisfaction = 0;
        String activityDescription = "";
        int activityDificultity = 0;
        int activityIntensivity = 0;
        Activity activity = new PhysicalActivity();

        try {
            System.out.println("Informe o id da atividade que deseja editar");
            activityId = this.input.nextInt();
            activityId -= 1;

            // Input Section
            System.out.print("Digite a data da atividade: ");
            activityDate = this.input.nextInt();
            System.out.print("Digite a duracao da atividade: ");
            activityDuration = this.input.nextInt();
            System.out.print("Digite a satisfacao que voce teve na atividade: ");
            activitySatisfaction = this.input.nextInt();
            System.out.print("Digite a descricao da atividade: ");
            activityDescription = this.input.next();

            if (this.database.get(activityId) instanceof PhysicalActivity) {

                System.out.print("Informe a intensidade da atividade: ");
                activityIntensivity = this.input.nextInt();
                activity = new PhysicalActivity(activityDate, activityDuration, activitySatisfaction,
                        activityDescription, activityIntensivity);

            } else

            if (this.database.get(activityId) instanceof WorkActivity) {

                System.out.print("Informe a dificuldade da atividade: ");
                activityDificultity = this.input.nextInt();
                activity = new WorkActivity(activityDate, activityDuration, activitySatisfaction,
                        activityDescription, activityDificultity);
            } else {
                activity = new LeisureActivity(activityDate, activityDuration, activitySatisfaction,
                        activityDescription);
            }

            this.database.set(activityId, activity);
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

            this.database.remove(activityId - 1);
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

            System.out.println("ATIVIDADES FILTRADAS");
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
                System.out.println(activityList.get(counter).toString());
            }
        } catch(Exception error) {
            System.out.println("Erro: Voce nao possui 3 atividades ou mais. Voltando ao menu...");
            return;
        }

    }
}