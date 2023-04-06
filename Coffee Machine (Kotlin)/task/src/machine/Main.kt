package machine
enum class Coffee(val water: Int, val milk: Int, val beans: Int, val cost: Int){
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6)
}

class CoffeeMachine {
    var water = 400
    var milk = 540
    var beans = 120
    var disposableCups = 9
    var money = 550

    private fun state() =
        "The coffee machine has:\n" +
                "$water ml of water\n" +
                "$milk ml of milk\n" +
                "$beans g of coffee beans\n" +
                "$disposableCups disposable cups\n" +
                "\$$money of money"

    private fun buy() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
        when (readln()){
            "1" -> make(Coffee.ESPRESSO)
            "2" -> make(Coffee.LATTE)
            "3" -> make(Coffee.CAPPUCCINO)
            "back" -> null
        }
    }
    private fun canMakeCoffee(coffee: Coffee): String {
        return when {
            water < coffee.water -> "Sorry, not enough water!"
            milk < coffee.milk -> "Sorry, not enough milk!"
            beans < coffee.beans -> "Sorry, not enough beans!"
            disposableCups < 1 -> "Sorry, not enough cups!"
            else -> "I have enough resources, making you a coffee!"
        }
    }

    private fun make(coffee: Coffee) {
        val orderStatus = canMakeCoffee(coffee)
        println(orderStatus)
        if (orderStatus  == "I have enough resources, making you a coffee!"){
            water -= coffee.water
            milk -= coffee.milk
            beans -= coffee.beans
            disposableCups--
            money += coffee.cost
        }
    }

    private fun fill() {
        println("Write how many ml of water you want to add: ")
        water += readln().toInt()
        println("Write how many ml of milk you want to add: ")
        milk += readln().toInt()
        println("Write how many grams of coffee beans you want to add: ")
        beans += readln().toInt()
        println("Write how many disposable cups you want to add: ")
        disposableCups += readln().toInt()
    }

    private fun take() {
        println("I gave you \$$money")
        money = 0
    }

    fun choseAction() {
        while (true) {
            println("Write action (buy, fill, take, remaining, exit): ")
            when (readln()) {
                "buy" -> buy()
                "fill" -> fill()
                "take" -> take()
                "remaining" -> println(state())
                "exit" -> break
            }
        }
    }


}

fun main() {
    val coffeeMachine = CoffeeMachine()
//    println(coffeeMachine.state())
    coffeeMachine.choseAction()
//    println(coffeeMachine.state())
}
