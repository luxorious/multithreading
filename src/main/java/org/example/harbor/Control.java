package org.example.harbor;

public class Control {
    private Harbor harbor;

    public Control(Harbor harbor) {
        this.harbor = harbor;
    }

    public void canUnload(Ship ship, Harbor harbor) {
        int containers = quantityContainers("to unload the entire ship " + ship.getNameOfShip() +
                ", press 'a' or enter the number of containers") - 1;
        if (containers == -1) {
            harbor.unload(ship);
        } else {
            harbor.unload(ship, containers + 1);
        }
    }

    public void canLoading(Ship ship, Harbor harbor) throws IndexOutOfBoundsException {
        int containers = quantityContainers("to load the entire ship " + ship.getNameOfShip() +
                ", press 'a' or enter the number of containers") - 1;
        if (containers == -1) {
            harbor.loading(ship);
        } else {
            harbor.loading(ship, containers + 1);
        }
    }

    private boolean isFreeBerths() {
        return this.harbor.getFreeBerths() > 0;
    }

    private int quantityContainers(String message) {
        while (true) {
            try {
                String input = UserInput.input(message);
                if (isFreeBerths() && input.equalsIgnoreCase("a")) {
                    return 0;
                } else if (isFreeBerths()) {
                    return Integer.parseInt(input);
                }
            } catch (NumberFormatException e) {
                System.out.println("wrong input");
            }
        }
    }
}
