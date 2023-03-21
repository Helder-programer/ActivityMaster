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
            try {
                chosenOption = this.input.nextInt();
            } catch (Exception error) {
                this.input.nextLine();
            }

            switch (chosenOption) {
                case 1:
                    addNewActivity();
                    break;
                case 2:
                    showAllActivities();
                    break;
                case 4:
                    removeActivity();
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
        String message = "";
        int activityCategory = 0;
        Activity newActivity;
        int activityDate = 0;
        int activityDuration = 0;
        int activitySatisfaction = 0;
        String activityDescription = "";

        message += "***Adicionar Nova Atividade***\n\n";
        message += "Informe qual atividade deseja cadastrar:\n1-Atividade Física\n2-Atividade de Lazer\n3-Atividade de Trabalho";
        System.out.println(message);

        try {
            activityCategory = this.input.nextInt();
        } catch (Exception error) {
            System.out.println("Insira valores validos!");
            this.input.nextLine();
            return;
        }

        boolean isValidActivitycategory = activityCategory == 1 || activityCategory == 2 || activityCategory == 3;

        if (!isValidActivitycategory) {
            System.out.println("Digite apenas categorias de atividades validas");
            return;
        }

        try {
            System.out.print("Digite a data da atividade: ");
            activityDate = this.input.nextInt();
            System.out.print("Digite a duracao da atividade: ");
            activityDuration = this.input.nextInt();
            System.out.print("Digite a satisfacao que voce teve na atividade: ");
            activitySatisfaction = this.input.nextInt();
            System.out.print("Digite a descricao da atividade: ");
            activityDescription = this.input.next();
        } catch (Exception error) {
            System.out.println("Erro: " + error + ". Voltando ao menu...");
            return;
        }

        switch (activityCategory) {
            case 1:
                try {
                    System.out.print("Informe a intensidade da atividade: ");
                    int activityIntensivity = this.input.nextInt();
                    newActivity = new PhysicalActivity(activityDate, activityDuration, activitySatisfaction,
                            activityDescription, activityIntensivity);
                    this.database.add(newActivity);
                } catch (Exception error) {
                    System.out.println("Erro: " + error + ". Voltando ao menu...");
                    this.input.nextLine();
                    return;
                }
                break;
            case 2:
                try {
                    newActivity = new LeisureActivity(activityDate, activityDuration, activitySatisfaction,
                            activityDescription);
                    this.database.add(newActivity);
                } catch (Exception error) {
                    System.out.println("Erro: " + error + ". Voltando ao menu...");
                    this.input.nextLine();
                    return;
                }
                break;
            case 3:
                try {
                    System.out.print("Informe a dificuldade da atividade: ");
                    int activityDificultity = this.input.nextInt();
                    newActivity = new WorkActivity(activityDate, activityDuration, activitySatisfaction,
                            activityDescription, activityDificultity);
                    this.database.add(newActivity);
                } catch (Exception error) {
                    System.out.println("Erro: " + error + ". Voltando ao menu...");
                    this.input.nextLine();
                    return;
                }
                break;
        }
    }

    private void showAllActivities() {
        int currentId = 1;
        for (Activity activity : this.database) {
            System.out.println("ID: " + currentId + "; " + activity.toString());
            currentId++;
        }
    }

    private void removeActivity() {
        // Scanner this.input = new Scanner(System.in);
        System.out.println("Informe o id da atividade que deseja remover");
        int activityId = this.input.nextInt();

        boolean isValidId = activityId > 0 && activityId <= this.database.size();

        if (!isValidId) {
            System.out.println("Digite um ID Valido");
            this.input.close();
            return;
        }

        try {
            this.database.remove(activityId - 1);
        } catch (Exception error) {
            System.out.println(error);
        }

    }
}