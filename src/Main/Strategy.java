package Main;

/**
 * Интерфейс для конструирования фреймов

 */
interface Strategy {
    /**
     * Выполняет стратегию
     * @param board - доска, для которой выполняется стратегия
     */
    void execute(Board board);
}
