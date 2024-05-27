package com.project;

import java.util.*;
import java.util.stream.Collectors;

public class Mainn {

    Civilization civilization = new Civilization(1000, 1000, 1000, 0, 0, 0, 0, 0, 0, 0);
    private Timer timer = new Timer();
    private List<String> battleReports = new LinkedList<>();
    private List<Unidad> enemyArmy = new ArrayList<>();

    private TimerTask taskAttack = new TimerTask() {
        public void run() {
            System.out.println("Te van a atacar en poco.");
            createEnemyArmy();
            simulateBattle();
        }
    };


    private TimerTask taskResources = new TimerTask() {
        public void run() {
            increaseResources();
        }
    };

    public void startTimerAttack(long delay, long period) {
        timer.schedule(taskAttack, delay, period);
    }

    public void startTimerResources(long delay, long period) {
        timer.schedule(taskResources, delay, period);
    }

    public void limpiarPantalla() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mainMenu() {
        System.out.println("Main Menu");
        System.out.println("1. Create units");
        System.out.println("2. Build structures");
        System.out.println("3. Upgrade technologies");
        System.out.println("4. Battle reports");
        System.out.println("5. Enemy army");
        System.out.println("6. Exit");
    }

    public void buildingsMenu() {
        System.out.println("Structures ");
        System.out.println("1. Church");
        System.out.println("2. Magic Tower");
        System.out.println("3. Farm");
        System.out.println("4. Carpentry");
        System.out.println("5. Smithy");
        System.out.println("6. Return");
    }

    public void crearUnidades() {
    limpiarPantalla();
    System.out.println("Creando Unidades...");
    Scanner scanner = new Scanner(System.in);
    System.out.println("1. Espadachín");
    System.out.println("2. Lancero");
    System.out.println("3. Ballestero");
    System.out.println("4. Cañón");
    System.out.print("\nSelecciona una opción: ");
    int opcion = scanner.nextInt();

    try {
        switch (opcion) {
            case 1:
                civilization.newSwordsman(1);
                break;
            case 2:
                civilization.newSpearman(1);
                break;
            case 3:
                civilization.newCrossbow(1);
                break;
            case 4:
                civilization.newCannon(1);
                break;
            default:
                System.out.println("Opción no válida");
        }
    } catch (ResourceException e) {
        System.out.println("No se pudo crear la unidad: " + e.getMessage());
    }
}


    public void crearEdificios() {
        Scanner scanner = new Scanner(System.in);
        limpiarPantalla();
        while (true) {
            buildingsMenu();
            System.out.print("\nSelect an option: ");
            int opcion = scanner.nextInt();

            try {
                switch (opcion) {
                    case 1:
                        civilization.newChurch(opcion);
                        break;
                    case 2:
                        civilization.newMagicTower(opcion);
                        break;
                    case 3:
                        civilization.newFarm(opcion);
                        break;
                    case 4:
                        civilization.newCarpentry(opcion);
                        break;
                    case 5:
                        civilization.newSmithy(opcion);
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Opción no válida");
                }
            } catch (ResourceException e) {
                System.out.println("No se pudo construir: " + e.getMessage());
            }
        }
    }

    public void mejorarTecnologias() {
        limpiarPantalla();
        System.out.println("Mejorando Tecnologías...");
        // Implementar lógica para mejorar tecnologías
    }

    public void reportesDeBatalla() {
        limpiarPantalla();
        System.out.println("Mostrando Reportes de Batalla...");
        for (String report : battleReports) {
            System.out.println(report);
        }
    }

    public void verEjercitoEnemigo() {
        limpiarPantalla();
        System.out.println("Viendo el Ejército Enemigo...");
        Map<String, Long> conteoUnidades = enemyArmy.stream()
            .collect(Collectors.groupingBy(Unidad::getTipo, Collectors.counting()));
        conteoUnidades.forEach((tipo, cantidad) -> System.out.println(tipo + " " + cantidad));
    }

    public void increaseResources() {
        civilization.setWood(civilization.getWood() + 5);
        civilization.setFood(civilization.getFood() + 20);
        System.out.println("Recursos incrementados: " + civilization.getresources());
    }

    public void createEnemyArmy() {
        // Crear el ejército enemigo basado en las probabilidades
        enemyArmy.clear();
        Random rand = new Random();
        while (civilization.getWood() >= 10) { // Asumiendo que Swordsman cuesta 10 de madera
            int prob = rand.nextInt(100);
            if (prob < 35 && civilization.getWood() >= 10) {
                enemyArmy.add(new Unidad("Swordsman"));
                civilization.setWood(civilization.getWood() - 10);
            } else if (prob < 25 && civilization.getWood() >= 15) {
                enemyArmy.add(new Unidad("Spearman"));
                civilization.setWood(civilization.getWood() - 15);
            } else if (prob < 20 && civilization.getWood() >= 20) {
                enemyArmy.add(new Unidad("Crossbow"));
                civilization.setWood(civilization.getWood() - 20);
            } else if (civilization.getWood() >= 30) {
                enemyArmy.add(new Unidad("Cannon"));
                civilization.setWood(civilization.getWood() - 30);
            }
        }
    }

    public void simulateBattle() {
        // Lógica simplificada para simular una batalla y guardar el reporte
        StringBuilder report = new StringBuilder("Batalla:\n");
        report.append("Tus unidades: ").append(civilization.getArmy()).append("\n");
        report.append("Unidades enemigas: ").append(enemyArmy).append("\n");

        // Simulación de batalla (lógica básica, se puede mejorar)
        if (civilization.getArmy().size() > enemyArmy.size()) {
            report.append("¡Has ganado la batalla!\n");
        } else {
            report.append("Has perdido la batalla.\n");
        }

        battleReports.add(report.toString());
        if (battleReports.size() > 5) {
            battleReports.remove(0);
        }

        System.out.println(report);
    }

    public static void main(String[] args) {
        Main mainInstance = new Main();
        Scanner scanner = new Scanner(System.in);
        mainInstance.limpiarPantalla();

        // Inicia el temporizador con un retraso de 5 minutos (300,000 milisegundos) y un período de 3 minutos (180,000 milisegundos)
        mainInstance.startTimerAttack(300000, 180000);
        mainInstance.startTimerResources(0, 60000);

        while (true) {
            mainInstance.mainMenu();
            System.out.print("\nSelecciona una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    mainInstance.crearUnidades();
                    break;
                case 2:
                    mainInstance.crearEdificios();
                    break;
                case 3:
                    mainInstance.mejorarTecnologias();
                    break;
                case 4:
                    mainInstance.reportesDeBatalla();
                    break;
                case 5:
                    mainInstance.verEjercitoEnemigo();
                    break;
                case 6:
                    System.out.println("Game Over");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }
}

class Unidad {
    private String tipo;

    public Unidad(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
