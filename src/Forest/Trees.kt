package Forest

import java.util.*

val AGE_MESSAGE: String = "Age must be more then 0"
val HEIGHT_MESSAGE: String = "Height must be more then 0"
val FOOD_NUM = 10
val ANIMAL_NUM = 10

enum class FoodType {
    WORM, BUMP, FALLEN_BUMP, ROOT, NUT, FALLEN_NUT, MAPLE_LEAVE
}

interface ITreePart {
    var animals: MutableList<IAnimal>
    var foods: Map<FoodType, Int>
}

class TreeCrown : ITreePart {
    override var animals: MutableList<IAnimal> = mutableListOf()
    override var foods: Map<FoodType, Int> = mutableMapOf()
}

class HollowTrunk : ITreePart {
    override var animals: MutableList<IAnimal> = mutableListOf()
    override var foods: Map<FoodType, Int> = mutableMapOf()
}

class TreeRoot : ITreePart {
    override var animals: MutableList<IAnimal> = mutableListOf()
    override var foods: Map<FoodType, Int> = mutableMapOf()
}


interface ITree {
    var age: Int
    var height: Int
    fun addAnimal(animal: IAnimal, treePart: ITreePart)
}

open class Tree(age_: Int, height_: Int) : ITree {
    override var age: Int = 0

    override var height: Int = 0


    var crown: TreeCrown = TreeCrown()
    var hollowTrunk: HollowTrunk = HollowTrunk()
    var root: TreeRoot = TreeRoot()

    init {
        if (age_ > 0)
            this.age = age_
        else throw Exception(AGE_MESSAGE)

        if (height_ > 0)
            this.height = height_
        else throw Exception(HEIGHT_MESSAGE)
    }

    override fun addAnimal(animal: IAnimal, treePart: ITreePart) {
        treePart.animals.add(animal)
    }

    protected fun fillTree() {
        /// Initialise food for all parts of tree
        initialiseFood(crown)
        initialiseFood(hollowTrunk)
        initialiseFood(root)

        /// Initialise food for all parts 0f tree
        initialiseAnimals(crown)
        initialiseAnimals(hollowTrunk)
        initialiseAnimals(root)
    }

    open protected fun initialiseFood(treePart: ITreePart) {}

    open protected fun initialiseAnimals(treePart: ITreePart) {
        for (i in 0..ANIMAL_NUM) {
            addAnimal(animal = Animal(this), treePart = treePart)
        }
    }

    open fun moveOtherTree(animal: IAnimal, otherTree: Tree) {
        when (animal.animalLocation) {
            is TreeCrown -> {
                otherTree.crown.animals.add(animal)
                println("${animal.kindOfAnimal} переместилась на крону дерева $otherTree")
            }
            is HollowTrunk -> {
                otherTree.hollowTrunk.animals.add(animal)
                println("${animal.kindOfAnimal} переместилась в дупло дерева $otherTree")

            }
            is TreeRoot -> {
                otherTree.root.animals.add(animal)
                println("${animal.kindOfAnimal} переместилась в корень дерева $otherTree")
            }
        }
        animal.animalLocation.animals.remove(animal)


    }

    var numCrysis: Int = 0

    open fun treeProcess() {


        try {
            if (Random().nextInt(10) % 3 == 0) {
                val animalIndex = Random().nextInt(crown.animals.size)
                println("${crown.animals[animalIndex].kindOfAnimal} умер на дереве $this из - за голода")
                crown.animals.removeAt(animalIndex)
            }

            if (Random().nextInt(10) % 3 == 0) {
                val animalIndex = Random().nextInt(root.animals.size)
                println("${root.animals[animalIndex].kindOfAnimal} умер на дереве $this из - за голода")
                root.animals.removeAt(Random().nextInt(root.animals.size))
            }

            if (Random().nextInt(10) % 3 == 0) {
                val animalIndex = Random().nextInt(hollowTrunk.animals.size)
                println("${hollowTrunk.animals[animalIndex].kindOfAnimal} умер на дереве $this из - за голода")
                hollowTrunk.animals.removeAt(Random().nextInt(hollowTrunk.animals.size))
            }
             numCrysis++


        }
        catch (e: Exception)
        {

        }

        if (numCrysis > 4) {

            for (i in 0..ANIMAL_NUM / 5) {
                addAnimal(animal = Animal(this), treePart = crown)
                addAnimal(animal = Animal(this), treePart = root)
                addAnimal(animal = Animal(this), treePart = crown)
                println("На дереве $this появилось потомство!")

                if (Random().nextInt(12) % 5 == 3)
                    continue
            }

            crown.foods = mutableMapOf()
            initialiseFood(crown)
            root.foods = mutableMapOf()
            initialiseFood(root)
            hollowTrunk.foods = mutableMapOf()
            initialiseFood(hollowTrunk)
            numCrysis = 0
        }



        try {
            crown.foods.values.elementAt(Random().nextInt(10)).minus(Random().nextInt(5))
            root.foods.values.elementAt(Random().nextInt(10)).minus(Random().nextInt(5))
            hollowTrunk.foods.values.elementAt(Random().nextInt(10)).minus(Random().nextInt(5))
        }
        catch (e: Exception)
        {

        }

    }
}


class FirTree(override var age: Int, override var height: Int) : Tree(age, height) {

    init {
        fillTree()
    }


