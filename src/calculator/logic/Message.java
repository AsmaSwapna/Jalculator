// Messages can tell  the brain what to do. Eg  an AddMessage tell the
// brain to add.

package calculator.logic;

public abstract class Message {
    abstract void action(Brain brain);
}
