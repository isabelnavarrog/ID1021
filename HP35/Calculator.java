
public class Calculator {
    Item [] expr;
    int ip;
    Stack stack; // we create a Stack and we call it stack

    public Calculator(Item[] expr) {
        this.expr = expr;
        this.ip = 0;
        this.stack = new Stack(4);
    }
    public void step() {
        Item nxt = expr[ip++];
        switch (nxt.type()) {
            case ADD -> {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(x + y);
                break;
            }
            case SUB -> {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(x - y);
                break;
            }
            case MUL -> {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(x * y);
                break;
            }
            case DIV -> {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(x / y);
                break;
            }
            case VALUE -> {
                stack.push(nxt.value());
                break;
            }
        }
    }
    public int run () {
        while (ip < expr.length) {
            step();
        }
        return stack.pop();
    }

    public static void main(String[] args){
        Item[] expr = {
                Item.Value(3),
                Item.Value(4),
                Item.ADD(),
                Item.Value(2),
                Item.Value(4),
                Item.ADD(),
                Item.MUL(),
                };
        Calculator calc = new Calculator(expr);
        System.out.println(calc.run());
    }
}