    override fun initialiseFood(treePart: ITreePart) {

        when (treePart) {
            is TreeCrown -> {
                crown.foods.plus(Pair(FoodType.BUMP, FOOD_NUM))
                crown.foods.plus(Pair(FoodType.NUT, FOOD_NUM))
            }

            is TreeRoot -> {
                root.foods.plus(Pair(FoodType.FALLEN_BUMP, FOOD_NUM))
                root.foods.plus(Pair(FoodType.FALLEN_NUT, FOOD_NUM))
                root.foods.plus(Pair(FoodType.ROOT, FOOD_NUM / 2))
            }

            is HollowTrunk -> {
                hollowTrunk.foods.plus(Pair(FoodType.WORM, FOOD_NUM))
            }
        }

    }

    override fun toString(): String {
        return "Ель"
    }

}


class PineTree(override var age: Int, override var height: Int) : Tree(age, height) {

    init {
        fillTree()
    }


    override fun initialiseFood(treePart: ITreePart) {
        when (treePart) {
            is TreeCrown -> {
                crown.foods.plus(Pair(FoodType.BUMP, FOOD_NUM))
                crown.foods.plus(Pair(FoodType.NUT, FOOD_NUM))
            }

            is TreeRoot -> {
                root.foods.plus(Pair(FoodType.FALLEN_BUMP, FOOD_NUM))
                root.foods.plus(Pair(FoodType.FALLEN_NUT, FOOD_NUM))
                root.foods.plus(Pair(FoodType.ROOT, FOOD_NUM / 2))
            }

            is HollowTrunk -> {
                hollowTrunk.foods.plus(Pair(FoodType.WORM, FOOD_NUM))
            }
        }

    }

    override fun toString(): String {
        return "Сосна"
    }
}


class OakTree(override var age: Int, override var height: Int) : Tree(age, height) {

    init {
        fillTree()
    }

    override fun initialiseFood(treePart: ITreePart) {

        when (treePart) {
            is HollowTrunk -> {
                hollowTrunk.foods.plus(Pair(FoodType.WORM, FOOD_NUM))
            }
        }
    }

    override fun toString(): String {
        return "Дуб"
    }
}

class BirchTree(override var age: Int, override var height: Int) : Tree(age, height) {

    init {
        fillTree()
    }


    override fun initialiseFood(treePart: ITreePart) {

        when (treePart) {
            is HollowTrunk -> {
                hollowTrunk.foods.plus(Pair(FoodType.WORM, FOOD_NUM))
            }
        }
    }

    override fun toString(): String {
        return "Береза"
    }
}


class MapleTree(override var age: Int, override var height: Int) : Tree(age, height) {

    init {
        fillTree()
    }


    override fun initialiseFood(treePart: ITreePart) {
        when (treePart) {
            is TreeCrown -> {
                crown.foods.plus(Pair(FoodType.BUMP, FoodType.MAPLE_LEAVE))
            }

            is HollowTrunk -> {
                hollowTrunk.foods.plus(Pair(FoodType.WORM, FOOD_NUM))
            }
        }

    }

    override fun toString(): String {
        return "Клен"
    }
}

class NutTree(override var age: Int, override var height: Int) : Tree(age, height) {

    init {
        fillTree()
    }


    override fun initialiseFood(treePart: ITreePart) {

        when (treePart) {
            is TreeCrown -> {
                crown.foods.plus(Pair(FoodType.BUMP, FOOD_NUM))
                crown.foods.plus(Pair(FoodType.NUT, FOOD_NUM))
            }

            is TreeRoot -> {
                root.foods.plus(Pair(FoodType.FALLEN_BUMP, FOOD_NUM))
                root.foods.plus(Pair(FoodType.FALLEN_NUT, FOOD_NUM))
                root.foods.plus(Pair(FoodType.ROOT, FOOD_NUM / 2))
            }

            is HollowTrunk -> {
                hollowTrunk.foods.plus(Pair(FoodType.WORM, FOOD_NUM))
            }
        }

    }

    override fun toString(): String {
        return "Орех"
    }
}


class Forest {
    val trees: MutableList<Tree> = mutableListOf()
    val TREE_NUM = 3000

    init {

        for (i in 0..TREE_NUM) {
            addTrees()
        }
    }

    fun addTrees() {
        trees.add(FirTree(Random().nextInt(23) + 1, Random().nextInt(25) + 1))
        trees.add(PineTree(Random().nextInt(23) + 1, Random().nextInt(25) + 1))
        trees.add(OakTree(Random().nextInt(23) + 1, Random().nextInt(25) + 1))
        trees.add(NutTree(Random().nextInt(23) + 1, Random().nextInt(25) + 1))
        trees.add(MapleTree(Random().nextInt(23) + 1, Random().nextInt(25) + 1))
        trees.add(PineTree(Random().nextInt(23) + 1, Random().nextInt(25) + 1))
        trees.add(BirchTree(Random().nextInt(23) + 1, Random().nextInt(25) + 1))
    }

    fun forestProcess() {
        for (tree in trees) {
            tree.treeProcess()
            try {
                if (Random().nextInt(41) % 3 == 0) {
                    tree.moveOtherTree(tree.crown.animals.first(), trees.get(Random().nextInt(trees.size)))
                    tree.moveOtherTree(tree.root.animals.first(), trees.get(Random().nextInt(trees.size)))
                    tree.moveOtherTree(tree.hollowTrunk.animals.first(), trees.get(Random().nextInt(trees.size)))
                }
            }
            catch (e : Exception)
            {

            }
        }
    }

}

fun main(args: Array<String>) {
    val forest = Forest()
    while (true)
        forest.forestProcess()
}