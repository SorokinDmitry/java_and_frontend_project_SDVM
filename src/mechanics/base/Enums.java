package base;

/**
 * Created by Vadim on 08.11.14.
 */
enum Codes {
    OK,
    EMPTY,                  // Мимо
    DECK,                   // Попал
    KILLED,
    IS_FIRED,               // Возможно, если в ячейку уже стреляли
    CELL_DOES_NOT_EXIST,    // Возможно при выходе за пределы поля
    USER_PLAYS,
    USER_NOT_FOUND,
    FIELD_EXIST,

}