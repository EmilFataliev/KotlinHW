package Forest

import java.util.*

enum class AnimalsType {
    Squirrel, FlyingSquirrel, Woodpecker, Chipmunk, Badger
}

enum class Sex {
    MEN, WOMEN
}

interface IAnimal {
    var animalLocation: ITreePart
    val animalSex: Sex
    val kindOfAnimal: AnimalsType
}

class Animal(myTree: Tree): IAnimal {
    override lateinit var animalLocation: ITreePart
    override val animalSex: Sex
    override val kindOfAnimal: AnimalsType

    init {

        animalSex = when(Random().nextInt(2))
        {
            0 -> Sex.MEN
            1 -> Sex.WOMEN
            else -> Sex.MEN
        }

        kindOfAnimal = when (Random().nextInt(5)) {
            0 -> AnimalsType.Squirrel
            1 -> AnimalsType.FlyingSquirrel
            2 -> AnimalsType.Woodpecker
            3 -> AnimalsType.Chipmunk
            4 -> AnimalsType.Badger
            else -> AnimalsType.Badger
        }

        initialiseMyTree(myTree)
    }

    fun initialiseMyTree(myTree: Tree){

        if (kindOfAnimal == AnimalsType.Squirrel || kindOfAnimal == AnimalsType.FlyingSquirrel || kindOfAnimal == AnimalsType.Woodpecker)
            animalLocation = myTree.hollowTrunk
        else
            animalLocation = myTree.root
    }
}
