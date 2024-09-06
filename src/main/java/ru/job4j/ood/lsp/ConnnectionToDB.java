package ru.job4j.ood.lsp;


/**
 *  !!! Нельзя сокращать функционал в классах-наследниках !!!
 *  Если какой-то программист возьмёт и наследуется от класса ConnectionToAnotherDB()
 *  и попытается вызвать метод renameConnection()(или другой не реализованный класс) у своего класса-наследника,
 *  то он потратит кучу времени на выяснение причины появления NotImplementedException.
 *  Это можно увидеть запустив метод main() класса GrandsonOfConnectionToDB.
 *  (В конце этого файла)
 */

public class ConnnectionToDB {

    public ConnnectionToDB() {
    }

    public String createConnection() {
        return "Connection 1 was created";
    }

    public String closeConnection() {
        return "Connection 1 was closed";
    }

    public String renameConnection(String newName) {
        return "Connection was renamed to " + newName;
    }

    /* И ещё много других методов */
}

/**
 *  Класс-наследник класса ConnectionToDB,
 *  в котором, "урезан" функционал класса-родителя.
 */
class ConnectionToAnotherDB extends ConnnectionToDB {

    public ConnectionToAnotherDB() {
    }

    @Override
    public String createConnection() {
        return "Connection to another DB was created";
    }

    @Override
    public String closeConnection() {
        throw new NotImplementedException();
    }

    @Override
    public String renameConnection(String newName) {
        throw new NotImplementedException();
    }
}

/**
 *  "Самодельное" исключение, которое выбрасывается методами,
 *  которые ещё не реализованы.
 */
class NotImplementedException extends UnsupportedOperationException {
    public NotImplementedException() {
        super("Not implemented yet.");
    }
}

/**
 * Класс-наследник класса ConnectionToAnotherDB,
 *  запустив метод main() которого, можно воочию наблюдать ошибку,
 *  о которой рассказывается в начале данного файла.
 */
class GrandsonOfConnectionToDB extends ConnectionToAnotherDB {

    public GrandsonOfConnectionToDB() {
    }

    public static void main(String[] args) {
        ConnnectionToDB firstConnection = new ConnnectionToDB();
        System.out.println(firstConnection.createConnection());
        System.out.println(firstConnection.closeConnection());
        ConnnectionToDB secondConnection = new GrandsonOfConnectionToDB();
        System.out.println(secondConnection.createConnection());
        System.out.println(secondConnection.closeConnection());
    }
}
