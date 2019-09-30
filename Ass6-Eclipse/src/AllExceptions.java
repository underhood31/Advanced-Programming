//package RacerMan;

import java.io.Serializable;

class TileExceptions extends RuntimeException {
    TileExceptions(String Message){
        super(Message);
    }
}

class SnakeBiteException extends TileExceptions{
    SnakeBiteException(String Message){
        super(Message);
    }
}
class CricketBiteException extends TileExceptions{
    CricketBiteException(String Message){
        super(Message);
    }
}
class VultureBiteException extends TileExceptions{
    VultureBiteException(String Message){
        super(Message);
    }
}
class TrampoleneException extends TileExceptions{
    TrampoleneException(String Message){
        super(Message);
    }
}
class GameWinnerException extends RuntimeException{
    GameWinnerException(String Message){
        super(Message);
    }
}
class GameSaveException extends RuntimeException{
    GameSaveException(String message){
        super(message);
    }
}
