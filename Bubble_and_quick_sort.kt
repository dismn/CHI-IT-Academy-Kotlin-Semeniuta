import kotlin.random.Random
import kotlin.system.measureTimeMillis

// сортування методом бульбашки (Bubble Sort)
fun bubbleSort(arr: IntArray): IntArray {
    val n = arr.size // Розмір масиву
    // проходження по масиву n-1 разів
    for (i in 0 until n - 1) {
        // порівняння сусідніх елементів
        for (j in 0 until n - i - 1) {
            if (arr[j] > arr[j + 1]) {
                // обмін значень arr[j] та arr[j + 1]
                val temp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = temp
            }
        }
    }
    return arr
}

// алгоритм швидкого сорту (Quick Sort)
fun quickSort(arr: IntArray, low: Int, high: Int) {
    if (low < high) {
        // знаходимо позицію розділу
        val pi = partition(arr, low, high)
        // рекурсивне сортування підмасиву
        quickSort(arr, low, pi - 1) // перед розділом
        quickSort(arr, pi + 1, high) // після розділу
    }
}

// розділ масиву на підмасиви
fun partition(arr: IntArray, low: Int, high: Int): Int {
    val pivot = arr[high] // опорний елемент
    var i = (low - 1) // індекс меншого елемента

    for (j in low until high) {
        if (arr[j] < pivot) {
            i++ // збільшення індексу меншого елементу
            // обмін значень
            val temp = arr[i]
            arr[i] = arr[j]
            arr[j] = temp
        }
    }
    // переміщення опорного елементу на його правильну позицію
    val temp = arr[i + 1]
    arr[i + 1] = arr[high]
    arr[high] = temp
    return i + 1 // повертаємо позицію розділу
}

fun main() {
    val arraySize = 1000
    val randomArray = IntArray(arraySize) { Random.nextInt(0, 10000) }

    // вимірювання часу сортування методом бульбашки
    val bubbleTime = measureTimeMillis {
        bubbleSort(randomArray.copyOf()) // компіювання масиву для сортування
    }
    println("Bubble sorting time: $bubbleTime ms")

    // вимірювання часу для quick sort
    val quickTime = measureTimeMillis {
        quickSort(randomArray.copyOf(), 0, randomArray.size - 1) // копіювання масиву для сортування
    }
    println("Time for a quick sort: $quickTime ms")
}
