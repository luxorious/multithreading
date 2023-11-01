package org.example.harbor;

public class Ship {
//    6. Написать программу, моделирующую работу порта. Корабли могут заходить в порт
//    для разгрузки / загрузки контейнеров. Количество контейнеров, находящихся в текущий
//    момент в порту и на корабле, не должно превышать заданную грузоподъемность судна и
//    емкость порта. В порту работает несколько причалов. В одном причале может стоять
//    только один корабль. Корабль может загружаться у причала, разгружаться или выполнять оба действия.
//    Нужны любые Ваши идеи. Можете даже менять условие задачи (упростить, например), но
//    модель многопоточной системы должна работать.

    private int maxCapacity;
    private int actualCapacity = 0;
    private String nameOfShip;

    public Ship(int maxCapacity, String nameOfShip) {
        this.maxCapacity = maxCapacity;
        this.nameOfShip = nameOfShip;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getActualCapacity() {
        return actualCapacity;
    }

    public String getNameOfShip() {
        return nameOfShip;
    }

    public void setActualCapacity(int actualCapacity) {
        this.actualCapacity = actualCapacity;
    }

    @Override
    public String toString() {
        return "Ship - " + nameOfShip + " loaded by " +
                actualCapacity + " of " + maxCapacity;
    }
}
