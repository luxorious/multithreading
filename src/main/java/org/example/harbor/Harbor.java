package org.example.harbor;


//6. Написать программу, моделирующую работу порта. Корабли могут заходить в порт
//        для разгрузки / загрузки контейнеров. Количество контейнеров, находящихся в текущий
//        момент в порту и на корабле, не должно превышать заданную грузоподъемность судна и
//        емкость порта. В порту работает несколько причалов. В одном причале может стоять
//        только один корабль. Корабль может загружаться у причала, разгружаться или выполнять оба действия.
//        Нужны любые Ваши идеи. Можете даже менять условие задачи (упростить, например), но
//        модель многопоточной системы должна работать.
public class Harbor {

    private final int maxCapacity;
    private int actualCapacity;
    private final int berthsNumber;
    private int freeBerths;

    public Harbor(int maxCapacity, int berthsNumber) {
        this.maxCapacity = maxCapacity;
        this.actualCapacity = maxCapacity;
        this.berthsNumber = berthsNumber;
        this.freeBerths = berthsNumber;
    }

    public void unload(Ship ship) {
        unload(ship, ship.getActualCapacity());
    }

    public void unload(Ship ship, int quantityContainersForUnloading) {
        if (ship.getActualCapacity() == 0) {
            return;
        }
        if (quantityContainersForUnloading > ship.getMaxCapacity()) {
            quantityContainersForUnloading = ship.getMaxCapacity();
        }
        if (actualCapacity + quantityContainersForUnloading > maxCapacity) {
            int canPutInHarbor = maxCapacity - actualCapacity;
            int leaveOnTheShip = quantityContainersForUnloading - canPutInHarbor;
            ship.setActualCapacity(leaveOnTheShip);
            actualCapacity += canPutInHarbor;
            System.out.println("Ship " + ship.getNameOfShip() +
                    " unloaded, but harbor is full and " +
                    leaveOnTheShip + " containers leaved on the ship");
        } else {
            actualCapacity += ship.getActualCapacity();
            ship.setActualCapacity(0);
            System.out.println("Ship " + ship.getNameOfShip() + " already is unloaded");
        }
    }

    //fill to full
    public void loading(Ship ship) {
        int quantityNeedsToLoad = ship.getMaxCapacity() - ship.getActualCapacity();
        if ((actualCapacity - quantityNeedsToLoad) >= 0) {
            ship.setActualCapacity(quantityNeedsToLoad);
            actualCapacity -= quantityNeedsToLoad;
        } else {
            loading(ship, actualCapacity);
        }
    }

    //fill part.
    public void loading(Ship ship, int quantityContainersForLoading) {
        while (ship.getActualCapacity() < ship.getMaxCapacity()) {
            if (actualCapacity == 0) {
                System.out.println("Harbor is empty");
                break;
            }
            if (quantityContainersForLoading == 0) {
                System.out.println("all ordered goods have been loaded on to the ship");
                break;
            }
            ship.setActualCapacity(ship.getActualCapacity() + 1);
            actualCapacity--;
            quantityContainersForLoading--;
        }
    }

    private boolean isFull(Ship ship) {
        return (actualCapacity + ship.getActualCapacity()) <= maxCapacity;
    }

    private boolean isFreeBerths() {
        return freeBerths > 0;
    }

    public void setActualCapacity(int actualCapacity) {
        this.actualCapacity = actualCapacity;
    }

    public synchronized void setFreeBerths(int freeBerths) {
        this.freeBerths = freeBerths;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getActualCapacity() {
        return actualCapacity;
    }

    public int getBerthsNumber() {
        return berthsNumber;
    }

    public int getFreeBerths() {
        return freeBerths;
    }
}
