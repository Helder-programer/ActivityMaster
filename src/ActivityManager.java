import activities.Activity;
import activities.LeisureActivity;
import activities.PhysicalActivity;
import activities.WorkActivity;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class ActivityManager {
    private List<Activity> database = new ArrayList<Activity>();
    private Scanner input = new Scanner(System.in);

    private void menu() {
        int chosenOption = 0;
        String message = "";
        message += "***Bem vindo ao ActivityMaster***\n\n";
        message += "1-Cadastrar nova atividade\n";
        message += "2-Mostrar atividades\n";
        message += "3-Editar atividade\n";
        message += "4-Deletar atividade\n";
        message += "5-Pesquisar atividade por categoria\n";
        message += "6-Relatorio de atividades com mais gasto de energia\n";
        message += "7-Sair";

        while (chosenOption != 7) {
            System.out.println(message);
            chosenOption = this.input.nextInt();
            switch (chosenOption) {
                case 1:
                    addNewActivity();
                    break;
                case 2:
                    showAllActivities();
                    break;
                case 7:
                    System.out.println("Saindo...");
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
        String message = "";
        int activityCategory = 0;
        Activity newActivity;

        message += "***Adicionar Nova Atividade***\n\n";
        message += "Informe qual atividade deseja cadastrar:\n1-Atividade Física\n2-Atividade de Lazer\n3-Atividade de Trabalho";
        System.out.println(message);

        try {
            activityCategory = this.input.nextInt();
            boolean isValidActivitycategory = activityCategory == 1 || activityCategory == 2 || activityCategory == 3;

            if (!isValidActivitycategory) {
                System.out.println("Digite apenas categorias de atividades validas");

                return;
            }

        } catch (Exception error) {
            System.out.println(error);
        }

        System.out.print("Digite a data da atividade: ");
        int activityDate = this.input.nextInt();
        System.out.print("Digite a duracao da atividade: ");
        int activityDuration = this.input.nextInt();
        System.out.print("Digite a satisfacao que voce teve na atividade: ");
        int activitySatisfaction = this.input.nextInt();
        System.out.print("Digite a descricao da atividade: ");
        String activityDescription = this.input.nextLine();

        int currentId = 0;

        if (this.database.isEmpty()) {
            currentId = 1;
        } else {
            int lastId = database.get(database.size() - 1).getId();
            currentId = lastId + 1;
        }

        switch (activityCategory) {
            case 1:
                try {
                    System.out.print("Informe a intensidade da atividade: ");
                    int activityIntensivity = this.input.nextInt();
                    newActivity = new PhysicalActivity(currentId, activityDate, activityDuration, activitySatisfaction,
                            activityDescription, activityIntensivity);
                    this.database.add(newActivity);
                } catch (Exception error) {
                    System.out.println(error);
                }
                break;
            case 2:
                try {
                    newActivity = new LeisureActivity(currentId, activityDate, activityDuration, activitySatisfaction,
                            activityDescription);
                    this.database.add(newActivity);
                } catch (Exception error) {
                    System.out.println(error);
                }
                break;
            case 3:
                try {
                    System.out.print("Informe a dificuldade da atividade: ");
                    int activityDificultity = this.input.nextInt();
                    newActivity = new WorkActivity(currentId, activityDate, activityDuration, activitySatisfaction,
                            activityDescription, activityDificultity);
                    this.database.add(newActivity);
                } catch (Exception error) {
                    System.out.println(error);
                }
                break;

        }
    }

    private void showAllActivities() {
        for (Activity activity:  this.database) {
            System.out.println(activity.toString());
        }
    }
}