// Інтерфейси для продуктів
interface Button {
    fun paint() // Метод для малювання кнопки
}

interface Checkbox {
    fun paint() // Метод для малювання чекбокса
}

// Класи кнопок та чекбоксів для Windows
class WinButton : Button {
    override fun paint() {
        println("Відобразити кнопку в стилі Windows.")
    }
}

class WinCheckbox : Checkbox {
    override fun paint() {
        println("Відобразити чекбокс в стилі Windows.")
    }
}

// Класи кнопок та чекбоксів для macOS
class MacButton : Button {
    override fun paint() {
        println("Відобразити кнопку в стилі macOS.")
    }
}

class MacCheckbox : Checkbox {
    override fun paint() {
        println("Відобразити чекбокс в стилі macOS.")
    }
}

// Інтерфейс фабрики для створення кнопок і чекбоксів
interface GUIFactory {
    fun createButton(): Button // Створення кнопки
    fun createCheckbox(): Checkbox // Створення чекбокс
}

// Фабрика для Windows
class WinFactory : GUIFactory {
    override fun createButton(): Button {
        return WinButton() // Створення кнопки Windows
    }

    override fun createCheckbox(): Checkbox {
        return WinCheckbox() // Створення чекбокс Windows
    }
}

// Фабрика для macOS
class MacFactory : GUIFactory {
    override fun createButton(): Button {
        return MacButton() // Створення кнопки macOS
    }

    override fun createCheckbox(): Checkbox {
        return MacCheckbox() // Створення чекбокс macOS
    }
}

// Клас програми, яка використовує фабрику
class Application(private val factory: GUIFactory) {
    private lateinit var button: Button // Оголошення кнопки, але поки без створення

    fun createUI() {
        button = factory.createButton() // Створення кнопки через фабрику
    }

    fun paint() {
        button.paint() // Виклик методу малювання кнопки
    }
}

// Вирішення, яку фабрику використовувати
object ApplicationConfigurator {
    @JvmStatic
    fun main(args: Array<String>) {
        // Читання конфігурації (псевдокод)
        val config = readApplicationConfigFile()

        // Створюємо фабрику в залежності від ОС
        val factory: GUIFactory = when (config.OS) {
            "Windows" -> WinFactory()
            "Mac" -> MacFactory()
            else -> WinFactory() // Вибір за замовчуванням Windows
        }

        // Створення програми з використанням фабрики
        val app = Application(factory)
        app.createUI()
        app.paint()
    }

    // Метод, який повертає конфігурацію з ОС
    private fun readApplicationConfigFile(): Config {
        return Config("Windows") // Для тесту повертаємо Windows
    }
}

// Клас конфігурації (простий клас для збереження ОС)
data class Config(val OS: String)

