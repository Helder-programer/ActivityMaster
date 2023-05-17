import models.activity.Activity;
import models.leisureActivity.LeisureActivity;
import models.physicalActivity.PhysicalActivity;
import models.user.User;
import models.workActivity.WorkActivity;

import java.util.List;
import java.util.Scanner;

import controllers.ActivityController;
import controllers.UserController;

import java.util.Calendar;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ActivityManager {
    private User user;
    private Scanner input = new Scanner(System.in);
    private UserController userController = new UserController();
    private ActivityController activityController = new ActivityController();

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
                    this.addNewActivity();
                    break;
                case 2:
                    this.showAllActivities();
                    break;
                case 3:
                    this.updateActivity();
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
                    System.out.println("Saindo...");
                    // this.input.close();
                    break;
                default:
                    System.out.println("informe uma opcao valida");
            }
        }
    }

    public void init() {
        this.authMenu();
    }

    public void login() throws Exception {
        System.out.println("---Login---");
        System.out.print("Digite o usuario: ");
        String username = this.input.next();
        System.out.print("Digite a senha: ");
        String password = this.input.next();

        User userToLogin = userController.login(username, password);

        if (userToLogin != null) {
            System.out.println("Autenticado!");
            this.user = userToLogin;
            this.menu();
            return;
        }

        System.out.println("Usuario e/ou senha incorretos!");

    }

    public void register() throws Exception {
        System.out.println("---Crie sua conta---");
        System.out.print("Digite o usuario: ");
        String username = this.input.next();
        System.out.print("Digite a senha: ");
        String password = this.input.next();

        User user = new User(username, password);

        userController.register(user);
        System.out.println("Conta criada com sucesso!");
    }

    public void authMenu() {
        int chosenOption = 0;
        String message = """

                --------------
                1-Login;
                2-Criar Conta;
                3-Sair;
                """;
        while (chosenOption != 3) {
            try {
                System.out.println(message);
                chosenOption = this.input.nextInt();
                switch (chosenOption) {
                    case 1:
                        this.login();
                        break;
                    case 2:
                        this.register();
                        break;
                    case 3:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Informe uma opcao valida");
                }
            } catch (Exception error) {
                System.out.println("Erro: " + error);
                this.input.nextLine();
            }

        }
    }

    private void addNewActivity() {
        int activityCategory = 0;
        String dateText = "";
        int activityDuration = 0;
        int activitySatisfaction = 0;
        String activityDescription = "";

        try {
            String message = """

                    ---ADICIONAR NOVA ATIVIDADE---
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
            this.input.nextLine();
            activityDescription = this.input.nextLine();

            // Date Tratament
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate assistentDate = LocalDate.parse(dateText, formatter);
            Calendar activityDate = Calendar.getInstance();
            activityDate.set(assistentDate.getYear(), assistentDate.getMonthValue() - 1, assistentDate.getDayOfMonth());

            switch (activityCategory) {
                case 1:
                    System.out.print("Informe a intensidade da atividade: ");
                    int activityIntensivity = this.input.nextInt();
                    Activity physicalActivity = new PhysicalActivity(activityDate, activityDuration,
                            activitySatisfaction, activityDescription, activityIntensivity, this.user.getId());

                    activityController.create(physicalActivity);

                    break;
                case 2:
                    Activity leisureActivity = new LeisureActivity(activityDate, activityDuration, activitySatisfaction,
                            activityDescription, this.user.getId());
                    activityController.create(leisureActivity);

                    break;
                case 3:
                    System.out.print("Informe a dificuldade da atividade: ");
                    int activityDificultity = this.input.nextInt();
                    Activity workActivity = new WorkActivity(activityDate, activityDuration, activitySatisfaction,
                            activityDescription, activityDificultity, this.user.getId());
                    activityController.create(workActivity);

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
        try {
            System.out.println("---SUAS ATIVIDADES---");
            List<Activity> activities = activityController.findAll(this.user.getId());
            for (Activity activity : activities) {
                System.out.println(activity.toString());
            }
        } catch (Exception error) {
            System.out.println(error);
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

            Activity searchedActivity = activityController.findById(activityId, this.user.getId());

            System.out.print("Digite a data da atividade: ");
            dateText = this.input.next();
            System.out.print("Digite a duracao da atividade: ");
            activityDuration = this.input.nextInt();
            System.out.print("Digite a satisfacao que voce teve na atividade: ");
            activitySatisfaction = this.input.nextInt();
            System.out.print("Digite a descricao da atividade: ");
            this.input.nextLine();
            activityDescription = this.input.nextLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate assistentDate = LocalDate.parse(dateText, formatter);
            Calendar activityDate = Calendar.getInstance();
            activityDate.set(assistentDate.getYear(), assistentDate.getMonthValue() - 1, assistentDate.getDayOfMonth());

            if (searchedActivity instanceof PhysicalActivity) {
                System.out.print("Informe a intensidade da atividade: ");
                activityIntensivity = this.input.nextInt();

                PhysicalActivity physicalActivity = new PhysicalActivity(activityId, activityDate, activityDuration,
                        activitySatisfaction, activityDescription, activityIntensivity);

                searchedActivity = physicalActivity;

                activityController.update(searchedActivity);

            } else

            if (searchedActivity instanceof WorkActivity) {
                System.out.print("Informe a dificuldade da atividade: ");
                activityDificultity = this.input.nextInt();

                WorkActivity workActivity = new WorkActivity(activityId, activityDate, activityDuration,
                        activitySatisfaction, activityDescription, activityDificultity);
                searchedActivity = workActivity;

                activityController.update(searchedActivity);
            } else {
                LeisureActivity leisureActivity = new LeisureActivity(activityId, activityDate, activityDuration, activitySatisfaction, activityDescription);
                searchedActivity = leisureActivity;
                activityController.update(searchedActivity);
            }

            System.out.println("ATIVIDADE EDITADA COM SUCESSO!");
        } catch (Exception error) {
            System.out.println("Erro: " + error + ". Voltando ao menu...");
            this.input.nextLine();
            return;
        }
    }

    private void removeActivity() {
        try {
            System.out.println("Informe o id da atividade que deseja remover");
            int activityId = this.input.nextInt();

            Activity searchedActivity = activityController.findById(activityId, this.user.getId());

            activityController.delete(searchedActivity);

            System.out.print("ATIVIDADE REMOVIDA COM SUCESSO!");
        } catch (Exception error) {
            System.out.println("Erro: " + error + ". Voltando ao menu...");
            this.input.nextLine();
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
        double totalEnergyExpense = 0;
        double totalWellBeing = 0;

        try {
            System.out.print("Informe da data inicial: ");
            String initialDateText = this.input.next();
            System.out.print("Informe da data final: ");
            String finalDateText = this.input.next();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate assistentInitialDate = LocalDate.parse(initialDateText, formatter);
            LocalDate assistentFinalDate = LocalDate.parse(finalDateText, formatter);

            Calendar initialDate = Calendar.getInstance();
            Calendar finalDate = Calendar.getInstance();

            initialDate.set(assistentInitialDate.getYear(), assistentInitialDate.getMonthValue() - 1,
                    assistentInitialDate.getDayOfMonth());
            finalDate.set(assistentFinalDate.getYear(), assistentFinalDate.getMonthValue() - 1,
                    assistentFinalDate.getDayOfMonth());

            List<Activity> activities = activityController.findByDate(initialDate, finalDate, this.user.getId());


            System.out.println("---ATIVIDADES FILTRADAS---");
            for (Activity activity : activities) {
                System.out.println(activity.toString());
                totalEnergyExpense += activity.calculateEnergyExpense();
                totalWellBeing += activity.calculateWellBeing();
            }

            System.out.println("\n\nGASTO DE ENERGIA TOTAL: " + totalEnergyExpense);
            System.out.println("BEM-ESTAR TOTAL: " + totalWellBeing);

        } catch (Exception error) {
            System.out.println("Erro: " + error + ". Voltando ao menu...");
            this.input.nextLine();
            return;
        }
    }

    private void filterWithCategories() {
        try {
            String message = """

                    ---FILTRO POR CATEGORIA---
                    1-Atividade Física
                    2-Atividade de Lazer
                    3-Atividade de Trabalho
                    """;
            System.out.println(message);
            int activityCategory = this.input.nextInt();

            List<Activity> searchedActivities = activityController.findByCategory(activityCategory, this.user.getId());

            System.out.println("---ATIVIDADES FILTRADAS---\n");
            for (Activity activity : searchedActivities) {
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
            System.out.println("---ATIVIDADES COM MAIS GASTO DE ENERGIA---");
            System.out.println("TOP 3 ATIVIDADES COM MAIS GASTO DE ENERGIA");

            List<Activity> activities = activityController.ranking(this.user.getId());

            for (int counter = 0; counter < 3; counter++) {
                System.out.println("RANKING: " + (counter + 1));
                System.out.println(activities.get(counter).toString());

            }

        } catch (Exception error) {
            System.out.println("Erro: Voce nao possui 3 atividades ou mais. Voltando ao menu...");
            return;
        }
    }
}